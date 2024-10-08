package com.social.news_service.service.user;

import com.social.news_service.dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GetUserServiceImp implements  GetUserService {

    private final RestTemplate restTemplate;

    public GetUserServiceImp(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserResponse getUserById(Long id) {
        String url = String.format("http://localhost:8091/users/%s", id);
        return restTemplate.getForObject(url, UserResponse.class);
    }
}
