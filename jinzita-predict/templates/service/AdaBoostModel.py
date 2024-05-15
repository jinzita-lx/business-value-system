import joblib
from sklearn.ensemble import AdaBoostClassifier
from sklearn.model_selection import train_test_split

from templates.api.load_dataset import load_dataset


class AdaBoostModel:
    def __init__(self, test_size=0.2, random_state=42):
        # 数据集载入
        x, y = load_dataset()
        # 划分训练集、测试集
        self.x_train, self.x_test, self.y_train, self.y_test = (
            train_test_split(x, y,
                             test_size=test_size,
                             random_state=random_state))

    def fit(self, n_estimators=100, learning_rate=0.2):
        model = AdaBoostClassifier(n_estimators=n_estimators, learning_rate=learning_rate)
        model.fit(self.x_train, self.y_train)
        joblib.dump(model, 'static/model/model.pkl')
        print('训练成功')

    def predict(self, x_predict):
        model = joblib.load('static/model/model.pkl')
        return model.predict(x_predict)

    def score(self):
        model = joblib.load('static/model/model.pkl')
        score = model.score(self.x_test, self.y_test)
        return score
