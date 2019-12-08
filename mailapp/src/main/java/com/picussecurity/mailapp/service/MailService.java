package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.data.Mail;
import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.entity.MailEntity;
import com.picussecurity.mailapp.entity.MailGroup;

import java.util.List;

public interface MailService {
    void sendEmail(Mail mail);

    void sendEmail(MailGroup mailGroup, Campaign campaign);

    MailEntity saveMailEntity(MailEntity mailEntity);

    List<MailEntity> findAll();

    String generateUniqueKey();

    MailEntity handleKeyEmailFromSuccessPage(MailEntity mailEntity);
}
