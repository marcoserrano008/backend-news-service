package com.social.news_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralException extends RuntimeException {

    private final HttpStatus status;

    public GeneralException(HttpStatus status) {
        this.status = status;
    }
}

