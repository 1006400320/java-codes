package com.linhuanjie.activemq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : ActiveMQ配置类
 * @CreateTime : 2020.04.04 19:30
 **/
@Configuration
public class ActiveMQConfig {

    /**
     * 添加JMS事务管理器
     * @param connectionFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager createTransactionManager(ConnectionFactory connectionFactory){
        return new JmsTransactionManager(connectionFactory);
    }
}
