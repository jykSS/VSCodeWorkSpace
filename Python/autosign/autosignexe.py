import requests
import time
import smtplib
from email.mime.text import MIMEText
from email.header import Header
import random
import datetime
import calendar
import yaml
import json
import http.client, urllib
import logging

# 运行前操作 导入PyYaml包 修改yaml文件 因为端口原因 推荐163邮箱
# pip3 install PyYaml
# yaml文件和python文件放同一目录下
# 后台运行 
# nohup python3 -u autoSign.py > autoSign.log 2>&1 &


def useConfig():
    path ='./config.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        return content

def sendEmail(subject,param):
    yaml_reader = useConfig()
    if 'receiver' in param:
        receiver=param['receiver']
        sender = yaml_reader['sender']
        # 收件人需要改一下
        receivers = [receiver] # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

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
            time_now = time.strftime("%Y-%m-%d|%H:%M:%S", time.localtime())
            logging.debug(time_now+"发送邮件成功")
        except smtplib.SMTPException:
            time_now = time.strftime("%Y-%m-%d|%H:%M:%S", time.localtime())
            logging.debug(time_now+"发送邮件失败")
    if 'SendKey' in param:
        key = param['SendKey']
        title = subject
        payload = {
            'text': title
        }
        url = yaml_reader['serverjUrl'].format(key)
        requests.post(url, params=payload)
        
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
    
    params = (
    #-----------------------这需要改成自己token---------------------#
    ('token', token),)
    global result
    time_now = time.strftime("%Y-%m-%d|%H:%M:%S", time.localtime())
    logging.debug(time_now+"当前token",token)
    # 睡20秒
    x = random.randint(1, 20)
    time.sleep(x)
    subject = requests.post('https://bj.psaas.cn/ess/app/tc/save',headers=headers, params=params, data=data)
    time_now = time.strftime("%Y-%m-%d|%H:%M:%S", time.localtime())
    logging.debug(time_now+subject.text)
    obj=json.loads(subject.text)
    result = int(obj['code'])
    
logging.basicConfig(level=logging.DEBUG,  
                filename='./autosign.log',  
                filemode='w')

while True:
        time.sleep(0.01)
        time_now = time.strftime("%H:%M:%S", time.localtime())  # 刷新
        if time_now == "08:26:05" or time_now == "17:33:10":  # 此处设置每天定时的时间
            #网页获取节假日
            # 获取当前时间 格式20190225
            yaml_reader = useConfig()
            apikey = yaml_reader['apikey']
            nowTime = time.strftime('%Y%m%d', time.localtime())
            date = nowTime
            conn = http.client.HTTPSConnection('api.tianapi.com')  #接口域名
            params = urllib.parse.urlencode({'key':apikey,'date':date})
            headers = {'Content-type':'application/x-www-form-urlencoded'}
            conn.request('POST','/jiejiari/index',params,headers)
            res = conn.getresponse()
            data = res.read()
            vop_data = json.loads(data.decode('utf-8'))
            code=vop_data['code']
            if code==200:
                newList=vop_data['newslist']
                isnotwork=newList[0]['isnotwork']
                if isnotwork==0:
                    logging.debug(time_now+"当天为工作日需要打卡")
                    params = yaml_reader['Object']
                    for param in params:
                            # 随机睡一会儿
                            x = random.randint(1, 250)
                            time.sleep(x)
                            for num in range(0, 1):  # 打卡1次
                                if param['register']!='N':
                                    autoSign(param['token'])
                            if result == 2:
                                time_nowH = time.strftime("%H", time.localtime())
                                if time_nowH=="17":
                                    if param['register']=='Y':
                                        sendEmail(param['name']+"的下班autoSign诺明自动打卡成功",param)
                                    else:
                                        sendEmail(param['name']+"正在休假,无需打卡",param)
                                else:
                                    if param['register']=='Y':
                                        sendEmail(param['name']+"的上班autoSign诺明自动打卡成功",param)
                                    else:
                                        sendEmail(param['name']+"正在休假,无需打卡",param)
                                    result = 0
                            else:
                                if param['register']!='N':  
                                    sendEmail(param['name']+"的autoSign诺明自动打卡失败",param)
                                else:
                                    sendEmail(param['name']+"正在休假,无需打卡",param)
                                result = 0           
                else:
                    logging.debug(time_now+"当天为周末不需要打卡")
            else:
                #节假日api接口失效
                # 获取当天日期值
                currentdate = datetime.date.today()
                logging.debug(time_now+currentdate)
                year = currentdate.year
                month = currentdate.month
                day = currentdate.day
                # 系统默认：星期一作为一周的第一天（即：0），星期日作为一周的最后一天（即：6）
                logging.debug(time_now+calendar.weekday(year, month, day))
                currentday = calendar.weekday(year, month, day)
                # 判断当天是否为周末
                if currentday > 4:
                    logging.debug(time_now+"当天为周末不打卡---节假日接口损坏")
                else:
                    logging.debug(time_now+"当天为工作日需要打卡---节假日接口损坏")
                    params = yaml_reader['Object']
                    for param in params:
                            # 随机睡一会儿
                            x = random.randint(1, 250)
                            time.sleep(x)
                            for num in range(0, 1):  # 打卡1次
                                if param['register']!='N':
                                    autoSign(param['token'])
                            if result == 2:
                                time_nowH = time.strftime("%H", time.localtime())
                                if time_nowH=="17":
                                    if param['register']=='Y':
                                        sendEmail(param['name']+"的下班autoSign诺明自动打卡成功",param)
                                    else:
                                        sendEmail(param['name']+"正在休假,无需打卡",param)
                                else:
                                    if param['register']=='Y':
                                        sendEmail(param['name']+"的上班autoSign诺明自动打卡成功",param)
                                    else:
                                        sendEmail(param['name']+"正在休假,无需打卡",param)
                                    result = 0                         
#pyinstaller -F autosignexe.py
# NB. Original query string below. It seems impossible to parse and
# reproduce query strings 100% accurately so the one below is given
# in case the reproduced version is not "correct".
# response = requests.post('https://bj.psaas.cn/ess/app/tc/save?token=88D0F74F-F54C-4DE4-A91F-A1D3D4DD9134&', headers=headers, data=data)
