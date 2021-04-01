package com.test.spring01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Test01Controller {
	@RequestMapping("/test01.do")
	public String test01() {
		return "test01/exam01";
	}
	
	@RequestMapping("/test01Proc.do")
	public String test01Proc(
			Model model,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="jumin", defaultValue="XXX") String jumin,
			@RequestParam(value="gender", defaultValue="F") String gender
			) {
		model.addAttribute("name", name);
		model.addAttribute("jumin", jumin);
		model.addAttribute("gender", gender);
		
		
		return "test01/exam01Result";
	}
}
