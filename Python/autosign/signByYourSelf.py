import requests
import time
import smtplib
from email.mime.text import MIMEText
from email.header import Header
import yaml

# 运行前操作 导入PyYaml包 修改yaml文件 因为端口原因 推荐163邮箱
# pip3 install PyYaml
# yaml文件和python文件放同一目录下
# 后台运行 
# nohup python3 -u autoSign.py > autoSign.log 2>&1 &


def useConfig():
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        print(content)
        return content

def sendEmail(subject):
    yaml_reader = useConfig()
    sender = yaml_reader['sender']
    # 收件人需要改一下
    receivers = yaml_reader['receivers']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

    # JHMODUBIERVVCFLO
    mail_host = yaml_reader['mail_host']  # 设置服务器
    mail_user = yaml_reader['mail_user']  # 用户名
    mail_pass = yaml_reader['mail_pass']  # 口令

    message = MIMEText(subject, 'plain', 'utf-8')
    message['From'] = Header(yaml_reader['sender'], 'utf-8')
    message['To'] = Header(subject, 'utf-8')

    # subject = 'autoSign诺明自动打卡成功'
    message['Subject'] = Header(subject, 'utf-8')

    try:
        smtpObj = smtplib.SMTP()
        smtpObj.connect(mail_host, 25)    # 25 为 SMTP 端口号
        smtpObj.login(mail_user, mail_pass)
        smtpObj.sendmail(sender, receivers, message.as_string())
        print("发送邮件成功")
    except smtplib.SMTPException:
        print("发送邮件失败")


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
    yaml_reader = useConfig()
    params = (
    #-----------------------这需要改成自己token---------------------#
    ('token', token),)
    global result
    print("当前token",token)
    # 睡20秒
    time.sleep(20)
    subject = requests.post('https://bj.psaas.cn/ess/app/tc/save',headers=headers, params=params, data=data)
    print(subject.text)
    result = subject.status_code

yourName = input("请输入你的名字:")
yaml_reader = useConfig()
tokens = yaml_reader['Object']
for token in tokens:
    if token['name']==yourName:
        for num in range(0, 3):  # 打卡三次
            autoSign(token['token'])
        if result == 200:
            print("打卡成功")
        else:
            print("打卡失败")