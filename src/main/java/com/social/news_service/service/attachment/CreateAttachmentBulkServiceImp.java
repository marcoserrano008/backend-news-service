package com.social.news_service.service.attachment;

import com.social.news_service.dto.mapper.AttachmentMapper;
import com.social.news_service.dto.request.AttachmentRequest;
import com.social.news_service.dto.response.AttachmentResponse;
import com.social.news_service.entity.Attachment;
import com.social.news_service.repository.AttachmentRepository;

import java.util.List;

public class CreateAttachmentBulkServiceImp implements  CreateAttachmentBulkService {

    private final AttachmentRepository attachmentRepository;

    public CreateAttachmentBulkServiceImp(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public List<AttachmentResponse> createAttachmentBulk(List<AttachmentRequest> request) {

        List<Attachment> attachments = AttachmentMapper.mapper.attachmentRequestsToAttachments(request);
        return null;
    }
}
