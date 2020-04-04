package com.linhuanjie.activemq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
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

    /**
     * JMS监听器工厂
     * @param connectionFactory
     * @param transactionManager
     * @return
     */
    @Bean("jmsQueryListenerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, PlatformTransactionManager transactionManager){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setTransactionManager(transactionManager);
        // 开启事务
        factory.setSessionTransacted(true);
        // 应答模式： 4-为手动应答
//        factory.setSessionAcknowledgeMode(4);
        return factory;
    }
}

