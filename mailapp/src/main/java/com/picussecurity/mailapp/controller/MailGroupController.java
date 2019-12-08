package com.picussecurity.mailapp.controller;


import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.service.MailGroupService;
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
import java.util.*;


/**
 * Created volkanulutas on 07.12.2019.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/mailgroups")
public class MailGroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailGroupController.class);

    @Autowired
    private MailGroupService mailGroupService;


    @GetMapping("/")
    public List<MailGroup> getAllMailGroups() {
        try {
            return mailGroupService.findAll();
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting all mail groups. Detail: ", ex);
        }
        return Collections.emptyList();
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<MailGroup> getMailGroupByName(@PathVariable(value = "name") String name) {
        try {
            List<MailGroup> mailGroupList = mailGroupService.findByName(name);
            if (!mailGroupList.isEmpty()) {
                return ResponseEntity.ok(mailGroupList.get(0));
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting all mail groups. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MailGroup> getMailGroupById(@PathVariable(value = "id") Long mailGroupId) {
        try {
            MailGroup mailGroup = mailGroupService.findById(mailGroupId);
            if (mailGroup != null) {
                return ResponseEntity.ok().body(mailGroup);
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while getting mail group by id. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<MailGroup> createMailGroup(@Valid @RequestBody MailGroup mailGroup) {
        try {
            return ResponseEntity.ok(mailGroupService.saveMailGroup(mailGroup));
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while creating mail group. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MailGroup> updateMailGroup(@PathVariable(value = "id") Long mailGroupId,
                                                     @Valid @RequestBody MailGroup mailGroupParam) {
        try {
            MailGroup mailGroup = mailGroupService.findById(mailGroupId);
            if (mailGroup != null) {
                mailGroup.setId(mailGroupParam.getId());
                mailGroup.setName(mailGroupParam.getName());
                mailGroup.setUserList(mailGroupParam.getUserList());
                final MailGroup updatedMailGroup = mailGroupService.saveMailGroup(mailGroup);
                return ResponseEntity.ok(updatedMailGroup);
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while updating mail group. Detail: ", ex);
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteMailGroup(@PathVariable(value = "id") Long mailGroupId) {
        try {
            MailGroup mailGroup = mailGroupService.findById(mailGroupId);
            if (mailGroup != null) {
                mailGroupService.deleteUser(mailGroupId);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return response;
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while deleting mail group. Detail: ", ex);
        }
        return Collections.emptyMap();
    }
}