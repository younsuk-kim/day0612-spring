package com.green.domain.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity {
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	protected LocalDateTime createdAt;
	
	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	protected LocalDateTime updatedAt;
}
