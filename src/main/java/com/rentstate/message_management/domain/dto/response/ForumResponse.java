package com.rentstate.message_management.domain.dto.response;

import com.rentstate.message_management.domain.model.entities.Forum;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ForumResponse {

    private Long id;
    private String question;
    private Long userId;
    private Date createdAt;
    private List<ForumAnswerResponse> answers;

    public ForumResponse(Forum forum) {
        this.id = forum.getId();
        this.question = forum.getQuestion();
        this.userId = forum.getUserId();
        this.createdAt = forum.getCreatedAt();
        this.answers = forum.getAnswers().stream().map(ForumAnswerResponse::new).toList();
    }
}
