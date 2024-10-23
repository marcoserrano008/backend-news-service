package com.social.news_service.controller;

import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.service.comment.CreateCommentService;
import com.social.news_service.service.user.GetUserIdFromTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CommentCreateController {

    private final CreateCommentService createCommentService;

    private final GetUserIdFromTokenService getUserIdFromTokenService;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public CommentCreateController(CreateCommentService createCommentService,
                                   GetUserIdFromTokenService getUserIdFromTokenService,
                                   SimpMessagingTemplate simpMessagingTemplate) {
        this.createCommentService = createCommentService;
        this.getUserIdFromTokenService = getUserIdFromTokenService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Operation(summary = "Create a comment on a bulletin",
            description = "Add a comment to a bulletin and broadcast it via WebSocket.")
    @PostMapping("/bulletins/{bulletin-id}/comments")
    public ResponseEntity<CommentResponse> createComment(@Valid @PathVariable("bulletin-id") Long bulletinId, @RequestBody CommentRequest request) {
        Long userId = getUserIdFromTokenService.getCurrentUserId();
        CommentResponse commentResponse = createCommentService.createComment(request, bulletinId, userId);

        simpMessagingTemplate.convertAndSend("/topic/comments/" + bulletinId, commentResponse);
        return ResponseEntity.ok(commentResponse);
    }
}
