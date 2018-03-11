package com.minsx.framework.common.mailtest;

import org.apache.commons.mail.EmailAttachment;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainTest {

    @Test
    public void sendEmailByTencentPersonalSSLSender() throws IOException {
        MailSender mailSender = MailSenderFactory.getTencentPersonalSSLSender("goodsave@qq.com", "xxxxx");
        mailSender.sendSimpleMail("869304858@qq.com", "测试邮件", "测试邮件内容");
    }

    @Test
    public void sendEmailByTencentEnterpriseSender() {
        MailSender mailSender = MailSenderFactory.getTencentEnterpriseSender("support@minsx.com", "xxxx");
        mailSender.sendHtmlMail("goodsave@qq.com", "测试邮件", "测试邮件内容");
    }

    @Test
    public void sendEmailByTencentEnterpriseSSLSender() {
        MailSender mailSender = MailSenderFactory.getTencentEnterpriseSSLSender("support@minsx.com", "xxxxx");
        mailSender.sendHtmlMail("goodsave@qq.com", "测试邮件", "测试邮件内容");
    }

    @Test
    public void sendManyEmail() {
        MailSender mailSender = MailSenderFactory.getTencentPersonalSSLSender("goodsave@qq.com", "xxxxx");
        mailSender.sendSimpleMail(Arrays.asList("869304858@qq.com", "support@minsx.com"), "测试邮件", "测试邮件内容");
    }

    @Test
    public void sendEmailWithAttachment() throws MalformedURLException {
        MailSender mailSender = MailSenderFactory.getTencentPersonalSSLSender("goodsave@qq.com", "xxxxx");
        List<EmailAttachment> emailAttachments = new ArrayList<>();
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setName("附件1.ico");
        emailAttachment.setDescription("这是第1个附件");
        emailAttachment.setURL(new URL("http://image-1251505282.file.myqcloud.com/pic/minsx/logo/LogoOfBlack.png"));
        emailAttachments.add(emailAttachment);
        emailAttachment = new EmailAttachment();
        emailAttachment.setName("附件2.ico");
        emailAttachment.setDescription("这是第2个附件");
        emailAttachment.setURL(new URL("http://image-1251505282.file.myqcloud.com/pic/minsx/logo/LogoOfBlack.png"));
        emailAttachments.add(emailAttachment);
        mailSender.sendHtmlMail("869304858@qq.com", "测试邮件", "测试邮件内容", emailAttachments);
    }


    @Test
    public void sendEmailWithTemplate() throws MalformedURLException {
        MailSender mailSender = MailSenderFactory.getTencentPersonalSSLSender("goodsave@qq.com", "xxxxx");
        Map<String, Object> params = new HashMap<>();
        params.put("headerLogo", "https://image.minsx.com/pic/minsx/logo/LogoOfWhite.png");
        params.put("footerLogo", "https://image.minsx.com/pic/minsx/logo/logoSuperGray.png");
        params.put("webSite", "https://www.minsx.com/");
        params.put("webName", "米斯云平台");
        params.put("welcome", "尊敬的Only小春:");
        params.put("content", "您正在米斯云平台修改密码\n您的验证码是: 869304858\n温馨提示: 如非您本人操作,请忽略该邮件!");
        params.put("company", "杭州米斯云科技股份有限公司");
        params.put("remind", "该邮件由米斯云平台系统发出,请勿直接回复!");
        params.put("copyRight", "如果您有任何问题，请及时联系我们：\nEmail: support@minsx.com\nCopyright © 2016-2017 minsx.com All rights reserved.");
        params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        mailSender.sendSysTemplateMail("869304858@qq.com", "测试邮件", params);
    }

    @Test
    public void sendManyTimes(){
        sendEmailByTencentEnterpriseSender();
        sendEmailByTencentEnterpriseSender();
    }


}
