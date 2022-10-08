import requests
from email.mime.text import MIMEText
from email.header import Header
import yaml
import json
from flask import Flask
app = Flask(__name__)
# 这里默认的是get请求方式
# pip3 install PyYaml
# pip install flask
# yaml文件和python文件放同一目录下
# 后台运行 
# nohup python3 -u signByWeb.py > signByWeb.log 2>&1 &

def useConfig():
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        print(content)
        return content
def updateConfig(updateParam):
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        for index,param in enumerate(content['Object']):
            if param['name']==updateParam['name']:
               content['Object'][index]=updateParam
    with open(path, 'w', encoding='utf-8') as doc:
        yaml.dump(content,doc)
result = 0
# 自动打卡
def autoSign(token):
    headers = {
    'User-Agent': 'psa shang dian/6.8.40 (iPhone; iOS 15.3; Scale/2.00)',
    'Host': 'bj.psaas.cn',
    'Content-Type': 'application/x-www-form-urlencoded',
    'Connection': 'keep-alive',
    'Accept': '*/*',
    'Accept-Language': 'zh-Hans-CN;q=1',
    'Content-Length': '760',
    'Accept-Encoding': 'gzip, deflate, br',}

    data = {
        'altitude': '0',
        'docemp': '1C7C8699-1DA1-40E7-A420-20EC12B236A5',
        'identity': 'E6BE9822-2601-4661-97DB-CAB71306CD86',
        'latitude': '40.12257062284873',
        'logemp': '1C7C8699-1DA1-40E7-A420-20EC12B236A5',
        'location': '北京办公区',
        'longitude': '116.5341508607004',
    }
    params = (
    #-----------------------这需要改成自己token---------------------#
    ('token', token),)
    global result
    print("当前token",token)
    subject = requests.post('https://bj.psaas.cn/ess/app/tc/save',headers=headers, params=params, data=data)
    print(subject.text)
    obj=json.loads(subject.text)
    result = int(obj['code'])
    if result==2:  
        print(result)
        
@app.route('/sign/<username>')
def signByYourSelf(username):
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yourName =username
    yaml_reader = useConfig()
    params = yaml_reader['Object']
    for param in params:
        if param['name']==yourName:
            autoSign(param['token'])
            if result==2:
                title = param['name']+"的autoSign诺明手动打卡成功"
            else:
                title = param['name']+"的autoSign诺明手动打卡失败,请重新检查token"
            if 'SendKey' in param:
                key = param['SendKey']
                payload = {
                    'text': title
                }
                url = 'https://sc.ftqq.com/{}.send'.format(key)
                requests.post(url, params=payload)
            return title
    return "请检查是否添加用户"

@app.route('/sign/update/<username>')
def updateByYourSelf(username):
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yourName =username
    yaml_reader = useConfig()
    params = yaml_reader['Object']
    for param in params:
        if param['name']==yourName:
            param['register']='N' if param['register']=='Y' else 'Y'
            updateConfig(param)
            return '修改成功'
    return "请检查是否添加用户"

@app.route('/sign/users')
def getUserInfo():
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yaml_reader = useConfig()
    users = yaml_reader['Object']
    return json.dumps(users)


if __name__ == '__main__':
    # 这里host是你的后端地址，这里写0.0.0.0， 表示的是这个接口在任何服务器上都可以被访问的到，只需要前端访问该服务器地址就可以的，
    # 当然你也可以写死，如222.222.222.222， 那么前端只能访问222.222.222.222, port是该接口的端口号,
    # debug = True ,表示的是，调试模式，每次修改代码后不用重新启动服务
    app.run(host='0.0.0.0', port=8099, debug=True)
