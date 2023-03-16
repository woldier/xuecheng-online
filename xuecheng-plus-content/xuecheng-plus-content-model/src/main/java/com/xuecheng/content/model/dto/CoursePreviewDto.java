package com.xuecheng.content.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 用于课程预览的返回dto
 * @date 2023/3/16 18:40
 **/
@Data
public class CoursePreviewDto {
    //课程基本信息,课程营销信息
    CourseBaseInfoDto courseBase;


    //课程计划信息
    List<TeachplanDto> teachplans;

    //师资信息暂不添加


}
