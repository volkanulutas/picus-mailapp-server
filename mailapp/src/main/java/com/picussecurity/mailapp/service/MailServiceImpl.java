package com.picussecurity.mailapp.service;

import com.picussecurity.mailapp.config.ApplicationConfig;
import com.picussecurity.mailapp.data.Mail;
import com.picussecurity.mailapp.entity.Campaign;
import com.picussecurity.mailapp.entity.MailEntity;
import com.picussecurity.mailapp.entity.MailGroup;
import com.picussecurity.mailapp.entity.User;
import com.picussecurity.mailapp.repository.MailEntityRepository;
import com.picussecurity.mailapp.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Service
public class MailServiceImpl implements MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Autowired
    private MailEntityRepository mailEntityRepository;

    public void sendEmail(Mail mail) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(applicationConfig.getAdminMailAddress());
            msg.setTo(mail.getToEmailAddress());
            msg.setSubject(mail.getMailSubject());


            String successPageUrl = applicationConfig.getSuccessPageUrl();
            mail.setMailBody(mail.getMailBody() + " " + applicationConfig.getSuccessPageUrl() + mail.getKey());

            msg.setText(mail.getMailBody());
  /*    String[] toMailAddress = mail.getToUserDirectList().stream().map(User::getEmailId).toArray(String[]::new);
        String[] ccMailAddress = mail.getToUserDirectList().stream().map(User::getEmailId).toArray(String[]::new);
        msg.setTo(toMailAddress);
        msg.setCc(ccMailAddress);
*/
            javaMailSender.send(msg);
        } catch (Exception ex) {
            LOGGER.error("Error is occurred javamailsender. Detail: ", ex);
        }
    }

    @Override
    public MailEntity saveMailEntity(MailEntity mailEntity) {
        return mailEntityRepository.save(mailEntity);
    }

    @Override
    public List<MailEntity> findAll() {
        return mailEntityRepository.findAll();
    }

    @Override
    public String generateUniqueKey() {
        UUID uniqueKey = UUID.randomUUID();
        return uniqueKey.toString();
    }

    @Override
    public MailEntity handleKeyEmailFromSuccessPage(MailEntity mailEntityParam) {
        MailEntity result = null;
        try {
            String key = mailEntityParam.getKey();
            String seenTime = mailEntityParam.getSeenTime();
            String toMailAdress = mailEntityParam.getToMailAdress();
            List<MailEntity> mailEntityList = mailEntityRepository.findByKey(key);
            if (!mailEntityList.isEmpty()) {
                MailEntity mailEntity = mailEntityList.get(0);
                mailEntity.setSeenTime(seenTime);
                // coming long
                long seenTimeLong = 0L;
                try {
                    seenTimeLong = Long.parseLong(mailEntityParam.getSeenTime());
                } catch (Exception ex) {
                    LOGGER.error("Error is occurred while converting seenTimeLong! Detail: ", ex);
                }
                long sendingTimeLon = Util.convertToMillis(mailEntity.getSendingTime());
                long differenceLong = seenTimeLong - sendingTimeLon;
                //TODO: difference sending understandable
                mailEntity.setDifference(differenceLong + "");
                mailEntity.setSeenTime(Util.convertToFormattedDate(seenTimeLong));
                result = saveMailEntity(mailEntity);
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred saving email success request! Detail:", ex);
        }
        return result;
    }

    @Override
    public void sendEmail(MailGroup mailGroup, Campaign campaign) {
        try {
            List<User> userList = mailGroup.getUserList();

            if (!userList.isEmpty()) {
                for (User user : userList) {
                    String uniqueKey = generateUniqueKey();
                    Mail mail = new Mail(user.getEmailId(), campaign.getMessageBody(), campaign.getName(), uniqueKey);
                    long sendingTime = System.currentTimeMillis();

                    sendEmail(mail);
                    MailEntity mailEntity = new MailEntity(mail.getToEmailAddress(),
                            Util.convertToFormattedDate(sendingTime),
                            null,
                            uniqueKey
                    );
                    saveMailEntity(mailEntity);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error is occurred while sending campaign mail. Detail: ", ex);
        }
    }
}
