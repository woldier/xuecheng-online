package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程计划dto类,属于树形结构但是只有两级分类
 * @date 2023/3/7 17:24
 **/
@Data
@ToString
public class TeachplanDto extends Teachplan {
    //课程计划关联的媒资信息
    private TeachplanMedia teachplanMedia;
    //课程计划对应的子节点信息
    private List<TeachplanDto> teachPlanTreeNodes;
}
