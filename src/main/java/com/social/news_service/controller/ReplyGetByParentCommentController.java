package com.social.news_service.controller;

import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.service.reply.GetReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyGetByParentCommentController {

    private final GetReplyService getReplyService;

    public ReplyGetByParentCommentController(GetReplyService getReplyService) {
        this.getReplyService = getReplyService;
    }

    @GetMapping("/comments/{comment-id}/replies")
    public ResponseEntity<List<CommentResponse>> getRepliesByCommentId(@PathVariable("comment-id") Long commentId) {
        List<CommentResponse> response = getReplyService.getRepliesByCommentId(commentId);
        return ResponseEntity.ok(response);
    }
}
