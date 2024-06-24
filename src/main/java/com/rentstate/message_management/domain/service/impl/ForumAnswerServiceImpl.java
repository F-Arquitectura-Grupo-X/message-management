package com.rentstate.message_management.domain.service.impl;

import com.rentstate.message_management.domain.dto.request.ForumAnswerRequest;
import com.rentstate.message_management.domain.dto.response.ForumAnswerResponse;
import com.rentstate.message_management.domain.model.entities.Forum;
import com.rentstate.message_management.domain.model.entities.ForumAnswer;
import com.rentstate.message_management.domain.service.ForumAnswerService;
import com.rentstate.message_management.infrastructure.repository.ForumAnswerRepository;
import com.rentstate.message_management.infrastructure.repository.ForumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ForumAnswerServiceImpl implements ForumAnswerService {

    private final ForumAnswerRepository forumAnswerRepository;
    private final ForumRepository forumRepository;

    @Override
    public ForumAnswerResponse createForumAnswer(ForumAnswerRequest forumAnswerRequest) {

        Optional<Forum> forum = forumRepository.findById(forumAnswerRequest.getForumId());
        if(forum.isEmpty()) return null;

        //VALIDAR SI USUARIO EXISTE

        ForumAnswer forumAnswer = new ForumAnswer(forumAnswerRequest, forum.get());
        forumAnswerRepository.save(forumAnswer);

        return new ForumAnswerResponse(forumAnswer);
    }

    @Override
    public List<ForumAnswerResponse> getAllByForumId(Long forumId) {

        if(!forumRepository.existsById(forumId)) return null;

        List<ForumAnswer> forumAnswers = forumAnswerRepository.findAllByForumId(forumId);

        return forumAnswers.stream().map(ForumAnswerResponse::new).toList();
    }
}
