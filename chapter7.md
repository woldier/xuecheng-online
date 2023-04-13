> 选课学习

# 1.模块需求分析

## 1.1 模块介绍

本模块实现了学生选课、下单支付、学习的整体流程。

网站的课程有免费和收费两种，对于免费课程学生选课后可直接学习，对于收费课程学生需要下单且支付成功方可选课、学习。

选课：是将课程加入我的课程表的过程。

我的课程表：记录我在网站学习的课程，我的课程表中有免费课程和收费课程两种，对于免费课程可直接添加到我的课程表，对于收费课程需要下单、支付成功后自动加入我的课程表。

模块整体流程如下：

![image-20230411193338679](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/aa55e446d354409ab93b46c660e8748c.png)

## 1.2 业务流程



### 1.2.1 学习引导

用户通过搜索课程、课程推荐等信息进入课程详情页面，点击“马上学习” 引导进入学习界面去学习。

流程如下：

![image-20230411193503898](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/173c3dab5242939dd5faed050c79f860.png)

1、进入课程详情点击马上学习

![image-20230411193619076](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/0bde907a7741d17fd22588819b5e6cf2.png)

2、课程免费时引导加入我的课程表、或进入学习界面。

![image-20230411193635484](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/80527789dee0a112d6ece462aaee8324.png)

3、课程收费时引导去支付、或试学。

![image-20230411193653877](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/62164cb0a693e2434b2da0d0f6d58383.png)



### 1.2.2 选课流程

选课是将课程加入我的课程表的过程。

对免费课程选课后可直接加入我的课程表，对收费课程选课后需要下单支付成功系统自动加入我的课程表。

流程如下：

![image-20230411193717606](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/7b455d52a6b4f564f58a7efd2da73525.png)





### 1.2.3 支付流程

本项目与第三方支付平台对接完成支付操作。

流程如下：

![image-20230411193939508](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/b1e108836d6a7d833f86fc90d66801b0.png)



### 1.2.4 在线学习

选课成功用户使可以在线学习，对于免费课程无需选课即可在线学习。

流程如下：

![image-20230411194050115](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/ad4488087155c1230e33372dc4422cc6.png)



### 1.2.5 免费课程续期

免费课程加入我的课程表默认为1年有效期，到期用户可申请续期，流程如下：

![image-20230411194102033](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/0bc31adb356ed5b0300a9bdb2b1bcc49.png)



# 2. 添加选课

## 2.1 需求分析

### 2.1.1 数据模型

选课是将课程加入我的课程表的过程，根据选课的业务流程进行详细分析，业务流程如下：

![image-20230411194503494](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/7b455d52a6b4f564f58a7efd2da73525.png)

选课信息存入选课记录表，免费课程被选课除了进入选课记录表同时进入我的课程表，收费课程进入选课记录表后需要经过下单、支付成功才可以进入我的课程表。

我的课程表记录了用户学习的课程，包括免费课程、收费课程（已经支付）。

1、选课记录表

当用户将课程添加到课程表时需要先创建选课记录。

结构如下：

![image-20230411200009834](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/1b5dd270bd5dea7dfa6089f2e20eede9.png)

选课类型：免费课程、收费课程。

选课状态：选课成功、待支付、选课删除。

对于免费课程：课程价格为0，有效期默认365，开始服务时间为选课时间，结束服务时间为选课时间加1年后的时间，选课状态为选课成功。

对于收费课程：按课程的现价、有效期确定开始服务时间、结束服务时间，选课状态为待支付。

收费课程的选课记录需要支付成功后选课状态为成功。



2、我的课程表

我的课程表中记录了用户选课成功的课程，所以我的课程表的数据来源于选课记录表。 

对于免费课程创建选课记录后同时向我的课程表添加记录。

对于收费课程创建选课记录后需要下单支付成功后自动向我的课程表添加记录。

![image-20230411200106499](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/3134eab4b9eb2d67bef5214e31f3975d.png)



### 2.1.2 执行流程

在学习引导处，可以直接将免费课程加入我的课程表，如下图：

![image-20230411200158490](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/80527789dee0a112d6ece462aaee8324.png)

对于收费课程先创建选课记录表，支付成功后，收到支付结果由系统自动加入我的课程表。

 

这里首先实现将免费课程加入我的课程表，执行流程如下：

![image-20230411200224852](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/eb38b08a5ec0263048d4045798fe395b.png)





## 2.2 接口开发

###  2.2.1 创建学习中心工程

从课程资料拷贝学习中心服务工程到自己的工程目录，结构如下：

![image-20230411200302429](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/bd595d00691d786de0e5ed376fc8c466.png)

注意去修改nacos的命名空间。

修改数据库的连接，改成自己的数据库。

