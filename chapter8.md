> 8. 项目部署

# 1.什么是DevOps

一个软件的生命周期包括：需求分析阶、设计、开发、测试、上线、维护、升级、废弃。

通过示例说明如下：

1、产品人员进行需求分析

2、设计人员进行软件架构设计和模块设计。

3、每个模块的开发人员并行开发，设计接口、进行编码，并进行单元测试

4、开发完毕，将代码集成部署到测试服务器，测试人员进行测试。

5、测试人员发现bug，提交bug、开发人员修改bug

6、bug修改完毕再次集成、测试。

7、测试完毕，项目上线。

8、运维人员进行安装部署、培训。

9、用户提出问题，返回给运维人员。

10、运维人员反馈给开发人员，开发人员进行问题处理。

11、再次提交测试。

12、测试完毕再次部署升级。

....

最后软件下线。

所以，在整体生命周期中比较核心的两个阶段是：开发阶段、维护阶段，开发阶段的成果是软件开发完成并成功上线，运维阶段则负责对软件进行维护和升级，而运维阶段通常在一个软件 的生命周期中占比最多。

提高开发阶段、运维阶段的工作效率是企业在进行软件项目管理的重点。

因此，专家提出了DevOps，DevOps是什么呢？

下边是摘自百度百科的定义：

