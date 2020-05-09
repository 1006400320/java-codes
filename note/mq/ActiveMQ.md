

# 启动&关闭
```shell script
...activemq/bin/activemq start
...activemq/bin/activemq stop
```

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
1. 原生JMS事务 Demo --> [SpringBootProducer.java: Lines 138-176](../../demos/src/test/java/com/linhuanjie/demos/mq/activemq/SpringBootProducer.java#L138-L176)
2. Spring的JmsTransactionManager功能
   1. 添加JMS事务管理器  Demo --> [ActiveMQConfig.java: Lines 10-28](../../demos/src/main/java/com/linhuanjie/mq/activemq/ActiveMQConfig.java#L10-L28)
   2. 生产者业务类 Demo --> [MessageService.java: Lines 24-32](../../demos/src/main/java/com/linhuanjie/mq/activemq/MessageService.java#L24-L32)
   3. 测试发送方法 Demo --> [SpringBootProducer.java: Lines 178-184](../../demos/src/test/java/com/linhuanjie/demos/mq/activemq/SpringBootProducer.java#L178-L184)

##  消费者事务
SpringBoot 默认会开启消费者事务
消费完信息，执行 `session.commit();` Demo --> [ActiveMQListener.java: Lines 10-79](../../demos/src/main/java/com/linhuanjie/mq/activemq/ActiveMQListener.java#L10-L79)

# 消息确认机制
JMS消息只有在被确认之后，才认为已经被成功地消费了。  
消息的确认通常包含三个阶段:
1. 消费者接收消息
2. 消费者处理消息
3. 消息被确认

**如果开启了事务，则事务被提交时，就会自动确认**
没开启事务时，消息何时被确认，则取决于创建会话时的**应答模式** （acknowledgement mode）

值 | 描述
:-: | :-:
Session.AUTO_ACKNOWLEDGE | 当客户成功的从receive方法返回的时候，或者从MessageListener.onMessage方法成功返回的时候，会话自动确认客户收到的消息
Session.CLIENT_ACKNOWLEDGE | 客户通过消息的acknowledge方法确认消息。需要注意的是，在这种模式中，确认是在会话层上进行：确认一个被消费的消息将自动确认所有已被会话消费的消息。例如，如果一个消息消费者消费了10个消息，然后确认第5个消息，那么所有10个消息都被确认。（SpringBoot整合 ActiveMQ 时，Session.CLIENT_ACKNOWLEDGE会失效，应填4）
Session.DUPS_ACKNOWLEDGE | 该选择只是会话迟钝确认消息的提交。如果JMS provider失败，那么可能会导致一些重复的消息。如果是重复的消息，那么JMS provider必须把消息头的JMSRedelivered字段设置为true

## SpringBoot环境开启 client_acknowledge 手动确认
配置类：
```java
@Configuration
public class ActiveMqConfig {
    @Bean(name = "jmsQueryListenerFactory")
    public DefaultJmsListenerContainerFactory
    jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new
                DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setSessionTransacted(false); // 不开启事务操作
        factory.setSessionAcknowledgeMode(4); //手动确认
        return factory;
    }
}
```
消费者：
```java
/**
 * 消息消费者
 */
@Component
public class Consumer {
    /**
     * 接收消息的方法
     */
    @JmsListener(destination = "${activemq.name}", containerFactory = "jmsQueryListenerFactory")
    public void receiveMessage(TextMessage textMessage) {
        try {
            System.out.println("消息内容：" + textMessage.getText() + ",是否重发："
                    + textMessage.getJMSRedelivered());
            textMessage.acknowledge(); // 确认收到消息，一旦消息确认，消息不会重新发送
            throw new RuntimeException("test");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
```

# 消息投递方式
## 异步投递

###  1、异步投递 vs 同步投递同步发送：

消息生产者使用持久（Persistent）传递模式发送消息的时候，Producer.send() 方法会被阻塞，直到broker 发送一个确认消息给生产者(ProducerAck)，这个确认消息暗示broker已经成功接收到消息并把消息保存到二级存储中。
- 异步发送:  
如果应用程序能够容忍一些消息的丢失，那么可以使用异步发送。异步发送不会在受到broker的确认之前一直阻塞 Producer.send方法。
想要使用异步，在brokerURL中增加 `jms.alwaysSyncSend=false&jms.useAsyncSend=true`属性
> 1）如果设置了alwaysSyncSend=true系统将会忽略useAsyncSend设置的值都采用同步  
> 2）当alwaysSyncSend=false时，“NON_PERSISTENT”(非持久化)、事务中的消息将使用“异步发送”  
> 3）当alwaysSyncSend=false时，如果指定了useAsyncSend=true，“PERSISTENT”类型的消息使用异步发
        送。如果useAsyncSend=false，“PERSISTENT”类型的消息使用同步发送。

总结： 默认情况(`alwaysSyncSend=false,useAsyncSend=false`)，非持久化消息、事务内的消息均采用
异步发送；对于持久化消息采用同步发送！！！

### 2、配置异步投递的方式
```java
@Configuration
public class ActiveConfig {
    /**
     * 配置用于异步发送的非持久化JmsTemplate
     */
    @Autowired
    @Bean
    public JmsTemplate asynJmsTemplate(PooledConnectionFactory pooledConnectionFactory) {
        JmsTemplate template = new JmsTemplate(pooledConnectionFactory);
        template.setExplicitQosEnabled(true);
        template.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        return template;
    }

    /**
     * 配置用于同步发送的持久化JmsTemplate
     */
    @Autowired
    @Bean
    public JmsTemplate synJmsTemplate(PooledConnectionFactory
                                              pooledConnectionFactory) {
        JmsTemplate template = new JmsTemplate(pooledConnectionFactory);
        return template;
    }
}
```

### 3、异步投递如何确认发送成功？

异步投递丢失消息的场景是：生产者设置 UseAsyncSend=true，使用 producer.send（msg）持续发送消息。  
由于消息不阻塞，生产者会认为所有 send 的消息均被成功发送至 MQ。如果 MQ 突然宕机，此时生产者端内存中尚未被发送至 MQ 的消息都会丢失。  
这时，可以给异步投递方法接收回调，以确认消息是否发送成功
```java
    /**
     * 异步投递，回调函数
     *
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public String sendQueue() {
        Connection connection = null;
        Session session = null;
        ActiveMQMessageProducer producer = null;
        // 获取连接工厂
        ConnectionFactory connectionFactory =
                jmsMessagingTemplate.getConnectionFactory();
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(name);
            int count = 10;
            producer = session.createProducer(queue);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            long start = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                //创建需要发送的消息
                TextMessage textMessage = session.createTextMessage("Hello");
                //设置消息唯一ID
                String msgid = UUID.randomUUID().toString();
                textMessage.setJMSMessageID(msgid);
                producer.send(textMessage, new AsyncCallback() {
                    @Override
                    public void onSuccess() {
                        // 使用msgid标识来进行消息发送成功的处理
                        System.out.println(msgid + " 消息发送成功");
                    }

                    @Override
                    public void onException(JMSException exception) {
                        // 使用msgid表示进行消息发送失败的处理
                        System.out.println(msgid + " 消息发送失败");
                        exception.printStackTrace();
                    }
                });
            }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
```

## 延迟投递

生产者提供两个发送消息的方法，一个是即时发送消息，一个是延时发送消息。
###  1、修改activemq.xml
添加 `broker` 标签添加 `schedulerSupport="true"`
```
<broker xmlns="http://activemq.apache.org/schema/core"  ...  schedulerSupport="true" >
    xxx
</broker>
```
###  2、在代码中设置延迟时长
```java
    /**
     * 延时投递
     * @return
     */
    @Test
    public String sendQueue() {
        Connection connection = null;
        Session session = null;
        ActiveMQMessageProducer producer = null;
        // 获取连接工厂
        ConnectionFactory connectionFactory =
                jmsMessagingTemplate.getConnectionFactory();
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(name);
            int count = 10;
            producer = (ActiveMQMessageProducer) session.createProducer(queue);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //创建需要发送的消息
            TextMessage textMessage = session.createTextMessage("Hello");
            //设置延时时长(延时10秒)
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,
                    10000);
            producer.send(textMessage);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
```

## 定时投递

###  1、启动类添加定时注解
```java
    @SpringBootApplication
    @EnableScheduling // 开启定时功能
    public class MyActiveMQApplication {
        public static void main(String[] args) {
            SpringApplication.run(MyActiveMQApplication.class,args);
        }
    }
```

### 2、在生产者添加@Scheduled设置定时

```java
    /**
     *消息生产者
     */
    @Component
    public class ProducerController3 {
        @Value("${activemq.name}")
        private String name;
        @Autowired
        private JmsMessagingTemplate jmsMessagingTemplate;

        /**
         * 延时投递
         */
        //每隔3秒定投
        @Scheduled(fixedDelay = 3000)
        public void sendQueue() {
            jmsMessagingTemplate.convertAndSend(name, "消息ID:" +
                    UUID.randomUUID().toString().substring(0, 6));
            System.out.println("消息发送成功...");
        }
    }
```

# 死信队列
`DLQ-Dead Letter Queue`，死信队列，用来保存处理失败或者过期的消息  
出现以下情况时，消息会被重发：
- 事务回滚（A transacted session is used and rollback() is called.）
- 事务提交前，会话关闭（A transacted session is closed before commit is called.）
- 手动应答模式下，调用Session.recover() （A session is using CLIENT_ACKNOWLEDGE and Session.recover() is called.）

当一个消息被重发超过6(缺省为6次)次数时，会给broker发送一个"Poison ack"，这个消息被认为是`a poison pill`，这时broker会将这个消息发送到死信队列，以便后续处理。  
注意两点：
1. 缺省持久消息过期，会被送到DLQ，**非持久消息不会送到DLQ**
2. 缺省的死信队列是ActiveMQ.DLQ，如果没有特别指定，死信都会被发送到这个队列。

可以通过配置文件(activemq.xml)来调整死信发送策略。
Demo --> [ActiveMQConfig.java: Lines 23-43](../../demos/src/main/java/com/linhuanjie/mq/activemq/ActiveMQConfig.java#L23-L43)

# ActiveMQ企业面试经典问题
## ActiveMQ 宕机了怎么办？
可以使用集群，来保证高可用
ActiveMQ 主从集群方案：Zookeeper集群 + Replicated LevelDB + ActiveMQ 集群

## 如何防止消费方消息重复消费 ？（消费方幂等问题）
解决思路：
1. 如果消费方是做数据库操作，那么可以把消息的ID作为表的唯一主键（或唯一索引），这样重复消费消息是，就会发生主键冲突，避免产生脏数据。
2. 如果消费方不是做数据库操作，那么可以借助第三方应用，如Redis来记录消费记录。消费完，将消息的ID作为key存入redis，每次消费前，先查询redis有没有该消息的消费记录。

## 如何防止消息丢失？
1. 在消息生产者和消费者使用事务
2. 在消费方采用手动消息确认（ACK）
3. 消息持久化，例如JDBC或日志

## 什么是死信队列？
[死信队列](#死信队列)


> 参考：http://yun.itheima.com/course/636.html