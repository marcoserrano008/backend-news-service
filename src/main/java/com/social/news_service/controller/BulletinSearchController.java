package com.social.news_service.controller;

import com.social.news_service.dto.request.BulletinSearchRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.service.bulletin.GetBulletinService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulletinSearchController {

    private final GetBulletinService getBulletinService;


    public BulletinSearchController(GetBulletinService getBulletinService) {
        this.getBulletinService = getBulletinService;
    }

    @GetMapping("/bulletins")
    public ResponseEntity<Page<BulletinResponse>> getBulletins(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection,
            @RequestParam(required = false) String body,
            @RequestParam(required = false) Long accountId,
            @RequestParam(required = false) Long senderUserId) {
        BulletinSearchRequest request = new BulletinSearchRequest(page, size, sortBy, sortDirection, body, accountId, senderUserId);
        Page<BulletinResponse> response = this.getBulletinService.getBulletins(request);

        return ResponseEntity.ok(response);
    }
}
