package com.social.news_service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {

    private Long id;

    private Long accountId;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDateTime createdDate;
}
