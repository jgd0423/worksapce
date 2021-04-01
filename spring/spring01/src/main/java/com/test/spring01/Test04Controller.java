package com.test.spring01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test04Controller {
	@RequestMapping("/test04.do")
	public String test04() {
		return "test04/exam01";
	}
	
	@RequestMapping("/test04Proc.do")
	public String test04Proc(@ModelAttribute Test04DTO dto, Model model) {
		String name = dto.getName();
		String gender = dto.getGender();
		String jumin = dto.getJumin();
		int age = dto.getAge();
		
		model.addAttribute("name", name);
		model.addAttribute("jumin", jumin);
		model.addAttribute("gender", gender);
		model.addAttribute("age", age);
		
		return "test04/exam01Result";
	}
	
	
}
