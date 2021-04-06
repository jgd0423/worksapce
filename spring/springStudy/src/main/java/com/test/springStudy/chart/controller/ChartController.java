package com.test.springStudy.chart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartController {
	
	@RequestMapping("/chart/index.do")
	public String index() {
		return "chart/index";
	}
}
