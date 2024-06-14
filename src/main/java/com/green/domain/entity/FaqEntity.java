package com.green.domain.entity;

import com.green.domain.dto.FaqListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@SequenceGenerator(name = "gen_faq",
			sequenceName = "seq_faq", initialValue = 1, allocationSize = 1)
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="faq")
@Entity
public class FaqEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_faq")
	private long no;
	
	@Column(nullable = false)	//not null
	@Enumerated(EnumType.STRING)//DB에 저장할때 Enum의 컬럼타입을 문자열로 지정-@Enumerated의 기본은 ordinal로 적용 
	private Division division;
	
	@Column(columnDefinition = "text", nullable = false)	//not null
	private String answer;
	
	@Column(nullable = false)	//not null
	private String question;
	
	public FaqListDTO toFaqListDTO() {
		return FaqListDTO.builder()
				.answer(answer)
				.question(question)
				.division(division)
				.build();
	}
}