nacos配置文件：learning-api-dev.yaml

```yaml
server:
  servlet:
    context-path: /learning
  port: 63020

```

learning-service-dev.yaml

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.101.65:3306/xc1010_learning?serverTimezone=UTC&userUnicode=true&useSSL=false&
    username: root
    password: mysql

```



### 2.2.2 添加查询课程接口

内容管理服务提供查询课程信息接口，此接口从课程发布表查询。

此接口主要提供其它微服务远程调用，所以此接口不用授权，本项目标记此类接口统一以 /r开头。

在课程发布controller类中定义课程发布信息查询接口。

```java
@ApiOperation("查询课程发布信息")
@ResponseBody
@GetMapping("/r/coursepublish/{courseId}")
public CoursePublish getCoursepublish(@PathVariable("courseId") Long courseId) {
    CoursePublish coursePublish = coursePublishService.getCoursePublish(courseId);
     return coursePublish;
}

```

Service如下：

如果课程发布状态正常则正常返回，否则返回空。

```java
public CoursePublish getCoursePublish(Long courseId){
    CoursePublish coursePublish = coursePublishMapper.selectById(courseId);
    return coursePublish ;
}

```

测试：

启动内容管理服务，使用httpclient测试

```http
### 查询课程发布信息
GET {{content_host}}/content/r/coursepublish/2

```







### 2.2.3 远程调用查询课程信息接口

学生中心服务远程调用内容管理服务的查询课程发布信息接口。

在学生中心service工程添加Feign接口：

```java
package com.xuecheng.learning.feignclient;

import com.xuecheng.content.model.po.CoursePublish;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author woldier
 * @version 1.0
 * @description 发布课程查询远程调用客户端
 * @date 2023/4/11 20:45
 **/
@FeignClient(name = "content-api", fallbackFactory = CoursePublishClientFactory.class)
@RequestMapping("/content/")
public interface CoursePublishClient {
    @ResponseBody
    @GetMapping("/r/coursepublish/{courseId}")
    CoursePublish getCoursepublish(@PathVariable("courseId") Long courseId) ;
}

```

降级工厂

```java
package com.xuecheng.learning.feignclient;

import com.xuecheng.content.model.po.CoursePublish;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author woldier
 * @version 1.0
 * @description CoursePublishClientFactory 回调工厂
 * @date 2023/4/11 20:50
 **/
@Component
public class CoursePublishClientFactory implements FallbackFactory<CoursePublishClient> {
    @Override
    public CoursePublishClient create(Throwable cause) {
        return new CoursePublishClient() {
            @Override
            public CoursePublish getCoursepublish(Long courseId) {
                return null;
            }
        };
    }
}

```

实体类

```java
package com.xuecheng.learning.feignclient;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程发布
 * </p>
 *
 * @author itcast
 */
@Data
public class CoursePublish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 机构ID
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 适用人群
     */
    private String users;

    /**
     * 标签
     */
    private String tags;

    /**
     * 创建人
     */
    private String username;

    /**
     * 大分类
     */
    private String mt;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类
     */
    private String st;

    /**
     * 小分类名称
     */
    private String stName;

    /**
     * 课程等级
     */
    private String grade;

    /**
     * 教育模式
     */
    private String teachmode;

    /**
     * 课程图片
     */
    private String pic;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 课程营销信息，json格式
     */
    private String market;

    /**
     * 所有课程计划，json格式
     */
    private String teachplan;

    /**
     * 教师信息，json格式
     */
    private String teachers;

    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 上架时间
     */
    private LocalDateTime onlineDate;

    /**
     * 下架时间
     */
    private LocalDateTime offlineDate;

    /**
     * 发布状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 收费规则，对应数据字典--203
     */
    private String charge;

    /**
     * 现价
     */
    private Float price;

    /**
     * 原价
     */
    private Float originalPrice;

    /**
     * 课程有效期天数
     */
    private Integer validDays;


}

```

测试

```java
package com.xuecheng.pubcourse.api;

import com.xuecheng.learning.feignclient.CoursePublishClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布测试
 * @date 2023/4/11 21:07
 **/

@SpringBootTest
public class CoursePubTest {
    @Autowired
    CoursePublishClient coursePublishClient;

    @Test
    public void test1(){
        System.out.println(coursePublishClient.getCoursepublish(2L));
    }
}

```



### 2.2.4 添加选课接口

####  2.2.4.1 接口分析

本接口支持免费课程选课、收费课程选课。

免费课程选课：添加选课记录、添加我的课程表。

收费课程选课：添加选课记录。

#### 2.2.4.2 接口定义

1、请求参数：课程id、当前用户id

2、响应结果：选课记录信息、学习资格

学习资格：[{"code":"702001","desc":"正常学习"},{"code":"702002","desc":"没有选课或选课后没有支付"},{"code":"702003","desc":"已过期需要申请续期或重新支付"}]

接口定义如下：

```java
package com.xuecheng.learning.api;


