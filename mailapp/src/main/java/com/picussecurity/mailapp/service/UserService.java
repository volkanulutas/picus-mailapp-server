package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long userId);

    User saveUser(User user);

    void deleteUser(Long userId);

    User addUserToMailGroup(User user, String mailGroupName);
}
