package com.xuecheng.content.api;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.po.CourseBase;
//import com.xuecheng.content.model.vo.QueryCourseParamsDto;
import com.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程基础信息接口
 * @date 2023/2/14 17:29
 **/
@Api(value = "课程信息编辑接口", tags = "课程信息编辑接口")
@RestController
@RequiredArgsConstructor // lombok bean注入
public class CourseBaseInfoController {
    /*
    通过Lombok生成构造方法进行注入
     */
    private final CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody
    QueryCourseParamsDto queryCourseParams) {
        return courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);
    }

    @ApiOperation("新增课程接口")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated({ValidationGroups.Inster.class}) AddCourseDto addCourseDto) throws XueChengPlusException {
        /*1.获取用户所属公司id*/
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 22L;
        /*2.call 新增课程service*/
        return courseBaseInfoService.addCourse(companyId, addCourseDto);

    }

    /**
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 根据id查询课程信息
     * @author: woldier
     * @date: 2023/3/6 19:27
     */
    @ApiOperation("查询单个课程接口")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseInfoById(@PathVariable Long courseId) {
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }


    /**
     * @param editCourseDto
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 修改课程
     * @author: woldier
     * @date: 2023/3/6 19:44
     */
    @ApiOperation("修改课程接口")
    @PutMapping("/course")
    public CourseBaseInfoDto ModifyCourseBase(@RequestBody @Validated({ValidationGroups.Update.class}) EditCourseDto editCourseDto) throws XueChengPlusException {
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId, editCourseDto);
    }


}