import com.xuecheng.learning.model.dto.XcChooseCourseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.M
 * @version 1.0
 * @description 我的课程表接口
 * @date 2022/10/2 14:52
 */
@Api(value = "我的课程表接口", tags = "我的课程表接口")
@Slf4j
@RestController
public class MyCourseTablesController {


    @ApiOperation("添加选课")
    @PostMapping("/choosecourse/{courseId}")
    public XcChooseCourseDto addChooseCourse(@PathVariable("courseId") Long courseId) {
        return null;
    }

}

```

service接口定义:

```java
package com.xuecheng.learning.service.impl;

import com.xuecheng.learning.model.dto.XcChooseCourseDto;
import com.xuecheng.learning.service.MyCourseTablesService;

/**
 * @author woldier
 * @version 1.0
 * @description 选课学习实现类
 * @date 2023/4/11 21:21
 **/
public class MyCourseTablesServiceImpl implements MyCourseTablesService {
    /**
     *
     * description 选课学习服务
     *
     * @param userId 用户id
     * @param courseId  课程id
     * @return com.xuecheng.learning.model.dto.XcChooseCourseDto
     * @author: woldier
     * @date: 2023/4/11 21:20
     */
    @Override
    public XcChooseCourseDto addChooseCourse(String userId, Long courseId) {
        return null;
    }
}

```

接口实现:
```java
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.learning.feignclient.CoursePublishClient;
import com.xuecheng.learning.mapper.XcChooseCourseMapper;
import com.xuecheng.learning.model.dto.XcChooseCourseDto;
import com.xuecheng.learning.model.po.XcChooseCourse;
import com.xuecheng.learning.service.MyCourseTablesService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 选课学习实现类
 * @date 2023/4/11 21:21
 **/
@Service
@RequiredArgsConstructor
public class MyCourseTablesServiceImpl implements MyCourseTablesService {

    private final CoursePublishClient coursePublishClient;
    private final XcChooseCourseMapper xcChooseCourseMapper;

    /**
     * description 选课学习服务
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcChooseCourseDto
     * @author: woldier
     * @date: 2023/4/11 21:20
     */
    @Override
    public XcChooseCourseDto addChooseCourse(String userId, Long courseId) {
        /**
         * 1.查询课程信息
         * 2.若为收费课程
         * 3.若为免费课程
         */
        CoursePublish coursepublish = coursePublishClient.getCoursepublish(courseId);

        if (coursepublish == null) throw new RuntimeException("未查询到对应课程");
        MyCourseTablesService proxy = (MyCourseTablesService) AopContext.currentProxy();
        if (coursepublish.getCharge().equals(CourseCharge.FREE.getCode())) {
            //免费课程
            proxy.addFreeCourse(userId, coursepublish);
        } else {
            //收费课程
            proxy.addChargeCourse(userId, coursepublish);
        }

        return null;
    }
}
```



#### 2.2.4.3 添加免费课程

```java
 /**
     * description 添加免费课程
     *
     * @param userId        用户id
     * @param coursepublish 课程id
     * @return com.xuecheng.learning.model.po.XcChooseCourse
     * @author: woldier
     * @date: 2023/4/11 21:37
     */
    @Override
    @Transactional
    public XcChooseCourse addFreeCourse(String userId, CoursePublish coursepublish) {
        //查询选课记录表是否存在免费的且选课成功的订单
        LambdaQueryWrapper<XcChooseCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(XcChooseCourse::getUserId, userId)
                .eq(XcChooseCourse::getCourseId, coursepublish.getId())
                .eq(XcChooseCourse::getOrderType, CourseType.FREE.getCode())//免费课程
                .eq(XcChooseCourse::getStatus, CourseStat.FREE.getCode());//选课成功
        List<XcChooseCourse> xcChooseCourses = xcChooseCourseMapper.selectList(queryWrapper);
        if (xcChooseCourses != null && xcChooseCourses.size() > 0) {
            return xcChooseCourses.get(0);
        }
        //添加选课记录
        XcChooseCourse xcChooseCourse = new XcChooseCourse();
        xcChooseCourse.setCourseId(coursepublish.getId());
        xcChooseCourse.setCourseName(coursepublish.getName());
        xcChooseCourse.setCoursePrice(0f);//免费课程价格为0
        xcChooseCourse.setUserId(userId);
        xcChooseCourse.setCompanyId(coursepublish.getCompanyId());
        xcChooseCourse.setOrderType(CourseType.FREE.getCode());//免费课程
        xcChooseCourse.setCreateDate(LocalDateTime.now());
        xcChooseCourse.setStatus(CourseStat.FREE.getCode());//选课成功
        xcChooseCourse.setValidDays(365);//免费课程默认365
        xcChooseCourse.setValidtimeStart(LocalDateTime.now());
        xcChooseCourse.setValidtimeEnd(LocalDateTime.now().plusDays(365));
        xcChooseCourseMapper.insert(xcChooseCourse);
        //添加到我的课程表
        return xcChooseCourse;
    }
