#!/bin/bash
#-------检查哪些服务未启动-------
#-每次检查maxSleepInterval/sleepInteval 次数，失败后记录到failedArr数组 然后输出
#------------------------------
failedArr=() 
TagPorts=('cfps-register:7848' 'cfps-monitor:5001' 'cfps-upms:7711' 'cfps-auth:7838' 'cfps-gateway:7858' 'cfps-txmanager:5004' 'cfps-base:7720' 'cfps-exp:7730' 'cfps-imp:7740' 'cfps-fee:7750' 'cfps-flight:7760' 'cfps-message:7770' 'cfps-warehouse:7780' 'cfps-custom:7790' 'cfps-awbchange:7690' 'cfps-abn:7580' 'cfps-selfservice:7670' 'cfps-bzcheck:7660' 'cfps-transfer:6070' 'cfps-awb:6060' 'cfps-extend:7630' 'cfps-openapi:7610' 'cfps-ws:7600' 'ctms-base:6030' 'ctms-guide:6020' 'cfps-scale:6670'  'xxl-job-admin:9080' 'cfps-jobs:5002' )
JarsPath="/opt/app/cfps7/"
#第一阶段  把微服务全部停止
#ps -ef | grep $JarsPath  | grep -v grep | grep .jar | awk '{print $2}' | xargs kill -9
# 第二阶段
cd $JarsPath
# for 遍历
sleepInteval=1
maxSleepInterval=3
topIndex=0
echo "Dependent service Starting..."
for TagPort in ${TagPorts[*]}; do
	OLD_IFS=”$IFS”
	IFS=”:”
	params=($TagPort)
	Tag=${params[0]}
	port=${params[1]}
	IFS=”$OLD_IFS”
    index=0
	#启动项目
	#nohup java -Xms128m -Xmx300m -jar $JarsPath$Tag.jar >>/dev/null 2>&1 &
	# 根据端口判断服务启动与否
	while [ -z "$(lsof -i:$port -t -s TCP:LISTEN)" ]; do
		#echo $JarsPath$TagPort is starting...
		sleep $sleepInteval
        let index=index+$sleepInteval
        if [ $index -gt $maxSleepInterval ]; then
            break
        fi
	done
	if [ -n "$(lsof -i:$port -t -s TCP:LISTEN)" ]; then
		echo "$Tag is normal!"
	else
		echo "ERROR: $Tag is abnormal "
        failedArr[$topIndex]=$TagPort     
        let topIndex=topIndex+1 
		#exit 5
	fi
	# 只有当依赖的服务启动成功后, 继续执行下面
done

echo 有这些服务不正常或者未开启:${failedArr[*]}

