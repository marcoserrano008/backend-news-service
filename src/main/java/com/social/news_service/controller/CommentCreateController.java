package com.social.news_service.controller;

import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.service.comment.CreateCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentCreateController {

    private final CreateCommentService createCommentService;

    public CommentCreateController(CreateCommentService createCommentService) {
        this.createCommentService = createCommentService;
    }

    @PostMapping("/bulletins/comments")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest request) {
        CommentResponse commentResponse = createCommentService.createComment(request);
        return ResponseEntity.ok(commentResponse);
    }
}
