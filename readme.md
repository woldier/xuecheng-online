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
