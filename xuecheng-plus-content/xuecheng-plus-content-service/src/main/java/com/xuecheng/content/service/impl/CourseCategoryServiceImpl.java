package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author woldier
 * @version 1.0
 * @description 课程分类service 实现
 * @date 2023/2/16 20:57
 **/
@Service
@RequiredArgsConstructor  //lombok 注入
public class CourseCategoryServiceImpl implements CourseCategoryService {
    private final CourseCategoryMapper courseCategoryMapper;

    /**
     * @return java.util.List<com.xuecheng.content.model.dto.CourseCategoryTreeDto>
     * @description 递归查询出所有的课程分类
     * @author: woldier
     * @date: 2023/2/16 21:00
     */
    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        /*从数据库中取出所有的节点*/
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes("1");
        /*遍历节点把他们根据tree关系放到返回集中*/
        /*
        * 1. 遍历整个list,把每个element加入map中(以id为key).
        * 2. 查看elem的parentid 如果在map中,取出map中与parentid对应的元素mapElem,并且将其elem加入其childList(如果childList为空新建否则直接插入)
        * 3. 进行filter 过滤掉childList为空的
        * */
        Map<String,CourseCategoryTreeDto> elemMap = new HashMap<>();
        courseCategoryTreeDtos.stream().forEach(e -> {
            /* 把每个element加入map中(以id为key).*/
            elemMap.put(e.getId(),e);
            /*查看elem的parentid 如果在map中*/
            if(elemMap.keySet().contains(e.getParentid())){
                /*取出map中与parentid对应的元素mapElem*/
                CourseCategoryTreeDto courseCategoryTreeDto = elemMap.get(e.getParentid());
                /*并且将其elem加入其childList(如果childList为空新建否则直接插入)*/
                if(courseCategoryTreeDto.getChildrenTreeNodes()==null) courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<>());
                courseCategoryTreeDto.getChildrenTreeNodes().add(e);
            }
        });
       /*进行filter 过滤掉childList为空的,并且去除根节点*/
        List<CourseCategoryTreeDto> collect = courseCategoryTreeDtos.stream().filter(e -> e.getChildrenTreeNodes() != null&&!e.getId().equals("1")).collect(Collectors.toList());
        return collect;
    }
}
