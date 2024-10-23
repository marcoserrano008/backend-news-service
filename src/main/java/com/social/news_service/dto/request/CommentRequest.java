package com.social.news_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long parentCommentId;

    @NotNull(message = "Content cannot be null")
    @Size(min = 1, max = 500, message = "Content must be between 1 and 500 characters")
    private String content;
}
