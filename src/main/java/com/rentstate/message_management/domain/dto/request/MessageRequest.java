package com.rentstate.message_management.domain.dto.request;

import lombok.Data;

@Data
public class MessageRequest {

    private Long senderId;
    private Long receiverId;
    private String content;
}
