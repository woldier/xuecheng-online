package com.xuecheng.content.service.jobhandler;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.feignclient.search.CourseIndex;
import com.xuecheng.content.feignclient.search.SearchCourseIndexClient;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.service.CoursePublishService;
import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.messagesdk.service.MessageProcessAbstract;
import com.xuecheng.messagesdk.service.MqMessageService;
import com.xuecheng.messagesdk.service.impl.MqMessageServiceImpl;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布任务实现类
 * @date 2023/3/27 10:27
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class CoursePublishTask extends MessageProcessAbstract {

    /**
     * @return void
     * @description 任务调度入口
     * @author: woldier
     * @date: 2023/3/27 11:09
     */

    private final CoursePublishService coursePublishService;
    private final SearchCourseIndexClient searchCourseIndexClient;

    @XxlJob("CoursePublishJobHandler") //任务名
    public void coursePublishJobHandler() throws Exception {
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        log.debug("shardIndex=" + shardIndex + ",shardTotal=" + shardTotal);
        //参数:分片序号、分片总数、消息类型、一次最多取到的任务数量、一次任务调度执行的超时时间
        process(shardIndex, shardTotal, "course_publish", 30, 60);
    }


    /**
     * @param mqMessage
     * @return boolean
     * @description 任务执行
     * @author: woldier
     * @date: 2023/3/27 10:28
     */
    @Override
    public boolean execute(MqMessage mqMessage) {
        //消息id
        Long id = mqMessage.getId();
        //取出对应的course 这里我们约定课程id存在于message表的 businessKey1 字段
        Integer courseId = Integer.valueOf(mqMessage.getBusinessKey1());


        try {
            if (!Boolean.parseBoolean(mqMessage.getStageState1()))
                //执行阶段1 ,静态化页面
                generateCourseHtml(mqMessage, courseId);
            //执行阶段2 , 写入 elasticsearch
            if (!Boolean.parseBoolean(mqMessage.getStageState2()))
                saveCourseIndex(mqMessage, courseId);
            if (!Boolean.parseBoolean(mqMessage.getStageState3()))
                //执行阶段3 , 写入redis
                saveCourseCache(mqMessage, courseId);
        } catch (XueChengPlusException e) {
            log.error("执行任务出错{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //生成课程静态化页面并上传至文件系统
    public void generateCourseHtml(MqMessage mqMessage, long courseId) throws XueChengPlusException {

        log.debug("开始进行课程静态化,课程id:{}", courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理
        int stageOne = mqMessageService.getStageOne(id);
        if (stageOne > 0) {
            log.debug("课程静态化已处理直接返回，课程id:{}", courseId);
            return;
        }
        //生成静态页面

        File file = coursePublishService.generateCourseHtml(courseId);
        coursePublishService.uploadCourseHtml(courseId, file);
        file.delete();


        //保存第一阶段状态
        mqMessageService.completedStageOne(id);

    }

    //将课程信息缓存至redis
    public void saveCourseCache(MqMessage mqMessage, long courseId) {
        log.debug("将课程信息缓存至redis,课程id:{}", courseId);
        // TODO 将课程信息缓存至redis
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    //保存课程索引信息
    public void saveCourseIndex(MqMessage mqMessage, long courseId) throws XueChengPlusException {
        log.debug("保存课程索引信息,课程id:{}", courseId);

        CoursePublish coursePublish = coursePublishService.getById(courseId);
        CourseIndex courseIndex = new CourseIndex();
        BeanUtils.copyProperties(coursePublish, courseIndex);


        if (!searchCourseIndexClient.add(courseIndex)) {
            log.error("添加索引失败");
            XueChengPlusException.cast("添加索引失败");
        }
        //保存第二阶段状态
        MqMessageService mqMessageService = this.getMqMessageService();
        mqMessageService.completedStageTwo(courseId);
    }


}




