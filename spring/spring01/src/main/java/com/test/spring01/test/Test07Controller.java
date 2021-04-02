package com.test.spring01.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test07Controller {
	@RequestMapping("/test07.do")
	public String test07() {
		return "test07/exam01";
	}
	
	@RequestMapping("/test07Proc.do")
	public ModelAndView test07Proc(@ModelAttribute Test07DTO dto, Model model) {
		dto.calcDiscountedPrice();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", dto);
		
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("test07/exam01Result");
//		mv.addObject("map", map);
		
		return new ModelAndView("test07/exam01Result", "map", map);
		//return mv;
	}
	
	
}
