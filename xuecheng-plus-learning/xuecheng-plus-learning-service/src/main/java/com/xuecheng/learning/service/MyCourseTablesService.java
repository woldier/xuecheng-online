package com.xuecheng.learning.service;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.learning.model.dto.XcChooseCourseDto;
import com.xuecheng.learning.model.dto.XcCourseTablesDto;
import com.xuecheng.learning.model.po.XcChooseCourse;
import com.xuecheng.learning.model.po.XcCourseTables;

/**
 * @author woldier
 * @version 1.0
 * @description 选课学习
 * @date 2023/4/11 21:19
 **/
public interface MyCourseTablesService {
    /**
     * description 选课学习服务
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcChooseCourseDto
     * @author: woldier
     * @date: 2023/4/11 21:20
     */
    XcChooseCourseDto addChooseCourse(String userId, Long courseId) throws XueChengPlusException;

    /**
     * description 添加免费课程
     *
     * @param userId        用户id
     * @param coursepublish 课程信息
     * @return com.xuecheng.learning.model.po.XcChooseCourse
     * @author: woldier
     * @date: 2023/4/11 21:37
     */
    XcChooseCourse addFreeCourse(String userId, CoursePublish coursepublish) throws XueChengPlusException;

    /**
     * description 添加收费课程
     *
     * @param userId        用户id
     * @param coursepublish 课程id
     * @return com.xuecheng.learning.model.po.XcChooseCourse
     * @author: woldier
     * @date: 2023/4/11 21:37
     */
    XcChooseCourse addChargeCourse(String userId, CoursePublish coursepublish);

    /**
     * description 添加到我的课程表
     *
     * @param xcChooseCourse 选课记录
     * @return com.xuecheng.learning.model.po.XcCourseTables
     * @author: woldier
     * @date: 2023/4/11 22:03
     */
    XcCourseTables addCourseTabls(XcChooseCourse xcChooseCourse) throws XueChengPlusException;

    /**
     * description 获取学习资格
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcCourseTablesDto
     * @author: woldier
     * @date: 2023/4/12 10:38
     */
    XcCourseTablesDto getLeanringStatus(String userId, Long courseId);

    boolean saveChooseCourseStauts(String choosecourseId) throws XueChengPlusException;

}
