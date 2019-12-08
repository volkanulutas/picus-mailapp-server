package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.data.Mail;
import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;

import java.util.List;

public interface MailGroupService {
    MailGroup saveMailGroup(MailGroup mailGroup);

    MailGroup findById(Long mailGroupId);

    void deleteUser(Long mailGroupId);

    List<MailGroup> findAll();

    void addUserToMailGroup(User user, String mailGroupName);

    List<MailGroup> findByName(String name);

    MailGroup findMaiGroupByName(String name);
}
