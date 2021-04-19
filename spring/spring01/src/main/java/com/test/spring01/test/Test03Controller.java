package com.test.spring01.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Test03Controller {
	@RequestMapping("/test03.do")
	public String test03() {
		return "test03/exam01";
	}
	
//	@RequestMapping("/test03Proc.do")
//	public String test03Proc(Model model, HttpServletRequest request) {
//		String name = request.getParameter("name");
//		String gender = request.getParameter("gender");
//		String jumin = request.getParameter("jumin");
//		String age_ = request.getParameter("age");
//		int age = Integer.parseInt(age_);
//		
//		model.addAttribute("name", name);
//		model.addAttribute("jumin", jumin);	
//		model.addAttribute("gender", gender);
//		model.addAttribute("age", age);
//		
//		return "test03/exam01Result";
//	}
	
	public Map<String, String> test(HttpServletRequest request) {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String jumin = request.getParameter("jumin");
		String age = request.getParameter("age");
		
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("gender", gender);
		map.put("jumin", jumin);
		map.put("age", age);
		
		return map;
	}
	
	@RequestMapping("/test03Proc.do")
	public String test03Proc(Model model, HttpServletRequest request) {
		Map<String, String> map = test(request);
		
		String name = map.get("name");
		String gender = map.get("gender");
		String jumin = map.get("jumin");
		String age = map.get("age");
		
		model.addAttribute("name", name);
		model.addAttribute("jumin", jumin);
		model.addAttribute("gender", gender);
		model.addAttribute("age", age);
		
		return "test03/exam01Result";
	}
	
	
}
