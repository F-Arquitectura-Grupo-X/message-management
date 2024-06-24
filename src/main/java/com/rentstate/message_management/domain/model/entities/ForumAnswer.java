package com.rentstate.message_management.domain.model.entities;

import com.rentstate.message_management.domain.dto.request.ForumAnswerRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "forum_answers")
public class ForumAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forum;

    @NotNull
    private Date createdAt;

    public ForumAnswer(ForumAnswerRequest forumAnswerRequest, Forum forum) {
        this.content = forumAnswerRequest.getContent();
        this.userId = forumAnswerRequest.getUserId();
        this.createdAt = new Date();
        this.forum = forum;
    }

}
