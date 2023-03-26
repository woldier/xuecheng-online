package com.xuecheng.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.enums.CourseAuditStatus;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.model.po.CoursePublishPre;
import com.xuecheng.content.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程预发布服务实现类
 * @date 2023/3/25 18:55
 **/
@Service
@RequiredArgsConstructor
public class CoursePublishPreCustomServiceImpl implements CoursePublishPreCustomService {


    private final CourseBaseMapper courseBaseMapper;
    private final CourseBaseInfoService courseBaseInfoService;
    private final CourseMarketService courseMarketService;
    private final CoursePublishService coursePublishService;
    private final CoursePublishPreService coursePublishPreService;
    private final TeachplanService teachplanService;

    @Override
    @Transactional
    public void commitAudit(Long companyId, Long courseId) throws XueChengPlusException {
        /**
         *
         * 1、查询课程基本信息、课程营销信息、课程计划信息等课程相关信息，整合为课程预发布信息。
         * 2、向课程预发布表course_publish_pre插入一条记录，如果已经存在则更新，审核状态为：已提交。
         * 3、更新课程基本表course_base课程审核状态为：已提交。
         * 约束：
         * 1、对已提交审核的课程不允许提交审核。
         * 2、本机构只允许提交本机构的课程。
         * 3、没有上传图片不允许提交审核。
         * 4、没有添加课程计划不允许提交审核。
         * */
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        //如果当前课程审核状态为已提交,不允许再提交审核
        if (courseBaseInfo.getAuditStatus().equals(CourseAuditStatus.COMMIT.getCode()))
            XueChengPlusException.cast("课程已经提交审核,请等待课程审核结果");
        // 查询课程预览的信息 里面包含课程基本信息,课程营销信息,课程计划信息
        CoursePreviewDto coursePreviewInfo = coursePublishService.getCoursePreviewInfo(courseId);
        //判断课程id是否匹配
        if (!coursePreviewInfo.getCourseBase().getId().equals(courseId))
            XueChengPlusException.cast("课程id不匹配");
        CoursePublishPre coursePublishPre = new CoursePublishPre();
        //判断课程信息是否完整
        if (StringUtils.isEmpty(coursePreviewInfo.getCourseBase().getPic()))
            XueChengPlusException.cast("课程图片信息为空,请先修改");

        /*
         * 拷贝信息到课程语法与实体
         * */
        BeanUtils.copyProperties(coursePreviewInfo.getCourseBase(), coursePublishPre);

        //添加营销信息
        CourseMarket courseMarket = courseMarketService.getById(courseId);
        String courseMarketJsonString = JSON.toJSONString(courseMarket);
        coursePublishPre.setMarket(courseMarketJsonString);

        //添加课程信息
        List<TeachplanDto> teachplanDtoList = teachplanService.selectTreeNodes(courseId);
        if (teachplanDtoList.isEmpty())
            XueChengPlusException.cast("课程计划信息为空,不允许提交审核");
        String teachplanDtoListJSONString = JSON.toJSONString(teachplanDtoList);
        coursePublishPre.setTeachplan(teachplanDtoListJSONString);

        //设置预发布记录状态,已提交
        coursePublishPre.setStatus(CourseAuditStatus.COMMIT.getCode());

        //课程公司id
        coursePublishPre.setCompanyId(companyId);

        coursePublishPre.setCreateDate(LocalDateTime.now());

        coursePublishPreService.saveOrUpdate(coursePublishPre);
        //更新课程基本信息表状态
        courseBaseInfo.setAuditStatus(CourseAuditStatus.COMMIT.getCode());
        courseBaseMapper.updateById(courseBaseInfo);
    }
}
