package com.rentstate.message_management.domain.dto.response;

import com.rentstate.message_management.domain.model.entities.Message;
import lombok.Data;

import java.util.Date;

@Data
public class MessageResponse {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private String content;
    private Date sentAt;

    public MessageResponse(Message message) {
        this.id = message.getId();
        this.senderId = message.getSenderId();
        this.receiverId = message.getReceiverId();
        this.content = message.getContent();
        this.sentAt = message.getSentAt();
    }
}
