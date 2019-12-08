package com.picussecurity.mailapp.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Entity(name = "Campaign")
@EntityListeners(AuditingEntityListener.class)
public class Campaign implements Serializable {
    private static final long serialVersionUID = 1888652130689778063L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String beginDate;

    private String endDate;

    private String messageBody;

    /*private boolean active;*/

    public Campaign() {
    }

    public Campaign(String name, String beginDate, String endDate, String messageBody) {
        this.name = name;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.messageBody = messageBody;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return Objects.equals(id, campaign.id) &&
                Objects.equals(name, campaign.name) &&
                Objects.equals(beginDate, campaign.beginDate) &&
                Objects.equals(endDate, campaign.endDate) &&
                Objects.equals(messageBody, campaign.messageBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, beginDate, endDate, messageBody);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
