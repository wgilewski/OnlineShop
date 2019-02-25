package com.app.controllers;

import com.app.model.message.Message;
import com.app.service.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.stream.Collectors;

@Controller
public class MessageController {

    private MessageService messageService;
    private JavaMailSender javaMailSender;

    public MessageController(MessageService messageService, @Qualifier("myJavaMailSender") JavaMailSender javaMailSender) {
        this.messageService = messageService;
        this.javaMailSender = javaMailSender;
    }

    //Admin
    @GetMapping("/admin/messageList")
    public String messagesGet(Model model) {
        model.addAttribute("messages", messageService.findAllMessages());
        return "correspondence/messageList";
    }

    @GetMapping("/admin/deleteMessage/{id}")
    public String deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return "redirect:/admin/messageList";
    }

    @GetMapping("/admin/replyMessage/{id}")
    public String replyMessageGet(@PathVariable Long id, Model model) {
        Message message = messageService.findOneMessage(id);
        model.addAttribute("message", message);
        return "correspondence/answerMessage";
    }

    @PostMapping("/admin/answerMessage")
    public String replyMessagePost(@ModelAttribute Message message) {

        String subject = "Opowiedź na pytanie. Sklep AllPhones";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(message.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message.getReplyMessage());
        javaMailSender.send(simpleMailMessage);
        Message message1 = messageService.findOneMessage(message.getId());
        message1.setReplyMessage(message.getReplyMessage());
        message1.setStatus(true);
        messageService.updateMessage(message1);

        return "redirect:/admin/messageList";
    }

    @GetMapping("/admin/message/{id}")
    public String message(@PathVariable Long id, Model model) {
        model.addAttribute("message", messageService.findOneMessage(id));
        return "correspondence/message";
    }


    //USER
    @GetMapping("/contact")
    public String contactGet(Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("errors", new HashMap<>());
        return "unLogged/googleMap";
    }

    @PostMapping("/contact")
    public String contactPost(@Valid @ModelAttribute Message message, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", message);
            model.addAttribute("errors", bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField,
                            FieldError::getDefaultMessage,
                            (m1, m2) -> m1 + ", " + m2)));

            return "unLogged/googleMap";
        }
        messageService.addMessage(message);
        return "/index";
    }


}
