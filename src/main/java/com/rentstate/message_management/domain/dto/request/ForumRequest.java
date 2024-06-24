package com.rentstate.message_management.domain.dto.request;

import lombok.Data;

@Data
public class ForumRequest {

    private String question;
    private Long userId;

}
