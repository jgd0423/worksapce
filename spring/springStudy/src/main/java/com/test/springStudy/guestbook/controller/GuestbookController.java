package com.test.springStudy.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestbookController {
	
	@RequestMapping("/guestbook/index.do")
	public String index() {
		return "guestbook/index";
	}
}
