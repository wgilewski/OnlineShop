package com.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@SpringBootApplication
public class OnlineShopApplication {


    public static void main(String[] args) {
        try {

            SpringApplication.run(OnlineShopApplication.class, args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Bean
    @Qualifier("myJavaMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setProtocol("smtp");
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("email");
        sender.setPassword("password");
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtps.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        sender.setJavaMailProperties(mailProperties);
        return sender;

    }

}
