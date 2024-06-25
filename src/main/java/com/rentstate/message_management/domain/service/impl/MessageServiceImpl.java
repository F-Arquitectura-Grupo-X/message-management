package com.rentstate.message_management.domain.service.impl;

import com.rentstate.message_management.application.exceptions.NotFoundException;
import com.rentstate.message_management.client.UserClient;
import com.rentstate.message_management.domain.dto.request.MessageRequest;
import com.rentstate.message_management.domain.dto.request.UserDTO;
import com.rentstate.message_management.domain.dto.response.Chat;
import com.rentstate.message_management.domain.dto.response.MessageResponse;
import com.rentstate.message_management.domain.model.entities.Message;
import com.rentstate.message_management.domain.service.MessageService;
import com.rentstate.message_management.infrastructure.repository.MessageRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserClient userClient;

    @Override
    public MessageResponse sendMessage(MessageRequest messageRequest) {

        try {
            userClient.getUser(messageRequest.getReceiverId());
            userClient.getUser(messageRequest.getSenderId());

            Message message = new Message(messageRequest);

            messageRepository.save(message);
            return new MessageResponse(message);

        }catch (FeignException.NotFound e) {
            throw new NotFoundException("one of the users does not exist");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }


    @Override
    public List<Chat> getChats(Long userId) {
        Set<Message> allMessages = new HashSet<>();
        allMessages.addAll(messageRepository.findAllByReceiverId(userId));
        allMessages.addAll(messageRepository.findAllBySenderId(userId));

        Map<Long, List<MessageResponse>> chatMap = groupMessagesByUser(allMessages, userId);

        return chatMap.entrySet().stream()
                .map(entry -> Chat.builder()
                        .chatName(getChatName(entry.getKey()))
                        .messages(entry.getValue())
                        .build())
                .collect(Collectors.toList());
    }


    private Map<Long, List<MessageResponse>> groupMessagesByUser(Set<Message> allMessages, Long userId) {
        return allMessages.stream()
                .collect(Collectors.groupingBy(
                        message -> message.getSenderId().equals(userId) ? message.getReceiverId() : message.getSenderId(),
                        Collectors.mapping(MessageResponse::new, Collectors.toList())
                ));
    }

    private String getChatName(Long userId) {
        try {
            UserDTO user = userClient.getUser(userId);
            return user.getName() + " " + user.getLastName();
        } catch (FeignException.NotFound e) {
            return "anonymous";
        }
    }


}
