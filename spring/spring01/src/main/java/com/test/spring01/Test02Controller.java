package com.test.spring01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Test02Controller {
	@RequestMapping("/test02.do")
	public String test02() {
		return "test02/exam01";
	}
	
	@RequestMapping("/test02Proc.do")
	public String test02Proc(
			Model model,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="jumin", defaultValue="") String jumin,
			@RequestParam(value="gender", defaultValue="") String gender
			) {
		model.addAttribute("name", name);
		model.addAttribute("jumin", jumin);
		model.addAttribute("gender", gender);
		
		
		return "test02/exam01Result";
	}
}
