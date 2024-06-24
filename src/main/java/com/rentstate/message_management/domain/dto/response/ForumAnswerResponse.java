package com.rentstate.message_management.domain.dto.response;

import com.rentstate.message_management.domain.model.entities.ForumAnswer;
import lombok.Data;

import java.util.Date;

@Data
public class ForumAnswerResponse {

    private Long id;
    private String content;
    private Long userId;
    private Long forumId;
    private Date createdAt;

    public ForumAnswerResponse (ForumAnswer forumAnswer) {
        this.id = forumAnswer.getId();
        this.content = forumAnswer.getContent();
        this.userId = forumAnswer.getUserId();
        this.forumId = forumAnswer.getForum().getId();
        this.createdAt = forumAnswer.getCreatedAt();
    }
}
