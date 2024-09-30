package com.social.news_service.dto.request;

import lombok.Data;

@Data
public class AttachmentRequest {

    private Long accountId;

    private Long bulletinId;

    private String field;
}
