package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
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

    /**
    * @description 课程计划上下移动
    * @param id 课程计划id
     * @param moveDown   是否是下移 Ture 代表下移 ,False 代表上移
    * @return void
    * @author: woldier
    * @date: 2023/3/7 22:29
    */
    void move(Long id,Boolean moveDown) throws XueChengPlusException;

    /**
    * @description 媒资与课程资源进行绑定
    * @param dto
    * @return void
    * @author: woldier
    * @date: 2023/3/16 15:23
    */
    void association(BindTeachplanMediaDto dto) throws XueChengPlusException;

    /**
    * @description 删除课程计划与媒资的绑定信息
    * @param teachPlanId 课程计划id
     * @param mediaId  媒资信息id
    * @return void
    * @author: woldier
    * @date: 2023/3/16 16:00
    */
    void deleteAssociation(Long teachPlanId, Long mediaId) throws XueChengPlusException;

}
