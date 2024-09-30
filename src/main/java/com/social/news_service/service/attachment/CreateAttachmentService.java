package com.social.news_service.service.attachment;

import com.social.news_service.entity.Attachment;
import com.social.news_service.entity.Bulletin;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CreateAttachmentService {

    Attachment createAttachment(MultipartFile file, Bulletin bulletin);
}
