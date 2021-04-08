package com.test.spring01.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.spring01.member.model.dao.MemberDAO;
import com.test.spring01.member.model.dto.MemberDTO;
import com.test.spring01.member.service.MemberService;

@Controller
public class MemberController {
	
	@Inject   // 의존성주입
	MemberDAO memberDao;
	@Inject
	MemberService memberService;
	
	int imsi = 1;   // 0 - dao, 1 - service
	
	@RequestMapping("memberList.do")
	public String memberList(Model model) {
		List<MemberDTO> list;  
		if (imsi == 0) {
			list = memberDao.getList();
			System.out.println("-- use memberDao --");
		} else {
			list = memberService.getList();
			System.out.println("-- use memberService --");
		}
		
		model.addAttribute("list", list);
		return "member/list";
	}
	
	@RequestMapping("memberView.do")
	public String memberView(@RequestParam(value="id", defaultValue="") String id, Model model) {
		MemberDTO dto;
		if (imsi == 0) {
			dto = memberDao.getView(id);
		} else {
			dto = memberService.getView(id);
		}
		
		model.addAttribute("dto", dto);
		return "member/view";
	}
	
	@RequestMapping("memberWrite.do")
	public String memberWrite() {
		return "member/write";
	}
	
	@RequestMapping("memberWriteProc.do")
	public String memberWriteProc(@ModelAttribute MemberDTO dto) {
		if (imsi == 0) {
			memberDao.setInsert(dto);
		} else {
			memberService.setInsert(dto);
		}
		
		return "redirect:memberList.do";
	}
	
	@RequestMapping("memberModify.do")
	public String memberModify(@RequestParam(value="id", defaultValue="") String id, Model model) {
		MemberDTO dto;
		if (imsi == 0) {
			dto = memberDao.getView(id);
		} else {
			dto = memberService.getView(id);
		}
	
		model.addAttribute("dto", dto);
		return "member/modify";
	}
	
	@RequestMapping("memberModifyProc.do")
	public String memberModifyProc(@ModelAttribute MemberDTO dto) {
		if (imsi == 0) {
			memberDao.setUpdate(dto);
		} else {
			memberService.setUpdate(dto);
		}
		
		return "redirect:memberView.do?id=" + dto.getId();
	}
	
	@RequestMapping("memberDelete.do")
	public String memberDelete(@RequestParam(value="id", defaultValue="") String id, Model model) {
		MemberDTO dto;
		if (imsi == 0) {
			dto = memberDao.getView(id);
		} else {
			dto = memberService.getView(id);
		}
		
		model.addAttribute("dto", dto);
		return "member/delete";
	}
	
	@RequestMapping("memberDeleteProc.do")
	public String memberDeleteProc(@RequestParam(value="id", defaultValue="") String id) {
		if (imsi == 0) {
			memberDao.setDelete(id);
		} else {
			memberService.setDelete(id);
		}
		
		return "redirect:memberList.do";
	}
}
