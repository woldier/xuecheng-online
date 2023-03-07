package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Mapper
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    /**
    * @description 根据课程id查询课程计划信息和课程媒体信息
    * @param courseId
    * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
    * @author: woldier
    * @date: 2023/3/7 17:50
    */
    List<TeachplanDto> selectTreeNodes(Long courseId);
}
