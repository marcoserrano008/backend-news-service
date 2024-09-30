package com.social.news_service.dto.mapper;

import com.social.news_service.dto.request.BulletinRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.entity.Bulletin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = AttachmentMapper.class)
public interface BulletinMapper {

    BulletinMapper mapper = Mappers.getMapper(BulletinMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "commentsCounter", ignore = true)
    Bulletin bulletinRequestToBulletin(BulletinRequest bulletinRequest);

    @Mapping(target = "attachments", source = "attachments", qualifiedByName = "attachmentsToAttachmentResponses")
    BulletinResponse bulletinToBulletinResponse(Bulletin bulletin);

    List<BulletinResponse> bulletinsToBulletinResponses(List<Bulletin> bulletins);
}
