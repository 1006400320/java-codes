package com.linhuanjie.mq.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author: linhuanjie
 * @description: ActiveMQ消费者
 * @createTime : 2019-05-19 13:25
 * @email: lhuanjie@qq.com
 */
public class JMSConsumer {
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

        // 6. 创建消息消费者
        MessageConsumer consumer = session.createConsumer(destination);

        // 7. 接收消息
        while (true){
            Message msg = consumer.receive();

            if (msg == null){
                break;
            }
            if (msg instanceof TextMessage){
                TextMessage textMessage = (TextMessage) msg;
                System.out.println("textMessage = " + textMessage);
            }
        }
    }

}
