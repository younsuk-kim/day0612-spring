package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.green.domain.dto.FaqSaveDTO;
import com.green.domain.entity.Division;
import com.green.service.FaqService;

import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/cscenter/faq")
@Controller
@RequiredArgsConstructor
public class FaqController {
	
	private final FaqService service;
	
	@GetMapping
	public String list() {
		service.listProcess();
		return "redirect:/cscenter/faq/division/1";
	}
	
	
	// no -> 번호별 테마 게시판
	@GetMapping("/division/{dno}")
	public String list(@PathVariable("dno") int dno, Model model) {
		//읽어오기 서비스
		service.listProcess(dno, model);
		return "views/cs/faq/list";
	}
	
	//faq 쓰기 페이지 이동
	@GetMapping("/new")
	public String write() {
		return "views/cs/faq/write";
	}
	
	//faq 저장하기
	@PostMapping()
	public String write(FaqSaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/cscenter/faq/division/1";
	}
}
