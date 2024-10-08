package com.social.news_service.service.user;

import com.social.news_service.security.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class GetUserIdFromTokenServiceImp implements GetUserIdFromTokenService {

    private final JwtTokenUtil jwtTokenUtil;

    public GetUserIdFromTokenServiceImp(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        return jwtTokenUtil.getUserIdFromToken(token);
    }
}
