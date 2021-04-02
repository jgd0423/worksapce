package com.test.spring01.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test06Controller {
	@RequestMapping("/test06.do")
	public String test06() {
		return "test06/exam01";
	}
	
	@RequestMapping("/test06Proc.do")
	public String test06Proc(@ModelAttribute Test06DTO dto, Model model) {
		dto.calcDiscountedPrice();
		
		model.addAttribute("dto", dto);
		
		return "test06/exam01Result";
	}
	
	
}
