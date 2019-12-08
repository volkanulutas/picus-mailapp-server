package com.picussecurity.mailapp.controller;

import com.picussecurity.mailapp.MailTransferDto;
import com.picussecurity.mailapp.data.Mail;
import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.entity.MailEntity;
import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;
import com.picussecurity.mailapp.service.CampaignService;
import com.picussecurity.mailapp.service.MailGroupService;
import com.picussecurity.mailapp.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * Created volkanulutas on 07.12.2019.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/mail")
public class MailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private MailGroupService mailGroupService;

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity sendMail(@RequestBody Mail mail) {
        try {
            mailService.sendEmail(mail);
        } catch (Exception ex) {
            System.out.println("Error is occurred. Detail: " + ex);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/sendMailToMailGroup", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity sendMail(@RequestBody MailGroup mailGroup, @RequestBody Campaign campaign) {
        try {
            mailService.sendEmail(mailGroup, campaign);
        } catch (Exception ex) {
            System.out.println("Error is occurred while sending mail to mail groups. Detail: " + ex);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/save/")
    public ResponseEntity<MailEntity> sendKeyEmail(@Valid @RequestBody MailEntity mailEntity) {
        try {
            ResponseEntity.ok(mailService.handleKeyEmailFromSuccessPage(mailEntity));
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while handling success page request! Detail:", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/sendMail/")
    public ResponseEntity<User> sendMail(@Valid @RequestBody MailTransferDto mailTransferDto) {
        try {
            String campaignName = mailTransferDto.getCampaignName();
            String mailGroupName = mailTransferDto.getMailGroupName();

            MailGroup mailGroup = mailGroupService.findMaiGroupByName(mailGroupName);
            Campaign campaign = campaignService.findByName(campaignName);

            mailService.sendEmail(mailGroup, campaign);


            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while sending mail!", ex);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/")
    public List<MailEntity> getAllMailEntities() {
        try {
            return mailService.findAll();
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting all mail groups. Detail: ", ex);
        }
        return Collections.emptyList();
    }


}
