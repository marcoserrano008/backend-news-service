package com.social.news_service.service.reply;

import com.social.news_service.dto.mapper.CommentMapper;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.dto.response.UserResponse;
import com.social.news_service.entity.Comment;
import com.social.news_service.repository.CommentRepository;
import com.social.news_service.service.user.GetUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetReplyServiceImp implements GetReplyService {

    private final CommentRepository commentRepository;

    private final GetUserService getUserService;

    public GetReplyServiceImp(CommentRepository commentRepository,
                              GetUserService getUserService) {
        this.commentRepository = commentRepository;
        this.getUserService = getUserService;
    }

    @Override
    public List<CommentResponse> getRepliesByCommentId(Long parentCommentId) {
        List<Comment> replies = this.commentRepository.findByParentCommentId(parentCommentId);
        return mapToCommentResponse(replies);
    }

    private List<CommentResponse> mapToCommentResponse(List<Comment> replies) {
        return replies.stream().map(reply -> {
            UserResponse senderUser = this.getUserService.getUserById(reply.getSenderUserId());
            CommentResponse response = CommentMapper.mapper.commentToCommentResponse(reply);
            response.setSenderUser(senderUser);
            return response;
        }).toList();
    }
}
