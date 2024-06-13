package com.green.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import com.green.domain.dto.NoticeSaveDTO;
import com.green.domain.entity.NoticeEntity;
import com.green.domain.entity.NoticeEntityRepository;
import com.green.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceProcess implements NoticeService{

	private final NoticeEntityRepository repository;
	@Override
	public void listProcess(int _division, Model model) {
		int pageNumber=1;	//1페이지
		int pageSize=10;	//최대 10개까지
		
		Sort sort = Sort.by(Direction.DESC, "fixed","no");
		Pageable pageable=PageRequest.of(pageNumber-1, pageSize, sort);
		
		String division = (_division==1?"전체":"영화관");
		
		//쿼리메소드 : findAll() 
		//사용자가 만들수 있는 쿼리 메소드 키워드 제공
		Page<NoticeEntity> result=repository.findAllByDivision(division, pageable);
		
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
	@Override
	public void saveProcess(NoticeSaveDTO dto) {
		System.out.println("save 스타트-----------");
		repository.save(dto.toEntity());
		System.out.println("save 끝-----------");
	}
	
	@Override
	//@Transaction// DBConnection 트랜잭션
	public void detailProcess(long no, Model model) {
		//상세정보 조회해서 model에 담아라
		System.out.println("findById 시작-----------");
		model.addAttribute(//key and value
				"detail", 
				repository.findById(no)
				.map(NoticeEntity::incrementReadcount)//조회수 증가
				.map(NoticeEntity::toNoticeDetailDTO)
				.orElseThrow() );
		System.out.println("findById 끝-----------");
		
	}
	
	public void detailProcess(long no) {
		// no(pk)해당하는 공지 DB에서 삭제
		//repository.deleteById(no);
		
		repository.delete(repository.findById(no).orElseThrow());
	}

}
