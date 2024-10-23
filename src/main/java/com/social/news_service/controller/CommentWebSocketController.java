package com.social.news_service.controller;

import com.social.news_service.dto.response.CommentResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class CommentWebSocketController {

    @Operation(summary = "Broadcast comment via WebSocket",
            description = "Send a comment to all WebSocket subscribers on `/topic/comments/{bulletinId}`.")
    @MessageMapping("/createComment")
    @SendTo("/topic/comments/{bulletinId}")
    public CommentResponse broadcastComment(@DestinationVariable Long bulletinId, CommentResponse commentResponse) {
        return commentResponse;
    }
}
