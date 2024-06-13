package com.green.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeEntityRepository extends JpaRepository<NoticeEntity, Long>{

	//엔티티 기준 문법에 맞지 않으면
	//사용자 정의 쿼리메소드
	Page<NoticeEntity> findAllByDivision(String division, Pageable pageable);

}
