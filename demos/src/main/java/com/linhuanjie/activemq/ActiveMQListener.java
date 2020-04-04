package com.linhuanjie.activemq;

import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.FileOutputStream;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : SpringBoot 整合 activeMQ 的消费者，生成者 --> com.linhuanjie.demos.activemq.SpringBootProducer
 * @CreateTime : 2020.04.01 23:42
 **/
@Component
public class ActiveMQListener {

    @JmsListener(destination = "${activemq.queue-name}")
    public void receiveMessage(Message message, Session session) throws JMSException {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("textMessage.getText() = " + textMessage.getText());
            }

            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                User user = (User) objectMessage.getObject();
                System.out.println("user = " + user);
            }

            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                System.out.println("名称：" + mapMessage.getString("name"));
                System.out.println("年龄：" + mapMessage.getString("age"));
            }

            if (message instanceof BytesMessage) {
                BytesMessage bytesMessage = (BytesMessage) message;

                System.out.println("接收消息内容：" + bytesMessage.getBodyLength());
                //1.设计缓存数组
                byte[] buffer = new byte[(int) bytesMessage.getBodyLength()];

                //2.把字节消息的内容写入到缓存数组
                bytesMessage.readBytes(buffer);

                //3.构建文件输出流
                FileOutputStream outputStream = new FileOutputStream("h:/tmp/qiao_nai_nai.jpg");

                //4.把数据写出本地硬盘
                outputStream.write(buffer);
            }

            if (message instanceof StreamMessage) {
                StreamMessage streamMessage = (StreamMessage) message;

                //接收消息属性
                System.out.println(streamMessage.getStringProperty("订单"));

                System.out.println(streamMessage.readString());
                System.out.println(streamMessage.readInt());
            }

            int a = 10 / 0;

            // 提交事务
            session.commit();
            System.out.println("commit?");
        } catch (Exception e) {
            System.out.println("rollback..");
            // 回滚事务（一旦事务回滚，该消息会被加进死信队列，然后重发，一共重发6次）
            session.rollback();
            e.printStackTrace();
        }
    }

}
