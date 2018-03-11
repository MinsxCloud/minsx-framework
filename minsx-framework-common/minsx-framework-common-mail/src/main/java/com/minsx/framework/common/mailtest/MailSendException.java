package com.minsx.framework.common.mailtest;


public class MailSendException extends RuntimeException {

    private String from;

    private String to;

    private String title;

    private String text;

    public MailSendException(String from, String to, String title, String text, String message, Throwable t) {
        super(message, t);
        this.from = from;
        this.to = to;
        this.title = title;
        this.text = text;
    }

    public MailSendException(String message) {
        super(message);
    }

    MailSendException(Throwable t) {
        super(t);
    }

    public MailSendException(String message, Throwable t) {
        super(message, t);
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
