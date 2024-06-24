package com.rentstate.message_management.domain.service;

import com.rentstate.message_management.domain.dto.request.ForumAnswerRequest;
import com.rentstate.message_management.domain.dto.response.ForumAnswerResponse;

import java.util.List;

public interface ForumAnswerService {

    ForumAnswerResponse createForumAnswer(ForumAnswerRequest forumAnswerRequest);
    List<ForumAnswerResponse> getAllByForumId(Long forumId);
}
