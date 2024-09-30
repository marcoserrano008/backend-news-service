package com.social.news_service.service.bulletin;

import com.social.news_service.dto.request.BulletinSearchRequest;
import com.social.news_service.dto.response.BulletinResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface GetBulletinService {

    Page<BulletinResponse> getBulletins(BulletinSearchRequest request);
}
