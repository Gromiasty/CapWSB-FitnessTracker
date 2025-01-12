package com.capgemini.wsb.fitnesstracker.mail.api;

public class EmailDto {
    private String toAddress;
    private String subject;
    private String content;

    // Constructor
    public EmailDto(String toAddress, String subject, String content) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.content = content;
    }

    // Getter methods
    public String getToAddress() {
        return toAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    // Setter methods
    public void setTo(String toAddress) {
        this.toAddress = toAddress;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String content) {
        this.content = content;
    }
}