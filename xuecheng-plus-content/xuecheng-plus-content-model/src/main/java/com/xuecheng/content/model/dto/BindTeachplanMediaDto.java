package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* @description 课程计划与媒资信息绑定的请求参数dto
* @return
* @author: woldier
* @date: 2023/3/16 15:23
*/
@Data
@ApiModel(value="BindTeachplanMediaDto", description="教学计划-媒资绑定提交数据")
public class BindTeachplanMediaDto {

@ApiModelProperty(value = "媒资文件id", required = true)
@NotEmpty(message = "媒资文件id不能为空")
private String mediaId;

@ApiModelProperty(value = "媒资文件名称", required = true)
@NotEmpty(message = "媒资文件名称不能为空")
private String fileName;

 @ApiModelProperty(value = "课程计划标识", required = true)
 @NotNull(message = "课程计划标识不能为空")
 private Long teachplanId;


}
