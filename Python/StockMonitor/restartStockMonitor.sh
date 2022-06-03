#! /bin/sh
#kill
SERVER_NAME_ACTIVITY_MANAGEMENT="StockMonitor"
echo "stop StockMonitor..."
pid=`ps -ef |grep python |grep -w $SERVER_NAME_ACTIVITY_MANAGEMENT| grep -v grep | awk '{print $2}'`
echo "run pid:"$pid
if [ "$pid" != "" ]
then
        echo "Try to kill python $pid......"
        kill -9 $pid
        sleep 1
fi
echo "begin start"
#启动
nohup python3 -u /opt/StockMonitor/StockMonitor.py > /opt/StockMonitor/StockMonitor.txt 2>&1 &
echo "end"
#vim /etc/crontab     
#25 9 * * *  root  cd /opt/StockMonitor && nohup python3 -u /opt/StockMonitor/StockMonitor.py > /opt/StockMonitor/StockMonitor.txt 2>&1 &