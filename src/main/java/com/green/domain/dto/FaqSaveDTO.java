package com.green.domain.dto;

import com.green.domain.entity.Division;
import com.green.domain.entity.FaqEntity;
import com.green.domain.entity.NoticeEntity;

import lombok.Setter;

@Setter
public class FaqSaveDTO {
	//필드 이름은 form태그 내부의
	//입력요소(input, select, textarea)들의 name과 일치해야 합니다.
	//Enum Division 타입도 자동매핑 됩니다.
	private Division division;
	private String question;
	private String answer;
	
	public FaqEntity toEntity() {
		return FaqEntity.builder()
				.answer(answer)
				.question(question)
				.division(division)
				.build();
	}
}
