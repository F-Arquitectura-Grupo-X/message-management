package com.rentstate.message_management.domain.service.impl;

import com.rentstate.message_management.domain.dto.request.MessageRequest;
import com.rentstate.message_management.domain.dto.response.Chat;
import com.rentstate.message_management.domain.dto.response.MessageResponse;
import com.rentstate.message_management.domain.model.entities.Message;
import com.rentstate.message_management.domain.service.MessageService;
import com.rentstate.message_management.infrastructure.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public MessageResponse sendMessage(MessageRequest messageRequest) {

        // VALIDAR SI USUARIOS EXISTEN
        Message message = new Message(messageRequest);

        messageRepository.save(message);
        return new MessageResponse(message);
    }


    @Override
    public List<Chat> getChats(Long userId) {
        Set<Message> allMessages = new HashSet<>();
        allMessages.addAll(messageRepository.findAllByReceiverId(userId));
        allMessages.addAll(messageRepository.findAllBySenderId(userId));

        Map<Long, List<MessageResponse>> chatMap = allMessages.stream()
                .collect(Collectors.groupingBy(
                        message -> message.getSenderId().equals(userId) ? message.getReceiverId() : message.getSenderId(),
                        Collectors.mapping(MessageResponse::new, Collectors.toList())
                ));

        List<Chat> chats = chatMap.entrySet().stream()
                .map(entry -> Chat.builder()
                        .chatName("user " + entry.getKey())
                        .messages(entry.getValue())
                        .build())
                .collect(Collectors.toList());

        return chats;
    }

}
