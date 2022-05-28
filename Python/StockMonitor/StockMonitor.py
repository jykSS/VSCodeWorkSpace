import tushare,time
import requests
import yaml
import json
import http.client, urllib


def useConfig():
    path ='./StockConfig.yaml'
    with open(path, 'r', encoding='utf-8') as doc:
        content = yaml.load(doc, Loader=yaml.Loader)
        return content

def sendMsg(message):
    yaml_reader = useConfig()
    params = yaml_reader['SendMsg']
    for param in params:
        if 'SendKey' in param:
            key = param['SendKey']
            token=yaml_reader['sendToken']
            headers = {
                'token': token,
            }
            data = {
                'message': message,
                'userId': key,
            }
            url = yaml_reader['serverjUrl']
            requests.post(url, headers=headers, data=data)

def sendMsgFilter(message,times):
    if times<4:
        sendMsg(message)
    


def getrealtimedata(share):
    data = tushare.get_realtime_quotes(share.code)
    share.name = data.loc[0][0]
    share.open = float(data.loc[0][1])
    share.price = float(data.loc[0][3])
    share.high = float(data.loc[0][4])
    share.low = float(data.loc[0][5])
    share.describe='股票编号：{}，股票名称：{}，今日开盘价：{}，当前价格：{}，今日最高价：{}，今日最低价：{}'.format(share.code,share.name,share.open,share.price,share.high,share.low)
    return share
def isCasinoOpen():
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
        return isnotwork
    else:
        sendMsg('来bug了---节假日接口损坏')
        return 1

class Share():
    def __init__(self,code,buy,sale):
        self.name = ''
        self.open = ''
        self.price = ''
        self.high = ''
        self.low = ''
        self.describe=''
        self.code = code
        self.buy = buy
        self.sale = sale
        self.time=0

def main(sharelist):
    for share in sharelist:
        sss=getrealtimedata(share)
        if sss.price <=sss.buy:
            share.time=share.time+1
            sendMsgFilter('到达底部，赶紧买入！'+sss.describe,sss.time)
            print('到达底部，赶紧买入！'+sss.describe)
        elif sss.price >= sss.sale:
            share.time=share.time+1
            sendMsgFilter('赶紧卖出。大赚了！'+sss.describe,sss.time)
            print('赶紧卖出。大赚了！'+sss.describe)
        else:
            print('静观其变……'+sss.describe)
                      
# 判断赌场是否开门
isCasinoOpen=isCasinoOpen();
yaml_reader = useConfig()
sharelist=[]
params = yaml_reader['Object']
for param in params:
    sharelist.append(Share(param['code'],param['buy'],param['sale']))
if isCasinoOpen==0: 
    while True:
        main(sharelist)
        time.sleep(4)
        time_now = time.strftime("%H:%M", time.localtime())  # 刷新
        if time_now >= "15:30":
            break;
            