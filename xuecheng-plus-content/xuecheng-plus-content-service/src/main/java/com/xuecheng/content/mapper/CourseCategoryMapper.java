package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    /**
    * @description 根据id 查询所有子节点
    * @param id
    * @return java.util.List<com.xuecheng.content.model.dto.CourseCategoryTreeDto>
    * @author: woldier
    * @date: 2023/2/16 20:38
    */
    List<CourseCategoryTreeDto> selectTreeNodes(String id);
}
