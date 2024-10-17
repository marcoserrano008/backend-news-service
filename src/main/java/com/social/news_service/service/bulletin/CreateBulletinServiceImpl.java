package com.social.news_service.service.bulletin;

import com.social.news_service.dto.mapper.BulletinMapper;
import com.social.news_service.dto.request.BulletinRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.entity.Attachment;
import com.social.news_service.entity.Bulletin;
import com.social.news_service.repository.BulletinRepository;
import com.social.news_service.service.attachment.CreateAttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateBulletinServiceImpl implements CreateBulletinService {

    private final BulletinRepository bulletinRepository;

    private final CreateAttachmentService createAttachmentService;

    public CreateBulletinServiceImpl(BulletinRepository bulletinRepository,
                                     CreateAttachmentService createAttachmentService) {
        this.bulletinRepository = bulletinRepository;
        this.createAttachmentService = createAttachmentService;
    }

    @Override
    @Transactional
    public BulletinResponse createBulletin(BulletinRequest request, Long userId) {

        Bulletin bulletin = new Bulletin();
        bulletin.setAccountId(userId);
        bulletin.setSenderUserId(userId);
        bulletin.setBody(request.getBody());
        bulletin.setCommentsCounter(0);
        bulletinRepository.save(bulletin);

        if (request.getAttachments() != null && !request.getAttachments().isEmpty()) {
            for (MultipartFile file : request.getAttachments()) {
                Attachment attachment = createAttachmentService.createAttachment(file, bulletin);
                bulletin.getAttachments().add(attachment);
            }
        }

        bulletinRepository.save(bulletin);

        return BulletinMapper.mapper.bulletinToBulletinResponse(bulletin);
    }
}
