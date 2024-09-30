package com.social.news_service.controller;

import com.social.news_service.dto.request.BulletinRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.service.bulletin.CreateBulletinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulletinCreateController {

    private final CreateBulletinService createBulletinService;

    public BulletinCreateController(CreateBulletinService createBulletinService) {
        this.createBulletinService = createBulletinService;
    }

    @PostMapping("/bulletins")
    public ResponseEntity<BulletinResponse> createBulletin(@ModelAttribute BulletinRequest request) {
        BulletinResponse bulletinResponse = createBulletinService.createBulletin(request);
        return ResponseEntity.ok(bulletinResponse);
    }
}
