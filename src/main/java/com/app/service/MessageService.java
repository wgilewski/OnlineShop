package com.app.service;

import com.app.model.message.Message;
import com.app.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addMessage(Message message) {
        message.setName(message.getName().toUpperCase());
        message.setSurname(message.getSurname().toUpperCase());
        messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }

    public Message findOneMessage(Long id) {
        return messageRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public void updateMessage(Message message) {
        messageRepository.save(message);
    }


}