```

#### 2.2.4.4 添加到我的课程表

我的课程表的记录来源于选课记录，选课记录成功将课程信息添加到我的课程表。

如果我的课程表已存在课程可能已经过期，如果有新的选课记录则需要更新我的课程表中的现有信息。

```java
/**
     * @param xcChooseCourse 选课记录
     * @return com.xuecheng.learning.model.po.XcCourseTables
     * @description 添加到我的课程表
     * @author Mr.M
     * @date 2022/10/3 11:24
     */
    @Transactional
    public XcCourseTables addCourseTabls(XcChooseCourse xcChooseCourse) throws XueChengPlusException {
        //选课记录完成且未过期可以添加课程到课程表
        String status = xcChooseCourse.getStatus();
        if (!"701001".equals(status)) {
            XueChengPlusException.cast("选课未成功，无法添加到课程表");
        }
        //查询我的课程表
        XcCourseTables xcCourseTables = getXcCourseTables(xcChooseCourse.getUserId(), xcChooseCourse.getCourseId());
        if (xcCourseTables != null) {
            return xcCourseTables;
        }
        XcCourseTables xcCourseTablesNew = new XcCourseTables();
        xcCourseTablesNew.setChooseCourseId(xcChooseCourse.getId());
        xcCourseTablesNew.setUserId(xcChooseCourse.getUserId());
        xcCourseTablesNew.setCourseId(xcChooseCourse.getCourseId());
        xcCourseTablesNew.setCompanyId(xcChooseCourse.getCompanyId());
        xcCourseTablesNew.setCourseName(xcChooseCourse.getCourseName());
        xcCourseTablesNew.setCreateDate(LocalDateTime.now());
        xcCourseTablesNew.setValidtimeStart(xcChooseCourse.getValidtimeStart());
        xcCourseTablesNew.setValidtimeEnd(xcChooseCourse.getValidtimeEnd());
        xcCourseTablesNew.setCourseType(xcChooseCourse.getOrderType());
        xcCourseTablesMapper.insert(xcCourseTablesNew);

        return xcCourseTablesNew;

    }

    /**
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.po.XcCourseTables
     * @description 根据课程和用户查询我的课程表中某一门课程
     * @author Mr.M
     * @date 2022/10/2 17:07
     */
    public XcCourseTables getXcCourseTables(String userId, Long courseId) {
        return xcCourseTablesMapper.selectOne(new LambdaQueryWrapper<XcCourseTables>().eq(XcCourseTables::getUserId, userId).eq(XcCourseTables::getCourseId, courseId));
    }
```



#### 2.2.4.5 添加收费课程

```java
/**
     * description 添加收费课程
     *
     * @param userId        用户id
     * @param coursepublish 课程id
     * @return com.xuecheng.learning.model.po.XcChooseCourse
     * @author: woldier
     * @date: 2023/4/11 21:37
     */
    @Override
    @Transactional
    public XcChooseCourse addChargeCourse(String userId, CoursePublish coursepublish) {
        //如果存在待支付交易记录直接返回
        LambdaQueryWrapper<XcChooseCourse> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper = queryWrapper.eq(XcChooseCourse::getUserId, userId)
                .eq(XcChooseCourse::getCourseId, coursepublish.getId())
                .eq(XcChooseCourse::getOrderType, CourseType.CHARGED.getCode())//收费订单
                .eq(XcChooseCourse::getStatus, CourseStat.NEED_CHARGE.getCode());//待支付
        List<XcChooseCourse> xcChooseCourses = xcChooseCourseMapper.selectList(queryWrapper);
        if (xcChooseCourses != null && xcChooseCourses.size() > 0) {
            return xcChooseCourses.get(0);
        }
        XcChooseCourse xcChooseCourse = new XcChooseCourse();
        xcChooseCourse.setCourseId(coursepublish.getId());
        xcChooseCourse.setCourseName(coursepublish.getName());
        xcChooseCourse.setCoursePrice(coursepublish.getPrice());
        xcChooseCourse.setUserId(userId);
        xcChooseCourse.setCompanyId(coursepublish.getCompanyId());
        xcChooseCourse.setOrderType(CourseType.CHARGED.getCode());//收费课程
        xcChooseCourse.setCreateDate(LocalDateTime.now());
        xcChooseCourse.setStatus(CourseStat.NEED_CHARGE.getCode());//待支付
        xcChooseCourse.setValidDays(coursepublish.getValidDays());
        xcChooseCourse.setValidtimeStart(LocalDateTime.now());
        xcChooseCourse.setValidtimeEnd(LocalDateTime.now().plusDays(coursepublish.getValidDays()));
        xcChooseCourseMapper.insert(xcChooseCourse);
        return xcChooseCourse;
    }
