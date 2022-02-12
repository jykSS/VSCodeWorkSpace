#!/usr/bin/python3
# -*-coding:utf-8-*-
import os
import sys
import subprocess
import threading
import time


def toMp4(m4a, mp3):
    subprocess.call(['ffmpeg', '-i', m4a, mp3])


thread_num = 4

thread_list = []

down_dir = sys.path[0] + '/down/'

for root, dirnames, filenames in os.walk(sys.path[0] + '/down/'):
    for filename in filenames:
        if os.path.splitext(down_dir + filename)[1] != ".m4a":
            continue
        if os.path.exists(down_dir + filename.replace(".m4a", ".mp3")):
            continue
        print("开始转码: %s", down_dir + filename)
        t = threading.Thread(target=toMp4, args=(down_dir + filename, down_dir + filename.replace(".m4a", ".mp3")))
        # 加入线程池并启动
        thread_list.append(t)
        t.start()
        # 当线程池满时，等待线程结束
        while len(thread_list) > thread_num:
            # 移除已结束线程
            thread_list = [x for x in thread_list if x.is_alive()]
            time.sleep(3)
