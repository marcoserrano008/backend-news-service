package com.social.news_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserAuthDetailResponse {

    private String username;

    private String password;

    private List<String> authorities;
}