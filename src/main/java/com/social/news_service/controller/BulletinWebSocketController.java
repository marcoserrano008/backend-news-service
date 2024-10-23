package com.social.news_service.controller;

import com.social.news_service.dto.response.BulletinResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BulletinWebSocketController {

    @Operation(summary = "Broadcast bulletin via WebSocket",
            description = "Send a bulletin to all WebSocket subscribers on `/topic/bulletins`.")
    @MessageMapping("/createBulletin")
    @SendTo("/topic/bulletins")
    public BulletinResponse broadcastBulletin(BulletinResponse bulletinResponse) {
        return bulletinResponse;
    }
}
