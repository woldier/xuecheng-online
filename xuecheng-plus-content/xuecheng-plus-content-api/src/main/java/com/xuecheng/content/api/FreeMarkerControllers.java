package com.xuecheng.content.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author woldier
 * @version 1.0
 * @description 返回模板对象
 * @date 2023/3/16 16:57
 **/
@Controller
@Api("模板相关")
public class FreeMarkerControllers {

    @ApiOperation("模板测试")
    @GetMapping("/testfreemarker")

    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test"); //会根据配置的模板后缀进行拼接
        modelAndView.addObject("name","woldier"); //设置model
        return modelAndView;
    }
}
