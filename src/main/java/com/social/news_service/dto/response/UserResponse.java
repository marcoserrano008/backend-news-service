package com.social.news_service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;

    private Long accountId;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDateTime createdDate;
}
