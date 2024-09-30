package com.social.news_service.service.bulletin;

import com.social.news_service.dto.mapper.BulletinMapper;
import com.social.news_service.dto.request.BulletinSearchRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.entity.Bulletin;
import com.social.news_service.repository.BulletinRepository;
import com.social.news_service.util.BulletinSpecification;
import com.social.news_service.util.PaginationAndSortingHelper;
import com.social.news_service.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
public class GetBulletinServiceImp implements GetBulletinService {

    private final BulletinRepository bulletinRepository;

    public GetBulletinServiceImp(BulletinRepository bulletinRepository) {
        this.bulletinRepository = bulletinRepository;
    }

    @Override
    public Page<BulletinResponse> getBulletins(BulletinSearchRequest request) {
        Pageable pageable = PaginationAndSortingHelper.createPageable(request.getPage(),
                                                                      request.getSize(),
                                                                      request.getSortBy(),
                                                                      request.getSortDirection());
        SearchCriteria searchCriteria = new SearchCriteria();
        if (request.getBody() != null) searchCriteria.addCriteria("body", request.getBody());
        if (request.getAccountId() != null) searchCriteria.addCriteria("accountId", request.getAccountId());
        if (request.getSenderUserId() != null) searchCriteria.addCriteria("senderUserId", request.getSenderUserId());

        BulletinSpecification specification = new BulletinSpecification(searchCriteria);
        Page<Bulletin> bulletins = bulletinRepository.findAll(specification, pageable);
        return bulletins.map(BulletinMapper.mapper::bulletinToBulletinResponse);
    }
}
