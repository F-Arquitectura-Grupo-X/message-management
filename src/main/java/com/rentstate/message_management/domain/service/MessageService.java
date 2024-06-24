package com.rentstate.message_management.domain.service;


import com.rentstate.message_management.domain.dto.request.MessageRequest;
import com.rentstate.message_management.domain.dto.response.Chat;
import com.rentstate.message_management.domain.dto.response.MessageResponse;

import java.util.List;

public interface MessageService {

    MessageResponse sendMessage(MessageRequest messageRequest);
    List<Chat> getChats(Long userId);

}
