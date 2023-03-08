package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.dto.TeacherSaveOrUpdateDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
@RequiredArgsConstructor
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {
    private final CourseBaseMapper courseBaseMapper;
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
        if (courseId == null || courseId < 0)
            XueChengPlusException.cast("课程id不合法");
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId, courseId);

        List<CourseTeacher> list = this.list(lambdaQueryWrapper);
        if (list.isEmpty()) return null;

        return list;
    }

    /**
     * @param dto 传入的参数
     * @return void 返回
     * @description 新增/更新教师信息
     * @author: woldier
     * @date: 2023/3/8 9:56
     */
    @Override
    @Transactional
    public void saveOrUpdateTeacher(TeacherSaveOrUpdateDto dto) throws XueChengPlusException {
        /*
        1. 判断dto中是否有id
        2. 有表明是修改,先查询数据看,看对应id是否存在,不存在保存,存在则更新
        3. 无表明是新增,直接update
         */
        Long id = dto.getId();
        Long courseId = dto.getCourseId();
        /*创建数据库对象*/
        CourseTeacher courseTeacher = new CourseTeacher();
        BeanUtils.copyProperties(dto, courseTeacher);
        if (id != null) {
            /*修改*/
            LambdaQueryWrapper<CourseTeacher> q1 = new LambdaQueryWrapper<>();
            q1.eq(CourseTeacher::getId, id).eq(CourseTeacher::getCourseId, courseId);
            if (!this.update(courseTeacher, q1))
                XueChengPlusException.cast("非法更新");
        } else {
            /*新增*/
            if(courseBaseMapper.selectById(courseId)==null) XueChengPlusException.cast("当前课程不存在");

            courseTeacher.setCreateDate(LocalDateTime.now());
            if (!this.save(courseTeacher)) XueChengPlusException.cast("新增失败");


        }

    }
}
