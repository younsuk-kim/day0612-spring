package com.green.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.FaqSaveDTO;
import com.green.domain.entity.Division;
import com.green.domain.entity.FaqEntity;
import com.green.domain.entity.FaqEntityRepository;
import com.green.domain.entity.NoticeEntity;
import com.green.service.FaqService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FaqServiceProcess implements FaqService{
	
	private final FaqEntityRepository repository;

	@Override
	public void saveProcess(FaqSaveDTO dto) {
		repository.save(dto.toEntity());
	}
	
	@Override
	public void listProcess(int dno, Model model) {
		Division division = Division.values()[dno-1];
		
		Sort sort=Sort.by(Direction.DESC, "no");
		Pageable pageable=PageRequest.of(0, 10, sort);
		model.addAttribute("list", repository.findAllByDivision(division ,pageable).get()
										.map(FaqEntity::toFaqListDTO)//메서드 참조를 사용할 수 있다
										.collect(Collectors.toList()));
		
	}

	@Override
	public void listProcess() {
		
	}

}
