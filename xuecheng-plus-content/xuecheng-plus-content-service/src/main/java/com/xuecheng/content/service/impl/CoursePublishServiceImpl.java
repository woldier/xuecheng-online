package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CoursePublishMapper;
import com.xuecheng.content.mapper.CoursePublishPreMapper;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CoursePublishService;
import com.xuecheng.content.service.TeachplanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final CourseBaseInfoService courseBaseInfoService;
    private final TeachplanService teachplanService;
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
}
