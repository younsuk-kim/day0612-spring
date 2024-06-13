package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.NoticeEntity;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter	//Controller에서 메소드바인딩(파라미터 매핑)시 setter메소드가 있어야함.
public class NoticeSaveDTO{
	
	private String division;	//구분-"전체","영화관"
	private boolean fixed;		//고정여부
	private String title;
	private String content;
	
	//formdata->dto->entity
	public NoticeEntity toEntity() {
		return NoticeEntity.builder()
				.division(division).fixed(fixed).title(title).content(content)
				.build();
	}

}
