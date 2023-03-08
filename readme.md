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







#### 4.2.3 接口开发

##### 4.2.3.1 DAO开发

##### 4.2.3.2 Service开发

##### 4.2.3.3 接口代码完善

#### 4.2.4 接口测试



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



