package com.xuecheng.orders.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description MQ消息队列配置类
 * @date 2023/4/13 15:46
 **/
@Configuration
public class PayNotifyConfig {
    //交换机
    public static final String PAYNOTIFY_EXCHANGE_FANOUT = "paynotify_exchange_fanout";
    //支付结果通知消息类型
    public static final String MESSAGE_TYPE = "payresult_notify";
    //声明交换机
    @Bean(PAYNOTIFY_EXCHANGE_FANOUT)
    public FanoutExchange paynotify_exchange_fanout(){
        // 三个参数：交换机名称、是否持久化、当没有queue与其绑定时是否自动删除
        return new FanoutExchange(PAYNOTIFY_EXCHANGE_FANOUT, true, false);
    }
}
