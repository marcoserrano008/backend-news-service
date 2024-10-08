package com.social.news_service.service.user;

import com.social.news_service.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface GetUserService {
    UserResponse getUserById(Long id);
}
