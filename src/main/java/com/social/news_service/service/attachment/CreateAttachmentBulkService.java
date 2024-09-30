package com.social.news_service.service.attachment;

import com.social.news_service.dto.request.AttachmentRequest;
import com.social.news_service.dto.response.AttachmentResponse;

import java.util.List;

public interface CreateAttachmentBulkService {

    List<AttachmentResponse> createAttachmentBulk(List<AttachmentRequest> attachmentRequests);
}
