package com.linhuanjie.activemq.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author: linhuanjie
 * @description: ActiveMQ发布者（发布者-订阅者）
 * @createTime : 2019-05-19 10:50
 * @email: lhuanjie@qq.com
 */
public class JMSProducer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    // 发送的消息数量
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
        // 连接工厂
        ConnectionFactory connectionFactory;
        // 连接
        Connection connection = null;
        // 会话，接受或者发送消息的线程
        Session session;
        // 消息的目的地
        Destination destination;
        // 消息生产者
        MessageProducer producer;

        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD,BROKERURL);
        try {
            // 通过连接工厂创建连接
            connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建消息队列
//            destination = session.createQueue("MyFirstQueue");
            destination = session.createTopic("MyFirstTopic");
            // 创建消息生产者
            producer = session.createProducer(destination);
            sendMessage(session,producer);


        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 发送消息
     * @param session
     * @param producer
     * @throws Exception
     */
    public static void sendMessage(Session session,MessageProducer producer) throws Exception{
        for(int i = 1; i<= JMSProducer.SENDNUM; i++) {
            TextMessage message = session.createTextMessage("MyFirstTopic-ActiveMQ-send..."+i);
            System.out.println("send:"+"MyFirstTopic-ActiveMQ-send..."+i);
            producer.send(message);
        }

    }


}
