package com.rentstate.message_management.domain.service.impl;

import com.rentstate.message_management.domain.dto.request.ForumRequest;
import com.rentstate.message_management.domain.dto.response.ForumResponse;
import com.rentstate.message_management.domain.model.entities.Forum;
import com.rentstate.message_management.domain.service.ForumService;
import com.rentstate.message_management.infrastructure.repository.ForumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForumServiceImpl implements ForumService {

    private final ForumRepository forumRepository;


    @Override
    public ForumResponse addForum(ForumRequest forumRequest) {

        //VALIDAR QUE EL USUARIO EXISTA

        Forum forum = new Forum(forumRequest);

        forumRepository.save(forum);
        return new ForumResponse(forum);
    }

    @Override
    public List<ForumResponse> getAllForums() {
        return forumRepository.findAll().stream().map(ForumResponse::new).toList();
    }
}
