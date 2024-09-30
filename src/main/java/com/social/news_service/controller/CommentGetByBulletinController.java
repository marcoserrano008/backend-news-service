package com.social.news_service.controller;

import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.service.comment.GetCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentGetByBulletinController {

    private final GetCommentService getCommentService;

    public CommentGetByBulletinController(GetCommentService getCommentService) {
        this.getCommentService = getCommentService;
    }

    @GetMapping("/bulletins/{bulletin-id}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByBulletinId(@PathVariable("bulletin-id") Long bulletinId) {
        List<CommentResponse> commentResponses = getCommentService.getCommentsByBulletinId(bulletinId);
        return ResponseEntity.ok(commentResponses);
    }
}
