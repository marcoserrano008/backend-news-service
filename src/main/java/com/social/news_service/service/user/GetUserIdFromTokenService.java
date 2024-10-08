package com.social.news_service.service.user;

import org.springframework.stereotype.Service;

@Service
public interface GetUserIdFromTokenService {
    Long getCurrentUserId();
}
