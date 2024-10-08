package com.social.news_service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {

    private boolean success;

    private String message;

    public CommonResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
