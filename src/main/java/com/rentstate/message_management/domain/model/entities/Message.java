package com.rentstate.message_management.domain.model.entities;

import com.rentstate.message_management.domain.dto.request.MessageRequest;
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
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long senderId;

    @NotNull
    private Long receiverId;

    @NotNull
    private String content;

    @NotNull
    private Date sentAt;

    public Message(MessageRequest messageRequest){
        this.senderId = messageRequest.getSenderId();
        this.receiverId = messageRequest.getReceiverId();
        this.content = messageRequest.getContent();
        this.sentAt = new Date();
    }
}
