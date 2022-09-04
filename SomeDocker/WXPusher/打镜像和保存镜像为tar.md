# 打镜像和保存镜像为tar
## 为了解决获取不到容器的内存限制
容器环境，由于java获取不到容器的内存限制，只能获取到服务器的配置： 
这样容易引起不必要问题，例如限制容器使用100M内存，但是jvm根据服务器配置来分配初始化内存，导致java进程超过容器限制被kill掉。为了解决这个问题，可以设置-Xmx或者MaxRAM来解决，但就想第一部分描述的一样，这样太不优雅了！
为了解决这个问题，Java 10 引入了 +UseContainerSupport（默认情况下启用），通过这个特性，可以使得JVM在容器环境分配合理的堆内存。 并且，在JDK8U191版本之后，这个功能引入到了JDK 8，而JDK 8是广为使用的JDK版本。
-XX:+UseContainerSupport允许JVM 从主机读取cgroup限制，例如可用的CPU和RAM，并进行相应的配置。这样当容器超过内存限制时，会抛出OOM异常，而不是杀死容器。
该特性在Java 8u191 +，10及更高版本上可用。
注意，在191版本后，-XX:{Min|Max}RAMFraction 被弃用，引入了-XX:MaxRAMPercentage，其值介于0.0到100.0之间，默认值为25.0。
在应用的启动参数，设置 -XX:+UseContainerSupport，设置-XX:MaxRAMPercentage=75.0，这样为其他进程（debug、监控）留下足够的内存空间，又不会太浪费RAM。
## 下载JDK版本并且解压
> 70.83 机器的
> /data/cfpsjava/ 
## 编写Dockerfile,和解压后的JDK放在同一文件夹下
```Dockerfile
#1.指定基础镜像，并且必须是第一条指令
FROM centos:7

#3.在构建镜像时，指定镜像的工作目录，之后的命令都是基于此工作目录，如果不存在，则会创建目录
WORKDIR /jdk

#4.一个复制命令，把jdk安装文件复制到镜像中，语法：ADD <src>... <dest>,注意：jdk*.tar.gz使用的是相对路径
COPY ./jdk1.8.0_311 /jdk

#5.设置时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

#6.配置环境变量
ENV JAVA_HOME=/jdk
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH=$JAVA_HOME/bin:$PATH

#容器启动时需要执行的命令
#CMD ["java","-version"]
```
## 打镜像
```shell
docker build -t java:8_311 .
## 判断是否打好了
docker run -it java:8_311 /bin/bash
java -version
显示出正确java版本
exit
## 保存镜像到tar包
docker save -o java.tar java:8_311
```