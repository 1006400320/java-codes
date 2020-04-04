package com.linhuanjie.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020.04.04 17:48
 **/
@Service
public class MessageService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value("${activemq.queue-name}")
    private String name;

    @Transactional // 对消息发送加入事务管理（同时也对JDBC数据库的事务生效）
    public void sendMessage() {
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                int a = 10 / 0;
            }
            jmsMessagingTemplate.convertAndSend(name, "ActiveMQ_tx_" + i);
        }
    }

}
