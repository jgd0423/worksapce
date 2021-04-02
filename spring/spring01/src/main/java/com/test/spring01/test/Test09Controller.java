package com.test.spring01.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test09Controller {
	@RequestMapping("/test09.do")
	public String test09() {
		return "test09/exam01";
	}
	
	@RequestMapping("/test09Proc.do")
	@ResponseBody
	public Map<String, String> test09Proc(
			@RequestParam(value="id", defaultValue="") String id,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="email", defaultValue="") String email
			) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("email", email);
		
		return map;
	}
	
	
}
