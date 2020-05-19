# 拉取镜像

```shell script
docker pull rabbitmq:management
```

# 运行
```shell script
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management
```

显示 ip:15672

账号密码guest，guest