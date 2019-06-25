package com.linhuanjie.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author: linhuanjie
 * @description: ActiveMQ订阅者，使用监听器MessageListener（发布者-订阅者）
 * @createTime : 2019-05-19 13:25
 * @email: lhuanjie@qq.com
 */
public class JMSConsumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;


    public static void main(String[] args) {
        // 连接工厂
        ConnectionFactory connectionFactory;
        // 连接
        Connection connection = null;
        // 会话，接受或者发送消息的线程
        Session session;
        // 消息的目的地
        Destination destination;
        // 消息的消费者
        MessageConsumer consumer;

        // 连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD,BROKERURL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            // 创建session(事务标识:transacted,签收模式:acknowledgeMode)
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("MyFirstTopic");
//            destination = session.createQueue("test-queue");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new JMSListener());


        } catch (JMSException e) {
            e.printStackTrace();
        }


    }

}
