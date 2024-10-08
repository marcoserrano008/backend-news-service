package com.social.news_service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentResponse {

    private Long id;

    private UserResponse senderUser;

    private String content;

    private Integer repliesCounter;

    private LocalDateTime createdDate;

    private List<CommentResponse> replies;
}
