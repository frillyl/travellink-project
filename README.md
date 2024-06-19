# Machine Learning Path

## Description
Travellink  implements a content-based recommendation system to suggest tourist attractions in Indonesia based on their features. The system uses machine learning techniques to find similar attractions and provide recommendations.

## Dataset
Our team built the dataset by collecting data from several sources such as Kaggle and Google Maps. The dataset includes various features of tourist attractions across Indonesia, which are essential for the recommendation system. The key attributes in the dataset are:

- Place_Name: The name of the tourist attraction.
- Category: The type of attraction.
- City: The city where the attraction is located.
- Rating: The average user rating of the attraction.
- Price: The typical cost associated with visiting the attraction.
- Lat: The latitude coordinate of the attraction.
- Long: The longitude coordinate of the attraction.

## Content Based Filtering
Content-based filtering is a recommendation technique that suggests items similar to those a user has shown interest in, based on the item's features. Unlike collaborative filtering, which relies on user-item interactions, content-based filtering uses the properties of the items themselves to make recommendations. In this project, content-based filtering is used to recommend tourist attractions in Indonesia by comparing their features. The features considered include Category, City, Price, Rating, Longitude, and Latitude. By encoding these features and training a neural network, the system can learn the relationships between different attractions and recommend similar ones based on a query attraction.

