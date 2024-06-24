package com.rentstate.message_management.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Chat{

    private String chatName;
    private List<MessageResponse> messages;

}
