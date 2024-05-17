from flask import Flask, request

from templates.service.AdaBoostModel import AdaBoostModel

app = Flask(__name__)


@app.route('/')
def hello_world():  # put application's code here
    return 'Hello World!'


@app.route('/train')
def train():
    ada_boost_model = AdaBoostModel()
    ada_boost_model.fit()


@app.route('/predict', methods=['POST'])
def predict():
    ada_boost_model = AdaBoostModel()
    ada_boost_model.score()
    form = request.json

    # text_x, text_y = [[51, 99, 82, 75, 70]], [98]
    # predict_y = ada_boost_model.predict(text_x)
    # print(predict_y, '预测')
    return 'predict'


if __name__ == '__main__':
    train()
    app.run(port=8088)

