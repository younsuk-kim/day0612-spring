package com.green.domain.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NoticeListDTO{
	
	private long no;
	private String title;
	private String division;	//구분-"전체","영화관"
	private boolean fixed;		//고정여부
	private LocalDateTime updatedAt;

}
