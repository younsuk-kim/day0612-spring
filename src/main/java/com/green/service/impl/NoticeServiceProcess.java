package com.green.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.domain.dto.NoticeListDTO;
import com.green.domain.entity.NoticeEntity;
import com.green.domain.entity.NoticeEntityRepository;
import com.green.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceProcess implements NoticeService{

	private final NoticeEntityRepository repository;
	@Override
	public void listProcess(Model model) {
		int pageNumber=1;	//1페이지
		int pageSize=10;	//최대 10개까지
		
		Sort sort = Sort.by(Direction.DESC, "fixed","no");
		Pageable pageable=PageRequest.of(pageNumber-1, pageSize, sort);
		
		Page<NoticeEntity> result=repository.findAll(pageable);
		
		model.addAttribute("list", result.getContent().stream()
					.map(NoticeEntity::toNoticeListDTO)
					//매개변수가 1개인 경우 괄호생략() 가능, 바디 내용이 한줄인경우 중괄호{} 생략 가능, return 키워드도 생략 가능
					.collect(Collectors.toList()));
		
//		List<NoticeEntity> list=result.getContent();
//		
//		List<NoticeListDTO> dtoList = new ArrayList<>();
//		for(NoticeEntity ent:list) {
//			NoticeListDTO dto=new NoticeListDTO(ent.getNo(), 
//					ent.getTitle(), ent.getDivision(), ent.isFixed(), ent.getUpdatedAt());
//			dtoList.add(dto);
//		}
//		
//		model.addAttribute("list", dtoList);
		
	}

}
