package com.picussecurity.mailapp.repository;

import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.entity.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Repository
public interface MailEntityRepository extends JpaRepository<MailEntity, Long> {
    List<MailEntity> findByKey(String key);
}