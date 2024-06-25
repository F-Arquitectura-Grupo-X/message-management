package com.rentstate.message_management.domain.service.impl;

import com.rentstate.message_management.application.exceptions.NotFoundException;
import com.rentstate.message_management.client.UserClient;
import com.rentstate.message_management.domain.dto.request.ForumAnswerRequest;
import com.rentstate.message_management.domain.dto.response.ForumAnswerResponse;
import com.rentstate.message_management.domain.model.entities.Forum;
import com.rentstate.message_management.domain.model.entities.ForumAnswer;
import com.rentstate.message_management.domain.service.ForumAnswerService;
import com.rentstate.message_management.infrastructure.repository.ForumAnswerRepository;
import com.rentstate.message_management.infrastructure.repository.ForumRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ForumAnswerServiceImpl implements ForumAnswerService {

    private final ForumAnswerRepository forumAnswerRepository;
    private final ForumRepository forumRepository;
    private final UserClient userClient;

    @Override
    public ForumAnswerResponse createForumAnswer(ForumAnswerRequest forumAnswerRequest) {

        Optional<Forum> forum = forumRepository.findById(forumAnswerRequest.getForumId());
        if(forum.isEmpty()) return null;

        try{
            userClient.getUser(forumAnswerRequest.getUserId());

            ForumAnswer forumAnswer = new ForumAnswer(forumAnswerRequest, forum.get());
            forumAnswerRepository.save(forumAnswer);

            return new ForumAnswerResponse(forumAnswer);

        }catch (FeignException.NotFound e) {
            throw new NotFoundException
                    ("User with id "+forumAnswerRequest.getUserId()+" not found");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }


    }

    @Override
    public List<ForumAnswerResponse> getAllByForumId(Long forumId) {

        if(!forumRepository.existsById(forumId)) return null;

        List<ForumAnswer> forumAnswers = forumAnswerRepository.findAllByForumId(forumId);

        return forumAnswers.stream().map(ForumAnswerResponse::new).toList();
    }
}
