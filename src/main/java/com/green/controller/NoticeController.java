package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.service.NoticeService;


@RequestMapping("/cscenter/notices")
@Controller
@RequiredArgsConstructor
public class NoticeController {
	
	private final NoticeService service;
	
	@GetMapping// "/cscenter/notice"
	public String list(Model model) {
		service.listProcess(model);
		return "views/cs/notice/list";
	}
	

}
