package com.social.news_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BulletinRequest {

    @NotBlank(message = "Bulletin body is required")
    private String body;

    private List<MultipartFile> attachments;
}
