package com.minsx.framework.common.mail;

import java.util.Properties;

public class MailSenderFactory {

    private final static String PROTOCOL = "smtp";
    private final static String TENCENT_ENTERPRISE_HOST = "smtp.exmail.qq.com";
    private final static String TENCENT_PERSONAL_HOST = "smtp.qq.com";
    private final static String ALIBABA_ENTERPRISE_HOST = "smtp.mxhichina.com";
    private final static String ALIBABA_PERSONAL_HOST = "smtp.aliyun.com";
    private final static String NETEASE_PERSONAL_HOST = "smtp.163.com";
    private final static String GOOGLE_PERSONAL_HOST = "smtp.google.com";

    public static MailSender getMailSender(Properties config) {
        return new MailSenderDefaultImpl(config);
    }

    public static MailSender getTencentPersonalSSLSender(String username, String password) {
        return new MailSenderDefaultImpl(TENCENT_PERSONAL_HOST, 465, username, password);
    }

    public static MailSender getTencentEnterpriseSender(String username, String password) {
        return new MailSenderDefaultImpl(TENCENT_ENTERPRISE_HOST, 25, username, password);
    }

    public static MailSender getTencentEnterpriseSSLSender(String username, String password) {
        return new MailSenderDefaultImpl(TENCENT_ENTERPRISE_HOST, 465, username, password);
    }

    public static MailSender getAlibabaEnterpriseSender(String username, String password) {
        return new MailSenderDefaultImpl(ALIBABA_ENTERPRISE_HOST, 25, username, password);
    }

    public static MailSender getAlibabaEnterpriseSSLSender(String username, String password) {
        return new MailSenderDefaultImpl(ALIBABA_ENTERPRISE_HOST, 465, username, password);
    }

    public static MailSender getAlibabaPersonalSender(String username, String password) {
        return new MailSenderDefaultImpl(ALIBABA_PERSONAL_HOST, 25, username, password);
    }

    public static MailSender getNetEasePersonalSender(String username, String password) {
        return new MailSenderDefaultImpl(NETEASE_PERSONAL_HOST, 25, username, password);
    }

    public static MailSender getNetEasePersonalSSLSender(String username, String password) {
        return new MailSenderDefaultImpl(NETEASE_PERSONAL_HOST, 465, username, password);
    }

    public static MailSender getGooglePersonalSender(String username, String password) {
        return new MailSenderDefaultImpl(GOOGLE_PERSONAL_HOST, 25, username, password);
    }

    public static MailSender getGooglePersonalSSLSender(String username, String password) {
        return new MailSenderDefaultImpl(GOOGLE_PERSONAL_HOST, 465, username, password);
    }

}
