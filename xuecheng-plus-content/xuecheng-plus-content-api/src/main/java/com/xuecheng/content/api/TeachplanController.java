package com.xuecheng.content.api;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.BindTeachplanMediaDto;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程计划相关接口
 * @date 2023/3/7 17:28
 **/
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
@RequiredArgsConstructor
public class TeachplanController {

    private final TeachplanService teachplanService;
    /**
    * @description 查询树形课程计划接口
    * @param courseId
    * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
    * @author: woldier
    * @date: 2023/3/7 19:14
    */
    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.selectTreeNodes(courseId);
    }

    /**
    * @description 新增课程计划接口
    *
    * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
    * @author: woldier
    * @date: 2023/3/7 19:13
    */
    @ApiOperation("添加课程计划信息")
    @PostMapping("/teachplan")
    public void saveOrUpdateTeachPlan(@RequestBody SaveTeachplanDto dto){
        teachplanService.saveOrUpdateTeachPlan(dto);
    }

    /**
    * @description 删除课程计划信息
    * @param id
    * @return void
    * @author: woldier
    * @date: 2023/3/7 20:59
    */
    @ApiOperation("删除课程计划信息")
    @DeleteMapping("/teachplan/{id}")
    public void deleteTeachPlan(@PathVariable Long id) throws XueChengPlusException {
        teachplanService.deleteTeachPlan(id);
    }

    /**
    * @description 课程下移
    * @param id
    * @return void
    * @author: woldier
    * @date: 2023/3/7 22:20
    */
    @ApiOperation("移动课程计划信息位置下移")
    @PostMapping("/teachplan/movedown/{id}")
    public void moveDown(@PathVariable Long id) throws XueChengPlusException {
        teachplanService.move(id,Boolean.TRUE);
    }

    /**
     * @description 课程上移
     * @param id
     * @return void
     * @author: woldier
     * @date: 2023/3/7 22:20
     */
    @ApiOperation("移动课程计划信息位置上移")
    @PostMapping("/teachplan/moveup/{id}")
    public void moveUp(@PathVariable Long id) throws XueChengPlusException {
        teachplanService.move(id,Boolean.FALSE);
    }

    @ApiOperation("课程计划绑定媒资")
    @PostMapping("/teachplan/association/media")
    public void associationMedia(@RequestBody @Validated BindTeachplanMediaDto dto) throws XueChengPlusException {
        teachplanService.association(dto);
    }
}
