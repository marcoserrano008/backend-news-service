package com.social.news_service.service.comment;

import com.social.news_service.dto.mapper.CommentMapper;
import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.entity.Bulletin;
import com.social.news_service.entity.Comment;
import com.social.news_service.repository.BulletinRepository;
import com.social.news_service.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentServiceImpl implements CreateCommentService {

    private final CommentRepository commentRepository;

    private final BulletinRepository bulletinRepository;


    public CreateCommentServiceImpl(CommentRepository commentRepository, BulletinRepository bulletinRepository) {
        this.commentRepository = commentRepository;
        this.bulletinRepository = bulletinRepository;
    }

    @Override
    public CommentResponse createComment(CommentRequest request) {

        Bulletin bulletin = findBulletinById(request.getBulletinId());
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setBulletin(bulletin);
        comment.setSenderUserId(request.getSenderUserId());
        commentRepository.save(comment);
        updateCommentCounter(bulletin);

        return CommentMapper.mapper.commentToCommentResponse(comment);
    }

    private Bulletin findBulletinById(Long bulletinId) {
        return bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new RuntimeException("Bulletin Not found"));
    }

    private void updateCommentCounter(Bulletin bulletin) {
        bulletin.setCommentsCounter(bulletin.getCommentsCounter() + 1);
        this.bulletinRepository.save(bulletin);
    }
}
