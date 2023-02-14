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

### 3.3 接口开发

#### 3.3.1 课程查询

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