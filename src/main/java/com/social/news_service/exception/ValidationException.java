package com.social.news_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends GeneralException {

    private final String description;

    public ValidationException(String description) {
        super(HttpStatus.BAD_REQUEST);
        this.description = description;
    }
}
