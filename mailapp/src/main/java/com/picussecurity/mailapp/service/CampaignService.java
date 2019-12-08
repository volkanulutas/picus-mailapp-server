package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.entity.Campaign;

import java.util.List;

public interface CampaignService {
    Campaign findById(Long campaignId);

    void deleteCampaign(Long campaignId);

    Campaign saveCampaign(Campaign campaign);

    List<Campaign> findAll();

    Campaign findByName(String campaignName);
}
