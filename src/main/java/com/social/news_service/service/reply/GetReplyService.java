package com.social.news_service.service.reply;

import com.social.news_service.dto.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetReplyService {

    List<CommentResponse> getRepliesByCommentId(Long parentCommentId);
}
