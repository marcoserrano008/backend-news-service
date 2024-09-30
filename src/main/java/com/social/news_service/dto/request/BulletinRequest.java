package com.social.news_service.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BulletinRequest {

    private Long accountId;

    private Long senderUserId;

    private String body;

    private List<MultipartFile> attachments;
}
