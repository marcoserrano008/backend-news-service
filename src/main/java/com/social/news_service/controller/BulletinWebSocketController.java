package com.social.news_service.controller;

import com.social.news_service.dto.response.BulletinResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BulletinWebSocketController {

    @MessageMapping("/createBulletin")
    @SendTo("/topic/bulletins")
    public BulletinResponse broadcastBulletin(BulletinResponse bulletinResponse) {
        System.out.println("broadcastingBulletin" + " " + bulletinResponse.getBody());
        return bulletinResponse;
    }
}
