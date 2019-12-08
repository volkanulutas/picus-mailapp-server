package com.picussecurity.mailapp;

import java.io.Serializable;
import java.util.Objects;

public class MailTransferDto implements Serializable {
    private static final long serialVersionUID = -3638756054103259366L;

   private String mailGroupName;

   private String campaignName;

    public MailTransferDto() {
    }

    public MailTransferDto(String mailGroupName, String campaignName) {
        this.mailGroupName = mailGroupName;
        this.campaignName = campaignName;
    }

    public String getMailGroupName() {
        return mailGroupName;
    }

    public void setMailGroupName(String mailGroupName) {
        this.mailGroupName = mailGroupName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailTransferDto that = (MailTransferDto) o;
        return Objects.equals(mailGroupName, that.mailGroupName) &&
                Objects.equals(campaignName, that.campaignName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailGroupName, campaignName);
    }

    @Override
    public String toString() {
        return "MailTransferDto{" +
                "mailGroupName='" + mailGroupName + '\'' +
                ", campaignName='" + campaignName + '\'' +
                '}';
    }
}
