package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CoursePublishMapper;
import com.xuecheng.content.mapper.CoursePublishPreMapper;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.enums.CourseAuditStatus;
import com.xuecheng.content.model.enums.CourseStatus;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.model.po.CoursePublishPre;
import com.xuecheng.content.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布预览service-impl
 * @date 2023/3/16 18:44
 **/
@Service
@RequiredArgsConstructor
public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish> implements CoursePublishService {
    private final CoursePublishPreService coursePublishPreService;
    private final CourseBaseInfoService courseBaseInfoService;
    private final TeachplanService teachplanService;
    private final CourseBaseMapper courseBaseMapper;
    /**
     * @description 获取课程预览所需要的信息
     * @param courseId  课程id
     * @return com.xuecheng.content.model.dto.CoursePreviewDto
     * @author: woldier
     * @date: 2023/3/16 18:45
     */
    @Override
    public CoursePreviewDto getCoursePreviewInfo(Long courseId) throws XueChengPlusException {
        /*
         * 1.查询课程基本,营销信息
         * 2.查询课程计划信息
         */
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        if(courseBaseInfo==null) XueChengPlusException.cast("获取课程基本信息出错");
        List<TeachplanDto> teachplanDtos = teachplanService.selectTreeNodes(courseId);
        if(teachplanDtos==null) XueChengPlusException.cast("获取课程计划信息出错");
        CoursePreviewDto coursePreviewDto = new CoursePreviewDto();
        coursePreviewDto.setCourseBase(courseBaseInfo);
        coursePreviewDto.setTeachplans(teachplanDtos);
        return coursePreviewDto;
    }

    /**
     * @description 课程发布
     * @param courseId  课程id
     * @return void
     * @author: woldier
     * @date: 2023/3/26 17:25
     */
    @Override
    @Transactional()
    public void coursePublish(Long companyId,Long courseId) throws XueChengPlusException {
        /**
         * 1. 判断课程预发布表的审核状态,若不为审核通过不允许发布课程
         * 2. 在课程预发布表中status字段指的是课程审核状态,而在课程发布表中的status字段指的是课程的发布状态,因此我们需要修改status字段
         * 3. 设置课程基本信息表的课程发布状态为已发布
         * 4. 将课程发布任务写入的消息表(用于同步redis,elasticsearch,minio)
         */
        // 查询课程预发布信息
        CoursePublishPre coursePublishPre = coursePublishPreService.getById(courseId);
        //判断是否是本机构课程

        if(!    coursePublishPre.getCompanyId().equals(companyId))
            XueChengPlusException.cast("只允许提交本机构的课程");
        // 判断审核状态
        if(!coursePublishPre.getStatus().equals(CourseAuditStatus.AUDIT.getCode()))
            XueChengPlusException.cast("当前课程审核状态不是审核通过");


        // 对象拷贝,设置coursePublish的status字段
        CoursePublish coursePublish = new CoursePublish();
        BeanUtils.copyProperties(coursePublishPre,coursePublish);
        coursePublish.setStatus(CourseStatus.PUBLISHED.getCode());
        //保存课程发布表
        this.saveOrUpdate(coursePublish);

        //更新课程表的状态
        CourseBase courseBase = new CourseBase();
        courseBase.setId(courseId);
        courseBase.setStatus(CourseStatus.NOT_PUBLISH.getCode());
        courseBaseMapper.updateById(courseBase);
        //删除课程预发布表
        coursePublishPreService.removeById(courseId);
        //写入事务信息到消息表同步信息
        CoursePublishService proxy = (CoursePublishService)AopContext.currentProxy();
        proxy.saveCoursePublishMessage(courseId);
    }


    /**
    * @description TODO 课程发布成功写入消息表
    * @param courseId
    * @return void
    * @author: woldier
    * @date: 2023/3/26 20:15
    */
    @Override
    @Transactional
    public void saveCoursePublishMessage(Long courseId){


    }

}
