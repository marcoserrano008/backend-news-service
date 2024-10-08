package com.social.news_service.dto.mapper;

import com.social.news_service.dto.request.CommentRequest;
import com.social.news_service.dto.response.CommentResponse;
import com.social.news_service.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper mapper = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "bulletin", ignore = true)
    @Mapping(target = "parentComment", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "repliesCounter", ignore = true)
    @Mapping(target = "childComments", ignore = true)
    Comment commentRequestToComment(CommentRequest request);

    @Mapping(target = "replies", source = "childComments", qualifiedByName = "mapReplies")
    @Mapping(target = "parentCommentId", ignore = true)
    CommentResponse commentToCommentResponse(Comment comment);

    List<CommentResponse> commentsToCommentResponses(List<Comment> comments);

    @Named("mapReplies")
    default List<CommentResponse> mapReplies(List<Comment> childComments) {
        return childComments != null ? commentsToCommentResponses(childComments) : Collections.emptyList();
    }
}
