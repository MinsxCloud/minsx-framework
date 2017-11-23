# Minsx-Framework

#### Minsx (米斯艾克斯)
+ Min : 轻量 
+ S : Spring框架 
+ X : 未知、无限、完美、千言万语 (参考自百度百科)
#### 是一款基于Spring的轻量级云平台框架，她可以让你以较短的时间开发出企业级云平台应用

### 项目说明
+ 软件名称：Minsx-Framework
+ 版本号：1.0.0
+ 开发者：www.minsx.com
+ 语言：Java
+ 功能：提供一整套企业云平台解决方案,包括以下四个基础端：
	+ 用户登录认证服务端  
	+ 用户中心及核心业务服务端
	+ 管理WEB前端
	+ 用户WEB前端
+ 优点：高灵活/高可配置/微服务/模块化/快速二次开发
+ 缺点：仅适用于通用行业(不适用于商城,医疗等特殊行业)
+ 开源协议：Apache License Version 2.0 http://www.apache.org/licenses/
				
### 技术选型
+ Spring Boot 基础框架
+ Spring Security 权限认证框架
+ Spring Data JPA ORM框架
+ Spring Security Oauth2.0 单点登录及认证框架
+ Spring Aop 切面编程框架
+ Spring Cloud 云应用框架
+ Druid 数据源框架
+ Dubbo 分布式框架
+ Radis 缓存框架
+ Minsx CCS 自家通用云存储框架 [点此查看](https://github.com/MinsxFramework/minsx-ccs)

### 部署方面
+ 采用Nginx 主要用于解决代理问题及部分简单负载均衡问题
+ 采用Docker 主要用于解决环境隔离,单服务器多应用问题


### 架构及开发方面
+ 项目采用前后端分离/模块化设计
+ 提供单点登录服务端(请参照：minsx-authorization-server [点此查看](https://github.com/MinsxFramework/minsx-authorization-server))
+ 提供用户中心及核心业务服务端(请参照：minsx-framework)
+ 提供默认通用用户前端(请参照：minsx-user-ui)
+ 提供默认通用管理前端(请参照：minsx-management-ui)
+ 采用Restful-URL进行开发
+ 代码规范方面遵循最新阿里巴巴JAVA代码规范

### 项目截图 (V1.0.0版本)
![登录](https://raw.githubusercontent.com/MinsxFramework/minsx-framework/master/docs/image/login.png "登录")
![权限管理](https://raw.githubusercontent.com/MinsxFramework/minsx-framework/master/docs/image/auth.png "权限管理")
![菜单管理](https://raw.githubusercontent.com/MinsxFramework/minsx-framework/master/docs/image/menu.png "菜单管理")
![请求监控](https://raw.githubusercontent.com/MinsxFramework/minsx-framework/master/docs/image/request.png "请求监控")

### 项目截图 (V2.0.0版本)
正在加紧Coding...(预计在12月底开放,敬请期待)
