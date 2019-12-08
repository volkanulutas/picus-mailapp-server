package com.picussecurity.mailapp;

import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.entity.MailEntity;
import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;
import com.picussecurity.mailapp.service.CampaignService;
import com.picussecurity.mailapp.service.MailGroupService;
import com.picussecurity.mailapp.service.MailService;
import com.picussecurity.mailapp.service.UserService;
import com.picussecurity.mailapp.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ApplicationStartUp {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private MailGroupService mailGroupService;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("Sample data creation is beginning. ");

        Campaign campaign = new Campaign("Campaign Test 1",
                "08/12/2019",
                "15/12/2019",
                "Campaign sample body");
        campaignService.saveCampaign(campaign);
        Campaign campaign2 = new Campaign("Campaign Test 2",
                "08/12/2019",
                "15/12/2019",
                "Campaign sample body");
        campaignService.saveCampaign(campaign2);


        User user = new User("Volkan", "Ulutas", "volkanulutaspicus@gmail.com", true);
        userService.saveUser(user);

        MailGroup mailGroup = new MailGroup("MailGroup Test 1", Collections.emptyList());
        mailGroupService.saveMailGroup(mailGroup);
        MailGroup mailGroup2 = new MailGroup("MailGroup Test 2", Collections.emptyList());
        mailGroupService.saveMailGroup(mailGroup2);

        MailEntity mailEntity = new MailEntity("test@test.com",
                Util.convertToFormattedDate(System.currentTimeMillis()),
                Util.convertToFormattedDate(System.currentTimeMillis()), "key1");
        mailEntity.setDifference("O");
        mailService.saveMailEntity(mailEntity);

        System.out.println("Sample data creation is finished. ");
    }

}
