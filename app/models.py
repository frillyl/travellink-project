from . import db


class Place(db.Model):
    __tablename__ = 'tb_tourism'

    place_id = db.Column(db.Integer, primary_key=True)
    place_name = db.Column(db.String(255), nullable=False)
    category = db.Column(db.String(255), nullable=False)
    city = db.Column(db.String(255), nullable=False)
    description = db.Column(db.Text, nullable=False)
    lat = db.Column(db.Float, nullable=False)
    long = db.Column(db.Float, nullable=False)
    price = db.Column(db.Integer, nullable=False)
    rating = db.Column(db.Float, nullable=False)

    def to_dict(self):
        return {
            'place_id': self.place_id,
            'place_name': self.place_name,
            'category': self.category,
            'city': self.city,
            'description': self.description,
            'coordinate': {'lat': self.lat, 'lng': self.long},
            'price': self.price,
            'rating': self.rating,
        }
