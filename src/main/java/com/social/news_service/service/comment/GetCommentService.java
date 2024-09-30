package com.social.news_service.service.comment;

import com.social.news_service.dto.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetCommentService {

    List<CommentResponse> getCommentsByBulletinId(Long bulletinId);
}
