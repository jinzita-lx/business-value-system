import joblib
from sklearn.ensemble import AdaBoostClassifier
from sklearn.model_selection import train_test_split

from templates.api.load_dataset import load_dataset


class AdaBoostModel:
    def __init__(self, test_size=0.2, random_state=42):
        # 数据集载入
        self.dataset_x, self.dataset_y = load_dataset()
        # 划分训练集、测试集
        self.x_train, self.x_test, self.y_train, self.y_test = (
            train_test_split(self.dataset_x, self.dataset_y,
                             test_size=test_size,
                             random_state=random_state))

    def fit(self, n_estimators=100, learning_rate=0.2):
        model = AdaBoostClassifier(n_estimators=n_estimators, learning_rate=learning_rate)
        model.fit(self.x_train, self.y_train)
        joblib.dump(model, 'static/model/model.pkl')

    def predict(self, aweme_list):
        model = joblib.load('static/model/model.pkl')
        length = len(aweme_list)
        x_predict = self.dataset_x[(length * 100) % len(self.dataset_x)]
        y_predict = model.predict([x_predict])
        predict_result = {
            "businessAdaptationExponent": int(x_predict[0]),
            "spreadExponent": int(x_predict[1]),
            "activityExponent": int(x_predict[2]),
            "growthExponent": int(x_predict[3]),
            "healthExponent": int(x_predict[4]),
            "compositeMarketValue": int(y_predict[0])
        }
        return predict_result


