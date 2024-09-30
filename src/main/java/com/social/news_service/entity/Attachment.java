package com.social.news_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = Constants.AttachmentTable.TABLE_NAME)
@SQLRestriction(Constants.SQL_RESTRICTION)
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constants.AttachmentTable.Id.NAME)
    private Long id;

    @Column(name = Constants.AttachmentTable.AccountId.NAME)
    private Long accountId;

    @Column(name = Constants.AttachmentTable.Field.NAME)
    private String field;

    @Column(name = Constants.AttachmentTable.Name.NAME)
    private String name;

    @Column(name = Constants.AttachmentTable.Size.NAME)
    private Long size;

    @Column(name = Constants.AttachmentTable.MimeType.NAME)
    private String mimeType;

    @Column(name = Constants.AttachmentTable.CreatedDate.NAME)
    private LocalDateTime createdDate;

    @Column(name = Constants.AttachmentTable.IsDeleted.NAME)
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "bulletin_id", referencedColumnName = "id", nullable = false)
    private Bulletin bulletin;

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
