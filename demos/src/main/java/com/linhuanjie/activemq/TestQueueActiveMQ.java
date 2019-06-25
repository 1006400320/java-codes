package com.linhuanjie.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author: linhuanjie
 * @description: 点对点（queue）
 * @createTime : 2019-05-19 13:17
 * @email: lhuanjie@qq.com
 */
public class TestQueueActiveMQ {

    public static void main(String[] args) {
        try {
            testMQProducerQueue();
            TestMQConsumerQueue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ActiveMQ 生产者
     * @throws Exception
     */
    public static void testMQProducerQueue() throws Exception{
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //6、使用会话对象创建生产者对象
        MessageProducer producer = session.createProducer(queue);
        //7、使用会话对象创建一个消息对象
        TextMessage textMessage = session.createTextMessage("hello!test-queue");
        //8、发送消息
        producer.send(textMessage);
        System.out.println("send_queue:"+textMessage.getText());
        //9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    /**
     * ActiveMQ 消费者
     * @throws Exception
     */
    public static void TestMQConsumerQueue() throws Exception{
        //1、创建工厂连接对象，需要制定ip和端口号
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //2、使用连接工厂创建一个连接对象
        Connection connection = connectionFactory.createConnection();
        //3、开启连接
        connection.start();
        //4、使用连接对象创建会话（session）对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用会话对象创建目标对象，包含queue和topic（一对一和一对多）
        Queue queue = session.createQueue("test-queue");
        //6、使用会话对象创建生产者对象
        MessageConsumer consumer = session.createConsumer(queue);
        //7、向consumer对象中设置一个messageListener对象，用来接收消息
        consumer.setMessageListener( message -> {
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("receive_queue:"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //8、程序等待接收用户消息
        System.in.read();
        //9、关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
