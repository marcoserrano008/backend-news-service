package com.social.news_service.service.comment;

import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreateCommentService {

   CommentResponse createComment(CommentRequest request, Long bulletinId, Long userId);
}