```



#### 2.2.4.6 获取学习资格

定义获取学习资格接口

```java
 /**
     * description 获取学习资格
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcCourseTablesDto
     * @author: woldier
     * @date: 2023/4/12 10:38
     */
    XcCourseTablesDto getLeanringStatus(String userId, Long courseId);
```

```java
    /**
     *
     * description 获取学习资格
     *
     * @param userId 用户id
     * @param courseId  课程id
     * @return com.xuecheng.learning.model.dto.XcCourseTablesDto
     * @author: woldier
     * @date: 2023/4/12 10:38
     */
    @Override
    public XcCourseTablesDto getLeanringStatus(String userId, Long courseId) {
        //查询我的课程表
        XcCourseTables xcCourseTables = getXcCourseTables(userId, courseId);
        if(xcCourseTables==null){
            XcCourseTablesDto xcCourseTablesDto = new XcCourseTablesDto();
            //没有选课或选课后没有支付
            xcCourseTablesDto.setLearnStatus(LearningStat.NOT_CHOSE_OR_ONT_CHARGED.getCode());
            return xcCourseTablesDto;
        }
        XcCourseTablesDto xcCourseTablesDto = new XcCourseTablesDto();
        BeanUtils.copyProperties(xcCourseTables,xcCourseTablesDto);
        //是否过期,true过期，false未过期
        boolean isExpires = xcCourseTables.getValidtimeEnd().isBefore(LocalDateTime.now());
        if(!isExpires){
            //正常学习
            xcCourseTablesDto.setLearnStatus(LearningStat.LEARNING.getCode());
            return xcCourseTablesDto;

        }else{
            //已过期
            xcCourseTablesDto.setLearnStatus(LearningStat.EXPIRE_OR_RECHARGE.getCode());
            return xcCourseTablesDto;
        }

    }
```



#### 2.2.4.7 接口完善

1. 完善service

```java
/**
     * description 选课学习服务
     *
     * @param userId   用户id
     * @param courseId 课程id
     * @return com.xuecheng.learning.model.dto.XcChooseCourseDto
     * @author: woldier
     * @date: 2023/4/11 21:20
     */
    @Override
    public XcChooseCourseDto addChooseCourse(String userId, Long courseId) throws XueChengPlusException {
        /**
         * 1.查询课程信息
         * 2.若为收费课程
         * 3.若为免费课程
         */
        CoursePublish coursepublish = coursePublishClient.getCoursepublish(courseId);
        if (coursepublish == null) throw new RuntimeException("未查询到对应课程");
        XcChooseCourse xcChooseCourse = null;
        MyCourseTablesService proxy = getProxy();
        if (coursepublish.getCharge().equals(CourseCharge.FREE.getCode()))
            //免费课程
            xcChooseCourse = proxy.addFreeCourse(userId, coursepublish);
        else
            //收费课程
            xcChooseCourse = proxy.addChargeCourse(userId, coursepublish);
        XcChooseCourseDto xcChooseCourseDto = new XcChooseCourseDto();
        BeanUtils.copyProperties(xcChooseCourse, xcChooseCourseDto);
        //获取学习资格
        XcCourseTablesDto xcCourseTablesDto = getLeanringStatus(userId, courseId);
        xcChooseCourseDto.setLearnStatus(xcCourseTablesDto.getLearnStatus());
        return xcChooseCourseDto;
    }
```

2. 完善controller

```java
    /**
    *
    * description 添加选课
    *        
    * @param courseId  课程id
    * @return com.xuecheng.learning.model.dto.XcChooseCourseDto 
    * @author: woldier 
    * @date: 2023/4/12 10:55
    */
    @ApiOperation("添加选课")
    @PostMapping("/choosecourse/{courseId}")
    public XcChooseCourseDto addChooseCourse(@PathVariable("courseId") Long courseId) throws XueChengPlusException {
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        if (user != null && !StringUtils.isEmpty(user.getId()))
            return myCourseTablesService.addChooseCourse(user.getId(), courseId);
        else
            XueChengPlusException.cast("未登录");
        return null;
    }
