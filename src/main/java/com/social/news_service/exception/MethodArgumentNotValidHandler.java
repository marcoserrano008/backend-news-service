package com.social.news_service.exception;

import com.social.news_service.dto.response.ProblemDetail;
import com.social.news_service.util.Message;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Order( value = Ordered.HIGHEST_PRECEDENCE )
public class MethodArgumentNotValidHandler extends AbstractExceptionHandler<MethodArgumentNotValidException>{

    public MethodArgumentNotValidHandler(Message messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleException(MethodArgumentNotValidException ex) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("timestamp", Instant.now());
        properties.put("errors", getErrors(ex));
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), "Invalid request content.");
        problemDetail.setTitle(getMessage("badRequest.title"));
        problemDetail.setInstance(getInstance());
        problemDetail.setProperties(properties);
        problemDetail.setMessage(getMessage("input.fields.check"));
        return ResponseEntity.status(ex.getStatusCode()).body(problemDetail);
    }

    private List<Map<String, String>> getErrors(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errorsList = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, String> error = new HashMap<>();
            error.put("detail", fieldError.getDefaultMessage());
            error.put("pointer", "#/" + fieldError.getField());
            errorsList.add(error);
        });
        return errorsList;
    }
}
