package com.test.springStudy.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/board/index.do")
	public String index() {
		return "board/index";
	}
}
