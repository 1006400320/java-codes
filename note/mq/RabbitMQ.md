
RabbitMQ 是 AMQP（Advanced Message Queuing Protocol）协议的 Erlang 的实现

> AMQP，即Advanced Message Queuing Protocol，一个提供统一消息服务的应用层标准高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品，不同的开发语言等条件的限制。

# 安装
## docker 安装
```shell script
# 拉取镜像
docker pull rabbitmq:management

# 运行
docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management

# 账号密码 guest，guest
```

[Demos](../../demos/src/main/java/com/linhuanjie/mq/rabbitmq)
 



