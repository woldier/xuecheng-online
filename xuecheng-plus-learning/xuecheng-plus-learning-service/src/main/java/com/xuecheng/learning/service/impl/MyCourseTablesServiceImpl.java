package com.xuecheng.learning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.learning.feignclient.CoursePublishClient;
import com.xuecheng.learning.mapper.XcChooseCourseMapper;
import com.xuecheng.learning.mapper.XcCourseTablesMapper;
import com.xuecheng.learning.model.dto.XcChooseCourseDto;
import com.xuecheng.learning.model.dto.XcCourseTablesDto;
import com.xuecheng.learning.model.po.XcChooseCourse;
import com.xuecheng.learning.model.po.XcCourseTables;
import com.xuecheng.learning.service.MyCourseTablesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 选课学习实现类
 * @date 2023/4/11 21:21
 **/
@Service
@RequiredArgsConstructor
public class MyCourseTablesServiceImpl implements MyCourseTablesService {

    private final CoursePublishClient coursePublishClient;
    private final XcChooseCourseMapper xcChooseCourseMapper;
    private final XcCourseTablesMapper xcCourseTablesMapper;

    /**
     * description 选课学习服务
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcChooseCourseDto
     * @author: woldier
     * @date: 2023/4/11 21:20
     */
    @Override
    public XcChooseCourseDto addChooseCourse(String userId, Long courseId) throws XueChengPlusException {
        /**
         * 1.查询课程信息
         * 2.若为收费课程
         * 3.若为免费课程
         */
        CoursePublish coursepublish = coursePublishClient.getCoursepublish(courseId);
        if (coursepublish == null) throw new RuntimeException("未查询到对应课程");
        XcChooseCourse xcChooseCourse = null;
        MyCourseTablesService proxy = getProxy();
        if (coursepublish.getCharge().equals(CourseCharge.FREE.getCode()))
            //免费课程
            xcChooseCourse = proxy.addFreeCourse(userId, coursepublish);
        else
            //收费课程
            xcChooseCourse = proxy.addChargeCourse(userId, coursepublish);
        XcChooseCourseDto xcChooseCourseDto = new XcChooseCourseDto();
        BeanUtils.copyProperties(xcChooseCourse, xcChooseCourseDto);
        //获取学习资格
        XcCourseTablesDto xcCourseTablesDto = getLeanringStatus(userId, courseId);
        xcChooseCourseDto.setLearnStatus(xcCourseTablesDto.getLearnStatus());
        return xcChooseCourseDto;
    }

    @NotNull
    private MyCourseTablesService getProxy() {
        return (MyCourseTablesService) AopContext.currentProxy();
    }

    /**
     * description 添加免费课程
     *
     * @param userId        用户id
     * @param coursepublish 课程id
     * @return com.xuecheng.learning.model.po.XcChooseCourse
     * @author: woldier
     * @date: 2023/4/11 21:37
     */
    @Override
    @Transactional
    public XcChooseCourse addFreeCourse(String userId, CoursePublish coursepublish) throws XueChengPlusException {
        //查询选课记录表是否存在免费的且选课成功的订单
        LambdaQueryWrapper<XcChooseCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(XcChooseCourse::getUserId, userId)
                .eq(XcChooseCourse::getCourseId, coursepublish.getId())
                .eq(XcChooseCourse::getOrderType, CourseType.FREE.getCode())//免费课程
                .eq(XcChooseCourse::getStatus, CourseStat.SUCCESS.getCode());//选课成功
        List<XcChooseCourse> xcChooseCourses = xcChooseCourseMapper.selectList(queryWrapper);
        if (xcChooseCourses != null && xcChooseCourses.size() > 0) {
            return xcChooseCourses.get(0);
        }
        //添加选课记录
        XcChooseCourse xcChooseCourse = new XcChooseCourse();
        xcChooseCourse.setCourseId(coursepublish.getId());
        xcChooseCourse.setCourseName(coursepublish.getName());
        xcChooseCourse.setCoursePrice(0f);//免费课程价格为0
        xcChooseCourse.setUserId(userId);
        xcChooseCourse.setCompanyId(coursepublish.getCompanyId());
        xcChooseCourse.setOrderType(CourseType.FREE.getCode());//免费课程
        xcChooseCourse.setCreateDate(LocalDateTime.now());
        xcChooseCourse.setStatus(CourseStat.SUCCESS.getCode());//选课成功
        xcChooseCourse.setValidDays(365);//免费课程默认365
        xcChooseCourse.setValidtimeStart(LocalDateTime.now());
        xcChooseCourse.setValidtimeEnd(LocalDateTime.now().plusDays(365));
        xcChooseCourseMapper.insert(xcChooseCourse);
        //添加到我的课程表
        getProxy().addCourseTabls(xcChooseCourse);
        return xcChooseCourse;
    }

