# 学成在线

写在前面:此文档的作用是记录开发的过程,并不是一个完整的使用文档.

相关资料

链接：https://pan.baidu.com/s/1VsgH7pqrYqb0VM3rmOqDyg?pwd=dfx5 
提取码：dfx5 
--来自百度网盘超级会员V5的分享

## 1.项目背景及介绍

### 1.1 项目背景

以下内容摘自https://report.iresearch.cn/content/2021/01/358854.shtml
在线教育行业是一个有着极强的广度和深度的行业，从校内到校外；从早幼教到职业培训；从教育工具 到全信息化平台等等。 2020年的新冠疫情外生冲击，让在线教育再次站在聚光灯下。疫情下教育领域获融资最多，而其中在线
教育最受资本青睐。据艾瑞咨询统计，2020年教育行业累计融资1164亿元，其中在线教育融资金额 1034亿元，占比89%。与此同时，在行业处于困境的情况下，会加速洗牌，资源向好的企业集中。 2020年资源向头部集中趋势明显，中小型机构生存更加困难。2020年资本向在线教育行业累计输送的 1034亿元中，80%都流向了头部的5家公司。
To C市场
据艾瑞咨询统计核算，2020年中国在线教育行业市场规模2573亿元，过去4年的CAGR达34.5%，其中 低幼及素质教育赛道、K12学科培训赛道在线化进程加快是在线教育市场快速增长的最主要贡献因素。 疫情影响下，低幼及素质教育领域的在线化范围持续纵深，职业教育领域的在线化进程也在不断加速， 新的供给和需求不断产生。但同时，2020年疫情外生冲击加快了2020年的在线教育进程，将会透支一 部分2021年的增速，艾瑞预计2021年在线教育行业同比增速将回落到20%左右。

![image-20230213104923138](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213104923138.png)

To B 市场
疫情也加速了整个教育产业链的进化，to B机构快速成长起来，扮演着赋能者的角色，课程内容、招 生、师训、直播系统、管理系统等产品及服务大量涌现。随着云服务发展成熟以及疫情对直播课需求的 催化，大量提供直播授课系统等PaaS/SaaS服务的机构迅速成长起来，成为各种会展上的主力军。



本项目是黑马程序员公司自研的一个专门针对成人职业技能教育的网络课堂系统，网站提供了成人职业技能培训 的相关课程，如：软件开发培训、职业资格证书培训、成人学历教育培训等课程。项目基于B2B2C的业 务模式，培训机构可以在平台入驻、发布课程，运营人员对发布的课程进行审核，审核通过后课程才可 以发布成功，课程包括免费和收费两种形式，对于免费课程可以直接选课学习，对于收费课程在选课后 需要支付成功才可以继续学习。
什么是B2B2C？
B2B2C是一种电子商务类型的网络购物商业模式，B是Business的简称，C是Consumer的简称，第一个 B指的是商品或服务的供应商，第二个B指的是从事电子商务的企业，C则是表示消费者。

### 1.2 项目介绍

本项目包括了用户端、机构端、运营端。
核心模块包括：内容管理、媒资管理、课程搜索、订单支付、选课管理、认证授权等。 下图是项目的功能模块图：

![image-20230213105137070](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213105137070.png)

​	本项目采用前后端分离架构，后端采用SpringBoot、SpringCloud技术栈开发，数据库使用了MySQL， 还使用的Redis、消息队列、分布式文件系统、Elasticsearch等中间件系统。
​	划分的微服务包括：内容管理服务、媒资管理服务、搜索服务、订单支付服务、 学习中心服务、系统管 理服务、认证授权服务、网关服务、注册中心服务、配置中心服务等。

下边介绍业务流程： 

1. 课程编辑与发布流程如下：

![image-20230213105231243](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213105231243.png)

2. 课程发布后学生登录平台进行选课、在线学习

免费课程可直接学习，收费课程需要下单购买。 学生选课流程如下：

![image-20230213105255982](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213105255982.png)

​	项目架构图

![image-20230213105422398](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213105422398.png)

| 序号 | 名称      | 功能描述                                                     |
| ---- | --------- | ------------------------------------------------------------ |
| 1    | 用户层    | 用户层描述了本系统所支持的用户类型包括：pc用户、app用户、h5用户。pc用 户通过浏览器访问系统、app用户通过android、ios手机访问系统，H5用户通过 h5页面访问系统。 |
| 2    | CDN       | CDN全称Content Delivery Network，即内容分发网络，本系统所有静态资源全 部通过CDN加速来提高访问速度。系统静态资源包括：html页面、js文件、css文 件、image图片、pdf和ppt及doc教学文档、video视频等。 |
| 3    | 负载 均衡 | 系统的CDN层、UI层、服务层及数据层均设置了负载均衡服务，上图仅在UI层前 边标注了负载均衡。 每一层的负载均衡会根据系统的需求来确定负载均衡器的类 型，系统支持4层负载均衡+7层负载均衡结合的方式，4层负载均衡是指在网络传 输层进行流程转发，根据IP和端口进行转发，7层负载均衡完成HTTP协议负载均 衡及反向代理的功能，根据url进行请求转发。 |
| 4    | UI层      | UI层描述了系统向pc用户、app用户、h5用户提供的产品界面。根据系统功能模 块特点确定了UI层包括如下产品界面类型： 1）面向pc用户的门户系统、学习中 心系统、教学管理系统、系统管理中心。 2）面向h5用户的门户系统、学习中心 系统。 3）面向app用户的门户系统、学习中心系统。 |
| 5    | 微服 务层 | 微服务层将系统服务分类三类：业务服务、基础服务、第三方代理服务。 业务服 务：主要为学成在线核心业务提供服务，并与数据层进行交互获得数据。 基础服 务：主要管理学成在线系统运行所需的配置、日志、任务调度、短信等系统级别 的服务。 第三方代理服务：系统接入第三方服务完成业务的对接，例如认证、支 付、视频点播/直播、用户认证和授权。 |
| 6    | 数据 层   | 数据层描述了系统的数据存储的内容类型，关系性数据库：持久化的业务数据使 用MySQL。 消息队列：存储系统服务间通信的消息，本身提供消息存取服务，与 微服务层的系统服务连接。 索引库：存储课程信息的索引信息，本身提供索引维 护及搜索的服务，与微服务层的系统服务连接。 缓存：作为系统的缓存服务，作 为微服务的缓存数据便于查询。 文件存储：提供系统静态资源文件的分布式存储 服务，文件存储服务器作为CDN服务器的数据来源，CDN上的静态资源将最终在 文件存储服务器上保存多份。 |

> l流程说明

 	1. 用户可以通过pc、手机等客户端访问系统进行在线学习。 
 	2. 系统应用CDN技术，对一些图片、CSS、视频等资源从CDN调度访问。 
 	3.  所有的请求全部经过负载均衡器。 
 	4.  对于PC、H5等客户端请求，首先请求UI层，渲染用户界面。
 	5.  客户端UI请求服务层获取进行具体的业务操作。
 	6.  服务层将数据持久化到数据库。



- 项目技术栈

学成在线按照技术分层的基础上，需要对主要层次使用具体的技术作说明。下面是学成在线技术栈结 构图。

![image-20230213105802244](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213105802244.png)

## 2.基础环境搭建

![image-20230213105816537](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213105816537.png)



每一种类的工程都有不同的作用，下面是对其功能进行说明： 

- 父工程
  对依赖包的版本进行管理 本身为Pom工程，对子工程进行聚合管理
- 基础工程
  继承父类工程 提供基础类库 提供工具类库
- 微服务工程
  分别从业务、技术方面划分模块，每个模块构建为一个微服务。 每个微服务工程依赖基础工程，间接继承父工程。
  包括：内容管理服务、媒资管理服务、搜索服务、缓存服务、消息服务等。

1. 搭建父工程

​	父工程pom文件需要把打包方式设为pom以便于其他工程继承，完整的pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.xuecheng</groupId>
    <artifactId>xuecheng-plus-parent</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF8</project.reporting.outputEncoding>
        <spring-boot.version>2.3.7.RELEASE</spring-boot.version>
        <spring-cloud.version>Hoxton.SR9</spring-cloud.version>
        <org.mapstruct.version>1.3.1.Final</org.mapstruct.version>
        <spring-cloud-alibaba.version>2.2.6.RELEASE</spring-cloud-alibaba.version>
        <org.projectlombok.version>1.18.8</org.projectlombok.version>
        <javax.servlet-api.version>4.0.1</javax.servlet-api.version>
        <fastjson.version>1.2.83</fastjson.version>
        <druid-spring-boot-starter.version>1.2.8</druid-spring-boot-starter.version>
        <mysql-connector-java.version>8.0.30</mysql-connector-java.version>
        <mybatis-plus-boot-starter.version>3.4.1</mybatis-plus-boot-starter.version>
        <commons-lang.version>2.6</commons-lang.version>
        <minio.version>8.4.3</minio.version>
        <xxl-job-core.version>2.3.1</xxl-job-core.version>
        <swagger-annotations.version>1.5.20</swagger-annotations.version>
        <commons-lang3.version>3.10</commons-lang3.version>
        <okhttp.version>4.8.1</okhttp.version>
        <swagger-spring-boot-starter.version>1.9.0.RELEASE</swagger-spring-boot-starter.version>
        <elasticsearch.version>7.12.1</elasticsearch.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring-cloud-alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency> <!-- lombok，简化类的构建-->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${org.projectlombok.version}</version>
            </dependency> <!-- mapstruct 代码生成器，简化java bean之间的映射 -->
            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-jdk8</artifactId>
                <version>${org.mapstruct.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct-processor</artifactId>
                <version>${org.mapstruct.version}</version>
            </dependency>
            <dependency>
                <groupId>io.swagger</groupId>
                <artifactId>swagger-annotations</artifactId>
                <version>${swagger-annotations.version}</version>
            </dependency> <!-- Servlet 容器管理 -->
            <dependency>
                <groupId>javax.servlet</groupId>
                <artifactId>javax.servlet-api</artifactId>
                <version>${javax.servlet-api.version}</version>
                <scope>provided</scope>
            </dependency> <!-- fastjson ，json解析工具 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>fastjson</artifactId>
                <version>${fastjson.version}</version>
            </dependency> <!-- druid 连接池管理 -->
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid-spring-boot-starter</artifactId>
                <version>${druid-spring-boot-starter.version}</version>
            </dependency>
            <!-- mySQL数据库驱动包管理 -->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql-connector-java.version}</version>
            </dependency> <!-- mybatis plus 集成Spring Boot启动器 -->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-boot-starter</artifactId>
                <version>${mybatis-plus-boot-starter.version}</version>
            </dependency>
            <!-- mybatis plus 代码生成器 -->
            <dependency>
                <groupId>com.baomidou</groupId>
                <artifactId>mybatis-plus-generator</artifactId>
                <version>${mybatis-plus-boot-starter.version}</version>
            </dependency>
            <!-- 工具类管理 -->
            <dependency>
                <groupId>commons-lang</groupId>
                <artifactId>commons-lang</artifactId>
                <version>${commons-lang.version}</version>
            </dependency> <!-- 分布式文件系统 minIO的客户端API包 -->
            <dependency>
                <groupId>io.minio</groupId>
                <artifactId>minio</artifactId>
                <version>${minio.version}</version>
            </dependency> <!--google推荐的一套工具类库-->
            <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>25.0-jre</version>
            </dependency> <!--分布式任务调度-->
            <dependency>
                <groupId>com.xuxueli</groupId>
                <artifactId>xxl-job-core</artifactId>
                <version>${xxl-job-core.version}</version>
            </dependency> <!--Spring boot单元测试-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <version>${spring-boot.version}</version>
                <scope>test</scope>
                <exclusions>
                    <exclusion>
                        <groupId>org.junit.vintage</groupId>
                        <artifactId>junit-vintage-engine</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>com.squareup.okhttp3</groupId>
                <artifactId>okhttp</artifactId>
                <version>${okhttp.version}</version>
            </dependency>
            <dependency>
                <groupId>org.apache.commons</groupId>
                <artifactId>commons-lang3</artifactId>
                <version>${commons-lang3.version}</version>
            </dependency>
            <dependency>
                <groupId>com.spring4all</groupId>
                <artifactId>swagger-spring-boot-starter</artifactId>
                <version>${swagger-spring-boot-starter.version}</version>
            </dependency>
            <dependency>
                <groupId>org.elasticsearch.client</groupId>
                <artifactId>elasticsearch-rest-high-level-client</artifactId>
                <version>${elasticsearch.version}</version>
            </dependency>
            <dependency>
                <groupId>org.elasticsearch</groupId>
                <artifactId>elasticsearch</artifactId>
                <version>${elasticsearch.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <finalName>${project.name}</finalName>
        <!--编译打包过虑配置-->
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <includes>
                    <include>**/*</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
        </resources>
        <plugins> <!--打包插件-->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring-boot.version}</version>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration> <!--指定项目源码jdk的版本-->
                    <source>1.8</source> <!--指定项目编译后的jdk的版本-->
                    <target>1.8</target> <!--配置注解预编译-->
                    <annotationProcessorPaths>

                        <path>
                            <groupId>org.mapstruct</groupId>
                            <artifactId>mapstruct-processor</artifactId>
                            <version>${org.mapstruct.version}</version>
                        </path>

                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${org.projectlombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            <!--责处理项目资源文件并拷贝到输出目录，如果有额外的资源文件目录则需要配置-->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <encoding>utf-8</encoding> <!--使用默认分隔符，resource中可以使用分割符定义过虑的路径-->
                    <useDefaultDelimiters>true</useDefaultDelimiters>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



2. 搭建base工程

base工程需要继承自parent工程，但是需要注意的是两个工程处于同级目录，因此需要加入`relativePath`标签,表明parent工程的位置

![image-20230213110554446](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213110554446.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.xuecheng</groupId>
        <artifactId>xuecheng-plus-parent</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../xuecheng-plus-parent</relativePath>
    </parent>
    <groupId>com.xuecheng</groupId>
    <artifactId>xuecheng-plus-base</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency> <!-- fast Json -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
        </dependency> <!-- servlet Api 依赖 -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <scope>provided</scope>
        </dependency>
        <!-- 通用组件 -->
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.11</version>
        </dependency>
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-annotations</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency> <!--根据扩展名取mimetype-->
        <dependency>
            <groupId>com.j256.simplemagic</groupId>
            <artifactId>simplemagic</artifactId>
            <version>1.17</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
        <dependency>
            <groupId>com.google.zxing</groupId>
            <artifactId>core</artifactId>
            <version>3.3.3</version>
        </dependency>
        <dependency>
            <groupId>com.google.zxing</groupId>
            <artifactId>javase</artifactId>
            <version>3.3.3</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.module</groupId>
            <artifactId>jackson-module-parameter-names</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jdk8</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
        </dependency>
    </dependencies>

</project>
```

## 3.内容管理模块

### 3.1 mysql

建立数据库`xc_content`(下图的数据库名有误)建立mysql表

表sql语句详见`day1/资料/db/xcplus_content.sql`

![image-20230213141349871](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213141349871.png)

### 3.2 创建模块工程

![image-20230214143951564](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214143951564.png)

1. 首先在项目根目录创建内容管理模块的父工程xuecheng-plus-content![image-20230213142420250](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213142420250.png)

创建完成，删除多余的文件(这里的src也要删除)。
内容管理父工程的主要职责是聚合内容管理接口和内容管理接口实现两个工程，它的父工程是 xuecheng-plus-parent。 pom.xml如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.xuecheng</groupId>
        <artifactId>xuecheng-plus-parent</artifactId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../xuecheng-plus-parent</relativePath>
    </parent>

    <groupId>com.xuecheng</groupId>
    <artifactId>xuecheng-plus-content</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <modules>
        <module>xuecheng-plus-content-api</module>
        <module>xuecheng-plus-content-model</module>
        <module>xuecheng-plus-content-service</module>
    </modules>
    

</project>
```

>  由于xuecheng-plus-content-api和xuecheng-plus-content-service两个工程还没有创建所以modules 报错。



2. 在xuecheng-plus-content下创建xuecheng-plus-content-model数据模型工程。

![image-20230213142636082](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213142636082.png)

pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.xuecheng</groupId>
        <artifactId>xuecheng-plus-content</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>xuecheng-plus-content-model</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>com.xuecheng</groupId>
            <artifactId>xuecheng-plus-base</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```



3. 在xuecheng-plus-content下创建xuecheng-plus-content-service接口实现工程。

![image-20230213143033736](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230213143033736.png)

对应的pom文件如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.xuecheng</groupId>
        <artifactId>xuecheng-plus-content</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>xuecheng-plus-content-service</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>com.xuecheng</groupId>
            <artifactId>xuecheng-plus-content-model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

</project>
```

4. 在xuecheng-plus-content下创建xuecheng-plus-content-api接口工程。

xuecheng-plus-content-api接口工程的父工程是xuecheng-plus-content，它依赖了xuecheng-plusbase基础工程。

### 3.2 课程查询

#### 3.2.1 课程查询

- 需求分析

业务流程

1. 教学机构人员点击课程管理首先进入课程查询界面，如下：

![image-20230214144354265](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214144354265.png)

2. 在课程进行列表查询页面输入查询条件查询课程信息

当不输入查询条件时输入全部课程信息。 输入查询条件查询符合条件的课程信息。 约束：本教学机构查询本机构的课程信息。

![image-20230214144413832](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214144413832.png)

- 数据模型

课程查询功能涉及的数据表有课程基本信息表、课程计划表，如下图：

![image-20230214144446037](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214144446037.png)

下边从查询条件、查询列表两个方面进行分析：

1. 查询条件：

包括：课程名称、课程审核状态、课程发布状态 课程名称：可以模糊搜索
课程审核状态：未提交、已提交、审核通过、审核未通过 课程发布状态：未发布、已发布、已下线
因为是分页查询所以查询条件中还要包括当前页码、每页显示记录数。

2. 查询结果：

查询结果中包括：课程id、课程名称、任务数、创建时间、审核状态、类型，从结果上看基本来源于课 程基本信息表，任务数需要关联教学计划学查询。
因为是分页查询所以查询结果中还要包括总记录数、当前页、每页显示记录数。

- 生成PO

PO即持久对象(Persistent Object)，它们是由一组属性和属性的get和set方法组成，PO对应于数据库的 表。
在开发持久层代码时需要根据数据表编写PO类，在实际开发中通常使用代码生成器（工具）生成PO类 的代码。
由于在需求分析阶段对数据模型进行分析，PO类对应于数据模型，所以在需求分析阶段即可使用工具 生成PO类，为下面的接口定义准备好模型类。
本项目使用mybatis-plus的generator工程生成PO类，地址在：https://github.com/baomidou/gener ator
将资料目录下的`day01/资料/xuecheng-plus-generator.zip`解压后拷贝至项目工程根目录

![image-20230214154817365](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214154817365.png)

打开IDEA将其导入项目工程

打开xuecheng-plus-generator工程的pom.xml，右键 点击“Add as Maven Project” 自动识别maven工 程。

![image-20230214154937013](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214154937013.png)



此时xuecheng-plus-generator工程的pom.xml文件图标发生改变。

![image-20230214155023734](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214155023734.png)

打开`ContentCodeGenerator.java`修改数据库的账号密码,以及ip地址,点击运行即可生成对应的PO

生成好后的文件目录如下

![image-20230214160517328](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214160517328.png)

我们只需要把生成好的po类拷贝到对应的项目模块中去即可,这里我们将其复制到`xuecheng-plus-content-model`模块中

![image-20230214161037982](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214161037982.png)

拷贝完成后,我们会发现有一些报错信息,找不到包,这是因为我们没有导入mybatis-plus依赖,将其添加即可(另外,这里创建的包名有误,应该是com.xuecheng.content.model.po)



- 接口定义

定义一个接口需要包括以下几个方面：

1. 协议

通常协议采用HTTP，查询类接口通常为get或post，查询条件较少的使用get，较多的使用post。 本接口使用 http post。 还要确定content-type，参数以什么数据格式提交，结果以什么数据格式响应。 一般情况没有特殊情况结果以json 格式响应。

2. 分析请求参数

根据前边对数据模型的分析，请求参数为：课程名称、课程审核状态、当前页码、每页显示记录数。 根据分析的请求参数定义模型类。

3. 分析响应结果

根据前边对数据模型的分析，响应结果为数据列表加一些分页信息（总记录数、当前页、每页显示记录 数）。
数据列表中数据的属性包括：课程id、课程名称、任务数、创建时间、审核状态、类型。
注意：查询结果中的审核状态为数据字典中的代码字段，前端会根据审核状态代码 找到对应的名称显 示。
根据分析的响应结果定义模型类。

4. 分析完成，使用SpringBoot注解开发一个Http接口。

5. 使用接口文档工具查看接口的内容。

6. 接口中调用Service方法完成业务处理。

```
POST /content/course/list?pageNo=2&pageSize=1 
Content-Type: application/json 
{
"auditStatus": "202002", "courseName": ""
} 
###成功响应结果 
{
	"items": [
    {
		"id": 26, 
		"companyId": 1232141425, 
		"companyName": null, 
		"name": "spring cloud实战", 
		"users": "所有人", "tags": null, 
		"mt": "1-3", 
		"mtName": null,
        "st": "1-3-2",
		"pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/SpringBOOT-Interview-questions.jpg", 
		"createDate": "2019-09-04 09:56:19", 
		"changeDate": "2021-12-26 22:10:38", 
		"createPeople": null, 
		"changePeople": null, 
		"auditStatus": "202002", 
		"auditMind": null, 
		"auditNums": 0, 
		"auditDate": null, 
		"auditPeople": null, 
		"status": 1, 
		"coursePubId": null, 
		"coursePubDate": null
	}
	], 
	"counts": 23, 
	"page": 2, 
	"pageSize": 1
}
```

- 定义请求查询模型类

对于查询条件较多的接口定义单独的模型类接收参数。
由于分页查询这一类的接口在项目较多，这里针对分页查询的参数（当前页码、每页显示记录数）单独 在`xuecheng-plus-base`基础工程定义

![image-20230214164942643](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214164942643.png)

```java
package com.xuecheng.base.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.java.Log;
/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/2/14 16:47
 **/


/**
 * @description 分页查询通用参数 * @author Mr.M * @date 2022/9/6 14:02 * @version 1.0
 */
@Data
@ToString
public class PageParams {
    //当前页码默认值
    public static final long DEFAULT_PAGE_CURRENT = 1L; //每页记录数默认值
    public static final long DEFAULT_PAGE_SIZE = 10L;

    //当前页码
    private Long pageNo = DEFAULT_PAGE_CURRENT;
    //每页记录数默认值

    private Long pageSize = DEFAULT_PAGE_SIZE;

    public PageParams() {
    }

    public PageParams(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}

```

除了分页查询参数，剩下的就是课程查询的特有参数，此时需要在内容管理的model工程中定义课程查 询参数模型类。
定义DTO包，DTO即数据传输对象（DTO）(Data Transfer Object)，用于接口层和业务层之间传输数 据。

![image-20230214165445348](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214165445348.png)

```java
package com.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * * @description 课程查询参数Dto
 * * @author Woldier Wong
 * * @date 2022/9/6 14:36
 * * @version 1.0
 */
@Data
@ToString
public class QueryCourseParamsDto { //审核状态
    private String auditStatus; //课程名称 private String courseName; //发布状态
    private String publishStatus;
}
```



现在项目中有两类模型类：DTO数据传输对象、PO持久化对象，DTO用于接口层向业务层之间传输数 据，PO用于业务层与持久层之间传输数据，有些公司还会设置VO对象，VO对象用在前端与接口层之间 传输数据，如下图：

![image-20230214165648046](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214165648046.png)

当前端有多个平台且接口存在差异时就需要设置VO对象用于前端和接口层传输数据。

比如：
课程列表查询接口，根据用户需求用户在手机端也要查询课程信息，此时课程查询接口是否需要编写手 机端和PC端两个接口呢？
如果用户要求通过手机和PC的查询条件或查询结果不一样，此时就需要定义两个Controller课程查询接 口，每个接口定义VO对象与前端传输数据。
手机查询：根据课程状态查询，查询结果只有课程名称和课程状态。
PC查询：可以根据课程名称、课程状态、课程审核状态等条件查询，查询结果也比手机查询结果内容 多。
此时，Service业务层尽量提供一个业务接口，即使两个前端接口需要的数据不一样，Service可以提供 一个最全查询结果，由Controller进行数据整合。
如下图：

![image-20230214165814374](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214165814374.png)

如果前端的接口没有多样性且比较固定，此时可以取消VO，只用DTO即可。

如下图:
![image-20230214165833338](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214165833338.png)

- 定义响应模型类

根据接口分析，下边定义响应结果模型类。 针对分页查询结果经过分析也存在固定的数据和格式，所以在base工程定义一个基础的模型类。

![image-20230214170102580](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230214170102580.png)

```java
package com.xuecheng.base.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class PageResult<T> implements Serializable {
    // 数据列表 
    private List<T> items;
    //总记录数 
    private long counts;
    //当前页码 
    private long page;
    //每页记录数 
    private long pageSize;

    public PageResult(List<T> items, long counts, long page, long pageSize) {
        this.items = items;
        this.counts = counts;
        this.page = page;
        this.pageSize = pageSize;
    }
}
```

我们发现此模型类中定义了List属性，此属性存放数据列表，且支持泛型，课程查询接口的返回类型可 以是此模型类型。 List中的数据类型用什么呢？根据需求分析使用生成的PO类即可，所以课程查询接口返回结果类型如 下：

```java
//泛型中填写CourseBase类型。 
 PageResult<CourseBase>
```

- 接口定义

根据分析，此接口提供 HTTP post协议，查询条件以json格式提交，响应结果为json 格式。 可使用SpringBoot注解在Controller类中实现。

![image-20230215094835502](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215094835502.png)

1. 首先在api工程添加依赖

```xml
<dependencies>
        <dependency>
            <groupId>com.xuecheng</groupId>
            <artifactId>xuecheng-plus-content-model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.xuecheng</groupId>
            <artifactId>xuecheng-plus-content-service</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

       <!--cloud的基础环境包-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-context</artifactId>
        </dependency> <!-- Spring Boot 的 Spring Web MVC 集成 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- 排除 Spring Boot 依赖的日志包冲突 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <!-- Spring Boot 集成 log4j2 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
        <!-- Spring Boot 集成 swagger -->
        <dependency>
            <groupId>com.spring4all</groupId>
            <artifactId>swagger-spring-boot-starter</artifactId>
            <version>1.9.0.RELEASE</version>
        </dependency>
    </dependencies>
```

2. 定义controller方法

```java
package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.po.CourseBase;
//import com.xuecheng.content.model.vo.QueryCourseParamsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/2/14 17:29
 **/
@Api(value = "课程信息编辑接口", tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {
    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody
    QueryCourseParamsDto queryCourseParams) {
        return null;
    }
}

```

3. 定义启动类

```java
package com.xuecheng;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author woldier
 * @version 1.0
 * @description 启动类
 * @date 2023/2/15 9:34
 **/
@SpringBootApplication
@EnableSwagger2Doc //开启swagger
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

```

4. 定义配置文件

- `application.yml`

```yml
server:
  servlet:
    context-path: /content # 服务访问根路径
    port: 63040
#微服务配置
spring:
  application:
    name: content-api
# 日志文件配置路径
logging:
  config: classpath:log4j2-dev.xml

#swagger 配置
swagger:
  title: "学成在线内容管理系统"
  description: "内容系统管理系统对课程相关信息进行业务管理数据"
  base-package: com.xuecheng.content #扫描包路径该包及其子包下所有方法和model都会被扫描
  enabled: true 
  version: 1.0.0
```

- `log4j2-dev.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration monitorInterval="180" packages="">
    <properties>
        <property name="logdir">logs</property>
        <property name="PATTERN">%date{YYYY-MM-dd HH:mm:ss,SSS} %level [%thread][%file:%line] - %msg%n%throwable</property>
    </properties>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${PATTERN}"/>
        </Console>

        <RollingFile name="ErrorAppender" fileName="${logdir}/error.log"
            filePattern="${logdir}/$${date:yyyy-MM-dd}/error.%d{yyyy-MM-dd-HH}.log" append="true">
            <PatternLayout pattern="${PATTERN}"/>
            <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>

        <RollingFile name="DebugAppender" fileName="${logdir}/info.log"
            filePattern="${logdir}/$${date:yyyy-MM-dd}/info.%d{yyyy-MM-dd-HH}.log" append="true">
            <PatternLayout pattern="${PATTERN}"/>
            <ThresholdFilter level="DEBUG" onMatch="ACCEPT" onMismatch="DENY"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>
        
        <!--异步appender-->
         <Async name="AsyncAppender" includeLocation="true">
            <AppenderRef ref="ErrorAppender"/>
            <AppenderRef ref="DebugAppender"/>
        </Async>
    </Appenders>
    
    <Loggers>
         <!--过滤掉spring和mybatis的一些无用的debug信息-->
        <logger name="org.springframework" level="INFO">
        </logger>
        <logger name="org.mybatis" level="INFO">
        </logger>
        <logger name="cn.itcast.wanxinp2p.consumer.mapper" level="DEBUG">
        </logger>

        <logger name="springfox" level="INFO">
        </logger>
		<logger name="org.apache.http" level="INFO">
        </logger>
        <logger name="com.netflix.discovery" level="INFO">
        </logger>
        
        <logger name="RocketmqCommon"  level="INFO" >
		</logger>
		
		<logger name="RocketmqRemoting" level="INFO"  >
		</logger>
		
		<logger name="RocketmqClient" level="WARN">
		</logger>

        <logger name="org.dromara.hmily" level="WARN">
        </logger>

        <logger name="org.dromara.hmily.lottery" level="WARN">
        </logger>

        <logger name="org.dromara.hmily.bonuspoint" level="WARN">
        </logger>
		
        <!--OFF   0-->
        <!--FATAL   100-->
        <!--ERROR   200-->
        <!--WARN   300-->
        <!--INFO   400-->
        <!--DEBUG   500-->
        <!--TRACE   600-->
        <!--ALL   Integer.MAX_VALUE-->
        <Root level="DEBUG" includeLocation="true">
            <AppenderRef ref="AsyncAppender"/>
            <AppenderRef ref="Console"/>
            <AppenderRef ref="DebugAppender"/>
        </Root>
    </Loggers>
</Configuration>

```

运行启动类.

启动工厂,访问http://localhost:63040/content/swagger-ui.html 查看接口信息

![image-20230215095520824](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215095520824.png)

##### 3.2.2 swagger介绍

上节最后打开的是一个在线的接口文档，它是由Swaager生成的。

什么是Swagger？

​	OpenAPI规范（OpenAPI Specification 简称OAS）是Linux基金会的一个项目，试图通过定义一种用来描述API格式或API定义的语言，来规范RESTful服务开发过程，目前版本是V3.0，并且已经发布并开源在github上。

（https://github.com/OAI/OpenAPI-Specification）

​	Swagger是全球最大的OpenAPI规范（OAS）API开发工具框架，Swagger是一个在线接口文档的生成工具，前后端开发人员依据接口文档进行开发。 (https://swagger.io/)

只要添加Swagger的依赖和配置信息即可使用它。

```xml
<!-- Spring Boot 集成 swagger -->
<dependency>
    <groupId>com.spring4all</groupId>
    <artifactId>swagger-spring-boot-starter</artifactId>
</dependency>
```

在	bootstrap.yml中配置

```yml
swagger:
  title: "学成在线内容管理系统"
  description: "内容系统管理系统对课程相关信息进行业务管理数据"
  base-package: com.xuecheng.content #扫描包路径该包及其子包下所有方法和model都会被扫描
  enabled: true
  version: 1.0.0
```

base-package为扫描的包路径，扫描Controller类。

Spring Boot 可以集成Swagger，Swaager根据Controller类中的注解生成接口文档 ，在模型类上也可以添加注解对模型类中的属性进行说明，方便对接口文档的阅读。

比如：下边标红的属性名称，可以通过swaager注解标注一个中文名称，方便阅读接口文档。



标注的方法非常简单：

找到模型类，在属性上添加注解：

分别是`com.xuecheng.base.model.PageParams`和`com.xuecheng.content.model.dto.QueryCourseParamsDto`

```java
public class PageParams {
 ...
 @ApiModelProperty("当前页码")
private Long pageNo = DEFAULT_PAGE_CURRENT;

@ApiModelProperty("每页记录数默认值")
private Long pageSize = DEFAULT_PAGE_SIZE;
...
public class QueryCourseParamsDto {

  //审核状态
@ApiModelProperty("审核状态")
 private String auditStatus;
 //课程名称
 @ApiModelProperty("课程名称")
 private String courseName;

}
```

![image-20230215102113427](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215102113427.png)

Swaager的常用注解如下：

在Java类中添加Swagger的注解即可生成Swagger接口，常用Swagger注解如下：

@Api：修饰整个类，描述Controller的作用
 @ApiOperation：描述一个类的一个方法，或者说一个接口
 @ApiParam：单个参数描述
 @ApiModel：用对象来接收参数
 @ApiModelProperty：用对象接收参数时，描述对象的一个字段
 @ApiResponse：HTTP响应其中1个描述
 @ApiResponses：HTTP响应整体描述
 @ApiIgnore：使用该注解忽略这个API
 @ApiError ：发生错误返回的信息
 @ApiImplicitParam：一个请求参数
 @ApiImplicitParams：多个请求参数

@ApiImplicitParam属性：

| 属性         | 取值   | 作用                                          |
| ------------ | ------ | --------------------------------------------- |
| paramType    |        | 查询参数类型                                  |
|              | path   | 以地址的形式提交数据                          |
|              | query  | 直接跟参数完成自动映射赋值                    |
|              | body   | 以流的形式提交 仅支持POST                     |
|              | header | 参数在request headers 里边提交                |
|              | form   | 以form表单的形式提交 仅支持POST               |
| dataType     |        | 参数的数据类型 只作为标志说明，并没有实际验证 |
|              | Long   |                                               |
|              | String |                                               |
| name         |        | 接收参数名                                    |
| value        |        | 接收参数的意义描述                            |
| required     |        | 参数是否必填                                  |
|              | true   | 必填                                          |
|              | false  | 非必填                                        |
| defaultValue |        | 默认值                                        |

使用Swagger可以进行接口的测试。

修改接口内容，添加一些测试代码 

```java
 @ApiOperation("课程查询接口")
 @PostMapping("/course/list")
  public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParams){

      CourseBase courseBase = new CourseBase();
      courseBase.setName("测试名称");
      courseBase.setCreateDate(LocalDateTime.now());
      List<CourseBase> courseBases = new ArrayList();
      courseBases.add(courseBase);
      PageResult pageResult = new PageResult<CourseBase>(courseBases,10,1,10);
      return pageResult;
  }
```

debug方式启动，在 return pageResult;处打断点。

再用swagger请求接口。

通过下图可以看到请求参数已经正常传输controller方法

![image-20230215103209926](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215103209926.png)

放行继续运行，观察swagger界面，结果可以正常返回

![image-20230215103239336](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215103239336.png)

不过存在一个问题就是LocalDateTime类型的数据转json后数据格式并不是我们要的年月日时分秒

在base工程下添加配置类如下，可从课程资料中直接拷贝。

```java
package com.xuecheng.base.config;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * @author woldier
 * @version 1.0
 * @description
 *  时间的序列化与反序列化
 *  base模块怕配置LocalDateTime序列化与反序列化规范成yyyy-MM-dd HH-mm-ss
 *  并且将其注入到sping容器中，以便其他工程使用（需要注意的是，其他工程的app必须是在com.xuecheng下，否侧无法扫描到）
 * @date 2023/2/15 10:34
 **/
@Configuration
public class LocalDateTimeConfig {
    /**
    * @ 序列化内容
    *  string -> localDateTIme
    * @author  woldier
    *
    */
    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer(){
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer(){
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
        };
    }
}

```

修改完成,重新启动项目

![image-20230215105007846](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215105007846.png)

data字段如我们所设置

#### 3.2.2 面试

1、SpringBoot接口开发的常用注解有哪些？

@Controller 标记此类是一个控制器，可以返回视图解析器指定的html页面，通过@ResponseBody可以将结果返回json、xml数据。

@RestController 相当于@ResponseBody加 @Controller，实现rest接口开发，返回json数据，不能返回html页面。

@RequestMapping 定义接口地址，可以标记在类上也可以标记在方法上，支持http的post、put、get等方法。

@PostMapping 定义post接口，只能标记在方法上，用于添加记录，复杂条件的查询接口。

@GetMapping 定义get接口，只能标记在方法上，用于查询接口的定义。

@PutMapping 定义put接口，只能标记在方法上，用于修改接口的定义。

@DeleteMapping 定义delete接口，只能标记在方法上，用于删除接口的定义。

@RequestBody 定义在方法上，用于将json串转成java对象。

@PathVarible 接收请求路径中占位符的值.

@ApiOperation swagger注解，对接口方法进行说明。

@Api wagger注解，对接口类进行说明。

@Autowired 基于类型注入。

@Resource 基于名称注入，如果基于名称注入失败转为基于类型注入。



2、项目的开发流程是什么？

1、产品人员设计产品原型。

2、讨论需求。

3、分模块设计接口。

4、出接口文档。

5、将接口文档给到前端人员，前后端分离开发。

6、开发完毕进行测试。

7、测试完毕发布项目，由运维人员进行部署安装。

#### 3.2.3 业务层开发

##### 3.2.3.1 DAO开发

业务层为接口层提供业务处理支撑，本项目业务层包括了持久层的代码，一些大型公司的团队职责划分更细，会将持久层和业务层分为两个工程，不过这需要增加成本。

DAO即数据访问对象，通过DAO去访问数据库对数据进行持久化。本项目使用持久层框架MyBatis-Plus进行开发。

由于课程计划功能还没有讲解，这里暂时查询course_base单表数据。

持久层的基础代码我们也采用代码生成工具生成，在前边生成PO类的同时将Mapper接口及xml文件也生成了，如下图：

![image-20230215120530598](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215120530598.png)

将mapper接口和xml文件拷贝至业务工程`com.xuecheng.content.service.mapper`下。

![image-20230215130335344](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215130335344.png)

在pom.xml中添加mp支持,mysql支持,以及spring boot test

```xml
<dependencies>
        <dependency>
            <groupId>com.xuecheng</groupId>
            <artifactId>xuecheng-plus-content-model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!-- MySQL 驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- mybatis plus的依赖 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>
        <!-- Spring Boot 集成 Junit -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- 排除 Spring Boot 依赖的日志包冲突 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- Spring Boot 集成 log4j2 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
    </dependencies>
```

下边搭建单元测试环境 

1、上边已将spring-boot-starter-test加入依赖

2、配置扫描mapper及分页插件

```java
package com.xuecheng.content.service.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description mybatis plus 配置类 当前配置了分页插件
 * @date 2023/2/15 12:13
 **/
@Configuration
@MapperScan("com.xuecheng.content.service.mapper")
public class MybatisPlusConfig {

   /**
   * @description TODO 
   *  
   * @return  
   * @author  
   * @date  
   */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /*
        * 如果有多种数据库方言,就不给定方言,让其自动推理
        * */
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}

```

在内容管理的service工程创建：log4j2-dev.xml、application.yml从课程 资料目录获取

application.yml如下

```yml
spring:
  application:
    name: content-service
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://remote.centos.com:3306/xc_content?serverTimezone=UTC&userUnicode=true&useSSL=false&
    username: root
    password: 123456
# 日志文件配置路径
logging:
  config: classpath:log4j2-dev.xml
```

`log4j2-dev.xml`可从前面的工程拷贝

4、编写测试类

```java
package com.xuecheng.content;

import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.po.CourseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author woldier
 * @version 1.0
 * @description 课程内容测试类
 * @date 2023/2/15 12:53
 **/
@SpringBootTest
public class CourseBaseMapperTest {
    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Test
    void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(74L);
        Assertions.assertNotNull(courseBase);
    }
}

```

5、编写启动类

```java
package com.xuecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author woldier
 * @version 1.0
 * @description content 服务 启动类
 * @date 2023/2/15 12:03
 **/
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}

```

##### 3.2.3.2 service开发

##### 3.2.3.1 数据字典表

课程基本信息查询的主要数据来源是课程基本信息表，这里有一个点需要注意，就是课程的审核状态、发布状态。

审核状态在查询条件和查询结果中都存在，审核状态包括：未审核、审核通过、审核未通过三种，下边思考一个问题：一个课程的审核状态如果是“审核未通过”那么在课程基本信息表记录“审核未通过”三个字合适吗？

如果将“审核未通过”五个字记录在课程基本信息表中，显示出来的审核状态就是“审核未通过”这五个字，看起来没有什么问题，如果有一天客户想要将审核未通过的记录在显示时改为“未通过”三个字，怎么办？

这时你可以需要批量处理数据库中记录了，写一个 update 语句，审核状态等于“审核未通过”的全部更新 为“未通过”。看起来解决了问题，如果有一天客户又让改了呢？

和审核状态同类的有好多这样的信息，比如：课程状态、课程类型、用户类型等等，这一类数据有一个共同点就是它有一些分类项，且这些分类项较为固定。针对这些数据，为了提高系统的可扩展性，专门定义数据字典表去维护。

下边是课程审核状态的定义：

```text
[
    {"code":"202001","desc":"审核未通过"},
    {"code":"202002","desc":"未审核"},
    {"code":"202003","desc":"审核通过"}
]
```

每一项都由代码和名称组成。

此时我们好像要干 什么了 ，该课程 的审核状态为审核未通过，那么我们在课程基本信息表存储202001，也就是审核未通过对应的代码，这样查询出的数据在前端展示时根据代码取出它对应的内容显示给用户。如果用户要修改“审核未通过”的显示内容只需要在数据字典表修改，无法修改课程基本信息表。

数据字典表在系统管理数据库中存储，首先导入系统管理数据库，创建系统管理服务的数据库

表sql文件在资料`day1/资料/db/xcplus_system.sql`路径下

![image-20230215193741914](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215193741914.png)

导入脚本后成功如下图:

![image-20230215193845407](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230215193845407.png)

##### 3.2.3.3 Service开发

service的代码可以从generate工程中拷贝生成的也可以自己书写

`com.xuecheng.content.service.CourseBaseInfoService`

```java
package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author woldier
 * @version 1.0
 * @description 课程基本信息管理业务接口
 * @date 2023/2/15 20:40
 **/
public interface CourseBaseInfoService {
   /**
   * @description 课程查询接口 
   * @param pageParams  分页参数
    * @param queryCourseParamsDto   查询参数
   * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase> 
   * @author: woldier 
   * @date: 2023/2/15 22:04
   */
    PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);
}

```

编写接口实现类

```java
package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/2/15 22:05
 **/
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
    @Autowired
    private CourseBaseMapper courseBaseMapper;

    /**
    * @description 课程查询接口的实现
    * @param pageParams 分页参数
     * @param queryCourseParamsDto 查询参数
    * @return com.xuecheng.base.model.PageResult<com.xuecheng.content.model.po.CourseBase>
    * @author: woldier
    * @date: 2023/2/15 22:15
    */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        LambdaQueryWrapper<CourseBase> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        /*
        *添加查询条件
        *对于课程名采用模糊查询 ,其他的采用精确查询 
        * */
        lambdaQueryWrapper.like(queryCourseParamsDto.getCourseName()!=null,CourseBase::getName,queryCourseParamsDto.getCourseName())
                .eq(queryCourseParamsDto.getAuditStatus()!=null,CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus())
                .eq(queryCourseParamsDto.getPublishStatus()!=null,CourseBase::getStatus,queryCourseParamsDto.getPublishStatus());
        /*初始化分页器*/
        IPage<CourseBase> page = new Page<>(pageParams.getPageNo(),pageParams.getPageSize());
        /*分页查询*/
        IPage<CourseBase> selectPage = courseBaseMapper.selectPage(page, lambdaQueryWrapper);
        /*获取数据列表*/
        List<CourseBase> records = selectPage.getRecords();
        /*获取数据总数*/
        long total = selectPage.getTotal();
        /*构造返回集*/
        return new PageResult<>(records,total, pageParams.getPageNo(), pageParams.getPageSize());
    }
}

```

##### 3.2.2.3 接口层完善

```java
package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.po.CourseBase;
//import com.xuecheng.content.model.vo.QueryCourseParamsDto;
import com.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/2/14 17:29
 **/
@Api(value = "课程信息编辑接口", tags = "课程信息编辑接口")
@RestController
@RequiredArgsConstructor // lombok bean注入
public class CourseBaseInfoController {
    /*
    通过Lombok生成构造方法进行注入
     */
    private final CourseBaseInfoService courseBaseInfoService;
    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody
    QueryCourseParamsDto queryCourseParams) {
        return courseBaseInfoService.queryCourseBaseList(pageParams,queryCourseParams);
    }
}


```

值得注意的是,content-api的配置文件`bootstrap.yml`中并没有配置数据库信息,但是为什么能启动成功并且访问数据库呢,这是因为content-api依赖了content-service 而service中的配置文件`application.yml`配置了数据库.

除此之外,还应该注意的一点是,这两个配置文件不能重名,重名的话(比如都叫`application.yml `)则会启动失败

![image-20230216090007519](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216090007519.png)

#### 3.2.4 接口测试

##### 3.2.4.1 Httpclient测试

Swagger是一个在线接口文档，虽然使用它也能测试但需要浏览器进入Swagger，最关键的是它并不能保存测试数据。

在IDEA中有一个非常方便的http接口测试工具httpclient，下边介绍它的使用方法，后边我们会用它进行接口测试。

如果IDEA版本较低没有自带httpclient，需要安装httpclient插件

![image-20230216090738005](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216090738005.png)

进入controller类，找到http接口对应的方法

![image-20230216090805827](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216090805827.png)

点击Generate request in HTTP Client即可生成的一个测试用例。

我们可以添加请求参数进行测试

```http
###
POST http://localhost:63040/content/course/list?pageNo=2&pageSize=10
Content-Type: application/json

{
  "auditStatus": null,
  "courseName": "java",
  "publishStatus": null
}
```



```text
POST http://localhost:63040/content/course/list?pageNo=1&pageSize=2

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 16 Feb 2023 01:12:17 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "items": [
    {
      "id": 1,
      "companyId": 1232141425,
      "companyName": null,
      "name": "JAVA8/9/10新特性讲解",
      "users": "java爱好者,有一定java基础",
      "tags": "有个java 版本变化的新内容，帮助大家使用最新的思想和工具",
      "mt": "1",
      "st": "1-3-2",
      "grade": "204002",
      "teachmode": "200002",
      "description": null,
      "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
      "createDate": "2019-09-03 17:48:19",
      "changeDate": "2022-09-17 16:47:29",
      "createPeople": "1",
      "changePeople": null,
      "auditStatus": "202004",
      "status": "203001"
    },
    {
      "id": 18,
      "companyId": 1232141425,
      "companyName": null,
      "name": "java零基础入门",
      "users": "java小白java小白java小白java小白",
      "tags": "aa",
      "mt": "1-3",
      "st": "1-3-2",
      "grade": "200001",
      "teachmode": "200002",
      "description": "java零基础入门java零基础入门java零基础入门java零基础入门",
      "pic": "/mediafiles/2022/09/13/a16da7a132559daf9e1193166b3e7f52.jpg",
      "createDate": "2019-09-04 09:56:19",
      "changeDate": "2022-09-15 17:43:18",
      "createPeople": null,
      "changePeople": null,
      "auditStatus": "202004",
      "status": "203001"
    }
  ],
  "counts": 6,
  "page": 1,
  "pageSize": 2
}
<Response body is empty>Response code: 200; Time: 148ms (148 ms); Content length: 971 bytes (971 B)
```

.http文件即测试用例文档 ，它可以随着项目工程一起保存，这样测试的数据就可以保存下来，方便进行测试。

为了方便保存.http文件 ，我们单独在项目工程的根目录创建一个目录单独存放它们。

![image-20230216091722016](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216091722016.png)

这里的ip与段都都是写死的

为了方便将来和网关集成测试，这里我们把测试主机地址在配置文件`http-client.env.json` 中配置

![image-20230216091846392](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216091846392.png)

注意：文件名称http-client.env.json保持一致，否则无法读取dev环境变量的内容。

再回到xc-content-api.http文件，将http://localhost:63040 用变量代替

![image-20230216092042085](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216092042085.png)

到此就完成了httpclient的配置与使用测试。

##### 3.2.42 安装系统管理服务

要进行前后端连调首先启动前端工程，参考百度网盘的“开发工具配置”文档安装配置前端环境。

启动前端工程成功，如下 图：

![image-20230216092912900](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216092912900.png)

在浏览器访问http://localhost:8601/

浏览器报错

![image-20230216092932132](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216092932132.png)

http://localhost:8601/api/system/dictionary/all 指向的是系统管理服务。在前端讲解内容管理模块的需求时我们提到一个数据字典表，此链接正是在前端请求后端获取数据字典数据的接口地址。

数据字典表中配置了项目用的字典信息，此接口是查询字典中的全部数据 ，在此我们不在开发，按照下边的步骤安装系统管理服务即可。

从课程资料目录获取xuecheng-plus-system.zip，并解压

将xuecheng-plus-system目录拷贝到项目工程根目录，刷新maven，或进入pom.xml右键转为pom工程。

![image-20230216093744109](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216093744109.png)

并且修改service中application.yml mysql的配置信息

启动系统管理服务，启动成功，在浏览器请求：http://localhost:63110/system/dictionary/all

系统服务的端口是63110

果可以正常读取数据字典信息则说明系统管理服务安装成功。

##### 3.2.4.3 解决跨域问题

系统管理服务启动完成，此时还需要修改前端工程中访问数据字典信息接口的地址，因为默认前端工程请求的是网关地址，目前网关工程还没有部署。

![image-20230216094728460](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216094728460.png)



修改完成，刷新前端工程首页不能正常显示，查看浏览器报错如下：

![image-20230216094800744](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216094800744.png)

提示：从http://localhost:8601访问http://localhost:63110/system/dictionary/all被CORS policy阻止，因为没有Access-Control-Allow-Origin 头信息。CORS全称是 cross origin resource share 表示跨域资源共享。

出这个提示的原因是基于浏览器的同源策略，去判断是否跨域请求，同源策略是浏览器的一种安全机制，从一个地址请求另一个地址，如果协议、主机、端口三者则不是跨域，否则就是跨域请求。

比如：

从http://localhost:8601  到   http://localhost:8602  由于端口不同，是跨域。

从http://192.168.101.10:8601  到   http://192.168.101.11:8601  由于主机不同，是跨域。

从http://192.168.101.10:8601  到   https://192.168.101.11:8601  由于协议不同，是跨域。

注意：服务器之间不存在跨域请求。

浏览器判断是跨域请求会在请求头上添加origin，表示这个请求来源哪里。

比如：

```
GET / HTTP/1.1
Origin: http://localhost:8601
```

服务器收到请求判断这个Origin判断是否允许跨域，如果允许则在响应头中说明允许该来源的跨域请求，如下：

```
Access-Control-Allow-Origin：http://localhost:8601
```

如果允许域名来源的跨域请求，则响应如下：

```
Access-Control-Allow-Origin：*
```

解决跨域的方法：

1、JSONP

通过script标签的src属性进行跨域请求，如果服务端要响应内容则首先读取请求参数callback的值，callback是一个回调函数的名称，服务端读取callback的值后将响应内容通过调用callback函数的方式告诉请求方。如下图：

![image-20230216095035519](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216095035519.png)

2、添加响应头

服务端在响应头添加 Access-Control-Allow-Origin：*



3、通过nginx代理跨域

由于服务端之间没有跨域，浏览器通过nginx去访问跨域地址。

![image-20230216095130739](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216095130739.png)

1）浏览器先访问http://192.168.101.10:8601 nginx提供的地址，进入页面

2）此页面要跨域访问http://192.168.101.11:8601 ，不能直接跨域访问http://www.baidu.com:8601  ，而是访问nginx的一个同源地址，比如：http://192.168.101.11:8601/api ，通过http://192.168.101.11:8601/api 的代理去访问http://www.baidu.com:8601。

这样就实现了跨域访问。

浏览器到http://192.168.101.11:8601/api 没有跨域

nginx到http://www.baidu.com:8601通过服务端通信，没有跨域。



我们准备使用方案2解决跨域问题。在内容管理的api工程config包下编写GlobalCorsConfig.java，代码如下：

```java
package com.xuecheng.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @description 跨域过虑器
 * @author Mr.M
 * @date 2022/9/7 11:04
 * @version 1.0
 */
 @Configuration
 public class GlobalCorsConfig {

  /**
   * 允许跨域调用的过滤器
   */
  @Bean
  public CorsFilter corsFilter() {
   CorsConfiguration config = new CorsConfiguration();
   //允许白名单域名进行跨域调用
   config.addAllowedOrigin("*");
   //允许跨越发送cookie
   config.setAllowCredentials(true);
   //放行全部原始头信息
   config.addAllowedHeader("*");
   //允许所有请求方法跨域调用
   config.addAllowedMethod("*");
   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
   source.registerCorsConfiguration("/**", config);
   return new CorsFilter(source);
  }
 }

```

此配置类实现了跨域过虑器，在响应头添加Access-Control-Allow-Origin。

重启系统管理服务，前端工程可以正常进入http://localhost:8601，观察浏览器记录，成功解决跨域。

![image-20230216100834949](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216100834949.png)

##### 3.2.4.4 前后端连调

前端启动完毕，再启内容管理服务端。

前端默认连接的是项目的网关地址，由于现在网关工程还没有创建，这里需要更改前端工程的参数配置文件 ，修改网关地址为内容管理服务的地址。

![image-20230216101104354](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216101104354.png)

启动前端工程，用前端访问后端接口，观察前端界面的数据是否正确。

访问前端首页，进入课程管理：http://localhost:8601/#/organization/course-list

![image-20230216102241935](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216102241935.png)

更改课程条件及分页参数测试课程查询列表是否正常显示。

跟踪内容管理服务的输出日志，查看是否正常。

到此基本完成了前后端连调。

#### 3.2.5 面试

1、Mybatis分页插件的原理？

首先分页参数放到ThreadLocal中，拦截执行的sql，根据数据库类型添加对应的分页语句重写sql，例如：(select * from table where a) 转换为 (select count(*) from table where a)和(select * from table where a limit ,)

计算出了total总条数、pageNum当前第几页、pageSize每页大小和当前页的数据，是否为首页，是否为尾页，总页数等。

### 3.2 课程分类查询

#### 3.2.1 需求分析

下边根据内容管理模块的业务流程，下一步要实现新增课程，在新增课程界面，有三处信息需要选择，如下图：

![image-20230216105011893](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216105011893.png)

课程等级、课程类型来源于数据字典表，此部分的信息前端已从系统管理服务读取。

课程分类信息没有在数据字典表中存储，而是由单独一张课程分类表，存储在内容管理数据库中。

![image-20230216105002923](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216105002923.png)

下边看下course_category课程分类表的结构

![image-20230216105050578](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216105050578.png)

这张表是一个树型结构，通过父结点id将各元素组成一个树。

我们可以看下该表的数据，下图是一部分数据：

![image-20230216105113066](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216105113066.png)

现在的需求是需要在内容管理服务中编写一个接口读取该课程分类表的数据，组成一个树型结构返回给前端。

下边生成课程分类的PO类为接口开发作准备。



![image-20230216105347890](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216105347890.png)

![image-20230216105411650](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216105411650.png)

将生成的po类拷贝到内容管理模块的model工程中，将mapper拷贝到内容管理模块的service工程中。

#### 3.2.2 接口定义

我们可以点击新增课程，观察前端的请求记录：

![image-20230216105542469](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216105542469.png)

http://localhost:8601/api/content/course-category/tree-nodes 该地址正是前端获取课程分类的接口地址。

通过上图界面的内容可以看出该接口的协议为：HTTP GET

请求参数为空。

通过查阅接口文档，此接口要返回全部课程分类，以树型结构返回，如下所示。

```json
[
         {
            "childrenTreeNodes" : [
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-1",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "HTML/CSS",
                  "name" : "HTML/CSS",
                  "orderby" : 1,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-2",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "JavaScript",
                  "name" : "JavaScript",
                  "orderby" : 2,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-3",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "jQuery",
                  "name" : "jQuery",
                  "orderby" : 3,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-4",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "ExtJS",
                  "name" : "ExtJS",
                  "orderby" : 4,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-5",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "AngularJS",
                  "name" : "AngularJS",
                  "orderby" : 5,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-6",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "ReactJS",
                  "name" : "ReactJS",
                  "orderby" : 6,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-7",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "Bootstrap",
                  "name" : "Bootstrap",
                  "orderby" : 7,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-8",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "Node.js",
                  "name" : "Node.js",
                  "orderby" : 8,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-9",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "Vue",
                  "name" : "Vue",
                  "orderby" : 9,
                  "parentid" : "1-1"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-1-10",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "其它",
                  "name" : "其它",
                  "orderby" : 10,
                  "parentid" : "1-1"
               }
            ],
            "id" : "1-1",
            "isLeaf" : null,
            "isShow" : null,
            "label" : "前端开发",
            "name" : "前端开发",
            "orderby" : 1,
            "parentid" : "1"
         },
         {
            "childrenTreeNodes" : [
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-1",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "微信开发",
                  "name" : "微信开发",
                  "orderby" : 1,
                  "parentid" : "1-2"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-2",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "iOS",
                  "name" : "iOS",
                  "orderby" : 2,
                  "parentid" : "1-2"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-3",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "手游开发",
                  "name" : "手游开发",
                  "orderby" : 3,
                  "parentid" : "1-2"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-4",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "Swift",
                  "name" : "Swift",
                  "orderby" : 4,
                  "parentid" : "1-2"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-5",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "Android",
                  "name" : "Android",
                  "orderby" : 5,
                  "parentid" : "1-2"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-6",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "ReactNative",
                  "name" : "ReactNative",
                  "orderby" : 6,
                  "parentid" : "1-2"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-7",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "Cordova",
                  "name" : "Cordova",
                  "orderby" : 7,
                  "parentid" : "1-2"
               },
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-8",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "其它",
                  "name" : "其它",
                  "orderby" : 8,
                  "parentid" : "1-2"
               }
            ],
            "id" : "1-2",
            "isLeaf" : null,
            "isShow" : null,
            "label" : "移动开发",
            "name" : "移动开发",
            "orderby" : 2,
            "parentid" : "1"
         }
   ]
```

上边的数据格式是一个数组结构，数组的元素即为分类信息，分类信息设计两级分类，第一级的分类信息示例如下：

```
"id" : "1-2",
"isLeaf" : null,
"isShow" : null,
"label" : "移动开发",
"name" : "移动开发",
"orderby" : 2,
"parentid" : "1"
```

第二级的分类是第一级分类中childrenTreeNodes属性，它是一个数组结构：

```
{
"id" : "1-2",
"isLeaf" : null,
"isShow" : null,
"label" : "移动开发",
"name" : "移动开发",
"orderby" : 2,
"parentid" : "1",
"childrenTreeNodes" : [
               {
                  "childrenTreeNodes" : null,
                  "id" : "1-2-1",
                  "isLeaf" : null,
                  "isShow" : null,
                  "label" : "微信开发",
                  "name" : "微信开发",
                  "orderby" : 1,
                  "parentid" : "1-2"
               }
 }
```

所以，定义一个DTO类表示分类信息的模型类，如下：

```java
package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程分类树型结点dto
 * @date 2023/2/16 19:38
 **/
public class CourseCategoryTreeDto extends CourseCategory {
    /**
     * 子节点信息
     */
    List childrenTreeNodes;
}
```

接口定义如下：
```java
package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
public class CourseCategoryController {
    @ApiOperation("课程分类查询接口")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
        return null;

    }
}

```

#### 3.2.3 接口开发

##### 3.2.3.1 树形表查询

课程分类表是一个树型结构，其中parentid字段为父结点ID，它是树型结构的标志字段。

如果树的层级固定可以使用表的自链接去查询，比如：我们只查询两级课程分类，可以用下边的SQL

```sql
select
       one.id            one_id,
       one.name          one_name,
       one.parentid      one_parentid,
       one.orderby       one_orderby,
       one.label         one_label,
       two.id            two_id,
       two.name          two_name,
       two.parentid      two_parentid,
       two.orderby       two_orderby,
       two.label         two_label
   from course_category one
            inner join course_category two on one.id = two.parentid
   where one.parentid = 1
     and one.is_show = 1
     and two.is_show = 1
   order by one.orderby,
            two.orderby
```

如果树的层级不确定，此时可以使用MySQL递归实现，使用with语法，如下：

```sql
WITH [RECURSIVE]
        cte_name [(col_name [, col_name] ...)] AS (subquery)
        [, cte_name [(col_name [, col_name] ...)] AS (subquery)] ...
```

cte_name :公共表达式的名称,可以理解为表名,用来表示as后面跟着的子查询

col_name :公共表达式包含的列名,可以写也可以不写

下边是一个递归的简单例子：

```sql
with RECURSIVE t1  AS
(
  SELECT 1 as n
  UNION ALL
  SELECT n + 1 FROM t1 WHERE n < 5
)
SELECT * FROM t1;
```

说明：

t1 相当于一个表名

select 1 相当于这个表的初始值，这里使用UNION ALL 将初始值加入到表中。

n<5为递归执行的条件，当n>=5时结束递归调用。

下边我们使用递归实现课程分类的查询

```sql
with recursive t1 as (
select * from  course_category p where  id= '1'
union all
 select t.* from course_category t inner join t1 on t1.id = t.parentid
)
select *  from t1 order by t1.parentid, t1.orderby
```

![image-20230216202023405](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216202023405.png)

t1表中初始的数据是id等于1的记录，即根结点。

通过inner join t1 t2 on t2.id = t.parentid 找到id='1'的下级节点 。

通过这种方法就找到了id='1'的所有下级节点，下级节点包括了所有层级的节点。

上边这种方法是向下递归，即找到初始节点的所有下级节点。

如何向上递归？

下边的sql实现了向上递归：

```sql
with recursive t1 as (
    select * from   course_category p where  id= '1-9-1'
    union all
    select * from course_category t inner t1 where t.id = t1.parentid
)
select * from t1 order by  t1.parentid
```

![image-20230216202351563](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216202351563.png)

初始节点为1-9-1，通过递归找到它的父级节点，父级节点包括所有级别的节点。

以上是我们研究了树型表的查询方法，通过递归的方式查询课程分类比较灵活，因为它可以不限制层级

##### 3.2.3.2 开发mapper

下边我们可自定义mapper方法查询课程分类，最终将查询结果映射到List<CourseCategoryTreeDto>中。

生成课程分类表的mapper文件并拷贝至内容管理模块 的service工程中。

![image-20230216205754740](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216205754740.png)

1、下边 定义一个mapper方法，并定义sql语句。

```java
package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.po.CourseCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Mapper
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    /**
    * @description 根据id 查询所有子节点
    * @param id
    * @return java.util.List<com.xuecheng.content.model.dto.CourseCategoryTreeDto>
    * @author: woldier
    * @date: 2023/2/16 20:38
    */
    List<CourseCategoryTreeDto> selectTreeNodes(String id);
}

```

2、找到对应 的mapper.xml文件，编写sql语句。

```xml

    <select id="selectTreeNodes" resultType="com.xuecheng.content.model.dto.CourseCategoryTreeDto" parameterType="string">
        with recursive t1 as (
            select * from  course_category p where  id= #{id}
            union all
            select t.* from course_category t inner join t1 on t1.id = t.parentid
        )
        select *  from t1 order by t1.parentid, t1.orderby
    </select>



```

##### 3.2.3.3 开发service

定义service接口，调用mapper查询课程分类，遍历数据按照接口要求对数据进行封装

```java
package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程分类service
 * @date 2023/2/16 20:56
 **/
public interface CourseCategoryService {
    List<CourseCategoryTreeDto> queryTreeNodes();
}

```

编写service接口实现

```java
package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author woldier
 * @version 1.0
 * @description 课程分类service 实现
 * @date 2023/2/16 20:57
 **/
@Service
@RequiredArgsConstructor  //lombok 注入
public class CourseCategoryServiceImpl implements CourseCategoryService {
    private final CourseCategoryMapper courseCategoryMapper;

    /**
     * @return java.util.List<com.xuecheng.content.model.dto.CourseCategoryTreeDto>
     * @description 递归查询出所有的课程分类
     * @author: woldier
     * @date: 2023/2/16 21:00
     */
    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        /*从数据库中取出所有的节点*/
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes("1");
        /*遍历节点把他们根据tree关系放到返回集中*/
        /*
        * 1. 遍历整个list,把每个element加入map中(以id为key).
        * 2. 查看elem的parentid 如果在map中,取出map中与parentid对应的元素mapElem,并且将其elem加入其childList(如果childList为空新建否则直接插入)
        * 3. 进行filter 过滤掉childList为空的
        * */
        Map<String,CourseCategoryTreeDto> elemMap = new HashMap<>();
        courseCategoryTreeDtos.stream().forEach(e -> {
            /* 把每个element加入map中(以id为key).*/
            elemMap.put(e.getId(),e);
            /*查看elem的parentid 如果在map中*/
            if(elemMap.keySet().contains(e.getParentid())){
                /*取出map中与parentid对应的元素mapElem*/
                CourseCategoryTreeDto courseCategoryTreeDto = elemMap.get(e.getParentid());
                /*并且将其elem加入其childList(如果childList为空新建否则直接插入)*/
                if(courseCategoryTreeDto.getChildrenTreeNodes()==null) courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<>());
                courseCategoryTreeDto.getChildrenTreeNodes().add(e);
            }
        });
       /*进行filter 过滤掉childList为空的*/
        List<CourseCategoryTreeDto> collect = courseCategoryTreeDtos.stream().filter(e -> e.getChildrenTreeNodes() != null).collect(Collectors.toList());
        return collect;
    }
}

```

##### 3.2.3.4 单元测试

```java
package com.xuecheng.content;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 测试
 * @date 2023/2/16 20:42
 **/
@SpringBootTest
@Slf4j
public class CourseCategoryMapperTest {

    @Autowired
    private  CourseCategoryMapper courseCategoryMapper;

    @Autowired
    private CourseCategoryService courseCategoryService;


    @Test
    public void testCourseCategoryMapper(){
//        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes("1");
//        log.info(courseCategoryTreeDtos.toString());

        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryService.queryTreeNodes();
    }
}

```

#### 3.2.4 接口测试

##### 3.2.4.1 接口层代码完善

完善controller方法，注入service调用业务层方法查询课程分类。

```java
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

```

##### 3.2.4.2 接口层代码完善

![image-20230216220007269](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216220007269.png)

运行测试。

完成前后端连调：

打开前端工程，进入新增课程页面。

课程分类下拉框可以正常显示

![image-20230216221124792](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230216221124792.png)

#### 3.2.5 面试

1、树型表的标记字段是什么？如何查询MySQL树型表？

树型表的标记字段是parentid即父结点的id。

查询一个树型表的方法：

1）当层级固定时可以用表的自链接进行查询。

2）如果想灵活查询每个层级可以使用mysql递归方法，使用with RECURSIVE 实现。



2、MyBatis的ResultType和ResultMap的区别？

ResultType：指定映射类型，只要查询的字段名和类型的属性名匹配可以自动映射。

ResultMap：自定义映射规则，当查询的字段名和映射类型的属性不匹配时可以通过ResultMap自定义映射规则，也可以实现一对多、一对一映射。



3、#{} 和 ${} 有什么区别？

\#{}是标记一个占位符，可以防止sql注入。

${} 用于在动态 sql中拼接字符串，可能导致sql注入。

### 3.3 新增课程

#### 3.3.1 需求分析

##### 3.3.1.1 业务流程

根据前边对内容管理模块的数据模型分析，课程相关的信息有：课程基本信息、课程营销信息、课程图片信息、课程计划、课程师资信息，所以新增一门课程需要完成这几部分信息的填写。

以下是业务流程：

1、进入课程查询列表

![image-20230217091837712](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217091837712.png)

2、点击添加课程，选择课程类型是直播还是录播。

![image-20230217091855010](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217091855010.png)

课程类型不同课程的授课方式不同。

3、选择完毕，点击下一步，进入课程基本信息添加界面。

本界面分两部分信息，一部分是课程基本信息上，一部分是课程营销信息。

课程基本信息：

![image-20230217091920331](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217091920331.png)

在这个界面中填写课程的基本信息、课程营销信息上。

填写完毕，保存并进行下一步。

4、在此界面填写课程计划信息

![image-20230217092146141](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217092146141.png)

课程计划即课程的大纲目录。

课程计划分为两级，章节和小节。

每个小节需要上传课程视频，用户点击 小节的标题即开始播放视频。

如果是直播课程则会进入直播间。

5、课程 计划填写完毕进入课程师资的管理。

![image-20230217092200789](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217092200789.png)

在课程师资界面维护该课程的授课老师。

![image-20230217092215582](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217092215582.png)

至此，一门课程新增完成。

##### 3.3.1.2 数据模型

![image-20230217095829780](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217095829780.png)

这两部分信息分别在course_base、course_market两张表存储。当点击保存按钮时向这两张表插入数据。这两张表是一对一关联关系。

![image-20220915180825089](D:\bat\IdeaProjects\xuecheng-online\imgs\image-20220915180825089.png)

![image-20230217095844923](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230217095844923.png)

新建课程的初始审核状态为“未提交”、初始发布状态为“未发布”。

####  3.3.2 接口定义

根据业务流程，这里先定义提交课程基本信息的接口。

1、接口协议 ：HTTP POST，Content-Type为application/json

2、请求及响应结果如下

![image-20230222101541341](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230222101541341.png)

![image-20230222101602798](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230222101602798.png)

3、接口请求示例如下 

```http
### 创建课程
POST {{content_host}}/content/course
Content-Type: application/json

{

  "mt": "",
  "st": "",
  "name": "",
  "pic": "",
  "teachmode": "200002",
  "users": "初级人员",
  "tags": "",
  "grade": "204001",
  "description": "",
  "objectives": "",
  "charge": "201000",
  "price": 0,
  "originalPrice":0,
  "qq": "",
  "wechat": "",
  "phone": "",
  "validDays": 365
}

###响应结果如下
#成功响应结果如下
{
  "id": 109,
  "companyId": 1,
  "companyName": null,
  "name": "测试课程103",
  "users": "初级人员",
  "tags": "",
  "mt": "1-1",
  "mtName": null,
  "st": "1-1-1",
  "stName": null,
  "grade": "204001",
  "teachmode": "200002",
  "description": "",
  "pic": "",
  "createDate": "2022-09-08 07:35:16",
  "changeDate": null,
  "createPeople": null,
  "changePeople": null,
  "auditStatus": "202002",
  "status": 1,
  "coursePubId": null,
  "coursePubDate": null,
  "charge": "201000",
  "price": null,
  "originalPrice":0,
  "qq": "",
  "wechat": "",
  "phone": "",
  "validDays": 365
}
```

请求参数相比 CourseBase模型类不一致，需要定义

```java
package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @description 添加课程dto
 * @author Mr.M
 * @date 2022/9/7 17:40
 * @version 1.0
 */
@Data
@ApiModel(value="AddCourseDto", description="新增课程基本信息")
public class AddCourseDto {

 @NotEmpty(message = "课程名称不能为空")
 @ApiModelProperty(value = "课程名称", required = true)
 private String name;

 @NotEmpty(message = "适用人群不能为空")
 @Size(message = "适用人群内容过少",min = 10)
 @ApiModelProperty(value = "适用人群", required = true)
 private String users;

 @ApiModelProperty(value = "课程标签")
 private String tags;

 @NotEmpty(message = "课程分类不能为空")
 @ApiModelProperty(value = "大分类", required = true)
 private String mt;

 @NotEmpty(message = "课程分类不能为空")
 @ApiModelProperty(value = "小分类", required = true)
 private String st;

 @NotEmpty(message = "课程等级不能为空")
 @ApiModelProperty(value = "课程等级", required = true)
 private String grade;

 @ApiModelProperty(value = "教学模式（普通，录播，直播等）", required = true)
 private String teachmode;

 @ApiModelProperty(value = "课程介绍")
 private String description;

 @ApiModelProperty(value = "课程图片", required = true)
 private String pic;

 @NotEmpty(message = "收费规则不能为空")
 @ApiModelProperty(value = "收费规则，对应数据字典", required = true)
 private String charge;

 @ApiModelProperty(value = "价格")
 private BigDecimal price;
 @ApiModelProperty(value = "原价")
 private BigDecimal originalPrice;


 @ApiModelProperty(value = "qq")
 private String qq;

 @ApiModelProperty(value = "微信")
 private String wechat;
 @ApiModelProperty(value = "电话")
 private String phone;

 @ApiModelProperty(value = "有效期")
 private Integer validDays;
}

```





```java
package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description 课程基本信息dto
 * @author Mr.M
 * @date 2022/9/7 17:44
 * @version 1.0
 */
@Data
public class CourseBaseInfoDto extends CourseBase {


 /**
  * 收费规则，对应数据字典
  */
 private String charge;

 /**
  * 价格
  */
 private Float price;


 /**
  * 原价
  */
 private Float originalPrice;

 /**
  * 咨询qq
  */
 private String qq;

 /**
  * 微信
  */
 private String wechat;

 /**
  * 电话
  */
 private String phone;

 /**
  * 有效期天数
  */
 private Integer validDays;

 /**
  * 大分类名称
  */
 private String mtName;

 /**
  * 小分类名称
  */
 private String stName;

}

```

4、定义接口如下

```java
@ApiOperation("新增课程基础信息")
@PostMapping("/course")
public CourseBaseInfoDto createCourseBase(@RequestBody AddCourseDto addCourseDto){
    return null;
}
```

#### 3.3.3 接口开发

定义service方法

```java
public interface CourseBaseInfoService {
 /**
    * @description 新增课程
    * @param companyId 公司id
     * @param addCourseDto  课程信息
    * @return com.xuecheng.content.model.dto.CourseBaseInfoDto 返回课程基本信息及营销信息
    * @author: woldier
    * @date: 2023/2/22 11:14
    */
    CourseBaseInfoDto addCourse(Long companyId, AddCourseDto addCourseDto);
}
```

定义serviceImpl方法实现

```java
@Service
@RequiredArgsConstructor
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {
/**
     * @description 新增课程
     * @param companyId 公司id
     * @param dto  课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto 返回课程基本信息及营销信息
     * @author: woldier
     * @date: 2023/2/22 11:14
     */
    @Override
    @Transactional
    public CourseBaseInfoDto addCourse(Long companyId, AddCourseDto dto) {

        /*1.参数合法性校验*/
//合法性校验
        if (StringUtils.isEmpty(dto.getName())) {
            throw new RuntimeException("课程名称为空");
        }

        if (StringUtils.isEmpty(dto.getMt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isEmpty(dto.getSt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isEmpty(dto.getGrade())) {
            throw new RuntimeException("课程等级为空");
        }

        if (StringUtils.isEmpty(dto.getTeachmode())) {
            throw new RuntimeException("教育模式为空");
        }

        if (StringUtils.isEmpty(dto.getUsers())) {
            throw new RuntimeException("适应人群为空");
        }

        if (StringUtils.isEmpty(dto.getCharge())) {
            throw new RuntimeException("收费规则为空");
        }
        /*2.数据封装调用mapper持久化数据*/
        CourseBase insertCourseBase = new CourseBase();
        //数据拷贝
        BeanUtils.copyProperties(dto,insertCourseBase);
        //设置审核状态
        insertCourseBase.setAuditStatus("202002");
        //设置发布状态
        insertCourseBase.setStatus("203001");
        //机构id
        insertCourseBase.setCompanyId(companyId);
        //添加时间
        insertCourseBase.setCreateDate(LocalDateTime.now());
        //插入课程基本信息表
        int insert = courseBaseMapper.insert(insertCourseBase);

        //得到插入数据的id
        Long courseBaseId = insertCourseBase.getId();

        //组装营销课程信息
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto,courseMarket);
        //设置id
        courseMarket.setId(courseBaseId);
        int insert1 = courseMarketMapper.insert(courseMarket);
        String charge = dto.getCharge();
        if("201001".equals(charge)) //如果为收费课程,扣费规则不能为空
            if(courseMarket.getPrice() == null)
                throw new RuntimeException("本课程为收费课程,但是价格为空");

        /*两张表有一张插入不成功则进行事务回滚*/
        if(insert1<=0||insert<=0)
            throw new RuntimeException("添加课程失败");
        /*3.组装返回结果*/
        return getCourseBaseInfo(courseBaseId);
    }

    public CourseBaseInfoDto getCourseBaseInfo(long courseId){
        /*查询课程*/
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase==null)
            return null;
        /*查询课程营销信息*/
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        /*创建返回的dto*/
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        if(courseMarket!=null)
            BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        CourseCategory courseCategoryMt = courseCategoryMapper.selectById(courseBaseInfoDto.getMt());
        CourseCategory courseCategorySt = courseCategoryMapper.selectById(courseBaseInfoDto.getSt());
        courseBaseInfoDto.setMtName(courseCategoryMt.getName());
        courseBaseInfoDto.setStName(courseCategorySt.getName());
        return courseBaseInfoDto;
    }
}
```



完善controller方法

```java
@ApiOperation("新增课程基础信息")
@PostMapping("/course")
public CourseBaseInfoDto createCourseBase(@RequestBody AddCourseDto addCourseDto){
    //机构id，由于认证系统没有上线暂时硬编码
    Long companyId = 22L;
  return courseBaseInfoService.createCourseBase(companyId,addCourseDto);
}
```

#### 3.3.4 接口测试

1、使用httpclient测试

在xc-content-api.http中定义：

```http
### 课程新增
POST {{content_host}}/content/course
Content-Type: application/json

{
  "charge": "201000",
  "price": 0,
  "originalPrice":0,
  "qq": "22333",
  "wechat": "223344",
  "phone": "13333333",
  "validDays": 365,
  "mt": "1-1",
  "st": "1-1-1",
  "name": "测试课程103",
  "pic": "",
  "teachmode": "200002",
  "users": "初级人员",
  "tags": "",
  "grade": "204001",
  "description": "",
  "objectives": ""
}
```

#### 3.3.5 前后端联调

打开新增课程页面，除了课程图片其它信息全部输入。

点击保存，观察浏览器请求接口参数及响应结果是否正常。

#### 

![](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230226201541433.png)



![image-20230226201700012](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230226201700012.png)





#### 3.3.6 异常处理

##### 3.3.6.1 异常问题分析

在service方法中有很多的参数合法性校验，当参数不合法则抛出异常，下边我们测试下异常处理。

请求创建课程基本信息，故意将必填项设置为空。

测试发现报500异常，如下：

```http
http://localhost:63040/content/course

HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 07 Sep 2022 11:40:29 GMT
Connection: close

{
  "timestamp": "2022-09-07T11:40:29.677+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "",
  "path": "/content/course"
}
```

![image-20230306145427810](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306145427810.png)

问题：并没有输出我们抛出异常时指定的异常信息。

所以，现在我们的需求是当正常操作时按接口要求返回数据，当非正常流程时要获取异常信息进行记录，并提示给用户。

异常处理除了输出在日志中，还需要提示给用户，前端和后端需要作一些约定：

1、错误提示信息统一以json格式返回给前端。

2、以HTTP状态码决定当前是否出错，非200为操作异常。

如何规范异常信息？

代码中统一抛出项目的自定义异常类型，这样可以统一去捕获这一类或几类的异常。

规范了异常类型就可以去获取异常信息。

如果捕获了非项目自定义的异常类型统一向用户提示“执行过程异常，请重试”的错误信息。

如何捕获异常？

代码统一用try/catch方式去捕获代码比较臃肿，可以通过SpringMVC提供的控制器增强类统一由一个类去完成异常的捕获。

如下图：

![image-20230306145731652](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306145731652.png)



##### 3.3.6.2 统一异常处理实现

根据上边分析的方案，统一在base基础工程实现统一异常处理，各模块依赖了base基础工程都 可以使用。

首先在base基础工程添加需要依赖的包：

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

1、定义一些通用的异常信息

从课程资料目录下的exception目录拷贝CommonError枚举类到base工程。

```java
package com.xuecheng.base.exception;

import java.util.Arrays;

/**
 * @author woldier
 * @version 1.0
 * @description 通用的错误信息枚举类
 * @date 2023/3/6 15:01
 **/
public enum CommonError {
    UNKOWN_ERROR("执行过程异常，请重试。"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");

    private String errMessage;

    public String getErrMessage() {
        return errMessage;
    }

    private CommonError( String errMessage) {
        this.errMessage = errMessage;
    }

    /**
    * @description 使用方法案例
    * @param args
    * @return void
    * @author: woldier
    * @date: 2023/3/6 15:04
    */
    public static void main(String[] args) {
        System.out.println(CommonError.PARAMS_ERROR.getErrMessage());
    }
}

```

2、自定义异常类型

```java
package com.xuecheng.base.exception;

/**
 * @author woldier
 * @version 1.0
 * @description 学成项目的自定义业务异常类
 * @date 2023/3/6 15:06
 **/
public class XueChengPlusException extends Exception{
    private static final long serialVersionUID = 5565760508056698922L;

    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    /**
    * @description 优雅化代码，使得抛异常的代码更加简洁
    * @param commonError  传入通用异常枚举 如 CommonError.UNKOWN_ERROR
    * @return void
    * @author: woldier
    * @date: 2023/3/6 15:08
    */
    public static void cast(CommonError commonError) throws XueChengPlusException {
        throw new XueChengPlusException(commonError.getErrMessage());
    }
    /**
    * @description 优雅化代码，使得抛异常的代码更加简洁
    * @param errMessage  传入自定义的报错信息
    * @return void
    * @author: woldier
    * @date: 2023/3/6 15:09
    */
    public static void cast(String errMessage) throws XueChengPlusException {
        throw new XueChengPlusException(errMessage);
    }
}

```

3、响应用户的统一类型

注意： 这里的异常返回类中只有`errMessage`属性,其状态码使用的时http状态码;其实在实际开发中，通常异常返回类还包括状态码属性,而对于http状态码都统一成`200`.此处因为项目前端已经固定好了格式,所以我们就按照项目原有的方式进行实现.

```java
package com.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @author woldier
 * @version 1.0
 * @description 客户端异常返回信息类
 * @date 2023/3/6 15:12
 **/
public class RestErrorResponse implements Serializable {
    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}

```

4、全局异常处理器

从 Spring 3.0 - Spring 3.2 版本之间，对 Spring 架构和 SpringMVC 的Controller 的异常捕获提供了相应的异常处理。

- @ExceptionHandler

  Spring3.0提供的标识在方法上或类上的注解，用来表明方法的处理异常类型。

- @ControllerAdvice

  Spring3.2提供的新注解，从名字上可以看出大体意思是控制器增强，	在项目中来增强SpringMVC中的Controller。通常和**`@ExceptionHandler`** 结合使用，来处理SpringMVC的异常信息。

- @ResponseStatus

  Spring3.0提供的标识在方法上或类上的注解，用状态代码和应返回的原因标记方法或异常类。
  调用处理程序方法时，状态代码将应用于HTTP响应。

通过上面的两个注解便可实现微服务端全局异常处理，具体代码如下：

```java
package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author woldier
 * @version 1.0
 * @description 全局异常处理器
 * @date 2023/3/6 15:18
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
    * @description XueChengPlusException.class 异常处理，提取异常信息并且返回
    * @param e
    * @return com.xuecheng.base.exception.RestErrorResponse
    * @author: woldier
    * @date: 2023/3/6 15:22
    */
    @ExceptionHandler(XueChengPlusException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "业务模块抛出异常")
    public RestErrorResponse doXueChengPlusException(XueChengPlusException e){
        return new RestErrorResponse(e.getErrMessage());
    }

    /**
    * @description Exception 异常处理，此异常为未知异常
    * @param e
    * @return com.xuecheng.base.exception.RestErrorResponse
    * @author: woldier
    * @date: 2023/3/6 15:27
    */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "服务器未知异常")
    public RestErrorResponse doXueChengPlusException(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }
}

```

##### 3.3.6.3 异常处理测试

由于content-api依赖了content-model而content-model又依赖了base因此content-api间接依赖了base不用在content-api中再添加该依赖

![image-20230306153329059](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306153329059.png)

在异常处理测试之前首先在代码中抛出自定义类型的异常，这里以新增课程`com.xuecheng.content.service.impl.CourseBaseInfoServiceImpl`的service方法为例进行代码修改。

```java
@Override
 public CourseBaseInfoDto createCourseBase(Long companyId,AddCourseDto dto) {
 ...
//合法性校验
  if (StringUtils.isBlank(dto.getName())) {
   throw new XueChengPlusException("课程名称为空");
  }

  if (StringUtils.isBlank(dto.getMt())) {
   throw new XueChengPlusException("课程分类为空");
  }

  if (StringUtils.isBlank(dto.getSt())) {
   throw new XueChengPlusException("课程分类为空");
  }

  if (StringUtils.isBlank(dto.getGrade())) {
   throw new XueChengPlusException("课程等级为空");
  }

  if (StringUtils.isBlank(dto.getTeachmode())) {
   throw new XueChengPlusException("教育模式为空");
  }

  if (StringUtils.isBlank(dto.getUsers())) {
   throw new XueChengPlusException("适应人群");
  }

  if (StringUtils.isBlank(dto.getCharge())) {
   throw new XueChengPlusException("收费规则为空");
  }
  。。。
    if ("201001".equals(charge)) {
   BigDecimal price = dto.getPrice();
   if (ObjectUtils.isEmpty(price)) {
    throw new XueChengPlusException("收费课程价格不能为空");
   }
   courseMarketNew.setPrice(dto.getPrice().floatValue());
  }
```

1、首先使用httpclient测试

请求新增课程接口，故意将必填项课程名称设置为空。

测试结果与预期一致，可以捕获异常并响应异常信息，如下：

```htt
http://localhost:63040/content/course

HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 07 Sep 2022 13:17:14 GMT
Connection: close

{
  "errMessage": "课程名称为空。"
}
```



2、前后端调试

仍然测试新增课程接口，当课程收费的时候必须填写价格，这里设置课程为收费，价格设置-1。

![image-20230306160216728](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306160216728.png)

至此，项目异常处理的测试完毕，我们在开发中对于业务分支中错误的情况要抛出项目自定义的异常类型。

##### 3.3.6.4 面试

1、系统如何处理异常？

我们自定义一个统一的异常处理器去捕获并处理异常。

使用控制器增加注解@ControllerAdvice和异常处理注解@ExceptionHandler来实现。

1) 处理自定义异常

程序在编写代码时根据校验结果主动抛出自定义异常类对象，抛出异常时指定详细的异常信息，异常处理器捕获异常信息记录异常日志并响应给用户。

2) 处理未知异常

接口执行过程中的一些运行时异常也会由异常处理器统一捕获，记录异常日志，统一响应给用户500错误。

在异常处理器中还可以针对某个异常类型进行单独处理。



#### 3.3.7 JSR303校验

##### 3.3.7.1 统一校验需求

前端请求后端接口传输参数，是在controller中校验还是在Service中校验？

答案是都需要校验，只是分工不同。

Contoller中校验请求参数的合法性，包括：必填项校验，数据格式校验，比如：是否是符合一定的日期格式，等。

Service中要校验的是业务规则相关的内容，比如：课程已经审核通过所以提交失败。

Service中根据业务规则去校验不方便写成通用代码，Controller中则可以将校验的代码写成通用代码。

早在JavaEE6规范中就定义了参数校验的规范，它就是JSR-303，它定义了Bean Validation，即对bean属性进行校验。

SpringBoot提供了JSR-303的支持，它就是spring-boot-starter-validation，它的底层使用Hibernate Validator，Hibernate Validator是Bean Validation 的参考实现。

所以，我们准备在Controller层使用spring-boot-starter-validation完成对请求参数的基本合法性进行校验。

##### 3.3.7.2 统一校验实现

首先在Base工程添加spring-boot-starter-validation的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

现在准备对内容管理模块添加课程接口`com.xuecheng.content.api.CourseBaseInfoController`进行参数校验，如下接口

```java
@ApiOperation("新增课程接口")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated AddCourseDto addCourseDto) throws XueChengPlusException {
        /*1.获取用户所属公司id*/
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 22L;
        /*2.call 新增课程service*/
        return courseBaseInfoService.addCourse(companyId,addCourseDto);

    }
```

此接口使用AddCourseDto模型对象接收参数，所以进入AddCourseDto类，在属性上添加校验规则。

```java
package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @description 添加课程dto
 * @author Mr.M
 * @date 2022/9/7 17:40
 * @version 1.0
 */
@Data
@ApiModel(value="AddCourseDto", description="新增课程基本信息")
public class AddCourseDto {

 @NotEmpty(message = "课程名称不能为空")
 @ApiModelProperty(value = "课程名称", required = true)
 private String name;

 @NotEmpty(message = "适用人群不能为空")
 @Size(message = "适用人群内容过少",min = 10)
 @ApiModelProperty(value = "适用人群", required = true)
 private String users;

 @ApiModelProperty(value = "课程标签")
 private String tags;

 @NotEmpty(message = "课程分类不能为空")
 @ApiModelProperty(value = "大分类", required = true)
 private String mt;

 @NotEmpty(message = "课程分类不能为空")
 @ApiModelProperty(value = "小分类", required = true)
 private String st;

 @NotEmpty(message = "课程等级不能为空")
 @ApiModelProperty(value = "课程等级", required = true)
 private String grade;

 @ApiModelProperty(value = "教学模式（普通，录播，直播等）", required = true)
 private String teachmode;

 @ApiModelProperty(value = "课程介绍")
 private String description;

 @ApiModelProperty(value = "课程图片", required = true)
 private String pic;

 @NotEmpty(message = "收费规则不能为空")
 @ApiModelProperty(value = "收费规则，对应数据字典", required = true)
 private String charge;

 @ApiModelProperty(value = "价格")
 private BigDecimal price;
 @ApiModelProperty(value = "原价")
 private BigDecimal originalPrice;


 @ApiModelProperty(value = "qq")
 private String qq;

 @ApiModelProperty(value = "微信")
 private String wechat;
 @ApiModelProperty(value = "电话")
 private String phone;

 @ApiModelProperty(value = "有效期")
 private Integer validDays;
}

```

上边用到了@NotEmpty和@Size两个注解，@NotEmpty表示属性不能为空，@Size表示限制属性内容的长短。

在javax.validation.constraints包下有很多这样的校验注解

![image-20230306172641815](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306172641815.png)

规则如下：

![image-20230306172748117](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306172748117.png)

定义好校验规则还需要开启校验，在controller方法中添加@Validated注解，如下：

```java
@ApiOperation("新增课程基础信息")
@PostMapping("/course")
public CourseBaseInfoDto createCourseBase(@RequestBody @Validated AddCourseDto addCourseDto){
    //机构id，由于认证系统没有上线暂时硬编码
    Long companyId = 1L;
  return courseBaseInfoService.createCourseBase(companyId,addCourseDto);
}
```

如果校验出错Spring会抛出MethodArgumentNotValidException异常，我们需要在`base项目`统一异常处理器中捕获异常，解析出异常信息。

代码 如下：

```java
/**
    * @description Controller JSR303校验异常
    * @param e
    * @return com.xuecheng.base.exception.RestErrorResponse
    * @author: woldier
    * @date: 2023/3/6 16:46
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse doMethodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer errMsg = new StringBuffer();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(error -> {
            errMsg.append(error.getDefaultMessage()).append(",");
        });
        log.error(errMsg.toString());
        return new RestErrorResponse(errMsg.toString());
    }
```

重启内容管理服务。

使用httpclient进行测试，将必填项设置为空，“适用人群” 属性的内容设置1个字。

执行测试，接口响应结果如下：

```json
{
  "errMessage": "课程名称不能为空 课程分类不能为空 课程分类不能为空 适用人群内容过少 "
}
```

可以看到校验器生效。

##### 3.3.7.3 分组校验

有时候在同一个属性上设置一个校验规则不能满足要求，比如：订单编号由系统生成，在添加订单时要求订单编号为空，在更新 订单时要求订单编写不能为空。此时就用到了分组校验，同一个属性定义多个校验规则属于不同的分组，比如：添加订单定义@NULL规则属于insert分组，更新订单定义@NotEmpty规则属于update分组，insert和update是分组的名称，是可以修改的。

下边举例说明

我们用class类型来表示不同的分组，所以我们定义不同的接口类型（空接口）表示不同的分组，由于校验分组是公用的，所以定义在 base工程中。如下：

```java
package com.xuecheng.base.execption;
 /**
 * @description 校验分组
 * @author Mr.M
 * @date 2022/9/8 15:05
 * @version 1.0
 */
public class ValidationGroups {

 public interface Inster{};
 public interface Update{};
 public interface Delete{};

}
```

下边在定义校验规则时指定分组：

```java
@NotEmpty(groups = {ValidationGroups.Inster.class},message = "添加课程名称不能为空")
 @NotEmpty(groups = {ValidationGroups.Update.class},message = "修改课程名称不能为空")
// @NotEmpty(message = "课程名称不能为空")
 @ApiModelProperty(value = "课程名称", required = true)
 private String name;
```

在Controller方法中启动校验规则指定要使用的分组名：

```java
@ApiOperation("新增课程接口")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated({ValidationGroups.Inster.class}) AddCourseDto addCourseDto) throws XueChengPlusException {
        /*1.获取用户所属公司id*/
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 22L;
        /*2.call 新增课程service*/
        return courseBaseInfoService.addCourse(companyId,addCourseDto);

    }
```

再次测试，由于这里指定了Insert分组，所以抛出 异常信息：添加课程名称不能为空。

如果修改分组为ValidationGroups.Update.class，异常信息为：修改课程名称不能为空。

>
>
>如果时加了group信息,若某属性校验没有组信息则不会进行校验

##### 3.3.7.4 校验规则不满足？

如果javax.validation.constraints包下的校验规则满足不了需求怎么办？

1、手写校验代码 。

2、自定义校验规则注解。

参考blog 

https://blog.csdn.net/blueheart20/article/details/88817334

https://blog.csdn.net/qq_43437874/article/details/117229391



##### 3.3.7.5 面试

1、请求参数的合法性校验如何做？ 

使用基于JSR303的校验框架实现，SpringBoot提供了JSR-303的支持，它就是spring-boot-starter-validation，它包括了很多校验规则，只需要在模型类中通过注解指定校验规则，在controller方法上开启校验。

### 3.4 修改课程

#### 3.4.1 需求分析

##### 3.4.1.1 业务流程

1、进入课程列表查询

![image-20230306191206404](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306191206404.png)

2、点击编辑

因为课程审核通过方可发布，任何时候都 可以编辑，下图是编辑课程的界面：



进入编辑界面显示出当前课程的信息。

3、修改成功自动进入课程计划编辑页面。

##### 3.4.1.2 数据模型

修改课程的涉及到的数据表是课程基本信息表,课程营销信息表：

![image-20230306193550619](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306193550619.png)

1、进入课程编辑界面

界面中显示了课程的当前信息，需要根据课程id查询课程基本和课程营销信息，显示在表单上。

2、编辑、提交

修改课程提交的数据比新增课程多了一项课程id，因为修改课程需要针对某个课程进行修改。

3、保存数据

编辑完成保存课程基础信息和课程营销信息。

更新课程基本信息表中的修改人、修改时间。

#### 3.4.2 接口定义

##### 3.4.2.1 查询课程信息

定义根据课程id查询课程信息接口。

接口示例如下：

```http
GET /content/course/40
Content-Type: application/json
#响应结果
#{
#  "id": 40,
#  "companyId": 1232141425,
#  "companyName": null,
#  "name": "SpringBoot核心",
#  "users": "Spring Boot初学者",
#  "tags": "Spring项目的快速构建",
#  "mt": "1-3",
#  "mtName": null,
#  "st": "1-3-2",
#  "stName": null,
#  "grade": "200003",
#  "teachmode": "201001",
#  "description": "课程系统性地深度探讨 Spring Boot 核心特性，引导小伙伴对 Java 规范的重视，启发对技术原理性的思考，掌握排查问题的技能，以及学习阅读源码的方法和技巧，全面提升研发能力，进军架构师队伍。",
#  "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
#  "createDate": "2019-09-10 16:05:39",
#  "changeDate": "2022-09-09 07:27:48",
#  "createPeople": null,
#  "changePeople": null,
#  "auditStatus": "202004",
#  "status": "203001",
#  "coursePubId": 21,
#  "coursePubDate": null,
#  "charge": "201001",
#  "price": 0.01
#}
```

查询结果为单条课程信息，内容和新增课程返回结果一致，所以采用与新增课程一致的模型类。

接口定义如下：

```java
/**
    * @description 根据id查询课程信息
    * @param courseId  课程id
    * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
    * @author: woldier
    * @date: 2023/3/6 19:27
    */
    @ApiOperation("查询单个课程接口")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseInfoById(@PathVariable Long courseId){
        return null;
    }
```

##### 3.4.2.2 修改课程信息

根据前边的数据模型分析，修改课程提交的数据比新增多了课程id，接口示例如下：

```http
### 修改课程
PUT /content/course
Content-Type: application/json

{
  "id": 40,
  "companyName": null,
  "name": "SpringBoot核心",
  "users": "Spring Boot初学者",
  "tags": "Spring项目的快速构建",
  "mt": "1-3",
  "st": "1-3-2",
  "grade": "200003",
  "teachmode": "201001",
  "description": "课程系统性地深度探讨 Spring Boot 核心特性，引导小伙伴对 Java 规范的重视，启发对技术原理性的思考，掌握排查问题的技能，以及学习阅读源码的方法和技巧，全面提升研发能力，进军架构师队伍。",
  "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
  "charge": "201001",
  "price": 0.01
}

###修改成功响应结果如下
#{
#  "id": 40,
#  "companyId": 1232141425,
#  "companyName": null,
#  "name": "SpringBoot核心",
#  "users": "Spring Boot初学者",
#  "tags": "Spring项目的快速构建",
#  "mt": "1-3",
#  "mtName": null,
#  "st": "1-3-2",
#  "stName": null,
#  "grade": "200003",
#  "teachmode": "201001",
#  "description": "课程系统性地深度探讨 Spring Boot 核心特性，引导小伙伴对 Java 规范的重视，启发对技术原理性的思考，掌握排查问题的技能，以及学习阅读源码的方法和技巧，全面提升研发能力，进军架构师队伍。",
#  "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
#  "createDate": "2019-09-10 16:05:39",
#  "changeDate": "2022-09-09 07:27:48",
#  "createPeople": null,
#  "changePeople": null,
#  "auditStatus": "202004",
#  "status": "203001",
#  "coursePubId": 21,
#  "coursePubDate": null,
#  "charge": "201001",
#  "price": 0.01
#}
```

这里定义修改课程提交的数据模型。

```java
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


```

修改后返回最新课程信息，采用与新增课程接口返回类型一致的数据模型。

接口定义如下：

```java
/**
    * @description 修改课程
    * @param editCourseDto
    * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
    * @author: woldier
    * @date: 2023/3/6 19:44
    */
    @ApiOperation("修改课程接口")
    @PutMapping("/course")
    public CourseBaseInfoDto ModifyCourseBase(@RequestBody @Validated({ValidationGroups.Update.class}) EditCourseDto editCourseDto){
        return null;
    }
```

#### 3.4.3 接口开发

##### 3.4.3.1 查询课程信息

Dao之前已生成无需开发。

查询课程信息的Service方法在新增课程接口开发中已实现，无需实现，如下：

```java
//根据课程id查询课程基本信息，包括基本信息和营销信息
public CourseBaseInfoDto getCourseBaseInfo(long courseId){

 CourseBase courseBase = courseBaseMapper.selectById(courseId);
 LambdaQueryWrapper<CourseMarket> queryWrapper = new LambdaQueryWrapper();
 queryWrapper.eq(CourseMarket::getCourseId,courseId);
 CourseMarket courseMarket = courseMarketMapper.selectOne(queryWrapper);

 if(courseBase == null || courseMarket == null){
  return null;
 }
 CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
 BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
 courseBaseInfoDto.setPrice(courseMarket.getPrice());
 courseBaseInfoDto.setCharge(courseMarket.getCharge());

 return courseBaseInfoDto;

}
```

需要将查询课程信息的方法提到接口上，这样在controller中通过接口调用此方法。

```java
  /**
     * @param courseId
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 根据id查询课程基本信息
     * @author: woldier
     * @date: 2023/3/6 20:13
     */
    CourseBaseInfoDto getCourseBaseInfo(Long courseId);
```

完善接口层代码 ：

```java
/**
     * @param courseId 课程id
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 根据id查询课程信息
     * @author: woldier
     * @date: 2023/3/6 19:27
     */
    @ApiOperation("查询单个课程接口")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseInfoById(@PathVariable Long courseId) {
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }
```

测试查询课程

用httpclient测试查询课程接口：

```http
GET /content/course/40
Content-Type: application/json
```

##### 3.4.3.2 修改课程信息

Dao之前已生成无需开发。

修改Service修改课程的接口与方法：

```java
/**
     * @param companyId     该课程所对应的公司id
     * @param editCourseDto 修改的课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 修改课程基本信息
     * @author: woldier
     * @date: 2023/3/6 20:19
     */
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto) throws XueChengPlusException;
```

实现方法如下：

```java
 /**
     * @param companyId     该课程所对应的公司id
     * @param editCourseDto 修改的课程信息
     * @return com.xuecheng.content.model.dto.CourseBaseInfoDto
     * @description 修改课程基本信息
     * @author: woldier
     * @date: 2023/3/6 20:19
     */
    @Override
    @Transactional
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto editCourseDto) throws XueChengPlusException {
        /*1.查询数据库 看是否存在课程*/
        /*得到id*/
        Long id = editCourseDto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(id);
        /*2.若为空抛出异常*/
        if(courseBase==null)
            XueChengPlusException.cast("课程不存在");
        /*2.1 不为空检查companyId是否一致,不一致抛出*/
        if(!courseBase.getCompanyId().equals(companyId))
            XueChengPlusException.cast("companyId是不一致");
        /*更新courseBase信息*/
        /*拷贝*/
        BeanUtils.copyProperties(editCourseDto,courseBase);

        courseBase.setChangeDate(LocalDateTime.now());
        courseBaseMapper.updateById(courseBase);

        /*更新营销信息*/
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(editCourseDto,courseMarket);
        courseMarket.setOriginalPrice(editCourseDto.getOriginalPrice().floatValue());
        courseMarket.setPrice(editCourseDto.getPrice().floatValue());
        checkCharge(courseMarket.getPrice(), editCourseDto.getCharge());
        courseMarketService.saveOrUpdate(courseMarket);

        /*查询封装返回*/
        return getCourseBaseInfo(editCourseDto.getId());


    }

private static void checkCharge(Float price, String charge) throws XueChengPlusException {
        if ("201001".equals(charge)) //如果为收费课程,扣费规则不能为空
            if (price == null || price < 0.0)
                //throw new RuntimeException("本课程为收费课程,但是价格为空");
                XueChengPlusException.cast("本课程为收费课程,但是价格为空");
    }
```

课程营销信息属于课程基本的附属信息，当添加课程、修改课程时对其进行保存，如果因为一些其它的原因课程基本信息存在而课程营销信息不存在，此时当修改课程基本信息时要可以将课程营销信息也保存到数据库。

如何实现保存课程营销信息，没有则添加，有则更新 ?

courseMarketMapper中定义了insert方法和update方法，这里需要判断决定调用哪一个方法。

Mybatis-plus框架封装了一个通用的service类，里边有一个saveOrUpdate方法即可实现该功能。

`CourseMarketServiceImpl`与 `CourseMarketService` 从generate中拷贝

![image-20230306205117232](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230306205117232.png)

#### 3.4.4 接口测试

接口开发完成进行测试，使用httpclient测试

```http
### 根据课程id查询课程信息
GET {{content_host}}/content/course/40
Content-Type: application/json
#响应结果
#{
#  "id": 40,
#  "companyId": 1232141425,
#  "companyName": null,
#  "name": "SpringBoot核心",
#  "users": "Spring Boot初学者",
#  "tags": "Spring项目的快速构建",
#  "mt": "1-3",
#  "mtName": null,
#  "st": "1-3-2",
#  "stName": null,
#  "grade": "200003",
#  "teachmode": "201001",
#  "description": "课程系统性地深度探讨 Spring Boot 核心特性，引导小伙伴对 Java 规范的重视，启发对技术原理性的思考，掌握排查问题的技能，以及学习阅读源码的方法和技巧，全面提升研发能力，进军架构师队伍。",
#  "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
#  "createDate": "2019-09-10 16:05:39",
#  "changeDate": "2022-09-09 07:27:48",
#  "createPeople": null,
#  "changePeople": null,
#  "auditStatus": "202004",
#  "status": "203001",
#  "coursePubId": 21,
#  "coursePubDate": null,
#  "charge": "201001",
#  "price": 0.01
#}

### 修改课程
PUT {{content_host}}/content/course
Content-Type: application/json

{
  "id": 40,
  "companyName": null,
  "name": "SpringBoot核心",
  "users": "Spring Boot初学者",
  "tags": "Spring项目的快速构建",
  "mt": "1-3",
  "st": "1-3-2",
  "grade": "200003",
  "teachmode": "201001",
  "description": "课程系统性地深度探讨 Spring Boot 核心特性，引导小伙伴对 Java 规范的重视，启发对技术原理性的思考，掌握排查问题的技能，以及学习阅读源码的方法和技巧，全面提升研发能力，进军架构师队伍。",
  "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
  "charge": "201001",
  "price": 0.01
}

###修改成功响应结果如下
#{
#  "id": 40,
#  "companyId": 1232141425,
#  "companyName": null,
#  "name": "SpringBoot核心",
#  "users": "Spring Boot初学者",
#  "tags": "Spring项目的快速构建",
#  "mt": "1-3",
#  "mtName": null,
#  "st": "1-3-2",
#  "stName": null,
#  "grade": "200003",
#  "teachmode": "201001",
#  "description": "课程系统性地深度探讨 Spring Boot 核心特性，引导小伙伴对 Java 规范的重视，启发对技术原理性的思考，掌握排查问题的技能，以及学习阅读源码的方法和技巧，全面提升研发能力，进军架构师队伍。",
#  "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
#  "createDate": "2019-09-10 16:05:39",
#  "changeDate": "2022-09-09 07:27:48",
#  "createPeople": null,
#  "changePeople": null,
#  "auditStatus": "202004",
#  "status": "203001",
#  "coursePubId": 21,
#  "coursePubDate": null,
#  "charge": "201001",
#  "price": 0.01
#}
```

前端开发完毕进行前后端接口联调。

过程略。

#### 3.4.5 代码优化

程序员写的代码不仅要完成功能实现，还要养成代码重构优化的习惯，这样久而久之在写代码的过程中就养成了代码抽取及封装的习惯。

下边举例说明：

上边修改课程接口中对课程营销信息进行保存，并且校验了课程营销信息的价格字段，在添加课程和修改课程时都对课程营销信息的价格进行校验、最后保存，这里就可以将课程营销信息的校验及保存进行抽取，如下：

```java
 private static void checkCharge(Float price, String charge) throws XueChengPlusException {
        if ("201001".equals(charge)) //如果为收费课程,扣费规则不能为空
            if (price == null || price < 0.0)
                //throw new RuntimeException("本课程为收费课程,但是价格为空");
                XueChengPlusException.cast("本课程为收费课程,但是价格为空");
    }
```



添加课程和修改课程时只需要准备好课程营销的数据库调用此抽取方法即可。

请自行修改添加课程和修改课程的代码，调用此抽取方法校验和保存课程营销信息。



### 3.5 查询课程计划

#### 3.5.1 需求分析

##### 3.5.1.1 业务流程

课程基本信息添加或修改成功将自动进入课程计划编辑器界面，如下图：

![image-20230307163447424](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307163447424.png)

课程计划即课程的大纲目录。

课程计划分为两级：章节和小节。

本小节完成课程计划信息的查询。

##### 3.5.1.2 数据模型

从课程计划查询界面上可以看出整体上是 一个树型结构，课程计划表teachplan如下：

![image-20230307163617470](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307163617470.png)

![image-20230307163815431](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307163815431.png)

每个课程计划都有所属课程。

每个课程的课程计划有两个级别，第一级为章，grade为1、第二级为小节，grade为2

3。第二级的parentid为第一级的id。

课程计划的显示顺序根据排序字段去显示。

根据业务流程中的界面原型，课程计划列表展示时还有课程计划关联的视频信息。

课程计划关联的视频信息在teachplan_media表，结构如下：

![image-20230307163747590](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307163747590.png)

两张表是一对一关系，每个课程计划只能在teachplan_media表中存在一个视频。

#### 3.5.2 接口定义

```http
GET /teachplan/22/tree-nodes

 [
      {
         "changeDate" : null,
         "courseId" : 74,
         "cousePubId" : null,
         "createDate" : null,
         "endTime" : null,
         "grade" : "2",
         "isPreview" : "0",
         "mediaType" : null,
         "orderby" : 1,
         "parentid" : 112,
         "pname" : "第1章基础知识",
         "startTime" : null,
         "status" : null,
         "id" : 113,
         "teachPlanTreeNodes" : [
            {
               "changeDate" : null,
               "courseId" : 74,
               "cousePubId" : null,
               "createDate" : null,
               "endTime" : null,
               "grade" : "3",
               "isPreview" : "1",
               "mediaType" : "001002",
               "orderby" : 1,
               "parentid" : 113,
               "pname" : "第1节项目概述",
               "startTime" : null,
               "status" : null,
               "id" : 115,
               "teachPlanTreeNodes" : null,
               "teachplanMedia" : {
                  "courseId" : 74,
                  "coursePubId" : null,
                  "mediaFilename" : "2.avi",
                  "mediaId" : 41,
                  "teachplanId" : 115,
                  "id" : null
               }
            }
         ],
         "teachplanMedia" : null
      },
      {
         "changeDate" : null,
         "courseId" : 74,
         "cousePubId" : null,
         "createDate" : null,
         "endTime" : null,
         "grade" : "2",
         "isPreview" : "0",
         "mediaType" : "",
         "orderby" : 1,
         "parentid" : 112,
         "pname" : "第2章快速入门",
         "startTime" : null,
         "status" : null,
         "id" : 242,
         "teachPlanTreeNodes" : [
            {
               "changeDate" : null,
               "courseId" : 74,
               "cousePubId" : null,
               "createDate" : null,
               "endTime" : null,
               "grade" : "3",
               "isPreview" : "1",
               "mediaType" : "001002",
               "orderby" : 2,
               "parentid" : 242,
               "pname" : "第1节搭建环境",
               "startTime" : null,
               "status" : null,
               "id" : 244,
               "teachPlanTreeNodes" : null,
               "teachplanMedia" : {
                  "courseId" : 74,
                  "coursePubId" : null,
                  "mediaFilename" : "3.avi",
                  "mediaId" : 42,
                  "teachplanId" : 244,
                  "id" : null
               }
            },
            {
               "changeDate" : null,
               "courseId" : 74,
               "cousePubId" : null,
               "createDate" : null,
               "endTime" : null,
               "grade" : "3",
               "isPreview" : "0",
               "mediaType" : "001002",
               "orderby" : 3,
               "parentid" : 242,
               "pname" : "第2节项目概述",
               "startTime" : null,
               "status" : null,
               "id" : 245,
               "teachPlanTreeNodes" : null,
               "teachplanMedia" : {
                  "courseId" : 74,
                  "coursePubId" : null,
                  "mediaFilename" : "1a.avi",
                  "mediaId" : 39,
                  "teachplanId" : 245,
                  "id" : null
               }
            }
         ],
         "teachplanMedia" : null
      }
   ]
```

查询课程计划的请求参数：课程id

响应结果需要自定义模型类：

```java
package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;

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

```

定义接口如下：

```java
package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.TeachplanDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程计划相关接口
 * @date 2023/3/7 17:28
 **/
@Api(value = "课程计划编辑接口",tags = "课程计划编辑接口")
@RestController
public class TeachplanController {
    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return null;
    }
}

```

#### 3.5.3 接口开发

##### 3.5.3.1 DAO开发

DAO接口使用sql查询课程计划，组成一个树型结构。

在TeachplanMapper自定义方法：

```java
@Mapper
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    /**
    * @description 根据课程id查询课程计划信息和课程媒体信息
    * @param courseId
    * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
    * @author: woldier
    * @date: 2023/3/7 17:50
    */
    List<TeachplanDto> selectTreeNodes(Long courseId);
}
```



定义mapper.xml中的sql语句，分析如下：

1、一级分类和二级分类通过teachplan表的自链接进行，如果只有一级分类其下边没有二级分类，此时也需要显示一级分类，这里使用左连接，左边是一级分类，右边是二级分类。

2、由于当还没有关联 视频时teachplan_media对应的记录为空，所以需要teachplan和teachplan_media左链接。

sql如下：

```xml
  <!-- 课程分类树型结构查询映射结果 -->
    <resultMap id="treeNodeResultMap" type="com.xuecheng.content.model.dto.TeachplanDto">
        <!-- 一级数据映射 -->
        <id column="one_id" property="id"/>
        <result column="one_pname" property="pname"/>
        <result column="one_parentid" property="parentid"/>
        <result column="one_grade" property="grade"/>
        <result column="one_mediaType" property="mediaType"/>
        <result column="one_stratTime" property="stratTime"/>
        <result column="one_endTime" property="endTime"/>
        <result column="one_orderby" property="orderby"/>
        <result column="one_courseId" property="courseId"/>
        <result column="one_coursePubId" property="coursePubId"/>
        <!-- 一级中包含多个二级数据 -->
        <collection property="teachPlanTreeNodes" ofType="com.xuecheng.content.model.dto.TeachplanDto">
            <!-- 二级数据映射 -->
            <id column="two_id" property="id"/>
            <result column="two_pname" property="pname"/>
            <result column="two_parentid" property="parentid"/>
            <result column="two_grade" property="grade"/>
            <result column="two_mediaType" property="mediaType"/>
            <result column="two_stratTime" property="stratTime"/>
            <result column="two_endTime" property="endTime"/>
            <result column="two_orderby" property="orderby"/>
            <result column="two_courseId" property="courseId"/>
            <result column="two_coursePubId" property="coursePubId"/>
            <!--    每一个二级课程计划对应一个媒资信息        -->
            <association property="teachplanMedia" javaType="com.xuecheng.content.model.po.TeachplanMedia">
                <result column="teachplanMeidaId" property="id"/>
                <result column="mediaFilename" property="mediaFilename"/>
                <result column="mediaId" property="mediaId"/>
                <result column="two_id" property="teachplanId"/>
                <result column="two_courseId" property="courseId"/>
                <result column="two_coursePubId" property="coursePubId"/>
            </association>
        </collection>
    </resultMap>

    <select id="selectTreeNodes" resultMap="treeNodeResultMap" parameterType="long">
        SELECT one.id            one_id,
               one.pname         one_pname,
               one.parentid      one_parentid,
               one.grade         one_grade,
               one.media_type    one_mediaType,
               one.start_time    one_startTime,
               one.end_time      one_endTime,
               one.orderby       one_orderby,
               one.course_id     one_courseId,
               one.course_pub_id one_coursePubId,
               two.id            two_id,
               two.pname         two_pname,
               two.parentid      two_parentid,
               two.grade         two_grade,
               two.media_type    two_mediaType,
               two.start_time    two_stratTime,
               two.end_time      two_endTime,
               two.orderby       two_orderby,
               two.course_id     two_courseId,
               two.course_pub_id two_coursePubId,
               m1.media_fileName mediaFilename,
               m1.id             teachplanMeidaId,
               m1.media_id       mediaId
        FROM teachplan one
                 LEFT JOIN teachplan two ON one.id = two.parentid
                 LEFT JOIN teachplan_media m1 on two.id = m1.teachplan_id
        WHERE one.grade="1" AND one.course_id = #{courseId}
        ORDER BY one.orderby, two.orderby
    </select>
```

单元测试方法

```java
package com.xuecheng.content;

import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 测试
 * @date 2023/3/7 17:54
 **/
@SpringBootTest

@Slf4j
public class TeachplanMapperTest {
    @Autowired
    private TeachplanMapper teachplanMapper;

    @Test
    public void test(){
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(117L);
        log.info(teachplanDtos.toString());
    }
}

```

##### 3.5.3.2 Service开发

```java
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

```

定义service接口实现类

```java
package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程计划 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements TeachplanService {
    /**
     * @description 根据课程id查询课程计划信息和课程媒体信息
     * @param courseId
     * @return java.util.List<com.xuecheng.content.model.dto.TeachplanDto>
     * @author: woldier
     * @date: 2023/3/7 17:50
     */
    @Override
    public List<TeachplanDto> selectTreeNodes(Long courseId) {
        return baseMapper.selectTreeNodes(courseId);
    }
}

```

##### 3.5.3.3 接口层代码完善

```java
private final TeachplanService teachplanService;
    @ApiOperation("查询课程计划树形结构")
    @ApiImplicitParam(value = "courseId",name = "课程Id",required = true,dataType = "Long",paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.selectTreeNodes(courseId);
    }
```

#### 3.5.4 接口测试

1、使用httpclient测试

找一个有课程计划的课程进行测试

```http
### 查询某个课程的课程计划

GET {{content_host}}/content/teachplan/74/tree-nodes
Content-Type: application/json
```

2、前后端联调

1）进入课程编辑页面

2）保存进入下一步

观察课程计划获取是否成功。

1）进入新增课程页面

2）新增课程成功，自动进入课程计划编辑界面。

由于是新增的课程，课程计划为空。

![image-20230307190712325](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307190712325.png)

### 3.6 新增/修改课程计划

#### 3.6.1 需求分析

##### 3.6.1.1 业务流程

1、进入课程计划界面

![image-20230307192728614](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307192728614.png)

2、点击“添加章”新增第一级课程计划。

新增成功自动刷新课程计划列表。

3、点击“添加小节”向某个第一级课程计划下添加小节。

新增成功自动刷新课程计划列表。

新增的课程计划自动排序到最后。

4、点击“章”、“节”的名称，可以修改名称、选择是否免费。

##### 3.6.1.2 数据模型

1、新增第一级课程计划

名称默认为：新章名称 [点击修改]

grade：1

orderby:  所属课程中同级别下排在最后

2、新增第二级课程计划

名称默认为：新小节名称 [点击修改]

grade：2

orderby:  所属课程计划中排在最后

3、修改第一级、第二级课程计划的名称，修改第二级课程计划是否免费

#### 3.6.2  接口定义



接口示例如下：

```http
### 新增课程计划--章,当grade为1时parentid为0
POST {{content_host}}/content/teachplan
Content-Type: application/json

{
  "courseId" : 74,
  "parentid": 0,
  "grade" : 1,
  "pname" : "新章名称 [点击修改]"
}
### 新增课程计划--节
POST {{content_host}}/content/teachplan
Content-Type: application/json

{
  "courseId" : 74,
  "parentid": 247,
  "grade" : 2,
  "pname" : "小节名称 [点击修改]"
}
```

同一个接口接收新增和修改两个业务请求，以是否传递课程计划id 来判断是新增还是修改。

如果传递了课程计划id说明当前是要修改该课程计划，否则是新增一个课程计划。

定义接收请求参数的数据模型类：

定义SaveTeachplanDto

```java
package com.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author woldier
 * @version 1.0
 * @description 新增/修改课程计划的请求参数dto
 * @date 2023/3/7 19:34
 **/
@Data
@ToString
public class SaveTeachplanDto {
    /***
     * 教学计划id
     */
    private Long id;

    /**
     * 课程计划名称
     */
    private String pname;

    /**
     * 课程计划父级Id
     */
    private Long parentid;

    /**
     * 层级，分为1、2、3级
     */
    private Integer grade;

    /**
     * 课程类型:1视频、2文档
     */
    private String mediaType;


    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 课程发布标识
     */
    private Long coursePubId;


    /**
     * 是否支持试学或预览（试看）
     */
    private String isPreview;
}

```

`com.xuecheng.content.api.TeachplanController`定义接口如下：

```java
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
        
    }
```

#### 3.6.3 接口开发

##### 3.6.3.1 DAO开发

根据业务的分析，DAO使用自动生成的mapper即可满足要求。

##### 3.6.3.2 Service开发

定义保存课程计划的Service接口。

```java
 /**
     * @param dto
     * @return void
     * @description 新增或者更新课程计划  通过判断dto中是否有id主键判断是新增还是更新
     * @author: woldier
     * @date: 2023/3/7 19:39
     */
    void saveOrUpdateTeachPlan(SaveTeachplanDto dto);
```

编写接口实现：

```java
@Override
    @Transactional
    public void saveOrUpdateTeachPlan(SaveTeachplanDto dto) {
        /*
        算法如下
        1. 检查dto中的课程计划id是否为null为空
        2. 若为空说明是新建,需要从数据库中查询当前节点应该排在第几位
        3. 若不为空说明是修改课程,那么我们直接保存数据库
         */
        Long id = dto.getId();
        Teachplan teachplan = new Teachplan();
        BeanUtils.copyProperties(dto,teachplan);
        if(id==null){
            /*新建*/
            /*数据库中查询当前节点应该排在第几位*/
            LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            /*计数与dto课程id相同且父节点相同的节点数目*/
            lambdaQueryWrapper.eq(Teachplan::getCourseId,dto.getCourseId())
                    .eq(Teachplan::getParentid,dto.getParentid());
            int count = this.count(lambdaQueryWrapper);

            /*设置排序号为总数加1*/
            //TODO 思考了下感觉这里有一个bug,就是如果我们先添加了10个节点(orderBy 从1-10),然后删除了5个现在节点总数为五,那么此时插入的这个节点orderBy应该为6 这就有一些小问题
            teachplan.setOrderby(count+1);
            this.save(teachplan);
        }else{
            /*更新*/
            this.updateById(teachplan);
        }
    }
```

##### 3.6.3.3 接口代码完善

```java
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
```

#### 3.6.4 接口测试

### 3.7 删除课程计划

#### 3.7.1 需求分析

##### 3.7.1.1 业务流程

在课程修改界面对章节或者小结点击删除

![image-20230307204936409](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307204936409.png)

##### 3.7.1.2 数据模型

1、删除第一级课程计划

id:

grade：1

删除以及课程计划的同时需要删除其下二级记录

2、删除第二级课程计划

id:

grade：2

orderby:  所属课程计划中排在最后

#### 3.7.2 接口定义

```http
### 课程计划删除--章
DELETE {{content_host}}/content/teachplan/{id}
Content-Type: application/json

### 课程计划删除--节
DELETE {{content_host}}/content/teachplan/123
Content-Type: application/json

```

#### 3.7.3 接口开发

##### 3.7.3.2 DAO开发

dao层使用原生

##### 3.7.3.3 Service开发

接口代码

```java
/**
     * @param id 课程计划id
     * @return void
     * @description 删除课程计划
     * @author: woldier
     * @date: 2023/3/7 21:05
     */
    void deleteTeachPlan(Long id) throws XueChengPlusException;

    void delete4Grade2(Long id);
```

impl代码

```java
 /**
     * @param id 课程计划id
     * @return void
     * @description 删除课程计划
     * @author: woldier
     * @date: 2023/3/7 21:05
     */
    @Override
    @Transactional
    public void deleteTeachPlan(Long id) throws XueChengPlusException {
        /*
         * 1.查询数据库中课程计划
         * 查看是否存在不存在抛出异常
         * 2.获取课程计划的等级
         * 若为章则递归删除其子节点
         * 若为节,直接删除,查询是否有媒体信息,有则删除
         *
         * */
        Teachplan teachplan = this.getById(id);
        if (teachplan == null)
            XueChengPlusException.cast("课程不存在");
        Integer grade = teachplan.getGrade();

        if (grade.equals(1)) {
            /*为章递归删除其子节点*/
            List<TeachplanDto> teachplanDtos = this.selectTreeNodes(teachplan.getCourseId());
            /*取出二级节点*/
            List<TeachplanDto> teachPlanTreeNodes = teachplanDtos.stream().filter(e -> e.getId().equals(teachplan.getId())).collect(Collectors.toList()).get(0).getTeachPlanTreeNodes();
            /*删除章*/
            this.removeById(id);
            /*删除节*/
            teachPlanTreeNodes.forEach(e->this.delete4Grade2(e.getId()));
        } else
            //delete4Grade2(id);
            /*事务传递*/
            this.delete4Grade2(id);

    }

    /**
     * @param id 二级课程id
     * @return
     * @description 根据二级课程的id 删除 仅仅二级使用
     * @author: woldier
     * @date: 2023/3/7 21:17
     */
    @Override
    @Transactional
    public void delete4Grade2(Long id) {
        this.removeById(id);
        LambdaQueryWrapper<TeachplanMedia> lb4Media = new LambdaQueryWrapper<>();
        lb4Media.eq(TeachplanMedia::getTeachplanId, id);
        /*若根据课程计划id查询到有数据,说明绑定了视频,进行删除*/
        if (teachplanMediaService.count(lb4Media) > 0)
            teachplanMediaService.remove(lb4Media);

    }
```







##### 3.7.3.4 接口完善

```java
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
```

#### 3.7.5 接口测试

![image-20230307214628665](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307214628665.png)

![image-20230307214640942](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307214640942.png)

![image-20230307214656604](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307214656604.png)

不过这里的章删除有bug,这是前端的代码有一些小bug,我们通过http测试即可

![image-20230307214751200](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307214751200.png)

点击删除章节的时候,id为undefined

现在重新添加一个小结,然后通过http进行删除

![image-20230307214900660](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307214900660.png)

可以通过控制台查看到章节3的id

![image-20230307215010408](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307215010408.png)

```http
### 课程计划删除--节
DELETE {{content_host}}/content/teachplan/280
Content-Type: application/json
```

再次刷新前端页面,已经成功删除

![image-20230307215210829](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230307215210829.png)



### 3.8 课程计划排序

#### 3.8.1 需求分析

##### 3.8.1.1 业务流程

在课程修改界面对章节或者小结点击上移下移

##### 3.8.1.2 数据模型

#### 3.8.2 接口定义

接口示例如下：

```http
### 课程计划上移
DELETE {{content_host}}/content/teachplan/{id}
Content-Type: application/json

### 课程计划删除--节
DELETE {{content_host}}/content/teachplan/123
Content-Type: application/json
```

#### 3.8.3 接口开发

##### 3.8.3.1 dao开发

到层使用原生mapper



##### 3.8.3.2 service开发

service层只开发一个方法，通过传入参数(Boolean)moveDown确实是下移还是上移(为true时表示下移)

`com.xuecheng.content.service.TeachplanService`

```java
 /**
    * @description 课程计划上下移动
    * @param id 课程计划id
     * @param moveDown   是否是下移 Ture 代表下移 ,False 代表上移
    * @return void
    * @author: woldier
    * @date: 2023/3/7 22:29
    */
    void move(Long id,Boolean moveDown) throws XueChengPlusException;
```



`com.xuecheng.content.service.impl.TeachplanServiceImpl`

```java
/**
     * @param id       课程计划id
     * @param moveDown 是否是下移 Ture 代表下移 ,False 代表上移
     * @return void
     * @description 课程计划上下移动
     * @author: woldier
     * @date: 2023/3/7 22:29
     */
    @Override
    public void move(Long id, Boolean moveDown) throws XueChengPlusException {
        /*
        1.根据id查询,若查询为空抛出异常
        2.根据查询得到的courseId以及grade查询到所有的计划根据moveDown选择Asc还是Desc 为True选择Desc
        3.通过filter进行流过滤,过滤条件是元素的order大于/小于本order ,moveDown为True时是大于,为False小于
        4.检查filter后收集的list是否为空empty说明无法移动抛出异常,不为空取出list尾部元素两者交换order然后更新
         */
        /*1.根据id查询,若查询为空抛出异常*/
        Teachplan teachplan = this.getById(id);
        if (teachplan == null) XueChengPlusException.cast("课程计划不存在");
        /*2.根据查询得到的courseId以及grade查询到所有的计划根据moveDown选择Asc还是Desc 为True选择Desc*/
        Long courseId = teachplan.getCourseId();
        Integer grade = teachplan.getGrade();
        Integer orderby = teachplan.getOrderby();
        LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(Teachplan::getCourseId, courseId)
                .eq(Teachplan::getGrade, grade);
        if (moveDown)
            lambdaQueryWrapper.orderByDesc(Teachplan::getOrderby);
        else
            lambdaQueryWrapper.orderByAsc(Teachplan::getOrderby);

        /*通过filter进行流过滤,过滤条件是元素的order大于/小于本order ,moveDown为True时是大于,为False小于 */
        List<Teachplan> teachplanList = this.list(lambdaQueryWrapper);
        List<Teachplan> teachplanList1 = teachplanList.stream().filter(e -> moveDown ? e.getOrderby() > orderby : e.getOrderby() < orderby).collect(Collectors.toList());
        /*检查filter后收集的list是否为空empty说明无法移动抛出异常,不为空取出list尾部元素两者交换order然后更新*/
        if (teachplanList1.isEmpty()) XueChengPlusException.cast("无法完成该操作");
        Teachplan teachplan1 = teachplanList1.get(teachplanList1.size()-1);
        teachplan.setOrderby(teachplan1.getOrderby());
        teachplan1.setOrderby(orderby);
        this.updateById(teachplan);
        this.updateById(teachplan1);

    }
```



##### 3.8.3.3 接口代码完善

controller层需要开发两个接口,两个接口都调用同一个service方法,通过moveDown参数标识上下移动

`com.xuecheng.content.api.TeachplanController`

```java
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
```

### 3.9 师资管理

#### 3.9.1 需求分析

##### 3.9.1.1 业务流程

1. 首先在第二步修改完课程计划信息会请求数据库获取课程教师信息

![image-20230308084605366](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308084605366.png)

2. 进入教师设置页面后可以点击修改教师信息,或者点击新增教师信息

这两个业务共用一个接口,通过查看是否传入id来进行区分

- 修改时如下

![image-20230308094018791](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308094018791.png)

- 新增时如下

![image-20230308093951166](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308093951166.png)

3. 课程教师信息删除

![image-20230308103041679](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308103041679.png)

##### 3.9.1.2 数据模型

所使用到的课程教师数据模型如下

![image-20230308084711671](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308084711671.png)

其po类如下:

`com.xuecheng.content.model.po.CourseTeacher`

```java
package com.xuecheng.content.model.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程-教师关系表
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("course_teacher")
public class CourseTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 教师标识
     */
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

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;


}

```



新增/修改是请求参数dto

```java
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

```



#### 3.9.2 接口定义

1. 根据课程id查询所有课程教师

```http
### 课程教师信息查询
GET {{content_host}}/content/courseTeacher/list/{courseId}
Content-Type: application/json

```

新建一个接口

```java
package com.xuecheng.content.api;

import com.xuecheng.content.model.po.CourseTeacher;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程教师相关接口
 * @date 2023/3/8 8:52
 **/
@RestController
@RequestMapping("/courseTeacher")
@RequiredArgsConstructor
public class CourseTeacherController {

    /**
    * @description 根据课程id查询教师
    * @param courseId  课程id
    * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
    * @author: woldier
    * @date: 2023/3/8 8:55
    */
    @ApiOperation("根据课程id查询课程教师信息")
    @GetMapping("/list/{courseId}")
    public List<CourseTeacher> list(@PathVariable @NotNull(message = "课程id不能为空") Long courseId){
        return null;
    }
}

```

2. 新增/修改课程教师信息

```http
### 新增/修改课程教师信息
POST {{content_host}}/content/courseTeacher/list/{courseId}
Content-Type: application/json

{
  "id": 1,
  "courseId": 72,
  "teacherName": "wangxu",
  "position": "java高级讲师",
  "introduction": "111111",
  "photograph": "http://r3zc5rung.hd-bkt.clouddn.com/2424e25d-b3ff-4ea2-92a5-249af918a42dGDSzBXIgWuwMCiZ4",
  "createDate": "2021-12-25 17:44:07"
}
```

```java
 /**
    * @description 新增/修改教师信息
    * @param dto  请求参数
    * @return void
    * @author: woldier
    * @date: 2023/3/8 9:48
    */
    @ApiOperation("新增/修改课程教师信息")
    @PostMapping("/courseTeacher")
    public void saveOrUpdate(@RequestBody @Validated TeacherSaveOrUpdateDto dto){

    }
```

3. 课程删除

```http
### 删除课程教师信息 后面分别对应{courseId}/{id}
DELETE {{content_host}}/content/courseTeacher/course/72/22
```



```java
/**
     * @param courseId 课程id
     * @param id       教师id
     * @return void
     * @description 删除课程教师
     * @author: woldier
     * @date: 2023/3/8 10:35
     */
    @ApiOperation("新增/修改课程教师信息")
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteById(@PathVariable Long courseId, @PathVariable Long id) {

    }
```



#### 3.9.3 接口开发

##### 3.9.3.1 DAO开发

直接使用原生mapper

#### 

##### 3.9.3.2 service开发

新建service

```java
package com.xuecheng.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.content.model.po.CourseTeacher;

/**
 * <p>
 * 课程-教师关系表 服务类
 * </p>
 *
 * @author itcast
 * @since 2023-02-16
 */
public interface CourseTeacherService extends IService<CourseTeacher> {

}
```

其实现类

```java
package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程-教师关系表 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class CourseTeacherServiceImpl extends ServiceImpl<CourseTeacherMapper, CourseTeacher> implements CourseTeacherService {

}

```

1. 根据课程id查询所有课程教师

接口

```java
/**
     * @param courseId 课程id
     * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
     * @description 根据课程id查询所有课程教师
     * @author: woldier
     * @date: 2023/3/8 9:15
     */
    List<CourseTeacher> listTeacherByCourseId(Long courseId) throws XueChengPlusException;
```

实现

```java
/**
     * @param courseId 课程id
     * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
     * @description 根据课程id查询所有课程教师
     * @author: woldier
     * @date: 2023/3/8 9:15
     */
    @Override
    public List<CourseTeacher> listTeacherByCourseId(Long courseId) throws XueChengPlusException {
        /*
        根据courseId查询数据库
         */
        if(courseId==null||courseId<0)
            XueChengPlusException.cast("课程id不合法");
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId);

        List<CourseTeacher> list = this.list(lambdaQueryWrapper);
        if(list.isEmpty()) return null;

        return list;
    }
```

2. 新增/修改课程教师

```java
 /**
     * @param dto
     * @return void
     * @description 新增/更新教师信息
     * @author: woldier
     * @date: 2023/3/8 9:56
     */
    void saveOrUpdateTeacher(TeacherSaveOrUpdateDto dto) throws XueChengPlusException;
```



```java

private final CourseBaseMapper courseBaseMapper;

/**
     * @param dto 传入的参数
     * @return void 返回
     * @description 新增/更新教师信息
     * @author: woldier
     * @date: 2023/3/8 9:56
     */
    @Override
    @Transactional
    public void saveOrUpdateTeacher(TeacherSaveOrUpdateDto dto) throws XueChengPlusException {
        /*
        1. 判断dto中是否有id
        2. 有表明是修改,先查询数据看,看对应id是否存在,不存在保存,存在则更新
        3. 无表明是新增,直接update
         */
        Long id = dto.getId();
        Long courseId = dto.getCourseId();
        /*创建数据库对象*/
        CourseTeacher courseTeacher = new CourseTeacher();
        BeanUtils.copyProperties(dto, courseTeacher);
        if (id != null) {
            /*修改*/
            LambdaQueryWrapper<CourseTeacher> q1 = new LambdaQueryWrapper<>();
            q1.eq(CourseTeacher::getId, id).eq(CourseTeacher::getCourseId, courseId);
            if (!this.update(courseTeacher, q1))
                XueChengPlusException.cast("非法更新");
        } else {
            /*新增*/
            if(courseBaseMapper.selectById(courseId)==null) XueChengPlusException.cast("当前课程不存在");

            courseTeacher.setCreateDate(LocalDateTime.now());
            if (!this.save(courseTeacher)) XueChengPlusException.cast("新增失败");


        }

    }
```

3. 课程教师删除

```java
/**
     * @param courseId 课程id
     * @param id       id
     * @return void
     * @description 删除课程教师
     * @author: woldier
     * @date: 2023/3/8 10:38
     */
    void deleteTeacher(Long courseId, Long id) throws XueChengPlusException;
```

```java
/**
     * @param courseId 课程id
     * @param id       id
     * @return void
     * @description 删除课程教师
     * @author: woldier
     * @date: 2023/3/8 10:38
     */
    @Override
    public void deleteTeacher(Long courseId, Long id) throws XueChengPlusException {
        /*
        1. 根据id与courseId删除数据
         */
        if (courseId == null || id == null)
            XueChengPlusException.cast("id或者课程id为空");

        /*查询课程id是否存在于数据库*/
        if (courseBaseMapper.selectById(id) == null)
            XueChengPlusException.cast("课程id不合法");


        LambdaQueryWrapper<CourseTeacher> q = new LambdaQueryWrapper<>();
        q.eq(CourseTeacher::getId, id).eq(CourseTeacher::getCourseId, courseId);
        if (!this.remove(q))
            XueChengPlusException.cast("数据库不存在对应数据");

    }
```



##### 3.9.3.3 接口代码完善

1. 查询课程教师

```java
private final CourseTeacherService courseTeacherService;

    /**
    * @description 根据课程id查询教师
    * @param courseId  课程id
    * @return java.util.List<com.xuecheng.content.model.po.CourseTeacher>
    * @author: woldier
    * @date: 2023/3/8 8:55
    */
    @ApiOperation("根据课程id查询课程教师信息")
    @GetMapping("/list/{courseId}")
    public List<CourseTeacher> list(@PathVariable @NotNull(message = "课程id不能为空") Long courseId) throws XueChengPlusException {
        return courseTeacherService.listTeacherByCourseId(courseId);
    }
```

2. 新增或者修改

```java
 /**
    * @description 新增/修改教师信息
    * @param dto  请求参数
    * @return void
    * @author: woldier
    * @date: 2023/3/8 9:48
    */
    @ApiOperation("新增/修改课程教师信息")
    @PostMapping("/courseTeacher")
    public void saveOrUpdate(@RequestBody @Validated TeacherSaveOrUpdateDto dto) throws XueChengPlusException {
        courseTeacherService.saveOrUpdateTeacher(dto);
    }
```



3. 课程教师删除

```java
/**
     * @param courseId 课程id
     * @param id       教师id
     * @return void
     * @description 删除课程教师
     * @author: woldier
     * @date: 2023/3/8 10:35
     */
    @ApiOperation("新增/修改课程教师信息")
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public void deleteById(@PathVariable Long courseId, @PathVariable Long id) throws XueChengPlusException {
        courseTeacherService.deleteTeacher(courseId,id);
    }
```



#### 3.9.4 接口测试

1. 查询教师信息

```http
GET http://localhost:63040/content/courseTeacher/list/72

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 08 Mar 2023 01:24:12 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "courseId": 72,
    "teacherName": "wangxu",
    "position": "java高级讲师",
    "introduction": "1111",
    "photograph": "http://r3zc5rung.hd-bkt.clouddn.com/2424e25d-b3ff-4ea2-92a5-249af918a42dGDSzBXIgWuwMCiZ4",
    "createDate": "2021-12-25 17:44:07"
  }
]
```



![image-20230308093010392](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308093010392.png)

2. 课程新增/修改测试

- 测试课程新增,并且给定一个courseId为不存在

- 测试课程新增,并且给定一个courseId为存在
- 测试课程修改,并且给定courseId为其他值
- 测试课程修改,并且给定courseId为正确值

3. 测试较为简单

主要是注意检查当给定不正确的courseId时是否会出现问题

### 3.10 删除课程

#### 3.10.1 需求分析

##### 3.10.1.1 业务流程

在课程管理界面,点击删除按钮删除课程

![image-20230308122407546](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308122407546.png)

![image-20230308122434405](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308122434405.png)

##### 3.10.1.2 数据模型

这里请求参数为path参数因此不需要额外的数据模型来接受数据

#### 3.10.2 接口定义

```http
### 删除课程
DELETE {{content_host}}/content//course/25
```

```java
/**
     * @param id 课程id
     * @return void
     * @description 根据课程id删除课程信息
     * @author: woldier
     * @date: 2023/3/8 13:19
     */
    @ApiOperation("删除课程")
    @DeleteMapping("/course/{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long id) throws XueChengPlusException {
        courseBaseInfoService.deleteCourseById(id);
    }
```



#### 3.10.3 接口开发

##### 3.10.3.1 DAO开发

dao层采用mp原生

##### 3.10.3.2 Service开发

`com.xuecheng.content.service.CourseBaseInfoService`

```java
/**
     * @param id
     * @return void
     * @description 根据courseId删除
     * @author: woldier
     * @date: 2023/3/8 13:24
     */
    void deleteCourseById(Long id) throws XueChengPlusException;
```

`com.xuecheng.content.service.impl.CourseBaseInfoServiceImpl`

```java
/**
     * @param id
     * @return void
     * @description 根据courseId删除
     * @author: woldier
     * @date: 2023/3/8 13:24
     */
    @Override
    @Transactional
    public void deleteCourseById(Long id) throws XueChengPlusException {
        /*
        0.验证此课程id是否合法
        1.根据id删除课程教师信息
        2.根据courseId删除课程计划信息
        2.1查询课程计划信息
        2.2调用删除章目录的服务
        3.删除课程营销信息和课程基本信息
         */
        /*获取课程基本信息*/
        CourseBase courseBase = courseBaseMapper.selectById(id);
        if (courseBase == null) XueChengPlusException.cast("非法课程id");
        /*1.根据id删除课程教师信息*/
        /*根据课程id查询课程教师*/
        LambdaQueryWrapper<CourseTeacher> teacherLambda = new LambdaQueryWrapper<>();
        teacherLambda.eq(CourseTeacher::getCourseId,id);
        List<CourseTeacher> courseTeacherList = courseTeacherService.list(teacherLambda);
        if (courseTeacherList!=null&&!courseTeacherList.isEmpty())
            /*list非空说明有教师数据,通过主键remove*/
            courseTeacherList.forEach(e->courseTeacherService.removeById(e.getId()));
        /*根据courseId删除课程计划信息*/
        List<TeachplanDto> teachplanDtos = teachplanService.selectTreeNodes(id);
        if(teachplanDtos!=null&&!teachplanDtos.isEmpty())
            teachplanDtos.forEach(e->{
                /*获取二级节点*/
                List<TeachplanDto> teachPlanTreeNodes = e.getTeachPlanTreeNodes();
                /*若二级节点不空,遍历删除*/
                if (teachPlanTreeNodes!=null&&!teachPlanTreeNodes.isEmpty())
                    teachPlanTreeNodes.forEach(kid -> teachplanService.removeById(kid.getId()));
                teachplanService.removeById(e.getId());
            });
        /*删除课程营销信息和课程基本信息*/
        LambdaQueryWrapper<CourseMarket> courseMarketLambda = new LambdaQueryWrapper<>();
        courseMarketLambda.eq(CourseMarket::getId,id);
        if(courseMarketService.count(courseMarketLambda)>0)
            courseMarketService.removeById(id);
        courseBaseMapper.deleteById(id);
    }
```



##### 3.10.3.3 接口代码完善

`com.xuecheng.content.api.CourseBaseInfoController`

```java
/**
     * @param id 课程id
     * @return void
     * @description 根据课程id删除课程信息
     * @author: woldier
     * @date: 2023/3/8 13:19
     */
    @ApiOperation("删除课程")
    @DeleteMapping("/course/{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long id) throws XueChengPlusException {
        courseBaseInfoService.deleteCourseById(id);
    }
```



#### 3.10.4 接口测试

![image-20230308141152109](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308141152109.png)

```http
DELETE http://localhost:63040/content/course/180

HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 08 Mar 2023 06:13:07 GMT
Connection: close

{
  "errMessage": "非法课程id"
}
```

http测试给出一个代码健壮性测试,测试服务的安全性



## 4.媒体资源管理模块



### 4.1 模块背景

#### 4.1.1 模块介绍

媒资管理系统是每个在线教育平台所必须具备的，查阅百度百科对它的定义如下：

媒体资源管理(Media Asset Management，MAM)系统是建立在多媒体、网络、数据库和数字存储等先进技术基础上的一个对各种媒体及内容(如视/音频资料、文本文件、图表等)进行数字化存储、管理以及应用的总体解决方案，包括数字媒体的采集、编目、管理、传输和编码转换等所有环节。其主要是满足[媒体](https://baike.baidu.com/item/媒体?fromModule=lemma_inlink)资源拥有者收集、保存、查找、编辑、发布各种信息的要求，为媒体资源的使用者提供访问内容的便捷方法，实现对媒体资源的高效管理，大幅度提高媒体资源的价值。

每个教学机构都可以在媒资系统管理自己的教学资源，包括：视频、教案等文件。

目前媒资管理的主要管理对象是视频、图片、文档等，包括：媒资文件的查询、文件上传、视频处理等。

媒资查询：教学机构查询自己所拥有的媒资信息。

文件上传：包括上传图片、上传文档、上传视频。

视频处理：视频上传成功，系统自动对视频进行编码处理。

文件删除：教学机构删除自己上传的媒资文件。



下图是课程编辑与发布的整体流程，通过下图可以看到媒资在整体流程的位置：

![image-20230308155249502](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155249502.png)

#### 4.1.2 业务流程

##### 4.1.2.1 上传图片

教学机构人员在课程信息编辑页面上传课程图片，课程图片统一记录在媒资管理系统。

下图是上传图片的界面：

![image-20230308155403825](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155403825.png)





##### 4.1.2.2 上传视频

1、教学机构人员进入媒资管理列表查询自己上传的媒资文件。点击“媒资管理”

![image-20230308155435711](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155435711.png)

进入媒资管理列表页面查询本机构上传的媒资文件

![image-20230308155458189](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155458189.png)

2、教育机构用户在"媒资管理"页面中点击 "上传视频" 按钮。

![image-20230308155511456](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155511456.png)

点击“上传视频”打开上传页面

![image-20230308155524527](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155524527.png)

3、选择要上传的文件，自动执行文件上传。

![image-20230308155535551](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155535551.png)



4、视频上传成功会自动处理，处理完成可以预览视频。

![image-20230308155548658](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155548658.png)

##### 4.1.2.3 处理视频

对需要转码处理的视频系统会自动对其处理，处理后生成视频的URL。

处理视频没有用户界面，完全是后台自动执行。

##### 4.1.2.4 审核媒资

1.运营用户登入运营平台并进入媒资管理页面，查找待审核媒资

![image-20230308155726262](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155726262.png)

2.点击列表中媒资名称链接，可预览该媒资，若是视频，则播放视频。

![image-20230308155744578](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155744578.png)

3.点击列表中某媒资后的"审核" 按钮，既完成媒资的审批过程。

![image-20230308155754729](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155754729.png)

点击“审核”，选择审核结果，输入审核意见。

##### 4.1.2.5 绑定媒资

课程计划创建好后需要绑定媒资文件，比如：如果课程计划绑定了视频文件，进入课程在线学习界面后点课程计划名称则在线播放视频。如下图：

![image-20230308155834440](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155834440.png)

如何将课程计划绑定媒资呢？

1.教育机构用户进入课程管理页面并编辑某一个课程，在"课程大纲"标签页的某一小节后可点击”添加视频“。

![image-20230308155850372](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155850372.png)

2.弹出添加视频对话框，可通过视频关键字搜索已审核通过的视频媒资。

![image-20230308155903451](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155903451.png)

3.选择视频媒资，点击提交按钮，完成课程计划绑定媒资流程。

![image-20230308155913858](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155913858.png)

课程计划关联视频后如下图：

![image-20230308155928055](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308155928055.png)



#### 4.1.3 数据模型

本模块媒资文件相关的数据表如下

![image-20230308160005238](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308160005238.png)

媒资文件表：存储文件信息，包括图片、视频、文档等。

media_process: 待处理视频表。

media_process_history: 视频处理历史表，记录已经处理成功的视频信息。

媒资文件与课程计划绑定关系表如下：

![image-20230308160025883](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308160025883.png)

### 4.2 环境搭建

#### 4.2.1 架构问题分析

当前要开发的是媒资管理服务，目前为止共三个三微服务：内容管理、系统管理、媒资管理，如下图：

![image-20230308162150573](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162150573.png)

后期还会添加更多的微服务，当前这种由前端直接请求微服务的方式存在弊端：

如果在前端对每个请求地址都配置绝对路径，非常不利于系统维护，比如下边代码中请求系统管理服务的地址使用的是localhost

![image-20230308162208097](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162208097.png)

当系统上线后这里需要改成公网的域名，如果这种地址非常多则非常麻烦。

基于这个问题可以采用网关来解决，如下图：

![image-20230308162218744](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162218744.png)

这样在前端的代码中只需要指定每个接口的相对路径，如下所示：

![image-20230308162239677](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162239677.png)

在前端代码的一个固定的地方在接口地址前统一加网关的地址，每个请求统一到网关，由网关将请求转发到具体的微服务。

为什么所有的请求先到网关呢？

有了网关就可以对请求进行路由，比如：可以根据请求路径路由、根据host地址路由等， 当微服务有多个实例时可以通过负载均衡算法进行路由，如下：

另外，网关还可以实现权限控制、限流等功能。

项目采用Spring Cloud Gateway作为网关，网关在请求路由时需要知道每个微服务实例的地址，项目使用Nacos作用服务发现中心和配置中心，整体的架构图如下：

![image-20230308162336839](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162336839.png)

流程如下：

1、微服务启动，将自己注册到Nacos，Nacos记录了各微服务实例的地址。

2、网关从Nacos读取服务列表，包括服务名称、服务地址等。

3、请求到达网关，网关将请求路由到具体的微服务。

要使用网关首先搭建Nacos，Nacos有两个作用：

1、服务发现中心。

微服务将自身注册至Nacos，网关从Nacos获取微服务列表。

2、配置中心。

微服务众多，它们的配置信息也非常复杂，为了提供系统的可维护性，微服务的配置信息统一在Nacos配置。



#### 4.2.2 搭建Nacos

##### 4.2.2.1 服务发现中心

根据上节讲解的网关的架构图，要使用网关首先搭建Nacos。

首先搭建Nacos服务发现中心。

在搭建Nacos服务发现中心之前需要搞清楚两个概念：namespace和group

namespace：用于区分环境、比如：开发环境、测试环境、生产环境。

group：用于区分项目，比如：xuecheng-plus项目、xuecheng2.0项目

首先在nacos配置namespace:

登录Centos，启动Naocs，使用sh /data/soft/restart.sh将自动启动Nacos。

访问：http://ip:8848/nacos/

账号密码：nacos/nacos

登录成功，点击左侧菜单“命名空间”进入命名空间管理界面，

![image-20230308162543252](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162543252.png)

点击“新建命名空间”，填写命名空间的相关信息。如下图：

![image-20230308162554064](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162554064.png)

使用相同的方法再创建“测试环境”、"生产环境"的命名空间。

![image-20230308162608472](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162608472.png)



![image-20230308162612150](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308162612150.png)

创建成功，如下图：

![image-20230308163911582](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308163911582.png)



首先完成各服务注册到Naocs，下边将内容管理服务注册到nacos中。

1) 在xuecheng-plus-parent中添加依赖管理 

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dependencies</artifactId>
    <version>${spring-cloud-alibaba.version}</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>

```

2. 在内容管理模块的接口工程、系统管理模块的接口工程中添加如下依赖

```xml
<dependency>
     <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>

```

3. 配置nacos的地址

在系统管理的接口工程的配置文件中配置如下信息：

```yaml
#微服务配置
spring:
  application:
    name: system-service
  cloud:
    nacos:
      server-addr: 192.168.101.65:8848
      discovery:
        namespace: dev
        group: xuecheng-plus-project

```

在内容管理的接口工程的配置文件中配置如下信息：

```yaml
spring:
  application:
    name: content-service
  cloud:
    nacos:
      server-addr: 192.168.101.65:8848
      discovery:
        namespace: dev
        group: xuecheng-plus-project
```

4. 重启内容管理服务、系统管理服务

待微服务启动成功，进入Nacos服务查看服务列表

![image-20230308164221455](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308164221455.png)

在 “开发环境” 命名空间下有两个服务实例。微服务在Nacos注册成功。

点击其它一个微服务的“详情”

![image-20230308164230992](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308164230992.png)

通过上图可以查看微服务实例的地址。



##### 4.2.2.2 配置中心

###### 4.2.2.2.1 配置三要素

搭建完成Nacos服务发现中心，下边搭建Nacos为配置中心，其目的就是通过Nacos去管理项目的所有配置。

先将项目中的配置文件分分类：

1、每个项目特有的配置

是指该配置只在有些项目中需要配置，或者该配置在每个项目中配置的值不同。

比如：spring.application.name每个项目都需要配置但值不一样，以及有些项目需要连接数据而有些项目不需要，有些项目需要配置消息队列而有些项目不需要。

2、项目所公用的配置

指在若干项目中配置内容相同的配置。比如：redis的配置，很多项目用的同一套redis服务所以配置也一样。

另外还需要知道nacos如何去定位一个具体的配置文件，即配置的三要素：namespace、group、dataid. 

1、通过namespace、group找到具体的环境和具体的项目。

2、通过dataid找到具体的配置文件，dataid有三部分组成，

比如：content-service-dev.yaml配置文件 由（content-service）-（dev）. (yaml)三部分组成

content-service：第一部分，它是在application.yaml中配置的应用名，即spring.application.name的值。

dev：第二部分，它是环境名，通过spring.profiles.active指定，

Yaml: 第三部分，它是配置文件 的后缀，目前nacos支持properties、yaml等格式类型，本项目选择yaml格式类型。

所以，如果我们要配置content-service工程的配置文件:

在开发环境中配置content-service-dev.yaml

在测试环境中配置content-service-test.yaml

在生产环境中配置content-service-prod.yaml



###### 4.2.2.2.2 配置content-service

下边以开发环境为例对content-service工程的配置文件进行配置，进入nacos，进入开发环境。

![image-20230308195023520](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308195023520.png)

点击 加号，添加一个配置

Data ID`content-service-dev.yaml`

Group`xuecheng-plus-project`

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://remote.centos.com:3307/xc_content?serverTimezone=UTC&userUnicode=true&useSSL=false&
    username: root
    password: 123456
```

注意添加的DataId一定要有后缀



![image-20230308195236964](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308195236964.png)

输入data id、group以及配置文件内容。

为什么没在nacos中配置下边的内容 ？

```yaml
spring:
  application:
    name: content-service

```

因为刚dataid第一部分就是spring.application.name，nacos 客户端要根据此值确定配置文件 名称，所以spring.application.name不在nacos中配置，而是要在工程的本地进行配置。

本地配置文件现在是application.yaml需要修改为bootstrap.yaml，因为SpringBoot读取配置文件 的顺序如下：

![image-20230308195407550](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308195407550.png)

所以在content-service工程 的test目录中添加bootstrap.yaml进行单元测试，内容如下：

```yaml
spring:
  application:
    name: content-service
  cloud:
    nacos:
      server-addr: remote.centos.com:8848
      discovery:
        namespace: dev #命名空间，可随意取与spring.profile不一定一致
        group: xuecheng-plus-project
      config:
        namespace: dev  #命名空间，可随意取与spring.profile不一定一致
        group: xuecheng-plus-project
        file-extension: yaml
        refresh-enabled: true

#profiles默认为dev
  profiles:
    active: dev

```

最后删除原来的application.yaml。

在内容管理模块的接口工程和service工程、系统管理的接口工程和service工程配置依赖：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>

```

配置完成，运行content-service工程 的单元测试文件，能否正常测试。

通过运行观察控制台打印出下边的信息：

```shell
[NacosRestTemplate.java:476] - HTTP method: POST, url: http://192.168.101.65:8848/nacos/v1/cs/configs/listener, body: {Listening-Configs=content-service.yaml?xuecheng-plus-project??dev?content-service-dev.yaml?xuecheng-plus-project?88459b1483b8381eccc2ef462bd59182?dev?content-service?xuecheng-plus-project??dev?, tenant=dev}
```



###### 4.2.2.2.3 配置content-api

以相同的方法配置content-api工程的配置文件，在nacos中的开发环境中配置content-api-dev.yaml，内容如下：

```yaml
server:
  servlet:
    context-path: /content # 服务访问根路径
  port: 63040
# 日志文件配置路径
logging:
  config: classpath:log4j2-dev.xml
# swagger 文档配置
swagger:
  title: "学成在线内容管理系统"
  description: "内容系统管理系统对课程相关信息进行业务管理数据"
  base-package: com.xuecheng.content
  enabled: true
  version: 1.0.0
```



![image-20230308214237689](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308214237689.png)

在content-api工程 的本地配置bootstrap.yaml，内容如下：

```yaml
#微服务配置
spring:
  application:
    name: content-api
  cloud:
    nacos:
      server-addr: remote.centos.com:8848
      discovery:
        namespace: dev #命名空间 (与profile.active有区别配置文件的拉取不是根据命名空间来拼接的)
        group: xuecheng-plus-project
      config:
        namespace: dev
        group: xuecheng-plus-project
        file-extension: yaml
        refresh-enabled: true
        extension-configs:
          - data-id: content-service-${spring.profiles.active}.yaml
            group: xuecheng-plus-project
            refresh: true
  profiles:
    active: dev


```

注意：因为api接口工程依赖了service工程 的jar，所以这里使用extension-configs扩展配置文件 的方式引用service工程所用到的配置文件。

如果添加多个扩展文件，继续在下添加即可，如下：

```yaml
extension-configs:
          - data-id: content-service-${spring.profiles.active}.yaml
            group: xuecheng-plus-project
            refresh: true
          - data-id: 填写文件 dataid
            group: xuecheng-plus-project
            refresh: true        

```

启动content-api工程，查询控制台是否打印出了请求nacos的日志，如下:

```shell
[NacosRestTemplate.java:476] - HTTP method: POST, url: http://192.168.101.65:8848/nacos/v1/cs/configs/listener
```

并使用Httpclient测试课程查询接口是否可以正常查询。

##### 4.2.2.3 公用配置

还有一个优化的点是如何在nacos中配置项目的公用配置呢？

nacos提供了shared-configs可以引入公用配置。

在content-api中配置了swagger，所有的接口工程 都需要配置swagger，这里就可以将swagger的配置定义为一个公用配置，哪个项目用引入即可。

单独在xuecheng-plus-common分组下创建xuecheng-plus的公用配置，进入nacos的开发环境，添加swagger-dev.yaml公用配置

![image-20230308215015982](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308215015982.png)

删除`content-api-dev.yaml`中对swagger的配置。

![image-20230308215550366](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230308215550366.png)

项目使用shared-configs可以引入公用配置。在接口工程的本地配置文件 中引入公用配置(其他配置类似,这里忽略了log4j的配置过程)，如下：

```yaml
#微服务配置
spring:
  application:
    name: content-api
  cloud:
    nacos:
      server-addr: remote.centos.com:8848
      discovery:
        namespace: dev #命名空间 (与profile.active有区别配置文件的拉取不是根据命名空间来拼接的)
        group: xuecheng-plus-project
      config:
        namespace: dev
        group: xuecheng-plus-project
        file-extension: yaml
        refresh-enabled: true
        extension-configs:
          - data-id: content-service-${spring.profiles.active}.yaml
            group: xuecheng-plus-project
            refresh: true
        shared-configs:
          - data-id: swagger-${spring.profiles.active}.yaml
            group: xuecheng-plus-common
            refresh: true
          - data-id: logging-${spring.profiles.active}.yaml
            group: xuecheng-plus-common
            refresh: true

  profiles:
    active: dev


```

配置完成，重启content-api接口工程，访问http://localhost:63040/content/swagger-ui.html 查看swagger接口文档是否可以正常访问，查看控制台log4j2日志输出是否正常。

##### 4.2.2.4 系统管理配置

按照上边的方法 自行将系统管理服务的配置信息在nacos上进行配置。

##### 4.2.2.5 配置优先级

到目前为止已将所有微服务的配置统一在nacos进行配置，用到的配置文件有本地的配置文件 bootstrap.yaml和nacos上的配置文件，引入配置文件的形式有：

1、通过dataid方式引入

2、以扩展配置文件方式引入

3、以共享配置文件 方式引入

下边测试这几种配置文件方式的优先级。

我们使用内容管理服务中的配置文件，首先在共享配置文件 swagger-dev.yaml中配置四个配置项，如下：

![image-20230309134210974](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309134210974.png)

配置完成发布。

下边在content-api工程的启动类中添加如下代码读取这四个配置项的值

```java
public class ContentApplication {

   @Value("${test_config.a}")
   String a;
   @Value("${test_config.b}")
   String b;
   @Value("${test_config.c}")
   String c;
   @Value("${test_config.d}")
   String d;

   @Bean
   public Integer getTest(){
      System.out.println("a="+a);
      System.out.println("b="+b);
      System.out.println("c="+c);
      System.out.println("d="+d);
      return new Integer(1);
   }

   public static void main(String[] args) {
      SpringApplication.run(ContentApplication.class, args);
   }


}

```

启动content-api工程，在return new Integer(1);处打断点，运行到断点处，如下：

![image-20230309134251802](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309134251802.png)



这说明已经成功读取到 四个配置项的值。

下边在content-api工程的扩展配置文件 conent-service-dev.yaml中配置三个配置项，如下：

![image-20230309135407678](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309135407678.png)

再次重启content-api工程，在return new Integer(1);处打断点，运行到断点处，如下：

![image-20230309135419829](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309135419829.png)

从结果可以看出，扩展配置文件比共享配置文件优先级高。

 

下边继续content-api-dev.yaml中配置两个配置项，如下：

```yaml
test_config:
  a: 4a
  b: 4b
  c: 4c
  d: 4d

```

再次重启内容管理接口工程，在return new Integer(1);处打断点，运行到断点处，如下：

![image-20230309135654218](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309135654218.png)

这说明本地配置文件配置的内容没有起作用，原因是nacos配置文件中的相同的配置项覆盖了本地的配置项。

到这可以总结各各配置文件 的优先级：项目应用名配置文件 > 扩展配置文件 > 共享配置文件 > 本地配置文件。

有时候我们在测试程序时直接在本地加一个配置进行测试，这时我们想让本地最优先，可以在nacos配置文件 中配置如下即可实现：

```yaml
spring:
 cloud:
  config:
    override-none: true

```

再次重启content-api工程，在return new Integer(1);处打断点，运行到断点处，如下：

![image-20230309135741633](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309135741633.png)

可以看出此时本地配置最优先

除此之外,我们一般不会带本地配置写入到bootstrap.yml中而是通过jvm参数

如我们要启动两个服务进行调试,那么端口号就不能一致,因此我们可以通过添加jvm参数指定端口号:

```shell
-Dserver.port=8088
```

如我们现在向启动两个content服务

![image-20230309145108805](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309145108805.png)

![image-20230309145353006](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309145353006.png)

![image-20230309145419800](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309145419800.png)

![image-20230309145613692](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309145613692.png)



##### 4.2.2.6 导入配置文件

课程资料中提供了系统用的所有配置文件nacos_config_export.zip，下边将nacos_config_export.zip导入nacos。

进入具体的命名空间，点击“导入配置”

![image-20230309140250256](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309140250256.png)

打开导入窗口

![image-20230309140325306](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309140325306.png)

相同的配置跳过覆盖配置。

点击“上传文件”选择nacos_config_export.zip开始导入。



#### 4.2.3 搭建Gateway

本项目使用Spring Cloud Gateway作为网关，下边创建网关工程。

新建一个网关工程。

![image-20230309141037907](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309141037907.png)

项目结构如下

![image-20230309141934603](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309141934603.png)

添加依赖：

```xml
<dependencies>
    <!--网关-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    <!--服务发现中心-->
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    </dependency>
     <!-- 排除 Spring Boot 依赖的日志包冲突 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <!-- Spring Boot 集成 log4j2 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>


</dependencies>


```

配置网关的bootstrap.yaml配置文件

```yaml
#微服务配置
spring:
  application:
    name: gateway
  cloud:
    nacos:
      server-addr: 192.168.101.65:8848
      discovery:
        namespace: ${spring.profiles.active}
        group: xuecheng-plus-project
      config:
        namespace: ${spring.profiles.active}
        group: xuecheng-plus-project
        file-extension: yaml
        refresh-enabled: true
        shared-configs:
          - data-id: logging-${spring.profiles.active}.yaml
            group: xuecheng-plus-common
            refresh: true
  profiles:
    active: dev

```

在nacos上配置网关路由策略：

![image-20230309141606145](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309141606145.png)

```yaml
server:
  port: 63010 # 网关端口
spring:
  cloud:
    gateway:
#      filter:
#        strip-prefix:
#          enabled: true
      routes: # 网关路由配置
        - id: content-api # 路由id，自定义，只要唯一即可
          # uri: http://127.0.0.1:8081 # 路由的目标地址 http就是固定地址
          uri: lb://content-api # 路由的目标地址 lb就是负载均衡，后面跟服务名称
          predicates: # 路由断言，也就是判断请求是否符合路由规则的条件
            - Path=/content/** # 这个是按照路径匹配，只要以/content/开头就符合要求
#          filters:
#            - StripPrefix=1
        - id: system-api
          # uri: http://127.0.0.1:8081
          uri: lb://system-api
          predicates:
            - Path=/system/**
#          filters:
#            - StripPrefix=1
        - id: media-api
          # uri: http://127.0.0.1:8081
          uri: lb://media-api
          predicates:
            - Path=/media/**
#          filters:
#            - StripPrefix=1
        - id: search-service
          # uri: http://127.0.0.1:8081
          uri: lb://search
          predicates:
            - Path=/search/**
#          filters:
#            - StripPrefix=1
        - id: auth-service
          # uri: http://127.0.0.1:8081
          uri: lb://auth-service
          predicates:
            - Path=/auth/**
#          filters:
#            - StripPrefix=1
        - id: checkcode
          # uri: http://127.0.0.1:8081
          uri: lb://checkcode
          predicates:
            - Path=/checkcode/**
#          filters:
#            - StripPrefix=1
        - id: learning-api
          # uri: http://127.0.0.1:8081
          uri: lb://learning-api
          predicates:
            - Path=/learning/**
#          filters:
#            - StripPrefix=1
        - id: orders-api
          # uri: http://127.0.0.1:8081
          uri: lb://orders-api
          predicates:
            - Path=/orders/**
#          filters:
#            - StripPrefix=1
```

启动网关工程，通过网关工程访问微服务进行测试。

在http-client-env.json中配置网关的地址

```http
### 查询所有
#POST {{content_host}}/content/course/list?pageNo=1&pageSize=2
POST {{gateway_host}}/content/course/list?pageNo=1&pageSize=2
Content-Type: application/json

{
  "auditStatus": null,
  "courseName": "java",
  "publishStatus": null
}

```

```shell
POST http://localhost:63010/content/course/list?pageNo=1&pageSize=2

HTTP/1.1 200 OK
transfer-encoding: chunked
Content-Type: application/json
Date: Thu, 09 Mar 2023 06:21:44 GMT

{
  "items": [
    {
      "id": 1,
      "companyId": 1232141425,
      "companyName": null,
      "name": "JAVA8/9/10新特性讲解",
      "users": "java爱好者,有一定java基础",
      "tags": "有个java 版本变化的新内容，帮助大家使用最新的思想和工具",
      "mt": "1",
      "st": "1-3-2",
      "grade": "204002",
      "teachmode": "200002",
      "description": null,
      "pic": "https://cdn.educba.com/academy/wp-content/uploads/2018/08/Spring-BOOT-Interview-questions.jpg",
      "createDate": "2019-09-03 17:48:19",
      "changeDate": "2023-03-08 12:16:43",
      "createPeople": "1",
      "changePeople": null,
      "auditStatus": "202004",
      "status": "203001"
    },
    {
      "id": 18,
      "companyId": 1232141425,
      "companyName": null,
      "name": "java零基础入门",
      "users": "java小白java小白java小白java小白",
      "tags": "aa",
      "mt": "1-3",
      "st": "1-3-2",
      "grade": "200001",
      "teachmode": "200002",
      "description": "java零基础入门java零基础入门java零基础入门java零基础入门",
      "pic": "/mediafiles/2022/09/13/a16da7a132559daf9e1193166b3e7f52.jpg",
      "createDate": "2019-09-04 09:56:19",
      "changeDate": "2022-09-15 17:43:18",
      "createPeople": null,
      "changePeople": null,
      "auditStatus": "202004",
      "status": "203001"
    }
  ],
  "counts": 6,
  "page": 1,
  "pageSize": 2
}
```

网关工程搭建完成即可将前端工程中的接口地址改为网关的地址

![image-20230309142633578](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309142633578.png)

启动前端工程，测试之前开发内容管理模块的功能。

![image-20230309142650735](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309142650735.png)



#### 4.2.4 搭建媒资工程

至此网关、Nacos已经搭建完成，下边将媒资工程导入项目。

从课程资料中获取媒资工程 xuecheng-plus-media，拷贝到项目工程根目录。

右键pom.xml转为maven工程。

![image-20230309142937651](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309142937651.png)

创建媒资数据库，并导入xcplus_media.sql

![image-20230309145949422](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309145949422.png)

重启media-api工程。





### 4.3 分布式文件系统

#### 4.3.1 什么是分布式文件系统

要理解分布式文件系统首先了解什么是文件系统。

查阅百度百科：

![image-20230309161232945](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309161232945.png)

文件系统是负责管理和存储文件的系统软件，操作系统通过文件系统提供的接口去存取文件，用户通过操作系统访问磁盘上的文件。

下图指示了文件系统所处的位置：

![image-20230309161245621](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309161245621.png)

常见的文件系统：FAT16/FAT32、NTFS、HFS、UFS、APFS、XFS、Ext4等 。

现在有个问题，一此短视频平台拥有大量的视频、图片，这些视频文件、图片文件该如何存

储呢？如何存储可以满足互联网上海量用户的浏览。

今天讲的分布式文件系统就是海量用户查阅海量文件的方案。

我们阅读百度百科去理解分布式文件系统的定义：

![image-20230309161304244](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309161304244.png)

通过概念可以简单理解为：一个计算机无法存储海量的文件，通过网络将若干计算机组织起来共同去存储海量的文件，去接收海量用户的请求，这些组织起来的计算机通过网络进行通信，如下图：

![image-20230309161315754](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309161315754.png)



好处：

1、一台计算机的文件系统处理能力扩充到多台计算机同时处理。

 2、一台计算机挂了还有另外副本计算机提供数据。

 3、每台计算机可以放在不同的地域，这样用户就可以就近访问，提高访问速度。

市面上有哪些分布式文件系统的产品呢？

1、NFS

阅读百度百科：

![image-20230309162416128](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309162416128.png)

![image-20230309162420197](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309162420197.png)



特点：

1）在客户端上映射NFS服务器的驱动器。

2）客户端通过网络访问NFS服务器的硬盘完全透明。

2、GFS

![image-20230309162441038](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309162441038.png)

![image-20230309162447188](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309162447188.png)

1）GFS采用主从结构，一个GFS集群由一个master和大量的chunkserver组成。

2）master存储了数据文件的元数据，一个文件被分成了若干块存储在多个chunkserver中。

3）用户从master中获取数据元信息，向chunkserver存储数据。

3) HDFS

HDFS，是Hadoop Distributed File System的简称，是Hadoop抽象文件系统的一种实现。HDFS是一个高度容错性的系统，适合部署在廉价的机器上。HDFS能提供高吞吐量的数据访问，非常适合大规模数据集上的应用。 HDFS的文件分布在集群机器上，同时提供副本进行容错及可靠性保证。例如客户端写入读取文件的直接操作都是分布在集群各个机器上的，没有单点性能压力。

下图是HDFS的架构图：

![image-20230309162521420](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309162521420.png)

1）HDFS采用主从结构，一个HDFS集群由一个名称结点和若干数据结点组成。

2) 名称结点存储数据的元信息，一个完整的数据文件分成若干块存储在数据结点。

3）客户端从名称结点获取数据的元信息及数据分块的信息，得到信息客户端即可从数据块来存取数据。



**4****、云计算厂家**

阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。其数据设计持久性不低于 99.9999999999%（12 个 9），服务设计可用性（或业务连续性）不低于 99.995%。

*官方网站：*[*https://www.aliyun.com/product/oss*](https://www.aliyun.com/product/oss) 

百度对象存储BOS提供稳定、安全、高效、高可扩展的云存储服务。您可以将任意数量和形式的非结构化数据存入BOS，并对数据进行管理和处理。BOS支持标准、低频、冷和归档存

储等多种存储类型，满足多场景的存储需求。 

*官方网站：*[*https://cloud.baidu.com/product/bos.html*](https://cloud.baidu.com/product/bos.html) 



#### 4.3.2 MinIO

##### 4.3.2.1 介绍

本项目采用MinIO构建分布式文件系统，MinIO 是一个非常轻量的服务,可以很简单的和其他应用的结合使用，它兼容亚马逊 S3 云存储服务接口，非常适合于存储大容量非结构化的数据，例如图片、视频、日志文件、备份数据和容器/虚拟机镜像等。

它一大特点就是轻量，使用简单，功能强大，支持各种平台，单个文件最大5TB，兼容 Amazon S3接口，提供了 Java、Python、GO等多版本SDK支持。

官网：https://min.io

中文：https://www.minio.org.cn/，http://docs.minio.org.cn/docs/



MinIO集群采用去中心化共享架构，每个结点是对等关系，通过Nginx可对MinIO进行负载均衡访问。

去中心化有什么好处？

在大数据领域，通常的设计理念都是无中心和分布式。Minio分布式模式可以帮助你搭建一个高可用的对象存储服务，你可以使用这些存储设备，而不用考虑其真实物理位置。

它将分布在不同服务器上的多块硬盘组成一个对象存储服务。由于硬盘分布在不同的节点上，分布式Minio避免了单点故障。如下图：

![image-20230309164159781](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309164159781.png)

Minio使用纠删码技术来保护数据，它是一种恢复丢失和损坏数据的数学算法，它将数据分块冗余的分散存储在各各节点的磁盘上，所有的可用磁盘组成一个集合，上图由8块硬盘组成一个集合，当上传一个文件时会通过纠删码算法计算对文件进行分块存储，除了将文件本身分成4个数据块，还会生成4个校验块，数据块和校验块会分散的存储在这8块硬盘上。

使用纠删码的好处是即便丢失一半数量（N/2）的硬盘，仍然可以恢复数据。 比如上边集合中有4个以内的硬盘损害仍可保证数据恢复，不影响上传和下载，如果多于一半的硬盘坏了则无法恢复。



##### 4.3.2.2 数据恢复演示



下边在本机演示MinIO恢复数据的过程，在本地创建4个目录表示4个硬盘。

![image-20230309164502100](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309164502100.png)

首先下载MinIO，下载地址：https://dl.min.io/server/minio/release/，也可从课程资料找到MinIO的安装文件。

CMD进入有minio.exe的目录，运行下边的命令：

![image-20230309170211322](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309170211322.png)

```
minio.exe server D:\minIO\data1  D:\minIO\data2  D:\minIO\data3  D:\minIO\data4
```

```shell
WARNING: MINIO_ACCESS_KEY and MINIO_SECRET_KEY are deprecated.
         Please use MINIO_ROOT_USER and MINIO_ROOT_PASSWORD
Formatting 1st pool, 1 set(s), 4 drives per set.
WARNING: Host local has more than 2 drives of set. A host failure will result in data becoming unavailable.
WARNING: Detected default credentials 'minioadmin:minioadmin', we recommend that you change these values with 'MINIO_ROOT_USER' and 'MINIO_ROOT_PASSWORD' environment variables

```

1）老版本使用的MINIO_ACCESS_KEY 和 MINIO_SECRET_KEY不推荐使用，推荐使用MINIO_ROOT_USER 和MINIO_ROOT_PASSWORD设置账号和密码。

2）pool即minio节点组成的池子，当前有一个pool和4个硬盘组成的set集合

3）因为集合是4个硬盘，大于2的硬盘损坏数据将无法恢复。

4）账号和密码默认为minioadmin、minioadmin，可以在环境变量中设置通过

'MINIO_ROOT_USER' and 'MINIO_ROOT_PASSWORD' 进行设置。

下边输入http://localhost:9000进行登录。

![image-20230309170245340](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309170245340.png)

下一步创建bucket，桶，它相当于存储文件的目录，可以创建若干的桶。

![image-20230309170334197](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309170334197.png)

![image-20230309170407052](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309170407052.png)

输入bucket的名称，点击“CreateBucket”，创建成功

![image-20230309170437357](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309170437357.png)

点击“upload”上传文件。

![image-20230309170548656](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309170548656.png)

![image-20230309170613277](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309170613277.png)

下边去四个目录观察文件的存储情况

![image-20230309172843289](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309172843289.png)



我们发现上传的1.mp4文件存储在了四个目录，即四个硬盘上。

下边测试minio的数据恢复过程：

1、首先删除一个目录。

删除目录后仍然可以在web控制台上传文件和下载文件。

稍等片刻删除的目录自动恢复。

2、删除两个目录。

删除两个目录也会自动恢复。

3、删除三个目录 。

由于 集合中共有4块硬盘，有大于一半的硬盘损坏数据无法恢复。

此时报错：We encountered an internal error, please try again. (Read failed. Insufficient number of drives online)在线驱动器数量不足。





##### 4.3.2.3 分布式集群测试

条件允许的情况下可以测试MinIO分布式存储的特性，首先准备环境。

分布式MinIO要求至少四个磁盘，建议至少4个节点，每个节点2个磁盘。

准备四台虚拟机：192.168.101.65、192.168.101.66、192.168.101.67、192.168.101.68

将课程资料下的minio的执行文件拷贝到四台虚拟机的/home/minio/目录下。

在四台虚拟机分别创建下边的脚本run.sh，内容如下：

```shell
#!/bin/bash
# 创建日志目录
mkdir -p /boot/mediafiles/logs
# 创建存储目录
mkdir -p /boot/mediafiles/data/d{1,2,3,4}
# 创建配置目录
mkdir -p /etc/minio
export MINIO_ROOT_USER=minioadmin
export MINIO_ROOT_PASSWORD=minioadmin

# 在四台机器上都执行该文件，以分布式的方式启动minio
# --address 为api端口（如Java客户端）访问的端口
# --console-address web控制台端口
/home/minio/minio server \
http://192.168.101.65:9000/home/mediafiles/data/export1 \
http://192.168.101.65:9000/home/mediafiles/data/export2 \
http://192.168.101.66:9000/home/mediafiles/data/export1 \
http://192.168.101.66:9000/home/mediafiles/data/export2 \
http://192.168.101.67:9000/home/mediafiles/data/export1 \
http://192.168.101.67:9000/home/mediafiles/data/export2 \
http://192.168.101.68:9000/home/mediafiles/data/export1 \
http://192.168.101.68:9000/home/mediafiles/data/export2 

```

访问任意一个都可以操作 minio集群。

下边进行测试：

1、向集群上传一个文件，观察每个节点的两个磁盘目录都存储了数据。

2、停止 一个节点，不影响上传和下载。

假如停止了65节点，通过其它节点上传文件，稍后启动65后自动从其它结点同步文件。

3、停止 两个节点，无法上传，可以下载。

此时上传文件客户端报错如下：

![image-20230309173209233](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309173209233.png)

上传文件需要至少一半加1个可用的磁盘。

将停止的两个节点的minio启动，稍等片刻 minio恢复可用。

![image-20230309173224820](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309173224820.png)

##### 4.3.2.4 Docker集群环境





##### 4.3.2.5 SDK

本项目创建两个buckets：(设置privacy 为public)

mediafiles： 普通文件

video：视频文件

![image-20230309173729297](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309173729297.png)

MinIO提供多个语言版本SDK的支持，下边找到java版本的文档：

地址：https://docs.min.io/docs/java-client-quickstart-guide.html

最低需求Java 1.8或更高版本:

maven依赖如下：

```xml
<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>8.4.3</version>
</dependency>
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
    <version>4.8.1</version>
</dependency>

```

在`media-service`工程添加此依赖。

示例代码如下：

参数说明：

需要三个参数才能连接到minio服务。

| 参数       | 说明                                      |
| ---------- | ----------------------------------------- |
| endpoint   | 对象存储的URL                             |
| Access Key | Access Key就像用户id,可以唯一标识你的账户 |
| Secret Key | Secret key是你账户的密码                  |



```java
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
public class FileUploader {
  public static void main(String[] args)throws IOException, NoSuchAlgorithmException, InvalidKeyException {
    try {
      // Create a minioClient with the MinIO server playground, its access key and secret key.
      MinioClient minioClient =
          MinioClient.builder()
              .endpoint("https://play.min.io")
              .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
              .build();
      // Make 'asiatrip' bucket if not exist.
      boolean found =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket("asiatrip").build());
      if (!found) {
        // Make a new bucket called 'asiatrip'.
        minioClient.makeBucket(MakeBucketArgs.builder().bucket("asiatrip").build());
      } else {
        System.out.println("Bucket 'asiatrip' already exists.");
      }
      // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
      // 'asiatrip'.
      minioClient.uploadObject(
          UploadObjectArgs.builder()
              .bucket("asiatrip")
              .object("asiaphotos-2015.zip")
              .filename("/home/user/Photos/asiaphotos.zip")
              .build());
      System.out.println(
          "'/home/user/Photos/asiaphotos.zip' is successfully uploaded as "
              + "object 'asiaphotos-2015.zip' to bucket 'asiatrip'.");
    } catch (MinioException e) {
      System.out.println("Error occurred: " + e);
      System.out.println("HTTP trace: " + e.httpTrace());
    }
  }
}

```

参考上面示例在media-service工程中编写代码测试上传文件功能

###### 4.3.2.5.1 上传文件

在media-service test目录下创建SpringBoot启动类

```java
package com.xuecheng.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author woldier
 * @version 1.0
 * @description media -service Test启动类
 * @date 2023/3/9 19:05
 **/
@SpringBootApplication
public class MediaServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(MediaServiceApp.class,args);
    }
}

```

上传的同时我们最好把contentType传入进去,因此要使用到一个工具类`com.j256.simplemagic.ContentInfoUtil`

此工具类需要导入依赖

```xml
 <dependency>
            <groupId>com.j256.simplemagic</groupId>
            <artifactId>simplemagic</artifactId>
        </dependency>
```



```java
package com.xuecheng.media;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

/**
 * @author woldier
 * @version 1.0
 * @description MinIO上传测试
 * @date 2023/3/9 18:51
 **/
@SpringBootTest()
public class MinioTest {
    static MinioClient minioClient;

    /**
     * @return void
     * @description 单元测试前通用操作
     * @author: woldier
     * @date: 2023/3/9 19:23
     */
    @BeforeAll
    public static void before() {
        /*
         * 创建MinIO客户端
         * */
        minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
        boolean bucketExists = false;
        /*查看桶是否存在,不存在则创建*/
        try {
            bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("testbucket").build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!bucketExists) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("testbucket").build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * @return void
     * @description 上传
     * @author: woldier
     * @date: 2023/3/9 19:22
     */
    @Test
    public void upload() {
        /*通过扩展名获取媒体资源类型*/
        ContentInfo contentInfo = ContentInfoUtil.findExtensionMatch("123.mp4");
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if(contentInfo!=null) mimeType = contentInfo.getMimeType();

        /*上传*/
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("testbucket")  //桶
                            .object("/test/hello2.zip") // 对象名,在桶下存储的文件
                            .filename("D:\\BaiduNetdiskDownload\\stc-isp.zip")  //指定本地文件路径
                            .contentType(mimeType) //设置媒体文件类型
                            .build()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

```



###### 4.3.2.5.2 删除文件

参考：https://docs.min.io/docs/java-client-api-reference#removeObject

```java

    /**
    * @description 删除文件
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/9 19:23
    */
    @Test
    public void delete(){
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket("testbucket")
                            .object("test/hello.zip")
                            .build()
            );

        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

```



###### 4.3.2.5.3 查询文件

通过查询文件查看文件是否存在minio中。

参考：https://docs.min.io/docs/java-client-api-reference#getObject

```java
package com.xuecheng.media;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import io.minio.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.io.*;

/**
 * @author woldier
 * @version 1.0
 * @description MinIO上传测试
 * @date 2023/3/9 18:51
 **/
@SpringBootTest()
public class MinioTest {
    static MinioClient minioClient;

    /**
     * @return void
     * @description 单元测试前通用操作
     * @author: woldier
     * @date: 2023/3/9 19:23
     */
    @BeforeAll
    public static void before() {
        /*
         * 创建MinIO客户端
         * */
        minioClient = MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
        boolean bucketExists = false;
        /*查看桶是否存在,不存在则创建*/
        try {
            bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket("testbucket").build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (!bucketExists) {
            try {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("testbucket").build());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
    * @description 查询/下载文件
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/9 20:05
    */
    @Test
    public void download(){
        try {
            /*获取MinIO传给我们的输入流*/
            InputStream object = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket("testbucket")
                            .object("test/hello2.zip")
                            .build());
            /*输出到本地文件系统*/
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\hello_download.zip"));
            IOUtils.copy(object,fileOutputStream);

            /*校验文件完整性
            * 对文件内容进行md5如果inputStream与outputStream一致
            * 下载文件完整
            * 此处用到apache的一个工具类
            * */
            String inputMd5 = DigestUtils.md5Hex(object);
            String downLoadMd5 = DigestUtils.md5Hex(new FileInputStream(new File("D:\\hello_download.zip")));
            String upLoadMd5 = DigestUtils.md5Hex(new FileInputStream(new File("D:\\BaiduNetdiskDownload\\stc-isp.zip")));
            if(inputMd5.equals(downLoadMd5)){
                System.out.println("下载成功");
            }
            if(downLoadMd5.equals(upLoadMd5)){
                System.out.println("本地上传的原始文件与下载到本地的文件md5一致");
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}

```

这里对下载后的文件与MinIO的文件进行Md5比对

为什么要进行md5校验呢?

通过网络传输可能会出现丢包现象,为了防止数据的不完整,因此我们在上传下载后都要进行md5校验,保证文件完整性

但是我们代码中来自MinIO的远程流`object`对象计算的md5值为什么和我们下载完成后的不一致呢,这是因为我们这是一个远程网络流,而这个流本身就是不稳定的.

因此我们将上传时使用的本地文件`D:\\BaiduNetdiskDownload\\stc-isp.zip`读入流中,与我们下载到本地的文件`D:\\hello_download.zip`进行比对,这时候发现md5值相同.

```shell
2023-03-09 20:39:32,752 DEBUG [com.alibaba.nacos.client.Worker.longPolling.fixed-121.4.122.56_8848-dev][NacosRestTemplate.java:494] - Execute via interceptors :[com.alibaba.nacos.client.config.impl.ConfigHttpClientManager$LimiterHttpClientRequestInterceptor@ec99af0]
本地上传的原始文件与下载到本地的文件md5一致
2023-03-09 20:39:33,040 WARN [Thread-2][HttpClientBeanHolder.java:108] - [HttpClientBeanHolder] Start destroying common HttpClient
2023-03-09 20:39:33,041 WARN [Thread-2][HttpClientBeanHolder.java:114] - [HttpClientBeanHolder] Destruction of the end
```



### 4.4 上传图片

#### 4.4.1 需求分析

##### 4.4.1.1 业务流程

课程图片是宣传课程非常重要的信息，在新增课程界面上传课程图片，也可以修改课程图片。

如下图：

![image-20230309205154959](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309205154959.png)

课程图片上传至分布式文件系统，在课程信息中保存课程图片路径，如下流程：

![image-20230309205459875](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309205459875.png)

1、前端进入上传图片界面

2、上传图片，请求媒资管理服务。

3、媒资管理服务将图片文件存储在MinIO。

4、媒资管理记录文件信息到数据库。

5、保存课程信息，在内容管理数据库保存图片地址。

![image-20230309205533505](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309205533505.png)

##### 4.4.1.2 数据模型

涉及到的数据表有：课程信息表中的图片字段、媒资数据库的文件表，下边

主要看媒资数据库的文件表。

![image-20230309210134727](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309210134727.png)

字段描述如下

![image-20230309210213449](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309210213449.png)

#### 4.4.2 环境准备

首先在minio配置bucket，bucket名称为：mediafiles，并设置bucket的权限为公开。

在nacos配置中minio的相关信息，进入media-service-dev.yaml:

![image-20230309212926866](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230309212926866.png)

```yaml
minio:
  endpoint: http://localhost:9000
  accessKey: minioadmin
  secretKey: minioadmin
  bucket:
    files: mediafiles
    videofiles: video

```

在media-service工程编写minio的配置类：

MinIO配置属性类

```java
package com.xuecheng.media.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/3/9 21:31
 **/
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinIOProperties {
}

```

Minio配置类

```java
package com.xuecheng.media.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/3/9 21:33
 **/
@Configuration
@EnableConfigurationProperties({MinIOProperties.class})
@RequiredArgsConstructor
public class MinIOConfig {
    private final MinIOProperties minIOProperties;

    /**
    * @description 初始化MinIO客户端,并且交给spring管理
    *
    * @return io.minio.MinioClient
    * @author: woldier
    * @date: 2023/3/9 21:36
    */
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("minioadmin", "minioadmin")
                .build();
    }
}

```



#### 4.4.3 接口定义

根据需求分析，下边进行接口定义，此接口定义为一个通用的上传文件接口，可以上传图片或其它文件。

首先分析接口：

请求地址：/media/upload/coursefile

请求参数：

**Content-Type:** multipart/form-data;boundary=.....

FormData:  **filedata=??** 

响应参数：文件信息，如下

```json
{
  "id": "a16da7a132559daf9e1193166b3e7f52",
  "companyId": 1232141425,
  "companyName": null,
    "filename": "1.jpg",
  "fileType": "001001",
  "tags": "",
  "bucket": "/testbucket/2022/09/12/a16da7a132559daf9e1193166b3e7f52.jpg",
  "fileId": "a16da7a132559daf9e1193166b3e7f52",
  "url": "/testbucket/2022/09/12/a16da7a132559daf9e1193166b3e7f52.jpg",
  "timelength": null,
  "username": null,
  "createDate": "2022-09-12T21:57:18",
  "changeDate": null,
  "status": "1",
  "remark": "",
  "auditStatus": null,
  "auditMind": null,
  "fileSize": 248329

}
```

在media-model定义上传响应模型类：

```java
package com.xuecheng.media.model.dto;

import com.xuecheng.media.model.po.MediaFiles;

/**
 * @author woldier
 * @version 1.0
 * @description 上传文件响应结果类
 * @date 2023/3/9 21:55
 **/
public class UploadFileResultDto extends MediaFiles {
}

```

定义接口如下

```java
/**
     * @param upload     表单数据
     * @param folder     文件夹名-非必须
     * @param objectName 文件名-非必须
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 文件上传
     * @author: woldier
     * @date: 2023/3/9 22:09
     */
    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // 定义请求url与可消费的文件操作content-type类型
    public UploadFileResultDto upload(
            @RequestPart("filedata") MultipartFile upload,
            @RequestParam(value = "folder", required = false) String folder,
            @RequestParam(value = "objectName", required = false) String objectName) {
        return null;
    }
```

controller层中我们需要将文件暂存在本地,让后将临时文件的地址放进服务层方法参数中

#### 4.4.3 接口开发

##### 4.4.3.1 DAO开发

根据需求分析DAO层实现向media_files表插入一条记录，使用media_files表生成的mapper即可。

##### 4.4.3.2 Service开发

为了使代码更具有可读性，我们创建了两个枚举工具类，用于区分数据库字段值

![image-20230310165611318](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230310165611318.png)

可以看到我们操作mediaFile表时需要用到这两种字段，因此我们使用枚举简化

在meida-model的dto包下创建如下两个枚举类

```java
package com.xuecheng.media.model.dto;

/**
 * @author woldier
 * @version 1.0
 * @description 媒体资源类型枚举
 * [{"code":"001001","desc":"图片"},{"code":"001002","desc":"视频"},{"code":"001003","desc":"其它"}]
 * @date 2023/3/10 15:45
 **/
public enum MediaResourceType {
    IMAGE("001001","图片"),
    VIDEO("001002","视频"),
    OTHER("001003","其它")
    ;

    private String code;
    private String description;

    MediaResourceType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }
}

```



```java
package com.xuecheng.media.model.dto;

/**
 * @author woldier
 * @version 1.0
 * @description 审核状态
 * [{"code":"002001","desc":"审核未通过"},
 * {"code":"002002","desc":"未审核"},
 * {"code":"002003","desc":"审核通过"}]
 * @date 2023/3/10 14:55
 **/
public enum MediaAuditStatus {

    NOT_Approved("002001","审核未通过"),

    Not_Audited("002002","未审核"),
    Approved("002003","审核通过");

    private String code;
    private String description;

     MediaAuditStatus(String code, String description){
        this.code = code;
        this.description = description;

    }

    public String getCode() {
        return code;
    }
}

```

除此之外由于我们操作的数据表有公共字段，updateTime，createTime。因此我们可以加入一个mp的自动填充功能。

在media-service的config包下创建如下类

```java
package com.xuecheng.media.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <P>
 * 		Mybatis-Plus 配置
 * </p>
 */
@Configuration
@MapperScan("com.xuecheng.media.mapper")
public class MybatisPlusConfig {
	/**
	 * 新的分页插件
	 * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
	 * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return interceptor;
	}


}
```





Service方法需要提供一个更加通用的保存文件的方法。

定义请求参数类：

```java
package com.xuecheng.media.model.dto;

import lombok.Data;

/**
 * @author woldier
 * @version 1.0
 * @description 文件上传通用参数dto,这里的大部分都来自与 MediaFiles
 * @date 2023/3/9 22:14
 **/
@Data
public class UploadFileParamsDto {
    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件content-type
     */
    private String contentType;

    /**
     * 文件类型（文档，音频，视频）
     */
    private String fileType;
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 标签
     */
    private String tags;

    /**
     * 上传人
     */
    private String username;

    /**
     * 备注
     */
    private String remark;

}

```

定义service方法：

`com.xuecheng.media.service.MediaFileService`

```java
/**
     * @param companyId           公司ID
     * @param uploadFileParamsDto 上传文件参数类
     * @param LocalFilePath       要上传的文件其本地路径
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 上传文件
     * @author: woldier
     * @date: 2023/3/10 13:36
     */
    UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String LocalFilePath) throws XueChengPlusException;
    
    /**
    * @description 将上传的文件插入数据库 
    * @param companyId 
     * @param uploadFileParamsDto 
     * @param md5 
     * @param bucket 
     * @param objectName  
    * @return com.xuecheng.media.model.po.MediaFiles 
    * @author: woldier 
    * @date: 2023/3/10 16:42
    */
     MediaFiles insertMediaFile2DB(Long companyId, UploadFileParamsDto uploadFileParamsDto, String md5, String bucket,String objectName);
```

`com.xuecheng.media.service.impl.MediaFileServiceImpl`

```java
/**
     * @param companyId           公司ID
     * @param uploadFileParamsDto 上传文件参数类
     * @param localFilePath       要上传的文件其本地路径
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 上传文件
     * @author: woldier
     * @date: 2023/3/10 13:36
     */
    @Override
    public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath) throws XueChengPlusException {
        /*
         * 1.上传文件到minio ，文件路径为 /{桶名}/{年}/{月}/{日}/
         * 2.插入数据库
         * */
        /*通过扩展名获取媒体资源类型*/
        String mimeType = getMimeType(uploadFileParamsDto.getFilename());
        /*组装文件基路径*/
        String basePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        /*由于Minio中同一天的数据保存位置都在同一个文件夹下,为了防止重名现象,不能用原文件名,应该用md5值加后缀*/
        int index = uploadFileParamsDto.getFilename().lastIndexOf(".");
        //文件后缀
        String fileSuffix = uploadFileParamsDto.getFilename().substring(index);
        //文件md5值
        String md5 = getMd5(localFilePath);
        //拼接得到Minio存储路径
        String objectName = basePath + md5 + fileSuffix;
        //上传到minio
        boolean minIOUpload = minIOUpload(localFilePath, mimeType, fileBucket, objectName);
        if (!minIOUpload) XueChengPlusException.cast("MinIO上传出错");
        //上传到数据库
        MediaFiles files = insertMediaFile2DB(companyId, uploadFileParamsDto, md5, fileBucket, objectName);
//        MediaFileService proxy = (MediaFileService)AopContext.currentProxy();
//        MediaFiles files = proxy.insertMediaFile2DB(companyId, uploadFileParamsDto, md5, fileBucket,objectName);
        //结果为空表示上传失败
        if (files == null) XueChengPlusException.cast("文件上传后保存信息到数据库失败");
        UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
        BeanUtils.copyProperties(files, uploadFileResultDto);

        return uploadFileResultDto;
    }

    /**
     * @param companyId           公司id
     * @param uploadFileParamsDto 上传参数信息
     * @param md5                 md5
     * @param bucket              桶
     * @param objectName          对象名
     * @return com.xuecheng.media.model.po.MediaFiles
     * @description 插入数据库
     * @author: woldier
     * @date: 2023/3/10 15:21
     */
    @Transactional
    public MediaFiles insertMediaFile2DB(Long companyId, UploadFileParamsDto uploadFileParamsDto, String md5, String bucket, String objectName) {
        /*添加数据库之前,根据md5查询该文件是否已经存在*/
        MediaFiles files = mediaFilesMapper.selectById(md5);
        if (files == null) {
            /*生成数据库entity*/
            MediaFiles mediaFiles = new MediaFiles();
            BeanUtils.copyProperties(uploadFileParamsDto, mediaFiles);
            //设置uploadFileParamsDto中不存在的部分
            //设置id
            mediaFiles.setId(md5);
            //机构id
            mediaFiles.setCompanyId(companyId);
            //bucket
            mediaFiles.setBucket(bucket);
            //存储路径
            mediaFiles.setFilePath(objectName);
            //file_id
            mediaFiles.setFileId(md5);
            //url
            mediaFiles.setUrl("/" + bucket + "/" + objectName);
            //上传时间,更新时间自动设置
            //文件状态
            mediaFiles.setStatus("1");
            //审核状态
            mediaFiles.setAuditStatus(MediaAuditStatus.Approved.getCode());

            int insert = mediaFilesMapper.insert(mediaFiles);

            if (insert <= 0) {
                log.debug("向数据库保存文件失败,bucket:{},objectName{}", fileBucket, objectName);
                return null;
            }
            return mediaFiles;
        }
        return files;
    }

    @NotNull
    private static String getMd5(String localFilePath) throws XueChengPlusException {
        String md5 = null;
        try {
            md5 = DigestUtils.md5Hex(Files.newInputStream(new File(localFilePath).toPath()));
        } catch (IOException e) {
            XueChengPlusException.cast("md5计算时出错");
        }
        return md5;
    }

    /**
     * @param fileName 带后缀文件名
     * @return java.lang.String
     * @description 根据文件后缀名获取MimeType
     * @author: woldier
     * @date: 2023/3/10 13:55
     */
    private String getMimeType(String fileName) {
        if (fileName == null) fileName = "";
        ContentInfo contentInfo = ContentInfoUtil.findExtensionMatch(fileName);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (contentInfo != null) mimeType = contentInfo.getMimeType();
        return mimeType;
    }

    /**
     * @param localFilePath 本地文件路径
     * @param fileType      文件类型
     * @param bucket        桶名称
     * @return boolean
     * @description 上传文件到MinIO的方法
     * @author: woldier
     * @date: 2023/3/10 13:33
     */
    private boolean minIOUpload(String localFilePath, String fileType, String bucket, String objectName) {

        /*上传*/
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucket)  //桶
                            .object(objectName) // 对象名,在桶下存储的文件
                            .filename(localFilePath)  //指定本地文件路径
                            .contentType(fileType) //设置媒体文件类型
                            .build()
            );
        } catch (Exception e) {
            log.error("文件上传到MinIO出错,buckcet:{},path:{},error:{}", bucket, objectName, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }
```



##### 4.4.3.3 接口代码完善

```java
 /**
     * @param upload     表单数据
     * @param folder     文件夹名-非必须
     * @param objectName 文件名-非必须
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 文件上传
     * @author: woldier
     * @date: 2023/3/9 22:09
     */
    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload/coursefile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // 定义请求url与可消费的文件操作content-type类型
    public UploadFileResultDto upload(
            @RequestPart("filedata") MultipartFile upload,
            @RequestParam(value = "folder", required = false) String folder,
            @RequestParam(value = "objectName", required = false) String objectName) throws IOException, XueChengPlusException {
        /*
        *对接受到的文件进行处理
         */
        /*产生一个临时文件*/
        File tempFile = File.createTempFile("minio", "temp");
        /*将请求中的表单数据拷贝到临时文件中*/
        upload.transferTo(tempFile);
        /*获取绝对路径*/
        String absolutePath = tempFile.getAbsolutePath();

        /*
        *对上传参数进行处理
         */

        //上传文件参数类
        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        //原始文件名称
        uploadFileParamsDto.setFilename(upload.getOriginalFilename());
        //文件大小
        uploadFileParamsDto.setFileSize(upload.getSize());
        //文件类型
        uploadFileParamsDto.setFileType(MediaResourceType.IMAGE.getCode());
        

        /*
        * 公司id获取
        * */
        //TODO 硬编码公司id
        Long companyId = 123456789L;

        UploadFileResultDto uploadFileResultDto = mediaFileService.uploadFile(companyId, uploadFileParamsDto, absolutePath);

        /*删除临时文件*/
        tempFile.deleteOnExit();
        return uploadFileResultDto;
```

#### 4.4.4 service事务代码优化

上边的service方法优化后并测试通过，现在思考关于uploadFile方法的是否应该开启事务。

目前是在uploadFile方法上添加@Transactional，当调用uploadFile方法前会开启数据库事务，如果上传文件过程时间较长那么数据库的事务持续时间就会变长，这样数据库链接释放就慢，最终导致数据库链接不够用。

我们只将addMediaFilesToDb方法添加事务控制即可,uploadFile方法上的@Transactional注解去掉。(上小节代码已经时这样做的)

但是现在的问题是,controller调用的service方法upload没用加入事务注解,相当于在service中一个没有事务的方法调用了另一个事务方法,事务不生效

下边分析原因：

如果在uploadFile方法上添加@Transactional注解，代理对象执行此方法前会开启事务，如下图：

![image-20230310170730673](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230310170730673.png)



如果在uploadFile方法上没有@Transactional注解，代理对象执行此方法前

不进行事务控制，如下图：

![image-20230310170810020](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230310170810020.png)

现在在addMediaFilesToDb方法上添加@Transactional注解，也不会进行事务是因为并不是通过代理对象执行的addMediaFilesToDb方法。为了判断在uploadFile方法中去调用addMediaFilesToDb方法是否是通过代理对象去调用，我们可以打断点跟踪。

![image-20230310170826985](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230310170826985.png)

我们发现在uploadFile方法中去调用addMediaFilesToDb方法不是通过代理对象去调用。

 

如何解决呢？通过代理对象去调用addMediaFilesToDb方法即可解决。

我们先获取代理对象,然后调用代理对象的insertMediaFile2DB方法

```java
MediaFileService proxy = (MediaFileService)AopContext.currentProxy();
MediaFiles files = proxy.insertMediaFile2DB(companyId, uploadFileParamsDto, md5, fileBucket,objectName);
```

但是只修改这个代码启动会报错

```shell
Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to make it available, and ensure that AopContext.currentProxy() is invoked in the same thread as the AOP invocation context.
```

我们需要在启动类下加入注解并且设置exposeProxy属性,除此之外可能的报错原因是没有加入包

```java
@EnableAspectJAutoProxy(exposeProxy = true)
```

```xml
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
        </dependency>
```

我们可以在代码中插入数据库后除0模拟错误,看一下是否进行了数据库回归,经过测试可以发现进行了回滚

![image-20230310172853142](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230310172853142.png)

#### 4.4.5 接口测试

```http
### 上传文件
POST {{gateway_host}}/media//upload/coursefile
Content-Type: multipart/form-data; boundary=WebAppBoundary

--WebAppBoundary
Content-Disposition: form-data;name="filedata"; filename="1.jpg"
Content-Type: application/octet-stream

< d:/java_lesson/upload/1.jpg
```

```shell
POST http://localhost:63010/media//upload/coursefile

HTTP/1.1 200 OK
transfer-encoding: chunked
Content-Type: application/json
Date: Fri, 10 Mar 2023 08:33:46 GMT

{
  "id": "8a58662af30ace3e83f629a10ddd8662",
  "companyId": 123456789,
  "companyName": null,
  "filename": "1.jpg",
  "fileType": "001001",
  "tags": null,
  "bucket": "mediafiles",
  "filePath": "2023/03/10/8a58662af30ace3e83f629a10ddd8662.jpg",
  "fileId": "8a58662af30ace3e83f629a10ddd8662",
  "url": "/mediafiles/2023/03/10/8a58662af30ace3e83f629a10ddd8662.jpg",
  "username": null,
  "createDate": null,
  "changeDate": null,
  "status": "1",
  "remark": null,
  "auditStatus": "002003",
  "auditMind": null,
  "fileSize": 9778
}
```

![image-20230310170259443](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230310170259443.png)



### 4.5 上传视频

#### 4.5.1 需求分析

##### 4.5.1.1 业务流程

1、教学机构人员进入媒资管理列表查询自己上传的媒资文件。

点击“媒资管理”

![image-20230311105746421](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311105746421.png)

进入媒资管理列表页面查询本机构上传的媒资文件。

2、教育机构用户在"媒资管理"页面中点击 "上传视频" 按钮。

![image-20230311105919362](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311105919362.png)

点击“上传视频”打开上传页面

![image-20230311105934687](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311105934687.png)



3、选择要上传的文件，自动执行文件上传。

![image-20230311105946833](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311105946833.png)

4、视频上传成功会自动处理，处理完成可以预览视频。

![image-20230311110013147](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311110013147.png)

点击“上传视频”打开上传页面

![image-20230311110027353](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311110027353.png)



3、选择要上传的文件，自动执行文件上传。

![image-20230311110051493](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311110051493.png)



4、视频上传成功会自动处理，处理完成可以预览视频。

![image-20230311110100554](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311110100554.png)



#### 4.5.2 理解断点续传

##### 4.5.2.1 什么是断点续传

通常视频文件都比较大，所以对于媒资系统上传文件的需求要满足大文件的上传要求。http协议本身对上传文件大小没有限制，但是客户的网络环境质量、电脑硬件环境等参差不齐，如果一个大文件快上传完了网断了没有上传完成，需要客户重新上传，用户体验非常差，所以对于大文件上传的要求最基本的是断点续传。

什么是断点续传：

​    引用百度百科：断点续传指的是在下载或上传时，将下载或上传任务（一个文件或一个压缩包）人为的划分为几个部分，每一个部分采用一个线程进行上传或下载，如果碰到网络故障，可以从已经上传或下载的部分开始

继续上传下载未完成的部分，而没有必要从头开始上传下载，断点续传可以提高节省操作时间，提高用户体验性。

断点续传流程如下图：

![image-20230311110315589](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311110315589.png)



流程如下：

1、前端上传前先把文件分成块

2、一块一块的上传，上传中断后重新上传，已上传的分块则不用再上传

3、各分块上传完成最后在服务端合并文件



##### 4.5.2.2 分块与合并测试

为了更好的理解文件分块上传的原理，下边用java代码测试文件的分块与合并。

文件分块的流程如下：

1、获取源文件长度

2、根据设定的分块文件的大小计算出块数

3、从源文件读数据依次向每一个块文件写数据。

测试代码如下：

```java

    @Autowired
    MinioClient minioClient;
    /**
     * @return void
     * @description 本地文件分片
     * @author: woldier
     * @date: 2023/3/11 8:50
     */
    @Test
    public void shading() throws IOException {
        /*
         * 1.指定本地文件路径，分片大小，分片后文件存储位置
         * 2.根据文件大小与分片大小得到分片数目，创建对应文件
         * 3.分片写入
         * */
        //本地源文件
        File source = new File("D:\\java_lesson\\sharding\\visualcrustdemo.rar");
        FileInputStream inputStream = new FileInputStream(source);
        //分片保存的文件夹
        File chunkDir = new File("D:\\java_lesson\\sharding\\chunk");
        //分片大小
        Long chunkSize = 1024 * 1024 * 5L;
        //分片数目
        int shardingNum = (int) Math.ceil((source.length() * 1.0 / chunkSize));
        //缓冲区
        byte[] b = new byte[1024];
        for (int i = 0; i < shardingNum; i++) {
            //创建分片文件
            File shadedFile = new File(chunkDir, String.valueOf(i));
            //创建分片文件输出流
            FileOutputStream shaded = new FileOutputStream(shadedFile);
            int len = -1;
            while ((len = inputStream.read(b)) != -1) {
                shaded.write(b, 0, len);
                if (shadedFile.length() >= chunkSize) break;  //当该分片达到分片大小时跳出
            }
            shaded.close();


        }
        inputStream.close();


    }
```



文件合并流程：

1、找到要合并的文件并按文件合并的先后进行排序。

2、创建合并文件

3、依次从合并的文件中读取数据向合并文件写入数

文件合并的测试代码 ：

```java
/**
    * @description 分片文件聚合
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/11 10:36
    */
    @Test
    public void merging() throws IOException {
        /*
         * 1.从分片文件夹中获取所有文件
         * 2.写入
         * */
        //分片文件目录
        File sourceDir = new File("D:\\java_lesson\\sharding\\chunk");
        //目标文件
        File target = new File("D:\\java_lesson\\sharding\\visualcrustdemo_merge.rar");
        //目标文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        String[] list = sourceDir.list();
        assert list != null;
        /*文件排序*/
        List<String> fileListSorted = Arrays.asList(list).stream().sorted(Comparator.comparing(Integer::valueOf)).collect(Collectors.toList());
        byte[] b = new byte[1024];
        fileListSorted.forEach(
                e -> {
                    try {
                        FileInputStream inputStream = new FileInputStream(new File(sourceDir, e));
                        int len = -1;
                        while((len=inputStream.read(b)) != -1){
                            fileOutputStream.write(b,0,len);
                        }
                        inputStream.close();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
        fileOutputStream.close();
    }
```

##### 4.5.3.4 上传视频流程

下图是上传视频的整体流程：

![image-20230311110849671](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230311110849671.png)



1、前端上传文件前请求媒资接口层检查文件是否存在，如果已经存在则不再上传。

2、如果文件在系统不存在前端开始上传，首先对视频文件进行分块

3、前端分块进行上传，上传前首先检查分块是否上传，如已上传则不再上传，如果未上传则开始上传分块。

4、前端请求媒资管理接口层请求上传分块。

5、接口层请求服务层上传分块。

6、服务端将分块信息上传到MinIO。

7、前端将分块上传完毕请求接口层合并分块。

8、接口层请求服务层合并分块。

9、服务层根据文件信息找到MinIO中的分块文件，下载到本地临时目录，将所有分块下载完毕后开始合并 。

10、合并完成将合并后的文件上传到MinIO。

minio合并文件



```java
/**
    * @description 本地分片上传到minio并合并
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/11 10:38
    */
    @Test
    public void MinIOMerge() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //分片文件目录
        File sourceDir = new File("D:\\java_lesson\\sharding\\chunk");
        String[] list = sourceDir.list();
        assert list != null;
        /*文件排序*/
        Arrays.stream(list).sorted(Comparator.comparing(Integer::valueOf)).forEach(
                i ->{
                    /*上传*/
                    try {
                        minioClient.uploadObject(
                                UploadObjectArgs.builder()
                                        .bucket("testbucket")  //桶
                                        .object("/test/chunk/"+i) // 对象名,在桶下存储的文件
                                        .filename("D:\\java_lesson\\sharding\\chunk\\"+i)  //指定本地文件路径

                                        .build()
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        List<ComposeSource> composeSourceList = Arrays.asList(list).stream().map(e ->

                ComposeSource.builder().bucket("testbucket").object("/test/chunk/" + e).build()
        ).collect(Collectors.toList());
        /*minio文件合并*/
        ComposeObjectArgs composeObjectArgs = ComposeObjectArgs.builder().bucket("testbucket").object("/test/conpose.rar").sources(composeSourceList).build();
        minioClient.composeObject(composeObjectArgs);


    }
```



#### 4.5.3 接口定义

接口层需要提供以下几个接口，第一个接口是检查文件是否存在于服务器（通过文件md5来进行标识），第二个是检查某分片是否存在于服务器，第三个是上传分片到服务器，第四个是合并分片。这几个接口都是通过文件的md5值来区分文件的。

```java
package com.xuecheng.media.api;

import com.xuecheng.base.model.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author woldier
 * @version 1.0
 * @description 大文件上传接口
 * @date 2023/3/11 11:43
 **/
@Api(value = "大文件上传",tags = "大文件上传接口")
@RestController
public class BigFileController {

    /**
    * @description 文件上传前的检查接口
    * @param fileMd5  文件md5值
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 11:54
    */
    @ApiOperation("检查文件是否存在")
    @PostMapping("/upload/checkfile")
    public RestResponse checkFile(
            @RequestParam(value = "fileMd5")  String  fileMd5
    ){
        return null;

    }

    /**
    * @description 检查分片是否存在
    * @param fileMd5 文件md5值
     * @param chunk  分片id
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 11:59
    */
    @ApiOperation("检查分片是否存在")
    @PostMapping("/upload/checkchunk")
    public RestResponse checkChunk(
            @RequestParam(value = "fileMd5") String  fileMd5,
            @RequestParam(value = "chunk") Integer chunk
            ){
        return null;
    }


    /**
    * @description 分片上传接口
    * @param file 文件
     * @param fileMd5 分片md5
     * @param chunk  分片id
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 12:04
    */
    @ApiOperation("上传分片")
    @PostMapping("/upload/uploadchunk")
    public RestResponse uploadChunk(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileMd5")  String fileMd5,
            @RequestParam("chunk")  Integer chunk
            ){
        return null;
    }

    /**
    * @description 合并分片
    * @param fileName 文件名
     * @param fileMd5 文件md5值
     * @param chunkTotal  分片总数
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 12:18
    */
    @ApiOperation("合并分片")
    @PostMapping("/upload/mergechunk")
    public RestResponse mergeChunks(
            @RequestParam("fileMd5")  String fileMd5,
            @RequestParam("fileName") String fileName,
            @RequestParam("chunkTotal")  Integer chunkTotal
    ){
        return null;
    }
}

```



#### 4.5.4 接口开发

##### 4.5.4.1 DAO开发

向媒资数据库的文件表插入记录,使用自动生成的Mapper接口即可满足要求

##### 4.5.4.2 Service开发

1. 检查文件

`com.xuecheng.media.service.MediaFileService`

```java

    /**
     * @param md5 文件md值
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件是否存在
     * @author: woldier
     * @date: 2023/3/11 22:31
     */
    RestResponse checkFile(String md5);
```

```java
 /**
     * @param md5 文件md值
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件是否存在
     * @author: woldier
     * @date: 2023/3/11 22:31
     */
    @Override
    public RestResponse checkFile(String md5) {
        /*
         * 1.查询md5值数据库
         * 2.若没有数据直接返回false
         * 3.若有则查询minio,查看是否可以get到对象,对于视频文件我们也要将他分文件夹存储,分为两级目录,第一级是md5的第一个字符,第二级是md5的第二个字符
         * 如avsdsdsdsds 为某文件的md值,那么该文件在minio中的存储路径是 a/v/avsdsdsdsds.文件后缀
         * */
        //从数据库查询
        MediaFiles mediaFiles = this.getById(md5);
        if(mediaFiles!=null){//数据库中查询到不为空
            //获取桶
            String bucket = mediaFiles.getBucket();
            //获取存储路径
            String filePath = mediaFiles.getFilePath();
            try(InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filePath).build())){ //通过这种方法创建额流会在try catch后自动释放
                //若input流对象不为空,说明minio中有数据,那么返回存在
                if (inputStream != null) return RestResponse.success(Boolean.TRUE);
            }catch (Exception e){
                log.debug("在minio中获取对象出错bucket:{},objectName{},errInfo{}",bucket,filePath,e.getMessage());
            }
        }

        //查询到数据库为空,或者查询minio报错返回错误
        return RestResponse.success(Boolean.FALSE);
    }
```



2. 检查文件分块

```java
/**
     * @param md5   文件md值
     * @param chunk 分片id
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件分片是否存在;
     * 首先分片数据记录并不会存在于数据库中,
     * 因此只能通过访问minio来进行查询,
     * 我们可以通过minio的getobject方法,有则说明可以
     * @author: woldier
     * @date: 2023/3/11 22:34
     */
    RestResponse checkChunk(String md5, Integer chunk);
```

```java
/**
     * @param md5   文件md值
     * @param chunk 分片id
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件分片是否存在;
     * 首先分片数据记录并不会存在于数据库中,
     * 因此只能通过访问minio来进行查询,
     * 我们可以通过minio的getobject方法,有则说明可以
     * @author: woldier
     * @date: 2023/3/11 22:34
     */
    @Override
    public RestResponse checkChunk(String md5, Integer chunk) {
        /*
        * 由于分片文件信息不会记录在db中,因此我们之后访问minio进行查询
        * 1.通过md5得到分片文件存放的文件夹
        * 2.将文件夹路径与分片号chunk合并,得到分片路径
        * 例如 分片为23 md5为0dbc6409995eaa9589676c585459e02b
        * 则拼接的对象 路径为 0/d/0dbc6409995eaa9589676c585459e02b/chunk/23
        * 3.通过Minio查看是否可以成功获取若能说明存在返回成功,否则返回失败
        * */
        //获取分片文件夹路径
        String chunkFolder = getChunkFolderByMd5(md5);
        //得到分片存储路径
        String objectName = chunkFolder + chunk;
        Boolean exist = checkFileInMinio(videoBucket, objectName);
        if(Boolean.TRUE.equals(exist)) return RestResponse.success(Boolean.TRUE);

        return RestResponse.success(Boolean.FALSE);
    }

/**
    * @description 检查minio中是否存在
    * @param bucket 桶
     * @param filePath  对象路径
    * @return Boolean 若存在返回True 不存在返回False
    * @author: woldier
    * @date: 2023/3/12 10:35
    */
    @Nullable
    private Boolean checkFileInMinio(String bucket, String filePath) {
        try(InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(filePath).build())){ //通过这种方法创建额流会在try catch后自动释放
            //若input流对象不为空,说明minio中有数据,那么返回存在
            if (inputStream != null) return Boolean.TRUE;
        }catch (Exception e){
            log.debug("在minio中获取对象出错bucket:{},objectName{},errInfo{}", bucket, filePath,e.getMessage());
        }
        return Boolean.FALSE;
    }
```





3. 上传分块



由于前端文件设置的默认分片大小为1，所以我们需要修改前端代码将分片大小改为5

进入前端code  修改/src/utils/uploadtools.ts

![image-20230312115047151](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/image-20230312115047151.png)

由于spring boot web 默认上传大小为1M，所以我们需要在media-api修改配置文件

```yaml
spring：
	servlet：
		multipart：
			max-file-size: 50MB
			max-request-size: 50MB
```





4. 合并分块

```java
package com.xuecheng.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.base.model.RestResponse;
import com.xuecheng.media.mapper.MediaFilesMapper;
import com.xuecheng.media.model.dto.*;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Streams;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/9/10 8:58
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MediaFileServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements MediaFileService {

    @Autowired
    private MediaFilesMapper mediaFilesMapper;

    @Value("${minio.bucket.files}")
    private String fileBucket;
    @Value("${minio.bucket.videofiles}")
    private String videoBucket;
    /**
     * minio客户端
     */
    private final MinioClient minioClient;

    @Override
    public PageResult<MediaFiles> queryMediaFiels(Long companyId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto) {

        //构建查询条件对象
        LambdaQueryWrapper<MediaFiles> queryWrapper = new LambdaQueryWrapper<>();

        //分页对象
        Page<MediaFiles> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<MediaFiles> pageResult = mediaFilesMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<MediaFiles> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<MediaFiles> mediaListResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return mediaListResult;

    }


    /**
     * @param companyId           公司ID
     * @param uploadFileParamsDto 上传文件参数类
     * @param localFilePath       要上传的文件其本地路径
     * @return com.xuecheng.media.model.dto.UploadFileResultDto
     * @description 上传文件
     * @author: woldier
     * @date: 2023/3/10 13:36
     */
    @Override
    public UploadFileResultDto uploadFile(Long companyId, UploadFileParamsDto uploadFileParamsDto, String localFilePath) throws XueChengPlusException {
        /*
         * 1.上传文件到minio ，文件路径为 /{桶名}/{年}/{月}/{日}/
         * 2.插入数据库
         * */
        /*通过扩展名获取媒体资源类型*/
        String mimeType = getMimeType(uploadFileParamsDto.getFilename());
        /*组装文件基路径*/
        String basePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        /*由于Minio中同一天的数据保存位置都在同一个文件夹下,为了防止重名现象,不能用原文件名,应该用md5值加后缀*/
        int index = uploadFileParamsDto.getFilename().lastIndexOf(".");
        //文件后缀
        String fileSuffix = uploadFileParamsDto.getFilename().substring(index);
        //文件md5值
        String md5 = getMd5(localFilePath);
        //拼接得到Minio存储路径
        String objectName = basePath + md5 + fileSuffix;
        //上传到minio
        boolean minIOUpload = minIOUpload(localFilePath, mimeType, fileBucket, objectName);
        if (!minIOUpload) XueChengPlusException.cast("MinIO上传出错");
        //上传到数据库
        //MediaFiles files = insertMediaFile2DB(companyId, uploadFileParamsDto, md5, fileBucket, objectName);
        //通过代理对象调用
        MediaFileService proxy = (MediaFileService) AopContext.currentProxy();
        MediaFiles files = proxy.insertMediaFile2DB(companyId, uploadFileParamsDto, md5, fileBucket, objectName);
        //结果为空表示上传失败
        if (files == null) XueChengPlusException.cast("文件上传后保存信息到数据库失败");
        UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
        BeanUtils.copyProperties(files, uploadFileResultDto);

        return uploadFileResultDto;
    }

    /**
     * @param companyId           公司id
     * @param uploadFileParamsDto 上传参数信息
     * @param md5                 md5
     * @param bucket              桶
     * @param objectName          对象名
     * @return com.xuecheng.media.model.po.MediaFiles
     * @description 插入数据库
     * @author: woldier
     * @date: 2023/3/10 15:21
     */
    @Transactional
    public MediaFiles insertMediaFile2DB(Long companyId, UploadFileParamsDto uploadFileParamsDto, String md5, String bucket, String objectName) {
        /*添加数据库之前,根据md5查询该文件是否已经存在*/
        MediaFiles files = mediaFilesMapper.selectById(md5);
        if (files == null) {
            /*生成数据库entity*/
            MediaFiles mediaFiles = new MediaFiles();
            BeanUtils.copyProperties(uploadFileParamsDto, mediaFiles);
            //设置uploadFileParamsDto中不存在的部分
            //设置id
            mediaFiles.setId(md5);
            //机构id
            mediaFiles.setCompanyId(companyId);
            //bucket
            mediaFiles.setBucket(bucket);
            //存储路径
            mediaFiles.setFilePath(objectName);
            //file_id
            mediaFiles.setFileId(md5);
            //url
            mediaFiles.setUrl("/" + bucket + "/" + objectName);
            //上传时间,更新时间自动设置
            mediaFiles.setCreateDate(LocalDateTime.now());
            mediaFiles.setCreateDate(LocalDateTime.now());
            //文件状态
            mediaFiles.setStatus("1");
            //审核状态
            mediaFiles.setAuditStatus(MediaAuditStatus.Approved.getCode());

            int insert = mediaFilesMapper.insert(mediaFiles);

            if (insert <= 0) {
                log.debug("向数据库保存文件失败,bucket:{},objectName{}", fileBucket, objectName);
                return null;
            }
            return mediaFiles;
        }
        return files;
    }

    /**
     * @param md5 文件md值
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件是否存在
     * @author: woldier
     * @date: 2023/3/11 22:31
     */
    @Override
    public RestResponse checkFile(String md5) {
        /*
         * 1.查询md5值数据库
         * 2.若没有数据直接返回false
         * 3.若有则查询minio,查看是否可以get到对象,对于视频文件我们也要将他分文件夹存储,分为两级目录,第一级是md5的第一个字符,第二级是md5的第二个字符
         * 如avsdsdsdsds 为某文件的md值,那么该文件在minio中的存储路径是 a/v/avsdsdsdsds.文件后缀
         * */
        //从数据库查询
        MediaFiles mediaFiles = this.getById(md5);
        if (mediaFiles != null) {//数据库中查询到不为空
            //获取桶
            String bucket = mediaFiles.getBucket();
            //获取存储路径
            String filePath = mediaFiles.getFilePath();
            Boolean status = checkFileInMinio(bucket, filePath);
            if (Boolean.TRUE.equals(status)) return RestResponse.success(Boolean.TRUE);
        }

        //查询到数据库为空,或者查询minio报错返回错误
        return RestResponse.success(Boolean.FALSE);
    }

    /**
     * @param bucket     桶
     * @param objectName 对象路径
     * @return Boolean 若存在返回True 不存在返回False
     * @description 检查minio中是否存在
     * @author: woldier
     * @date: 2023/3/12 10:35
     */
    @Nullable
    private Boolean checkFileInMinio(String bucket, String objectName) {
        return checkFileInMinio(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    private Boolean checkFileInMinio(GetObjectArgs getObjectArgs) {
        try (InputStream inputStream = minioClient.getObject(getObjectArgs)) { //通过这种方法创建额流会在try catch后自动释放
            //若input流对象不为空,说明minio中有数据,那么返回存在
            if (inputStream != null) return Boolean.TRUE;
        } catch (Exception e) {
            log.debug("在minio中获取对象出错bucket:{},objectName{},errInfo{}", getObjectArgs.bucket(), getObjectArgs.object(), e.getMessage());
        }
        return Boolean.FALSE;
    }

    /**
     * @param bucket     桶
     * @param objectName 对象名
     * @return StatObjectResponse
     * @description 获取minio对象信息
     * 此方法中不会做任何事,而是调用 private long  getObjStatInMinio(StatObjectArgs statObjectArgs)
     * @author: woldier
     * @date: 2023/3/12 15:08
     */
    private StatObjectResponse getObjStatInMinio(String bucket, String objectName) {
        return getObjStatInMinio(StatObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    /**
     * @param statObjectArgs minio 对象状态参数类
     * @return StatObjectResponse
     * @description 获取minio对象信息
     * @author: woldier
     * @date: 2023/3/12 15:08
     */
    private StatObjectResponse getObjStatInMinio(StatObjectArgs statObjectArgs) {
        StatObjectResponse statObjectResponse = null;
        try {
             statObjectResponse = minioClient.statObject(statObjectArgs);
        } catch (Exception e) {
            log.debug("获取minio对象信息时出错,bucket{},objectName{},errMsg{}", statObjectArgs.bucket(), statObjectArgs.object(), e.getMessage());
        }
        return statObjectResponse;
    }

    /**
     * @param md5 md5值
     * @return java.lang.String
     * @description 根据md5值得到存储在minio中的分片对象文件夹路径
     * 如文件md5为 0dbc6409995eaa9589676c585459e02b 则得到的文件夹路径为 0/d/0dbc6409995eaa9589676c585459e02b/chunk/
     * @author: woldier
     * @date: 2023/3/11 22:57
     */
    private String getChunkFolderByMd5(String md5) {

        return md5.charAt(0) + "/" + md5.charAt(1) + "/" + md5 + "/chunk/";

    }

    private String getFolderByMd5(String md5) {
        return md5.charAt(0) + "/" + md5.charAt(1) + "/";
    }


    /**
     * @param md5   文件md值
     * @param chunk 分片id
     * @return com.xuecheng.base.model.RestResponse
     * @description 检查文件分片是否存在;
     * 首先分片数据记录并不会存在于数据库中,
     * 因此只能通过访问minio来进行查询,
     * 我们可以通过minio的getobject方法,有则说明可以
     * @author: woldier
     * @date: 2023/3/11 22:34
     */
    @Override
    public RestResponse checkChunk(String md5, Integer chunk) {
        /*
         * 由于分片文件信息不会记录在db中,因此我们之后访问minio进行查询
         * 1.通过md5得到分片文件存放的文件夹
         * 2.将文件夹路径与分片号chunk合并,得到分片路径
         * 例如 分片为23 md5为0dbc6409995eaa9589676c585459e02b
         * 则拼接的对象 路径为 0/d/0dbc6409995eaa9589676c585459e02b/chunk/23
         * 3.通过Minio查看是否可以成功获取若能说明存在返回成功,否则返回失败
         * */
        //获取分片文件夹路径
        String chunkFolder = getChunkFolderByMd5(md5);
        //得到分片存储路径
        String objectName = chunkFolder + chunk;
        Boolean exist = checkFileInMinio(videoBucket, objectName);
        if (Boolean.TRUE.equals(exist)) return RestResponse.success(Boolean.TRUE);

        return RestResponse.success(Boolean.FALSE);
    }

    /**
     * @param md5           md值
     * @param chunk         分片id
     * @param localFilePath 本地文件路径
     * @return com.xuecheng.base.model.RestResponse
     * @description 上传文件分块
     * @author: woldier
     * @date: 2023/3/12 10:50
     */
    @Override
    public RestResponse uploadChuck(String md5, Integer chunk, String localFilePath) {
        /*
         * 1.通过md5获取分片存储文件夹
         * 2.拼接得到分片文件存储路径
         * 2.上传文件到minio
         * */
        //获取文件路径
        String chunkFolder = getChunkFolderByMd5(md5);
        //拼接得到文件存储路径
        String objectName = chunkFolder + chunk;
        //生成文件contentType
        String mimeType = getMimeType(null); //为空会返回
        //上传到服务器
        if (minIOUpload(localFilePath, mimeType, videoBucket, objectName))
            return RestResponse.success(Boolean.TRUE);
        return RestResponse.success(Boolean.FALSE);
    }


    /**
     * @param md5        md5id
     * @param chunkTotal 分块总数
     * @return com.xuecheng.base.model.RestResponse
     * @description 分块合并
     * @author: woldier
     * @date: 2023/3/12 13:01
     */
    @Override
    public RestResponse mergeChunk(String md5, Integer chunkTotal, Long companyId, String fileName) throws IOException {
        /*
         *1.生成分片数组
         * 2.查询分片是否存在
         * 3.合并分片
         * 4.获取合并后文件的信息
         * 5.写入数据库
         * */
        String chunkFolder = getChunkFolderByMd5(md5);
        //生成用于查询是否存在的参数集合
        List<ComposeSource> composeSourceList = Stream.iterate(0, t -> t + 1).limit(chunkTotal).map(
                e -> ComposeSource.builder().bucket(videoBucket).object(chunkFolder + e).build()).collect(Collectors.toList());
        //检查分片是否存在
        for (ComposeSource elem : composeSourceList) {
            if (checkFileInMinio(videoBucket, elem.object()).equals(Boolean.FALSE))
                return RestResponse.success(Boolean.FALSE);
        }
        //合并分片
        //拿到文件存储路径
        String folder = getFolderByMd5(md5); // 得到文件夹
        String suffix = fileName.substring(fileName.indexOf(".")); //得到后缀
        String objectName = folder + md5 + suffix;
        //minio合并,若失败则返回
        if (composeObjectInMinio(videoBucket, objectName, composeSourceList)) RestResponse.success(false);
        long size = -1L;
        File download = File.createTempFile("download","temp");//创建一个临时文件
        try (
                InputStream getObjectResponse = minioClient.getObject(GetObjectArgs.builder().bucket(videoBucket).object(objectName).build()); //minio文件输入流
                FileOutputStream fileOutputStream = new FileOutputStream(download) //本地文件输出流
        ){
            IOUtils.copy(getObjectResponse,fileOutputStream);  //input -> output
            String downLoadMd5 = DigestUtils.md5Hex(Files.newInputStream(download.toPath())); //得到下载文件的md5值
            if(!downLoadMd5.equals(md5)) { //如果两个md5值不相同
                deleteObjInMinio(videoBucket, objectName); //删除对应文件
                return RestResponse.validfail("合并后的文件md5值与原md5值不一致");
            }
            size = download.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            boolean delete = download.delete(); //删除临时文件
        }
        //md5值相等说明合并分片成功


        //操作数据库
        MediaFileService currentProxy = (MediaFileService) AopContext.currentProxy();
        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        //设置文件名
        uploadFileParamsDto.setFilename(fileName);
        //设置文件大小
        StatObjectResponse objStatInMinio = getObjStatInMinio(videoBucket, objectName); //得到对象信息
        if (objStatInMinio== null) {
            log.debug("从minio中获取合并后的文件信息失败,bucket{},objectName{}",videoBucket,objectName);
            return RestResponse.success(false);
        }
        uploadFileParamsDto.setFileSize(size);
        //文件类型
        uploadFileParamsDto.setFileType(MediaResourceType.VIDEO.getCode());
        //文件标签
        uploadFileParamsDto.setTags("课程视频");
        currentProxy.insertMediaFile2DB(companyId, uploadFileParamsDto, md5, videoBucket, objectName);


        //删除分片数据

        composeSourceList.forEach(e->{
            deleteObjInMinio(videoBucket,e.object());
        });

        return RestResponse.success(true);
    }
    /**
    * @description 删除对象
    * @param bucket
     * @param objectName
    * @return boolean
    * @author: woldier
    * @date: 2023/3/12 16:40
    */
    private boolean deleteObjInMinio(String bucket, String objectName){
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build()
            );
        }
        catch (Exception e){
            log.debug("删除失败,bucket{},objectName{},err{}",bucket,objectName,e.getMessage());
            return false;
        }
        return true;
    }
    /**
     * minio合并文件
     *
     * @param bucket            桶
     * @param objectName        对象名
     * @param composeSourceList 分片信息list
     * @return
     */
    private boolean composeObjectInMinio(String bucket, String objectName, List<ComposeSource> composeSourceList) {
        /*minio文件合并*/
        ComposeObjectArgs composeObjectArgs = ComposeObjectArgs.builder().bucket(bucket).object(objectName).sources(composeSourceList).build();
        try {
            minioClient.composeObject(composeObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("合并数据分片出现错误,bucket{},objectName{},errMsg{}", bucket, objectName, e.getMessage());
            return false;
        }
        return true;
    }

    @NotNull
    private static String getMd5(String localFilePath) throws XueChengPlusException {
        String md5 = null;
        try {
            md5 = DigestUtils.md5Hex(Files.newInputStream(new File(localFilePath).toPath()));
        } catch (IOException e) {
            XueChengPlusException.cast("md5计算时出错");
        }
        return md5;
    }

    /**
     * @param fileName 带后缀文件名 若为空 返回 MediaType.APPLICATION_OCTET_STREAM_VALUE;
     * @return java.lang.String
     * @description 根据文件后缀名获取MimeType
     * @author: woldier
     * @date: 2023/3/10 13:55
     */
    private String getMimeType(String fileName) {
        if (fileName == null) fileName = "";
        ContentInfo contentInfo = ContentInfoUtil.findExtensionMatch(fileName);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (contentInfo != null) mimeType = contentInfo.getMimeType();
        return mimeType;
    }

    /**
     * @param localFilePath 本地文件路径
     * @param fileType      文件类型
     * @param bucket        桶名称
     * @return boolean
     * @description 上传文件到MinIO的方法
     * @author: woldier
     * @date: 2023/3/10 13:33
     */
    private boolean minIOUpload(String localFilePath, String fileType, String bucket, String objectName) {
        /*上传*/
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucket)  //桶
                            .object(objectName) // 对象名,在桶下存储的文件
                            .filename(localFilePath)  //指定本地文件路径
                            .contentType(fileType) //设置媒体文件类型
                            .build()
            );
        } catch (Exception e) {
            log.error("文件上传到MinIO出错,buckcet:{},path:{},error:{}", bucket, objectName, e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;

    }
}

```



##### 4.5.4.3 接口代码完善

```java
```

```
package com.xuecheng.media.api;

import com.xuecheng.base.exception.CommonError;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.RestResponse;
import com.xuecheng.media.model.dto.MediaResourceType;
import com.xuecheng.media.model.dto.UploadFileParamsDto;
import com.xuecheng.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.IOException;

/**
 * @author woldier
 * @version 1.0
 * @description 大文件上传接口
 * @date 2023/3/11 11:43
 **/
@Api(value = "大文件上传",tags = "大文件上传接口")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BigFileController {

    private final MediaFileService mediaFileService;
    /**
    * @description 文件上传前的检查接口
    * @param fileMd5  文件md5值
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 11:54
    */
    @ApiOperation("检查文件是否存在")
    @PostMapping("/upload/checkfile")
    public RestResponse checkFile(
            @RequestParam(value = "fileMd5")  String  fileMd5
    ){
        return mediaFileService.checkFile(fileMd5);

    }

    /**
    * @description 检查分片是否存在
    * @param fileMd5 文件md5值
     * @param chunk  分片id
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 11:59
    */
    @ApiOperation("检查分片是否存在")
    @PostMapping("/upload/checkchunk")
    public RestResponse checkChunk(
            @RequestParam(value = "fileMd5") String  fileMd5,
            @RequestParam(value = "chunk") Integer chunk
            ){
        return mediaFileService.checkChunk(fileMd5, chunk);
    }


    /**
    * @description 分片上传接口
    * @param file 文件
     * @param fileMd5 分片md5
     * @param chunk  分片id
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 12:04
    */
    @ApiOperation("上传分片")
    @PostMapping("/upload/uploadchunk")
    public RestResponse uploadChunk(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileMd5")  String fileMd5,
            @RequestParam("chunk")  Integer chunk
            ) throws IOException {
        /*
        * 1.暂存file到本地
        * 2. 调用service服务
        * */
        //创建临时文件
        File tempFile =  File.createTempFile("minio", "temp");
        file.transferTo(tempFile);
        String localFilePath = tempFile.getAbsolutePath();

        RestResponse restResponse = mediaFileService.uploadChuck(fileMd5, chunk, localFilePath);
        //删除临时文件
        if(!tempFile.delete()) log.debug("删除临时文件失败,filePath{}",localFilePath);
        return restResponse;
    }

    /**
    * @description 合并分片
    * @param fileName 文件名
     * @param fileMd5 文件md5值
     * @param chunkTotal  分片总数
    * @return com.xuecheng.base.model.RestResponse
    * @author: woldier
    * @date: 2023/3/11 12:18
    */
    @ApiOperation("合并分片")
    @PostMapping("/upload/mergechunks")
    public RestResponse mergeChunks(
            @RequestParam("fileMd5")  String fileMd5,
            @RequestParam("fileName") String fileName,
            @RequestParam("chunkTotal")  Integer chunkTotal
    ) throws IOException {

        return mediaFileService.mergeChunk(fileMd5,chunkTotal,getCompanyId(),fileName);
    }

    private long getCompanyId(){
        return 1232141425L;
    }
}
```

#### 4.5.5 接口测试

![image-20230312173750961](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312173750961.png)



经过测试我发现，这里合并后在minio中生成的md值与我们自己生成的不同，但是我们将该文件从minio中下载下来生成的md5值却又是与前端传的相同的，此外我尝试把下载下来的文件再次上传上去，结果也是minio 与我们生成的不同，但是我们通过minio网页上传的文件其生成的md5值有的则是与前端相同的。

![image-20230312182248963](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312182248963.png)

猜测minio中md5的生成算法可能和我们用的有一些差别

#### 4.5.6 面试

##### 4.5.6.1 事务相关

1. 什么情况下事务会失效
   1. 在方法中捕获异常没有抛出
   2. 非事务方法调用事务方法  \ 一个未开启事务的方法调用了事务方法会产生事务失效，因为这种情况时在方法内部调用事务方法，并不是通过代理对象调用事务方法。
   3. 事务方法内部调用事务方法 \ 内部调用的这个方法开启了事务且设置了POROPAGATION_REQUEST_NET 如果不是通过代理对象调用，依旧没办法开启一个新的事务
   4. @Transactional标记的方法不是public
   5. 抛出的异常与rollbackFor指定的异常不一致，默认rollbackFor指定的时RuntimeException
   6. 数据库不支持事务，比如mysql的 MyISAM引擎
   7. Spring的传播行为导致事务失效，比如PROPAGATION_NEVER,PROPAGATION_SUPPORTED

![image-20230312185910100](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312185910100.png)

详解blog https://blog.csdn.net/yuan520588/article/details/88919659

##### 4.5.6.2 分片上传

![image-20230312192249573](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312192249573.png)





 



### 4.6 文件预览

#### 4.6.1 需求分析

##### 4.6.1.1 业务流程

##### 4.6.1.2 数据模型

#### 4.6.2 接口定义



#### 4.6.3 接口开发

##### 4.6.3.1 DAO开发

##### 4.6.3.2 Service开发

##### 4.6.3.3 接口代码完善

#### 4.6.4 接口测试







### 4.7 视频处理

#### 4.7.1 分布式任务处理

##### 4.7.1.1 什么事分布式任务处理

视频上传成功需要对视频的格式进行处理，如何用Java程序对视频进行处理呢？这里有一个关键的需求就是当视频比较多的时候我们如何可以高效处理。

如何去高效处理一批任务呢？

1. 多线程

多线程时充分利用单机的资源

2. 分布式加多线程

充分利用多台计算机，每台计算机使用多线程处理。

方案2可扩展性更强。

方案2是一种分布式任务调度的处理方案。

什么是分布式任务调度？

我们可以先思考一下下面业务场景的解决方案：

​    某电商系统需要在每天上午10点，下午3点，晚上8点发放一批优惠券。

​    某财务系统需要在每天上午10点前结算前一天的账单数据，统计汇总。

​    某电商平台每天凌晨3点，要对订单中的无效订单进行清理。

​    12306网站会根据车次不同，设置几个时间点分批次放票。

​    电商整点抢购，商品价格某天上午8点整开始优惠。

​    商品成功发货后，需要向客户发送短信提醒。

类似的场景还有很多，我们该如何实现？

以上这些场景，就是任务调度所需要解决的问题。

**任务调度顾名思义，就是对任务的调度，它是指系统为了完成特定业务，基于给定时间点，给定时间间隔或者给定执行次数自动执行任务。**

如何实现任务调度？

- 多线程方式实现

学过多线程的同学，可能会想到，我们可以开启一个线程，每sleep一段时间，就去检查是否已到预期执行时间。

以下代码简单实现了任务调度的功能：

```java
public static void main(String[] args) {    
    //任务执行间隔时间
    final long timeInterval = 1000;
    Runnable runnable = new Runnable() {
        public void run() {
            while (true) {
                //TODO：something
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    Thread thread = new Thread(runnable);
    thread.start();
}

```

上面的代码实现了按一定的间隔时间执行任务调度的功能。

Jdk也为我们提供了相关支持，如Timer、ScheduledExecutor，下边我们了解下。

- Timer方式

```java
public static void main(String[] args){  
    Timer timer = new Timer();  
    timer.schedule(new TimerTask(){
        @Override  
        public void run() {  
           //TODO：something
        }  
    }, 1000, 2000);  //1秒后开始调度，每2秒执行一次
}

```

Timer的优点在于简单易用，每个Timer对应一个线程，因此可以同时启动多个Timer并行执行多个任务，同一个Timer中的任务是串行执行。

- ScheduledExecutor

```java
public static void main(String [] agrs){
    ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
    service.scheduleAtFixedRate(
            new Runnable() {
                @Override
                public void run() {
                    //TODO：something
                    System.out.println("todo something");
                }
            }, 1,
            2, TimeUnit.SECONDS);
}

```

Java 5 推出了基于线程池设计的 ScheduledExecutor，其设计思想是，每一个被调度的任务都会由线程池中一个线程去执行，因此任务是并发执行的，相互之间不会受到干扰。

​    Timer 和 ScheduledExecutor 都仅能提供基于开始时间与重复间隔的任务调度，不能胜任更加复杂的调度需求。比如，设置每月第一天凌晨1点执行任务、复杂调度任务的管理、任务间传递数据等等。

​    Quartz 是一个功能强大的任务调度框架，它可以满足更多更复杂的调度需求，Quartz 设计的核心类包括 Scheduler, Job 以及 Trigger。其中，Job 负责定义需要执行的任务，Trigger 负责设置调度策略，Scheduler 将二者组装在一起，并触发任务开始执行。Quartz支持简单的按时间间隔调度、还支持按日历调度方式，通过设置CronTrigger表达式（包括：秒、分、时、日、月、周、年）进行任务调度。

- 第三方Quartz方式实现

```java
public static void main(String [] agrs) throws SchedulerException {
    //创建一个Scheduler
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = schedulerFactory.getScheduler();
    //创建JobDetail
    JobBuilder jobDetailBuilder = JobBuilder.newJob(MyJob.class);
    jobDetailBuilder.withIdentity("jobName","jobGroupName");
    JobDetail jobDetail = jobDetailBuilder.build();
    //创建触发的CronTrigger 支持按日历调度
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName", "triggerGroupName")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
        //创建触发的SimpleTrigger 简单的间隔调度
        /*SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName","triggerGroupName")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();*/
    scheduler.scheduleJob(jobDetail,trigger);
    scheduler.start();
}
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        System.out.println("todo something");
    }
}


```

通过以上内容我们学习了什么是任务调度，任务调度所解决的问题，以及任务调度的多种实现方式。

**什么是分布式任务调度？**

通常任务调度的程序是集成在应用中的，比如：优惠卷服务中包括了定时发放优惠卷的的调度程序，结算服务中包括了定期生成报表的任务调度程序，由于采用分布式架构，一个服务往往会部署多个冗余实例来运行我们的业务，在这种分布式系统环境下运行任务调度，我们称之为**分布式任务调度**，如下图：

![image-20230312212109189](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312212109189.png)

**分布式调度要实现的目标：**

​    不管是任务调度程序集成在应用程序中，还是单独构建的任务调度系统，如果采用分布式调度任务的方式就相当于将任务调度程序分布式构建，这样就可以具有分布式系统的特点，并且提高任务的调度处理能力：

1、并行任务调度

​    并行任务调度实现靠多线程，如果有大量任务需要调度，此时光靠多线程就会有瓶颈了，因为一台计算机CPU的处理能力是有限的。

​    如果将任务调度程序分布式部署，每个结点还可以部署为集群，这样就可以让多台计算机共同去完成任务调度，我们可以将任务分割为若干个分片，由不同的实例并行执行，来提高任务调度的处理效率。

2、高可用

​    若某一个实例宕机，不影响其他实例来执行任务。

3、弹性扩容

​    当集群中增加实例就可以提高并执行任务的处理效率。

4、任务管理与监测

​    对系统中存在的所有定时任务进行统一的管理及监测。让开发人员及运维人员能够时刻了解任务执行情况，从而做出快速的应急处理响应。

5、避免任务重复执行

​    当任务调度以集群方式部署，同一个任务调度可能会执行多次，比如在上面提到的电商系统中到点发优惠券的例子，就会发放多次优惠券，对公司造成很多损失，所以我们需要控制相同的任务在多个运行实例上只执行一次。

##### 4.7.1.2 XXL-JOB介绍

XXL-JOB是一个轻量级分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。

官网：https://www.xuxueli.com/xxl-job/

文档：https://www.xuxueli.com/xxl-job/#%E3%80%8A%E5%88%86%E5%B8%83%E5%BC%8F%E4%BB%BB%E5%8A%A1%E8%B0%83%E5%BA%A6%E5%B9%B3%E5%8F%B0XXL-JOB%E3%80%8B

XXL-JOB主要有调度中心、执行器、任务：

![image-20230312212325580](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312212325580.png)

- 调度中心

负责管理调度信息，按照调度配置发出调度请求，自身不承担业务代码；

主要职责为执行器管理、任务管理、监控运维、日志管理等

- 任务执行器

负责接收调度请求并执行任务逻辑；

​    只要职责是注册服务、任务执行服务（接收到任务后会放入线程池中的任务队列）、执行结果上报、日志服务等

**任务：**负责执行具体的业务处理。

调度中心与执行器之间的工作流程如下：

![image-20230312212423099](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312212423099.png)

**执行流程：**

​    1.任务执行器根据配置的调度中心的地址，自动注册到调度中心

​	2.达到任务触发条件，调度中心下发任务

​    3.执行器基于线程池执行任务，并把执行结果放入内存队列中、把执行日志写入日志文件中

​    4.执行器消费内存队列中的执行结果，主动上报给调度中心

​    5.当用户在调度中心查看任务日志，调度中心请求任务执行器，任务执行器读取任务日志文件并返回日志详情

##### 4.7.1.3  搭建XXL-JOB

###### 4.7.1.3.1 调度中心

首先下载XXL-JOB

GitHub：https://github.com/xuxueli/xxl-job

码云：https://gitee.com/xuxueli0323/xxl-job

项目使用2.3.1版本： https://github.com/xuxueli/xxl-job/releases/tag/2.3.1

也可从课程资料目录获取，解压xxl-job-2.3.1.zip

使用IDEA打开解压后的目录

![image-20230312212840105](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312212840105.png)

xxl-job-admin：调度中心

xxl-job-core：公共依赖

xxl-job-executor-samples：执行器Sample示例（选择合适的版本执行器，可直接使用）

  ：xxl-job-executor-sample-springboot：Springboot版本，通过Springboot管理执行器，推荐这种方式；

  ：xxl-job-executor-sample-frameless：无框架版本；

doc :文档资料，包含数据库脚本

![image-20230312213036054](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312213036054.png)



![image-20230312213224992](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312213224992.png)



执行sh /data/soft/restart.sh自动启动xxl-job

访问：http://localhost:8088/xxl-job-admin/

账号和密码：admin/123456

如果无法使用虚拟机运行xxl-job可以在本机idea运行xxl-job调度中心。

启动前注意修改配置文件

![image-20230312213416887](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312213416887.png)



###### 4.7.1.3.2 执行器

下边配置执行器，执行器负责与调度中心通信接收调度中心发起的任务调度请求。

1、首先在媒资管理模块的service工程添加依赖，在项目的父工程已约定了版本2.3.1

```xml
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-job-core</artifactId>
</dependency>

```

![image-20230312214355944](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312214355944.png)

2、在nacos下的media-service-dev.yaml下配置xxl-job

```yaml
xxl:
  job:
    admin: 
      addresses: http://localhost:8088/xxl-job-admin
    executor:
      appname: media-process-service #执行器名称
      address: 
      ip: 
      port: 9999  # 本机回调端口
      logpath: /data/applogs/xxl-job/jobhandler
      logretentiondays: 30
    accessToken: default_token

```

![image-20230312214201419](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312214201419.png)



注意配置中的appname这是执行器的应用名，稍后在调度中心配置执行器时要使用。

3、配置xxl-job的执行器

将示例工程下配置类拷贝到媒资管理的service工程下



![image-20230312214637615](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312214637615.png)



![image-20230312214616782](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312214616782.png)

4、下边进入调度中心添加执行器

![image-20230312214720775](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312214720775.png)

点击新增，填写执行器信息，appname是前边在nacos中配置xxl信息时指定的执行器的应用名。

![image-20230312214814369](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312214814369.png)





![image-20230312214900380](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312214900380.png)



到此完成媒资管理模块service工程配置xxl-job执行器，在xxl-job调度中心添加执行器，下边准备测试执行器与调度中心是否正常通信，因为接口工程依赖了service工程，所以启动媒资管理模块的接口工程。

启动后观察日志，出现下边的日志表示执行器在调度中心注册成功

```shell
2023-03-12 21:59:56,779 INFO [main][XxlJobConfig.java:46] - >>>>>>>>>>> xxl-job config init.
```

![image-20230312220045896](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312220045896.png)

###### 4.7.1.3.3 执行任务

下边编写任务，任务类的编写方法参考示例工程，如下图：

![image-20230312220227192](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312220227192.png)

在service包下新建jobhandler存放任务类，下边参考示例工

```java
package com.xuecheng.media.service.jobhandler;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author woldier
 * @version 1.0
 * @description 任务处理类
 * @date 2023/3/12 22:03
 **/
@Component
public class SampleJob {
    private static Logger logger = LoggerFactory.getLogger(SampleJob.class);
    /**
    * @description Bean模式
    *
    * @return void
    * @author: woldier
    * @date: 2023/3/12 22:05
    */
    @XxlJob("testJob")
    public void testJob(){
        logger.debug(">>>>>>>>>>>job test");
    }
}

```

下边在调度中心添加任务，进入任务管理

![image-20230312220740103](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312220740103.png)

点击新增,天界任务信息

![image-20230312220927925](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312220927925.png)

调度类型选择Cron，并配置Cron表达式设置定时策略。

Cron表达式是一个字符串，通过它可以定义调度策略，格式如下：

{秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}

xxl-job提供图形界面去配置：

![image-20230312220956739](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312220956739.png)

一些例子如下：

30 10 1 * * ? 每天1点10分30秒触发

0/30 * * * * ? 每30秒触发一次

\* 0/10 * * * ? 每10分钟触发一次

运行模式有BEAN和GLUE，bean模式较常用就是在项目工程中编写执行器的任务代码，GLUE是将任务代码编写在调度中心。

JobHandler任务方法名填写@XxlJob注解中的名称。

![image-20230312221103628](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312221103628.png)

添加成功，启动任务

![image-20230312221152266](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312221152266.png)

下边启动媒资管理的service工程，启动执行器。

观察执行器方法的执行。

```shell
2023-03-12 22:12:21,247 DEBUG [xxl-job, JobThread-2-1678630323338][SampleJob.java:26] - >>>>>>>>>>>job test
2023-03-12 22:12:24,228 DEBUG [xxl-job, JobThread-2-1678630323338][SampleJob.java:26] - >>>>>>>>>>>job test
```

如果要停止任务需要在调度中心操作

![image-20230312221403081](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312221403081.png)

任务跑一段时间注意清理日志

![image-20230312221455901](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312221455901.png)

###### 4.7.1.4 分片广播

掌握了xxl-job的基本使用，下边思考如何进行分布式任务处理呢？如下图，我们会启动多个执行器组成一个集群，去执行任务。

![image-20230312221606223](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312221606223.png)

执行器在集群部署下调度中心有哪些调度策略呢？

查看xxl-job官方文档，阅读高级配置相关的内容：

```shell
高级配置：
    - 路由策略：当执行器集群部署时，提供丰富的路由策略，包括；
        FIRST（第一个）：固定选择第一个机器；
        LAST（最后一个）：固定选择最后一个机器；
        ROUND（轮询）：；
        RANDOM（随机）：随机选择在线的机器；
        CONSISTENT_HASH（一致性HASH）：每个任务按照Hash算法固定选择某一台机器，且所有任务均匀散列在不同机器上。
        LEAST_FREQUENTLY_USED（最不经常使用）：使用频率最低的机器优先被选举；
        LEAST_RECENTLY_USED（最近最久未使用）：最久未使用的机器优先被选举；
        FAILOVER（故障转移）：按照顺序依次进行心跳检测，第一个心跳检测成功的机器选定为目标执行器并发起调度；
        BUSYOVER（忙碌转移）：按照顺序依次进行空闲检测，第一个空闲检测成功的机器选定为目标执行器并发起调度；
        SHARDING_BROADCAST(分片广播)：广播触发对应集群中所有机器执行一次任务，同时系统自动传递分片参数；可根据分片参数开发分片任务；

```

第一个：每次调度选择集群中第一台执行器。

最后一个：每次调度选择集群中最后一台执行器。

轮询：按照顺序每次调度选择一台执行器去调度。

随机：每次调度随机选择一台执行器去调度。

CONSISTENT_HASH：按任务的hash值选择一台执行器去调度。

其它策略请自行阅读文档。

下边要重点说的是分片广播策略，分片是指是调度中心将集群中的执行器标上序号：0，1，2，3...，广播是指每次调度会向集群中所有执行器发送调度请求，请求中携带分片参数。

如下图：

![image-20230312221719599](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312221719599.png)





每个执行器收到调度请求根据分片参数自行决定是否执行任务。

另外xxl-job还支持动态分片，当执行器数量有变更时，调度中心会动态修改分片的数量。

**作业分片适用哪些场景呢？**

- •    分片任务场景：10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍；
- •    广播任务场景：广播执行器同时运行shell脚本、广播集群节点进行缓存更新等。

所以，广播分片方式不仅可以充分发挥每个执行器的能力，并且根据分片参数可以控制任务是否执行，最终灵活控制了执行器集群分布式处理任务。

**使用说明：**

"分片广播" 和普通任务开发流程一致，不同之处在于可以获取分片参数进行分片业务处理。

Java语言任务获取分片参数方式：

BEAN、GLUE模式(Java)，可参考Sample示例执行器中的示例任务"ShardingJobHandler"

```java
@XxlJob("shardingJobHandler")
public void shardingJobHandler() throws Exception {
    // 分片序号，从0开始
    int shardIndex = XxlJobHelper.getShardIndex();
    // 分片总数
    int shardTotal = XxlJobHelper.getShardTotal();
    log.info("分片参数：当前分片序号 = {}, 总分片数 = {}", shardIndex, shardTotal);
log.info("开始执行第"+shardIndex+"批任务");

}
```

下边测试作业分片：

1、定义作业分片的任务方法

代码如上所示

2、在调度中心添加任务

![image-20230312222133712](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312222133712.png)

高级配置说明：

```shell
- 子任务：每个任务都拥有一个唯一的任务ID(任务ID可以从任务列表获取)，当本任务执行结束并且执行成功时，将会触发子任务ID所对应的任务的一次主动调度，通过子任务可以实现一个任务执行完成去执行另一个任务。
    - 调度过期策略：
     - 忽略：调度过期后，忽略过期的任务，从当前时间开始重新计算下次触发时间；
        - 立即执行一次：调度过期后，立即执行一次，并从当前时间开始重新计算下次触发时间；
    - 阻塞处理策略：调度过于密集执行器来不及处理时的处理策略；
        单机串行（默认）：调度请求进入单机执行器后，调度请求进入FIFO队列并以串行方式运行；
        丢弃后续调度：调度请求进入单机执行器后，发现执行器存在运行的调度任务，本次请求将会被丢弃并标记为失败；
        覆盖之前调度：调度请求进入单机执行器后，发现执行器存在运行的调度任务，将会终止运行中的调度任务并清空队列，然后运行本地调度任务；
    - 任务超时时间：支持自定义任务超时时间，任务运行超时将会主动中断任务；
    - 失败重试次数；支持自定义任务失败重试次数，当任务失败时将会按照预设的失败重试次数主动进行重试；


```

添加完成后,启动任务，观察日志

启动两个服务,此流程在nacos小结有提到过.

![image-20230312222418299](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312222418299.png)

![image-20230312222540831](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312222540831.png)

nacos开启本地优先

![image-20230312222702570](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312222702570.png)

启动两个任务,在xxl-job中检测到两个节点即可启动任务

![image-20230312222950795](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312222950795.png)

启动任务后,查看两个服务控制台的内容

节点0

```shell
2023-03-12 22:31:20,225 INFO [xxl-job, JobThread-3-1678631430349][SampleJob.java:36] - 分片参数：当前分片序号 = 0, 总分片数 = 2
2023-03-12 22:31:20,226 INFO [xxl-job, JobThread-3-1678631430349][SampleJob.java:37] - 开始执行第0批任务

2023-03-12 22:31:25,237 INFO [xxl-job, JobThread-3-1678631430349][SampleJob.java:36] - 分片参数：当前分片序号 = 0, 总分片数 = 2
2023-03-12 22:31:25,237 INFO [xxl-job, JobThread-3-1678631430349][SampleJob.java:37] - 开始执行第0批任务
```



节点1

```java
2023-03-12 22:30:40,422 INFO [xxl-job, JobThread-3-1678631430608][SampleJob.java:36] - 分片参数：当前分片序号 = 1, 总分片数 = 2
2023-03-12 22:30:40,422 INFO [xxl-job, JobThread-3-1678631430608][SampleJob.java:37] - 开始执行第1批任务

2023-03-12 22:30:45,567 INFO [xxl-job, JobThread-3-1678631430608][SampleJob.java:36] - 分片参数：当前分片序号 = 1, 总分片数 = 2
2023-03-12 22:30:45,568 INFO [xxl-job, JobThread-3-1678631430608][SampleJob.java:37] - 开始执行第1批任务
```



#### 4.7.2 需求分析

##### 4.7.2.1 作业分片方案

掌握了xxl-job的作业分片调度方式，下边思考如何分布式去执行学成在线平台中的视频处理任务。

任务添加成功后，对于要处理的任务会添加到待处理任务表中，现在启动多个执行器实例去查询这些待处理任务，此时如掌握了xxl-job的作业分片调度方式，下边思考如何分布式去执行学成在线平台中的视频处理任务。

任务添加成功后，对于要处理的任务会添加到待处理任务表中，现在启动多个执行器实例去查询这些待处理任务，此时如何保证多个执行器不会重复执行任务？

执行器收到调度请求后各自己查询属于自己的任务，这样就保证了执行器之间不会重复执行任务。

xxl-job设计作业分片就是为了分布式执行任务，XXL-JOB并不直接提供数据处理的功能，它只会给执行器分配好分片序号并向执行器传递分片总数、分片序号这些参数，开发者需要自行处理分片项与真实数据的对应关系。

下图表示了多个执行器获取视频处理任务的结构：

![image-20230312223308636](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312223308636.png)

每个执行器收到广播任务有两个参数：分片总数、分片序号。每个执行从数据表取任务时可以让任务id 模上 分片总数，如果等于分片序号则执行此任务。

上边两个执行器实例那么分片总数为2，序号为0、1，从任务1开始，如下：

1 % 2 = 1  执行器2执行

2 % 2 = 0  执行器1执行

3 % 2 = 1   执行器2执行

以此类推.

##### 4.7.2.2 保证任务不重复执行

通过作业分片方案保证了执行器之间分配的任务不重复，另外如果同一个执行器在处理一个视频还没有完成，此时调度中心又一次请求调度，为了不重复处理同一个视频该怎么办？

首先配置调度过期策略：

查看文档如下：

  \- 调度过期策略：
     \- 忽略：调度过期后，忽略过期的任务，从当前时间开始重新计算下次触发时间；
     \- 立即执行一次：调度过期后，立即执行一次，并从当前时间开始重新计算下次触发时间；
   \- 阻塞处理策略：调度过于密集执行器来不及处理时的处理策略；

这里我们选择忽略，如果立即执行一次可能会重复调度。

![image-20230312223424481](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312223424481.png)

其次，再看阻塞处理策略，阻塞处理策略就是当前执行器正在执行任务还没有结束时调度时间到达到，此时该如何处理。

查看文档如下：
     单机串行（默认）：调度请求进入单机执行器后，调度请求进入FIFO队列并以串行方式运行；
     丢弃后续调度：调度请求进入单机执行器后，发现执行器存在运行的调度任务，本次请求将会被丢弃并标记为失败；
     覆盖之前调度：调度请求进入单机执行器后，发现执行器存在运行的调度任务，将会终止运行中的调度任务并清空队列，然后运行本地调度任务；

这里选择 丢弃后续调度，避免重复调度。

最后，也就是要注意保证任务处理的幂等性，什么是任务的幂等性？任务的幂等性是指：对于数据的操作不论多少次，操作的结果始终是一致的。执行器接收调度请求去执行任务，要有办法去判断该任务是否处理完成，如果处理完则不再处理，即使重复调度处理相同的任务也不能重复处理相同的视频。

什么是幂等性？

它描述了一次和多次请求某一个资源对于资源本身应该具有同样的结果。

幂等性是为了解决重复提交问题，比如：恶意刷单，重复支付

等。

解决幂等性常用的方案：

1）数据库约束，比如：唯一索引，主键。

2）乐观锁，常用于数据库，更新数据时根据乐观锁状态去更新。

3）唯一序列号，操作传递一个唯一序列号，操作时判断与该序列号相等则执行。

这里我们在数据库视频处理表中添加处理状态字段，视频处理完成更新状态为完成，执行视频处理前判断状态是否完成，如果完成则不再处理。

##### 4.7.2.3 业务流程

确定了分片方案，下边梳理整个视频上传及处理的业务流程

![image-20230312223613813](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312223613813.png)

上传视频成功向视频处理待处理表添加记录。

![image-20230312223733375](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312223733375.png)



1、任务调度中心广播作业分片。

2、执行器收到广播作业分片，从数据库读取待处理任务。

3、执行器根据任务内容从MinIO下载要处理的文件。

4、执行器启动多线程去处理任务。

5、任务处理完成，上传处理后的视频到MinIO。

6、将更新任务处理结果，如果视频处理完成除了更新任务处理结果以外还要将文件的访问地址更新至任务处理表及文件表中，最后将任务完成记录写入历史表。

下图是待处理任务表：

![image-20230312223823236](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312223823236.png)

完成任务历史表与结果与待处理任务相同。

#### 4.7.3 查询待处理任务

##### 4.7.3.1 添加待处理任务

上传视频成功向视频处理待处理表添加记录，暂时只添加对avi视频的处理记录。

根据MIME Type去判断是否是avi视频，下边列出部分MIME Type

![image-20230312223920949](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230312223920949.png)

avi视频的MIME Type是video/x-msvideo

修改文件信息入库方法，添加对于后缀名得到的contentType判断,若为MP4或者image则进行url入库,如下：

```java
/**
     * @param companyId           公司id
     * @param uploadFileParamsDto 上传参数信息
     * @param md5                 md5
     * @param bucket              桶
     * @param objectName          对象名
     * @return com.xuecheng.media.model.po.MediaFiles
     * @description 插入数据库
     * @author: woldier
     * @date: 2023/3/10 15:21
     */
    @Transactional
    public MediaFiles insertMediaFile2DB(Long companyId, UploadFileParamsDto uploadFileParamsDto, String md5, String bucket, String objectName) {
        /*添加数据库之前,根据md5查询该文件是否已经存在*/
        MediaFiles files = mediaFilesMapper.selectById(md5);
        if (files == null) {
            /*生成数据库entity*/
            MediaFiles mediaFiles = new MediaFiles();
            BeanUtils.copyProperties(uploadFileParamsDto, mediaFiles);
            //设置uploadFileParamsDto中不存在的部分
            //设置id
            mediaFiles.setId(md5);
            //机构id
            mediaFiles.setCompanyId(companyId);
            //bucket
            mediaFiles.setBucket(bucket);
            //存储路径
            mediaFiles.setFilePath(objectName);
            //file_id
            mediaFiles.setFileId(md5);
            //url
            //设置url字段需要判断当前的contentType是否为image或者mp4，只有这两种格式才能设置url直接预览
            String mimeType = getMimeType(uploadFileParamsDto.getFilename());//获取文件content-type
            if(mimeType.contains("image")||mimeType.contains("mp4"))//若为图片或者mp4格式视频设置url
                mediaFiles.setUrl("/" + bucket + "/" + objectName);
            //上传时间,更新时间自动设置
            mediaFiles.setCreateDate(LocalDateTime.now());
            mediaFiles.setCreateDate(LocalDateTime.now());
            //文件状态
            mediaFiles.setStatus("1");
            //审核状态
            mediaFiles.setAuditStatus(MediaAuditStatus.Approved.getCode());

            int insert = mediaFilesMapper.insert(mediaFiles);

            /*
            * 若为avi格式，则插入转码任务
            * */
            getAopContextProxy().insertMediaProcessTask(md5, bucket, objectName, mimeType);


            if (insert <= 0) {
                log.debug("向数据库保存文件失败,bucket:{},objectName{}", fileBucket, objectName);
                return null;
            }
            return mediaFiles;
        }
        return files;
    }

    @Transactional(propagation = Propagation.MANDATORY) //设置传播级别为调用本方法的方法必须具有事务
    public  void insertMediaProcessTask(String md5, String bucket, String objectName, String mimeType) {
        if("video/x-msvideo".equals(mimeType)){
            MediaProcess mediaProcess = new MediaProcess();
            mediaProcess.setBucket(bucket); //设置桶
            mediaProcess.setFilename(objectName); //设置对象名
            mediaProcess.setFileId(md5); //设置md5
            mediaProcess.setCreateDate(LocalDateTime.now());  //设置创建时间
            mediaProcess.setStatus(MediaProcessStat.Pending.getCode()); //设置处理状态
        }
    }

/**
    * @description 获取代理对象
    *
    * @return com.xuecheng.media.service.MediaFileService
    * @author: woldier
    * @date: 2023/3/13 9:20
    */
    private MediaFileService getAopContextProxy(){
        return (MediaFileService) AopContext.currentProxy();
    }
```



##### 4.7.3.2 查询待处理任务

如何保证查询到的待处理视频记录不重复？

编写根据分片参数获取待处理任务的DAO方法，定义DAO接口如下：

```java
package com.xuecheng.media.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.media.model.po.MediaProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author woldier
 */
@Mapper
public interface MediaProcessMapper extends BaseMapper<MediaProcess> {
    /**
    * @description 分片节点获取任务 
    * @param shardTotal  节点总数
     * @param shardindex  当前节点的id
     * @param count  获取的条数
    * @return java.util.List<com.xuecheng.media.model.po.MediaProcess> 
    * @author: woldier 
    * @date: 2023/3/13 11:05
    */
    @Select("SELECT t.* FROM media_process t WHERE t.id % #{shardTotal} = #{shardindex} and (t.status='1' or t.status='3') and t.fail_count < 3 limit #{count}")
    List<MediaProcess> selectListByShardIndex(@Param("shardTotal") int shardTotal, @Param("shardindex") int shardindex, @Param("count") int count);
}


```

定义Service接口，查询待处理

```java
package com.xuecheng.media.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.media.model.po.MediaProcess;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itcast
 * @since 2023-03-10
 */
public interface MediaProcessService extends IService<MediaProcess> {
    /**
     * @description 分片节点获取任务
     * @param shardTotal  节点总数
     * @param shardindex  当前节点的id
     * @param count  获取的条数
     * @return java.util.List<com.xuecheng.media.model.po.MediaProcess>
     * @author: woldier
     * @date: 2023/3/13 11:05
     */
    List<MediaProcess> selectListByShardIndex(int shardTotal, int shardindex, int count);
}

```

service接口实现

```java
package com.xuecheng.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.media.mapper.MediaProcessMapper;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class MediaProcessServiceImpl extends ServiceImpl<MediaProcessMapper, MediaProcess> implements MediaProcessService {

    /**
     * @description 分片节点获取任务
     * @param shardTotal  节点总数
     * @param shardindex  当前节点的id
     * @param count  获取的条数
     * @return java.util.List<com.xuecheng.media.model.po.MediaProcess>
     * @author: woldier
     * @date: 2023/3/13 11:05
     */
    @Override
    public List<MediaProcess> selectListByShardIndex(int shardTotal, int shardindex, int count) {
        return baseMapper.selectListByShardIndex(shardTotal,shardindex,count);
    }
}

```











#### 4.7.5 分布式锁与视频编码

##### 4.7.5.1 什么是视频编码

视频上传成功后需要对视频进行转码处理。

什么是视频编码？查阅百度百科如下：

![image-20230313141432516](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230313141432516.png)

详情参考 ：[https://baike.baidu.com/item/%E8%A7%86%E9%A2%91%E7%BC%96%E7%A0%81/839038](https://baike.baidu.com/item/视频编码/839038)

首先我们要分清文件格式和编码格式：

文件格式：是指.mp4、.avi、.rmvb等 这些不同扩展名的视频文件的文件格式 ，视频文件的内容主要包括视频和音频，其文件格式是按照一 定的编码格式去编码，并且按照该文件所规定的封装格式将视频、音频、字幕等信息封装在一起，播放器会根据它们的封装格式去提取出编码，然后由播放器解码，最终播放音视频。

音视频编码格式：通过音视频的压缩技术，将视频格式转换成另一种视频格式，通过视频编码实现流媒体的传输。比如：一个.avi的视频文件原来的编码是a，通过编码后编码格式变为b，音频原来为c，通过编码后变为d。

音视频编码格式各类繁多，主要有几下几类：

MPEG系列

（由ISO[国际标准组织机构]下属的MPEG[运动图象专家组]开发 ）视频编码方面主要是Mpeg1（vcd用的就是它）、Mpeg2（DVD使用）、Mpeg4（的DVDRIP使用的都是它的变种，如：divx，xvid等）、Mpeg4 AVC（正热门）；音频编码方面主要是MPEG Audio Layer 1/2、MPEG Audio Layer 3（大名鼎鼎的mp3）、MPEG-2 AAC 、MPEG-4 AAC等等。注意：DVD音频没有采用Mpeg的。

H.26X系列

（由ITU[国际电传视讯联盟]主导，侧重网络传输，注意：只是视频编码）

包括H.261、H.262、H.263、H.263+、H.263++、H.264（就是MPEG4 AVC-合作的结晶）

目前最常用的编码标准是视频H.264，音频AAC。

提问：

H.264是编码格式还是文件格式？

mp4是编码格式还是文件格式？

##### 4.7.5.2 FFmpeg基本使用

我们将视频录制完成后，使用视频编码软件对视频进行编码，本项目 使用FFmpeg对视频进行编码 。

![image-20230313141536016](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230313141536016.png)

FFmpeg被许多开源项目采用，QQ影音、暴风影音、VLC等。

下载：FFmpeg https://www.ffmpeg.org/download.html#build-windows

请从课程资料目录解压ffmpeg.zip,并将解压得到的exe文件加入环境变量。

测试是否正常：cmd运行 ffmpeg -v



安装成功，作下简单测试

将一个.avi文件转成mp4、mp3、gif等。

比如我们将nacos.avi文件转成mp4，运行如下命令：

D:\soft\ffmpeg\ffmpeg.exe -i 1.avi 1.mp4

可以将ffmpeg.exe配置到环境变量path中，进入视频目录直接运行：ffmpeg.exe -i 1.avi 1.mp4

转成mp3：ffmpeg -i nacos.avi nacos.mp3

转成gif：ffmpeg -i nacos.avi nacos.gif

官方文档（英文）：http://ffmpeg.org/ffmpeg.html



##### 4.7.5.3 视频处理工具类

将课程资料目录中的util.zip解压，将解压出的工具类拷贝至base工程。

![image-20230313144730929](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230313144730929.png)

其中Mp4VideoUtil类是用于将视频转为mp4格式，是我们项目要使用的工具类。

下边看下这个类的代码，并进行测试。

我们要通过ffmpeg对视频转码，Java程序调用ffmpeg，使用java.lang.ProcessBuilder去完成，具体在Mp4VideoUtil类的63行，下边进行简单的测试，下边的代码运行本机安装的QQ软件。

```java
ProcessBuilder builder = new ProcessBuilder();
builder.command("C:\\Program Files (x86)\\Tencent\\QQ\\Bin\\QQScLauncher.exe");
//将标准输入流和错误输入流合并，通过标准输入流程读取信息
builder.redirectErrorStream(true);
Process p = builder.start();

```

对Mp4VideoUtil类需要学习使用方法，下边代码将一个avi视频转为mp4视频，如下：

```java
public static void main(String[] args) throws IOException {
    //ffmpeg的路径
    String ffmpeg_path = "D:\\soft\\ffmpeg\\ffmpeg.exe";//ffmpeg的安装位置
    //源avi视频的路径
    String video_path = "D:\\develop\\bigfile_test\\nacos01.avi";
    //转换后mp4文件的名称
    String mp4_name = "nacos01.mp4";
    //转换后mp4文件的路径
    String mp4_path = "D:\\develop\\bigfile_test\\nacos01.mp4";
    //创建工具类对象
    Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpeg_path,video_path,mp4_name,mp4_path);
    //开始视频转换，成功将返回success
    String s = videoUtil.generateMp4();
    System.out.println(s);
}

```



##### 4.7.5.4 分布式锁

为了避免多线程去争抢同一个任务可以使用synchronized同步锁去解决，如下代码：

```java
synchronized(锁对象){
   执行任务...
}

```

synchronized只能保证同一个虚拟机中多个线程去争抢锁。

![image-20230313145121763](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230313145121763.png)

如果是多个执行器分布式部署，并不能保证同一个视频只有一个执行器去处理。

现在要实现分布式环境下所有虚拟机中的线程去同步执行就需要让多个虚拟机去共用一个锁，虚拟机可以分布式部署，锁也可以分布式部署，如下图：

![image-20230313145140611](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230313145140611.png)

虚拟机都去抢占同一个锁，锁是一个单独的程序提供加锁、解锁服务。

该锁已不属于某个虚拟机，而是分布式部署，由多个虚拟机所共享，这种锁叫分布式锁。

实现分布式锁的方案有很多，常用的如下：

1、基于数据库实现分布锁

利用数据库主键唯一性的特点，或利用数据库唯一索引、行级锁的特点，多个线程同时去更新相同的记录，谁更新成功谁就抢到锁。

2、基于redis实现锁

redis提供了分布式锁的实现方案，比如：SETNX、set nx、redisson等。

拿SETNX举例说明，SETNX命令的工作过程是去set一个不存在的key，多个线程去设置同一个key只会有一个线程设置成功，设置成功的的线程拿到锁。

3、使用zookeeper实现

zookeeper是一个分布式协调服务，主要解决分布式程序之间的同步的问题。zookeeper的结构类似的文件目录，多线程向zookeeper创建一个子目录(节点)只会有一个创建成功，利用此特点可以实现分布式锁，谁创建该结点成功谁就获得锁。

下边基于数据库方式实现分布锁，开始执行任务将任务执行状态更新为4表示任务执行中。

下边的sql语句可以实现更新操作：

```java
update media_process m set m.status='4' where  m.id=?
```

如果是多个线程去执行该sql都将会执行成功，但需求是只能有一个线程抢到锁，所以此sql无法满足需求。

下边使用乐观锁的方式实现更新操作：

```sql
update media_process m set m.status='4' where (m.status='1' or m.status='3') and m.fail_count<3 and m.id=?
```

多个线程同时执行上边的sql只会有一个线程执行成功。

什么是乐观锁、悲观锁？

synchronized是一种悲观锁，在执行被synchronized包裹的代码时需要首先获取锁，没有拿到锁则无法执行，是总悲观的认为别的线程会去抢，所以要悲观锁。

乐观锁的思想是它不认为会有线程去争抢，尽管去执行，如果没有执行成功就再去重试。

实现如下:

1. 定义mapper

```java
public interface MediaProcessMapper extends BaseMapper<MediaProcess> {

    /**
     * 开启一个任务
     * @param id 任务id
     * @return 更新记录数
     */
    @Update("update media_process m set m.status='4' where (m.status='1' or m.status='3') and m.fail_count<3 and m.id=#{id}")
    int startTask(@Param("id") long id);

}

```

2. service方法

```java
/**
     *  开启一个任务
     * @param id 任务id
     * @return true开启任务成功，false开启任务失败
     */
    public boolean startTask(long id);
```



3. service实现

```java
/**
     *  开启一个任务
     * @param id 任务id
     * @return true开启任务成功，false开启任务失败
     */
    @Override
    public boolean startTask(long id) {
        if (baseMapper.startTask(id)>0) return true;
        return false;
    }
```

#### 4.7.6 更新业务状态

任务处理完成需要更新任务处理结果，任务执行成功更新视频的URL、及任务处理结果，将待处理任务记录删除，同时向历史任务表添加记录。

在Service接口添加方法

任务处理完成需要更新任务处理结果，任务执行成功更新视频的URL、及任务处理结果，将待处理任务记录删除，同时向历史任务表添加记录。

在Service接口添加方法

```java
/**
    * @description 保存任务结果
    * @param taskId 任务id
     * @param status 状态
     * @param fileId 文件id
     * @param url 设置url
     * @param errorMsg  错误信息
    * @return void
    * @author: woldier 
    * @date: 2023/3/13 11:12
    */
    void saveProcessFinishStatus(Long taskId,String status,String fileId,String url,String errorMsg);
```

service实现方法如下

```java
 /**
     * @description 保存任务结果
     * @param taskId 任务id/
     * @param status 状态
     * @param fileId 文件id
     * @param url 设置url
     * @param errorMsg  错误信息
     * @return void
     * @author: woldier
     * @date: 2023/3/13 11:12
     */
    @Override
    @Transactional
    public void saveProcessFinishStatus(Long taskId, String status, String fileId, String url, String errorMsg) {
        /*
        * 任务处理完成需要更新任务处理结果，
        * 任务执行成功更新视频的URL、及任务处理结果，
        * 将待处理任务记录删除，
        * 同时向历史任务表添加记录
        * */
        MediaProcess mediaProcess = this.getById(taskId);
        if(mediaProcess==null) return; //查询到数据库未空,直接返回
        if(status.equals(MediaProcessStat.SUCCESS.getCode())){//如果执行成功
            //从数据库获取数据

            //设置状态
            mediaProcess.setStatus(MediaProcessStat.SUCCESS.getCode());
            //设置完成时间
            mediaProcess.setFinishDate(LocalDateTime.now());
            //从MediaProcess表删除,并且把数据插入到MediaProcessHistory
            this.removeById(taskId);
            //创建插入到MediaProcessHistory的对象
            MediaProcessHistory mediaProcessHistory = new MediaProcessHistory();
            BeanUtils.copyProperties(mediaProcess,mediaProcessHistory);
            mediaProcessHistoryService.save(mediaProcessHistory);
            //在MediaFile表中设置Url
            MediaFiles mediaFiles = new MediaFiles();
            mediaFiles.setId(fileId);
            mediaFiles.setUrl(url);
            mediaFileService.updateById(mediaFiles);


        }else if (status.equals(MediaProcessStat.FAILED.getCode())){ //执行失败
            //执行失败,设置状态未失败,设置错误信息,并且将失败次数加1
            baseMapper.updateFailInfo(taskId,MediaProcessStat.FAILED.getCode(), errorMsg);
        }

    }
```



#### 4.7.7 视频处理

视频采用并发处理，每个视频使用一个线程去处理，每次处理的视频数量不要超过cpu核心数,所有视频处理完成结束本次执行，为防止代码异常出现无限期等待则添加超时设置，到达超时时间还没有处理完成仍结束任务。

定义任务类VideoTask 如下

```java
package com.xuecheng.media.service.jobhandler;

import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.xuecheng.base.utils.Mp4VideoUtil;
import com.xuecheng.media.model.dto.MediaProcessStat;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaFileService;
import com.xuecheng.media.service.MediaProcessService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author woldier
 * @version 1.0
 * @description 任务处理类
 * @date 2023/3/12 22:03
 **/
@Component
@RequiredArgsConstructor
public class SampleJob {
    private final MediaFileService mediaFileService;
    private final MediaProcessService mediaProcessService;
    private static Logger logger = LoggerFactory.getLogger(SampleJob.class);

    @Value("${videoprocess.ffmpegpath}")
    private String ffmpegpath;

    /**
     * @return void
     * @description 分片模式
     * @author: woldier
     * @date: 2023/3/12 22:05
     */
    @XxlJob("videoJobHandler")
    public void videoJobHandler() throws Exception {
        /*
         * 1. 从数据库获取任务
         * 2. 从minio下载相应的文件
         * 3. 开始转码
         * 4. 向数据库保存操作结果
         * */
        // 分片序号，从0开始
        int shardIndex = XxlJobHelper.getShardIndex();
        // 分片总数
        int shardTotal = XxlJobHelper.getShardTotal();
        //取出cpu核心数作为一次处理数据的条数
        int processors = Runtime.getRuntime().availableProcessors();
        //从数据库取出数据
        List<MediaProcess> mediaProcessList = mediaProcessService.selectListByShardIndex(shardTotal, shardIndex, processors);
        //如果没有数据,直接退出
        if (mediaProcessList.isEmpty()) return;
        int size = mediaProcessList.size();
        //启动size个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(size);
        //计数器
        CountDownLatch countDownLatch = new CountDownLatch(size);

        //开启线程池处理任务
        mediaProcessList.forEach(
                mediaProcess -> {
                    threadPool.execute(() -> {
                                /*
                                 * 1.获取id
                                 * 2.抢占任务
                                 * 3.下载文件
                                 * 4.处理文件
                                 * */
                                Long id = mediaProcess.getId();
                                //抢占任务不成功直接返回
                                if (!mediaProcessService.startTask(id)) {
                                    logger.debug("抢占任务不成功");
                                    return;
                                }
                                //从minio下载文件
                                File minIODownLoad = mediaFileService.minIODownLoad(mediaProcess.getBucket(), mediaProcess.getFilename());
                                if (minIODownLoad == null) {
                                    logger.debug("下载文件失败");
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, "文件下载失败");
                                    return;

                                }
                                //开始处理视频
                                File mp4File = null;
                                try {
                                    mp4File = File.createTempFile("mp4", ".mp4");
                                } catch (Exception e) {
                                    logger.debug("创建转码后文件失败");
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, "转码后创建文件失败");
                                    return;
                                }
                                //视频处理结果
                                String result = "";
                                try {
                                    //开始处理视频
                                    Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpegpath, minIODownLoad.getAbsolutePath(), mp4File.getName(), mp4File.getAbsolutePath());
                                    //开始视频转换，成功将返回success
                                    result = videoUtil.generateMp4();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    logger.error("处理视频文件:{},出错:{}", mediaProcess.getFilePath(), e.getMessage());
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, "处理视频文件出错");
                                }
                                if (!result.equals("success")) {
                                    //记录错误信息
                                    logger.error("处理视频失败,视频地址:{},错误信息:{}", mediaProcess.getBucket() + mediaProcess.getFilename(), result);

                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, result);

                                }
                                //上传到minio
                                String objectName = getFilePath(mediaProcess.getFileId(), ".mp4");
                                if (!mediaFileService.minIOUpload(mp4File.getPath(), getMimeType(objectName), mediaProcess.getBucket(), objectName)) {
                                    logger.error("上传文件出错");
                                    mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.FAILED.getCode(), mediaProcess.getFileId(), null, result);
                                }
                                //设置处理结果
                                mediaProcessService.saveProcessFinishStatus(mediaProcess.getId(), MediaProcessStat.SUCCESS.getCode(), mediaProcess.getFileId(), objectName, result);
                                minIODownLoad.delete();
                                mp4File.delete();
                                countDownLatch.countDown();
                            }
                    );

                }

        );
        //等待,给一个充裕的超时时间,防止无限等待，到达超时时间还没有处理完成则结束任务
        countDownLatch.await(30, TimeUnit.MINUTES);
    }


    /**
     * @param fileMd5 原文件md5
     * @param fileExt
     * @return java.lang.String
     * @description 获取对象存储路径
     * @author: woldier
     * @date: 2023/3/15 22:13
     */
    private String getFilePath(String fileMd5, String fileExt) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + fileExt;
    }

    /**
     * @param fileName 带后缀文件名 若为空 返回 MediaType.APPLICATION_OCTET_STREAM_VALUE;
     * @return java.lang.String
     * @description 根据文件后缀名获取MimeType
     * @author: woldier
     * @date: 2023/3/10 13:55
     */
    private String getMimeType(String fileName) {
        if (fileName == null) fileName = "";
        ContentInfo contentInfo = ContentInfoUtil.findExtensionMatch(fileName);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (contentInfo != null) mimeType = contentInfo.getMimeType();
        return mimeType;
    }

}

```

### 4.7.8 测试

##### 4.7.8.1 基本测试

进入xxl-job调度中心添加执行器和视频处理任务

在xxl-job配置任务调度策略：

1）配置阻塞处理策略为：丢弃后续调度

2）配置视频处理调度时间间隔不用根据视频处理时间去确定，可以配置的小一些，如：5分钟，即使到达调度时间如果视频没有处理完会丢弃调度请求。

配置完成开始测试视频处理：

1、首先上传至少4个视频，非mp4格式。

2、在xxl-job启动视频处理任务

3、观察媒资管理服务后台日志

##### 4.7.8.2 失败测试

1、先停止调度中心的视频处理任务。

2、上传视频，手动修改待处理任务表中file_path字段为一个不存在的文件地址

3、启动任务

观察任务处理失败后是否会重试，并记录失败次数。

##### 4.7.8.3 抢占式任务测试

1、修改调度中心中视频处理任务的阻塞处理策略为“覆盖之间的调度”

![image-20230316145716941](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316145716941.png)

2、在抢占任务代码处打断点并选择支持多线程方式

![image-20230316145905539](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316145905539.png)

3、在抢占任务代码处的下边两行代码分别打上断点，避免观察时代码继续执行。



4、启动任务

此时多个线程执行都停留在断点处

![image-20230316145945411](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316145945411.png)

依次放行，观察同一个任务只会被一个线程抢占成功。

![image-20230316150001899](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316150001899.png)



![image-20230316150010883](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316150010883.png)

#### 4.7.9 其他问题

##### 4.7.9.1 任务补偿机制

如果有线程抢占了某个视频的处理任务，如果线程处理过程中挂掉了，该视频的状态将会一直是处理中，其它线程将无法处理，这个问题需要用补偿机制。

单独启动一个任务找到待处理任务表中超过执行期限但仍在处理中的任务，将任务的状态改为执行失败。

任务执行期限是处理一个视频的最大时间，比如定为30分钟，通过任务的启动时间去判断任务是否超过执行期限。

大家思考这个sql该如何实现？

where 查询中判断开始时间是否和当前时间差30分钟

大家尝试自己实现此任务补偿机制。

##### 4.7.9.2 达到最大失败次数

当任务达到最大失败次数时一般就说明程序处理此视频存在问题，这种情况就需要人工处理，在页面上会提示失败的信息，人工可手动执行该视频进行处理，或通过其它转码工具进行视频转码，转码后直接上传mp4视频。





##### 4.7.9.3 分块文件清理问题

上传一个文件进行分块上传，上传一半不传了，之前上传到minio的分块文件要清理吗？怎么做的？

1、在数据库中有一张文件表记录minio中存储的文件信息。

2、文件开始上传时会写入文件表，状态为上传中，上传完成会更新状态为上传完成。

3、当一个文件传了一半不再上传了说明该文件没有上传完成，会有定时任务去查询文件表中的记录，如果文件未上传完成则删除minio中没有上传成功的文件目录。





### 4.8 绑定媒资

#### 4.8.1 需求分析

##### 4.8.1.1 业务流程

到目前为止，媒资管理已完成文件上传、视频处理、我的媒资功能等基本功能，其它模块可以使用媒资文件，本节要讲解课程计划绑定媒资文件。

如何将课程计划绑定媒资呢？

首先进入课程计划界面，然后选择要绑定的视频进行绑定即可。

具体的业务流程如下：

1.教育机构用户进入课程管理页面并编辑某一个课程，在"课程大纲"标签页的某一小节后可点击”添加视频“。

![image-20230316150712652](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316150712652.png)

2.弹出添加视频对话框，可通过视频关键字搜索已审核通过的视频媒资。

![image-20230316150722659](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316150722659.png)

3.选择视频媒资，点击提交按钮，完成课程计划绑定媒资流程。

![image-20230316150735132](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316150735132.png)

课程计划关联视频后如下图：

![image-20230316150851255](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316150851255.png)

点击已经绑定的视频名称即可解除绑定。

![image-20230316150908208](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316150908208.png)

##### 4.8.1.2 数据模型

课程计划绑定媒资文件后存储至课程计划绑定媒资表

![image-20230316151008057](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316151008057.png)

#### 4.8.2 接口定义

根据业务流程，用户进入课程计划列表，首先确定向哪个课程计划添加视频，点

击”添加视频“后用户选择视频，选择视频，点击提交，前端以json格式请求以下参数：

提交媒资文件id、文件名称、教学计划id

示例如下：

```java
@ApiOperation("课程计划绑定媒资")
    @PostMapping("/teachplan/association/media")
    public void associationMedia(@RequestBody @Validated BindTeachplanMediaDto dto) throws XueChengPlusException {
        teachplanService.association(dto);
    }
```



#### 4.8.3 接口开发

##### 4.8.3.1 DAO开发

##### 4.8.3.2 Service开发

```java
 /**
    * @description 媒资与课程资源进行绑定
    * @param dto
    * @return void
    * @author: woldier
    * @date: 2023/3/16 15:23
    */
    void association(BindTeachplanMediaDto dto) throws XueChengPlusException;

```

```java
 /**
     * @description 媒资与课程资源进行绑定
     * @param dto
     * @return void
     * @author: woldier
     * @date: 2023/3/16 15:23
     */
    @Override
    @Transactional
    public void association(BindTeachplanMediaDto dto) throws XueChengPlusException {
        /*
        * 1.查询课程计划
        * 2.如果不是2级，报错
        * 3.查询
        * */
        Teachplan teachplan = this.getById(dto.getTeachplanId());
        if(teachplan==null) XueChengPlusException.cast("未查询到相应的课程计划");
        if(!Objects.equals(teachplan.getGrade(), TeachplanGrade.GRADE2.getGrade())) XueChengPlusException.cast("非小节目录不允许绑定媒资");
        /*查询media 看该媒资文件是否存在*/
        LambdaQueryWrapper<TeachplanMedia> q1 = new LambdaQueryWrapper<>();
        q1.eq(TeachplanMedia::getTeachplanId,dto.getTeachplanId());
        TeachplanMedia one = teachplanMediaService.getOne(q1);
        if(one== null) { //空,新插入数据
            TeachplanMedia teachplanMedia = new TeachplanMedia();
            teachplanMedia.setTeachplanId(dto.getTeachplanId());
            teachplanMedia.setMediaId(dto.getMediaId());
            teachplanMedia.setMediaFilename(dto.getFileName());
            teachplanMedia.setCourseId(teachplan.getCourseId());
            teachplanMedia.setCreateDate(LocalDateTime.now());
            teachplanMediaService.save(teachplanMedia);
        }
        else { //非空新增数据
            one.setMediaId(dto.getMediaId());
            one.setMediaFilename(dto.getFileName());
            teachplanMediaService.updateById(one);
        }

    }
```



##### 4.8.3.3 接口代码完善

```java
@ApiOperation("课程计划绑定媒资")
    @PostMapping("/teachplan/association/media")
    public void associationMedia(@RequestBody @Validated BindTeachplanMediaDto dto) throws XueChengPlusException {
        teachplanService.association(dto);
    }
```



#### 4.8.4 接口测试



#### 4.8.5 实战

根据接口定义实现解除绑定功能。

点击已经绑定的视频名称即可解除绑定。

![image-20230316155556237](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316155556237.png)

接口定义如下：

```http
### 课程计划接触视频绑定
DELETE {{media_host}}/media/teachplan/association/media/{teachPlanId}/{mediaId}

```

```java
@ApiOperation("课程计划绑定媒资")
    @DeleteMapping("/teachplan/association/media/{teachPlanId}/{mediaId}")
    public void deleteAssociationMedia(@PathVariable("teachPlanId") Long teachPlanId,@PathVariable("mediaId") String mediaId) throws XueChengPlusException {
        teachplanService.deleteAssociation(teachPlanId,mediaId);
    }
```



```java
 /**
    * @description 删除课程计划与媒资的绑定信息
    * @param teachPlanId 课程计划id
     * @param mediaId  媒资信息id
    * @return void
    * @author: woldier
    * @date: 2023/3/16 16:00
    */
    void deleteAssociation(Long teachPlanId, Long mediaId) throws XueChengPlusException;
```

```java
 /**
     * @description 删除课程计划与媒资的绑定信息
     * @param teachPlanId 课程计划id
     * @param mediaId  媒资信息id
     * @return void
     * @author: woldier
     * @date: 2023/3/16 16:00
     */
    @Override
    public void deleteAssociation(Long teachPlanId, Long mediaId) throws XueChengPlusException {
        boolean b = teachplanMediaService.removeById(teachPlanId);
        if(!b) XueChengPlusException.cast("删除操作不成功");
    }
```

## 5. 课程发布

### 5.1 模块总览

#### 5.1.1 模块介绍

课程信息编辑完毕即可发布课程，发布课程相当于一个确认操作，课程发布后学习者在网站可以搜索到课程，然后查看课程的详细信息，进一步选课、支付、在线学习。

下边是课程编辑与发布的整体流程

![image-20230316162420849](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316162420849.png)

为了课程内容没有违规信息、课程内容安排合理，在课程发布之前运营方会进行课程审核，审核通过后课程方可发布。

作为课程制作方即教学机构，在课程发布前通过课程预览功能可以看到课程发布后的效果，哪里的课程信息存在问题方便查看，及时修改。

下图是课程预览的效果图，也是课程正式发布后的课程详情界面：

![image-20230316162446473](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316162446473.png)

教学机构确认课程内容无误，提交审核，平台运营人员对课程内容审核，审核通过后教学机构人员发布课程成功。

课程发布模块共包括三块功能：

1、课程预览

2、课程审核

3、课程发布

#### 5.1.2 业务流程

##### 5.1.2.1 课程预览

1.**教育机构用户**在课程管理中可对该机构内所管理的课程进行检索

![image-20230316162816281](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316162816281.png)

2.点击某课程数据后的预览链接，即可对该课程进行预览，可以看到发布后的详情页面效果。

下图是课程详情首页，显示了课程的基本信息。

![image-20230316162832531](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316162832531.png)

点击课程目录，显示课程计划，通过此界面去核实课程计划的信息是否存在问题。

![image-20230316163256402](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163256402.png)

点击课程目录中的具体章节，查看视频播放是否正常

![image-20230316163309350](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163309350.png)



##### 5.1.2.2 课程审核

教学机构提交课程审核后，平台运营人员登录运营平台进行课程审核，课程审核包括程序自动审核和人工审核，程序会审核内容的完整性，人员通过课程预览进行审核。

流程如下：

![image-20230316163336974](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163336974.png)

1、首先查询待审核的记录。

2、课程审核

具体审核的过程与课程预览的过程类似，运营人员查看课程信息、课程视频等内容。

如果存在问题则审核不通过，并附上审核不通过的原因供教学机构人员查看。

如果课程内容没有违规信息且课程内容全面则审核通过。

课程审核通过后教学机构发布课程成功。



##### 5.1.2.3 课程发布

1.**教育机构用户**在课程管理中可对机构内课程进行检索。

![image-20230316163408088](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163408088.png)

2.点击某课程数据后的 发布 链接（审核状态为通过），即可对该课程进行发布。

![image-20230316163424944](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163424944.png)

3、课程发布后可通过课程搜索查询到课程信息，并查看课程的详细信息。

![image-20230316163452636](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163452636.png)



### 5.2 课程预览

#### 5.2.1 需求分析

课程预览就是把课程的相关信息进行整合，在课程详情界面进行展示，通过课程预览页面查看信息是否存在问题。

![image-20230316163546072](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163546072.png)

下图是课程预览的数据来源：

![image-20230316163559815](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316163559815.png)

在课程预览页面点击"视频播放图片"打开视频播放页面，通过视频播放页面查看课程计划对应的视频是否存在问题。

![image-20230316164018006](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316164018006.png)

课程预览的效果与最终课程发布后查看到的效果是一致的，所以课程预览时会通过网站门户域名地址进行预览，下图显示了整个课程预览的流程图：

![image-20230316164034740](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316164034740.png)

说明如下：

1、点击课程预览，通过Nginx、后台服务网关请求内容管理服务进行课程预览。

2、内容管理服务查询课程相关信息进行整合，并通过模板引擎技术在服务端渲染生成页面，返回给浏览器。

3、通过课程预览页面点击”马上学习“打开视频播放页面。

4、视频播放页面通过Nginx请求后台服务网关，查询课程信息展示课程计划目录，请求媒资服务查询课程计划绑定的视频文件地址，在线浏览播放视频。

#### 5.2.2 模板引擎

##### 5.2.2.1 什么是模板引擎

根据前边的数据模型分析，课程预览就是把课程的相关信息进行整合，在课程预览界面进行展示，课程预览界面与课程发布的课程详情界面一致。

项目采用模板引擎技术实现课程预览界面。

什么是模板引擎？

早期我们采用的jsp技术就是一种模板引擎技术，如下图：

![image-20230316164220103](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316164220103.png)

在nacos中未内容管理接口层配置freemarker,公用配置组`xuecheng-plus-common`新加一个`freemarker-config-dev.yaml`

![image-20230316164752460](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316164752460.png)

配置信息如下

```yaml
spring:
  freemarker:
    enabled: true
    cache: false   #关闭模板缓存，方便测试
    settings:
      template_update_delay: 0
    suffix: .ftl   #页面模板后缀名
    charset: UTF-8
    template-loader-path: classpath:/templates/   #页面模板位置(默认为 classpath:/templates/)
    resources:
      add-mappings: false   #关闭项目中的静态资源映射(static、resources文件夹下的资源)
```

FreeMarker 是一款 *模板引擎*： 即一种基于模板和要改变的数据， 并用来生成输出文本(HTML网页，电子邮件，配置文件，源代码等)的通用工具。 它不是面向最终用户的，而是一个Java类库，是一款程序员可以嵌入他们所开发产品的组件。FreeMarker 是 [免费的](http://www.fsf.org/philosophy/free-sw.html)， 基于Apache许可证2.0版本发布。



##### 5.2.2.2 Freemarker快速入门

下边在内容管理接口层搭建Freemarker的运行环境并进行测试。

在内容管理接口工层 添加Freemarker与SpringBoot的整合包

```xml
<!-- Spring Boot 对结果视图 Freemarker 集成 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>

```

在内容管理接口工程添加freemarker-config-dev.yaml

```yaml
        shared-configs:
          - data-id: swagger-${spring.profiles.active}.yaml
            group: xuecheng-plus-common
            refresh: true
          - data-id: logging-${spring.profiles.active}.yaml
            group: xuecheng-plus-common
            refresh: true
          - data-id: freemarker-config-${spring.profiles.active}.yaml
            group: xuecheng-plus-common
            refresh: true
```

添加模板，在resources下创建templates目录，添加test.ftl模板文件

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
</body>
</html>

```

编写controller方法，准备模型数据

```java
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

```

启动内容管理接口工程，访问http://localhost:63040/content/testfreemarker

![image-20230316170455210](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316170455210.png)

freemarker提供很多指令用于解析各种类型的数据模型，参考地址：http://freemarker.foofun.cn/ref_directives.html





#### 5.2.3 测试静态资源





##### 5.2.3.1 部署网站门户

在课程预览界面上要加载css、js、图片等内容，这里部署nginx来访问这些静态资源，对于SpringBoot服务的动态资源由Nginx去代理请求，如下图：

![image-20230316170746698](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316170746698.png)

1、在本机安装 Nginx ，从课程资料目录获取nginx-1.23.1.zip并解压。

2、运行nginx-1.23.1目录下的nginx.exe。

默认端口为80，如果本机80端口被占用，则需要杀掉占用进程后再启动nginx。

如果无法杀掉80端口占用进程则需要修改nginx-1.23.1目录下conf/nginx.conf配置文件



![image-20230316170811639](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316170811639.png)

将80端口修改为空闲端口。

![image-20230316171131059](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316171131059.png)

![image-20230316171142361](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316171142361.png)

启动nginx，访问http://localhost 出现下边的网页表示启动成功

![image-20230316171020119](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316171020119.png)

下边开始部署前端工程：

1、从课程资料目录获取xc-ui-pc-static-portal.zip 并解压。

2、修改本机hosts文件，加入127.0.0.1 www.51xuecheng.cn 51xuecheng.cn ucenter.51xuecheng.cn teacher.51xuecheng.cn file.51xuecheng.cn。

window10操作系统hosts文件在C:\Windows\System32\drivers\etc下

Centos7操作系统的hosts文件在/etc目录下。

在hosts文件加入如下配置

```shell
127.0.0.1 www.51xuecheng.cn 51xuecheng.cn ucenter.51xuecheng.cn teacher.51xuecheng.cn file.51xuecheng.cn
```

3、在nginx-1.23.1目录中找到conf目录，配置目录下的nginx.conf文件。

配置内容如下，注意更改xc-ui-pc-static-portal目录的路径：

```conf
server {
        listen       80;
        server_name  www.51xuecheng.cn localhost; 
        #rewrite ^(.*) https://$server_name$1 permanent;
        #charset koi8-r;
        ssi on;
        ssi_silent_errors on;
        #access_log  logs/host.access.log  main;

        location / {
            alias   D:/nginx/xc-ui-pc-static-portal/;
            index  index.html index.htm;
        }
        #静态资源
        location /static/img/ {  
                alias  D:/nginx/xc-ui-pc-static-portal/img/;
        } 
        location /static/css/ {  
                alias   D:/nginx/xc-ui-pc-static-portal/css/;
        } 
        location /static/js/ {  
                alias   D:/nginx/xc-ui-pc-static-portal/js/;
        } 
        location /static/plugins/ {  
                alias   D:/nginx/xc-ui-pc-static-portal/plugins/;
                add_header Access-Control-Allow-Origin http://ucenter.51xuecheng.cn;

        add_header Access-Control-Allow-Credentials true;  
                add_header Access-Control-Allow-Methods GET;
        } 
        location /plugins/ {  
                alias   D:/nginx/xc-ui-pc-static-portal/plugins/;
        } 



        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
```

> 这里开启了一个叫ssi的功能
>
> SSI：Server Side Include，是一种基于服务端的网页制作技术，大多数（尤其是基于Unix平台）的web[服务器](https://cloud.tencent.com/product/cvm?from=10680)如Netscape Enterprise Server等均支持SSI命令。 它的工作原因是：在页面内容发送到客户端之前，使用SSI指令将文本、图片或代码信息包含到网页中。对于在多个文件中重复出现内容，使用SSI是一种简便的方法，将内容存入一个包含文件中即可，不必将其输入所有文件。通过一个非常简单的语句即可调用包含文件，此语句指示 Web 服务器将内容插入适当网页。而且，使用包含文件时，对内容的所有更改只需在一个地方就能完成。
>
> https://cloud.tencent.com/developer/article/1757424
>
> https://juejin.cn/post/6854573216887734279

启动nginx:

![image-20230316172508350](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316172508350.png)

日志文件在nginx安装目录下的logs目录：

![image-20230316172549026](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316172549026.png)



启动成功访问http://www.51xuecheng.cn 

![image-20230316172833081](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316172833081.png)

##### 5.2.3.2 课程详情页面

course_template.html是一个静态html页面，里边还没有添加freemarker标签，如果要预览该页面需要借助Nginx进行预览，因为页面需要加载一些css样式表、图片等内容。

course_template.html文件在xc-ui-pc-static-portal\course目录下

![image-20230316172922990](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316172922990.png)

通过浏览器访问：http://www.51xuecheng.cn/course/course_template.html

效果如下：

![image-20230316172952797](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316172952797.png)

出现这个画面说明模板文件正常浏览是没有问题的



##### 5.2.3.3 文件服务器

在进行课程预览时需要展示课程的图片，在线插放课程视频，课程图片、视频这些都在MinIO文件系统存储，下边统一由Nginx代理，通过文件服务域名统一访问。如下图：



![image-20230316173030034](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316173030034.png)



在nginx.conf中配置文件服务器的代理地址

```conf
#文件服务
  upstream fileserver{
    server 192.168.101.65:9000 weight=10;
  } 
   server {
        listen       80;
        server_name  file.51xuecheng.cn;
        #charset koi8-r;
        ssi on;
        ssi_silent_errors on;
        #access_log  logs/host.access.log  main;
        location /video {
            proxy_pass   http://fileserver;
             }

        location /mediafiles {
            proxy_pass   http://fileserver;
        }
   }


```

配置完毕，重新加载nginx配置文件。

通过cmd进入nginx.exe所在目录,运行如下命令

```shell
./nginx.exe -s reload
```

通过http://file.51xuecheng.cn/mediafiles/图片文件地址 访问图片

在媒资数据库的文件表中找一个图片的地址进行测试。

![image-20230316173757063](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316173757063.png)

##### 5.2.3.4 视频播放页面

进入课程详情页面，点击马上学习或课程目录下的小节的名称将打开视频播放页面。

![image-20230316173828102](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316173828102.png)

首先在nginx.conf中配置视频播放页面的地址

```java
        location /course/preview/learning.html {
                alias D:/nginx/xc-ui-pc-static-portal/course/learning.html;
        } 
        location /course/search.html {  
                root   D:/nginx/xc-ui-pc-static-portal;
        } 
        location /course/learning.html {  
                root   D:/nginx/xc-ui-pc-static-portal;
        } 

```

下边需要配置learning.html页面的视频播放路径来测试视频播放页面，找到learning.html页面中videoObject对象的定义处，配置视频的播放地址。

![image-20230316180310139](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316180310139.png)

加载nginx配置文件

点击课程详情页面上的视频播放链接，打开视频播放页面，如下图：

![image-20230316180242582](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316180242582.png)



#### 5.2.4 接口定义

##### 5.2.4.1 定义课程预览接口

课程预览接口要将课程信息进行整合，在服务端渲染页面后返回浏览器。

下边对课程预览接口进行分析：

1、请求参数

传入课程id，表示要预览哪一门课程。

2、响应结果

输出课程详情页面到浏览器。

 

响应页面到浏览器使用freemarker模板引擎技术实现，首先从课程资料目录下获取课程预览页面course_template.html，拷贝至内容管理的接口工程的resources/templates下，并将其在本目录复制一份命名为course_template.ftl

![image-20230316181807119](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316181807119.png)



```java
package com.xuecheng.content.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author woldier
 * @version 1.0
 * @description 课程预览发布
 * @date 2023/3/16 18:16
 **/
@Controller
public class CoursePublishController {
    /**
    * @description 课程预览
    * @param courseId  课程id
    * @return org.springframework.web.servlet.ModelAndView
    * @author: woldier
    * @date: 2023/3/16 18:17
    */
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model",null);
        modelAndView.setViewName("course_template");
        return modelAndView;
    }

}

```

重启内容管理接口工程，访问http://localhost:63040/content/coursepreview/74

如下图：

![image-20230316181837501](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316181837501.png)

![image-20230316181905862](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316181905862.png)

课程预览没有css,这是没有加载到静态资源,我们接下来解决

##### 5.2.4.2 配置反向代理

课程预览接口虽然可以正常访问，但是页面没有样式，查看浏览器请求记录，发现图片、样式无法正常访问。

这些静态资源全在门户下，我们需要由Nginx反向代理访问课程预览接口，通过门户的URL去访问课程预览。

1、在Nginx下配置：(这里我们代理的是网关)

```conf
    upstream gatewayserver{
        server localhost:63010 weight=10;
    }
    server {
        listen       80;
        #server_name  www.51xuecheng.cn localhost; 
        #rewrite ^(.*) https://$server_name$1 permanent;
        #charset koi8-r;
        ssi on;
        ssi_silent_errors on;
        #access_log  logs/host.access.log  main;
        location /api/ {
             proxy_pass http://gatewayserver/;
        }
```

![image-20230316183805171](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316183805171.png)

3、启动微服务网关

4、此时访问新地址： http://www.51xuecheng.cn/api/content/coursepreview/74 

页面样式正常

![image-20230316183832077](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316183832077.png)

页面虽然正常，但是里边的内容都是静态内容，稍后接口层调用service方式获取模型数据并进行页面渲染。

目前的方式是通过Nginx访问网关，由网关再将请求转发到微服务，Nginx是整个的项目最前方的代理服务器，如下图：

![image-20230316183845255](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316183845255.png)

#### 5.2.5 接口开发





##### 5.2.5.1 数据模型

课程预览就是把课程基本信息、营销信息、课程计划、师资等课程的相关信息进行整合，在预览页面进行展示。如下图：

![image-20230316183953958](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316183953958.png)

在使用freemarker渲染生成视图时需要数据模型，此数据模型包括了基本信息、营销信息、课程计划、师资等信息。

所以首先定义一个数据模型类：



##### 5.2.5.2 service 接口

Service负责从数据库查询基本信息、营销信息、课程计划等课程相关信息，组成CoursePreviewDto 对象。

```java
package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CoursePreviewDto;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布预览service
 * @date 2023/3/16 18:43
 **/
public interface CoursePublishService {

    
    /**
    * @description 获取课程预览所需要的信息 
    * @param courseId  课程id
    * @return com.xuecheng.content.model.dto.CoursePreviewDto 
    * @author: woldier 
    * @date: 2023/3/16 18:45
    */
    public CoursePreviewDto getCoursePreviewInfo(Long courseId);
}

```

接口实现如下：

```java
package com.xuecheng.content.service.impl;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CoursePublishService;
import com.xuecheng.content.service.TeachplanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布预览service-impl
 * @date 2023/3/16 18:44
 **/
@Service
@RequiredArgsConstructor
public class CoursePublishServiceImpl implements CoursePublishService {
    private final CourseBaseInfoService courseBaseInfoService;
    private final TeachplanService teachplanService;
    /**
     * @description 获取课程预览所需要的信息
     * @param courseId  课程id
     * @return com.xuecheng.content.model.dto.CoursePreviewDto
     * @author: woldier
     * @date: 2023/3/16 18:45
     */
    @Override
    public CoursePreviewDto getCoursePreviewInfo(Long courseId) throws XueChengPlusException {
        /*
         * 1.查询课程基本,营销信息 
         * 2.查询课程计划信息
         */
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        if(courseBaseInfo==null) XueChengPlusException.cast("获取课程基本信息出错");
        List<TeachplanDto> teachplanDtos = teachplanService.selectTreeNodes(courseId);
        if(teachplanDtos==null) XueChengPlusException.cast("获取课程计划信息出错");
        CoursePreviewDto coursePreviewDto = new CoursePreviewDto();
        coursePreviewDto.setCourseBase(courseBaseInfo);
        coursePreviewDto.setTeachplans(teachplanDtos);
        return coursePreviewDto;
    }
}

```



##### 5.2.5.3 接口完善

接口层Controller调用Service方法获取模板引擎需要的模型数据

```java
package com.xuecheng.content.api;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.service.CoursePublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author woldier
 * @version 1.0
 * @description 课程预览发布
 * @date 2023/3/16 18:16
 **/
@Controller
@RequiredArgsConstructor
public class CoursePublishController {
    private final CoursePublishService coursePublishService;
    /**
    * @description 课程预览
    * @param courseId  课程id
    * @return org.springframework.web.servlet.ModelAndView
    * @author: woldier
    * @date: 2023/3/16 18:17
    */
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId) throws XueChengPlusException {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model",coursePublishService.getCoursePreviewInfo(courseId));
        modelAndView.setViewName("course_template");
        return modelAndView;
    }

}

```







##### 5.2.5.4 前后端联调

原来前端直接指向后台网关地址，现在要更改为Nginx的地址，如下：

![image-20230316185415665](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316185415665.png)

重启前端工程，进入课程列表点击"预览"按钮，正常打开课程预览页面http://www.51xuecheng.cn/api/content/coursepreview/1

![image-20230316190446078](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316190446078.png)



##### 5.2.5.5 编写模板

freemarker提供很多指令用于解析各种类型的数据模型，参考地址：http://freemarker.foofun.cn/ref_directives.html

修改模板后需要编译，如下图：

![image-20230316190533793](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316190533793.png)



在调试模板时，可以看出哪些信息有缺少，在课程管理处进行补充，比如下图显示课程计划信息不完整，需要进入课程计划界面添加课程计划。

![image-20230316190551057](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316190551057.png)



完整的course_template.ftl模板在课程资料目录下，差不多学会了freemarker标签的使用方法，将课程资料下的course_template.ftl覆盖自己的工程下的course_template.ftl。

##### 5.2.5.6 视频播放页面接口

从课程详情页面进入视频播放页面，如下图：

![image-20230316191144008](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230316191144008.png)

在此页面需要从后台获取课程信息、根据课程计划获取对应的视频地址，下边编写这两个接口：

获取课程信息接口：/open/content/course/whole/{courseId}

```http
/open/content/course/whole/课程id

响应：同课程预览service接口返回数据

```

根据课程计划获取视频地址接口：/open/media/preview/{mediaId}

```http
/open/media/preview/课程计划id

响应：
{"code":0,"msg":"success","result":"视频的url","successful":true}

```

1、在nginx配置如下地址

```conf
#openapi
location /open/content/ {
        proxy_pass http://gatewayserver/content/open/;
} 
location /open/media/ {
        proxy_pass http://gatewayserver/media/open/;
} 

```

配置运行nginx.exe -s reload加载nginx的配置文件 

2、在内容管理接口层定义CourseOpenController类，并定义接口：获取课程信息接口：/open/content/course/whole/{courseId}

```java
package com.xuecheng.content.api;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CoursePublishService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "课程公开查询接口", tags = "课程公开查询接口")
@RestController
@RequestMapping("/open")
public class CourseOpenController {

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @Autowired
    private CoursePublishService coursePublishService;


    @GetMapping("/course/whole/{courseId}")
    public CoursePreviewDto getPreviewInfo(@PathVariable("courseId") Long courseId) throws XueChengPlusException {
        //获取课程预览信息
        CoursePreviewDto coursePreviewInfo = coursePublishService.getCoursePreviewInfo(courseId);
        return coursePreviewInfo;
    }

}

```



3、在媒资管理服务media-api工程定义MediaOpenController类，并定义接口/open/media/preview/

```java
package com.xuecheng.media.api;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.RestResponse;
import com.xuecheng.media.model.po.MediaFiles;
import com.xuecheng.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/3/16 19:17
 **/
@Api(value = "媒资文件管理接口", tags = "媒资文件管理接口")
@RestController
@RequestMapping("/open")

public class MediaOpenController {

    @Autowired
    MediaFileService mediaFileService;

    @ApiOperation("预览文件")
    @GetMapping("/preview/{mediaId}")
    public RestResponse<String> getPlayUrlByMediaId(@PathVariable String mediaId) throws XueChengPlusException {

        MediaFiles mediaFiles = mediaFileService.getFileById(mediaId);
        if (mediaFiles == null || StringUtils.isEmpty(mediaFiles.getUrl())) {
            XueChengPlusException.cast("视频还没有转码处理");
        }
        return RestResponse.success(mediaFiles.getUrl());
    }
}

```



5、测试

定义好后，启动内容管理、媒资管理、后台服务网关服务，测试视频播放页面是否可以正常获取课程计划，点击具体的课程计划是否正常可以播放视频。



### 5.3 课程审核

#### 5.3.1 需求分析

##### 5.3.1.1 业务流程

根据模块需求分析，课程发布前要先审核，审核通过方可发布。下图是课程审核及发布的流程图：

![image-20230321111242800](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230321111242800.png)

为什么课程审核通过才可以发布呢？

这样做为了防止课程信息有违规情况，课程信息不完善对网站用户体验也不好，课程审核不仅起到监督作用，也是帮助教学机构规范使用平台的手段。

如何控制课程审核通过才可以发布课程呢？

在课程基本表course_base表设置课程审核状态字段，包括：未提交、已提交(未审核)、审核通过、审核不通过。

下边是课程状态的转化关系：

![image-20230321111311757](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230321111311757.png)

说明如下：

1、一门课程新增后它的审核状为”未提交“，发布状态为”未发布“。

2、课程信息编辑完成，教学机构人员执行”提交审核“操作。此时课程

的审核状态为”已提交“。

3、当课程状态为已提交时运营平台人员对课程进行审核。

4、运营平台人员审核课程，结果有两个：审核通过、审核不通过。

5、课程审核过后不管状态是通过还是不通过，教学机构可以再次修改课程并提交审核，此时课程状态为”已提交“。此时运营平台人员再次审核课程。

6、课程审核通过，教学机构人员可以发布课程，发布成功后课程的发布状态为”已发布“。

7、课程发布后通过”下架“操作可以更改课程发布状态为”下架“

8、课程下架后通过”上架“操作可以再次发布课程，上架后课程发布状态为“发布”。

##### 5.3.1.2 数据模型

过业务流程的分析，现在我们思考：

1、课程提交审核后还允许修改课程吗？

如果不允许修改是不合理的，因为提交审核后可以继续做下一个阶段的课程内容，比如添加课程计划，上传课程视频等。

如果允许修改那么课程审核时看到的课程内容从哪里来？如果也从课程基本信息表、课程营销表、课程计划表查询那么存在什么问题呢？如下图：

![image-20230324164548640](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230324164548640.png)

![](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230324164548640.png)

运营人员审核课程和教学机构编辑课程操作的数据是同一份，此时会导致冲突。比如：运营人员正在审核时教学机构把数据修改了。

为了解决这个问题，专门设计课程预发布表。

如下图：

![image-20230324164616776](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230324164616776.png)

提交课程审核，将课程信息汇总后写入课程预发布表，课程预发布表记录了教学机构在某个时间点要发布的课程信息。

课程审核人员从预发布表查询信息进行审核。

课程审核的同时可以对课程进行修改，修改的内容不会写入课程预发布表。

课程审核通过执行课程发布，将课程预发布表的信息写入课程发布表。

 

2、提交审核课程后，也修改了课程信息，可以再次提交审核吗？

这个问题在上边分析课程审核状态时已经有了答案，如下图：

![image-20230324164937092](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230324164937092.png)

提交审核课程后，必须等到课程审核完成才可以再次提交课程。

 

课程审核功能涉及教学机构提交审核，运营人员进行课程审核。在课堂上我们仅实现教学机构提交审核功能，课程审核的结果通过手动修改数据库来实现。

虽然课堂上不实现课程审核功能，完整的课程审核数据表设计需要理解。

提交审核将信息写入课程预发布表，课程预发布表结构如下

![image-20230324170813086](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230324170813086.png)

更新课程基本信息表的课程审核状态为：已经提交

课程审核后更新课程基本信息表的审核状态、课程预发布表的审核状态，并将审核结果写入课程审核记录。

审核记录表结构如下：

![image-20230324170923312](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230324170923312.png)

#### 5.3.2 接口定义

下边定义提交课程审核的接口，在课程发布Controller中定义接口如下：

```java
/**
    * @description 课程提交审核
    * @param courseId  课程id
    * @return void
    * @author: woldier
    * @date: 2023/3/24 17:14
    */
    @ResponseBody
    @PostMapping("/courseaudit/commit/{courseId}")
    public void commitAudit(@PathVariable("courseId") Long courseId) {

    }
```



#### 5.3.3 接口开发

##### 5.3.3.1 DAO开发

1、查询课程基本信息、课程营销信息、课程计划信息等课程相关信息，整合为课程预发布信息。

2、向课程预发布表course_publish_pre插入一条记录，如果已经存在则更新，审核状态为：已提交。

3、更新课程基本表course_base课程审核状态为：已提交。

约束：

1、对已提交审核的课程不允许提交审核。

2、本机构只允许提交本机构的课程。

3、没有上传图片不允许提交审核。

4、没有添加课程计划不允许提交审核。

 

使用代码生成器生成课程发布表、课程预发布表的PO、Mpper，并拷贝到相应的工程下。

##### 5.3.3.2 Service开发

```java
package com.xuecheng.content.service;

import com.xuecheng.base.exception.XueChengPlusException;

/**
 * @author woldier
 * @version 1.0
 * @description 课程预发布
 * @date 2023/3/25 18:50
 **/
public interface CoursePublishPreCustomService {
    /**
    * @description 提交审核
    * @param companyId 公司id
     * @param courseId  课程id
    * @return void
    * @author: woldier
    * @date: 2023/3/25 18:55
    */
    void commitAudit(Long companyId,Long courseId) throws XueChengPlusException;
}

```

```java
package com.xuecheng.content.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.enums.CourseAuditStatus;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.model.po.CoursePublishPre;
import com.xuecheng.content.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author woldier
 * @version 1.0
 * @description TODO
 * @date 2023/3/25 18:55
 **/
@Service
@RequiredArgsConstructor
public class CoursePublishPreCustomServiceImpl implements CoursePublishPreCustomService {


    private final CourseBaseMapper courseBaseMapper;
    private final CourseBaseInfoService courseBaseInfoService;
    private final CourseMarketService courseMarketService;
    private final CoursePublishService coursePublishService;
    private final CoursePublishPreService coursePublishPreService;
    private final TeachplanService teachplanService;

    @Override
    @Transactional
    public void commitAudit(Long companyId, Long courseId) throws XueChengPlusException {
        /**
         *
         * 1、查询课程基本信息、课程营销信息、课程计划信息等课程相关信息，整合为课程预发布信息。
         * 2、向课程预发布表course_publish_pre插入一条记录，如果已经存在则更新，审核状态为：已提交。
         * 3、更新课程基本表course_base课程审核状态为：已提交。
         * 约束：
         * 1、对已提交审核的课程不允许提交审核。
         * 2、本机构只允许提交本机构的课程。
         * 3、没有上传图片不允许提交审核。
         * 4、没有添加课程计划不允许提交审核。
         * */
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        //如果当前课程审核状态为已提交,不允许再提交审核
        if (courseBaseInfo.getAuditStatus().equals(CourseAuditStatus.COMMIT.getCode()))
            XueChengPlusException.cast("课程已经提交审核,请等待课程审核结果");
        // 查询课程预览的信息 里面包含课程基本信息,课程营销信息,课程计划信息
        CoursePreviewDto coursePreviewInfo = coursePublishService.getCoursePreviewInfo(courseId);
        //判断课程id是否匹配
        if (!coursePreviewInfo.getCourseBase().getId().equals(courseId))
            XueChengPlusException.cast("课程id不匹配");
        CoursePublishPre coursePublishPre = new CoursePublishPre();
        //判断课程信息是否完整
        if (StringUtils.isEmpty(coursePreviewInfo.getCourseBase().getPic()))
            XueChengPlusException.cast("课程图片信息为空,请先修改");

        /*
         * 拷贝信息到课程语法与实体
         * */
        BeanUtils.copyProperties(coursePreviewInfo.getCourseBase(), coursePublishPre);

        //添加营销信息
        CourseMarket courseMarket = courseMarketService.getById(courseId);
        String courseMarketJsonString = JSON.toJSONString(courseMarket);
        coursePublishPre.setMarket(courseMarketJsonString);

        //添加课程信息
        List<TeachplanDto> teachplanDtoList = teachplanService.selectTreeNodes(courseId);
        if (teachplanDtoList.isEmpty())
            XueChengPlusException.cast("课程计划信息为空,不允许提交审核");
        String teachplanDtoListJSONString = JSON.toJSONString(teachplanDtoList);
        coursePublishPre.setTeachplan(teachplanDtoListJSONString);

        //设置预发布记录状态,已提交
        coursePublishPre.setStatus(CourseAuditStatus.COMMIT.getCode());

        //课程公司id
        coursePublishPre.setCompanyId(companyId);

        coursePublishPre.setCreateDate(LocalDateTime.now());

        coursePublishPreService.saveOrUpdate(coursePublishPre);
        //更新课程基本信息表状态
        courseBaseInfo.setAuditStatus(CourseAuditStatus.COMMIT.getCode());
        courseBaseMapper.updateById(courseBaseInfo);
    }
}

```



##### 5.3.3.3 接口代码完善



```java
 /**
    * @description 课程提交审核
    * @param courseId  课程id
    * @return void
    * @author: woldier
    * @date: 2023/3/24 17:14
    */
    @ResponseBody
    @PostMapping("/courseaudit/commit/{courseId}")
    public void commitAudit(@PathVariable("courseId") Long courseId) throws XueChengPlusException {

        coursePublishPreCustomService.commitAudit(1232141425L,courseId);
    }
```



#### 5.3.4 接口测试

使用前端提前课程审核：

1、找一门信息不全的课程，测试各各约束条件。

2、正常提交后，观察数据库中课程预发布表记录的内容是否完整。

3、测试审核过后再次提交，提交后观察数据库中课程预发布表记录的内容是否正确。

审核通过需手动修改数据库：

1、修改课程预发布表的状态为审核通过202004。

2、修改课程基本表的审核状态为审核通过202004。





### 5.4 课程发布

#### 5.4.1 需求分析

##### 5.4.1.1 业务流程

教学机构人员在课程审核通过后即可发布课程，课程发布后会公开展示在网站上供学生查看、选课和学习。

在网站上展示课程信息需要解决课程信息显示的性能问题，如果速度慢(排除网速)会影响用户的体验性。

如何去快速搜索课程？

打开课程详情页面仍然去查询数据库可行吗？

为了提高网站的速度需要将课程信息进行缓存，并且要将课程信息加入索引库方便搜索，下图显示了课程发布后课程信息的流转情况：

![image-20230326161503915](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230326161503915.png)

1、向内容管理数据库的课程发布表存储课程发布信息，更新课程基本信息表中发布状态为已发布。

2、向Redis存储课程缓存信息。

3、向Elasticsearch存储课程索引信息。

4、请求分布文件系统存储课程静态化页面(即html页面)，实现快速浏览课程详情页面。

##### 5.4.1.2 数据模型

课程发布表的数据来源于课程预发布表，它们的结构基本一样，只是课程发布表中的状态是课程发布状态，如下图：

![image-20230326162456100](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230326162456100.png)

redis中的课程缓存信息是将课程发布表中的数据转为json进行存储。

elasticsearch中的课程索引信息是根据搜索需要将课程名称、课程介绍等信息进行索引存储。

MinIO中存储了课程的静态化页面文件（html网页），查看课程详情是通过文件系统去浏览课程详情页面。







#### 5.4.2 分布式事务技术方案

##### 5.4.2.1 什么是分布式事务

一次课程发布操作需要向数据库、redis、elasticsearch、MinIO写四份数据，这里存在分布式事务问题。

什么是分布式事务？

首先理解什么是本地事务？

平常我们在程序中通过spring去控制事务是利用数据库本身的事务特性来实现的，因此叫数据库事务，由于应用主要靠关系数据库来控制事务，此数据库只属于该应用，所以基于本应用自己的关系型数据库的事务又被称为本地事务。 

本地事务具有ACID四大特性，数据库事务在实现时会将一次事务涉及的所有操作全部纳入到一个不可分割的执行单元，该执行单元中的所有操作 要么都成功，要么都失败，只要其中任一操作执行失败，都将导致整个事务的回滚。 

理解了本地事务，什么是分布式事务？

现在的需求是课程发布操作后将数据写入数据库、redis、elasticsearch、MinIO四个地方，这四个地方已经不限制在一个数据库内，是由四个分散的服务去提供，与这四个服务去通信需要网络通信，而网络存在不可到达性，这种分布式系统环境下，通过与不同的服务进行网络通信去完成事务称之为**分布式事务。**

在分布式系统中分布式事务的场景很多：

例如用户注册送积分，银行转账，创建订单减库存，这些都是分布式事务。

拿转账举例：

我们知道本地事务依赖数据库本身提供的事务特性来实现，因此以下逻辑可以控制本地事务： 

```shell
begin transaction； 
//1.本地数据库操作：张三减少金额 
//2.本地数据库操作：李四增加金额 
commit transation; 

```

但是在分布式环境下，会变成下边这样：

```shell
begin transaction； 
//1.本地数据库操作：张三减少金额 
//2.远程调用：让李四增加金额 

commit transation;


```

这里存在的远程调用,可能访问的都不是同一个数据库,那么居于单机mysql的innodb事务控制无法生效.

可以设想，当远程调用让李四增加金额成功了，由于网络问题远程调用并没有返回，此时本地事务提交失败就回滚了张三减少金额的操作，此时张三和李四的数据就不一致了。 

因此在分布式架构的基础上，传统数据库事务就无法使用了，张三和李四的账户不在一个数据库中甚至不在一个应 用系统里，实现转账事务需要通过远程调用，由于网络问题就会导致分布式事务问题。 

下边的场景都会产生分布式事务：

微服务架构下：

![image-20230326163431542](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230326163431542.png)



单服务多数据库：

![image-20230326163532370](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230326163532370.png)

多服务单数据库:

![image-20230326163545831](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230326163545831.png)

##### 5.4.2.2 什么是CAP理论

控制分布式事务首先需要理解CAP理论，什么是==CAP==理论？

CAP是 Consistency、Availability、Partition tolerance三个词语的缩写，分别表示一致性、可用性、分区容忍性。

使用下边的分布式系统结构 进行说明：

![image-20230326164219953](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230326164219953.png)

客户端经过网关访问用户服务的两个结点，一致性是指用户不管访问哪一个结点拿到的数据都是最新的，比如查询小明的信息，不能出现在数据没有改变的情况下两次查询结果不一样。

可用性是指任何时候查询用户信息都可以查询到结果，但不保证查询到最新的数据。

分区容忍性也叫分区容错性，当系统采用分布式架构时由于网络通信异常导致请求中断、消息丢失，但系统依然对外提供服务。

CAP理论要强调的是在分布式系统中这三点不可能全部满足，由于是分布式系统就要满足分区容忍性，因为服务之间难免出现网络异常，不能因为局部网络异常导致整个系统不可用。

满足P那么C和A不能同时满足：

比如我们添加一个用户小明的信息，该信息先添加到结点1中，再同步到结点2中，如下图：

![image-20230326164332200](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/2023-3/image-20230326164332200.png)

如果要满足C一致性，必须等待小明的信息同步完成系统才可用（否则会出现请求到结点2时查询不到数据，违反了一致性），在信息同步过程中系统是不可用的，所以满足C的同时无法满足A。

如果要满足A可用性，要时刻保证系统可用就不用等待信息同步完成，此时系统的一致性无法满足。

 

所以在分布式系统中进行分布式事务控制，要么保证CP、要么保证AP。

##### 5.4.2.3 分布式事务控制方案

==学习了CAP理论该如何控制分布式事务呢？==

学习了CAP理论我们知道进行分布式事务控制要在C和A中作出取舍，保证一致性就不要保证可用性，保证可用性就不要保证一致，首先你确认是要CP还是AP，具体要根据应用场景进行判断。

CP的场景：满足C舍弃A，强调一致性。

跨行转账：一次转账请求要等待双方银行系统都完成整个事务才算完



成，只要其中一个失败另一方执行回滚操作。

开户操作：在业务系统开户同时要在运营商开户，任何一方开户失败该用户都不可使用，所以要满足CP。

AP的场景：满足A舍弃C，强调可用性。

订单退款，今日退款成功，明日账户到账，只要用户可以接受在一定时间内到账即可。

注册送积分，注册成功积分在24分到账。

支付短信通信，支付成功发短信，短信发送可以有延迟，甚至没有发送成功。

在实际应用中符合AP的场景较多，其实虽然AP舍弃C一致性，实际上最终数据还是达到了一致，也就满足了最终一致性，所以业界定义了BASE理论。



==什么是BASE理论？==

BASE 是 Basically Available(基本可用)、Soft state(软状态)和 Eventually consistent (最终一致性)三个短语的缩写。

基本可用：当系统无法满足全部可用时保证核心服务可用即可，比如一个外卖系统，每到中午12点左右系统并发量很高，此时要保证下单流程涉及的服务可用，其它服务暂时不可用。

软状态：是指可以存在中间状态，比如：打印自己的社保统计情况，该操作不会立即出现结果，而是提示你打印中，请在XXX时间后查收。虽然出现了中间状态，但最终状态是正确的。

最终一致性：退款操作后没有及时到账，经过一定的时间后账户到账，舍弃强一致性，满足最终一致性。

==分布式事务控制有哪些常用的技术方案？==

实现CP就是要实现强一致性:

使用Seata框架基于AT模式实现

使用Seata框架基于TCC模式实现。

实现AP则要保证最终数据一致性:

使用消息队列通知的方式去实现，通知失败自动重试，达到最大失败次数需要人工处理；

使用任务调度的方案，启动任务调度将课程信息由数据库同步到elasticsearch、MinIO、redis中。





##### 5.4.2.4 课程发布的事务控制方案

==学习了这么多的理论，回到课程发布，执行课程发布操作后要向数据库、redis、elasticsearch、MinIO写四份数据，这个场景用哪种方案？==

 

满足CP？

如果要满足CP就表示课程发布操作后向数据库、redis、elasticsearch、MinIO写四份数据，只要有一份写失败其它的全部回滚。

满足AP？

课程发布操作后，先更新数据库中的课程发布状态，更新后向redis、elasticsearch、MinIO写课程信息，只要在一定时间内最终向redis、elasticsearch、MinIO写数据成功即可。

目前我们已经有了任务调度的技术积累，这里选用任务调度的方案去实现分布式事务控制，课程发布满足AP即可。

下图是具体的技术方案：

![image-20230326165620999](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/efe13434674b87397e02d3fcf3ba5b74.png)

1、在内容管理服务的数据库中添加一个消息表，消息表和课程发布表在同一个数据库。

2、点击课程发布通过本地事务向课程发布表写入课程发布信息，同时向消息表写课程发布的消息。通过数据库进行控制，只要课程发布表插入成功消息表也插入成功，消息表的数据就记录了某门课程发布的任务。

3、启动任务调度系统定时调度内容管理服务去定时扫描消息表的记录。

4、当扫描到课程发布的消息时即开始完成向redis、elasticsearch、MinIO同步数据的操作。

5、同步数据的任务完成后删除消息表记录。

时序图如下：

下图是课程发布操作的流程

![image-20230326170818255](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/aa5550cfd75fe10d246b4e7baba5d874.png)

1、执行发布操作，内容管理服务存储课程发布表的同时向消息表添加一条“课程发布任务”。这里使用本地事务保证课程发布信息保存成功，同时消息表也保存成功。

2、任务调度服务定时调度内容管理服务扫描消息表，由于课程发布操作后向消息表插入一条课程发布任务，此时扫描到一条任务。

3、拿到任务开始执行任务，分别向redis、elasticsearch及文件系统存储数据。

4、任务完成后删除消息表记录。



#### 5.4.3 课程发布接口

##### 5.4.3.1 接口定义

根据课程发布的分布式事务控制方案，课程发布操作首先通过本地事务向课程发布表写入课程发布信息并向消息表插入一条消息，这里定义的课程发布接口要实现该功能。

在内容管理接口工程中定义课程发布接口。

```java
 * @description 课程预览，发布
 * @author Mr.M
 * @date 2022/9/16 14:48
 * @version 1.0
 */
@Api(value = "课程预览发布接口",tags = "课程预览发布接口")
@Controller
public class CoursePublishController {
...
 @ApiOperation("课程发布")
 @ResponseBody
 @PostMapping ("/coursepublish/{courseId}")
public void coursepublish(@PathVariable("courseId") Long courseId){

}

```



##### 5.4.3.2 接口开发

###### 5.4.3.2.1 DAO开发

课程发布操作对数据库操作如下：

1、向课程发布表course_publish插入一条记录,记录来源于课程预发布表，如果存在则更新，发布状态为：已发布。

2、更新course_base表的课程发布状态为：已发布

3、删除课程预发布表的对应记录。

4、向mq_message消息表插入一条消息，消息类型为：course_publish

约束：

1、课程审核通过方可发布。

2、本机构只允许发布本机构的课程。



以上功能使用自动生成的mapper接口即可完成。

1、在内容管理数据库创建mq_message消息表及消息历史消息表（历史表存储已经完成的消息）。

![image-20230326194456600](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/0921dfec11fb9094d06b3290ee8ee3c0.png)



![image-20230326194526752](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/cf9f4cb309c10cfa2db7fed92ffe10f1.png)



2、生成mq_message消息表、course_publish课程发布表的po和mapper接口

稍后会开发一个通用的消息处理组件，这里先不生成代码。

![image-20230326195006814](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/2087b8c6cb10001edfa38fdadf49dc1e.png)



###### 5.4.3.2.1 service开发

```java
/**
     * @description 课程发布
     * @param courseId  课程id
     * @return void
     * @author: woldier
     * @date: 2023/3/26 17:25
     */
     void coursePublish(Long companyId,Long courseId) throws XueChengPlusException;
```

```java
/**
     * @description 课程发布
     * @param courseId  课程id
     * @return void
     * @author: woldier
     * @date: 2023/3/26 17:25
     */
    @Override
    @Transactional()
    public void coursePublish(Long companyId,Long courseId) throws XueChengPlusException {
        /**
         * 1. 判断课程预发布表的审核状态,若不为审核通过不允许发布课程
         * 2. 在课程预发布表中status字段指的是课程审核状态,而在课程发布表中的status字段指的是课程的发布状态,因此我们需要修改status字段
         * 3. 设置课程基本信息表的课程发布状态为已发布
         * 4. 将课程发布任务写入的消息表(用于同步redis,elasticsearch,minio)
         */
        // 查询课程预发布信息
        CoursePublishPre coursePublishPre = coursePublishPreService.getById(courseId);
        //判断是否是本机构课程

        if(!    coursePublishPre.getCompanyId().equals(companyId))
            XueChengPlusException.cast("只允许提交本机构的课程");
        // 判断审核状态
        if(!coursePublishPre.getStatus().equals(CourseAuditStatus.AUDIT.getCode()))
            XueChengPlusException.cast("当前课程审核状态不是审核通过");


        // 对象拷贝,设置coursePublish的status字段
        CoursePublish coursePublish = new CoursePublish();
        BeanUtils.copyProperties(coursePublishPre,coursePublish);
        coursePublish.setStatus(CourseStatus.PUBLISHED.getCode());
        //保存课程发布表
        this.saveOrUpdate(coursePublish);

        //更新课程表的状态
        CourseBase courseBase = new CourseBase();
        courseBase.setId(courseId);
        courseBase.setStatus(CourseStatus.NOT_PUBLISH.getCode());
        courseBaseMapper.updateById(courseBase);
        //删除课程预发布表
        coursePublishPreService.removeById(courseId);
        //写入事务信息到消息表同步信息
        CoursePublishService proxy = (CoursePublishService)AopContext.currentProxy();
        proxy.saveCoursePublishMessage(courseId);
    }


    /**
    * @description TODO 课程发布成功写入消息表
    * @param courseId
    * @return void
    * @author: woldier
    * @date: 2023/3/26 20:15
    */
    @Override
    @Transactional
    public void saveCoursePublishMessage(Long courseId){


    }
```



###### 5.4.3.2.1 接口完善

```java
 /**
    * @description 课程发布
    * @param courseId 课程id
    * @return void
    * @author: woldier
    * @date: 2023/3/26 17:22
    */
    @ApiOperation("课程发布")
    @ResponseBody
    @PostMapping ("/coursepublish/{courseId}")
    public void coursepublish(@PathVariable("courseId") Long courseId) throws XueChengPlusException {
        coursePublishService.coursePublish(1232141425L,courseId);
    }

```



##### 5.4.3.3 测试

先使用httpclient方法测试：

```http
### 课程发布
POST {{content_host}}/content/coursepublish/2

```

先测试约束条件：

1、在未提交审核时进行课程发布测试。

2、在课程未审核通过时进行发布。

正常流程测试：

1、提交审核课程

2、手动修改课程预发布表与课程基本信息的审核状态为审核通过。

3、执行课程发布

4、观察课程发布表记录是否正常，课程预发布表记录已经删除，课程基本信息表与课程发布表的发布状态为”发布“。

使用前后端联调方式测试。



#### 5.4.4 消息处理SDK

##### 5.4.4.1 消息模块技术方案

课程发布操作执行后需要扫描消息表的记录，有关消息表处理的有哪些？

![image-20230327082321225](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/a5e79aed7c2ab7ecb6200a9900b7a0de.png)



上图中红色框内的都是与消息处理相关的操作：

1、新增消息表

2、扫描消息表。

3、更新消息表。

4、删除消息表。

使用消息表这种方式实现最终事务一致性的地方除了课程发布还有其它业务场景。

![image-20230327082405869](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/ca424ed02979bf505d8852a53a7765e6.png)



如果在每个地方都实现一套针对消息表定时扫描、处理的逻辑基本上都是重复的，软件的可复用性太低，成本太高。

如何解决这个问题？

针对这个问题可以想到将消息处理相关的逻辑做成一个通用的东西。

是做成通用的服务，还是做成通用的代码组件呢？

通用的服务是完成一个通用的独立功能，并提供独立的网络接口，比如：项目中的文件系统服务，提供文件的分布式存储服务。

代码组件也是完成一个通用的独立功能，通常会提供API的方式供外部系统使用，比如：fastjson、Apache commons工具包等。

如果将消息处理做成一个通用的服务，该服务需要连接多个数据库，因为它要扫描微服务数据库下的消息表，并且要提供与微服务通信的网络接口，单就针对当前需求而言开发成本有点高。

如果将消息处理做一个SDK工具包相比通用服务不仅可以解决将消息处理通用化的需求，还可以降低成本。

所以，本项目确定将对消息表相关的处理做成一个SDK组件供各微服务使用,如下图所示：

![image-20230327082528857](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/5c7f9c68475337940e7f1535d91c8115.png)



下边对消息SDK的设计内容进行说明：

==sdk需要提供执行任务的逻辑吗？==

拿课程发布任务举例，执行课程发布任务是要向redis、索引库等同步数据，其它任务的执行逻辑是不同的，所以执行任务在sdk中不用实现任务逻辑，只需要提供一个抽象方法由具体的执行任务方去实现。

==如何保证任务的幂等性？==

在视频处理章节介绍的视频处理的幂等性方案，这里可以采用类似方案，任务执行完成后会从消息表删除，如果消息的状态是完成或不存在消息表中则不用执行。

==如何保证任务不重复执行？==

采用和视频处理章节一致方案，除了保证任务的幂等性外，任务调度采用分片广播，根据分片参数去获取任务，另外阻塞调度策略为丢弃任务。

注意：这里是信息同步类任务，即使任务重复执行也没有关系，不再使用抢占任务的方式保证任务不重复执行。

还有一个问题，根据消息表记录是否存在或消息表中的任务状态去保证任务的幂等性，如果一个任务有好几个小任务，比如：课程发布任务需要执行三个同步操作：存储课程到redis、存储课程到索引库，存储课程页面到文件系统。如果其中一个小任务已经完成也不应该去重复执行。这里该如何设计？



将小任务作为任务的不同的阶段，在消息表中设计阶段状态。

![image-20230327083625926](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/58f1c4e9a920e71a9c147e7ece888965.png)



每完成一个阶段在相应的阶段状态字段打上完成标记，即使这个大任务没有完成再重新执行时，如果小阶段任务完成了也不会重复执行某个小阶段的任务。

综上所述，除了消息表的基本的增、删、改、查的接口外，消息SDK还具有如下接口功能：

```java
package com.xuecheng.messagesdk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xuecheng.messagesdk.model.po.MqMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr.M
 * @since 2022-09-21
 */
public interface MqMessageService extends IService<MqMessage> {

    /**
     * @description 扫描消息表记录，采用与扫描视频处理表相同的思路
     * @param shardIndex 分片序号
     * @param shardTotal 分片总数
     * @param count 扫描记录数
     * @return java.util.List 消息记录
     * @author Mr.M
     * @date 2022/9/21 18:55
     */
    public List<MqMessage> getMessageList(int shardIndex, intshardTotal,  String messageType,int count);

    /**
     * @description 完成任务
     * @param id 消息id
     * @return int 更新成功：1
     * @author Mr.M
     * @date 2022/9/21 20:49
     */
    public int completed(long id);

    /**
     * @description 完成阶段任务
     * @param id 消息id
     * @return int 更新成功：1
     * @author Mr.M
     * @date 2022/9/21 20:49
     */
    public int completedStageOne(long id);
    public int completedStageTwo(long id);
    public int completedStageThree(long id);
    public int completedStageFour(long id);

    /**
     * @description 查询阶段状态
     * @param id
     * @return int
     * @author Mr.M
     * @date 2022/9/21 20:54
    */
    public int getStageOne(long id);
    public int getStageTwo(long id);
    public int getStageThree(long id);
    public int getStageFour(long id);

}

```

消息SDK提供消息处理抽象类，此抽象类供使用方去继承使用，如下：

```java
package com.xuecheng.messagesdk.service;

import com.xuecheng.messagesdk.model.po.MqMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Mr.M
 * @version 1.0
 * @description 消息处理抽象类
 * @date 2022/9/21 19:44
 */
@Slf4j
@Data
public abstract class MessageProcessAbstract {

    @Autowired
    MqMessageService mqMessageService;


    /**
     * @param mqMessage 执行任务内容
     * @return boolean true:处理成功，false处理失败
     * @description 任务处理
     * @author Mr.M
     * @date 2022/9/21 19:47
     */
    public abstract boolean execute(MqMessage mqMessage);


    /**
     * @description 扫描消息表多线程执行任务
     * @param shardIndex 分片序号
     * @param shardTotal 分片总数
     * @param messageType  消息类型
     * @param count  一次取出任务总数
     * @param timeout 预估任务执行时间,到此时间如果任务还没有结束则强制结束 单位秒
     * @return void
     * @author Mr.M
     * @date 2022/9/21 20:35
    */
    public void process(int shardIndex, int shardTotal,  String messageType,int count,long timeout) {

        try {
            //扫描消息表获取任务清单
            List<MqMessage> messageList = mqMessageService.getMessageList(shardIndex, shardTotal,messageType, count);
            //任务个数
            int size = messageList.size();
            log.debug("取出待处理消息"+size+"条");
            if(size<=0){
                return ;
            }

            //创建线程池
            ExecutorService threadPool = Executors.newFixedThreadPool(size);
            //计数器
            CountDownLatch countDownLatch = new CountDownLatch(size);
            messageList.forEach(message -> {
                threadPool.execute(() -> {
                    log.debug("开始任务:{}",message);
                    //处理任务
                    try {
                        boolean result = execute(message);
                        if(result){
                            log.debug("任务执行成功:{})",message);
                            //更新任务状态,删除消息表记录,添加到历史表
                            int completed = mqMessageService.completed(message.getId());
                            if (completed>0){
                                log.debug("任务执行成功:{}",message);
                            }else{
                                log.debug("任务执行失败:{}",message);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.debug("任务出现异常:{},任务:{}",e.getMessage(),message);
                    }finally {
                        //计数
                        countDownLatch.countDown();
                    }
                    log.debug("结束任务:{}",message);

                });
            });

            //等待,给一个充裕的超时时间,防止无限等待，到达超时时间还没有处理完成则结束任务
            countDownLatch.await(timeout,TimeUnit.SECONDS);
            System.out.println("结束....");
        } catch (InterruptedException e) {
           e.printStackTrace();

        }


    }



}

```





##### 5.4.4.2 消息模块sdk测试

1、在内容管理数据库创建消息表和消息历史表

![image-20230327090551682](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/04e63f2eafbb6ac3f1373786034f6c28.png)

2、拷贝课程资料中的xuecheng-plus-message-sdk到工程目录，如下图：

![image-20230327090851719](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/2626c3c1e081aa3bf6b96e89ced32270.png)

3、修改test下的bootstrap.yml中的数据库连接

下边测试消息SDK的接口：

1、继承MessageProcessAbstract 抽象类编写任务执行方法

```java
package com.xuecheng.messagesdk;

import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.messagesdk.service.MessageProcessAbstract;
import com.xuecheng.messagesdk.service.MqMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * @description 消息处理测试类，继承MessageProcessAbstract
 * @author Mr.M
 * @date 2022/9/21 21:44
 * @version 1.0
 */
@Slf4j
@Component
public class MessageProcessClass extends MessageProcessAbstract {


    @Autowired
    MqMessageService mqMessageService;

    //执行任务
    @Override
    public boolean execute(MqMessage mqMessage) {
        Long id = mqMessage.getId();
        log.debug("开始执行任务:{}",id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //取出阶段状态
        int stageOne = mqMessageService.getStageOne(id);
        if(stageOne<1){
            log.debug("开始执行第一阶段任务");
            System.out.println();
            int i = mqMessageService.completedStageOne(id);
            if(i>0){
                log.debug("完成第一阶段任务");
            }

        }else{
            log.debug("无需执行第一阶段任务");
        }

        return true;
    }
}

```

3、准备测试数据，在消息表添加消息类型为"test"的消息

4、执行MessageProcessClassTest 类中的test()方法，观察控制台任务执行的日志信息。

##### 5.4.4.3 集成消息sdk

###### 5.4.4.3.1 添加消息

在内容管理service工程中添加sdk依赖

```xml
<dependency>
    <groupId>com.xuecheng</groupId>
    <artifactId>xuecheng-plus-message-sdk</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

```

程发布操作使用本地事务保存课程发布信息、添加消息表。

回到当初编写课程发布时的代码，如下

![image-20230327100643557](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/c792cfa400fb3c86d1fd577bcc5f37f1.png)

```java
 /**
    * @description TODO 课程发布成功写入消息表
    * @param courseId
    * @return void
    * @author: woldier
    * @date: 2023/3/26 20:15
    */
    @Override
    @Transactional
    public void saveCoursePublishMessage(Long courseId) throws XueChengPlusException {

        MqMessage mqMessage = mqMessageService.addMessage("course_publish", String.valueOf(courseId), null, null);
        if(mqMessage==null){
            XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }

    }
```

下边进行测试：

发布一门课程，观察消息表是否正常添加消息。

需要手动修改课程审核状态为审核通过执行发布操作，发布后可以修改发布状态为下架重新发布测试。



###### 5.4.4.3.2 课程发布任务处理

在内容管理服务添加消息处理sdk的依赖即可使用它，实现sdk中的MessageProcessAbstract类，重写execte方法。

实现sdk中的MessageProcessAbstract类：

```java
package com.xuecheng.content.service.jobhandler;

import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.messagesdk.service.MessageProcessAbstract;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布任务实现类
 * @date 2023/3/27 10:27
 **/
public class CoursePublishTask extends MessageProcessAbstract {
    /**
    * @description 任务执行
    * @param mqMessage
    * @return boolean
    * @author: woldier
    * @date: 2023/3/27 10:28
    */
    @Override
    public boolean execute(MqMessage mqMessage) {
        //消息id
        Long id = mqMessage.getId();
        //取出对应的course 这里我们约定课程id存在于message表的 businessKey1 字段
        Integer courseId = Integer.valueOf(mqMessage.getBusinessKey1());

        //执行阶段1 ,静态化页面

        //执行阶段2 , 写入 elasticsearch

        //执行阶段3 , 写入redis



        return true;
    }
      //生成课程静态化页面并上传至文件系统
    public void generateCourseHtml(MqMessage mqMessage, long courseId) {

        log.debug("开始进行课程静态化,课程id:{}", courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理
        int stageOne = mqMessageService.getStageOne(id);
        if (stageOne > 0) {
            log.debug("课程静态化已处理直接返回，课程id:{}", courseId);
            return;
        }
        // TODO 静态化业务逻辑
        try {

            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //保存第一阶段状态
        mqMessageService.completedStageOne(id);

    }

    //将课程信息缓存至redis
    public void saveCourseCache(MqMessage mqMessage,long courseId){
        log.debug("将课程信息缓存至redis,课程id:{}",courseId);
        // TODO 将课程信息缓存至redis
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    //保存课程索引信息
    public void saveCourseIndex(MqMessage mqMessage,long courseId){
        log.debug("保存课程索引信息,课程id:{}",courseId);

        // TODO 保存课程索引信息
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}

```



###### 5.4.4.3.3 开启任务调度

首先在内容管理service工程中添加xxl-job依赖

```xml
<dependency>
    <groupId>com.xuxueli</groupId>
    <artifactId>xxl-job-core</artifactId>
</dependency>

```

配置执行器

在nacos中在content-service-dev.yaml中配置

```yaml
xxl:
  job:
    admin: 
      addresses: http://192.168.101.65:8088/xxl-job-admin
    executor:
      appname: coursepublish-job
      address: 
      ip: 
      port: 8999
      logpath: /data/applogs/xxl-job/jobhandler
      logretentiondays: 30
    accessToken: default_token

```

从媒资管理服务层工程中拷贝一个XxlJobConfig配置类到内容管理service工程中。

 ```java
 package com.xuecheng.content.config;
 
 import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 
 /**
  * xxl-job config
  * xxl-job config类
  * @author xuxueli 2017-04-28
  */
 @Configuration
 public class XxlJobConfig {
     private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);
 
     @Value("${xxl.job.admin.addresses}")
     private String adminAddresses;
 
     @Value("${xxl.job.accessToken}")
     private String accessToken;
 
     @Value("${xxl.job.executor.appname}")
     private String appname;
 
     @Value("${xxl.job.executor.address}")
     private String address;
 
     @Value("${xxl.job.executor.ip}")
     private String ip;
 
     @Value("${xxl.job.executor.port}")
     private int port;
 
     @Value("${xxl.job.executor.logpath}")
     private String logPath;
 
     @Value("${xxl.job.executor.logretentiondays}")
     private int logRetentionDays;
 
 
     @Bean
     public XxlJobSpringExecutor xxlJobExecutor() {
         logger.info(">>>>>>>>>>> xxl-job config init.");
         XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
         xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
         xxlJobSpringExecutor.setAppname(appname);
         xxlJobSpringExecutor.setAddress(address);
         xxlJobSpringExecutor.setIp(ip);
         xxlJobSpringExecutor.setPort(port);
         xxlJobSpringExecutor.setAccessToken(accessToken);
         xxlJobSpringExecutor.setLogPath(logPath);
         xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
 
         return xxlJobSpringExecutor;
     }
 
     /**
      * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
      *
      *      1、引入依赖：
      *          <dependency>
      *             <groupId>org.springframework.cloud</groupId>
      *             <artifactId>spring-cloud-commons</artifactId>
      *             <version>${version}</version>
      *         </dependency>
      *
      *      2、配置文件，或者容器启动变量
      *          spring.cloud.inetutils.preferred-networks: '5.5.xxx.'
      *
      *      3、获取IP
      *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
      */
 
 
 }
 ```



在xxl-job-admin控制台中添加执行器

![image-20230327110542345](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/ac4be71127c1eef4348994ca07810e07.png)

编写任务调度入口

```java
@Slf4j
@Component
public class CoursePublishTask extends MessageProcessAbstract {

    /**
     * @return void
     * @description 任务调度入口
     * @author: woldier
     * @date: 2023/3/27 11:09
     */

    @XxlJob("CoursePublishJobHandler") //任务名
    public void coursePublishJobHandler() throws Exception {
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        log.debug("shardIndex=" + shardIndex + ",shardTotal=" + shardTotal);
        //参数:分片序号、分片总数、消息类型、一次最多取到的任务数量、一次任务调度执行的超时时间
        process(shardIndex, shardTotal, "course_publish", 30, 60);
    }
    
    /*省略implament代码*/
}
```

在xxl-job添加任务

![image-20230327111214344](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/e33fddf8b0946e887083401ce53904ed.png)

到此SDK开发、集成完成，下一步添加课程发布后页面静态化、课程缓存、课程索引等任务。



###### 5.4.4.3.4 测试

在消息表添加课程发布的消息，消息类型为course_publish,business_key1为发布课程的ID

1、测试是否可以正常调度执行。

2、测试任务幂等性

在 saveCourseCache(mqMessage,courseId);处打断点，待执行到这里观察数据库第一阶段完成的标记预期标记为1。

结束进程，再重新启动，观察第一阶段的任务预期不再执行。

3、任务执行完成删除消息表记录，插入历史表，state状态字段为1

#### 5.4.5 页面静态化

##### 5.4.5.1 什么是页面静态化

根据课程发布的操作流程，执行课程发布后要将课程详情信息页面静态化，生成html页面上传至文件系统。

==什么是页面静态化？==

课程预览功能通过模板引擎技术在页面模板中填充数据，生成html页面，这个过程是当客户端请求服务器时服务器才开始渲染生成html页面，最后响应给浏览器，服务端渲染的并发能力是有限的。

页面静态化则强调将生成html页面的过程提前，提前使用模板引擎技术生成html页面，当客户端请求时直接请求html页面，由于是静态页面可以使用nginx、apache等高性能的web服务器，并发性能高。

==什么时候能用页面静态化技术？==

当数据变化不频繁，一旦生成静态页面很长一段时间内很少变化，此时可以使用页面静态化。因为如果数据变化频繁，一旦改变就需要重新生成静态页面，导致维护静态页面的工作量很大。

根据课程发布的业务需求，虽然课程发布后仍可以修改课程信息，但需要经过课程审核，且修改频度不大，所以适合使用页面静态化。

##### 5.4.5.2 静态化测试

下边使用freemarker技术对页面静态化生成html页面。

在内容管理service工程中添加freemarker依赖

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>

```

编写测试方法

```java
package com.xuecheng.content;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.model.dto.CoursePreviewDto;
import com.xuecheng.content.service.CoursePublishService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author woldier
 * @version 1.0
 * @description freemarker 静态化测试
 * @date 2023/3/27 12:01
 **/
@SpringBootTest
public class FreemarkerTest {
    @Autowired
    CoursePublishService coursePublishService;


    //测试页面静态化
    @Test
    public void testGenerateHtmlByTemplate() throws IOException, TemplateException, XueChengPlusException {
        //配置freemarker
        Configuration configuration = new Configuration(Configuration.getVersion());

        //加载模板
        //选指定模板路径,classpath下templates下
        //得到classpath路径
        String classpath = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates/"));
        //设置字符编码
        configuration.setDefaultEncoding("utf-8");

        //指定模板文件名称
        Template template = configuration.getTemplate("course_template.ftl");

        //准备数据
        CoursePreviewDto coursePreviewInfo = coursePublishService.getCoursePreviewInfo(2L);

        Map<String, Object> map = new HashMap<>();
        map.put("model", coursePreviewInfo);

        //静态化
        //参数1：模板，参数2：数据模型
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        System.out.println(content);
        //将静态化内容输出到文件中
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出流
        FileOutputStream outputStream = new FileOutputStream("D:\\java_lesson\\test.html");
        IOUtils.copy(inputStream, outputStream);

    }



}

```



##### 5.4.5.3 上传文件测试

###### 5.4.5.3.1 配置远程调用环境

静态化生成文件后需要上传至分布式文件系统，根据微服务的职责划分，媒资管理服务负责维护文件系统中的文件，所以内容管理服务对页面静态化生成html文件需要调用媒资管理服务的上传文件接口。如下图：

![image-20230327144045438](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/aa5550cfd75fe10d246b4e7baba5d874.png)

微服务之间难免会存在远程调用，在Spring Cloud中可以使用Feign进行远程调用，

Feign是一个声明式的http客户端，官方地址：https://github.com/OpenFeign/feign

其作用就是帮助我们优雅的实现http请求的发送，解决上面提到的问题。

下边先准备Feign的开发环境:

1、在内容管理content-service工程添加依赖：

```java
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<!-- Spring Cloud 微服务远程调用 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-httpclient</artifactId>
</dependency>
<!--feign支持Multipart格式传参-->
<dependency>
    <groupId>io.github.openfeign.form</groupId>
    <artifactId>feign-form</artifactId>
    <version>3.8.0</version>
</dependency>
<dependency>
    <groupId>io.github.openfeign.form</groupId>
    <artifactId>feign-form-spring</artifactId>
    <version>3.8.0</version>
</dependency>

```

2、在nacos配置feign-dev.yaml公用配置文件

```yaml
feign:
  hystrix:
    enabled: true
  circuitbreaker:
    enabled: true
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 30000  #熔断超时时间
ribbon:
  ConnectTimeout: 60000 #连接超时时间
  ReadTimeout: 60000 #读超时时间
  MaxAutoRetries: 0 #重试次数
  MaxAutoRetriesNextServer: 1 #切换实例的重试次数


```

3、在内容管理service工程和内容管理api工程都引入此配置文件

```java
shared-configs:
  - data-id: feign-${spring.profiles.active}.yaml
    group: xuecheng-plus-common
    refresh: true

```

4、在内容管理service工程配置feign支持Multipart，拷贝课程资料下的MultipartSupportConfig 到content-service工程下的config包下。

```JAVA
package com.xuecheng.content.config;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/10/15 22:13
 */
@Configuration
public class MultipartSupportConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    @Primary//注入相同类型的bean时优先使用
    @Scope("prototype")
    public Encoder feignEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

    //将file转为Multipart
    public static MultipartFile getMultipartFile(File file) {
        FileItem item = new DiskFileItemFactory().createItem("file", MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
        try (FileInputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = item.getOutputStream();) {
            IOUtils.copy(inputStream, outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonsMultipartFile(item);
    }
}

```



###### 5.4.5.3.2 扩充上传文件接口

现在需要将课程的静态文件上传到minio，单独存储到course目录下，文件的objectname为"课程id.html"，原有的上传文件接口需要增加一个参数 objectname。

下边扩充媒资服务的上传文件接口

```java
    @ApiOperation("静态化网页上传")
    @RequestMapping(value = "/upload/coursehtml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadHtml(
            @RequestPart("filedata") MultipartFile upload,
            @RequestParam(value = "objectName", required = false) String objectName) throws IOException {
        File tempFile = getTempFile(upload);
        mediaFileService.uploadObject(tempFile.getAbsolutePath(),"course/"+ objectName);
        boolean delete = tempFile.delete();
        if (!delete)
            log.error("删除文件错误{}", tempFile.getAbsolutePath());
    }


```

添加一个业务方法上传minio

```java
    void uploadObject(String localFilePath, String objectName);
```

实现类如下

```java

/**
     * @param localFilePath 临时文件路径
     * @param objectName    网页名
     * @return void
     * @description 上传课程静态化网页
     * @author: woldier
     * @date: 2023/3/27 15:31
     */
    @Override
    public void uploadObject(String localFilePath, String objectName) {
        //所有的静态文件都存在course文件夹下
        minIOUpload(localFilePath, getMimeType(objectName), fileBucket, objectName);
    }
```





###### 5.4.5.3.3 远程调用测试

在content-service下编写feign接口

1.接口加上`@FeignClient`注解表示这是一个feign远程调用的接口;

其中的`value`属性设置目标远程调用用服务的服务名

`configuration`configuration进行额外配置,使得能够支持spring-multipart文件

```java
package com.xuecheng.content.feignclient;

import com.xuecheng.content.config.MultipartSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author woldier
 * @version 1.0
 * @description openfeign-媒资服务远程调用
 * @date 2023/3/27 15:55
 **/
@FeignClient(value = "media-api")  // 注解未feign远程调用api,value属性执行调用的服务名
@RequestMapping("/media")
public interface MediaServiceClient {
    @RequestMapping(value = "/upload/coursehtml", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,configuration = MultipartSupportConfig.class)
    public void uploadHtml(
            @RequestPart("filedata") MultipartFile upload,
            @RequestParam(value = "objectName", required = false) String objectName);
}

```

2.在启动类添加@EnableFeignClients注解

```java
package com.xuecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author woldier
 * @version 1.0
 * @description content 服务 启动类
 * @date 2023/2/15 12:03
 **/
@SpringBootApplication
@EnableFeignClients(basePackages={"com.xuecheng.content.feignclient"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
```

注意的是,我们当前是在contentservice的启动类上加的,用于测试,若之后启动content-api,需要在content-api的启动类上加入该注解

```java
package com.xuecheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author woldier
 * @version 1.0
 * @description content 服务 启动类
 * @date 2023/2/15 12:03
 **/
@SpringBootApplication
@EnableFeignClients(basePackages={"com.xuecheng.content.feignclient"}) //配置扫描feign调用接口所存在的包路径
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
```

接下来我们进行测试,在测试文件夹下

```java
package com.xuecheng.content;

import com.xuecheng.content.config.MultipartSupportConfig;
import com.xuecheng.content.feignclient.MediaServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@SpringBootTest
public class FeignUploadTest {

    @Autowired
    MediaServiceClient mediaServiceClient;

    //远程调用，上传文件
    @Test
    public void test() {
    
        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(new File("D:\\java_lesson\\11.html"));
        mediaServiceClient.uploadHtml(multipartFile,"12.html");
    }

}

```

![image-20230327160939019](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/a9e34f5494762061d711f6040c066b23.png)

检查minio 上传成功

访问：http://192.168.0.108:9000/mediafiles/course/12.html

查看是否可以正常访问。

 

##### 5.4.5.4 熔断降级

###### 5.4.5.4.1 什么是熔断降级

微服务中难免存在服务之间的远程调用，比如：内容管理服务远程调用媒资服务的上传文件接口，当微服务运行不正常会导致无法正常调用微服务，此时会出现异常，如果这种异常不去处理可能导致雪崩效应。

微服务的雪崩效应表现在服务与服务之间调用，当其中一个服务无法提供服务可能导致其它服务也死掉，比如：服务B调用服务A，由于A服务异常导致B服务响应缓慢，最后B、C等服务都不可用，像这样由一个服务所引起的一连串的多个服务无法提供服务即是微服务的雪崩效应，如下图：

![image-20230327161108798](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/53c6eb7cf71ad32c5a1dc838908a8450.png)

如何解决由于微服务异常引起的雪崩效应呢？

可以采用熔断、降级的方法去解决。

熔断降级的相同点都是为了解决微服务系统崩溃的问题，但它们是两个不同的技术手段，两者又存在联系。

熔断：

当下游服务异常而断开与上游服务的交互，它就相当于保险丝，下游服务异常触发了熔断，从而保证上游服务不受影响。

![image-20230327161137731](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/b1769f578c7e0a76d04f8478f3ee1e5a.png)



降级：

当下游服务异常触发熔断后，上游服务就不再去调用异常的微服务而是执行了降级处理逻辑，这个降级处理逻辑可以是本地一个单独的方法。

![image-20230327161159811](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/f9a7144f46c28f53ee787b266ae0610f.png)



两者都是为了保护系统，熔断是当下游服务异常时一种保护系统的手段，降级是熔断后上游服务处理熔断的方法。



###### 5.4.5.4.2 熔断降级处理

项目使用Hystrix框架实现熔断、降级处理，在feign-dev.yaml中配置。

1、开启Feign熔断保护

```yaml
feign:
  hystrix:
    enabled: true
  circuitbreaker:
    enabled: true

```

2、设置熔断的超时时间，为了防止一次处理时间较长触发熔断这里还需要设置请求和连接的超时时间，如下：

```yaml
hystrix:
  command:
    default:
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 30000  #熔断超时时间
ribbon:
  ConnectTimeout: 60000 #连接超时时间
  ReadTimeout: 60000 #读超时时间
  MaxAutoRetries: 0 #重试次数
  MaxAutoRetriesNextServer: 1 #切换实例的重试次数

```

3、定义降级逻辑

两种方法：

1）fallback 

```java
@FeignClient(value = "media-api",configuration = MultipartSupportConfig.class,fallback = MediaServiceClientFallback.class)
@RequestMapping("/media")
public interface MediaServiceClient{
///***
}
```

定义一个fallback类`MediaServiceClientFallback`，此类实现了`MediaServiceClient`接口。

第一种方法无法取出熔断所抛出的异常，第二种方法定义`MediaServiceClientFallbackFactory `可以解决这个问题。

2）fallbackFactory 

第二种方法在FeignClient中指定fallbackFactory ，如下：

```java
@FeignClient(value = "media-api",configuration = MultipartSupportConfig.class,fallbackFactory = MediaServiceClientFallbackFactory.class)
@RequestMapping("/media")
public interface MediaServiceClient{
///***
}
```

定义`MediaServiceClientFallbackFactory`如下：

```java
@Slf4j
@Component
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {
    @Override
    public MediaServiceClient create(Throwable throwable) {
        return new MediaServiceClient(){
            @Override
            public String uploadFile(MultipartFile upload, String objectName) {
                //降级方法
                log.debug("调用媒资管理服务上传文件时发生熔断，异常信息:{}",throwable.toString(),throwable);
                return null;
            }
        };
    }
}

```

降级处理逻辑：

返回一个null对象，上游服务请求接口得到一个null说明执行了降级处理。

测试：

停止媒资管理服务或人为制造异常观察是否执行降级逻辑。



##### 5.4.5.5 课程静态化开发

课程页面静态化和静态页面远程上传测试通过，下一步开发课程静态化功能，最终使用消息处理SDK去调度执行。

###### 5.4.5.5.1 静态化测试

课程静态化包括两部分工作：生成课程静态化页面，上传静态页面到文件系统。

在课程发布的service编写这两部分内容，最后通过消息去调度执行。

`CoursePublishService.java`

1、接口方法定义

```java
/**
 * @description 课程静态化
 * @param courseId  课程id
 * @return File 静态化文件
 * @author Mr.M
 * @date 2022/9/23 16:59
*/
public File generateCourseHtml(Long courseId);
/**
 * @description 上传课程静态化页面
 * @param file  静态化文件
 * @return void
 * @author Mr.M
 * @date 2022/9/23 16:59
*/
public void  uploadCourseHtml(Long courseId,File file);

```

2、接口实现

将之前编写的静态化测试代码以及上传静态文件测试代码拷贝过来使用

```java


            /**
     * description 生成课程静态化页面
     *
     * @param courseId 课程id
     * @return java.io.File
     * @author: woldier
     * @date: 2023/3/27 16:52
     */
    @Override
    public File generateCourseHtml(Long courseId) throws XueChengPlusException {
        //配置freemarker
        Configuration configuration = new Configuration(Configuration.getVersion());

        //加载模板
        //选指定模板路径,classpath下templates下
        //得到classpath路径
        String classpath = this.getClass().getResource("/").getPath();
        try {
            configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates/"));
        } catch (IOException e) {
            log.error("失败,err{}",e.getCause());
            e.printStackTrace();
            XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }
        //设置字符编码
        configuration.setDefaultEncoding("utf-8");

        //指定模板文件名称
        Template template = null;
        try {
            template = configuration.getTemplate("course_template.ftl");
        } catch (IOException e) {
            log.error("加载模板文件失败,{}",e.getCause());
            e.printStackTrace();
            XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }

        //准备数据
        CoursePreviewDto coursePreviewInfo = getCoursePreviewInfo(courseId);

        Map<String, Object> map = new HashMap<>();
        map.put("model", coursePreviewInfo);

        //静态化
        //参数1：模板，参数2：数据模型
        String content = null;
        try {
            content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException | TemplateException e) {
            log.error("模型渲染出错");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println(content);
        //将静态化内容输出到文件中
        InputStream inputStream = IOUtils.toInputStream(content);
        //输出流
        File tempFile = null;
        try {
            tempFile = File.createTempFile("coursehtml",".temp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(FileOutputStream outputStream = new FileOutputStream(tempFile)){
            IOUtils.copy(inputStream, outputStream);
        }catch (Exception e){
            log.error("出错了");
            e.printStackTrace();
             XueChengPlusException.cast(CommonError.UNKOWN_ERROR);
        }

        return tempFile;
    }

    /**
     * description 上传课程静态化网页到minio
     *
     * @param courseId 课程id
     * @param file     本地静态化html文件
     * @return void
     * @author: woldier
     * @date: 2023/3/27 16:55
     */
    @Override
    public void uploadCourseHtml(Long courseId, File file) throws XueChengPlusException {
        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(file);
        mediaServiceClient.uploadHtml(multipartFile,courseId.toString()+".html");
    }
```



完善课程发布任务CoursePublishTask类的代码：

```java
private final CoursePublishService coursePublishService;
//生成课程静态化页面并上传至文件系统
    public void generateCourseHtml(MqMessage mqMessage, long courseId) {

        log.debug("开始进行课程静态化,课程id:{}", courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理
        int stageOne = mqMessageService.getStageOne(id);
        if (stageOne > 0) {
            log.debug("课程静态化已处理直接返回，课程id:{}", courseId);
            return;
        }
        //生成静态页面
        try {
            File file = coursePublishService.generateCourseHtml(courseId);
            coursePublishService.uploadCourseHtml(courseId,file);
            file.delete();
        } catch (XueChengPlusException e) {
            log.error("静态化页面出错");
            throw new RuntimeException(e);
        }


        //保存第一阶段状态
        mqMessageService.completedStageOne(id);

    }
```







###### 5.4.5.5.2 测试

1、启动网关、媒资管理服务工程。

2、在内容管理api工程的启动类上配置FeignClient

```java
@EnableFeignClients(basePackages={"com.xuecheng.content.feignclient"})
```

在bootstrap.yml引用feign-dev.yaml

```java
- data-id: feign-${spring.profiles.active}.yaml
  group: xuecheng-plus-common
  refresh: true  #profiles默认为dev

```

启动内容管理接口工程。

在CoursePublishTask类的execute方法中打上断点。

 

3、发布一门课程，保存消息表存在未处理的处理。

 

4、启动xxl-job调度中心、启动课程发布任务，等待定时调度。

![image-20230327172852366](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/67ed38c0526f5dddbc81d7ab6fd412d3.png)

5、观察任务调度日志，观察任务是否可以正常处理。

6、处理完成进入文件系统，查询mediafiles桶内是否存在以课程id命名的html文件

![image-20230327172939000](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/24c76b57b79d98d80214de634e85abe0.png)

如果不存在说明课程静态化存在问题，再仔细查看执行日志，排查问题。

如果存在则说明课程静态化并上传到minio成功。



###### 5.4.5.5.3 浏览详细页面             

课程静态化成功后可以用浏览器访问html文件是否可以正常浏览，下图表示可以正常浏览。

http://localhost:9000/mediafiles/course/130.html

http://file.51xuecheng.cn/mediafiles/course/130.html

![image-20230327173116583](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/191f11deb2e4a19cf0aa103be901c392.png)

页面还没有样式，需要在nginx配置虚拟目录，在www.51xuecheng.cn下配置： 

这是因为原有的访问路径为`file.51xuecheng.cn/mediafiles/course/130/html ` ,而该站点下没有其他的静态文件,所以我们要在主站也配置代理



![image-20230327173254133](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/e02f225cd88e3203909c57c7725dcba5.png)



​            在主站中配置如下`www.51xuecheng.cn`

```shell
        #映射到file.51xuecheng.cn
        location /course/ {  
        proxy_pass http://fileserver/mediafiles/course/;
} 


```

加载nginx配置文件

访问：http://www.51xuecheng.cn/course/130.html

![image-20230327174215186](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/8901765a95df1fd8bc47e9cb26fb83a2.png)



### 5.5 课程搜索

#### 5.5.1 需求分析

##### 5.5.1.1 模块介绍

搜索功能是一个系统的重要功能，是信息查询的方式。课程搜索是课程展示的渠道，用户通过课程搜索找到课程信息，进一步去查看课程的详细信息，进行选课、支付、学习。

本项目的课程搜索支持全文检索技术，什么是全文检索？

[全文检索](https://baike.baidu.com/item/全文检索/8028630?fromModule=lemma_inlink)是指计算机索引程序通过扫描文章中的每一个词，对每一个词建立一个索引，指明该词在文章中出现的次数和位置，当用户查询时，检索程序就根据事先建立的索引进行查找，并将查找的结果反馈给用户的检索方式。这个过程类似于通过字典中的检索字表查字的过程。

全文检索可以简单理解为通过索引搜索文章。

![image-20230328090729850](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/2f3478664fed8afb8dad1cab60dcd059.png)

全文检索的速度非常快，早期应用在搜索引擎技术中，比如：百度、google等，现在通常一些大型网站的搜索功能都是采用全文检索技术。

![image-20230328090759492](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/bc302f08ce2d681ed30ccb3579ae974e.png)

课程搜索也要将课程信息建立索引，在课程发布时建立课程索引，索引建立好用户可通过搜索网页去查询课程信息。

![image-20230328091026013](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/aef33ea678b7aeb5b32fd3a44abe4c4c.png)

所以，课程搜索模块包括两部分：课程索引、课程搜索。

课程索引是将课程信息建立索引。

课程搜索是通过前端网页，通过关键字等条件去搜索课程。





##### 5.5.1.2 业务流程



根据模块介绍的内容，课程搜索模块包括课程索引、课程搜索两部分。

1、课程索引

在课程发布操作执行后通过消息处理方式创建课程索引，如下图：

![image-20230328091108780](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/b8c45f80e6a8dd51baaff106449892ae.png)



本项目使用elasticsearch作为索引及搜索服务。

2、课程搜索

课程索引创建完成，用户才可以通过前端搜索课程信息。

课程搜索可以从首页进入搜索页面。

![image-20230328091138276](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/7c34feb3d4ae54f00de97f8a4dba3387.png)



下图是搜索界面，可以通过课程分类、课程难度等级等条件进行搜索。

![image-20230328091155865](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/05cb5af329fb704f28b80c901a57ed43.png)





#### 5.5.2 准备环境

##### 5.5.2.1 搭建elasticsearch

docker es 安装详见

https://zhuanlan.zhihu.com/p/163186766



安装ik分词https://blog.csdn.net/weixin_45821811/article/details/119960204

https://www.cnblogs.com/szwdun/p/10664348.html

若要修改kibanas对应es的ip可以

```shell
docker exec -it c_kibanas bash #进入容器

vi config.xml # 修改配置
```

开发中主要使用kibana通过api对elasticsearch进行索引和搜索操作，通过浏览器访问http://your.esip.com:5601/app/kibana#/dev_tools/console进入kibana的开发工具界面。

![image-20230328091657696](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/0b78784a3d4382361be0fabecc819394.png)

可通过命令：GET /_cat/indices?v 查看所有的索引，通过此命令判断kibana是否正常连接elasticsearch。

索引相当于MySQL中的表，Elasticsearch与MySQL之间概念的对应关系见下表：

![image-20230328091738245](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/cb8993feacee522e9f5b88be3a025078.png)



要使用elasticsearch需要建立索引，Mapping相当于表结构，Mapping创建后其字段不能删除，如果要删除需要删除整个索引，下边介绍创建索引、查询索引、删除索引的方法：

1、创建索引，并指定Mapping。

```json
PUT /course-publish
{
  "settings": {
    "number_of_shards": 1,
    "number_of_replicas": 0
  },
  "mappings": {"properties": {
      "id": {
        "type": "keyword"
      },
      "companyId": {
        "type": "keyword"
      },
      "companyName": {
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_smart",
        "type": "text"
      },
      "name": {
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_smart",
        "type": "text"
      },
      "users": {
        "index": false,
        "type": "text"
      },
      "tags": {
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_smart",
        "type": "text"
      },
      "mt": {
        "type": "keyword"
      },
      "mtName": {
        "type": "keyword"
      },
      "st": {
        "type": "keyword"
      },
      "stName": {
        "type": "keyword"
      },
      "grade": {
        "type": "keyword"
      },
      "teachmode": {
        "type": "keyword"
      },
  "pic": {
        "index": false,
        "type": "text"
      },
      "description": {
        "analyzer": "ik_max_word",
        "search_analyzer": "ik_smart",
        "type": "text"
      },
      "createDate": {
        "format": "yyyy-MM-dd HH:mm:ss",
        "type": "date"
      },
      "status": {
        "type": "keyword"
      },
      "remark": {
        "index": false,
        "type": "text"
      },
      "charge": {
        "type": "keyword"
      },
      "price": {
        "type": "scaled_float",
        "scaling_factor": 100
      },
      "originalPrice": {
        "type": "scaled_float",
        "scaling_factor": 100
      },
      "validDays": {
        "type": "integer"
      }
    }
  }
}


```



2、查询索引

通过 GET /_cat/indices?v 查询所有的索引，查找course-publish是否创建成功。

通过GET /course-publish/_mapping

 查询course-publish的索引结构。



3、删除索引

如果发现创建的course-publish不正确可以删除重新创建。

删除索引后当中的文档数据也同时删除，一定要谨慎操作！

删除索引命令：DELETE /course-publish



##### 5.5.2.2 部署搜索工程

拷贝课程资料中的xuecheng-plus-search搜索工程到自己的工程目录。

![image-20230328150238460](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/cce337eac708b111c86dddbfe0c6e6df.png)

修改bootstrap.yml 配置文件

启动网关、搜索服务。

部署完成通过httpclient进行测试



```http
### 添加课程索引
POST {{search_host}}/search/index/course
Content-Type: application/json

{
  "charge" : "201000",
  "companyId" : 100000,
  "companyName" : "北京黑马程序",
  "createDate" : "2022-09-25 09:36:11",
  "description" : "《Spring编程思想》是2007年6月1日机械工业出版社出版的图书，作者是埃克尔，译者是陈昊鹏。主要内容本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作",
  "grade" : "204001",
  "id" : 102,
  "mt" : "1-3",
  "mtName" : "编程开发",
  "name" : "Spring编程思想",
  "originalPrice" : 200.0,
  "pic" : "/mediafiles/2022/09/20/1d0f0e6ed8a0c4a89bfd304b84599d9c.png",
  "price" : 100.0,
  "remark" : "没有备注",
  "st" : "1-3-2",
  "stName" : "Java语言",
  "status" : "203002",
  "tags" : "没有标签",
  "teachmode" : "200002",
  "validDays" : 222
}



### 搜索课程
GET {{search_host}}/search/course/list?pageNo=1&keywords=spring
Content-Type: application/json


```



#### 5.5.3 索引管理

##### 5.5.3.1 REST API

###### 5.5.1.1.1 添加文档

索引创建好就可以向其中添加文档，此时elasticsearch会根据索引的mapping配置对有些字段进行分词。

这里我们要向course_publish中添加课程信息。

使用rest api进行测试，如下：

使用post请求，/course-publish/_doc/103 第一部分为索引名称，_doc固定，103为文档的主键id，这里为课程id。

课程内容使用json表示。

```json
POST /course-publish/_doc/103
{
  "charge" : "201001",
  "companyId" : 100000,
  "companyName" : "北京黑马程序",
  "createDate" : "2022-09-25 09:36:11",
  "description" : "HTML/CSS",
     "grade" : "204001",
  "id" : 102,
  "mt" : "1-1",
  "mtName" : "前端开发",
  "name" : "Html参考大全",
  "originalPrice" : 200.0,
  "pic" : "/mediafiles/2022/09/20/e726b71ba99c70e8c9d2850c2a7019d7.jpg",
  "price" : 100.0,
  "remark" : "没有备注",
  "st" : "1-1-1",
  "stName" : "HTML/CSS",
  "status" : "203002",
  "tags" : "没有标签",
  "teachmode" : "200002",
  "validDays" : 222
}


```

如果要修改文档的内容可以使用上边相同的方法，如果没有则添加，如果存在则更新。

###### 5.5.3.1.2 查询文档

添加文档成功后可以通过主键id查询该文档的信息。

语法如下：

```json
GET /{索引库名称}/_doc/{id}
```

5.5.3.1.3 更新文档

更新文档分为全量更新和局部更新。

全量更新是指先删除再更新，语法如下：

```java
PUT /{索引库名}/_doc/文档id
{
    "字段1": "值1",
    "字段2": "值2",
    // ... 略
}

```

局部更新语法如下：

```json
POST /{索引库名}/_update/文档id
{
    "doc": {
         "字段名": "新的值",
    }
}

```

5.5.3.1.4 删除文档

删除文档将从索引中删除文档的记录。

语法如下：

```json
DELETE /{索引库名}/_doc/id值
```

##### 5.5.3.2 接口定义

当课程发布时请求添加课程接口添加课程信息到索引，当课程下架时请求删除课程接口从索引中删除课程信息，这里先实现添加课程接口。

根据索引的mapping结构创建po类

```java
package com.xuecheng.search.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 课程索引信息
 * </p>
 *
 * @author itcast
 */
@Data
public class CourseIndex implements Serializable {

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
     * 发布时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 状态
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

创建索引接口如下：

```java
package com.xuecheng.search.controller;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.search.po.CourseIndex;
import com.xuecheng.search.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程索引接口
 * @date 2022/9/24 22:31
 */
@Api(value = "课程信息索引接口", tags = "课程信息索引接口")
@RestController
@RequestMapping("/index")
public class CourseIndexController {

    @Value("${elasticsearch.course.index}")
    private String courseIndexStore;

    @Autowired
    IndexService indexService;

    @ApiOperation("添加课程索引")
    @PostMapping("course")
    public Boolean add(@RequestBody CourseIndex courseIndex) throws XueChengPlusException {

        Long id = courseIndex.getId();
        if(id==null){
            XueChengPlusException.cast("课程id为空");
        }
        Boolean result = indexService.addCourseIndex(courseIndexStore, String.valueOf(id), courseIndex);
        if(!result){
            XueChengPlusException.cast("添加课程索引失败");
        }
        return result;

    }
}

```







##### 5.5.3.3 接口开发

定义service接口，请求elasticsearch添加课程信息。

注意：为了适应其它文档信息，将添加文档定义为通用的添加文档接口，此接口不仅适应添加课程还适应添加其它信息

```java

package com.xuecheng.search.service;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.search.po.CourseIndex;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程索引service
 * @date 2022/9/24 22:40
 */
public interface IndexService {

    /**
     * @param indexName 索引名称
     * @param id 主键
     * @param object 索引对象
     * @return Boolean true表示成功,false失败
     * @description 添加索引
     * @author Mr.M
     * @date 2022/9/24 22:57
     */
    public Boolean addCourseIndex(String indexName,String id,Object object) throws XueChengPlusException;


    /**
     * @description 更新索引
     * @param indexName 索引名称
     * @param id 主键
     * @param object 索引对象
     * @return Boolean true表示成功,false失败
     * @author Mr.M
     * @date 2022/9/25 7:49
    */
    public Boolean updateCourseIndex(String indexName,String id,Object object) throws XueChengPlusException;

    /**
     * @description 删除索引
     * @param indexName 索引名称
     * @param id  主键
     * @return java.lang.Boolean
     * @author Mr.M
     * @date 2022/9/25 9:27
    */
    public Boolean deleteCourseIndex(String indexName,String id) throws XueChengPlusException;

}

```

接口实现如下

```java

package com.xuecheng.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.search.po.CourseIndex;
import com.xuecheng.search.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Mr.M
 * @version 1.0
 * @description 课程索引管理接口实现
 * @date 2022/9/25 7:23
 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {


    @Autowired
    RestHighLevelClient client;

    @Override
    public Boolean addCourseIndex(String indexName, String id, Object object) throws XueChengPlusException {
        String jsonString = JSON.toJSONString(object);
        IndexRequest indexRequest = new IndexRequest(indexName).id(id);
        //指定索引文档内容
        indexRequest.source(jsonString, XContentType.JSON);
        //索引响应对象
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("添加索引出错:{}", e.getMessage());
            e.printStackTrace();
            XueChengPlusException.cast("添加索引出错");
        }
        String name = indexResponse.getResult().name();
        System.out.println(name);
        return name.equalsIgnoreCase("created") || name.equalsIgnoreCase("updated");

    }

    @Override
    public Boolean updateCourseIndex(String indexName, String id, Object object) throws XueChengPlusException {

        String jsonString = JSON.toJSONString(object);
        UpdateRequest updateRequest = new UpdateRequest(indexName, id);
        updateRequest.doc(jsonString, XContentType.JSON);
        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("更新索引出错:{}", e.getMessage());
            e.printStackTrace();
            XueChengPlusException.cast("更新索引出错");
        }
        DocWriteResponse.Result result = updateResponse.getResult();
        return result.name().equalsIgnoreCase("updated");

    }

    @Override
    public Boolean deleteCourseIndex(String indexName, String id) throws XueChengPlusException {

        //删除索引请求对象
        DeleteRequest deleteRequest = new DeleteRequest(indexName, id);
        //响应对象
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("删除索引出错:{}", e.getMessage());
            e.printStackTrace();
            XueChengPlusException.cast("删除索引出错");
        }
        //获取响应结果
        DocWriteResponse.Result result = deleteResponse.getResult();
        return result.name().equalsIgnoreCase("deleted");
    }
}

```







##### 5.5.3.4 接口完善

##### 5.5.3.5 接口测试

使用httpclient 测试

```shell

### 添加课程索引
POST {{search_host}}/search/index/course
Content-Type: application/json

{
  "charge" : "201000",
  "companyId" : 100000,
  "companyName" : "北京黑马程序",
  "createDate" : "2022-09-25 09:36:11",
  "description" : "《Spring编程思想》是2007年6月1日机械工业出版社出版的图书，作者是埃克尔，译者是陈昊鹏。主要内容本书赢得了全球程序员的广泛赞誉，即使是最晦涩的概念，在Bruce Eckel的文字亲和力和小而直接的编程示例面前也会化解于无形。从Java的基础语法到最高级特性（深入的面向对象概念、多线程、自动项目构建、单元测试和调试等），本书都能逐步指导你轻松掌握。从本书获得的各项大奖以及来自世界各地的读者评论中，不难看出这是一本经典之作",
  "grade" : "204001",
  "id" : 102,
  "mt" : "1-3",
  "mtName" : "编程开发",
  "name" : "Spring编程思想",
  "originalPrice" : 200.0,
  "pic" : "/mediafiles/2022/09/20/1d0f0e6ed8a0c4a89bfd304b84599d9c.png",
  "price" : 100.0,
  "remark" : "没有备注",
  "st" : "1-3-2",
  "stName" : "Java语言",
  "status" : "203002",
  "tags" : "没有标签",
  "teachmode" : "200002",
  "validDays" : 222
}



### 搜索课程
GET {{search_host}}/search/course/list?pageNo=1&keywords=java
Content-Type: application/json
```



#### 5.5.4 搜索

##### 5.5.4.1 需求分析

索引信息维护完成下一步定义搜索接口搜索课程信息，首先需要搞清楚搜索功能的需求。

进入搜索界面，如下图：

![image-20230328160821103](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/f21649cfaf8bfd07995733a0df94b760.png)





根据搜索界面可知需求如下：

1、根据一级分类、二级分类搜索课程信息。

2、根据关键字搜索课程信息，搜索方式为全文检索，关键字需要匹配课程的名称、 

课程内容。

3、根据难度等级搜索课程。

4、搜索结点分页显示。

技术点：

1、整体采用布尔查询。

2、根据关键字搜索，采用MultiMatchQuery，搜索name、description字段。

3、根据分类、课程等级搜索采用过滤器实现。

4、分页查询。

5、高亮显示。



==为什么课程分类、课程等级等查询使用过滤器方式？==

使用关键字查询需要计算相关度得分，根据课程分类、课程等级去查询不需要计算相关度得分，使用过滤器实现根据课程分类、课程等级查询的过程不会计算相关度得分，效率更高。

##### 5.5.4.2 接口定义

定义搜索所需要的dto

```java
/**
 * @description 搜索课程参数dtl
 * @author Mr.M
 * @date 2022/9/24 22:36
 * @version 1.0
 */
 @Data
 @ToString
public class SearchCourseParamDto {

private String keywords;

  //大分类
  private String mt;

  //小分类
  private String st;
  //难度等级
  private String grade;


}

```

为了适应后期的扩展，定义搜索结果类，让它继承PageResult

```java
@Data
@ToString
public class SearchPageResultDto<T> extends PageResult {

    public SearchPageResultDto(List<T> items, long counts, long page, long pageSize) {
        super(items, counts, page, pageSize);
    }

}

```

接口定义如下

```java
/**
 * @description 课程搜索接口
 * @author Mr.M
 * @date 2022/9/24 22:31
 * @version 1.0
 */
@Api(value = "课程搜索接口",tags = "课程搜索接口")
 @RestController
 @RequestMapping("/course")
public class CourseSearchController {



 @ApiOperation("课程搜索列表")
  @GetMapping("/list")
 public PageResult<CourseIndex> list(PageParams pageParams, SearchCourseParamDto searchCourseParamDto){

    
   
  }
}

```









##### 5.5.4.3 基本功能实现

```java
/**
 * @description 课程搜索service
 * @author Mr.M
 * @date 2022/9/24 22:40
 * @version 1.0
 */
public interface CourseSearchService {


    /**
     * @description 搜索课程列表
     * @param pageParams 分页参数
     * @param searchCourseParamDto 搜索条件
     * @return com.xuecheng.base.model.PageResult<com.xuecheng.search.po.CourseIndex> 课程列表
     * @author Mr.M
     * @date 2022/9/24 22:45
    */
    SearchPageResultDto<CourseIndex> queryCoursePubIndex(PageParams pageParams, SearchCourseParamDto searchCourseParamDto);

 }

```

实现类如下

```java
/**
 * @author Mr.M
 * @version 1.0
 * @description 课程搜索service实现类
 * @date 2022/9/24 22:48
 */
@Slf4j
@Service
public class CourseSearchServiceImpl implements CourseSearchService {
    @Value("${elasticsearch.course.index}")
    private String courseIndexStore;
    @Value("${elasticsearch.course.source_fields}")
    private String sourceFields;

    @Autowired
    RestHighLevelClient client;

    @Override
    public SearchPageResultDto<CourseIndex> queryCoursePubIndex(PageParams pageParams, SearchCourseParamDto courseSearchParam) {

        //设置索引
        SearchRequest searchRequest = new SearchRequest(courseIndexStore);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //source源字段过虑
        String[] sourceFieldsArray = sourceFields.split(",");
        searchSourceBuilder.fetchSource(sourceFieldsArray, new String[]{});
        
        //分页
        Long pageNo = pageParams.getPageNo();
        Long pageSize = pageParams.getPageSize();
        int start = (int) ((pageNo-1)*pageSize);
        searchSourceBuilder.from(start);
        searchSourceBuilder.size(Math.toIntExact(pageSize));
       //布尔查询
        searchSourceBuilder.query(boolQueryBuilder);
        
        //请求搜索
        searchRequest.source(searchSourceBuilder);
       
        
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) { e.printStackTrace();
            log.error("课程搜索异常：{}",e.getMessage());
            return new SearchPageResultDto<CourseIndex>(new ArrayList(),0,0,0);
        }

        //结果集处理
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        //记录总数
        TotalHits totalHits = hits.getTotalHits();
        //数据列表
        List<CourseIndex> list = new ArrayList<>();

        for (SearchHit hit : searchHits) {

            String sourceAsString = hit.getSourceAsString();
            CourseIndex courseIndex = JSON.parseObject(sourceAsString, CourseIndex.class);
            list.add(courseIndex);

        }
        SearchPageResultDto<CourseIndex> pageResult = new SearchPageResultDto<>(list, totalHits.value,pageNo,pageSize);

        

        return pageResult;
    }


}



```



##### 5.5.4.4 基本功能测试

当输入查询条件时会查询全部课程信息并支持分页查询。

1、准备测试

启动nginx、网关、搜索服务。

使用kibana通过rest api向索引库添加课程信息，或通过httpclient添加课程信息，至少添加两条信息。

2、进入搜索界面

默认查询出刚才添加的课程信息。

3、修改分页参数测试分页

打开course/ search.html页面 ，找到如下图所示位置：

![image-20230328161224677](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/f3ade035d414efa200c121fa27c74083.png)



修改pageSize为1,即一页显示一条记录。

刷新搜索界面，每页显示一条记录，如下图

![image-20230328161241778](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/a77d83163ebf2a89e7d09197dde3fae7.png)





##### 5.5.4.5 根据条件搜索

下边实现根据关键、一级分类、二级分类、难度等级搜索。



```java
@Override
public SearchPageResultDto<CourseIndex>queryCoursePubIndex(PageParams pageParams, SearchCourseParamDto courseSearchParam) {

    //设置索引
    SearchRequest searchRequest = new SearchRequest(courseIndexStore);

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    //source源字段过虑
    String[] sourceFieldsArray = sourceFields.split(",");
    searchSourceBuilder.fetchSource(sourceFieldsArray, new String[]{});
    if(courseSearchParam==null){
        courseSearchParam = new SearchCourseParamDto();
    }
    //关键字
    if(StringUtils.isNotEmpty(courseSearchParam.getKeywords())){
        //匹配关键字
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(courseSearchParam.getKeywords(), "name", "description");
        //设置匹配占比
        multiMatchQueryBuilder.minimumShouldMatch("70%");
        //提升另个字段的Boost值
        multiMatchQueryBuilder.field("name",10);
        boolQueryBuilder.must(multiMatchQueryBuilder);
    }
    //过虑
    if(StringUtils.isNotEmpty(courseSearchParam.getMt())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("mtName",courseSearchParam.getMt()));
    }
    if(StringUtils.isNotEmpty(courseSearchParam.getSt())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("stName",courseSearchParam.getSt()));
    }
    if(StringUtils.isNotEmpty(courseSearchParam.getGrade())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("grade",courseSearchParam.getGrade()));
         }
    //分页
    Long pageNo = pageParams.getPageNo();
    Long pageSize = pageParams.getPageSize();
    int start = (int) ((pageNo-1)*pageSize);
    searchSourceBuilder.from(start);
    searchSourceBuilder.size(Math.toIntExact(pageSize));
    //布尔查询
    searchSourceBuilder.query(boolQueryBuilder);
    
    //请求搜索
    searchRequest.source(searchSourceBuilder);
   
    SearchResponse searchResponse = null;
    try {
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    } catch (IOException e) {
        e.printStackTrace();
        log.error("课程搜索异常：{}",e.getMessage());
        return new SearchPageResultDto<CourseIndex>(new ArrayList(),0,0,0);
    }

    //结果集处理
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
    //记录总数
    TotalHits totalHits = hits.getTotalHits();
    //数据列表
    List<CourseIndex> list = new ArrayList<>();

    for (SearchHit hit : searchHits) {

        String sourceAsString = hit.getSourceAsString();
        CourseIndex courseIndex = JSON.parseObject(sourceAsString, CourseIndex.class);

        
        list.add(courseIndex);

    }
    SearchPageResultDto<CourseIndex> pageResult = new SearchPageResultDto<>(list, totalHits.value,pageNo,pageSize);

    return pageResult;
}

```



##### 5.5.5.6 条件搜索测试

进入搜索界面，输入关键字进行测试。

一级分类、二级分类在下边的聚合搜索中测试。

##### 5.5.5.7 聚合搜索

搜索界面上显示的一级分类、二级分类来源于搜索结果，使用聚合搜索实现找到搜索结果中的一级分类、二级分类。

1、首先在搜索结构DTO中添加一级分类、二级分类列表

```java
package com.xuecheng.search.dto;

import com.xuecheng.base.model.PageResult;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/9/25 17:51
 */
@Data
@ToString
public class SearchPageResultDto<T> extends PageResult {

    //大分类列表
    List<String> mtList;
    //小分类列表
    List<String> stList;
    public SearchPageResultDto(List<T> items, long counts, long page, long pageSize) {
        super(items, counts, page, pageSize);
    }

}


```

2、搜索方法如下：

```java
@Override
public SearchPageResultDto<CourseIndex> queryCoursePubIndex(PageParams pageParams, SearchCourseParamDto courseSearchParam) {

    //设置索引
    SearchRequest searchRequest = new SearchRequest(courseIndexStore);

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    //source源字段过虑
    String[] sourceFieldsArray = sourceFields.split(",");
    searchSourceBuilder.fetchSource(sourceFieldsArray, new String[]{});
    if(courseSearchParam==null){
        courseSearchParam = new SearchCourseParamDto();
    }
    //关键字
    if(StringUtils.isNotEmpty(courseSearchParam.getKeywords())){
        //匹配关键字
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(courseSearchParam.getKeywords(), "name", "description");
        //设置匹配占比
        multiMatchQueryBuilder.minimumShouldMatch("70%");
        //提升另个字段的Boost值
        multiMatchQueryBuilder.field("name",10);
        boolQueryBuilder.must(multiMatchQueryBuilder);
    }
    //过虑
    if(StringUtils.isNotEmpty(courseSearchParam.getMt())){boolQueryBuilder.filter(QueryBuilders.termQuery("mtName",courseSearchParam.getMt()));
    }
    if(StringUtils.isNotEmpty(courseSearchParam.getSt())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("stName",courseSearchParam.getSt()));
    }
    if(StringUtils.isNotEmpty(courseSearchParam.getGrade())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("grade",courseSearchParam.getGrade()));
    }
    //分页
    Long pageNo = pageParams.getPageNo();
    Long pageSize = pageParams.getPageSize();
    int start = (int) ((pageNo-1)*pageSize);
    searchSourceBuilder.from(start);
    searchSourceBuilder.size(Math.toIntExact(pageSize));
    //布尔查询
    searchSourceBuilder.query(boolQueryBuilder);
   
   
    //请求搜索
    searchRequest.source(searchSourceBuilder);
    //聚合设置
    buildAggregation(searchRequest);
    SearchResponse searchResponse = null;
    try {
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    } catch (IOException e) {
        e.printStackTrace();
        log.error("课程搜索异常：{}",e.getMessage());
        return new SearchPageResultDto<CourseIndex>(new ArrayList(),0,0,0);
    }

    //结果集处理
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
    //记录总数
    TotalHits totalHits = hits.getTotalHits();
    //数据列表
    List<CourseIndex> list = new ArrayList<>();

    for (SearchHit hit : searchHits) {

        String sourceAsString = hit.getSourceAsString();
        CourseIndex courseIndex = JSON.parseObject(sourceAsString, CourseIndex.class);

        

        list.add(courseIndex);

    }
    SearchPageResultDto<CourseIndex> pageResult = new SearchPageResultDto<>(list, totalHits.value,pageNo,pageSize);

    //获取聚合结果
    List<String> mtList= getAggregation(searchResponse.getAggregations(), "mtAgg");
    List<String> stList = getAggregation(searchResponse.getAggregations(), "stAgg");

    pageResult.setMtList(mtList);
    pageResult.setStList(stList);

    return pageResult;
}



```



##### 5.5.5.8 聚合搜索测试

进入搜索界面，观察搜索请求的响应内容中是否存在mtList和stList.

观察页面一级分类、二级分类是否有分类信息。

注意：当选中一个一级分类时才会显示二级分类。

##### 5.5.5.9 高亮设置

最后实现关键词在课程名称中高亮显示。

```java
@Override
public SearchPageResultDto<CourseIndex> queryCoursePubIndex(PageParams pageParams, SearchCourseParamDto courseSearchParam) {
    //设置索引
    SearchRequest searchRequest = new SearchRequest(courseIndexStore);

    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    //source源字段过虑
    String[] sourceFieldsArray = sourceFields.split(",");
    searchSourceBuilder.fetchSource(sourceFieldsArray, new String[]{});
    if(courseSearchParam==null){
        courseSearchParam = new SearchCourseParamDto();
    }
    //关键字
    if(StringUtils.isNotEmpty(courseSearchParam.getKeywords())){
        //匹配关键字
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(courseSearchParam.getKeywords(), "name", "description");
        //设置匹配占比
        multiMatchQueryBuilder.minimumShouldMatch("70%");
        //提升另个字段的Boost值
        multiMatchQueryBuilder.field("name",10);
        boolQueryBuilder.must(multiMatchQueryBuilder);
    }
    //过虑
    if(StringUtils.isNotEmpty(courseSearchParam.getMt())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("mtName",courseSearchParam.getMt()));
    }
    if(StringUtils.isNotEmpty(courseSearchParam.getSt())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("stName",courseSearchParam.getSt()));
    }
    if(StringUtils.isNotEmpty(courseSearchParam.getGrade())){
        boolQueryBuilder.filter(QueryBuilders.termQuery("grade",courseSearchParam.getGrade()));
    }
    //分页
    Long pageNo = pageParams.getPageNo();
    Long pageSize = pageParams.getPageSize();
    int start = (int) ((pageNo-1)*pageSize);
    searchSourceBuilder.from(start);
    searchSourceBuilder.size(Math.toIntExact(pageSize));
    //布尔查询
    searchSourceBuilder.query(boolQueryBuilder);
    //高亮设置
    HighlightBuilder highlightBuilder = new HighlightBuilder();
    highlightBuilder.preTags("<font class='eslight'>");
    highlightBuilder.postTags("</font>");
    //设置高亮字段
    highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
    searchSourceBuilder.highlighter(highlightBuilder);
    //请求搜索
    searchRequest.source(searchSourceBuilder);
    //聚合设置
    buildAggregation(searchRequest);
    SearchResponse searchResponse = null;
    try {
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
    } catch (IOException e) {
        e.printStackTrace();
        log.error("课程搜索异常：{}",e.getMessage());
        return new SearchPageResultDto<CourseIndex>(new ArrayList(),0,0,0);
    }

    //结果集处理
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
    //记录总数
    TotalHits totalHits = hits.getTotalHits();
    //数据列表
    List<CourseIndex> list = new ArrayList<>();

    for (SearchHit hit : searchHits) {

        String sourceAsString = hit.getSourceAsString();
        CourseIndex courseIndex = JSON.parseObject(sourceAsString, CourseIndex.class);

 //取出source
        Map<String, Object> sourceAsMap = hit.getSourceAsMap();

        //课程id
        Long id = courseIndex.getId();
        //取出名称
        String name = courseIndex.getName();
        //取出高亮字段内容
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        if(highlightFields!=null){
            HighlightField nameField = highlightFields.get("name");
            if(nameField!=null){
                Text[] fragments = nameField.getFragments();
                StringBuffer stringBuffer = new StringBuffer();
                for (Text str : fragments) {
                    stringBuffer.append(str.string());
                }
                name = stringBuffer.toString();

            }
        }
        courseIndex.setId(id);
        courseIndex.setName(name);

        list.add(courseIndex);

    }
    SearchPageResultDto<CourseIndex> pageResult = new SearchPageResultDto<>(list, totalHits.value,pageNo,pageSize);

    //获取聚合结果
    List<String> mtList= getAggregation(searchResponse.getAggregations(), "mtAgg");
    List<String> stList = getAggregation(searchResponse.getAggregations(), "stAgg");

    pageResult.setMtList(mtList);
    pageResult.setStList(stList);

    return pageResult;
}

```





##### 5.5.5.10 高亮测试

输入关键字，观察搜索结果，标题中是否对关键字信息进行高亮显示。

#### 5.5.5 课程信息索引同步

##### 5.5.5.1 技术方案

通过向索引中添加课程信息最终实现了课程的搜索，我们发现课程信息是先保存在关系数据库中，而后再写入索引，这个过程是将关系数据中的数据同步到elasticsearch索引中的过程，可以简单成为索引同步。

通常项目中使用elasticsearch需要完成索引同步，索引同步的方法很多：

1、针对实时性非常高的场景需要满足数据的及时同步，可以同步调用，或使用Canal去实现。

1）同步调用即在向MySQL写数据后远程调用搜索服务的接口写入索引，此方法简单但是耦合代码太高。

2）可以使用一个中间的软件canal解决耦合性的问题，但存在学习与维护成本。

canal主要用途是基于 MySQL 数据库增量日志解析，并能提供增量数据订阅和消费，实现将MySQL的数据同步到消息队列、Elasticsearch、其它数据库等，应用场景十分丰富。 

![image-20230328161642148](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/fb2194f938cf92335777c1fbf0edf446.png)

github地址：https://github.com/alibaba/canal 

版本下载地址：https://github.com/alibaba/canal/releases

文档地址：https://github.com/alibaba/canal/wiki/Docker-QuickStart

 

Canal基于mysql的binlog技术实现数据同步，什么是binlog，它是一个文件，二进制格式，记录了对数据库更新的SQL语句，向数据库写数据的同时向binlog文件里记录对应的sql语句。当数据库服务器发生了故障就可以使用binlog文件对数据库进行恢复。

所以，使用canal是需要开启mysql的binlog写入功能，Canal工作原理如下：

![image-20230328161704085](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/e924e7691f3480791e44bc71c533e987.png)



1、canal 模拟 MySQL slave 的交互协议，伪装自己为 MySQL slave ，向 MySQL master 发送dump 

协议 

2、MySQL master 收到 dump 请求，开始推送 binary log 给 slave (即 canal ) 

3、canal 解析 binary log 对象(原始为 byte 流)

详细使用Canal进行索引同步的步骤参考：Canal实现索引同步.pdf

 

2、当索引同步的实时性要求不高时可用的技术比较多，比如：MQ、Logstash、任务调度等。

MQ：向mysql写数据的时候向mq写入消息，搜索服务监听MQ，收到消息后写入索引。使用MQ的优势是代码解耦，但是需要处理消息可靠性的问题有一定的技术成本，做到消息可靠性需要做到生产者投递成功、消息持久化以及消费者消费成功三个方面，另外还要做好消息幂等性问题。

Logstash： 开源实时日志分析平台 ELK包括Elasticsearch、Kibana、Logstash，Logstash负责收集、解析和转换日志信息，可以实现MySQL与Elasticsearch之间的数据同步。也可以实现解耦合并且是官方推荐，但需要增加学习与维护成本。

任务调度：向mysql写数据的时候记录修改记录，开启一个定时任务根据修改记录将数据同步到Elasticsearch。

 

根据本项目的需求，课程发布后信息同步的实时性要求不高，从提交审核到发布成功一般两个工作日完成。综合比较以上技术方案本项目的索引同步技术使用任务调度的方法。

如下图：

![image-20230328161727255](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/e85f9eca44261a41ddd5953535d7e348.png)



1、课程发布向消息表插入记录。

2、由任务调度程序通过消息处理SDK对消息记录进行处理。

3、向elasticsearch索引中保存课程信息。

如何向向elasticsearch索引中保存课程信息？

执行流程如下：

由内容管理服务远程调用搜索服务添加课程信息索引，搜索服务再请求elasticsearch向课程索引中添加文档。



##### 5.5.5.2 课程索引任务开发

1、拷贝CourseIndex 模型类到内容管理model 工程的dto包下。

2、在内容管理服务中添加FeignClient

```java
/**
 * @description 搜索服务远程接口
 * @author Mr.M
 * @date 2022/9/20 20:29
 * @version 1.0
 */
@FeignClient(value = "search",fallbackFactory = SearchServiceClientFallbackFactory.class)
public interface SearchServiceClient {

 @PostMapping("/search/index/course")
 public Boolean add(@RequestBody CourseIndex courseIndex);
}

```

定义SearchServiceClientFallbackFactory ：

```java
@Slf4j
@Component
public class SearchServiceClientFallbackFactory implements FallbackFactory<SearchServiceClient> {
    @Override
    public SearchServiceClient create(Throwable throwable) {

        return new SearchServiceClient() {

            @Override
            public Boolean add(CourseIndex courseIndex) {
                throwable.printStackTrace();
                log.debug("调用搜索发生熔断走降级方法,熔断异常:", throwable.getMessage());

                return false;
            }
        };
    }
}

```

```java
package com.xuecheng.content.service.jobhandler;

import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.feignclient.search.CourseIndex;
import com.xuecheng.content.feignclient.search.SearchCourseIndexClient;
import com.xuecheng.content.model.po.CoursePublish;
import com.xuecheng.content.service.CoursePublishService;
import com.xuecheng.messagesdk.model.po.MqMessage;
import com.xuecheng.messagesdk.service.MessageProcessAbstract;
import com.xuecheng.messagesdk.service.MqMessageService;
import com.xuecheng.messagesdk.service.impl.MqMessageServiceImpl;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author woldier
 * @version 1.0
 * @description 课程发布任务实现类
 * @date 2023/3/27 10:27
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class CoursePublishTask extends MessageProcessAbstract {

    /**
     * @return void
     * @description 任务调度入口
     * @author: woldier
     * @date: 2023/3/27 11:09
     */

    private final CoursePublishService coursePublishService;
    private final SearchCourseIndexClient searchCourseIndexClient;

    @XxlJob("CoursePublishJobHandler") //任务名
    public void coursePublishJobHandler() throws Exception {
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        log.debug("shardIndex=" + shardIndex + ",shardTotal=" + shardTotal);
        //参数:分片序号、分片总数、消息类型、一次最多取到的任务数量、一次任务调度执行的超时时间
        process(shardIndex, shardTotal, "course_publish", 30, 60);
    }


    /**
     * @param mqMessage
     * @return boolean
     * @description 任务执行
     * @author: woldier
     * @date: 2023/3/27 10:28
     */
    @Override
    public boolean execute(MqMessage mqMessage) {
        //消息id
        Long id = mqMessage.getId();
        //取出对应的course 这里我们约定课程id存在于message表的 businessKey1 字段
        Integer courseId = Integer.valueOf(mqMessage.getBusinessKey1());


        try {
            if (!Boolean.parseBoolean(mqMessage.getStageState1()))
                //执行阶段1 ,静态化页面
                generateCourseHtml(mqMessage, courseId);
            //执行阶段2 , 写入 elasticsearch
            if (!Boolean.parseBoolean(mqMessage.getStageState2()))
                saveCourseIndex(mqMessage, courseId);
            if (!Boolean.parseBoolean(mqMessage.getStageState3()))
                //执行阶段3 , 写入redis
                saveCourseCache(mqMessage, courseId);
        } catch (XueChengPlusException e) {
            log.error("执行任务出错{}", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //生成课程静态化页面并上传至文件系统
    public void generateCourseHtml(MqMessage mqMessage, long courseId) throws XueChengPlusException {

        log.debug("开始进行课程静态化,课程id:{}", courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理
        int stageOne = mqMessageService.getStageOne(id);
        if (stageOne > 0) {
            log.debug("课程静态化已处理直接返回，课程id:{}", courseId);
            return;
        }
        //生成静态页面

        File file = coursePublishService.generateCourseHtml(courseId);
        coursePublishService.uploadCourseHtml(courseId, file);
        file.delete();


        //保存第一阶段状态
        mqMessageService.completedStageOne(id);

    }

    //将课程信息缓存至redis
    public void saveCourseCache(MqMessage mqMessage, long courseId) {
        log.debug("将课程信息缓存至redis,课程id:{}", courseId);
        // TODO 将课程信息缓存至redis
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    //保存课程索引信息
    public void saveCourseIndex(MqMessage mqMessage, long courseId) throws XueChengPlusException {
        log.debug("保存课程索引信息,课程id:{}", courseId);

        CoursePublish coursePublish = coursePublishService.getById(courseId);
        CourseIndex courseIndex = new CourseIndex();
        BeanUtils.copyProperties(coursePublish, courseIndex);


        if (!searchCourseIndexClient.add(courseIndex)) {
            log.error("添加索引失败");
            XueChengPlusException.cast("添加索引失败");
        }
        //保存第二阶段状态
        MqMessageService mqMessageService = this.getMqMessageService();
        mqMessageService.completedStageTwo(courseId);
    }


}





```



##### 5.5.5.3 测试

测试流程如下：

1、启动elasticsearch、kibana。

2、启动网关、内容管理、搜索服务、nginx。

3、启动xxl-job调度中心。

4、在任务调度中心开始课程发布任务。

5、发布一门课程，页面提示操作成功，查看发布课程任务是否写到任务表。

6、经过任务调度将课程信息写入索引。

7、通过门户进入搜索页面，查看课程信息是否展示。

![image-20230328161944441](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/03/2724926d0e3ee625453c1cc85c0fdbee.png)







## 6. 认证授权

[章节六](./chapter6.md)





```
### XXX.XXX xxxxxx模块

#### XXX.XXX.1 需求分析

##### XXX.XXX.1.1 业务流程

##### XXX.XXX.1.2 数据模型

#### XXX.XXX.2 接口定义



#### XXX.XXX.3 接口开发

##### XXX.XXX.3.1 DAO开发

##### XXX.XXX.3.2 Service开发

##### XXX.XXX.3.3 接口代码完善

#### XXX.XXX.4 接口测试
```





