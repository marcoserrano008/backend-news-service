package com.social.news_service.repository;

import com.social.news_service.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"childComments"})
    List<Comment> findByBulletinIdAndParentCommentIsNull(Long bulletinId);

    List<Comment> findByParentCommentId(Long parentCommentId);

    @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.childComments WHERE c.bulletin.id = :bulletinId AND c.parentComment IS NULL")
    List<Comment> findTopLevelCommentsByPostId(@Param("bulletinId") Long bulletinId);

}
