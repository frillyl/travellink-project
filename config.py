import os


class Config:
    SQLALCHEMY_DATABASE_URI = os.environ.get(
        'DATABASE_URL', 'mysql://root:travellinkproject@localhost:5000/db_tourism')
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    GOOGLE_CLOUD_PROJECT = os.environ.get('GOOGLE_CLOUD_PROJECT')
    GOOGLE_CLOUD_STORAGE_BUCKET = os.environ.get('GOOGLE_CLOUD_STORAGE_BUCKET')


class DevelopmentConfig(Config):
    DEBUG = True


class ProductionConfig(Config):
    DEBUG = False
