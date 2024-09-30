package com.social.news_service.service.reply;

import com.social.news_service.dto.mapper.CommentMapper;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.entity.Comment;
import com.social.news_service.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetReplyServiceImp implements GetReplyService {

    private final CommentRepository commentRepository;


    public GetReplyServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentResponse> getRepliesByCommentId(Long parentCommentId) {
        List<Comment> replies = this.commentRepository.findByParentCommentId(parentCommentId);
        return CommentMapper.mapper.commentsToCommentResponses(replies);
    }
}
