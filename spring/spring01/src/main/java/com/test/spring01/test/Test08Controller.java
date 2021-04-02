package com.test.spring01.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test08Controller {
	@RequestMapping("/test08.do")
	public String test08() {
		return "test08/exam01";
	}
	
	@RequestMapping("/test08Proc.do")
	public String test08Proc(
			@RequestParam(value="id", defaultValue="") String id,
			@RequestParam(value="passwd", defaultValue="") String passwd,
			Model model) {
		String dbId = "hong";
		String dbPasswd = "1111";
		String result = null;
		
		if (id.equals(dbId) && passwd.equals(dbPasswd)) {
			result = "로그인 성공";
		} else {
			result = "로그인 실패";
		}
		
		model.addAttribute("result", result);

		return "test08/exam01Result";

	}
	
	
}
