package com.linhuanjie.mq.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.jms.*;

/**
 * @author: linhuanjie
 * @description: ActiveMQ订阅者，使用监听器MessageListener（发布者-订阅者）
 * @createTime : 2019-05-19 13:25
 * @email: lhuanjie@qq.com
 */
public class JMSConsumer {
    private static final String USERNAME = "lymamacnactcp";
    private static final String PASSWORD = "7c0fb8ce1ef8cbd4db55664126a26ff8";
    @Value("${LH_HOST}")
    private static String host;
    private static final String BROKER_URL = "tcp://" + host + ":61616";

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
        Topic topic = session.createTopic("testTopic");

        // 6. 创建消息消费者
        MessageConsumer consumer = session.createConsumer(topic);

        // 7. 设置消息监听器来接收消息
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("textMessage = " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 不关闭连接，关闭了就收不到消息了。。。
    }
}
