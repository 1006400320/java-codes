package com.linhuanjie.activemq;

import org.apache.activemq.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : SpringBoot 整合 activeMQ 的消费者，生成者 --> com.linhuanjie.demos.activemq.SpringBootProducer
 * @CreateTime : 2020.04.01 23:42
 **/
@Component
public class SpringBootListener {

    @JmsListener(destination = "${activemq.queue-name}")
    public void receiveMessage(Message message) throws JMSException {
        if (message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            System.out.println("textMessage.getText() = " + textMessage.getText());
        }

    }

}
