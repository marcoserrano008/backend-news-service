package com.social.news_service.repository;

import com.social.news_service.entity.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BulletinRepository extends JpaRepository<Bulletin, Long>, JpaSpecificationExecutor<Bulletin> {
}
