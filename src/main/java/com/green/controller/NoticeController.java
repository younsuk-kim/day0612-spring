package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.domain.dto.NoticeSaveDTO;
import com.green.service.NoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RequestMapping("/cscenter/notices")
@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService service;
	
	@PostMapping
	public String save(@ModelAttribute NoticeSaveDTO dto) {
		//System.out.println("이건 dto"+dto);
		
		service.saveProcess(dto);
		
		return "redirect:/cscenter/notices";
	}
	
	@GetMapping()
	public String list() {
		return "redirect:/cscenter/notices/1";
	}
	
	//상세페이지 조회
	@GetMapping("/{no}")
	public String detail(@PathVariable("no") long no, Model model) {//변수로 들어오는값
		service.detailProcess(no, model);
		return "views/cs/notice/detail";
	}
	// 전체 공지사항 목록
    @GetMapping("/cinemas")
    public String listAll(Model model) {
        service.listProcess(1, model); // 1은 전체 공지를 나타내는 예시
        return "views/cs/notice/list";
    }
	 //1->전체공지, 2->영화관공지
	@GetMapping("/cinemas/{division}")
	public String list(@PathVariable("division") int division, Model model) {
	    service.listProcess(division, model);
		return "views/cs/notice/list";
	}
	
//	@GetMapping("/cinemas/{division}")
//	public String list(@PathVariable("division") String division, Model model) {
//		try {
//	        int div = Integer.parseInt(division); // String을 int로 변환
//	        service.listProcess(div, model);
//	    } catch (NumberFormatException e) {
//	        return "redirect:/error"; // 예시로 간단히 리다이렉트 처리
//	    }
//	    return "views/cs/notice/list";
//	}
	
	@GetMapping("/new")
	public String write() {
		return "views/cs/notice/write";
	}
	

}