```



### 2.2.5 接口测试

1、准备测试环境

保证课程发布有已发布的课程

2、测试添加免费课程

成功：选课记录表一条记录、我的课程表一条记录。

3、测试添加收费课程

成功：选课记录表一条记录

4、重复添加选课

重复添加相同的课程，观察是否存在异常。

使用httpclient

```http
### 添加选课
POST {{learning_host}}/learning/choosecourse/2
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsieHVlY2hlbmctcGx1cyJdLCJ1c2VyX25hbWUiOiJ7XCJjb21wYW55SWRcIjpcIjEyMzIxNDE0MjVcIixcImNyZWF0ZVRpbWVcIjpcIjIwMjItMDktMjhUMDg6MzI6MDNcIixcImlkXCI6XCI1MlwiLFwibmFtZVwiOlwiTeiAgeW4iFwiLFwicGVybWlzc2lvbnNcIjpbXCJ4Y190ZWFjaG1hbmFnZXJcIixcInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VcIixcInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfYWRkXCIsXCJ4Y190ZWFjaG1hbmFnZXJfY291cnNlX2RlbFwiLFwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9tYXJrZXRcIixcInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfYmFzZVwiLFwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9wbGFuXCIsXCJ4Y190ZWFjaG1hbmFnZXJfY291cnNlX3B1Ymxpc2hcIixcInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfbGlzdFwiXSxcInNleFwiOlwiMVwiLFwic3RhdHVzXCI6XCJcIixcInVzZXJuYW1lXCI6XCJ0MVwiLFwidXR5cGVcIjpcIjEwMTAwMlwifSIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2ODEyNzc0OTQsImF1dGhvcml0aWVzIjpbInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfYmFzZSIsInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfZGVsIiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9saXN0IiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9wbGFuIiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZSIsInhjX3RlYWNobWFuYWdlciIsInhjX3RlYWNobWFuYWdlcl9jb3Vyc2VfbWFya2V0IiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9wdWJsaXNoIiwieGNfdGVhY2htYW5hZ2VyX2NvdXJzZV9hZGQiXSwianRpIjoiMmM5YTFlMWItMGIyYy00ZTUyLWJhMmUtMmMwYmE4MjM5YzM5IiwiY2xpZW50X2lkIjoiWGNXZWJBcHAifQ.kWYrau1Wlq-BUc9foyTBNkw9DAHiKhcDdKJTLUWS6uE

```



## 2.3 前后端联调

### 2.3.1 查询学习资格

对于免费课程在课程详情页面点击“马上学习”，通过引导界面添加选课。

1、进入课程详情点击马上学习

![image-20230412165739993](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/0bde907a7741d17fd22588819b5e6cf2.png)

2、课程免费时引导加入我的课程表、或进入学习界面。

![image-20230412171111009](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/80527789dee0a112d6ece462aaee8324.png)

这里需要查询用户的学习资格，下边编写查询学习资格的接口

```java
@ApiOperation("查询学习资格")
@PostMapping("/choosecourse/learnstatus/{courseId}")
public XcCourseTablesDto getLearnstatus(@PathVariable("courseId") Long courseId) {
    //登录用户
    SecurityUtil.XcUser user = SecurityUtil.getUser();
    if(user == null){
        XueChengPlusException.cast("请登录后继续选课");
    }
    String userId = user.getId();
    return  courseTablesService.getLeanringStatus(userId, courseId);

}

