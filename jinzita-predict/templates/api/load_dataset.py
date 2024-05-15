import json
import requests
from sklearn.datasets import load_iris
from bs4 import BeautifulSoup


def get_all_dataset():
    base_url = "http://localhost:8080"
    headers = {"Content-Type": "application/json"}
    url = base_url + "/resource/value/data_set"
    response = requests.get(url, headers=headers, timeout=1000)
    if response.status_code == 200:
        text = json.loads(response.text)
        if text["code"] == 200:
            return text["rows"]


def load_dataset():
    data_set = get_all_dataset()
    data_set_x, data_set_y = [], []
    for data in data_set:
        data_x, data_y = [data['businessAdaptationExponent'], data['spreadExponent'], data['activityExponent'], data['growthExponent'], data['healthExponent']], data['compositeMarketValue']
        data_set_x.append(data_x)
        data_set_y.append(data_y)

    return data_set_x, data_set_y
