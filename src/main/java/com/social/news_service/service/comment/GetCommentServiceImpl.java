package com.social.news_service.service.comment;

import com.social.news_service.dto.mapper.CommentMapper;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.dto.response.UserResponse;
import com.social.news_service.entity.Comment;
import com.social.news_service.repository.CommentRepository;
import com.social.news_service.service.user.GetUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GetCommentServiceImpl implements GetCommentService {

    private final CommentRepository commentRepository;

    private final GetUserService getUserService;


    public GetCommentServiceImpl(CommentRepository commentRepository,
                                 GetUserService getUserService) {
        this.commentRepository = commentRepository;
        this.getUserService = getUserService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByBulletinId(Long bulletinId) {
        List<Comment> comments = this.commentRepository.findByBulletinIdAndParentCommentIsNull(bulletinId);
        return mapToCommentResponse(comments);
    }

    private List<CommentResponse> mapToCommentResponse(List<Comment> comments) {
        return comments.stream().map(this::mapCommentWithUserDetails).toList();
    }

    private CommentResponse mapCommentWithUserDetails(Comment comment) {
        UserResponse senderUser = this.getUserService.getUserById(comment.getSenderUserId());
        CommentResponse response = CommentMapper.mapper.commentToCommentResponse(comment);
        response.setSenderUser(senderUser);

        if (comment.getChildComments() != null && !comment.getChildComments().isEmpty()) {
            List<CommentResponse> replies = comment.getChildComments().stream().map(this::mapCommentWithUserDetails)
                    .toList();
            response.setReplies(replies);
        }

        return response;
    }


}
