package com.social.news_service.controller;

import com.social.news_service.dto.response.CommentResponse;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class CommentWebSocketController {

    @MessageMapping("/createComment")
    @SendTo("/topic/comments/{bulletinId}")
    public CommentResponse broadcastComment(@DestinationVariable Long bulletinId, CommentResponse commentResponse) {
        System.out.println("broadcastComment");
        return commentResponse;
    }
}