```





### 2.3.2 测试

测试流程：

1、启动认证服务、网关服务、验证码服务、学习中心服务、内容管理服务。

2、发布一门免费课程

3、进入课程详情界面，点击“马上学习”

4、报名成功，自动跳转到学习界面。

5、观察选课记录表、我的课程表数据是否正确



# 3. 支付

## 3.1 需求分析

### 3.1.1 业务流程

用户去学习收费课程时引导其去支付，如下图：

![image-20230412173138294](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/62164cb0a693e2434b2da0d0f6d58383.png)

当用户点击“微信支付”或支付宝支付时执行流程如下：

![image-20230412173151209](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/9a93241e12a7d9d7b629671eec01affc.png)

1、请求学习中心服务创建选课记录

2、请求订单服务创建商品订单、生成支付二维码。

3、用户扫码请求订单支付服务，订单支付服务请求第三方支付平台生成支付订单。

4、前端唤起支付客户端，用户输入密码完成支付。

5、第三方支付平台支付完成发起支付通知。

6、订单支付服务接收支付通知结果。

7、用户在前端查询支付结果，请求订单支付服务查询支付结果，如果订单支付服务还没有收到支付结果则请求学习中心查询支付结果。

8、订单支付服务向学习中心服务通知支付结果。

9、学习中心服务收到支付结果，如果支付成功则更新选课记录，并添加到我的课程表。



### 3.1.2 通用订单服务设计

在本项目中不仅选课需要下单、购买学习资料、老师一对一答疑等所以收费项目都需要下单支付。

所以本项目设计通用的订单服务，通用的订单服务承接各业务模块的收费支付需求，当用户需要交费时统一生成商品订单并进行支付。

![image-20230412173519908](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/14b2573c950cfbbdc881c5e45ce74b50.png)

所有收费业务最终转换为商品订单记录在订单服务的商品订单表。

![image-20230412173534558](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/df384b113e18c47fdbc6911ad895432b.png)

以选课为例，选课记录表的ID记录在商品订单表的out_business_id字段。

![image-20230412173852795](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/40ec134bf514b182948c6d89691c60d4.png)

## 3.2 支付接口调研

### 3.2.1 微信支付接口调研

一般情况下，一个网站要支持在线支付功能通常接入第三方支付平台，比如：微信支付、支付宝、其它的聚合支付平台。

本项目的需求实现手机扫码支付，现在对微信、支付宝的支付接口进行调研。

微信目前提供的支付方式如下：

地址：https://pay.weixin.qq.com/static/product/product_index.shtml

![image-20230413111926758](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/f7693c7b2d6e5f14452d8ea8f90c6f27.png)

1、付款码支付是指用户展示微信钱包内的“付款码”给商户系统扫描后直接完成支付，适用于线下场所面对面收银的场景，例如商超、便利店、餐饮、医院、学校、电影院和旅游景区等具有明确经营地址的实体场所。

![image-20230413112131677](C:\Users\woldier\AppData\Roaming\Typora\typora-user-images\image-20230413112131677.png)

2、JSAPI支付是指商户通过调用微信支付提供的JSAPI接口，在支付场景中调起微信支付模块完成收款

线下场所：调用接口生成二维码，用户扫描二维码后在微信浏览器中打开页面后完成支付

公众号场景：用户在微信公众账号内进入商家公众号，打开某个主页面，完成支付

PC网站场景：在网站中展示二维码，用户扫描二维码后在微信浏览器中打开页面后完成支付

![image-20230413112230798](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/d14c4564444518a173fd23553b3ade5d.png)

3、小程序支付是指商户通过调用微信支付小程序支付接口，在微信小程序平台内实现支付功能；用户打开商家助手小程序下单，输入支付密码并完成支付后，返回商家小程序。

![image-20230413112246107](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/d9dd52e9cab2008d3c009557026fb5b2.png)

4、Native支付是指商户系统按微信支付协议生成支付二维码，用户再用微信“扫一扫”完成支付的模式。该模式适用于PC网站、实体店单品或订单、媒体广告支付等场景。

![image-20230413112300015](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/1cdbd5a0635ae92ca83faefebfc63772.png)

5、APP支付是指商户通过在移动端应用APP中集成开放SDK调起微信支付模块来完成支付。适用于在移动端APP中集成微信支付功能的场景。

![image-20230413112314478](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/3de3c2e6f75540b7b458ad9e6b163edd.png)

6、刷脸支付是指用户在刷脸设备前通过摄像头刷脸、识别身份后进行的一种支付方式，安全便捷。适用于线下实体场所的收银场景，如商超、餐饮、便利店、医院、学校等。

![image-20230413112330884](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/71bd5d78c33e84c6d87cd0f8570d0a8e.png)

以上接口native和JSAPI都可以实现pc网站实现扫码支付，两者区别是什么？怎么选择？

JSAPI除了在pc网站扫码支付还可以实现公众号页面内支付，可以实现在H5页面唤起微信客户端完成支付。

本项目选择JSAPI支付接口。

接口文档：https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_1.shtml

如何开通JSAPI支付接口?

以企业身份注册微信公众号https://mp.weixin.qq.com/

![image-20230413112407364](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/be2bb0c0d52b18747fb8ae1e3fd5ebe4.png)

登录公众号，点击左侧菜单“微信支付”开通微信支付，如下：

需要提供营业执照、身份证等信息。

![image-20230413112421720](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/1ce1215fe747459841ae8f7aac634d50.png)

点击申请接入，需要注册微信商户号。

![image-20230413112436134](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/611ad60d988ceaa9ae2f68f37100717c.png)

注册微信商户号的过程请参考官方文档，本文档略。参考地址如下：

https://pay.weixin.qq.com/index.php/apply/applyment_home/guide_normal#none

开通微信支付后即可在微信商户平台（pay.weixin.qq.com）开通JSAPI支付。

登录商品平台，进入产品中心，开通JSAPI支付：

![image-20230413112451303](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/83a67511a95d16322fc6ec3e4cf68c06.png)

注意：JSAPI支付方式需要在公众号配置回调域名，此域名为已经备案的外网域名。

最后在公众号开发信息中获取：开发者id、开发者密码。

### 3.2.2 支付宝接口调研

支付宝支付产品如下：

文档：https://b.alipay.com/signing/productSetV2.htm

![image-20230413112515100](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/579a2d4a4709eac39cdf828375dfa6ea.png)

与本项目需求相关的接口：电脑网站支付、手机网站支付。

1、电脑网站支付

PC网站轻松收款，资金马上到账：用户在商家PC网站消费，自动跳转支付宝PC网站收银台完成付款。 交易资金直接打入商家支付宝账户，实时到账。

![image-20230413112531567](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/c243c23a1da0ca5159bea4935e1b746f.png)

2、手机网站支付

用户在商家手机网站消费，通过浏览器自动跳转支付宝APP或支付宝网页完成付款。 轻松实现和APP支付相同的支付体验

![image-20230413112552956](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/468fa8b66a5290feb7e3088c05594c4c.png)

对比两种支付方式：手机网站支付方式可以在H5网页唤起支付宝，手机扫码支付可以使用手机网站支付方式来完成，相比电脑网站支付形式更灵活。

本项目选择手机网站支付方式。

文档：https://opendocs.alipay.com/open/02ivbt

如何开通支付宝手机网站支付接口？

进入网址：https://b.alipay.com/signing/productDetailV2.htm?productId=I1011000290000001001

点击：立即开通

上传营业执照等资料，提交审核，根据提示进行开通。

![image-20230413112612759](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/f9795dd63f53857ffa90778245641ab8.png)

## 3.3 准备开发环境

### 3.3.1 支付宝发环境

第三方支付接口流程大同小异，考虑开发及教学的方便性，支付宝提供支付宝沙箱环境开发支付接口，在教学中接入支付宝手机网站支付接口。

1、配置沙箱环境

沙箱环境是支付宝开放平台为开发者提供的与生产环境完全隔离的联调测试环境，开发者在沙箱环境中完成的接口调用不会对生产环境中的数据造成任何影响。

接入手机网站支付需要具备如下条件：

•     申请前必须拥有经过实名认证的支付宝账户；

•     企业或个体工商户可申请；

•     需提供真实有效的营业执照，且支付宝账户名称需与营业执照主体一致；

•     网站能正常访问且页面显示完整，网站需要明确经营内容且有完整的商品信息；

•     网站必须通过ICP备案。如为个体工商户，网站备案主体需要与支付宝账户主体名称一致；

•     如为个体工商户，则团购不开放，且古玩、珠宝等奢侈品、投资类行业无法申请本产品。

详细参见：https://docs.open.alipay.com/203

本文档使用支付宝沙箱进行开发测试，这里主要介绍支付宝沙箱环境配置。

详细参见：https://docs.open.alipay.com/200/105311/

2、模拟器

下载模拟器：http://mumu.163.com/

安装模拟器，安装在没有空格和中文的目录。

安装成功，启动模拟器

![image-20230413112759733](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/c7ba4b736b2a27489925f936492e85a9.png)

下一步在模拟器安装支付宝：

选择资料文件夹提供的支付宝安装包wallet_101521226_client_release_201812261416.apk（沙箱版本）

安装成功后支付宝客户端的快捷方式出现在桌面上。

![image-20230413112822404](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/b41d0d738d8c0505877e5a726d553d50.png)

使用沙箱环境的买家账号登录沙箱版本的支付宝。

查看沙箱环境的账号：

![image-20230413112837899](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/b47d0fe6ab5e674cf4cbd3eb74c8c04f.png)





### 3.3.2 创建订单服务

拷贝课程资料目录下的订单服务工程xuecheng-plus-orders到自己的工程目录。

## 3.4 支付接口测试

### 3.4.1 阅读接口定义

### 3.4.2 下单执行流程

### 3.4.3 支付接口测试

####  3.4.3.1 编写下单代码

#### 3.4.3.2 生成二维码

#### 3.4.3.3 接口测试

### 3.4.4 支付结果查询接口

### 3.4.5 支付结果通知

####  3.4.5.1 准备环境

#### 3.4.5.2 编写测试代码

#### 3.4.5.3 通知接口测试



```
# XXX. xxxxxx模块

## XXX.1 需求分析

### XXX.1.1 业务流程

### XXX.1.2 数据模型

## XXX.2 接口定义



## XXX.3 接口开发

### XXX.3.1 DAO开发

### XXX.3.2 Service开发

### XXX.3.3 接口代码完善

## XXX.4 接口测试
```

