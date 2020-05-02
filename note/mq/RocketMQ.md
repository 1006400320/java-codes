
# 启动&关闭
## 单机
```shell script
# 启动 NameServer
nohup sh bin/mqnamesrv &

tail -f ~/logs/rocketmqlogs/namesrv.log

# 启动 Broker
nohup sh bin/mqbroker -n localhost:9876 autoCreateTopicEnable -c /usr/local/software/rocketmq-4.3.0/conf/broker.conf &

tail -f ~/logs/rocketmqlogs/broker.log 

# 关闭 NameServer
sh bin/mqshutdown namesrv
# 关闭 Broker
sh bin/mqshutdown broker
```
## 多机
```shell script

# 启动broker
cd /usr/local/rocketmq/bin
nohup sh mqbroker -c /usr/local/rocketmq/conf/2m-2s-sync/broker-a.properties &

...
````

# 配置 rocketmq-console
```shell script
git clone https://github.com/apache/rocketmq-externals
cd rocketmq-console

# 注意：打包前在rocketmq-console项目的中配置namesrv地址：
vim rocketmq-console/src/main/resources/application.properties
rocketmq.config.namesrvAddr=you_host:9876;

# 在 target 目录下生成 jar 包
mvn clean package -Dmaven.test.skip=true

# 启动rocketmq-console
nohup java -jar rocketmq-console-ng-1.0.0.jar &

```


由于broker启动时，需要占用大量内存，测试时，可以修改配置文件，以减小内存的消耗
```shell script
# 1. 修改bin目录下的runserver.sh文件：
 JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn128m -XX:PermSize=128m -XX:MaxPermSize=320m"
# 同理修改runbroker.sh文件：
JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn128m"
# 同理修改tools.sh文件：
JAVA_OPT="${JAVA_OPT} -server -Xms256m -Xmx256m -Xmn128m -XX:PermSize=128m -XX:MaxPermSize=128m"
```
修改配置文件 ./conf/broker.conf
文件末尾加入`namesrvAddr = 192.168.2.176:9876  brokerIP1 = 192.168.2.176  ` (虚拟机地址)
![undefined](http://ww1.sinaimg.cn/large/005CzYvJgy1gdq8lxh8rmj30i40bg0tg.jpg)


启动broker命令：`nohup bin/mqbroker -n 192.168.2.176:9876 autoCreateTopicEnable -c /usr/local/software/rocketmq-4.3.0/conf/broker.conf &`

> 参考：[RocketMq-单机搭建](https://blog.csdn.net/qq_33243189/article/details/90955464)
