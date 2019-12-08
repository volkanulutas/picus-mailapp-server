package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.repository.CampaignRepository;
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
public class CampaignServiceImpl implements CampaignService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignServiceImpl.class);

    private CampaignRepository campaignRepository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void saveCampaign(List<Campaign> campaignList) {
        for (Campaign campaign : campaignList) {
            campaignRepository.save(campaign);
        }
    }

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    @Override
    public Campaign findByName(String campaignName) {
        Campaign result = null;
        List<Campaign> campaignList = campaignRepository.findByName(campaignName);
        if (!campaignList.isEmpty()) {
            result = campaignList.get(0);
        }
        return result;
    }


    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Campaign updateCampaign(Campaign campaign) throws InvalidParameterException {
        if (campaignRepository.exists(campaign.getId())) {
            return campaignRepository.save(campaign);
        }
        throw new InvalidParameterException();
    }

    public void deleteCampaign(Long id) {
        if (campaignRepository.exists(id)) {
            campaignRepository.delete(id);
        }
    }

    public Campaign findById(Long campaignId) {
        return campaignRepository.findById(campaignId);
    }
}