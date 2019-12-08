package com.picussecurity.mailapp.data;

import com.picussecurity.mailapp.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
public class Mail implements Serializable {
    private static final long serialVersionUID = 4241136111743574466L;

    private Long id;

    private String fromEmailAddress;

    private String toEmailAddress;

    private String mailBody;

    private String mailSubject;

    private String key;

    public Mail() {
    }

    public Mail(String toEmailAddress, String mailBody, String mailSubject, String key) {
        this.key = key;
        this.toEmailAddress = toEmailAddress;
        this.mailBody = mailBody;
        this.mailSubject = mailSubject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromEmailAddress() {
        return fromEmailAddress;
    }

    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getToEmailAddress() {
        return toEmailAddress;
    }

    public void setToEmailAddress(String toEmailAddress) {
        this.toEmailAddress = toEmailAddress;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
