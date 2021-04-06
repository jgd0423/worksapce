package com.test.springStudy.memo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemoController {
	
	@RequestMapping("/memo/index.do")
	public String index() {
		return "memo/index";
	}
}
