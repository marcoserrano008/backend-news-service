package com.social.news_service.exception;

import com.social.news_service.dto.response.ProblemDetail;
import com.social.news_service.util.Message;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class ValidationExceptionHandler extends AbstractExceptionHandler<ValidationException> {

    public ValidationExceptionHandler(Message messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ProblemDetail> handleException(ValidationException ex) {
        String title = "Illegal argument(s)";
        String detail = ex.getDescription();

        Map<String, Object> properties = new HashMap<>();
        properties.put("timestamp", Instant.now());
        properties.put("errorType", title);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), detail);
        problemDetail.setTitle(title);
        problemDetail.setInstance(getInstance());
        problemDetail.setProperties(properties);
        problemDetail.setMessage(detail);

        return ResponseEntity.status(ex.getStatus()).body(problemDetail);
    }
}
