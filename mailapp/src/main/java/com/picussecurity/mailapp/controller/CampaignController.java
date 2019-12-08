package com.picussecurity.mailapp.controller;

import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created volkanulutas on 07.12.2019.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RequestMapping("/api/campaigns")
public class CampaignController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignController.class);

    @Autowired
    private CampaignService campaignService;

    @GetMapping("/")
    public List<Campaign> getAllCampaigns() {
        try {
            return campaignService.findAll();
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting all campaigns. Detail: ", ex);
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable(value = "id") Long campaignId) {
        try {
            Campaign campaign = campaignService.findById(campaignId);
            if (campaign != null) {
                return ResponseEntity.ok().body(campaign);
            }

        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting campaign by id. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Campaign> createCampaign(@Valid @RequestBody Campaign campaign) {
        try {
            return ResponseEntity.ok(campaignService.saveCampaign(campaign));
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while creating campaign. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable(value = "id") Long campaignId,
                                                   @Valid @RequestBody Campaign campaignParam) {
        try {
            Campaign campaign = campaignService.findById(campaignId);
            if (campaign != null) {
                campaign.setId(campaignParam.getId());
                campaign.setBeginDate(campaignParam.getBeginDate());
                campaign.setEndDate(campaignParam.getEndDate());
                campaign.setMessageBody(campaignParam.getMessageBody());
                campaign.setName(campaign.getName());
                final Campaign updatedCampaign = campaignService.saveCampaign(campaign);
                return ResponseEntity.ok(updatedCampaign);
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while updating campaign. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCampaign(@PathVariable(value = "id") Long campaignId) {
        try {
            Campaign campaign = campaignService.findById(campaignId);
            if (campaign != null) {
                campaignService.deleteCampaign(campaignId);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return response;
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while deleting campaign. Detail: ", ex);
        }
        return Collections.emptyMap();
    }
}
