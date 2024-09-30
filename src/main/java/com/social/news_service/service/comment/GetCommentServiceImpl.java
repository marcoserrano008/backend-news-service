package com.social.news_service.service.comment;

import com.social.news_service.dto.mapper.CommentMapper;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.entity.Comment;
import com.social.news_service.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentServiceImpl implements GetCommentService {

    private final CommentRepository commentRepository;


    public GetCommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentResponse> getCommentsByBulletinId(Long bulletinId) {
        List<Comment> comments = this.commentRepository.findByBulletinIdAndParentCommentIsNull(bulletinId);
        return CommentMapper.mapper.commentsToCommentResponses(comments);
    }
}
