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
import http.client
import urllib

# 运行前操作 导入PyYaml包 修改yaml文件 因为端口原因 推荐163邮箱
# pip3 install PyYaml
# yaml文件和python文件放同一目录下
# 后台运行
# nohup python3 -u autoSign.py > autoSign.log 2>&1 &

# 自动打卡


def autofuckmikecrm():
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36',
        'Host': 'mikecrmlulu.mikecrm.com',
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'Connection': 'keep-alive',
        'Accept': 'application/json, text/javascript, */*; q=0.01',
        'Accept-Language': 'zh-CN,zh;q=0.9',
        'Content-Length': '676',
        'Accept-Encoding': 'gzip, deflate',
    }

    data = {
        "cvs": {
            "i": 200564867,
            "t": "FkSR7sn",
            "s": 201997204,
            "acc": "6M93kaXwh3skwTe99dTybOjT9HIcPwHc",
            "r": "",
            "fc": "Kn27hWvENYsXBQJ5N9",
            "c": {
                "cp": {
                    "205883299": [
                        "13054511186"
                    ],
                    "205883303": {
                        "n": "姜延锴"
                    },
                    "205883659": "HT0242",
                    "205886440": [
                        206590548,
                        206590549,
                        206590550,
                        206590552,
                        206590553,
                    ],
                    "206022014": [
                        206590696,
                        206590697
                    ],
                    "205888894": 206600643,
                    "205948118": [
                        206683169
                    ]
                },
                "ext": {
                    "sc": [
                        205886501,
                        205883303,
                        205883659,
                        205888894,
                        205886440,
                        207289092,
                        205883299,
                        205927483,
                        205948117,
                        205948118
                    ]
                }
            }
        }
    }
    subject = requests.post(
        'http://mikecrmlulu.mikecrm.com/handler/web/form_runtime/handleSubmit.php', headers=headers, data=data)
    print(subject.status_code)

autofuckmikecrm();
        # NB. Original query string below. It seems impossible to parse and
        # reproduce query strings 100% accurately so the one below is given
        # in case the reproduced version is not "correct".
        # response = requests.post('https://bj.psaas.cn/ess/app/tc/save?token=88D0F74F-F54C-4DE4-A91F-A1D3D4DD9134&', headers=headers, data=data)
kubectl get svc -ncfps



40004


40001