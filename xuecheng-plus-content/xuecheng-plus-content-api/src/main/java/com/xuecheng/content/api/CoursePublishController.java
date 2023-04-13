package com.xuecheng.content.api;

import com.alibaba.fastjson.JSON;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.service.CoursePublishPreCustomService;
import com.xuecheng.content.service.CoursePublishService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程预览发布
 * @date 2023/3/16 18:16
 **/
@Controller
@RequiredArgsConstructor
public class CoursePublishController {
    private final CoursePublishService coursePublishService;
    private final CoursePublishPreCustomService coursePublishPreCustomService;

    /**
     * @param courseId 课程id
     * @return org.springframework.web.servlet.ModelAndView
     * @description 课程预览
     * @author: woldier
     * @date: 2023/3/16 18:17
     */
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId) throws XueChengPlusException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", coursePublishService.getCoursePreviewInfo(courseId));
        modelAndView.setViewName("course_template");
        return modelAndView;
    }

    /**
     * @param courseId 课程id
     * @return void
     * @description 课程提交审核
     * @author: woldier
     * @date: 2023/3/24 17:14
     */
    @ResponseBody
    @PostMapping("/courseaudit/commit/{courseId}")
    public void commitAudit(@PathVariable("courseId") Long courseId) throws XueChengPlusException {

        coursePublishPreCustomService.commitAudit(1232141425L, courseId);
    }

    /**
     * @param courseId 课程id
     * @return void
     * @description 课程发布
     * @author: woldier
     * @date: 2023/3/26 17:22
     */
    @ApiOperation("课程发布")
    @ResponseBody
    @PostMapping("/coursepublish/{courseId}")
    public void coursepublish(@PathVariable("courseId") Long courseId) throws XueChengPlusException {
        coursePublishService.coursePublish(1232141425L, courseId);
    }


    /**
     * description 微服务远程调用,根据id查询课程发布信息
     * 若查询成功返回对象,返回null表示未查询到
     *
     * @param courseId 课程id
     * @return CoursePublish
     * @author: woldier
     * @date: 2023/4/11 20:31
     */
    @ApiOperation("查询课程发布信息")
    @ResponseBody
    @GetMapping("/r/coursepublish/{courseId}")
    public CoursePublish getCoursepublish(@PathVariable("courseId") Long courseId) {
        CoursePublish coursePublish = coursePublishService.getCoursePublish(courseId);
        return coursePublish;
    }

    @ApiOperation("获取课程发布信息")
    @ResponseBody
    @GetMapping("/course/whole/{courseId}")
    public CoursePreviewDto getCoursePublish(@PathVariable("courseId") Long courseId) {
        //查询课程发布信息
        CoursePublish coursePublish = coursePublishService.getCoursePublish(courseId);

        //课程基本信息
        CourseBaseInfoDto courseBase = new CourseBaseInfoDto();
        BeanUtils.copyProperties(coursePublish, courseBase);
        //课程计划
        List<TeachplanDto> teachplans = JSON.parseArray(coursePublish.getTeachplan(), TeachplanDto.class);
        CoursePreviewDto coursePreviewInfo = new CoursePreviewDto();
        coursePreviewInfo.setCourseBase(courseBase);
        coursePreviewInfo.setTeachplans(teachplans);
        return coursePreviewInfo;
    }

}
