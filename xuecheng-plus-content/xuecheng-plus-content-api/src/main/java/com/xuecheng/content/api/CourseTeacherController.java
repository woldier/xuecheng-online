package com.xuecheng.content.api;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程教师相关接口
 * @date 2023/3/8 8:52
 **/
@RestController
@RequestMapping("/courseTeacher")
@RequiredArgsConstructor
public class CourseTeacherController {

    private final CourseTeacherService courseTeacherService;

    /**
    * @description 根据课程id查询教师
    * @param courseId  课程id
    * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
    * @author: woldier
    * @date: 2023/3/8 8:55
    */
    @ApiOperation("根据课程id查询课程教师信息")
    @GetMapping("/list/{courseId}")
    public List<CourseTeacher> list(@PathVariable @NotNull(message = "课程id不能为空") Long courseId) throws XueChengPlusException {
        return courseTeacherService.listTeacherByCourseId(courseId);
    }
}
