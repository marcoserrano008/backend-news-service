package com.social.news_service.controller;

import com.social.news_service.dto.request.BulletinRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.service.bulletin.CreateBulletinService;
import com.social.news_service.service.user.GetUserIdFromTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulletinCreateController {

    private final CreateBulletinService createBulletinService;

    private final GetUserIdFromTokenService getUserIdFromTokenService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public BulletinCreateController(CreateBulletinService createBulletinService,
                                    GetUserIdFromTokenService getUserIdFromTokenService,
                                    SimpMessagingTemplate simpMessagingTemplate) {
        this.createBulletinService = createBulletinService;
        this.getUserIdFromTokenService = getUserIdFromTokenService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Operation(summary = "Create a bulletin",
            description = "Submit a new bulletin and broadcast it via WebSocket.")
    @PostMapping("/bulletins")
    public ResponseEntity<BulletinResponse> createBulletin(@Valid @ModelAttribute BulletinRequest request) {
        Long userId = getUserIdFromTokenService.getCurrentUserId();
        BulletinResponse bulletinResponse = createBulletinService.createBulletin(request, userId);
        simpMessagingTemplate.convertAndSend("/topic/bulletins", bulletinResponse);

        return ResponseEntity.ok(bulletinResponse);
    }
}
