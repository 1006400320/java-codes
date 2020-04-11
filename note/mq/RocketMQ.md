

```shell script
# 启动 NameServer
nohup sh bin/mqnamesrv &

tail -f ~/logs/rocketmqlogs/namesrv.log

# 关闭 NameServer
sh bin/mqshutdown namesrv

# 启动 Broker
nohup sh bin/mqbroker -n localhost:9876 &

tail -f ~/logs/rocketmqlogs/broker.log 

# 关闭 Broker
sh bin/mqshutdown broker
```










