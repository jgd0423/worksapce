package com.test.spring01.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@RequestMapping("/")   // url mapping
	public String index(Model model) {
		model.addAttribute("name", "홍길동님, ");
		model.addAttribute("msg", "안녕하세요?");
		return "index";
	}
}
