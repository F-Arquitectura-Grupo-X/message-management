package com.rentstate.message_management.domain.service;

import com.rentstate.message_management.domain.dto.request.ForumRequest;
import com.rentstate.message_management.domain.dto.response.ForumResponse;

import java.util.List;

public interface ForumService{

    ForumResponse addForum(ForumRequest forumRequest);
    List<ForumResponse> getAllForums();

}
