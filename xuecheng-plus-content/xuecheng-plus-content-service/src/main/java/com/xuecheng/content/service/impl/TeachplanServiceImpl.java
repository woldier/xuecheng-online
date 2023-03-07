package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachplanMediaService;
import com.xuecheng.content.service.TeachplanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {
    private final TeachplanMediaService teachplanMediaService;

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
        BeanUtils.copyProperties(dto, teachplan);
        if (id == null) {
            /*新建*/
            /*数据库中查询当前节点应该排在第几位*/
            LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            /*计数与dto课程id相同且父节点相同的节点数目*/
            lambdaQueryWrapper.eq(Teachplan::getCourseId, dto.getCourseId())
                    .eq(Teachplan::getParentid, dto.getParentid());
            int count = this.count(lambdaQueryWrapper);

            /*设置排序号为总数加1*/
            //TODO 思考了下感觉这里有一个bug,就是如果我们先添加了10个节点(orderBy 从1-10),然后删除了5个现在节点总数为五,那么此时插入的这个节点orderBy应该为6 这就有一些小问题
            teachplan.setOrderby(count + 1);
            this.save(teachplan);
        } else {
            /*更新*/
            this.updateById(teachplan);
        }
    }

    /**
     * @param id 课程计划id
     * @return void
     * @description 删除课程计划
     * @author: woldier
     * @date: 2023/3/7 21:05
     */
    @Override
    @Transactional
    public void deleteTeachPlan(Long id) throws XueChengPlusException {
        /*
         * 1.查询数据库中课程计划
         * 查看是否存在不存在抛出异常
         * 2.获取课程计划的等级
         * 若为章则递归删除其子节点
         * 若为节,直接删除,查询是否有媒体信息,有则删除
         *
         * */
        Teachplan teachplan = this.getById(id);
        if (teachplan == null)
            XueChengPlusException.cast("课程不存在");
        Integer grade = teachplan.getGrade();

        if (grade.equals(1)) {
            /*为章递归删除其子节点*/
            List<TeachplanDto> teachplanDtos = this.selectTreeNodes(teachplan.getCourseId());
            /*取出二级节点*/
            List<TeachplanDto> teachPlanTreeNodes = teachplanDtos.stream().filter(e -> e.getId().equals(teachplan.getId())).collect(Collectors.toList()).get(0).getTeachPlanTreeNodes();
            /*删除章*/
            this.removeById(id);
            /*删除节*/
            teachPlanTreeNodes.forEach(e->this.delete4Grade2(e.getId()));
        } else
            //delete4Grade2(id);
            /*事务传递*/
            this.delete4Grade2(id);

    }

    /**
     * @param id 二级课程id
     * @return
     * @description 根据二级课程的id 删除 仅仅二级使用
     * @author: woldier
     * @date: 2023/3/7 21:17
     */
    @Override
    @Transactional
    public void delete4Grade2(Long id) {
        this.removeById(id);
        LambdaQueryWrapper<TeachplanMedia> lb4Media = new LambdaQueryWrapper<>();
        lb4Media.eq(TeachplanMedia::getTeachplanId, id);
        /*若根据课程计划id查询到有数据,说明绑定了视频,进行删除*/
        if (teachplanMediaService.count(lb4Media) > 0)
            teachplanMediaService.remove(lb4Media);

    }


}
