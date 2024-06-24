package com.rentstate.message_management.domain.dto.request;

import com.rentstate.message_management.domain.model.entities.Forum;
import lombok.Data;

@Data
public class ForumAnswerRequest {

    private String content;
    private Long userId;
    private Long forumId;

}
