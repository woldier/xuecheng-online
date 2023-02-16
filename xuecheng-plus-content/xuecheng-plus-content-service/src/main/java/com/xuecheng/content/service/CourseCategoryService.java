package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程分类service
 * @date 2023/2/16 20:56
 **/
public interface CourseCategoryService {
     /**
      * @description 递归查询出所有的课程分类
      *
      * @return java.util.List<com.xuecheng.content.model.dto.CourseCategoryTreeDto>
      * @author: woldier
      * @date: 2023/2/16 21:00
      */
    List<CourseCategoryTreeDto> queryTreeNodes();
}
