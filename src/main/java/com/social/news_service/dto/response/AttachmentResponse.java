package com.social.news_service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachmentResponse {

    private Long id;

    private String field;

    private Long size;

    private String name;

    private String mimeType;
}
