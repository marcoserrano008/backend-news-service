package com.social.news_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentRequest {

    private Long accountId;

    private Long bulletinId;

    private String field;
}
