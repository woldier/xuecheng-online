package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程分类树型结点dto
 * @date 2023/2/16 19:38
 **/
public class CourseCategoryTreeDto extends CourseCategory {
    /**
     * 子节点信息
     */
    List childrenTreeNodes;
}
