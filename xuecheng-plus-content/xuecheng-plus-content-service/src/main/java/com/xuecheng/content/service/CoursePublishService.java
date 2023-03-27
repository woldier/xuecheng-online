package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.po.CoursePublish;

import java.io.File;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布预览service
 * @date 2023/3/16 18:43
 **/
public interface CoursePublishService extends IService<CoursePublish> {


    /**
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CoursePreviewDto
     * @description 获取课程预览所需要的信息
     * @author: woldier
     * @date: 2023/3/16 18:45
     */
    CoursePreviewDto getCoursePreviewInfo(Long courseId) throws XueChengPlusException;


    /**
     * @param courseId 课程id
     * @return void
     * @description 课程发布
     * @author: woldier
     * @date: 2023/3/26 17:25
     */
    void coursePublish(Long companyId, Long courseId) throws XueChengPlusException;


    /**
     * @param courseId
     * @return void
     * @description 课程发布成功写入消息表
     * @author: woldier
     * @date: 2023/3/26 20:15
     */

    void saveCoursePublishMessage(Long courseId) throws XueChengPlusException;


    /**
     * description 生成课程静态化页面
     *
     * @param courseId 课程id
     * @return java.io.File
     * @author: woldier
     * @date: 2023/3/27 16:52
     */
    public File generateCourseHtml(Long courseId) throws XueChengPlusException;


    /**
     * description 上传课程静态化网页到minio
     *
     * @param courseId 课程id
     * @param file     本地静态化html文件
     * @return void
     * @author: woldier
     * @date: 2023/3/27 16:55
     */
    public void uploadCourseHtml(Long courseId, File file) throws XueChengPlusException;
}
