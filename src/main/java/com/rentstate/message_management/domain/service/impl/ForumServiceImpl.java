package com.rentstate.message_management.domain.service.impl;

import com.rentstate.message_management.application.exceptions.NotFoundException;
import com.rentstate.message_management.client.UserClient;
import com.rentstate.message_management.domain.dto.request.ForumRequest;
import com.rentstate.message_management.domain.dto.response.ForumResponse;
import com.rentstate.message_management.domain.model.entities.Forum;
import com.rentstate.message_management.domain.service.ForumService;
import com.rentstate.message_management.infrastructure.repository.ForumRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForumServiceImpl implements ForumService {

    private final ForumRepository forumRepository;
    private final UserClient userClient;


    @Override
    public ForumResponse addForum(ForumRequest forumRequest) {

        try{
            userClient.getUser(forumRequest.getUserId());

            Forum forum = new Forum(forumRequest);

            forumRepository.save(forum);
            return new ForumResponse(forum);

        }catch (FeignException.NotFound e) {
            throw new NotFoundException
                    ("User with id "+forumRequest.getUserId()+" not found");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }

    }

    @Override
    public List<ForumResponse> getAllForums() {
        return forumRepository.findAll().stream().map(ForumResponse::new).toList();
    }
}
