package com.test.spring01.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test05Controller {
	@RequestMapping("/test05.do")
	public String test05() {
		return "test05/exam01";
	}
	
	@RequestMapping("/test05Proc.do")
	public String test05Proc(@ModelAttribute Test05DTO dto, Model model) {
		dto.calcTot();
		dto.calcAvg();
		
		model.addAttribute("dto", dto);
		
		return "test05/exam01Result";
	}
	
	
}
