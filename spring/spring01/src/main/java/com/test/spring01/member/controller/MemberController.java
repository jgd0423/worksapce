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

@Controller
public class MemberController {
	
	@Inject   // 의존성주입
	MemberDAO memberDao;
	
	@RequestMapping("memberList.do")
	public String memberList(Model model) {
		List<MemberDTO> list = memberDao.getList();
		model.addAttribute("list", list);
		return "member/list";
	}
	
	@RequestMapping("memberView.do")
	public String memberView(@RequestParam(value="id", defaultValue="") String id, Model model) {
		MemberDTO dto = memberDao.getView(id);
		model.addAttribute("dto", dto);
		return "member/view";
	}
	
	@RequestMapping("memberWrite.do")
	public String memberWrite() {
		return "member/write";
	}
	
	@RequestMapping("memberWriteProc.do")
	public String memberWriteProc(@ModelAttribute MemberDTO dto) {
		memberDao.setInsert(dto);
		return "redirect:memberList.do";
	}
	
	@RequestMapping("memberModify.do")
	public String memberModify(@RequestParam(value="id", defaultValue="") String id, Model model) {
		MemberDTO dto = memberDao.getView(id);
		model.addAttribute("dto", dto);
		return "member/modify";
	}
	
	@RequestMapping("memberModifyProc.do")
	public String memberModifyProc(@ModelAttribute MemberDTO dto) {
		memberDao.setUpdate(dto);
		return "redirect:memberView.do?id=" + dto.getId();
	}
	
	@RequestMapping("memberDelete.do")
	public String memberDelete(@RequestParam(value="id", defaultValue="") String id, Model model) {
		MemberDTO dto = memberDao.getView(id);
		model.addAttribute("dto", dto);
		return "member/delete";
	}
	
	@RequestMapping("memberDeleteProc.do")
	public String memberDeleteProc(@RequestParam(value="id", defaultValue="") String id) {
		memberDao.setDelete(id);
		return "redirect:memberList.do";
	}
}
