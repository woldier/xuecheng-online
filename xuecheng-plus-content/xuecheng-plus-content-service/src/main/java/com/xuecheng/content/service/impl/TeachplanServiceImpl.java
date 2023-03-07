package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {
    /**
     * @param courseId
     * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
     * @description 根据课程id查询课程计划信息和课程媒体信息
     * @author: woldier
     * @date: 2023/3/7 17:50
     */
    @Override
    public List<TeachplanDto> selectTreeNodes(Long courseId) {
        return baseMapper.selectTreeNodes(courseId);
    }

    /**
     * @param dto
     * @return void
     * @description 新增或者更新课程计划  通过判断dto中是否有id主键判断是新增还是更新
     * @author: woldier
     * @date: 2023/3/7 19:39
     */
    @Override
    @Transactional
    public void saveOrUpdateTeachPlan(SaveTeachplanDto dto) {
        /*
        算法如下
        1. 检查dto中的课程计划id是否为null为空
        2. 若为空说明是新建,需要从数据库中查询当前节点应该排在第几位
        3. 若不为空说明是修改课程,那么我们直接保存数据库
         */
        Long id = dto.getId();
        Teachplan teachplan = new Teachplan();
        BeanUtils.copyProperties(dto,teachplan);
        if(id==null){
            /*新建*/
            /*数据库中查询当前节点应该排在第几位*/
            LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            /*计数与dto课程id相同且父节点相同的节点数目*/
            lambdaQueryWrapper.eq(Teachplan::getCourseId,dto.getCourseId())
                    .eq(Teachplan::getParentid,dto.getParentid());
            int count = this.count(lambdaQueryWrapper);

            /*设置排序号为总数加1*/
            //TODO 思考了下感觉这里有一个bug,就是如果我们先添加了10个节点(orderBy 从1-10),然后删除了5个现在节点总数为五,那么此时插入的这个节点orderBy应该为6 这就有一些小问题
            teachplan.setOrderby(count+1);
            this.save(teachplan);
        }else{
            /*更新*/
            this.updateById(teachplan);
        }
    }
}
