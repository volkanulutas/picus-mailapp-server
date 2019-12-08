package com.picussecurity.mailapp.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Entity(name = "MailEntity")
@EntityListeners(AuditingEntityListener.class)
public class MailEntity implements Serializable {
    private static final long serialVersionUID = 6221796712911449321L;

    @Id
    @GeneratedValue
    private Long id;

    private String toMailAdress;

    private String sendingTime;

    private String seenTime;

    private String difference;

    private String key;

    public MailEntity() {
    }

    public MailEntity(String toMailAdress, String sendingTime, String seenTime, String key) {
        this.toMailAdress = toMailAdress;
        this.sendingTime = sendingTime;
        this.seenTime = seenTime;
        this.key = key;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getToMailAdress() {
        return toMailAdress;
    }

    public void setToMailAdress(String toMailAdress) {
        this.toMailAdress = toMailAdress;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getSeenTime() {
        return seenTime;
    }

    public void setSeenTime(String seenTime) {
        this.seenTime = seenTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "MailEntity{" +
                "id=" + id +
                ", toMailAdress='" + toMailAdress + '\'' +
                ", sendingTime=" + sendingTime +
                ", seenTime=" + seenTime +
                ", key='" + key + '\'' +
                '}';
    }
}
