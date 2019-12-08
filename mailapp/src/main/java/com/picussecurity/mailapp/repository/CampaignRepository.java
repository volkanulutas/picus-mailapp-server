package com.picussecurity.mailapp.repository;

import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Campaign findById(Long id);

    List<Campaign> findByName(String name);
}