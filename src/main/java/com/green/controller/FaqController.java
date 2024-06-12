package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/cscenter/faq")
@Controller
@RequiredArgsConstructor
public class FaqController {
	
	@GetMapping// "/cscenter/faq"
	public String list(Model model) {
		return "views/cs/faq/list";
	}
	

}
