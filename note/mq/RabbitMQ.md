
# 安装
## docker安装
```shell script
# 拉取镜像
docker pull rabbitmq:management

# 运行
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management

# 账号密码 guest，guest
```


 



