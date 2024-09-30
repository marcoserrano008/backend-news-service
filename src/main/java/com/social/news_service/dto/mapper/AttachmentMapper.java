package com.social.news_service.dto.mapper;

import com.social.news_service.dto.request.AttachmentRequest;
import com.social.news_service.dto.response.AttachmentResponse;
import com.social.news_service.entity.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AttachmentMapper {

    AttachmentMapper mapper = Mappers.getMapper(AttachmentMapper.class);

    Attachment attachmentRequestToAttachment(AttachmentRequest attachmentRequest);

    AttachmentResponse attachmentToAttachmentResponse(Attachment attachment);

    @Named("attachmentsToAttachmentResponses")
    List<AttachmentResponse> attachmentsToAttachmentResponses(List<Attachment> attachments);

    List<Attachment> attachmentRequestsToAttachments(List<AttachmentRequest> attachmentRequests);
}
