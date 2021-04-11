package com.test.springStudy.questionBank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class questionBankController {

	@RequestMapping("/questionBank/index.do")
	public String index(Model model) {
		model.addAttribute("menu_gubun", "questionBank_index");
		return "main/main";
	}
}
