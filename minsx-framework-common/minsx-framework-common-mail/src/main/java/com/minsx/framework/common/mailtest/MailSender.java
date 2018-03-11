package com.minsx.framework.common.mailtest;

import org.apache.commons.mail.EmailAttachment;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface MailSender {

    void sendSimpleMail(String to, String title, String message);

    void sendSimpleMail(List<String> to, String title, String message);

    void sendHtmlMail(String to, String title, String htmlText);

    void sendHtmlMail(String to, String title, String htmlText, File file);

    void sendHtmlMail(String to, String title, String htmlText, List<EmailAttachment> emailAttachments);

    void sendHtmlMail(List<String> to, String title, String htmlText);

    void sendHtmlMail(List<String> to, String title, String htmlText, File file);

    void sendHtmlMail(List<String> to, String title, String htmlText, List<EmailAttachment> emailAttachments);

    void sendTemplateMail(String to, String title, Class clazz, String templateName, Map<String, Object> params);

    void sendTemplateMail(List<String> to, String title, Class clazz, String templateName, Map<String, Object> params);

    void sendTemplateMail(String to, String title, Class clazz, String templateName, Map<String, Object> params, List<EmailAttachment> emailAttachments);

    void sendTemplateMail(List<String> to, String title, Class clazz, String templateName, Map<String, Object> params, List<EmailAttachment> emailAttachments);

    void sendSysTemplateMail(String to, String title, Map<String, Object> params);

    void sendSysTemplateMail(List<String> to, String title, Map<String, Object> params);

    void sendSysTemplateMail(String to, String title, Map<String, Object> params, List<EmailAttachment> emailAttachments);

    void sendSysTemplateMail(List<String> to, String title, Map<String, Object> params, List<EmailAttachment> emailAttachments);


}
