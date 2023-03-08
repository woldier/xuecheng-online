package com.xuecheng.content.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author woldier
 * @version 1.0
 * @description 课程教师信息请求参数dto
 * @date 2023/3/8 9:46
 **/
@Data
public class TeacherSaveOrUpdateDto {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 课程标识
     */
    @NotNull(message = "新增或者修改时课程id不能为空")
    private Long courseId;

    /**
     * 教师标识
     */
    @NotNull(message = "新增或者修改时课程教师名不能为空")
    private String teacherName;

    /**
     * 教师职位
     */
    private String position;

    /**
     * 教师简介
     */
    private String introduction;

    /**
     * 照片
     */
    private String photograph;

}
