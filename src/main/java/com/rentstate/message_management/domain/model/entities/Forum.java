package com.rentstate.message_management.domain.model.entities;

import com.rentstate.message_management.domain.dto.request.ForumRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "forums")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String question;

    @NotNull
    private Long userId;

    @NotNull
    private Date createdAt;

    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ForumAnswer> answers;

    public Forum(ForumRequest forumRequest) {
        question = forumRequest.getQuestion();
        userId = forumRequest.getUserId();
        createdAt = new Date();
        answers = new ArrayList<>();
    }

}
