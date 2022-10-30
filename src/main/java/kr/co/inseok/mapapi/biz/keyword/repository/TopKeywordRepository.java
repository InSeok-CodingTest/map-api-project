package kr.co.inseok.mapapi.biz.keyword.repository;

import kr.co.inseok.mapapi.biz.keyword.entity.TopKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopKeywordRepository extends JpaRepository<TopKeywordEntity, String> {
}
