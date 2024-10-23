package com.social.news_service.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BulletinResponse {

    private Long id;

    private Long accountId;

    private UserResponse senderUser;

    private String body;

    private LocalDateTime createdDate;

    private Integer commentsCounter;

    private List<AttachmentResponse> attachments;
}
