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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.green.domain.dto.NoticeListDTO;
import com.green.domain.dto.NoticeSaveDTO;
import com.green.domain.dto.NoticeUpdateDTO;
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
		int pageNumber=1;//1페이지
		int pageSize=10; //최대 10개까지
		//String[] columns= {"fixed","no"};
		Sort sort=Sort.by(Direction.DESC, "fixed","no");
		Pageable pageable=PageRequest.of(pageNumber-1, pageSize, sort);
		
		String division= (_division==1)?"전체":"영화관";
		
		//JPA 쿼리메서드 : findAll()
		//사용자가 만들수 있는 쿼리메서드 문법-키워드
		Page<NoticeEntity> result=repository.findAllByDivision(division,pageable);
		
		model.addAttribute("list", result.getContent().stream()
										//.map((ent)->{return ent.toNoticeListDTO();})
										//.map(ent->{return ent.toNoticeListDTO();})//매개변수 1개인경우에 () 생략가능
										//.map(ent->ent.toNoticeListDTO())//{return null;} {}내용이1줄인데 return
										.map(NoticeEntity::toNoticeListDTO)//메서드 참조를 사용할 수 있다
										.collect(Collectors.toList()));
		
		/*
		List<NoticeEntity> list=result.getContent();
		
		List<NoticeListDTO> dtoList=new ArrayList<>();
		for(NoticeEntity ent:list) {
			NoticeListDTO dto=new NoticeListDTO(ent.getNo(), ent.getTitle(), ent.getDivision(), ent.isFixed(), ent.getUpdatedAt());
			dtoList.add(dto);
		}
		
		model.addAttribute("list", dtoList);
		*/
		
	}
	
	@Override
	public void saveProcess(NoticeSaveDTO dto) {
		//NoticeEntity entity=dto.toEntity();
		//repository.save(entity);
		System.out.println("----save srart");
		repository.save(dto.toEntity());
		System.out.println("----save end");
		
	}

	@Override
	//@Transactional // DBConnection- SqlSession 메서드종료될때까지 유지한다.
	public void detailProcess(long no, Model model) {
		// 상세정보 조회해서 model에 담아라
		System.out.println("----findById srart");
		//Null Pointer Exception 방지:
		//map(), flatmap(), filter()
		NoticeEntity result=repository.findById(no).orElseThrow();
		model.addAttribute("detail", result.toNoticeDetailDTO());
		result.incrementReadcount();
		System.out.println("----findById end");
		repository.save(result);
		//JPA를 사용시 SqlSession이유지되는 동안 Entity가 수정되면 -> update쿼리가 처리됨
		
	}

	@Override
	public void deleteProcess(long no) {
		// no(pk)해당하는 공지 DB에서 삭제
		//repository.deleteById(no);
		System.out.println("----삭제 srart");
		repository.delete(repository.findById(no).orElseThrow());
		System.out.println("----삭제 end");
	}
	
	@Override
	@Transactional
	public void updateProcess(long no, NoticeUpdateDTO dto) {
		// 1. 수정할 대상을 조회 2. 변경사항을 적용 -> 변경된entity 저장
		//repository.save(repository.findById(no).orElseThrow().update(dto)); //수정처리
		
		// 수정됨(@Transactional 인경우)
		repository.findById(no).orElseThrow().update(dto);
		
	}

}
