package com.rentstate.message_management.domain.dto.request;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Integer age;
    private String gender;
    private String description;
    private Boolean isPremium;
    private String photoUrl;
    private Double rating;
}

