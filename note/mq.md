

#  MQ(Message Queue)应用场景：
- 异步
- 解耦
- 削峰

# 常见的消息中间件

特性 | ActiveMQ | RabbitMQ | RocketMQ | Kafka
:-: |:-: |:-: |:-: | :-:
开发语言 | Java | Erlang | Java | Scala
单机吞吐量 | 万级 | 万级 | 10万级 | 10万级
时效性 | 毫秒级 | 微秒级 | 毫秒级 | 毫秒级
可用性 | 高（支持主从架构） | 高（支持主从架构） | 非常高（分布式架构） | 非常高（分布式架构） 
功能特性 | 成熟的产品，很多公司得到应用；有较多的文档；各种协议支持较好 | 基于Erlang开发,所以并发能力很强，性能几号，延时很低，管理界面较丰富 | MQ功能比较完备，扩张性佳 | 像一些消息查询，消息回溯等功能没有提供，在大数据领域应用广

#  JMS（Java Message Service）
**消息中间件**利用高效可靠的消息传递机智进行平台无关的数据交流，并基于数据通信来进行**分布式系统**的集成。它可以在分布式环境下扩展进程间的通信。对于消息中间件，常见的角色大致有 **Producer**（生产者）、**Consumer**（消费者）

## JMS消息模型

###  1. 点对点模式 （Queue队列模型）
> P2P (Pointer-to-Pointer ) 即生产者和消费者之间的消息往来
![mq_queue.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gdgwy2wli2j30vq09vdmq.jpg)
###  点对点模型的特点
- 每个消息只有一个消费者（Consumer）（即**一旦被消费，消息就从队列中移除**）；
- 发送者和接收者之间在**时间上没有依赖性**（也就是发送消息后，无论有没有接收者监听消息，都会保存在队列中）
- 接收者在成功接收消息之后需向队列应答成功
###  举例
![mq_queue_case.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gdgx7oy3vcj30v308wmzl.jpg)

### 2. 发布/订阅模型（topic主题模型）
> Public-Subscribe
包含三个角色：**主题（Topic）**、**发布者（Publisher）**、**订阅者（Subscriber）**，多个发布者将消息发送到topic，系统将这些消息投递到订阅此topic的订阅者
![mq_topic.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gdgwyblffwj30vk0cqn71.jpg)
###  发布/订阅模型的特点
- 每个消息可以有多个订阅者 （一对多）
- 发布者和订阅者之间有时间上的依赖性 （订阅者先订阅主题，发布者再发送消息）
- 订阅者必须保持运行的状态，才能接受发布者发布的消息

# JMS编程API
![mq_JMS.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gdgxt5te79j30g00chdk6.jpg)

要素 | 作用
:-: | :-:
Destination | 表示**消息所走通道的目标**，用来定义消息从发送端发出后要走的通道，而不是接收方。Destination属于管理类对象
ConnectionFactory | 连接工厂，用于创建连接对象，ConnectionFactory 属于管理类的对象
Connection | 连接接口，用于创建 Session
Session | 回话接口，用于创建**消息发送者**、**消息接收者** 和 **消息**
MessageConsume | 消息的消费者，订阅消息并处理消息
MessageProducer | 消息的生产者，发送消息

##  1. ConnectionFactory
创建Connection对象的工厂，针对两种不同的jms消息模型，  
分别有**QueueConnectionFactory**和**TopicConnectionFactory**两种。

##  2. Destination （Queue & Topic）
Destination的意思是消息**生产者的消息发送目标**或者说**消息消费者的消息来源**。  
对于消息生产者来说，它的Destination是某个队列（Queue）或某个主题（Topic）;  
对于消息消费者来说，它的Destination也是某个队列（Queue）或主题（Topic）。  
所以，Destination实际上就是两种类型的对象：**Queue**、**Topic**

