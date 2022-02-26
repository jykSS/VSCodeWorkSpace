import yaml
import requests

def useConfig():
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        print(content)
        return content

yaml_reader = useConfig()
params = yaml_reader['Object']
for param in params:
    if 'SendKey' in param:
        key = param['SendKey']
        title = param['name']+"的autoSign诺明自动打卡成功"
        payload = {
            'text': title
        }
        url = yaml_reader['serverjUrl'].format(key)
        requests.post(url, params=payload)
