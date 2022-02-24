import requests
import time
import smtplib
from email.mime.text import MIMEText
from email.header import Header
import random
import datetime
import calendar
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


headers = {
    'User-Agent': 'psa shang dian/6.8.40 (iPhone; iOS 15.3; Scale/2.00)',
    'Host': 'bj.psaas.cn',
    'Content-Type': 'application/x-www-form-urlencoded',
    'Connection': 'keep-alive',
    'Accept': '*/*',
    'Accept-Language': 'zh-Hans-CN;q=1',
    'Content-Length': '760',
    'Accept-Encoding': 'gzip, deflate, br',
}

data = {
    'altitude': '0',
    'docemp': '1C7C8699-1DA1-40E7-A420-20EC12B236A5',
    'identity': 'E6BE9822-2601-4661-97DB-CAB71306CD86',
    'latitude': '40.12257062284873',
    'logemp': '1C7C8699-1DA1-40E7-A420-20EC12B236A5',
    'location': '北京办公区',
    'longitude': '116.5341508607004',
}
result = 0
# 自动打卡


def autoSign():
    yaml_reader = useConfig()
    params = (
    #-----------------------这需要改成自己token---------------------#
    ('token', yaml_reader['token']),)
    global result
    print("当前token",yaml_reader['token'])
    # 睡20秒
    time.sleep(20)
    subject = requests.post('https://bj.psaas.cn/ess/app/tc/save',headers=headers, params=params, data=data)
    print(subject.text)
    result = subject.status_code


while True:
    time_now = time.strftime("%H:%M:%S", time.localtime())  # 刷新
    if time_now == "08:20:10" or time_now == "17:40:10":  # 此处设置每天定时的时间
        # 获取当天日期值
        currentdate = datetime.date.today()
        print(currentdate)
        year = currentdate.year
        month = currentdate.month
        day = currentdate.day
        # 系统默认：星期一作为一周的第一天（即：0），星期日作为一周的最后一天（即：6）
        print(calendar.weekday(year, month, day))
        currentday = calendar.weekday(year, month, day)
        # 判断当天是否为周末
        if currentday > 5:
            print("当天为周末不打卡")
        else:
            print("当天为工作日需要打卡")
            # 随机睡一会儿
            x = random.randint(1, 500)
            time.sleep(x)
            for num in range(0, 3):  # 打卡三次
                autoSign()
            if result == 200:
                sendEmail("autoSign诺明自动打卡成功")
                result = 0
            else:
                sendEmail("autoSign诺明自动打卡失败")
                result = 0






# NB. Original query string below. It seems impossible to parse and
# reproduce query strings 100% accurately so the one below is given
# in case the reproduced version is not "correct".
# response = requests.post('https://bj.psaas.cn/ess/app/tc/save?token=88D0F74F-F54C-4DE4-A91F-A1D3D4DD9134&', headers=headers, data=data)
# nohup python -u autoSign.py > autoSign.txt 2>&1 &
