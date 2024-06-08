from flask import Blueprint, request, jsonify
from flask_restful import Api, Resource
import numpy as np
import pandas as pd
import tensorflow as tf
from sklearn.preprocessing import StandardScaler, MinMaxScaler, LabelEncoder, OneHotEncoder
import joblib
from .models import Place
from . import db

# Load the model
model = tf.keras.models.load_model('models/rec_model.h5')

# Load the scaler
scaler = joblib.load('models/scaler.pkl')

# Load the label encoders
le_category = joblib.load('models/le_category.pkl')
le_city = joblib.load('models/le_city.pkl')

# Load the data
data = pd.read_excel('models/Data_v.7.xlsx')

# Fit the encoders with all unique categories and cities
le_category.fit(data['Category'].unique())
le_city.fit(data['City'].unique())

# Transform the actual data
data['Category'] = data['Category'].apply(lambda x: le_category.transform([x])[
                                          0] if x in le_category.classes_ else -1)
data['City'] = data['City'].apply(lambda x: le_city.transform([x])[
                                  0] if x in le_city.classes_ else -1)

# Handle unknown categories and cities
if -1 in data['Category'].values:
    max_category = data['Category'].max()
    data.loc[data['Category'] == -1, 'Category'] = max_category + 1
    le_category.classes_ = np.append(le_category.classes_, 'Unknown')

if -1 in data['City'].values:
    max_city = data['City'].max()
    data.loc[data['City'] == -1, 'City'] = max_city + 1
    le_city.classes_ = np.append(le_city.classes_, 'Unknown')

X = data[['Category', 'City', 'Rating', 'Price', 'Lat', 'Long']].values

scaler = MinMaxScaler()
X[:, 2:] = scaler.fit_transform(X[:, 2:])

# Step 2: Feature Encoding
num_categories = len(le_category.classes_)
num_cities = len(le_city.classes_)
X_encoded = np.zeros((X.shape[0], num_categories + num_cities + 4))
X_encoded[:, :num_categories] = tf.keras.utils.to_categorical(
    X[:, 0], num_classes=num_categories)
X_encoded[:, num_categories:num_categories +
          num_cities] = tf.keras.utils.to_categorical(X[:, 1], num_classes=num_cities)
X_encoded[:, -4:] = X[:, 2:]  # Rating and Price

input_dim = X_encoded.shape[1]
hidden_dim = 64


def get_feature_vector(attraction_name):
    attraction = data[data['Place_Name'] == attraction_name].iloc[0]
    features = [
        attraction['Category'],
        attraction['City'],
        attraction['Rating'],
        attraction['Price'],
        attraction['Lat'],
        attraction['Long']
    ]

    encoded = np.zeros((1, input_dim))
    encoded[0, :num_categories] = tf.keras.utils.to_categorical(
        features[0], num_classes=num_categories)
    encoded[0, num_categories:num_categories +
            num_cities] = tf.keras.utils.to_categorical(features[1], num_classes=num_cities)
    encoded[0, -4:] = scaler.transform([features[2:]])

    return encoded


def get_recommendations(attraction_name, top_k=10):
    query_vector = get_feature_vector(attraction_name)
    query_embedding = model.predict(query_vector)

    all_embeddings = model.predict(X_encoded)
    similarities = np.dot(all_embeddings, query_embedding.T).flatten()

    # Exclude the query itself
    top_indices = similarities.argsort()[::-1][1:top_k+1]
    top_attractions = data.iloc[top_indices]['Place_Name'].tolist()
    top_similarities = similarities[top_indices].tolist()

    recommendations = []
    for idx, (attraction, similarity) in enumerate(zip(top_attractions, top_similarities)):
        attraction_data = data[data['Place_Name'] == attraction].iloc[0]

        try:
            category = le_category.inverse_transform(
                [attraction_data['Category']])[0]
        except ValueError:
            category = 'Unknown'

        try:
            city = le_city.inverse_transform([attraction_data['City']])[0]
        except ValueError:
            city = 'Unknown'

        recommendations.append({
            'rank': int(idx + 1),
            'name': attraction,
            'similarity': float(similarity),
            'category': category,
            'city': city,
            'rating': float(attraction_data['Rating']),
            'price': float(attraction_data['Price']),
            'lat': float(attraction_data['Lat']),
            'long': float(attraction_data['Long'])
        })

    return recommendations


api_bp = Blueprint('api', __name__)
api = Api(api_bp)


class PlaceListResource(Resource):
    def get(self):
        category = request.args.get('category')
        city = request.args.get('city')
        name = request.args.get('name')

        query = Place.query
        if category:
            query = query.filter_by(category=category)
        if city:
            query = query.filter_by(city=city)
        if name:
            query = query.filter(Place.name.ilike(f'%{name}%'))

        places = query.all()
        return jsonify([place.to_dict() for place in places])


class PlaceRecommendationResource(Resource):
    def get(self, place_id):
        place = Place.query.get_or_404(place_id)
        place_name = place.place_name
        recommendations = get_recommendations(place_name)
        return jsonify(recommendations)


class CategoryListResource(Resource):
    def get(self):
        categories = db.session.query(Place.category).distinct().all()
        category_list = [category[0] for category in categories]
        return jsonify(category_list)


class CityListResource(Resource):
    def get(self):
        cities = db.session.query(Place.city).distinct().all()
        city_list = [city[0] for city in cities]
        return jsonify(city_list)


class PopularPlacesResource(Resource):
    def get(self):
        top_k = request.args.get(
            'top_k', default=10, type=int)  # Default top_k to 10
        places = Place.query.order_by(Place.rating.desc()).limit(top_k).all()
        return jsonify([place.to_dict() for place in places])


api.add_resource(PlaceListResource, '/places')
api.add_resource(PlaceRecommendationResource,
                 '/places/<int:place_id>/recommendations')
api.add_resource(CategoryListResource, '/categories')
api.add_resource(CityListResource, '/cities')
api.add_resource(PopularPlacesResource, '/places/popular')
