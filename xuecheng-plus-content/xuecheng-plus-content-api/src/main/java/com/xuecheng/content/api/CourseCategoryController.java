package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * @author woldier
 * @version 1.0
 * @description 课程分类controller
 * @date 2023/2/16 19:41
 **/
@Api(value = "课程分类相关接口", tags = "课程分类相关接口")
@RestController
@RequiredArgsConstructor
public class CourseCategoryController {
    private final CourseCategoryService courseCategoryService;
    /**
    * @description 课程分类查询接口
    *
    * @return java.util.List<com.xuecheng.content.model.dto.CourseCategoryTreeDto>
    * @author: woldier
    * @date: 2023/2/16 19:48
    */
    @ApiOperation("课程分类查询接口")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
        return courseCategoryService.queryTreeNodes();
    }
}
