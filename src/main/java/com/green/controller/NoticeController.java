package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.domain.dto.NoticeSaveDTO;
import com.green.domain.dto.NoticeUpdateDTO;
import com.green.domain.entity.Division;
import com.green.service.NoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/cscenter/notices")
@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService service;
	
	//공지사항 저장하기
	@PostMapping
	public String save(/* @ModelAttribute */ NoticeSaveDTO dto) {
		//System.out.println("NoticeSaveDTO>>>"+dto);
		service.saveProcess(dto);
		
		return "redirect:/cscenter/notices";
	}
	
	// /cscenter/notices -> 전체 공지사항 페이지
	@GetMapping
	public String list() {
		
		return "redirect:/cscenter/notices/cinemas/1";
	}
	
	//상세페이지 조회
	@GetMapping("/{no}")
	public String detail(@PathVariable("no") long no, Model model) {
		
		service.detailProcess(no, model);
		
		return "views/cs/notice/detail";
	}
	
	@DeleteMapping("/{no}")
	public String delete(@PathVariable("no") long no) {
		
		service.deleteProcess(no);
		
		return "redirect:/cscenter/notices";
	}
	
	@PutMapping("/{no}")
	public String update(@PathVariable("no")long no,NoticeUpdateDTO dto) {
		
		service.updateProcess(no, dto);
		
		return "redirect:/cscenter/notices/{no}";
	}
	
	// 1->전체공지 , 2->영화관공지
	@GetMapping("/cinemas/{division}")
	public String list(@PathVariable("division") int division,Model model) {
		service.listProcess(division, model);
		return "views/cs/notice/list";
	}
	
	@GetMapping("/new")
	public String write() {
		return "views/cs/notice/write";
	}
	

}
