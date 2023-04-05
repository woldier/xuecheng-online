package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.*;
import com.xuecheng.content.model.po.*;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CourseMarketService;
import com.xuecheng.content.service.CourseTeacherService;
import com.xuecheng.content.service.TeachplanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程service
 * @date 2023/2/15 22:05
 **/
@Service
@RequiredArgsConstructor
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    /*spring 注入*/
    private final CourseBaseMapper courseBaseMapper;
    /*spring 注入*/
    private final CourseMarketMapper courseMarketMapper;

    private final CourseCategoryMapper courseCategoryMapper;

    private final CourseMarketService courseMarketService;


    private final CourseTeacherService courseTeacherService;

    private final TeachplanService teachplanService;

    /**
     * @param pageParams           分页参数
     * @param queryCourseParamsDto 查询参数
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
     * @description 课程查询接口的实现
     * @author: woldier
     * @date: 2023/2/15 22:15
     */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(Long companyId,PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        LambdaQueryWrapper<CourseBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*
         *添加查询条件
         *对于课程名采用模糊查询 ,其他的采用精确查询
         * */
        lambdaQueryWrapper.like(!StringUtils.isEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName())
                .eq(CourseBase::getCompanyId,companyId)
                .eq(!StringUtils.isEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus())
                .eq(!StringUtils.isEmpty(queryCourseParamsDto.getPublishStatus()), CourseBase::getStatus, queryCourseParamsDto.getPublishStatus());

        /*初始化分页器*/
        IPage<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        /*分页查询*/
        IPage<CourseBase> selectPage = courseBaseMapper.selectPage(page, lambdaQueryWrapper);
        /*获取数据列表*/
        List<CourseBase> records = selectPage.getRecords();
        /*获取数据总数*/
        long total = selectPage.getTotal();
        /*构造返回集*/
        return new PageResult<>(records, total, pageParams.getPageNo(), pageParams.getPageSize());
    }

    /**
     * @param companyId 公司id
     * @param dto       课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto 返回课程基本信息及营销信息
     * @description 新增课程
     * @author: woldier
     * @date: 2023/2/22 11:14
     */
    @Override
    @Transactional
    public CourseBaseInfoDto addCourse(Long companyId, AddCourseDto dto) throws XueChengPlusException {

        /*1.参数合法性校验*/
//合法性校验
        if (StringUtils.isEmpty(dto.getName())) {
            //throw new RuntimeException("课程名称为空");
            XueChengPlusException.cast("课程名称为空");
        }

        if (StringUtils.isEmpty(dto.getMt())) {
            //throw new RuntimeException("课程分类为空");
            XueChengPlusException.cast("课程分类为空");
        }

        if (StringUtils.isEmpty(dto.getSt())) {
            //throw new RuntimeException("课程分类为空");
            XueChengPlusException.cast("课程分类为空");
        }

        if (StringUtils.isEmpty(dto.getGrade())) {
            //throw new RuntimeException("课程等级为空");
            XueChengPlusException.cast("课程等级为空");
        }

        if (StringUtils.isEmpty(dto.getTeachmode())) {
            //throw new RuntimeException("教育模式为空");
            XueChengPlusException.cast("教育模式为空");
        }

        if (StringUtils.isEmpty(dto.getUsers())) {
            //throw new RuntimeException("适应人群为空");
            XueChengPlusException.cast("适应人群为空");
        }

        if (StringUtils.isEmpty(dto.getCharge())) {
            //throw new RuntimeException("收费规则为空");
            XueChengPlusException.cast("收费规则为空");
        }
        /*2.数据封装调用mapper持久化数据*/
        CourseBase insertCourseBase = new CourseBase();
        //数据拷贝
        BeanUtils.copyProperties(dto, insertCourseBase);
        //设置审核状态
        insertCourseBase.setAuditStatus("202002");
        //设置发布状态
        insertCourseBase.setStatus("203001");
        //机构id
        insertCourseBase.setCompanyId(companyId);
        //添加时间
        insertCourseBase.setCreateDate(LocalDateTime.now());
        //插入课程基本信息表
        int insert = courseBaseMapper.insert(insertCourseBase);

        //得到插入数据的id
        Long courseBaseId = insertCourseBase.getId();

        //组装营销课程信息
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarket);
        courseMarket.setOriginalPrice(dto.getOriginalPrice().floatValue());
        courseMarket.setPrice(dto.getPrice().floatValue());
        //设置id
        courseMarket.setId(courseBaseId);
        int insert1 = courseMarketMapper.insert(courseMarket);
        String charge = dto.getCharge();
        /*查看是否收费,收费校验价格是否合法*/
        checkCharge(courseMarket.getPrice(), charge);

        /*两张表有一张插入不成功则进行事务回滚*/
        if (insert1 <= 0 || insert <= 0)
            //throw new RuntimeException("添加课程失败");
            XueChengPlusException.cast("添加课程失败");
        /*3.组装返回结果*/
        return getCourseBaseInfo(courseBaseId);
    }

    private static void checkCharge(Float price, String charge) throws XueChengPlusException {
        if ("201001".equals(charge)) //如果为收费课程,扣费规则不能为空
            if (price == null || price < 0.0)
                //throw new RuntimeException("本课程为收费课程,但是价格为空");
                XueChengPlusException.cast("本课程为收费课程,但是价格为空");
    }


    /**
     * @param courseId
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 根据id查询课程基本信息
     * @author: woldier
     * @date: 2023/3/6 20:13
     */
    @Override
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId) {
        /*查询课程*/
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null)
            return null;
        /*查询课程营销信息*/
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        /*创建返回的dto*/
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        if (courseMarket != null)
            BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        CourseCategory courseCategoryMt = courseCategoryMapper.selectById(courseBaseInfoDto.getMt());
        CourseCategory courseCategorySt = courseCategoryMapper.selectById(courseBaseInfoDto.getSt());
        courseBaseInfoDto.setMtName(courseCategoryMt.getName());
        courseBaseInfoDto.setStName(courseCategorySt.getName());
        return courseBaseInfoDto;
    }

    /**
     * @param companyId     该课程所对应的公司id
     * @param editCourseDto 修改的课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 修改课程基本信息
     * @author: woldier
     * @date: 2023/3/6 20:19
     */
    @Override
    @Transactional
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto) throws XueChengPlusException {
        /*1.查询数据库 看是否存在课程*/
        /*得到id*/
        Long id = editCourseDto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(id);
        /*2.若为空抛出异常*/
        if (courseBase == null)
            XueChengPlusException.cast("课程不存在");
        /*2.1 不为空检查companyId是否一致,不一致抛出*/
        if (!courseBase.getCompanyId().equals(companyId))
            XueChengPlusException.cast("companyId是不一致");
        /*更新courseBase信息*/
        /*拷贝*/
        BeanUtils.copyProperties(editCourseDto, courseBase);

        courseBase.setChangeDate(LocalDateTime.now());
        courseBaseMapper.updateById(courseBase);

        /*更新营销信息*/
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(editCourseDto, courseMarket);
        if (editCourseDto.getOriginalPrice() != null)
            courseMarket.setOriginalPrice(editCourseDto.getOriginalPrice().floatValue());
        if (editCourseDto.getPrice() != null)
            courseMarket.setPrice(editCourseDto.getPrice().floatValue());
        checkCharge(courseMarket.getPrice(), editCourseDto.getCharge());
        courseMarketService.saveOrUpdate(courseMarket);

        /*查询封装返回*/
        return getCourseBaseInfo(editCourseDto.getId());


    }

    /**
     * @param id
     * @return void
     * @description 根据courseId删除
     * @author: woldier
     * @date: 2023/3/8 13:24
     */
    @Override
    @Transactional
    public void deleteCourseById(Long id) throws XueChengPlusException {
        /*
        0.验证此课程id是否合法
        1.根据id删除课程教师信息
        2.根据courseId删除课程计划信息
        2.1查询课程计划信息
        2.2调用删除章目录的服务
        3.删除课程营销信息和课程基本信息
         */
        /*获取课程基本信息*/
        CourseBase courseBase = courseBaseMapper.selectById(id);
        if (courseBase == null) XueChengPlusException.cast("非法课程id");
        /*1.根据id删除课程教师信息*/
        /*根据课程id查询课程教师*/
        LambdaQueryWrapper<CourseTeacher> teacherLambda = new LambdaQueryWrapper<>();
        teacherLambda.eq(CourseTeacher::getCourseId,id);
        List<CourseTeacher> courseTeacherList = courseTeacherService.list(teacherLambda);
        if (courseTeacherList!=null&&!courseTeacherList.isEmpty())
            /*list非空说明有教师数据,通过主键remove*/
            courseTeacherList.forEach(e->courseTeacherService.removeById(e.getId()));
        /*根据courseId删除课程计划信息*/
        List<TeachplanDto> teachplanDtos = teachplanService.selectTreeNodes(id);
        if(teachplanDtos!=null&&!teachplanDtos.isEmpty())
            teachplanDtos.forEach(e->{
                /*获取二级节点*/
                List<TeachplanDto> teachPlanTreeNodes = e.getTeachPlanTreeNodes();
                /*若二级节点不空,遍历删除*/
                if (teachPlanTreeNodes!=null&&!teachPlanTreeNodes.isEmpty())
                    teachPlanTreeNodes.forEach(kid -> teachplanService.removeById(kid.getId()));
                teachplanService.removeById(e.getId());
            });
        /*删除课程营销信息和课程基本信息*/
        LambdaQueryWrapper<CourseMarket> courseMarketLambda = new LambdaQueryWrapper<>();
        courseMarketLambda.eq(CourseMarket::getId,id);
        if(courseMarketService.count(courseMarketLambda)>0)
            courseMarketService.removeById(id);
        courseBaseMapper.deleteById(id);
    }
}
