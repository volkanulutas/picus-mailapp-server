package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;
import com.picussecurity.mailapp.repository.MailGroupRepository;
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
public class MailGroupServiceImpl implements MailGroupService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailGroupServiceImpl.class);

    private MailGroupRepository mailGroupRepository;

    @Autowired
    public MailGroupServiceImpl(MailGroupRepository mailGroupRepository) {
        this.mailGroupRepository = mailGroupRepository;
    }

    public MailGroup saveMailGroup(MailGroup mailGroup) {
        return mailGroupRepository.save(mailGroup);
    }

    public List<MailGroup> findAll() {
        return mailGroupRepository.findAll();
    }

    @Override
    public void addUserToMailGroup(User user, String mailGroupNameParam) {
        try {
            List<MailGroup> mailGroupList = mailGroupRepository.getByName(mailGroupNameParam);
            if (!mailGroupList.isEmpty()) {
                MailGroup mailGroup = mailGroupList.get(0);
                mailGroup.addUser(user);
                saveMailGroup(mailGroup);
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while adding user to mail group. Detail: ", ex);
        }
    }

    @Override
    public List<MailGroup> findByName(String name) {
        return mailGroupRepository.getByName(name);
    }

    @Override
    public MailGroup findMaiGroupByName(String name) {
        MailGroup result = null;
        List<MailGroup> mailGroupList = mailGroupRepository.getByName(name);
        if (!mailGroupList.isEmpty()) {
            result = mailGroupList.get(0);
        }
        return result;
    }

    public MailGroup createUser(MailGroup mailGroup) {
        return mailGroupRepository.save(mailGroup);
    }

    public MailGroup updateUser(MailGroup mailGroup) throws InvalidParameterException {
        if (mailGroupRepository.exists(mailGroup.getId())) {
            return mailGroupRepository.save(mailGroup);
        }
        throw new InvalidParameterException();
    }

    public void deleteUser(Long id) {
        if (mailGroupRepository.exists(id)) {
            mailGroupRepository.delete(id);
        }
    }

    public MailGroup findById(Long userId) {
        return mailGroupRepository.findById(userId);
    }
}