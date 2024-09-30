package com.social.news_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BulletinSearchRequest {

    Integer page;

    Integer size;

    String sortBy;

    String sortDirection;

    String body;

    Long accountId;

    Long senderUserId;
}
