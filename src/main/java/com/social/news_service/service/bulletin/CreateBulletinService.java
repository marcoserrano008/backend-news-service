package com.social.news_service.service.bulletin;

import com.social.news_service.dto.request.BulletinRequest;
import com.social.news_service.dto.response.BulletinResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreateBulletinService {

    BulletinResponse createBulletin(BulletinRequest request, Long userId);
}
