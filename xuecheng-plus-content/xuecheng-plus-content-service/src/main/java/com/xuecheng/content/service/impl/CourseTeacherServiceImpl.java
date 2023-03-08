package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程-教师关系表 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {

    /**
     * @param courseId 课程id
     * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
     * @description 根据课程id查询所有课程教师
     * @author: woldier
     * @date: 2023/3/8 9:15
     */
    @Override
    public List<CourseTeacher> listTeacherByCourseId(Long courseId) throws XueChengPlusException {
        /*
        根据courseId查询数据库
         */
        if(courseId==null||courseId<0)
            XueChengPlusException.cast("课程id不合法");
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId);

        List<CourseTeacher> list = this.list(lambdaQueryWrapper);
        if(list.isEmpty()) return null;

        return list;
    }
}