##  3. Connection
Connection表示在客户端和JMS系统之间**建立的链接**（对TCP/IP socket的包装）。  
Connection可以产生一个或多个Session

##  4. Session
Session 是我们对消息进行操作的接口，可以通过session创建**生产者、消费者、消息**等。  
Session 提供了**事务**的功能，如果需要使用session发送/接收多个消息时，可以将这些发送/接收动作放到一个事务
中。


##  5. Producer
Producer（消息生产者）：消息生产者由Session创建，并用于将消息发送到Destination。  
同样，消息生产者分两种类型：**QueueSender**和**TopicPublisher**。  
可以调用消息生产者的方法（**send**或**publish**方法）发送消息。

##  6. Consumer
Consumer（消息消费者）：消息消费者由Session创建，用于接收被发送到Destination的消息。  
两种类型：**QueueReceiver**和**TopicSubscriber**。  
可分别通过session的**createReceiver**(Queue)或**createSubscriber**(Topic)来创建。  
可分别通过session的**createReceiver**(Queue)或**createSubscriber**(Topic)来创建。  
当然，也可以session的**creatDurableSubscriber**方法来创建**持久化**的订阅者。


##  7. MessageListener
消息监听器。如果注册了消息监听器，一旦消息到达，将自动调用监听器的onMessage方法。  
EJB中的MDB（Message-Driven Bean）就是一种MessageListener。

# JMS消息组成

结构 | 描述
:-: | :-:
JMS Provider | 消息中间件/消息服务器
JMS Producer | 消息生产者
JMS Consumer | 消息消费者
JMS Message | 消息

##  JMS Message 由三部分组成：
###  1.  消息头
JMS消息头预定义了若干字段用于客户端与JMS提供者之间识别和发送消息，预编译头如下：

名称 | 描述
:-: | :-:
<font style="color:red">JMSDestination</font> | 消息发送的 Destination，在发送过程中由提供者设置
<font style="color:red">JMSMessageID</font> | 唯一标识提供者发送的每一条消息。这个字段是在发送过程中由提供者设置的，客户机只能在消息发送后才能确定消息的 JMSMessageID
<font style="color:red">JMSDeliveryMode</font> | 消息持久化。包含值 DeliveryMode.PERSISTENT 或者 DeliveryMode.NON_PERSISTENT
JMSTimestamp | 提供者发送消息的时间，由提供者在发送过程中设置
<font style="color:red">JMSExpiration</font> | 消息失效的时间，毫秒，值 0 表明消息不会过期，默认值为0
<font style="color:red">JMSPriority</font> | 消息的优先级，由提供者在发送过程中设置。优先级 0 的优先级最低，优先级 9 的优先级最高。0-4为普通消息，5-9为加急消息。ActiveMQ不保证优先级高就一定先发送，只保证了加急消息必须先于普通消息发送。默认值为0
<font style="color:red">JMSCorrelationID</font> | 通常用来链接响应消息与请求消息，由发送消息的 JMS 程序设置。
JMSReplyTo | 请求程序用它来指出回复消息应发送的地方，由发送消息的 JMS 程序设置
JMSType JMS | 程序用它来指出消息的类型。
JMSRedelivered | 消息的重发标志，false，代表该消息是第一次发生，true，代表该消息为重发消息

需要注意的是，在传送消息时，消息头的值由JMS提供者来设置，  
因此开发者使用以上`setJMSXXX()`方法分配的值就被忽略了，  
只有以下几个值是可以由开发者设置的：
- JMSCorrelationID
- JMSReplyTo
- JMSType

###  2. 消息体

xxxMessage | 类型
:-: |:-:
<font style="color:red">TextMessage</font> | 一个字符串对象
<font style="color:red">ObjectMessage</font> | 一个序列化的 Java 对象
<font style="color:red">BytesMessage</font> | 一个字节的数据流
MapMessage | 一套名称-值对
StreamMessage | Java原始值的数据流

