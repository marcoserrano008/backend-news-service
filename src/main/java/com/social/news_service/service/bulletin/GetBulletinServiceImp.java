package com.social.news_service.service.bulletin;

import com.social.news_service.dto.mapper.BulletinMapper;
import com.social.news_service.dto.request.BulletinSearchRequest;
import com.social.news_service.dto.response.BulletinResponse;
import com.social.news_service.dto.response.UserResponse;
import com.social.news_service.entity.Bulletin;
import com.social.news_service.repository.BulletinRepository;
import com.social.news_service.service.user.GetUserService;
import com.social.news_service.util.BulletinSpecification;
import com.social.news_service.util.PaginationAndSortingHelper;
import com.social.news_service.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetBulletinServiceImp implements GetBulletinService {

    private final BulletinRepository bulletinRepository;
    private final GetUserService getUserService;

    public GetBulletinServiceImp(BulletinRepository bulletinRepository,
                                 GetUserService getUserService) {
        this.bulletinRepository = bulletinRepository;
        this.getUserService = getUserService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BulletinResponse> getBulletins(BulletinSearchRequest request) {
        Pageable pageable = PaginationAndSortingHelper.createPageable(request.getPage(),
                request.getSize(),
                request.getSortBy(),
                request.getSortDirection());

        SearchCriteria searchCriteria = buildSearchCriteria(request);
        BulletinSpecification specification = new BulletinSpecification(searchCriteria);

        Page<Bulletin> bulletins = bulletinRepository.findAll(specification, pageable);
        return mapToBulletinResponse(bulletins);
    }

    private SearchCriteria buildSearchCriteria(BulletinSearchRequest request) {
        SearchCriteria searchCriteria = new SearchCriteria();
        if (request.getBody() != null) searchCriteria.addCriteria("body", request.getBody());
        if (request.getAccountId() != null) searchCriteria.addCriteria("accountId", request.getAccountId());
        if (request.getSenderUserId() != null) searchCriteria.addCriteria("senderUserId", request.getSenderUserId());
        return searchCriteria;
    }

    private Page<BulletinResponse> mapToBulletinResponse(Page<Bulletin> bulletins) {
        return bulletins.map(bulletin -> {
            UserResponse senderUser = this.getUserService.getUserById(bulletin.getSenderUserId());
            BulletinResponse response = BulletinMapper.mapper.bulletinToBulletinResponse(bulletin);
            response.setSenderUser(senderUser);
            return response;
        });
    }
}
