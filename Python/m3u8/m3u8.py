import requests
import re
import os
import urllib.parse
import time
import shutil

start_time = time.time()
headers = {
    'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36',
}
m3u8_url = input("m3u8_url:")
# url解码
m3u8_url = urllib.parse.unquote(m3u8_url)
r_url = '.*&vurl=(.*ver=4)'
url_list = re.findall(r_url,m3u8_url)
if len(url_list) != 0:
    m3u8_url = url_list[0]
m3u8 = requests.get(m3u8_url,headers = headers).text
r_m3u8 = ',\n(.*?)\n#'
# re.S整体匹配
ts_all = re.findall(r_m3u8,m3u8,re.S)
print(ts_all[:10])
print('共 %d 个ts文件'%len(ts_all))
# 删除之前ts
if os.path.exists('./ts'):
    shutil.rmtree('./ts')
    os.mkdir('./ts')
if not os.path.exists("./ts"):
    os.mkdir("./ts")
num = 0
for ts_2 in ts_all:
    ts = requests.get(ts_2,headers = headers).content
    np = (len(str(len(ts_all)))-len(str(num)))*'0'+str(num)
    with open('./ts/%s.ts'%np,'wb') as fp:
        fp.write(ts)
    print('%s.ts save'%np)
    num += 1
end_time = time.time()
print('下载完成，总耗时：',end_time-start_time) 
# https://sod.bunediy.com/20211217/njayYgPt/index.m3u8  