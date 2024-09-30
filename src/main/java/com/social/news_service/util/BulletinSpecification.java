package com.social.news_service.util;

import com.social.news_service.entity.Bulletin;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BulletinSpecification implements Specification<Bulletin> {

    private final SearchCriteria searchCriteria;

    public BulletinSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Bulletin> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchCriteria.hasCriteria("body")) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("body")), "%" + searchCriteria.getCriteria("body").toString().toLowerCase() + "%"));
        }

        if (searchCriteria.hasCriteria("accountId")) {
            predicates.add(criteriaBuilder.equal(root.get("accountId"), searchCriteria.getCriteria("accountId")));
        }

        if (searchCriteria.hasCriteria("senderUserId")) {
            predicates.add(criteriaBuilder.equal(root.get("senderUserId"), searchCriteria.getCriteria("senderUserId")));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
