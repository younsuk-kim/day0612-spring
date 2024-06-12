package com.green;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.domain.entity.NoticeEntity;
import com.green.domain.entity.NoticeEntityRepository;

@SpringBootTest
class Web240612JpaMybtis01ApplicationTests {
	
	@Autowired
	NoticeEntityRepository noRep;
	@Test
	void contextLoads() {
		noRep.save(NoticeEntity.builder()
				.title("전체 제목테스트4")
				.content("전체 내용테스트4")
				.division("전체")
				//.fixed()
				.build());
	}

}
