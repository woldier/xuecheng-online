package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;

import java.util.List;

/**
 * <p>
 * 课程计划 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-02-16
 */
public interface TeachplanService extends IService<Teachplan> {
    /**
     * @param courseId
     * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
     * @description 根据课程id查询课程计划信息和课程媒体信息
     * @author: woldier
     * @date: 2023/3/7 17:50
     */
    List<TeachplanDto> selectTreeNodes(Long courseId);

    /**
     * @param dto
     * @return void
     * @description 新增或者更新课程计划  通过判断dto中是否有id主键判断是新增还是更新
     * @author: woldier
     * @date: 2023/3/7 19:39
     */
    void saveOrUpdateTeachPlan(SaveTeachplanDto dto);

    /**
     * @param id 课程计划id
     * @return void
     * @description 删除课程计划
     * @author: woldier
     * @date: 2023/3/7 21:05
     */
    void deleteTeachPlan(Long id) throws XueChengPlusException;

    void delete4Grade2(Long id);

}
