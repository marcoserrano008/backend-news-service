package com.social.news_service.service.attachment;

import com.social.news_service.dto.response.FileResponse;
import com.social.news_service.entity.Attachment;
import com.social.news_service.entity.Bulletin;
import com.social.news_service.repository.AttachmentRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateAttachmentServiceImp implements  CreateAttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final RestTemplate restTemplate;

    public CreateAttachmentServiceImp(AttachmentRepository attachmentRepository, RestTemplate restTemplate) {
        this.attachmentRepository = attachmentRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Attachment createAttachment(MultipartFile file, Bulletin bulletin) {

        FileResponse fileResponse = uploadFile(file);
        String field = fileResponse.getId();

        Attachment attachment = new Attachment();
        attachment.setAccountId(bulletin.getAccountId());
        attachment.setField(field);
        attachment.setName(fileResponse.getName());
        attachment.setSize(fileResponse.getSize());
        attachment.setMimeType(fileResponse.getMimeType());
        attachment.setBulletin(bulletin);

        return attachmentRepository.save(attachment);
    }

    private FileResponse uploadFile(MultipartFile file) {
        String url = "http://localhost:8092/file";

        HttpHeaders headers =  new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("multipartFile", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<FileResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                FileResponse.class
        );

        return response.getBody();
    }
}

