package com.social.news_service.exception;

import com.social.news_service.dto.response.ProblemDetail;
import com.social.news_service.util.Message;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;

public abstract class AbstractExceptionHandler<T extends Exception> {

    private final Message messageSource;

    protected AbstractExceptionHandler(Message messageSource) {
        this.messageSource = messageSource;
    }

    protected abstract ResponseEntity<ProblemDetail> handleException(T t);

    protected URI getInstance() {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        return URI.create(request.getRequestURL().toString());
    }

    protected String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args);
    }
}