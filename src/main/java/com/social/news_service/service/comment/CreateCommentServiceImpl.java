package com.social.news_service.service.comment;

import com.social.news_service.dto.mapper.CommentMapper;
import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.dto.response.UserResponse;
import com.social.news_service.entity.Bulletin;
import com.social.news_service.entity.Comment;
import com.social.news_service.repository.BulletinRepository;
import com.social.news_service.repository.CommentRepository;
import com.social.news_service.service.user.GetUserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreateCommentServiceImpl implements CreateCommentService {

    private final CommentRepository commentRepository;

    private final BulletinRepository bulletinRepository;

    private final GetUserService  getUserService;


    public CreateCommentServiceImpl(CommentRepository commentRepository,
                                    BulletinRepository bulletinRepository,
                                    GetUserService getUserService) {
        this.commentRepository = commentRepository;
        this.bulletinRepository = bulletinRepository;
        this.getUserService = getUserService;
    }

    @Override
    public CommentResponse createComment(CommentRequest request, Long bulletinId, Long userId) {
        Bulletin bulletin = findBulletinById(bulletinId);
        Comment comment = createNewComment(request, bulletin, userId);
        commentRepository.save(comment);

        UserResponse senderUser = this.getUserService.getUserById(userId);
        CommentResponse response = CommentMapper.mapper.commentToCommentResponse(comment);
        response.setSenderUser(senderUser);

        return response;
    }

    private Comment createNewComment(CommentRequest request, Bulletin bulletin, Long userId) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setBulletin(bulletin);
        comment.setSenderUserId(userId);

        if (Objects.nonNull(request.getParentCommentId())) {
            Comment parentComment = findParentCommentById(request.getParentCommentId());
            parentComment.setRepliesCounter(parentComment.getRepliesCounter() + 1);
            commentRepository.save(parentComment);
            comment.setParentComment(parentComment);
        } else {
            updateCommentCounter(bulletin);
        }

        return comment;
    }

    private Bulletin findBulletinById(Long bulletinId) {
        return bulletinRepository.findById(bulletinId)
                .orElseThrow(() -> new RuntimeException("Bulletin Not found"));
    }

    public Comment findParentCommentById(Long parentCommentId) {
        return commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new RuntimeException("Parent Comment not found"));
    }

    private void updateCommentCounter(Bulletin bulletin) {
        bulletin.setCommentsCounter(bulletin.getCommentsCounter() + 1);
        this.bulletinRepository.save(bulletin);
    }
}
