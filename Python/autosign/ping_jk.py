import yaml
import requests
import time

def useConfig():
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        print(content)
        return content


while True:
    time_nowH = time.strftime("%H", time.localtime())
    if time_nowH=="21":
        print("的下班autoSign诺明自动打卡成功")
        time.sleep(8)
        break;
