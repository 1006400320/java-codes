package com.linhuanjie.mq.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author: linhuanjie
 * @description: ActiveMQ生产者（一对一）
 * @createTime : 2019-05-19 10:50
 * @email: lhuanjie@qq.com
 */
public class JMSProducer {

    private static final String USERNAME = "lymamacnactcp";
    private static final String PASSWORD = "7c0fb8ce1ef8cbd4db55664126a26ff8";
    private static final String BROKER_URL = "tcp://49.234.41.101:61616";

    public static void main(String[] args) throws Exception {
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        // 2. 创建连接
        Connection connection = connectionFactory.createConnection();

        // 3. 打开连接
        connection.start();

        /**
         * 4.创建session
         * transacted : 是否开启事务
         * acknowledgeMode: 消息确认机制
         */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5.创建目标地址（Queue:点对点消息；Topic:发布订阅消息 ）
        Queue destination = session.createQueue("testQueue");

        // 6. 创建消息生产者
        MessageProducer producer = session.createProducer(destination);

        // 7. 创建消息
        TextMessage textMessage = session.createTextMessage("test message...");

        // 8. 发送消息
        producer.send(textMessage);

        System.out.println("send message done...");

        // 9. 释放资源
        session.close();
        connection.close();
    }

}
