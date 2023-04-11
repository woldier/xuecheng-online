package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.CommonError;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.config.MultipartSupportConfig;
import com.xuecheng.content.feignclient.MediaServiceClient;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CoursePublishMapper;
import com.xuecheng.content.mapper.CoursePublishPreMapper;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.enums.CourseAuditStatus;
import com.xuecheng.content.model.enums.CourseStatus;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.model.po.CoursePublishPre;
import com.xuecheng.content.service.*;
import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.messagesdk.service.MqMessageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布预览service-impl
 * @date 2023/3/16 18:44
 **/
@Service
@RequiredArgsConstructor
public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish> implements CoursePublishService {
    private final CoursePublishPreService coursePublishPreService;
    private final CourseBaseInfoService courseBaseInfoService;
    private final TeachplanService teachplanService;
    private final CourseBaseMapper courseBaseMapper;

    private final MqMessageService mqMessageService;

    private final MediaServiceClient mediaServiceClient;

    /**
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CoursePreviewDto
     * @description 获取课程预览所需要的信息
     * @author: woldier
     * @date: 2023/3/16 18:45
     */
    @Override
    public CoursePreviewDto getCoursePreviewInfo(Long courseId) throws XueChengPlusException {
        /*
         * 1.查询课程基本,营销信息
         * 2.查询课程计划信息
         */
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        if (courseBaseInfo == null) XueChengPlusException.cast("获取课程基本信息出错");
        List<TeachplanDto> teachplanDtos = teachplanService.selectTreeNodes(courseId);
        if (teachplanDtos == null) XueChengPlusException.cast("获取课程计划信息出错");
        CoursePreviewDto coursePreviewDto = new CoursePreviewDto();
        coursePreviewDto.setCourseBase(courseBaseInfo);
        coursePreviewDto.setTeachplans(teachplanDtos);
        return coursePreviewDto;
    }

    /**
     * @param courseId 课程id
     * @return void
     * @description 课程发布
     * @author: woldier
     * @date: 2023/3/26 17:25
     */
    @Override
    @Transactional()
    public void coursePublish(Long companyId, Long courseId) throws XueChengPlusException {
        /**
         * 1. 判断课程预发布表的审核状态,若不为审核通过不允许发布课程
         * 2. 在课程预发布表中status字段指的是课程审核状态,而在课程发布表中的status字段指的是课程的发布状态,因此我们需要修改status字段
         * 3. 设置课程基本信息表的课程发布状态为已发布
         * 4. 将课程发布任务写入的消息表(用于同步redis,elasticsearch,minio)
         */
        // 查询课程预发布信息
        CoursePublishPre coursePublishPre = coursePublishPreService.getById(courseId);
        //判断是否是本机构课程
        if (coursePublishPre == null)
            XueChengPlusException.cast("未查询到课程预发布信息");
        if (!coursePublishPre.getCompanyId().equals(companyId))
            XueChengPlusException.cast("只允许提交本机构的课程");
        // 判断审核状态
        if (!coursePublishPre.getStatus().equals(CourseAuditStatus.AUDIT.getCode()))
            XueChengPlusException.cast("当前课程审核状态不是审核通过");


        // 对象拷贝,设置coursePublish的status字段
        CoursePublish coursePublish = new CoursePublish();
        BeanUtils.copyProperties(coursePublishPre, coursePublish);
        coursePublish.setStatus(CourseStatus.PUBLISHED.getCode());
        //保存课程发布表
        this.saveOrUpdate(coursePublish);

        //更新课程表的状态
        CourseBase courseBase = new CourseBase();
        courseBase.setId(courseId);
        courseBase.setStatus(CourseStatus.NOT_PUBLISH.getCode());
        courseBaseMapper.updateById(courseBase);
        //删除课程预发布表
        coursePublishPreService.removeById(courseId);
        //写入事务信息到消息表同步信息
        CoursePublishService proxy = (CoursePublishService) AopContext.currentProxy();
        proxy.saveCoursePublishMessage(courseId);
    }


    /**
     * @param courseId
     * @return void
     * @description 课程发布成功写入消息表
     * @author: woldier
     * @date: 2023/3/26 20:15
     */
    @Override
    @Transactional
    public void saveCoursePublishMessage(Long courseId) throws XueChengPlusException {
        //加入消息表 约定消息表的 businessKey1 字段 存储课程id
        MqMessage mqMessage = mqMessageService.addMessage("course_publish", String.valueOf(courseId), null, null);
        if (mqMessage == null) {
            log.error("将信息添加到消息表出错");
            XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }

    }

    /**
     * description 生成课程静态化页面
     *
     * @param courseId 课程id
     * @return java.io.File
     * @author: woldier
     * @date: 2023/3/27 16:52
     */
    @Override
    public File generateCourseHtml(Long courseId) throws XueChengPlusException {
        //配置freemarker
        Configuration configuration = new Configuration(Configuration.getVersion());

        //加载模板
        //选指定模板路径,classpath下templates下
        //得到classpath路径
        String classpath = this.getClass().getResource("/").getPath();
        try {
            configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates/"));
        } catch (IOException e) {
            log.error("失败,err{}", e.getCause());
            e.printStackTrace();
            XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }
        //设置字符编码
        configuration.setDefaultEncoding("utf-8");

        //指定模板文件名称
        Template template = null;
        try {
            template = configuration.getTemplate("course_template.ftl");
        } catch (IOException e) {
            log.error("加载模板文件失败,{}", e.getCause());
            e.printStackTrace();
            XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }

        //准备数据
        CoursePreviewDto coursePreviewInfo = getCoursePreviewInfo(courseId);

        Map<String, Object> map = new HashMap<>();
        map.put("model", coursePreviewInfo);

        //静态化
        //参数1：模板，参数2：数据模型
        String content = null;
        try {
            content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException | TemplateException e) {
            log.error("模型渲染出错");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //System.out.println(content);
        //将静态化内容输出到文件中
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出流
        File tempFile = null;
        try {
            tempFile = File.createTempFile("coursehtml", ".temp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            log.error("出错了");
            e.printStackTrace();
            XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }

        return tempFile;
    }

    /**
     * description 上传课程静态化网页到minio
     *
     * @param courseId 课程id
     * @param file     本地静态化html文件
     * @return void
     * @author: woldier
     * @date: 2023/3/27 16:55
     */
    @Override
    public void uploadCourseHtml(Long courseId, File file) throws XueChengPlusException {
        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(file);
        mediaServiceClient.uploadHtml(multipartFile, courseId.toString() + ".html");
    }

    /**
     * description 根据id查询发布的课程
     *
     * @param courseId 课程id
     * @return com.xuecheng.content.model.po.CoursePublish
     * @author: woldier
     * @date: 2023/4/11 20:32
     */

    @Override
    public CoursePublish getCoursePublish(Long courseId) {
        return this.getById(courseId);
    }


}
