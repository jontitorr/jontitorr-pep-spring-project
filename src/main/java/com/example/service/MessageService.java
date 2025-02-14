package com.example.service;

import com.example.repository.MessageRepository;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository,
                         AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Message createMessage(Message message) {
        if (message.getMessageText().isBlank() ||
            message.getMessageText().length() > 255 ||
            !accountRepository.existsById(message.getPostedBy())) {
            return null;
        }
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    @Transactional
    public int deleteMessage(Integer messageId) {
        return messageRepository.deleteByMessageId(messageId);
    }

    @Transactional
    public int updateMessage(Integer messageId, String newText) {
        if (newText == null || newText.isBlank() || newText.length() > 255) {
            return 0;
        }
        return messageRepository.updateMessageText(messageId, newText);
    }

    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
}
