package com.social.news_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = Constants.CommentTable.TABLE_NAME)
@SQLRestriction(Constants.SQL_RESTRICTION)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.CommentTable.Id.NAME)
    private Long id;

    @Column(name = Constants.CommentTable.AccountId.NAME)
    private Long accountId;

    @Column(name = Constants.CommentTable.SenderUserId.NAME)
    private Long senderUserId;

    @Column(name = Constants.CommentTable.Content.NAME, length = Constants.CommentTable.Content.LENGTH)
    private String content;

    @Column(name = Constants.CommentTable.RepliesCounter.NAME)
    private Integer repliesCounter;

    @Column(name = Constants.CommentTable.CreatedDate.NAME)
    private LocalDateTime createdDate;

    @Column(name = Constants.CommentTable.IsDeleted.NAME)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulletin_id", referencedColumnName = "id", nullable = false)
    private Bulletin bulletin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> childComments;

    @PrePersist
    private void onCreate() {
        if (isDeleted == null) {
            isDeleted = Boolean.FALSE;
        }
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        if (repliesCounter == null) {
            repliesCounter = 0;
        }
    }
}
