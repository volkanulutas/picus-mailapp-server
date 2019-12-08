package com.picussecurity.mailapp.repository;

import com.picussecurity.mailapp.data.Mail;
import com.picussecurity.mailapp.entity.MailGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Repository
public interface MailGroupRepository extends JpaRepository<MailGroup, Long> {
    MailGroup findById(Long id);

    List<MailGroup> getByName(String mailGroupName);
}