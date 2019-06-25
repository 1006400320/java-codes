package com.linhuanjie.activemq.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author: linhuanjie
 * @description: ActiveMQ监听器
 * @createTime : 2019-05-19 13:50
 * @email: lhuanjie@qq.com
 */
public class JMSListener implements MessageListener {


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("topic_receive..."+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
