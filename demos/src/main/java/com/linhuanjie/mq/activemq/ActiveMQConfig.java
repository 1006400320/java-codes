package com.linhuanjie.mq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Value;
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
     * RedeliveryPolicy重发策略设置
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy() {
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        // 是否在每次尝试重新发送失败后,增长这个等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        // 重发次数,默认为6次   这里设置为10次
        redeliveryPolicy.setMaximumRedeliveries(10);
        // 重发时间间隔,默认为1秒
        redeliveryPolicy.setInitialRedeliveryDelay(2);
        // 第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
        redeliveryPolicy.setBackOffMultiplier(2);
        // 是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(false);
        // 设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);

        return redeliveryPolicy;
    }


    /**
     * 连接工厂
     *
     * @param url
     * @param redeliveryPolicy
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(@Value("${spring.activemq.broker-url}") String url,
                                                               @Value("${spring.activemq.user}") String user,
                                                               @Value("${spring.activemq.password}") String password,
                                                               RedeliveryPolicy redeliveryPolicy) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(user, password, url);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        return activeMQConnectionFactory;
    }

    /**
     * 添加JMS事务管理器
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager createTransactionManager(ConnectionFactory connectionFactory) {
        return new JmsTransactionManager(connectionFactory);
    }

    /**
     * JMS监听器工厂
     *
     * @param connectionFactory
     * @param transactionManager
     * @return
     */
    @Bean("jmsQueryListenerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, PlatformTransactionManager transactionManager) {
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

