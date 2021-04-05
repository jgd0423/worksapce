package com.test.spring01.exam.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.spring01.exam.model.dao.ExamDAO;
import com.test.spring01.exam.model.dto.ExamDTO;

@Controller
public class ExamController {
	
	@Inject   // 의존성주입
	ExamDAO examDao;
	
	@RequestMapping("examList.do")
	public String examList(Model model) {
		List<ExamDTO> list = examDao.getList();
		model.addAttribute("list", list);
		return "exam/list";
	}
	
	@RequestMapping("examWrite.do")
	public String examWrite() {
		return "exam/write";
	}
	
	@RequestMapping("examWriteProc.do")
	public String examWriteProc(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="socialNumber", defaultValue="") String socialNumber
			) {
		
		String gender = null;
		String genderNumber = socialNumber.substring(6, 7);
		if (genderNumber.equals("1") || genderNumber.equals("3")) {
			gender = "M";
		} else if (genderNumber.equals("2") || genderNumber.equals("4")) {
			gender = "F";
		}
		
		int age = 0;
		String bornYearStr = socialNumber.substring(0, 2);
		if (genderNumber.equals("1") || genderNumber.equals("2")) {
			bornYearStr = "19" + bornYearStr;
		} else if (genderNumber.equals("3") || genderNumber.equals("4")) {
			bornYearStr = "20" + bornYearStr;
		}
		
		age = 2021 - Integer.parseInt(bornYearStr);
		
		String ageStr = Integer.toString(age);
		
		ExamDTO dto = new ExamDTO();
		dto.setName(name);
		dto.setSocialNumber(socialNumber);
		dto.setGender(gender);
		dto.setAge(ageStr);
		examDao.setInsert(dto);
		return "redirect:examList.do";
	}
	
	@RequestMapping("examModify.do")
	public String examModify(
			@RequestParam(value="no", defaultValue="") int no, Model model
			) {
		ExamDTO dto = examDao.getView(no);
		model.addAttribute("dto", dto);
		return "exam/modify";
	}
	
	@RequestMapping("examModifyProc.do")
	public String examModifyProc(@ModelAttribute ExamDTO dto) {
		examDao.setUpdate(dto);
		return "redirect:examList.do";
	}
	
	@RequestMapping("examDelete.do")
	public String examDelete(@RequestParam(value="no", defaultValue="") int no, Model model) {
		ExamDTO dto = examDao.getView(no);
		model.addAttribute("dto", dto);
		return "exam/delete";
	}
	
	@RequestMapping("examDeleteProc.do")
	public String examDeleteProc(@RequestParam(value="no", defaultValue="") int no) {
		examDao.setDelete(no);
		return "redirect:examList.do";
	}
	
}
