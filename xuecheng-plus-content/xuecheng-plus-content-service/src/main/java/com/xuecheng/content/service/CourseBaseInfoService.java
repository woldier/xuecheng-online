package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author woldier
 * @version 1.0
 * @description 课程基本信息管理业务接口
 * @date 2023/2/15 20:40
 **/
public interface CourseBaseInfoService {
    /**
     * @param pageParams           分页参数
     * @param queryCourseParamsDto 查询参数
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
     * @description 课程查询接口
     * @author: woldier
     * @date: 2023/2/15 22:04
     */
    PageResult<CourseBase> queryCourseBaseList(Long companyId,PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * @param companyId    公司id
     * @param addCourseDto 课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto 返回课程基本信息及营销信息
     * @description 新增课程
     * @author: woldier
     * @date: 2023/2/22 11:14
     */
    CourseBaseInfoDto addCourse(Long companyId, AddCourseDto addCourseDto) throws XueChengPlusException;


    /**
     * @param courseId
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 根据id查询课程基本信息
     * @author: woldier
     * @date: 2023/3/6 20:13
     */
    CourseBaseInfoDto getCourseBaseInfo(Long courseId);

    /**
     * @param companyId     该课程所对应的公司id
     * @param editCourseDto 修改的课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 修改课程基本信息
     * @author: woldier
     * @date: 2023/3/6 20:19
     */
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto) throws XueChengPlusException;

    /**
     * @param id
     * @return void
     * @description 根据courseId删除
     * @author: woldier
     * @date: 2023/3/8 13:24
     */
    void deleteCourseById(Long id) throws XueChengPlusException;
}
