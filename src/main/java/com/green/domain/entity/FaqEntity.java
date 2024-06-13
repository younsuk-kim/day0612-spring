package com.green.domain.entity;

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
	private String division;
	
	@Column(columnDefinition = "text", nullable = false)	//not null
	private String answer;
	
	@Column(nullable = false)	//not null
	private String question;
}
