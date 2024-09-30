package com.social.news_service.controller;

import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.service.reply.CreateReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyCreateController {

    private final CreateReplyService createReplyService;

    public ReplyCreateController(CreateReplyService createReplyService) {
        this.createReplyService = createReplyService;
    }

    @PostMapping("/comments/replies")
    public ResponseEntity<CommentResponse> createReply(@RequestBody CommentRequest request) {
        CommentResponse commentResponse = this.createReplyService.createReply(request);
        return ResponseEntity.ok(commentResponse);
    }
}
