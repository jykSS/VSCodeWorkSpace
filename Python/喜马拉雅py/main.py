#!/usr/bin/python3
# coding=utf-8
import re
import sys
import requests
import os
import time
import logging
import math
import urllib.request

# 相册ID集合
albumIdArr = set()

self_dir = sys.path[0]

# 打开文件
fo = open("url.txt", "r")
print("开始匹配: album_id")
for line in fo.readlines():
    line = line.strip()
    res = re.findall(r'album\/(\d+)', line)
    if len(res) > 0:
        albumIdArr.add(res[0])

# 关闭文件
fo.close()

keyword = {
    "albumId": 0,
    "pageNum": 1,
    "sort": 0
}

headers = {
    "User-Agent": 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36',
    'Host': 'www.ximalaya.com',
    'Accept-Language': 'zh-CN,zh;q=0.9,ja;q=0.8', 'Accept-Encoding': 'gzip, deflate, br',
    'Accept': "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    'Upgrade-Insecure-Requests': '1',
    'Connection': 'keep-alive',
    'Cache-Control': 'max-age=0'
}


def Schedule(a, b, c):
    '''''
    a:已经下载的数据块
    b:数据块的大小
    c:远程文件的大小
    '''
    per = 100.0 * a * b / c
    if per > 100:
        per = 100
    print('%.2f%%' % per)


def down(data):
    print("本次下载: %s" % (len(data)))
    count = 1
    for item in data:
        try:
            if count % 5 == 0:
                # 每抓取5条休眠2秒
                print("(每抓取5个休眠1s)")
                time.sleep(1)
            print("名称：%s, 作品id: %s" % (item['title'], item["trackId"]))
            if not os.path.exists(self_dir + "/down/" + item['title'] + ".m4a"):
                count += 1
                audioInfo = requests.get(
                    url="https://www.ximalaya.com/revision/play/v1/audio?id=" + str(item["trackId"]) + "&ptype=1",
                    headers=headers, timeout=3).json()
                print("开始下载, 地址:%s 名称:%s" % (audioInfo["data"]["src"], item['title']))
                urllib.request.urlretrieve(audioInfo["data"]["src"], self_dir + "/down/" + item['title'] + ".m4a",
                                           Schedule)
                print("下载成功")
            else:
                print("文件已存在")

            if not os.path.exists(self_dir + "/down/" + item['title'] + ".txt"):
                count += 1
                content = requests.get(url='https://www.ximalaya.com' + item['url'], headers=headers, timeout=3)
                content = re.findall('<article class="intro  _Fc">(.*?)<\/article>', content.text)
                print("抓取内容")
                if len(content) > 0:
                    with open(self_dir + "/down/" + item['title'] + ".txt", 'w') as f:
                        f.write(content[0])
                        f.close()
            print("作品抓取成功~~")
        except BaseException as e:
            print("作品抓取错误~~")
            logging.exception(e)


for albumId in albumIdArr:
    keyword["albumId"] = int(albumId)
    i = 1
    print("本次抓取作品ID: %s" % albumId)
    while 1:
        keyword["pageNum"] = i
        print("本次抓取分页(每页休眠2s): albumId: %s, page: %s" % (albumId, i))
        time.sleep(2)
        try:
            res = requests.get(url='https://www.ximalaya.com/revision/album/v1/getTracksList', params=keyword,
                               headers=headers, timeout=3).json()
            down(res['data']['tracks'])

            totalPage = math.ceil(res['data']['trackTotalCount'] / res['data']['pageSize'])
            if totalPage == i:
                break
        except BaseException as e:
            print("分页数据抓取错误~~")
            logging.exception(e)
        if i > 100:
            # 大于100 防止死循环
            break
        i += 1
