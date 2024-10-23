package com.social.news_service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommentResponse {

    private Long id;

    private UserResponse senderUser;

    private String content;

    private Integer repliesCounter;

    private LocalDateTime createdDate;

    private Long parentCommentId;

    private List<CommentResponse> replies;
}
