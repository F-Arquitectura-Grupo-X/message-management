package com.rentstate.message_management.infrastructure.repository;

import com.rentstate.message_management.domain.model.entities.ForumAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumAnswerRepository extends JpaRepository<ForumAnswer, Long> {

    List<ForumAnswer> findAllByForumId(Long forumId);
}
