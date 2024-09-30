package com.social.news_service.dto.request;

import lombok.Data;

@Data
public class CommentRequest {

    private Long bulletinId;

    private Long parentCommentId;

    private Long senderUserId;

    private String content;
}
