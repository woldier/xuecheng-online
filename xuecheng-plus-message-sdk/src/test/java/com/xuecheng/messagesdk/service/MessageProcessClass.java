package com.xuecheng.messagesdk.service;

import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.messagesdk.service.impl.MqMessageHistoryServiceImpl;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author woldier
 * @version 1.0
 * @description MessageProcessAbstract 测试实现
 * @date 2023/3/27 9:12
 **/
@Service
@Slf4j
public class MessageProcessClass extends MessageProcessAbstract{


    @Autowired
    private MqMessageService mqMessageService;
    private MqMessageHistoryServiceImpl mqMessageHistoryServiceImpl;

    @Override
    public boolean execute(MqMessage mqMessage) {
        /**
         * 1.获取消息id
         * 2.执行第一阶段任务
         * 3.更新消息状态
         * */
        Long id = mqMessage.getId();

        log.debug("开始执行任务:{}",id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        /*执行第一段*/
        int stageOne = mqMessageService.getStageOne(id);
        if(stageOne<1){
            log.debug("开始执行第一阶段任务");
            System.out.println();
            int i = mqMessageService.completedStageOne(id);
            if(i>0){
                log.debug("完成第一阶段任务");
            }

        }else{
            log.debug("无需执行第一阶段任务");
        }


        return true;
    }


}
