# Minsx-framework
#### Minsx (米斯艾克斯、简称米斯)
+ Min : 轻量 
+ S : Spring框架 
+ X : 未知、无限、完美、千言万语 (参考自百度百科)
#### 是一款基于SpringBoot的轻量级云平台框架，她可以让你以较短的时间开发出企业级云平台应用

### 项目说明
- 项目名称：米斯云平台框架(Minsx家族的核心模块)
- 版本号：1.0.0
- 开发者：www.minsx.com
- 语言：Java
- 功能：提供基于SpringBoot的一系列功能框架，如安全框架、数据源框架、表单框架等以实现快速企业云平台应用的开发
- 开源协议：Apache License Version 2.0 http://www.apache.org/licenses/

### 项目文档

* Minsx-framework-common
    *  Minsx-framework-common-basic
    *  [Minsx-framework-common-excel](https://github.com/MinsxCloud/minsx-framework/wiki/minsx-framework-common-excel)
    *  [Minsx-framework-common-mail](https://github.com/MinsxCloud/minsx-framework/wiki/minsx-framework-common-mail)
    *  [Minsx-framework-common-shell](https://github.com/MinsxCloud/minsx-framework/wiki/minsx-framework-common-shell)
* [Minsx-framework-security](https://github.com/MinsxCloud/minsx-framework/wiki/minsx-framework-security)

### 简单示例
```java
import com.minsx.framework.common.shell.core.Shell;
import org.junit.Test;

public class ShellTest {

    @Test
    public void RunShell() {
        Shell.build("java -jar minsx-authorization-starter-1.0.0.jar")
                .inPath("E:/Temp/ServerRunner/MsAuthServer")
                .charset("UTF-8")
                .sync(true)
                .onOut((line, operator) -> {
                    System.out.println(line);
                }).run();
    }

}
```
### 输出
```
      __  __ _                 _ _ _
 /\\ |  \/  (_)               \ \ \ \
( ( )| \  / |_ _ __  _____  __ \ \ \ \
 \\/ | |\/| | | '_ \/ __\ \/ /  ) ) ) )
     | |  | | | | | \__ \>  <  / / / /
     |_|  |_|_|_| |_|___/_/\_\/_/_/_/
=======================================
 :: Minsx Authorization :: (v1.0.0)
2018-04-04 15:57:08.453  INFO 12720 --- [           main] c.m.a.starter.ApplicationStarter         : Starting ApplicationStarter on JokerPc with PID 12720 (E:\Temp\ServerRunner\MsAuthServer\minsx-authorization-starter-1.0.0.jar started by Joker in E:\Temp\ServerRunner\MsAuthServer)
2018-04-04 15:57:08.463  INFO 12720 --- [           main] c.m.a.starter.ApplicationStarter         : No active profile set, falling back to default profiles: default
2018-04-04 15:57:09.163  INFO 12720 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@1ff54e6: startup date [Wed Apr 04 15:57:09 CST 2018]; root of context hierarchy
2018-04-04 15:57:11.321  INFO 12720 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2018-04-04 15:57:12.151  INFO 12720 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2018-04-04 15:57:15.108  INFO 12720 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8693 (http)
2018-04-04 15:57:15.138  INFO 12720 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2018-04-04 15:57:15.148  INFO 12720 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.23
2018-04-04 15:57:15.402  INFO 12720 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2018-04-04 15:57:15.402  INFO 12720 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 6249 ms
```
