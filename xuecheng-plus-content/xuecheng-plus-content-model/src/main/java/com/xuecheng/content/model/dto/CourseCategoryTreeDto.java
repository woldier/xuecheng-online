package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程分类树型结点dto
 * @date 2023/2/16 19:38
 **/
@Data
@ToString(callSuper = true) //执行父类的toString
public class CourseCategoryTreeDto extends CourseCategory {
    /**
     * 子节点信息
     */
    List childrenTreeNodes;
}
