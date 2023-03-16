package com.xuecheng.content.service;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.CoursePreviewDto;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布预览service
 * @date 2023/3/16 18:43
 **/
public interface CoursePublishService {


    /**
    * @description 获取课程预览所需要的信息
    * @param courseId  课程id
    * @return com.xuecheng.content.model.dto.CoursePreviewDto
    * @author: woldier
    * @date: 2023/3/16 18:45
    */
    public CoursePreviewDto getCoursePreviewInfo(Long courseId) throws XueChengPlusException;
}
