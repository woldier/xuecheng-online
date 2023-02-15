package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author woldier
 * @version 1.0
 * @description 课程基本信息管理业务接口
 * @date 2023/2/15 20:40
 **/
public interface CourseBaseInfoService {
   /**
   * @description 课程查询接口
   * @param pageParams  分页参数
    * @param queryCourseParamsDto   查询参数
   * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
   * @author: woldier
   * @date: 2023/2/15 22:04
   */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);
}
