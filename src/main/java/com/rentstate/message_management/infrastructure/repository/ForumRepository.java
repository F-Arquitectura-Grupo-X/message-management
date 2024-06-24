package com.rentstate.message_management.infrastructure.repository;

import com.rentstate.message_management.domain.model.entities.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long> {

}
