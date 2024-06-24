package com.rentstate.message_management.application;

import com.rentstate.message_management.domain.dto.request.MessageRequest;
import com.rentstate.message_management.domain.dto.response.Chat;
import com.rentstate.message_management.domain.dto.response.MessageResponse;
import com.rentstate.message_management.domain.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/message")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageResponse> createMessage(@RequestBody MessageRequest messageRequest) {
        MessageResponse messageResponse = messageService.sendMessage(messageRequest);
        if (messageResponse != null) {
            return ResponseEntity.ok(messageResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("chats/{userId}")
    public ResponseEntity<List<Chat>> getChats(@PathVariable Long userId) {
        List<Chat> messageResponses = messageService.getChats(userId);
        if (messageResponses != null) {
            return ResponseEntity.ok(messageResponses);
        }
        return ResponseEntity.badRequest().build();
    }
}
