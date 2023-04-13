package com.xuecheng.learning.api;


import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.learning.model.dto.XcChooseCourseDto;
import com.xuecheng.learning.model.dto.XcCourseTablesDto;
import com.xuecheng.learning.service.MyCourseTablesService;
import com.xuecheng.learning.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.M
 * @version 1.0
 * @description 我的课程表接口
 * @date 2022/10/2 14:52
 */
@Api(value = "我的课程表接口", tags = "我的课程表接口")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MyCourseTablesController {
    private final MyCourseTablesService myCourseTablesService;

    /**
     * description 添加选课
     *
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcChooseCourseDto
     * @author: woldier
     * @date: 2023/4/12 10:55
     */
    @ApiOperation("添加选课")
    @PostMapping("/choosecourse/{courseId}")
    public XcChooseCourseDto addChooseCourse(@PathVariable("courseId") Long courseId) throws XueChengPlusException {
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        if (user != null && !StringUtils.isEmpty(user.getId()))
            return myCourseTablesService.addChooseCourse(user.getId(), courseId);
        else
            XueChengPlusException.cast("未登录");
        return null;
    }

    @ApiOperation("选课状态")
    @PostMapping("/choosecourse/learnstatus/{courseId}")
    public XcCourseTablesDto learnstatus(@PathVariable("courseId") Long courseId) throws XueChengPlusException {
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        if (user != null && !StringUtils.isEmpty(user.getId()))
            return myCourseTablesService.getLeanringStatus(user.getId(), courseId);
        else
            XueChengPlusException.cast("未登录");
        return null;
    }


}
