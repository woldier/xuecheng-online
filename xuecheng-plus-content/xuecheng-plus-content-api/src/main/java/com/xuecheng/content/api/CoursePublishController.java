package com.xuecheng.content.api;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.service.CoursePublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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
    /**
    * @description 课程预览
    * @param courseId  课程id
    * @return org.springframework.web.servlet.ModelAndView
    * @author: woldier
    * @date: 2023/3/16 18:17
    */
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId) throws XueChengPlusException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model",coursePublishService.getCoursePreviewInfo(courseId));
        modelAndView.setViewName("course_template");
        return modelAndView;
    }

}
