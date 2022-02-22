import requests
import time
import smtplib
from email.mime.text import MIMEText
from email.header import Header
import random

def sendEmail(subject):
        sender = '13054511186@163.com'
        #收件人需要改一下
        receivers = ['1010158370@qq.com']  # 接收邮件，可设置为你的QQ邮箱或者其他邮箱

        # JHMODUBIERVVCFLO
        mail_host="smtp.163.com"  #设置服务器
        mail_user="13054511186@163.com"    #用户名
        mail_pass="JHMODUBIERVVCFLO"   #口令 

        message = MIMEText(subject, 'plain', 'utf-8')
        message['From'] = Header(subject, 'utf-8')
        message['To'] =  Header(subject, 'utf-8')
        
        # subject = 'autoSign诺明自动打卡成功'
        message['Subject'] = Header(subject, 'utf-8')
        
        
        try:
            smtpObj = smtplib.SMTP() 
            smtpObj.connect(mail_host, 25)    # 25 为 SMTP 端口号
            smtpObj.login(mail_user,mail_pass)  
            smtpObj.sendmail(sender, receivers, message.as_string())
            print ("发送邮件成功")
        except smtplib.SMTPException:
            print ("发送邮件失败")


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

params = (
    #这需要改成自己token
    ('token', '88D0F74F-F54C-4DE4-A91F-A1D3D4DD9134'),
)

data = {
    'altitude':'0',
    'docemp':'1C7C8699-1DA1-40E7-A420-20EC12B236A5',
    'identity':'E6BE9822-2601-4661-97DB-CAB71306CD86',
    'latitude':'40.12257062284873',
    'logemp':'1C7C8699-1DA1-40E7-A420-20EC12B236A5',
    'location': '北京办公区',
    'longitude':'116.5341508607004',
}
result
#自动打卡
def autoSign():
    global result
    #睡20秒
    time.sleep(20) 
    subject=requests.post('https://bj.psaas.cn/ess/app/tc/save', headers=headers, params=params, data=data)
    print(subject.text)
    result=subject.status_code
    
while True:
      time_now = time.strftime("%H:%M:%S", time.localtime()) # 刷新
      if time_now == "08:20:10" or time_now == "17:40:10": #此处设置每天定时的时间
          #随机睡一会儿
          x=random.randint(1,500)
          time.sleep(x)  
          for num in range(0,3): #打卡三次
            autoSign()
            if result==200: 
                sendEmail("autoSign诺明自动打卡成功") 
            else:
                sendEmail("autoSign诺明自动打卡失败") 
            
    
    
 
     
       

#NB. Original query string below. It seems impossible to parse and
#reproduce query strings 100% accurately so the one below is given
#in case the reproduced version is not "correct".
# response = requests.post('https://bj.psaas.cn/ess/app/tc/save?token=88D0F74F-F54C-4DE4-A91F-A1D3D4DD9134&', headers=headers, data=data)
#nohup python -u autoSign.py > autoSign.txt 2>&1 & 