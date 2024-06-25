package com.rentstate.message_management.client;

import com.rentstate.message_management.domain.dto.request.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-userManagement")
public interface UserClient {

    @GetMapping("/{userId}")
    UserDTO getUser(@PathVariable Long userId);

}
