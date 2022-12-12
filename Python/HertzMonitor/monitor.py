import requests
import smtplib
from email.mime.text import MIMEText
from email.header import Header
import yaml
import time
import logging
import json

def useConfig():
    path = './MonitorConfig.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        return content


def sendEmail(subject):
    param = useConfig()
    if 'receiver' in param:
        receivers = param['receiver']
        for receiver in receivers:
            sender = yaml_reader['sender']
            # 收件人需要改一下
            receivers = [receiver]  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

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
    if 'SendKey' in param:
        key = param['SendKey']
        title = subject
        payload = {
            'text': title
        }
        url = yaml_reader['serverjUrl'].format(key)
        requests.post(url, params=payload)
    if 'userId' in param:
        url = "http://49.232.195.231:10086/push"
        payload={
            'userId':param['userId'],
            'message':subject
        }
        headers = {"token": "jyk_pusher"}
        response = requests.request("POST", url, data=payload, headers=headers)
        print(response.text)
        

def monitor(param):
    headers = {
        'Accept': '*/*',
        'Accept-Encoding': 'gzip, deflate',
        'Accept-Language': 'zh-CN,zh;q=0.9',
        'Connection': 'keep-alive',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36',
        'X-Requested-With': 'XMLHttpRequest',
        'cookie':'IsNeedResetPassword=0; UserID=2d17a8da6e3b454ea336e5015b2e1943; UserName=%E6%9E%97%E4%BD%B3%E7%91%9E; AvatarUrl=; LoginFlagKey=4a6d4fae713a464da83ebc9d32ac03ae; ShowHelloInfo=0'
    }
    time_now = time.strftime("%Y-%m-%d|%H:%M:%S", time.localtime())
    try:
        subject = requests.get(param['link'],headers=headers)
        print(subject.text)
        obj=json.loads(subject.text)
        if obj['msg']=='进行中':
            sendEmail('开始选课,请及时处理')
        # subject = requests.get('http://www.123213infoccsp.com',headers=headers)
    except Exception as e :
        print(time_now+":"+e)
        logging.debug(time_now+param['name']+'出现错误,请及时处理: '+e)
        # sendEmail(param['name']+'出现错误,请及时处理')
    else:
        if subject.status_code==200:
           print(time_now+":"+param['name']+' 正常')
           logging.debug(time_now+param['name']+' 正常')
        else:
        #    sendEmail(param['name']+'出现错误,请及时处理')
           logging.debug(time_now+param['name']+'出现错误,请及时处理')


while True:
    logging.basicConfig(level=logging.DEBUG,  
                    filename='./monitor.log',  
                    filemode='w')
    yaml_reader = useConfig()
    params = yaml_reader['targets']
    for param in params:
        monitor(param)
    time.sleep(yaml_reader['waitTime'])