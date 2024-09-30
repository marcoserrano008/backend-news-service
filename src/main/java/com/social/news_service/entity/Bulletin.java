package com.social.news_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = Constants.BulletinTable.TABLE_NAME)
@SQLRestriction(Constants.SQL_RESTRICTION)
public class Bulletin {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = Constants.BulletinTable.Id.NAME)
    private Long id;

    @Column(name = Constants.BulletinTable.AccountId.NAME)
    private Long accountId;

    @Column(name = Constants.BulletinTable.SenderUserId.NAME)
    private Long senderUserId;

    @Column(name = Constants.BulletinTable.Body.NAME)
    private String body;

    @Column(name = Constants.BulletinTable.CreatedDate.NAME)
    private LocalDateTime createdDate;

    @Column(name = Constants.BulletinTable.IsDeleted.NAME)
    private Boolean isDeleted;

    @Column(name = Constants.BulletinTable.CommentsCounter.NAME)
    private Integer commentsCounter;

    @OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL)
    private List<Attachment> attachments = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        if (isDeleted == null) {
            isDeleted = Boolean.FALSE;
        }
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
    }
}