DevOps（Development和Operations的组合词）是一组过程、方法与系统的统称，用于促进开发（[应用程序](https://baike.baidu.com/item/应用程序/5985445?fromModule=lemma_inlink)/软件工程）、技术运营和质量保障（[QA](https://baike.baidu.com/item/QA/476016?fromModule=lemma_inlink)）部门之间的沟通、协作与整合。

它是一种重视“[软件开发](https://baike.baidu.com/item/软件开发/3448966?fromModule=lemma_inlink)人员（Dev）”和“[IT运维](https://baike.baidu.com/item/IT运维/5727814?fromModule=lemma_inlink)技术人员（Ops）”之间沟通合作的文化、运动或惯例。透过自动化“[软件交付](https://baike.baidu.com/item/软件交付/638479?fromModule=lemma_inlink)”和“架构变更”的流程，来使得构建、测试、发布软件能够更加地快捷、频繁和可靠。

它的出现是由于软件行业日益清晰地认识到：为了按时交付[软件产品](https://baike.baidu.com/item/软件产品/6800028?fromModule=lemma_inlink)和服务，开发和运维工作必须紧密合作。

![image-20230417214140843](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/07dbe2c8154c43cdf4bd8bffc6fae047.png)

DevOps是一个工具吗？

DevOps是一个工作职位吗？

都不是。

DevOps是一种思想理念，它涵盖开发、测试、运维的整个过程。DevOps追求的目标是提高软件开发、测试、运维、运营等各部门的沟通与协作质量，DevOps强调软件开发人员与软件测试、软件运维、质量保障（QA）部门之间有效的沟通与协作，强调通过自动化的方法去管理软件变更、软件集成，使软件从构建到测试、发布更加快捷、可靠，最终按时交付软件。

![image-20230417214153708](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/6b01146b356f85823bdae1a650f464e8.png)

# 2. 什么是CI/CD

如何来落地实现DevOps呢？

DevOps兴起于2009年，近年来由于云计算、互联网的发展，促进了DevOps的基础设施及工具链的发展，涌现了一大批优秀的工具，这些工具包括开发、测试、运维的各各领域，例如：GitHub、Docker、Jenkins、Hudson、K8S、Ant/Maven/Gradle、Selenium、QUnit、JMeter等。下图是DevOps相关的工具集：

![image-20230417214233484](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/4131887af39110ec1ccc343e7c739c36.png)

好的工具有利于DevOps的实施，但并不代表实施DevOps就一定需要去引入一堆工具。

问题的关键：如何解决问题，而不是具体应用工具。

CI/CD 是近年来企业有效实施DevOps的具体方案。

CI/CD 包含了一个 CI 和两个 CD，CI全称 Continuous Integration，表示持续集成，CD包含 Continuous Delivery和 Continuous Deployment，分别是持续交付和持续部署，三者具有前后依赖关系。

CI 持续集成：

持续集成倡导团队成员需要频繁的集成他们的工作，将开发分支合并到主分支，每次集成都通过自动化构建（包括编译、构建、自动化测试）来验证，从而尽快地发现集成中的错误，让产品可以快速迭代，同时还能保持高质量。

![image-20230417214245447](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/96d540d92bbff3100b27862da6618f1a.png)

CD持续交付:

持续交付将集成后的代码部署到类生产环境(预发布)，除了交付到类生产环境之外，还会执行一些集成测试、API测试。持续交付强调的是“交付”，交付给测试、产品验收，不管怎么更新，软件是随时随地可以交付的。

![image-20230417214256954](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/15004026867afa6f70ffed0b0c5b4600.png)

CD持续部署：

在持续交付的基础上由开发人员或运维人员自助式的定期向生产环境部署稳定的构建版本，持续部署的目标是代码在任何时刻都是可部署的，并可自动进入到生产环境。

![image-20230417214307038](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/832418912dfd93a3acf2f4b9c64af324.png)

# 3. DevOps 实战

## 3.1 技术方案

下图是比较流行的一种CI/CD的技术方案：

![image-20230417214352154](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/4ca15266dba2d52126df64f529a51dfc.png)

下边我们参考该技术方案将学成在线项目使用Docker进行部署。

本次项目部署实战旨在理解CI/CD的流程，考虑Kubernates的复杂性课堂上我们用Jenkins代替Kubernates完成容器部署。

## 3.2 准备环境

准备一台Centos7 虚拟机，安装Docker、jdk、maven，通过Docker容器安装jenkins、Docker私服软件，其它软件为学成在线项目所需要的，如下：

| name          | 版本               | 位置   |
| ------------- | ------------------ | ------ |
| Mysql         | 8.x                | docker |
| nacos         | 1.4.1              | docker |
| rabbitmq      | 3.8.34             | docker |
| redis         | 6.2.7              | docker |
| xxl-job-admin | 2.3.1              | docker |
| minio         | RELEASE.2022-09-07 | docker |
| elasticsearch | 7.12.1             | docker |
| kibana        | 7.12.1             | docker |
| gogs          | 0.13.0             | docker |
| nginx         | 1.12.2             | docker |

## 3.3 人工部署方式

如果不使用CI/CD则需要人工手动对工程进行测试、打包、部署。使用CI/CD后通过自动化的工具去完成。

下边先演示手动部署方式，之后采用工具进行部署。

### 3.3.1 项目打包

1、在父工程聚合各模块

首先在父工程添加models，聚合各各模块



```xml

<modules>
    <module>../xuecheng-plus-base</module>
    <module>../xuecheng-plus-checkcode</module>
    <module>../xuecheng-plus-gateway</module>
    <module>../xuecheng-plus-auth</module>
    <module>../xuecheng-plus-content</module>
    <module>../xuecheng-plus-learning</module>
    <module>../xuecheng-plus-media</module>
    <module>../xuecheng-plus-orders</module>
    <module>../xuecheng-plus-message-sdk</module>
    <module>../xuecheng-plus-search</module>
    <module>../xuecheng-plus-system</module>
</modules>

```

2、配置打包插件

使用springboot打包插件进行打包，在需要打可执行包的工程中配置spring-boot-maven-plugin插件否则报 “jar中没有主清单属性” 。

注意：在要打可执行jar包的工程中配置该插件。

```xml
<build>
    <finalName>${project.artifactId}-${project.version}</finalName>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>${spring-boot.version}</version>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>

```

说明：

配置了springboot打包插件即可在maven窗口显示如下：

![image-20230417214840546](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/4c41f56a8585d5cb83044d3fc62027dd.png)

功能说明：

build-info：生成项目的构建信息文件 build-info.properties

repackage：这个是默认 goal，在 mvn package 执行之后，这个命令再次打包生成可执行的 jar，同时将 mvn package 生成的 jar 重命名为 *.origin

run：这个可以用来运行 Spring Boot 应用

start：这个在 mvn integration-test 阶段，进行 Spring Boot 应用生命周期的管理

stop：这个在 mvn integration-test 阶段，进行 Spring Boot 应用生命周期的管理

 

在父工程执行：`clean install -DskipTests -f pom.xml `对所有工程进行打包





![image-20230417220624808](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/674f73fd61d97231493a3a603249f513.png)



![image-20230417220842682](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/d1ae97e918a011d7acc68dd1c79de6aa.png)

打包完成可在本地通过java -jar 运行jar包观察是否可以正常运行。

![image-20230417222304379](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/6711605de681fb61ba443bde01c29713.png)

`要从这里点启动 而不是package哪里`

下边测试验证码服务：

进入验证码服务的target目录，cmd运行：

`java -Dfile.encoding=utf-8 -jar xuecheng-plus-checkcode-0.0.1-SNAPSHOT.jar `

![image-20230417222511015](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/91460697876f4feff0c30a99010c6c37.png)

使用httpclient测试申请验证码：

```http
### 申请验证码
POST localhost:63075/checkcode/pic

```

### 3.3.2 部署到Linux

将打成的jar包拷贝到Linux，生成镜像，并创建容器。

1、编写Dockerfile文件

```dockerfile
FROM java:8u20
MAINTAINER docker_maven docker_maven@email.com
WORKDIR /ROOT
ADD xuecheng-plus-checkcode-0.0.1-SNAPSHOT.jar xuecheng-plus-checkcode.jar
CMD ["java", "-version"]
ENTRYPOINT ["java", "-Dfile.encoding=utf-8","-jar", "xuecheng-plus-checkcode.jar"]
EXPOSE 63075

```

2、创建镜像

```shell
docker build -t checkcode:1.0 .
```

创建成功，查询镜像:

![image-20230417222643939](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/bfd375ab5d8cbd46445571b667d14946.png)

3、创建容器

```shell
docker run --name xuecheng-plus-checkcode -p 63075:63075 -idt checkcode:1.0
```

再次httpclient 测试

## 3.4 自动化部署

### 3.4.1 实战流程

下边使用jenkins实现CI/CD的流程。

![image-20230417222807981](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/4ca15266dba2d52126df64f529a51dfc.png)

1、将代码 使用Git托管

2、在jenkins创建任务，从Git拉取代码。

3、拉取代码后进行自动构建：测试、打包、部署。

首先将代码打成镜像包上传到docker私服。

自动创建容器、启动容器。

4、当有代码push到git实现自动构建。



### 3.4.2 代码提交至Git

![image-20230417222840863](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/7f9dbb3f9883a299c37462a5c8cde5e2.png)

### 3.5.3 修改pom文件

在pom.xml添加docker-maven-plugin插件实现将springboot工程创建镜像， 此pom.xml添加docker-maven-plugin插件用于生成镜像。

分别修改system-api、content-api、media-api、gateway、auth、checkcode服务的pom.xml文件。

插件的坐标如下：

```xml
<dependency>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>1.2.2</version>
</dependency>

```

修改pom.xml文件，以xuecheng-plus-checkcode为例，如下：

```xml
<build>
    <finalName>${project.artifactId}-${project.version}</finalName>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>${spring-boot.version}</version>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>docker-maven-plugin</artifactId>
            <version>1.2.2</version>
            <configuration>
                <!--修改imageName节点的内容，改为私有仓库地址和端口，再加上镜像id和 TAG,我们要直接传到私服-->
                <!--配置最后生成的镜像名，docker images里的，我们这边取项目名:版本-->
                <!--<imageName>${project.artifactId}:${project.version}</imageName>-->
                <imageName>192.168.101.65:5000/${project.artifactId}:${project.version}</imageName>
                <!--也可以通过以下方式定义image的tag信息。 -->
                <!-- <imageTags>
                     <imageTag>${project.version}</imageTag>
                     &lt;!&ndash;build 时强制覆盖 tag，配合 imageTags 使用&ndash;&gt;
                     <forceTags>true</forceTags>
                     &lt;!&ndash;build 完成后，push 指定 tag 的镜像，配合 imageTags 使用&ndash;&gt;
                     <pushImageTag>true</pushImageTag>
                 </imageTags>-->
                <baseImage>java:8u20</baseImage>
                <maintainer>docker_maven docker_maven@email.com</maintainer>
                <workdir>/root</workdir>
                <cmd>["java", "-version"]</cmd>
                <!--来指明Dockerfile文件的所在目录，如果配置了dockerDirectory则忽略baseImage，maintainer等配置-->
                <!--<dockerDirectory>./</dockerDirectory>-->
                <!--2375是docker的远程端口，插件生成镜像时连接docker，这里需要指定docker远程端口-->
                <dockerHost>http://192.168.101.65:2375</dockerHost>
                <!--入口点，project.build.finalName就是project标签下的build标签下 的filename标签内容，testDocker-->
                <!--相当于启动容器后，会自动执行java -jar ...-->
                <entryPoint>["java", "-Dfile.encoding=utf-8","-jar", "/root/${project.build.finalName}.jar"]</entryPoint>
                <!--是否推送到docker私有仓库，旧版本插件要配置maven的settings文件。 -->
                <pushImage>true</pushImage>
                <registryUrl>192.168.101.65:5000</registryUrl>  <!-- 这里是复制 jar 包到 docker 容器指定目录配置 -->
                <resources>
                    <resource>
                        <targetPath>/root</targetPath>
                        <directory>${project.build.directory}</directory>
                        <!--把哪个文件上传到docker，相当于Dockerfile里的add app.jar /-->
                        <include>${project.build.finalName}.jar</include>
                    </resource>
                </resources>
            </configuration>
        </plugin>
    </plugins>
</build>

```

### 3.5.4 自动构建测试

找到jenkins_02任务，配置源码管理

![image-20230418091308301](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/7ab7404217d9957b8fc8e7e23fdf683a.png)

![](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/7ab7404217d9957b8fc8e7e23fdf683a.png)

![image-20230418091326140](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/dbd33bb227cc9f934fdd498cddf006ca.png)

 

通过控制台输出日志观察构建情况 

![image-20230418091337855](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/b35cee29b466a3ffcdc6fd64a90204ef.png)

如果控制台有报错，根据错误信息进行调试。

部署成功后，进入服务器查看docker容器是否启动成功

![image-20230418091350242](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/bf54886fdc2aba62535c47e86f6f9dd7.png)

### 3.5.5 部署前端门户

在虚拟机的docker中已经部署了nginx，修改nginx.conf的配置文件 

```
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;


events {
    worker_connections  1024;
}


http {
     server_names_hash_bucket_size 64;
     client_max_body_size 100M; # 设置客户端请求体最大值
     client_body_buffer_size 128k; # 设置请求体缓存区大小
    include       mime.types;
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';

    #access_log  logs/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;
   #文件服务
  upstream fileserver{
    server 192.168.101.65:9000 weight=10;
  } 
     #后台网关
  upstream gatewayserver{
    server 192.168.101.65:63010 weight=10;
  } 
    #gzip  on;

    server {
        listen       80;
        server_name  www.51xuecheng.cn localhost;
        #rewrite ^(.*) https://$server_name$1 permanent;
        #charset koi8-r;
        ssi on;
        ssi_silent_errors on;
        #access_log  logs/host.access.log  main;

        location / {
            alias   /etc/nginx/html/;
            index  index.html index.htm;
        }
        #api
        location /api/ {
                proxy_pass http://gatewayserver/;
        } 
        #静态资源
        location /static/img/ {  
                alias  /etc/nginx/html/img/;
        } 
        location /static/css/ {  
                alias   /etc/nginx/html/css/;
        } 
        location /static/js/ {  
                alias   /etc/nginx/html/js/;
        } 
        location /static/plugins/ {  
                alias   /etc/nginx/html/plugins/;
                add_header Access-Control-Allow-Origin http://ucenter.51xuecheng.cn;  
                add_header Access-Control-Allow-Credentials true;  
                add_header Access-Control-Allow-Methods GET;
        } 
        location /plugins/ {  
                alias   /etc/nginx/html/plugins/;
        } 
        location /course/preview/learning.html {
                alias /etc/nginx/html/course/learning.html;
        } 
        location /course/search.html {  
                root   /etc/nginx/html;
        } 
        location /course/learning.html {  
                root   /etc/nginx/html;
        } 
        location /course/ {  
                proxy_pass http://fileserver/mediafiles/course/;
        } 
        #openapi
        location /open/content/ {
                proxy_pass http://gatewayserver/content/open/;
        } 
        location /open/media/ {
                proxy_pass http://gatewayserver/media/open/;
        } 

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
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
 
   server {
        listen       80;
        server_name  teacher.51xuecheng.cn;
        #charset koi8-r;
        ssi on;
        ssi_silent_errors on;
        #access_log  logs/host.access.log  main;
        location / {
            alias   /etc/nginx/html/dist/;
            index  index.html index.htm;
        }
        #location / {
        #    proxy_pass   http://uidevserver;
        #}

        location /api/ {
                proxy_pass http://gatewayserver/;
        } 
        
        
   }

    server {
        listen       80;
        server_name  ucenter.51xuecheng.cn;
        #charset koi8-r;
        ssi on;
        ssi_silent_errors on;
        #access_log  logs/host.access.log  main;
        location / {
            alias   /etc/nginx/html/ucenter/;
            index  index.html index.htm;
        }
        location /include {
            proxy_pass   http://192.168.101.65;
        }
        location /img/ {
            proxy_pass   http://192.168.101.65/static/img/;
        }
        location /api/ {
                proxy_pass http://gatewayserver/;
        } 
   }
    # another virtual host using mix of IP-, name-, and port-based configuration
    #
    #server {
    #    listen       8000;
    #    listen       somename:8080;
    #    server_name  somename  alias  another.alias;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}


    # HTTPS server
    #
    #server {
    #    listen       443 ssl;
    #    server_name  localhost;

    #    ssl_certificate      cert.pem;
    #    ssl_certificate_key  cert.key;

    #    ssl_session_cache    shared:SSL:1m;
    #    ssl_session_timeout  5m;

    #    ssl_ciphers  HIGH:!aNULL:!MD5;
    #    ssl_prefer_server_ciphers  on;

    #    location / {
    #        root   html;
    #        index  index.html index.htm;
    #    }
    #}

}

```

将前端门户的静态页面拷贝到 /data/soft/nginx/xuecheng_portal_static

启动nginx容器：

```shell
docker start nginx
```

修改本机hosts文件：

修改为容器所在的宿主机ip



将本机的nginx服务停掉，访问www.51xuecheng.cn。

### 3.5.6 部署机构端前端

将机构端的前端工程打包，运行yarn build

打包成功在工程目录生成dist目录

将此目录的内容拷贝到虚拟机的/data/soft/nginx/xuecheng_portal_static/dist

### 3.5.6 配置触发器

当向gitee提交代码时进行自动构建

![image-20230418091633783](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/202751dfe3fc9600ff30264a78d2dbdf.png)

在gitee配置钩子

![image-20230418091804137](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/3fe78b03c9c054cb9bfd5fb840aaca42.png)

推送地址设置jenkins的接口：

http://192.168.101.65:8888/gogs-webhook/?job=jenkins_02

配置好可以测试一下：



![image-20230418091939202](https://woldier-pic-repo-1309997478.cos.ap-chengdu.myqcloud.com/woldier/2023/04/34a17d01cf53b5b6d03b4661c106e61a.png)

测试后观察jenkina是否重新构建任务。

 

提交代码测试：

修改代码提交到gitee，观察jenkins是否自动构建任务
