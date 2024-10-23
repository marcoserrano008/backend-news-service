package com.social.news_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class BulletinRequest {

    @NotBlank(message = "Bulletin body is required")
    private String body;

    private List<MultipartFile> attachments;
}
