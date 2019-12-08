package com.picussecurity.mailapp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created volkanulutas on 07.12.2019.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfig {

    @Value("${spring.mail.username}")
    private String adminMailAddress;

    @Value("${email.success.page.url}")
    private String successPageUrl;

    public String getAdminMailAddress() {
        return adminMailAddress;
    }

    public String getSuccessPageUrl() {
        return successPageUrl;
    }
}
