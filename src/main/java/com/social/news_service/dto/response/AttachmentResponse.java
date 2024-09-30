package com.social.news_service.dto.response;

import lombok.Data;

@Data
public class AttachmentResponse {

    private Long id;

    private String field;

    private Long size;

    private String name;

    private String mimeType;
}
