package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;
import com.picussecurity.mailapp.repository.MailGroupRepository;
import com.picussecurity.mailapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    private MailGroupService mailGroupService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void saveUsers(List<User> userList) {
        for (User user : userList) {
            userRepository.save(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (userRepository.exists(user.getId())) {
            return userRepository.save(user);
        }
        throw new InvalidParameterException();
    }

    public void deleteUser(Long id) {
        if (userRepository.exists(id)) {
            userRepository.delete(id);
        }
    }

    @Override
    public User addUserToMailGroup(User user, String mailGroupName) {
        mailGroupService.addUserToMailGroup(user, mailGroupName);
        return user;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId);
    }
}