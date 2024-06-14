package com.green.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqEntityRepository extends JpaRepository<FaqEntity, Long>{

	Page<FaqEntity> findAllByDivision(Division division, Pageable pageable);

}
