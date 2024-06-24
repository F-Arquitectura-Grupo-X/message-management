package com.rentstate.message_management.infrastructure.repository;

import com.rentstate.message_management.domain.model.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderId(Long id);
    List<Message> findAllByReceiverId(Long id);

}
