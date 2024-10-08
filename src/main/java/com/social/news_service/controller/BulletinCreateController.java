package com.social.news_service.controller;

import com.social.news_service.dto.request.BulletinRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.service.bulletin.CreateBulletinService;
import com.social.news_service.service.user.GetUserIdFromTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulletinCreateController {

    private final CreateBulletinService createBulletinService;

    private final GetUserIdFromTokenService getUserIdFromTokenService;

    public BulletinCreateController(CreateBulletinService createBulletinService,
                                    GetUserIdFromTokenService getUserIdFromTokenService) {
        this.createBulletinService = createBulletinService;
        this.getUserIdFromTokenService = getUserIdFromTokenService;
    }

    @PostMapping("/bulletins")
    public ResponseEntity<BulletinResponse> createBulletin(@Valid @ModelAttribute BulletinRequest request) {
        Long userId = getUserIdFromTokenService.getCurrentUserId();
        BulletinResponse bulletinResponse = createBulletinService.createBulletin(request, userId);

        return ResponseEntity.ok(bulletinResponse);
    }
}
