package com.xuecheng.content.model.dto;

import com.xuecheng.base.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author woldier
 * @version 1.0
 * @description 修改课程基本信息所要使用的请求参数dto
 * @date 2023/3/6 19:33
 **/
@Data
@ApiModel(value = "EditCourseDto", description = "修改课程基本信息")
public class EditCourseDto extends AddCourseDto{
    @NotNull(groups = {ValidationGroups.Update.class},message = "修改课程时id不能为空")
    @ApiModelProperty(value = "课程名称", required = true)
    private Long id;
}
