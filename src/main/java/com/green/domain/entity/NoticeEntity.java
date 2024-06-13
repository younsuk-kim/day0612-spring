package com.green.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import com.green.domain.dto.NoticeDetailDTO;
import com.green.domain.dto.NoticeListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@SequenceGenerator(name = "gen_notice",
					sequenceName = "seq+notice", initialValue = 1, allocationSize = 1)

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "notice")
@Getter
@Entity
public class NoticeEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_notice")
	private long no;
	
	@Column(nullable = false)
	private String title;
	@Column(columnDefinition = "text" ,nullable = false)
	private String content;
	private int readCount;
	
	private String division;	//구분-"전체","영화관"
	private boolean fixed;		//고정여부
	
	//조회수 증가시키는 편의메소드
	public NoticeEntity incrementReadcount() {
		readCount++;
		return this;
	}
	
	
	//편의메소드 매핑하기위한 메소드
	public NoticeListDTO toNoticeListDTO() {
		return NoticeListDTO.builder()
				.no(no)
				.title(title)
				.division(division)
				.fixed(fixed)
				.updatedAt(updatedAt)
				.build();
	}
	
	public NoticeDetailDTO toNoticeDetailDTO() {
		return NoticeDetailDTO.builder()
				.no(no)
				.title(title)
				.content(content)
				.division(division)
				.readCount(readCount)
				.createdAt(createdAt)
				.updatedAt(updatedAt)
				.build();
	}
}
