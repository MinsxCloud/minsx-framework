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

### Minsx-framework-common 主要模块：

#### Minsx-framework-common-excel
##### 主要功能
+  支持同名列
+  支持合并单元格判断及取值
+  支持JAVA对象转化
+  支持去除其他空行/列
+  支持所有格式的单元格数据
+  所有单元格值均以String储存,以保持精度

##### Excel Reader使用示例
```java

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class ExcelTest {

    private ExcelReader excelReader;

    @Before
    public void initial() throws FileNotFoundException {
        excelReader = new ExcelReader(ExcelTest.class.getClassLoader().getResourceAsStream("测试.xlsx"));
    }

    @Test
    public void getAllSheetData() {
        List<Sheet> dataList = excelReader.getAllSheet();
        System.out.println(JSON.toJSONString(dataList));
    }

    @Test
    public void getAllSheetDataWithDataMapper() {
        List<Sheet> newDataList = excelReader.getAllSheet(oldCell -> {
            Cell newCell = new Cell(oldCell);
            if ("姓名".equals(oldCell.getColumnName())) {
                newCell.setValue("新姓名是:" + oldCell.getValue());
            }
            return newCell;
        });
        System.out.println(JSON.toJSONString(newDataList));
    }

    @Test
    public void getSheetData() {
        Sheet sheet = excelReader.getSheet(0);
        System.out.println(JSON.toJSONString(sheet));
    }

    @Test
    public void getRowData() {
        Row row = excelReader.getRow(0, 0);
        System.out.println(JSON.toJSONString(row));
    }

    @Test
    public void getCellData() {
        Cell cell = excelReader.getCell(0, 0, 0);
        System.out.println(JSON.toJSONString(cell));
    }

    @Test
    public void getSheetDataWithDataMapper() {
        Sheet newSheet = excelReader.getSheet(0, oldCell -> {
            Cell newCell = new Cell(oldCell);
            if ("姓名".equals(oldCell.getColumnName())) {
                newCell.setValue("新姓名是:" + oldCell.getValue());
            }
            return newCell;
        });
        System.out.println(JSON.toJSONString(newSheet));
    }

    @Test
    public void getBeansOfSheet() {
        List<Student> students = excelReader.getBeansOfSheet(1, oldRowData -> {
            Student student = new Student();
            oldRowData.getCells().forEach(oldCellData -> {
                if ("姓名".equals(oldCellData.getColumnName())) {
                    student.setName(oldCellData.getValue());
                } else if ("年龄".equals(oldCellData.getColumnName())) {
                    student.setAge(oldCellData.getValue());
                } else if ("性别".equals(oldCellData.getColumnName())) {
                    student.setSex(oldCellData.getValue());
                }
            });
            return student;
        });
        System.out.println(JSON.toJSONString(students));
    }

}

```

##### 输出结果
```
[{
	"rows": [{
		"cells": [{
			"columnName": "姓名",
			"type": "STRING",
			"value": "小张"
		}, {
			"columnName": "年龄",
			"type": "NUMERIC",
			"value": "21"
		}, {
			"columnName": "性别",
			"type": "STRING",
			"value": "男"
		}]
	}, {
		"cells": [{
			"columnName": "姓名",
			"type": "STRING",
			"value": "小红"
		}, {
			"columnName": "年龄",
			"type": "NUMERIC",
			"value": "14"
		}, {
			"columnName": "性别",
			"type": "STRING",
			"value": "女"
		}]
	}, {
		"cells": [{
			"columnName": "姓名",
			"type": "STRING",
			"value": "小明"
		}, {
			"columnName": "年龄",
			"type": "NUMERIC",
			"value": "25"
		}, {
			"columnName": "性别",
			"mRValue": "男",
			"type": "STRING"
		}]
	}, {
		"cells": [{
			"columnName": "姓名",
			"type": "STRING",
			"value": "小亮"
		}, {
			"columnName": "年龄",
			"type": "NUMERIC",
			"value": "31"
		}, {
			"columnName": "性别",
			"mRValue": "男",
			"type": "BLANK"
		}]
	}, .........]
}, {
	"rows": [{
		"cells": [{
			"columnName": "姓名",
			"type": "STRING",
			"value": "老张"
		}, {
			"columnName": "年龄",
			"type": "NUMERIC",
			"value": "54"
		}, {
			"columnName": "性别",
			"type": "STRING",
			"value": "男"
		}]
	}, ........]
}]

```


#### Minsx-framework-common-mail
##### 主要功能
+  整合阿里/腾讯/网易/谷歌等各大企业邮箱及个人邮箱服务器配置
+  仅需给定用户名与密码即可完成邮箱服务器配置
+  可根据需要选择是否开启SSL发送邮件
+  支持普通文本邮件
+  支持HTML邮件
+  支持任意附件邮件
+  支持批量发送邮件
+  内置系统模板
+  可自定义模板

##### 极简的配置

```java

import com.minsx.framework.common.mail.MailSender;
import com.minsx.framework.common.mail.MailSenderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    public MailSender getMailSender() {
        return MailSenderFactory.getTencentEnterpriseSSLSender("yourUsername", "yourPassword");
    }

}

```
##### 极简的使用

```java

import com.minsx.framework.common.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailUtil {

    @Autowired
    MailSender mailSender;

    public void sendMail(String to, String title, String content) {
        mailSender.sendSimpleMail(to,title,content);
    }

}

```
##### 系统模板使用

```java
	
import com.minsx.framework.common.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailUtil {

    @Autowired
    MailSender mailSender;

    public Boolean sendMail(String to, String username, String title, String content) {
        Boolean isSuccess = true;
        Map<String, Object> params = new HashMap<>();
        params.put("headerLogo", "https://image.minsx.com/pic/minsx/logo/LogoOfWhite.png");
        params.put("footerLogo", "https://image.minsx.com/pic/minsx/logo/logoSuperGray.png");
        params.put("webSite", "https://www.minsx.com/");
        params.put("webName", "米斯云平台");
        params.put("welcome", String.format("尊敬的%s:", username));

        params.put("content", content);
        params.put("company", "米斯云平台");
        params.put("remind", "该邮件由米斯云平台系统发出,请勿直接回复!");
        params.put("copyRight", "如果您有任何问题，请及时联系我们：\nEmail: support@minsx.com\nCopyright © 2016-2017 minsx.com All rights reserved.");
        params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            mailSender.sendSysTemplateMail(to, title, params);
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

}

```
##### 系统模板效果
![0](https://raw.githubusercontent.com/MinsxCloud/minsx-framework/master/docs/image/common/MailSystemTemplateExample.png "系统模板示例")
##### Maven依赖使用请参考
+ POM引入：
```
<dependency>
  <groupId>com.minsx</groupId>
  <artifactId>minsx-framework-common</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
+ 其他版本Maven地址：http://mvnrepository.com/search?q=minsx