生成者Demo --> [SpringBootProducer.java: Lines 18-135](../demos/src/test/java/com/linhuanjie/demos/activemq/SpringBootProducer.java#L18-L135)  
监听者Demo --> [SpringBootListener.java: Lines 11-67](../demos/src/main/java/com/linhuanjie/activemq/SpringBootListener.java#L11-L67)

###  3. 消息属性
我们可以给消息设置自定义属性，这些属性主要是提供给应用程序的。  
对于**实现消息过滤功能**，消息属性非常有用，JMS API定义了一些标准属性。  
JMS服务提供者可以选择性的提供部分标准属性。
```java
message.setStringProperty("Property",Property); //自定义属性
```
Demo --> [SpringBootProducer.java: Line 127](../demos/src/test/java/com/linhuanjie/demos/activemq/SpringBootProducer.java#L127)

# 消息持久化

##  ActiveMQ提供了以下三种的消息存储方式：
1.  Memory 消息存储（基于内存的消息存储。）
2.  基于日志消息存储方式，**KahaDB**是ActiveMQ的默认日志存储方式，它提供了容量的提升和恢复能力。
3. 基于JDBC的消息存储方式（数据存储于数据库，如：MySQL中）。

## ActiveMQ持久化机制流程图：
![ActiveMQ生产者消息持久化.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gdgzmuo6hnj30f908ydhe.jpg)
![ActiveMQ消费者消息持久化.png](http://ww1.sinaimg.cn/large/005CzYvJgy1gdgzn32cz3j30fn0aj40e.jpg)

## JDBC持久化配置
### 1. 配置application.yml
```yml
server:
    port: 9001
spring:
    activemq:
        broker-url: tcp://192.168.66.133:61616
        user: admin
        password: admin
    jms:
        pub-sub-domain: false  # false：点对点队列模式， true：发布/订阅模式
        template:
            delivery-mode: persistent # 持久化
    activemq:
        name: springboot-queue01
```
###  2. 修改activemq.xml
```xml
    <!--配置数据库连接池-->
    <bean name="mysql-ds" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
        <property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://192.168.66.133:3306/db_activemq" />
        <property name="username" value="root" />
        <property name="password" value="123456"/>
    </bean>
    
    <!--JDBC Jdbc用于master/slave模式的数据库分享 -->
    <persistenceAdapter>
        <jdbcPersistenceAdapter dataSource="#mysql-ds"/>
    </persistenceAdapter>
```
### 3. 拷贝mysql及durid数据源的jar包到activemq的lib目录下
- druid-1.1.6.jar
- mysql-connector-java-5.1.46.jar

### 4. 重启activemq

# 消息事务

消息事务，是保证消息传递**原子性**的一个重要特征，和JDBC的事务特征类似。

一个事务性发送，其中一组消息要么能够全部保证到达服务器，要么都不到达服务器。  
生产者、消费者与消息服务器之间都支持事务性；  
ActionMQ的事务主要偏向在生产者的应用。

##  生产者事务
1. 原生JMS事务 Demo --> [SpringBootProducer.java: Lines 138-176](../demos/src/test/java/com/linhuanjie/demos/activemq/SpringBootProducer.java#L138-L176)
2. Spring的JmsTransactionManager功能
   1. 添加JMS事务管理器  Demo --> [ActiveMQConfig.java: Lines 10-28](../demos/src/main/java/com/linhuanjie/activemq/ActiveMQConfig.java#L10-L28)
   2. 生产者业务类 Demo --> [MessageService.java: Lines 24-32](../demos/src/main/java/com/linhuanjie/activemq/MessageService.java#L24-L32)
   3. 测试发送方法 Demo --> [SpringBootProducer.java: Lines 178-184](../demos/src/test/java/com/linhuanjie/demos/activemq/SpringBootProducer.java#L178-L184)

##  消费者事务






> 参考：http://yun.itheima.com/course/636.html