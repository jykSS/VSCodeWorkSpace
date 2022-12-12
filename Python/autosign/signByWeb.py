import requests
import smtplib
from email.mime.text import MIMEText
from email.header import Header
import yaml
import json
from flask import Flask, make_response, Response, request
from json import dumps
from flask_cors import CORS

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
        
@app.route('/api/sign/<username>')
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
            if 'receiver' in param:
                receiver=param['receiver']
                sender = yaml_reader['sender']
                # 收件人需要改一下
                receivers = [receiver] # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

                # JHMODUBIERVVCFLO
                mail_host = yaml_reader['mail_host']  # 设置服务器
                mail_user = yaml_reader['mail_user']  # 用户名
                mail_pass = yaml_reader['mail_pass']  # 口令

                message = MIMEText(title, 'plain', 'utf-8')
                message['From'] = Header(yaml_reader['sender'], 'utf-8')
                message['To'] = Header(title, 'utf-8')

                # subject = 'autoSign诺明自动打卡成功'
                message['Subject'] = Header(title, 'utf-8')

                try:
                    smtpObj = smtplib.SMTP()
                    smtpObj.connect(mail_host, 25)    # 25 为 SMTP 端口号
                    smtpObj.login(mail_user, mail_pass)
                    smtpObj.sendmail(sender, receivers, message.as_string())
                    print("发送邮件成功")
                except smtplib.SMTPException:
                    print("发送邮件失败")
            return title
    return "请检查是否添加用户"
@app.route('/api/sign/addUser', methods=['POST'])
def signAddUser():
    # print(request.stream.read()) # 不要用，否则下面的form取不到数据
    yourName=request.form['name']
    SendKey=request.form.get('SendKey')
    receiver=request.form.get('receiver')
    token=request.form.get('token')
    register=request.form.get('register', default='Y')
    yaml_reader = useConfig()
    params = yaml_reader['Object']
    for param in params:
        if param['name']==yourName:
            date={"code": 0,"message": "success",'data':'该用户已经添加,无需添加'}
            return Response(json.dumps(date), mimetype='application/json')
    addparam={'name':yourName,'SendKey':SendKey,'receiver':receiver,'token':token,'register':register}
    if receiver is None or len(receiver) ==0:
        addparam={'name':yourName,'SendKey':SendKey,'token':token,'register':register}
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        content['Object'].append(addparam)
    with open(path, 'w', encoding='utf-8') as doc:
        yaml.dump(content,doc)
    return "添加成功"

@app.route('/api/sign/update/<username>')
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

@app.route('/api/updateToken/<username>/<token>')
def updateToken(username,token):
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yourName =username
    updatetoken=token
    yaml_reader = useConfig()
    params = yaml_reader['Object']
    for param in params:
        if param['name']==yourName:
            param['token'] = updatetoken
            updateConfig(param)
            return '修改成功'
    return "请检查是否添加用户"

@app.route('/api/sign/users')
def getUserInfo():
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yaml_reader = useConfig()
    users = yaml_reader['Object']
    return json.dumps(users)

##########  以下是app专用 ##########
@app.route('/api/app/sign/users')
def getAppUserInfo():
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yaml_reader = useConfig()
    users = yaml_reader['Object']
    date={"code": 0,"message": "success",'data':users}
    return Response(json.dumps(date), mimetype='application/json')

@app.route('/api/app/updateToken/<username>/<token>')
def updateAppToken(username,token):
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yourName =username
    updatetoken=token
    yaml_reader = useConfig()
    params = yaml_reader['Object']
    for param in params:
        if param['name']==yourName:
            param['token'] = updatetoken
            updateConfig(param)
            date={"code": 0,"message": "success",'data':'修改成功'}
            return Response(json.dumps(date), mimetype='application/json')
    date={"code": 0,"message": "success",'data':'请检查是否添加用户'}
    return Response(json.dumps(date), mimetype='application/json')
@app.route('/api/app/sign/update/<username>')
def updateByAppYourSelf(username):
    # 这里面就是你想要返回给前端的值， 切记，这里只能返回字符串，如果是个json数据，你的通过json.dumps(你的json数据)
    yourName =username
    yaml_reader = useConfig()
    params = yaml_reader['Object']
    for param in params:
        if param['name']==yourName:
            param['register']='N' if param['register']=='Y' else 'Y'
            updateConfig(param)
            date={"code": 0,"message": "success",'data':'打卡状态修改成功'}
            return Response(json.dumps(date), mimetype='application/json')
    date={"code": 0,"message": "success",'data':'请检查是否添加用户'}
    return Response(json.dumps(date), mimetype='application/json')
@app.route('/api/app/sign/<username>')
def signByAppYourSelf(username):
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
            if 'receiver' in param:
                receiver=param['receiver']
                sender = yaml_reader['sender']
                # 收件人需要改一下
                receivers = [receiver] # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

                # JHMODUBIERVVCFLO
                mail_host = yaml_reader['mail_host']  # 设置服务器
                mail_user = yaml_reader['mail_user']  # 用户名
                mail_pass = yaml_reader['mail_pass']  # 口令

                message = MIMEText(title, 'plain', 'utf-8')
                message['From'] = Header(yaml_reader['sender'], 'utf-8')
                message['To'] = Header(title, 'utf-8')

                # subject = 'autoSign诺明自动打卡成功'
                message['Subject'] = Header(title, 'utf-8')

                try:
                    smtpObj = smtplib.SMTP()
                    smtpObj.connect(mail_host, 25)    # 25 为 SMTP 端口号
                    smtpObj.login(mail_user, mail_pass)
                    smtpObj.sendmail(sender, receivers, message.as_string())
                    print("发送邮件成功")
                except smtplib.SMTPException:
                    print("发送邮件失败")
            date={"code": 0,"message": "success",'data':title}
            return Response(json.dumps(date), mimetype='application/json')
    date={"code": 0,"message": "success",'data':'请检查是否添加用户'}
    return Response(json.dumps(date), mimetype='application/json')

@app.route('/api/app/addUser', methods=['POST'])
def addUser():
    # print(request.stream.read()) # 不要用，否则下面的form取不到数据
    yourName=request.form['name']
    SendKey=request.form.get('SendKey')
    receiver=request.form.get('receiver')
    token=request.form.get('token')
    register=request.form.get('register', default='Y')
    yaml_reader = useConfig()
    params = yaml_reader['Object']
    for param in params:
        if param['name']==yourName:
            date={"code": 0,"message": "success",'data':'该用户已经添加,无需添加'}
            return Response(json.dumps(date), mimetype='application/json')
    addparam={'name':yourName,'SendKey':SendKey,'receiver':receiver,'token':token,'register':register}
    if receiver is None or len(receiver) ==0:
        addparam={'name':yourName,'SendKey':SendKey,'token':token,'register':register}
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        content['Object'].append(addparam)
    with open(path, 'w', encoding='utf-8') as doc:
        yaml.dump(content,doc)
    date={"code": 0,"message": "success",'data':'添加成功'}
    return Response(json.dumps(date), mimetype='application/json')


CORS(app, resources=r'/*')  
if __name__ == '__main__':
    # 这里host是你的后端地址，这里写0.0.0.0， 表示的是这个接口在任何服务器上都可以被访问的到，只需要前端访问该服务器地址就可以的，
    # 当然你也可以写死，如222.222.222.222， 那么前端只能访问222.222.222.222, port是该接口的端口号,
    # debug = True ,表示的是，调试模式，每次修改代码后不用重新启动服务
    app.run(host='0.0.0.0', port=8099, debug=True)
