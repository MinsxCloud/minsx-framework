package com.minsx.framework.common.mailtest;


import com.minsx.util.base.FileUtil;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MailSenderDefaultImpl implements MailSender {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String from;
    private SimpleEmail simpleEmail;
    private HtmlEmail htmlEmail;
    private String templateFolder = "MailTemplate";

    private void initialByField(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = username;
    }

    private void initialByProperties(Properties properties) {
        this.host = properties.getProperty("host");
        this.port = Integer.valueOf(properties.getProperty("port"));
        this.username = properties.getProperty("username");
        this.password = properties.getProperty("password");
        this.from = properties.getProperty("from");
        if (properties.getProperty("templateFolder") == null) {
            this.templateFolder = properties.getProperty("templateFolder");
        }

    }

    private void initialByConfig(MailConfig mailConfig) {
        this.host = mailConfig.getHost();
        this.port = mailConfig.getPort();
        this.username = mailConfig.getUsername();
        this.password = mailConfig.getPassword();
        this.from = mailConfig.getFrom();
        this.templateFolder = mailConfig.getTemplateFolder();
    }

    public MailSenderDefaultImpl() {
    }

    public MailSenderDefaultImpl(SimpleEmail simpleEmail) {
        this.simpleEmail = simpleEmail;
    }

    public MailSenderDefaultImpl(HtmlEmail htmlEmail) {
        this.htmlEmail = htmlEmail;
    }

    public MailSenderDefaultImpl(Properties properties) {
        initialByProperties(properties);
        finishInitial();
    }

    public MailSenderDefaultImpl(MailConfig mailConfig) {
        initialByConfig(mailConfig);
        finishInitial();
    }

    public MailSenderDefaultImpl(String host, Integer port, String username, String password) {
        initialByField(host, port, username, password);
        finishInitial();
    }

    public void finishInitial() {
        try {
            Integer sslPort = 465;
            simpleEmail = new SimpleEmail();
            simpleEmail.setHostName(this.host);
            simpleEmail.setSmtpPort(this.port);
            simpleEmail.setAuthentication(this.username, this.password);
            simpleEmail.setFrom(this.from);
            simpleEmail.setCharset("UTF-8");
            if (sslPort.equals(this.port)) {
                simpleEmail.setSSLOnConnect(true);
            }
            htmlEmail = new HtmlEmail();
            htmlEmail.setHostName(this.host);
            htmlEmail.setSmtpPort(this.port);
            htmlEmail.setAuthentication(this.username, this.password);
            htmlEmail.setFrom(this.from);
            htmlEmail.setCharset("UTF-8");
            if (sslPort.equals(this.port)) {
                htmlEmail.setSSLOnConnect(true);
            }
        } catch (EmailException e) {
            throw new MailSendException(String.format("Incorrect setting field of [from],cause: %s", e.getMessage()), e);
        }
    }

    //-------------------------------------------------------------

    @Override
    public void sendSimpleMail(String to, String title, String message) {
        finishInitial();
        try {
            simpleEmail.addTo(to);
            simpleEmail.setSubject(title);
            simpleEmail.setMsg(message);
            simpleEmail.setSentDate(new Date());
            simpleEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getCause().getMessage()), e);
        }
    }

    @Override
    public void sendSimpleMail(List<String> to, String title, String message) {
        finishInitial();
        try {
            for (String t : to) {
                simpleEmail.addBcc(t);
            }
            simpleEmail.setSubject(title);
            simpleEmail.setMsg(message);
            simpleEmail.setSentDate(new Date());
            simpleEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getCause().getMessage()), e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String title, String htmlText) {
        finishInitial();
        try {
            htmlEmail.setSubject(title);
            htmlEmail.addTo(to);
            htmlEmail.setHtmlMsg(htmlText);
            htmlEmail.setSentDate(new Date());
            htmlEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String title, String htmlText, File file) {
        finishInitial();
        try {
            htmlEmail.setSubject(title);
            htmlEmail.addTo(to);
            htmlEmail.setHtmlMsg(htmlText);
            htmlEmail.setSentDate(new Date());
            htmlEmail.attach(file);
            htmlEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String title, String htmlText, List<EmailAttachment> emailAttachments) {
        finishInitial();
        try {
            htmlEmail.setSubject(title);
            htmlEmail.addTo(to);
            htmlEmail.setHtmlMsg(htmlText);
            htmlEmail.setSentDate(new Date());
            for (EmailAttachment emailAttachment : emailAttachments) {
                htmlEmail.attach(emailAttachment);
            }
            htmlEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }


    @Override
    public void sendHtmlMail(List<String> to, String title, String htmlText) {
        finishInitial();
        try {
            htmlEmail.setSubject(title);
            for (String t : to) {
                simpleEmail.addBcc(t);
            }
            htmlEmail.setHtmlMsg(htmlText);
            htmlEmail.setSentDate(new Date());
            htmlEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendHtmlMail(List<String> to, String title, String htmlText, File file) {
        finishInitial();
        try {
            htmlEmail.setSubject(title);
            for (String t : to) {
                simpleEmail.addBcc(t);
            }
            htmlEmail.setHtmlMsg(htmlText);
            htmlEmail.setSentDate(new Date());
            htmlEmail.attach(file);
            htmlEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendHtmlMail(List<String> to, String title, String htmlText, List<EmailAttachment> emailAttachments) {
        finishInitial();
        try {
            htmlEmail.setSubject(title);
            for (String t : to) {
                simpleEmail.addBcc(t);
            }
            htmlEmail.setHtmlMsg(htmlText);
            htmlEmail.setSentDate(new Date());
            for (EmailAttachment emailAttachment : emailAttachments) {
                htmlEmail.attach(emailAttachment);
            }
            htmlEmail.send();
        } catch (EmailException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendTemplateMail(String to, String title, Class clazz, String templateName, Map<String, Object> params) {
        try {
            String templateContent = getTemplateContent(clazz, templateName, params);
            sendHtmlMail(to, title, templateContent);
        } catch (IOException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendTemplateMail(List<String> to, String title, Class clazz, String templateName, Map<String, Object> params) {
        try {
            String templateContent = getTemplateContent(clazz, templateName, params);
            sendHtmlMail(to, title, templateContent);
        } catch (IOException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendTemplateMail(String to, String title, Class clazz, String templateName, Map<String, Object> params, List<EmailAttachment> emailAttachments) {
        try {
            String templateContent = getTemplateContent(clazz, templateName, params);
            sendHtmlMail(to, title, templateContent, emailAttachments);
        } catch (IOException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendTemplateMail(List<String> to, String title, Class clazz, String templateName, Map<String, Object> params, List<EmailAttachment> emailAttachments) {
        try {
            String templateContent = getTemplateContent(clazz, templateName, params);
            sendHtmlMail(to, title, templateContent, emailAttachments);
        } catch (IOException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendSysTemplateMail(String to, String title, Map<String, Object> params) {
        sendTemplateMail(to, title, MailSenderDefaultImpl.class, "minsx.html", params);
    }

    @Override
    public void sendSysTemplateMail(List<String> to, String title, Map<String, Object> params) {
        sendTemplateMail(to, title, MailSenderDefaultImpl.class, "minsx.html", params);
    }

    @Override
    public void sendSysTemplateMail(String to, String title, Map<String, Object> params, List<EmailAttachment> emailAttachments) {
        try {
            String templateContent = getTemplateContent(MailSenderDefaultImpl.class, "minsx.html", params);
            sendHtmlMail(to, title, templateContent, emailAttachments);
        } catch (IOException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }

    @Override
    public void sendSysTemplateMail(List<String> to, String title, Map<String, Object> params, List<EmailAttachment> emailAttachments) {
        try {
            String templateContent = getTemplateContent(MailSenderDefaultImpl.class, "minsx.html", params);
            sendHtmlMail(to, title, templateContent, emailAttachments);
        } catch (IOException e) {
            throw new MailSendException(String.format("Send Email [%s] failed,cause: %s", title, e.getMessage()), e);
        }
    }


    //----------------------------------------------------------------------------------------
    public String getTemplateContent(Class clazz, String templateName, Map<String, Object> params) throws IOException {
        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(templateFolder + "/" + templateName);
        String templateContent = FileUtil.inputStreamToString(inputStream);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            String value = param.getValue().toString();
            value = value.replace("\n", "<br/>");
            value = value.replace(" ", "&nbsp;");
            value = value.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            templateContent = templateContent.replace("${" + param.getKey() + "}", value);
        }
        return templateContent;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTemplateFolder() {
        return templateFolder;
    }

    public void setTemplateFolder(String templateFolder) {
        this.templateFolder = templateFolder;
    }


}
