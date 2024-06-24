package com.rentstate.message_management.application;

import com.rentstate.message_management.domain.dto.request.ForumAnswerRequest;
import com.rentstate.message_management.domain.dto.request.ForumRequest;
import com.rentstate.message_management.domain.dto.response.ForumAnswerResponse;
import com.rentstate.message_management.domain.dto.response.ForumResponse;
import com.rentstate.message_management.domain.service.ForumAnswerService;
import com.rentstate.message_management.domain.service.ForumService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v17/forum")
public class ForumController {

    private final ForumService forumService;
    private final ForumAnswerService forumAnswerService;

    @PostMapping
    public ResponseEntity<ForumResponse> addForum(@RequestBody ForumRequest forumRequest) {
        ForumResponse forumResponse = forumService.addForum(forumRequest);
        if (forumResponse == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(forumResponse);
    }
    @GetMapping
    public ResponseEntity<List<ForumResponse>> getForums() {
        List<ForumResponse> forumResponseList = forumService.getAllForums();
        if (forumResponseList == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(forumResponseList);
    }

    @PostMapping("/answer")
    public ResponseEntity<ForumAnswerResponse> addAnswer(
            @RequestBody ForumAnswerRequest forumAnswerRequest) {

        ForumAnswerResponse forumAnswerResponse = forumAnswerService
                .createForumAnswer(forumAnswerRequest);

        if (forumAnswerResponse == null)
            return ResponseEntity.badRequest().body(forumAnswerResponse);

        return ResponseEntity.ok(forumAnswerResponse);
    }
    @GetMapping("/answer/forum/{forumId}")
    public ResponseEntity<List<ForumAnswerResponse>> getAnswersByForum(
            @PathVariable Long forumId) {
        List<ForumAnswerResponse>forumAnswerResponseList = forumAnswerService
                .getAllByForumId(forumId);

        if (forumAnswerResponseList == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(forumAnswerResponseList);
    }
}
