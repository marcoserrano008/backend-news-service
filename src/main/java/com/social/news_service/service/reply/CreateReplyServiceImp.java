package com.social.news_service.service.reply;

import com.social.news_service.dto.mapper.CommentMapper;
import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.entity.Comment;
import com.social.news_service.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateReplyServiceImp implements CreateReplyService{

    private final CommentRepository commentRepository;


    public CreateReplyServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentResponse createReply(CommentRequest request) {

        Comment parentComment = findParentCommentById(request.getParentCommentId());

        Comment reply = new Comment();
        reply.setContent(request.getContent());
        reply.setParentComment(parentComment);
        reply.setSenderUserId(request.getSenderUserId());
        reply.setBulletin(parentComment.getBulletin());
        commentRepository.save(reply);
        updateReplyCounter(parentComment);

        return CommentMapper.mapper.commentToCommentResponse(reply);
    }

    public Comment findParentCommentById(Long parentCommentId) {
        return commentRepository.findById(parentCommentId)
                .orElseThrow(() -> new RuntimeException("Parent Comment not found"));
    }

    private void updateReplyCounter(Comment parentComment) {
        parentComment.setRepliesCounter(parentComment.getRepliesCounter() + 1);
        this.commentRepository.save(parentComment);
    }
}