    /**
     * description 添加收费课程
     *
     * @param userId        用户id
     * @param coursepublish 课程id
     * @return com.xuecheng.learning.model.po.XcChooseCourse
     * @author: woldier
     * @date: 2023/4/11 21:37
     */
    @Override
    @Transactional
    public XcChooseCourse addChargeCourse(String userId, CoursePublish coursepublish) {
        //如果存在待支付交易记录直接返回
        LambdaQueryWrapper<XcChooseCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(XcChooseCourse::getUserId, userId)
                .eq(XcChooseCourse::getCourseId, coursepublish.getId())
                .eq(XcChooseCourse::getOrderType, CourseType.CHARGED.getCode())//收费订单
                .eq(XcChooseCourse::getStatus, CourseStat.NEED_CHARGE.getCode());//待支付
        List<XcChooseCourse> xcChooseCourses = xcChooseCourseMapper.selectList(queryWrapper);
        if (xcChooseCourses != null && xcChooseCourses.size() > 0) {
            return xcChooseCourses.get(0);
        }
        XcChooseCourse xcChooseCourse = new XcChooseCourse();
        xcChooseCourse.setCourseId(coursepublish.getId());
        xcChooseCourse.setCourseName(coursepublish.getName());
        xcChooseCourse.setCoursePrice(coursepublish.getPrice());
        xcChooseCourse.setUserId(userId);
        xcChooseCourse.setCompanyId(coursepublish.getCompanyId());
        xcChooseCourse.setOrderType(CourseType.CHARGED.getCode());//收费课程
        xcChooseCourse.setCreateDate(LocalDateTime.now());
        xcChooseCourse.setStatus(CourseStat.NEED_CHARGE.getCode());//待支付
        xcChooseCourse.setValidDays(coursepublish.getValidDays());
        xcChooseCourse.setValidtimeStart(LocalDateTime.now());
        xcChooseCourse.setValidtimeEnd(LocalDateTime.now().plusDays(coursepublish.getValidDays()));
        xcChooseCourseMapper.insert(xcChooseCourse);
        return xcChooseCourse;
    }


    /**
     * @param xcChooseCourse 选课记录
     * @return com.xuecheng.learning.model.po.XcCourseTables
     * @description 添加到我的课程表
     * @author Mr.M
     * @date 2022/10/3 11:24
     */
    @Transactional
    public XcCourseTables addCourseTabls(XcChooseCourse xcChooseCourse) throws XueChengPlusException {
        //选课记录完成且未过期可以添加课程到课程表
        String status = xcChooseCourse.getStatus();
        if (!CourseStat.SUCCESS.getCode().equals(status)) {
            XueChengPlusException.cast("选课未成功，无法添加到课程表");
        }
        //查询我的课程表
        XcCourseTables xcCourseTables = getXcCourseTables(xcChooseCourse.getUserId(), xcChooseCourse.getCourseId());
        if (xcCourseTables != null) {
            return xcCourseTables;
        }
        XcCourseTables xcCourseTablesNew = new XcCourseTables();
        xcCourseTablesNew.setChooseCourseId(xcChooseCourse.getId());
        xcCourseTablesNew.setUserId(xcChooseCourse.getUserId());
        xcCourseTablesNew.setCourseId(xcChooseCourse.getCourseId());
        xcCourseTablesNew.setCompanyId(xcChooseCourse.getCompanyId());
        xcCourseTablesNew.setCourseName(xcChooseCourse.getCourseName());
        xcCourseTablesNew.setCreateDate(LocalDateTime.now());
        xcCourseTablesNew.setValidtimeStart(xcChooseCourse.getValidtimeStart());
        xcCourseTablesNew.setValidtimeEnd(xcChooseCourse.getValidtimeEnd());
        xcCourseTablesNew.setCourseType(xcChooseCourse.getOrderType());
        xcCourseTablesMapper.insert(xcCourseTablesNew);
        return xcCourseTablesNew;
    }

