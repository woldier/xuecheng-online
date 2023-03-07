package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
     * @description 根据课程id查询课程计划信息和课程媒体信息
     * @param courseId
     * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
     * @author: woldier
     * @date: 2023/3/7 17:50
     */
    List<TeachplanDto> selectTreeNodes(Long courseId);
}
