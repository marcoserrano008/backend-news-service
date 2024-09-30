package com.social.news_service.service.attachment;

import com.social.news_service.entity.Attachment;
import com.social.news_service.entity.Bulletin;
import com.social.news_service.repository.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class CreateAttachmentServiceImp implements  CreateAttachmentService {

    private final AttachmentRepository attachmentRepository;

    public CreateAttachmentServiceImp(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment createAttachment(MultipartFile file, Bulletin bulletin) {
        String field = String.format("id_%s", UUID.randomUUID());

        Attachment attachment = new Attachment();
        attachment.setAccountId(bulletin.getAccountId());
        attachment.setField(field);
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setMimeType(file.getContentType());
        attachment.setBulletin(bulletin);

        return attachmentRepository.save(attachment);
    }


}
