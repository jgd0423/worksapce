package com.test.springStudy.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SurveyController {
	
	@RequestMapping("/survey/index.do")
	public String index() {
		return "survey/index";
	}
}