    /**
     * description 获取学习资格
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcCourseTablesDto
     * @author: woldier
     * @date: 2023/4/12 10:38
     */
    @Override
    public XcCourseTablesDto getLeanringStatus(String userId, Long courseId) {
        //查询我的课程表
        XcCourseTables xcCourseTables = getXcCourseTables(userId, courseId);
        if (xcCourseTables == null) {
            XcCourseTablesDto xcCourseTablesDto = new XcCourseTablesDto();
            //没有选课或选课后没有支付
            xcCourseTablesDto.setLearnStatus(LearningStat.NOT_CHOSE_OR_ONT_CHARGED.getCode());
            return xcCourseTablesDto;
        }
        XcCourseTablesDto xcCourseTablesDto = new XcCourseTablesDto();
        BeanUtils.copyProperties(xcCourseTables, xcCourseTablesDto);
        //是否过期,true过期，false未过期
        boolean isExpires = xcCourseTables.getValidtimeEnd().isBefore(LocalDateTime.now());
        if (!isExpires) {
            //正常学习
            xcCourseTablesDto.setLearnStatus(LearningStat.LEARNING.getCode());
            return xcCourseTablesDto;
        } else {
            //已过期
            xcCourseTablesDto.setLearnStatus(LearningStat.EXPIRE_OR_RECHARGE.getCode());
            return xcCourseTablesDto;
        }
    }

    /**
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.po.XcCourseTables
     * @description 根据课程和用户查询我的课程表中某一门课程
     * @author Mr.M
     * @date 2022/10/2 17:07
     */
    public XcCourseTables getXcCourseTables(String userId, Long courseId) {
        return xcCourseTablesMapper.selectOne(new LambdaQueryWrapper<XcCourseTables>().eq(XcCourseTables::getUserId, userId).eq(XcCourseTables::getCourseId, courseId));
    }

    @AllArgsConstructor
    @Getter
    protected enum CourseCharge {
        FREE("201000", "免费"),
        CHARGED("201001", "收费");
        private final String code;
        private final String desc;
    }

    /**
     * [{"code":"700001","desc":"免费课程"},{"code":"700002","desc":"收费课程"}]
     */
    @AllArgsConstructor
    @Getter
    protected enum CourseType {
        FREE("700001", "免费课程"),
        CHARGED("700002", "收费课程");
        private final String code;
        private final String desc;
    }

    /**
     * [{"code":"701001","desc":"选课成功"},{"code":"701002","desc":"待支付"}]
     */
    @AllArgsConstructor
    @Getter
    protected enum CourseStat {
        SUCCESS("701001", "选课成功"),
        NEED_CHARGE("701002", "待支付");
        private final String code;
        private final String desc;
    }

    /**
     * [{"code":"702001","desc":"正常学习"},{"code":"702002","desc":"没有选课或选课后没有支付"},{"code":"702003","desc":"已过期需要申请续期或重新支付"}]
     */

    @AllArgsConstructor
    @Getter
    protected enum LearningStat {
        LEARNING("702001", "正常学习"),
        NOT_CHOSE_OR_ONT_CHARGED("702001", "没有选课或选课后没有支付"),
        EXPIRE_OR_RECHARGE("702003", "已过期需要申请续期或重新支付");
        private final String code;
        private final String desc;
    }

}
