package com.social.news_service.service.reply;

import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreateReplyService {

    CommentResponse createReply(CommentRequest request);
}
