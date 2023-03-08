package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.TeacherSaveOrUpdateDto;
import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-02-16
 */
public interface CourseTeacherService extends IService<CourseTeacher> {
    /**
     * @param courseId 课程id
     * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
     * @description 根据课程id查询所有课程教师
     * @author: woldier
     * @date: 2023/3/8 9:15
     */
    List<CourseTeacher> listTeacherByCourseId(Long courseId) throws XueChengPlusException;

    /**
     * @param dto
     * @return void
     * @description 新增/更新教师信息
     * @author: woldier
     * @date: 2023/3/8 9:56
     */
    void saveOrUpdateTeacher(TeacherSaveOrUpdateDto dto) throws XueChengPlusException;

    /**
     * @param courseId 课程id
     * @param id       id
     * @return void
     * @description 删除课程教师
     * @author: woldier
     * @date: 2023/3/8 10:38
     */
    void deleteTeacher(Long courseId, Long id) throws XueChengPlusException;
}
