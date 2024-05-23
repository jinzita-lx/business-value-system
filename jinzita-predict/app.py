import json

from flask import Flask, request

from templates.service.AdaBoostModel import AdaBoostModel
from flask_cors import CORS

app = Flask(__name__)

CORS(app, supports_credentials=True)


@app.route('/')
def hello_world():  # put application's code here
    return 'Hello World!'


@app.route('/train')
def train():
    ada_boost_model = AdaBoostModel()
    ada_boost_model.fit()
    return 'train'


@app.route('/predict', methods=['POST'])
def predict():
    ada_boost_model = AdaBoostModel()
    aweme_list = request.json
    predict_result = ada_boost_model.predict(aweme_list)
    return {
        "code": 200,
        "msg": 'success',
        "data": predict_result
    }


if __name__ == '__main__':
    app.run(port=8088)


