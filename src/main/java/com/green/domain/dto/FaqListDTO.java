package com.green.domain.dto;

import java.time.LocalDateTime;

import com.green.domain.entity.Division;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class FaqListDTO {
	
	private long no;
	private Division division;
	private String question;
	private String answer;
	private LocalDateTime updatedAt;

}
