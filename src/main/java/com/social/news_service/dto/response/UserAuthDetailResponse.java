package com.social.news_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserAuthDetailResponse {

    private String username;

    private String password;

    private List<String> authorities;
}