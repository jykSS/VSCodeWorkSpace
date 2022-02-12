#!/bin/bash
TagPorts=('cfps-register:7848' 'cfps-upms:7711' 'cfps-auth:7838' 'cfps-gateway:7858' 'cfps-txmanager:5004')
JarsPath="/opt/app/cfps7/"
#第一阶段  把微服务全部停止
ps -ef | grep $JarsPath  | grep -v grep | awk '{print $2}' | xargs kill -9
# 第二阶段
cd JarsPath
# for 遍历
echo "Dependent service Starting..."
for TagPort in ${TagPorts[*]}; do
	OLD_IFS=”$IFS”
	IFS=”:”
	params=($TagPort)
	Tag=${params[0]}
	port=${params[1]}
	IFS=”$OLD_IFS”
	#启动项目
	nohup java -Xms256m -Xmx512m -jar $JarsPath$Tag.jar >>/dev/null 2>&1 &
	# 根据端口判断服务启动与否
	while [ -z "$(lsof -i:$port -t -s TCP:LISTEN)" ]; do
		echo $JarsPath$TagPort is running...
		sleep 3
	done
	if [ -n "$(lsof -i:$port -t -s TCP:LISTEN)" ]; then
		echo "$JarsPath$Tag server start success!"
	else
		echo "ERROR: $JarsPath$Tag server boot failure !!!! "
		exit 5
	fi
	# 只有当依赖的服务启动成功后, 继续执行下面
done

#第三阶段 启动所有非依赖项目
for file in $(ls); do
	# 当前文件不是一个文件夹
	if [[ -f $file ]]; then
		# 如果当前文件是一个.jar结尾的文件则启动它
		if [[ ${file:0-4} == '.jar' ]] && [[ ! "${TagPorts[*]}" =~ ${file%%.jar} ]]; then
			echo $JarsPath${file%%.jar} 开始启动...
			nohup java -Xms256m -Xmx512m -jar $JarsPath$file >>/dev/null 2>&1 &
			sleep 5
			echo $JarsPath${file%%.jar} 启动完成!
		fi
	fi
done

