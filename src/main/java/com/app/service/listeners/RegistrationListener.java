package com.app.service.listeners;

import com.app.model.user.User;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private UserService userService;
    private JavaMailSender javaMailSender;

    public RegistrationListener(UserService userService, @Qualifier("myJavaMailSender") JavaMailSender javaMailSender) {
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientEmail = user.getEmail();
        String subject = "APP - REGISTRATION CONFIRMATION";
        String confirmationURL = event.getAppURL() + "user/registrationConfirmation?token=" + token;
        String message = "CLICK ACTIVATION LINK: " + confirmationURL;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
    }
}
