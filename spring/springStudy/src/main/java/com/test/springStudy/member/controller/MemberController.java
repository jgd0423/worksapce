package com.test.springStudy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.springStudy.member.model.dao.MemberDAO;
import com.test.springStudy.member.model.dto.MemberDTO;
import com.test.springStudy.member.util.MemberUtil;

@Controller
@RequestMapping("/member")
public class MemberController {
	// --- 공통부분 ---
	@Inject
	MemberDAO memberDao;   // 수정할 부분
	
	MemberUtil util = new MemberUtil();   // 수정할 부분
	
    public Map<String, Object> topInfo(HttpServletRequest request) throws UnknownHostException {
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];
		
		Map<String, Object> map = util.basicInfo(request);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		return map;
	}
    // --- 공통부분 ---
	
	@RequestMapping("/index.do")
	public String member_index(
			HttpServletRequest request, 
			// HttpServletResponse response, 
			Model model
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		String ip = (String)map.get("ip");
		
		String arg01 = request.getParameter("arg01");
		arg01 = util.nullCheck(arg01);
		
		model.addAttribute("menu_gubun", "member_index");
		model.addAttribute("ip", ip);
		model.addAttribute("arg01", arg01);
		
		return "main/main";
	}
	
	@RequestMapping("/list.do")
	public String member_list(HttpServletRequest request, Model model) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int pageNum = (int)map.get("pageNumber");
		int no = (int)map.get("no");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		
		final int ONE_PAGE_ROWS = 15;
		final int MAX_PAGING_WIDTH = 15;
		int allRowsCount = memberDao.getAllRowsCount(search_option, search_data);
		int[] pagerArray = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
		int tableRowNum = pagerArray[0];
		int pagingStartNum = pagerArray[1];
		int pagingEndNum = pagerArray[2];
		int maxPagesCount = pagerArray[3];
		int startNum = pagerArray[4];
		int endNum = pagerArray[5];
		
		List<MemberDTO> list = memberDao.getPagingList(pagingStartNum, pagingEndNum, search_option, search_data);
		
		model.addAttribute("menu_gubun", "member_list");
		model.addAttribute("list", list);
		model.addAttribute("no", no);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("allRowsCount", allRowsCount);
		model.addAttribute("tableRowNum", tableRowNum);
		model.addAttribute("pagingStartNum", pagingStartNum);
		model.addAttribute("pagingEndNum", pagingEndNum);
		model.addAttribute("maxPagesCount", maxPagesCount);
		model.addAttribute("startNum", startNum);
		model.addAttribute("endNum", endNum);
		
		return "member/list";
	}
	
	@RequestMapping("/view.do")
	public String member_view(HttpServletRequest request, Model model) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		
		MemberDTO dto = memberDao.getSelectOne(no, search_option, search_data);
		
		model.addAttribute("menu_gubun", "member_view");
		model.addAttribute("dto", dto);
		return "member/view";
	}
	
	@RequestMapping("/write.do")
	public String member_write(Model model) {
		model.addAttribute("menu_gubun", "member_write");
		return "member/write";
	}
	
	@RequestMapping("/writeProc.do")
	public void member_writeProc(
			// HttpServletResponse response,
			Model model,
			@RequestParam(value="id", defaultValue="") String id,
			@RequestParam(value="passwd", defaultValue="") String passwd,
			@RequestParam(value="passwdChk", defaultValue="") String passwdChk,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="gender", defaultValue="") String gender,
			@RequestParam(value="bornYear", defaultValue="0") int bornYear,
			@RequestParam(value="sample6_postcode", defaultValue="") String postcode,
			@RequestParam(value="sample6_address", defaultValue="") String address,
			@RequestParam(value="sample6_detailAddress", defaultValue="") String detailAddress,
			@RequestParam(value="sample6_extraAddress", defaultValue="") String extraAddress
			) {
		
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPasswd(passwd);
		dto.setName(name);
		dto.setGender(gender);
		dto.setBornYear(bornYear);
		
		dto.setPostcode(postcode);
		dto.setAddress(address);
		dto.setDetailAddress(detailAddress);
		dto.setExtraAddress(extraAddress);
		
		int result = memberDao.setInsert(dto);
		model.addAttribute("menu_gubun", "member_writeProc");
		// System.out.println("result - :" + result);
/*
		try {
  			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정상적으로 등록되었습니다.');");
			out.println("suntaek_proc('list', '1', '');");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/		
	}
	
	@RequestMapping("/id_check.do")
	public void member_id_check(
			HttpServletResponse response,
			@RequestParam(value="id", defaultValue="") String id
			) throws IOException {
		int result = memberDao.getIdCheck(id);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/id_check_win.do")
	public String member_id_check_win() {
		return "member/id_check";
	}
	
	@RequestMapping("/id_check_win_open_Proc.do")
	public void member_id_check_win_open_Proc(
			HttpServletResponse response,
			@RequestParam(value="id", defaultValue="") String id
			) throws IOException {
		String result = memberDao.getIdCheckWin(id);   // db 아이디 리턴
		if (result == null || result.equals("")) {
			result = id;
		} else {
			result = "";
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
	
	@RequestMapping("/modify.do")
	public String member_modify(
			HttpServletResponse response,
			HttpServletRequest request, 
			Model model
			) throws IOException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		
		// no값 없거나 0이면 돌려보내기
		if (no == 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("chooseProc('list', '1', '');");
			out.println("</script>");
			return null;
		}
		
		MemberDTO dto = memberDao.getSelectOne(no, search_option, search_data);
		
		request.setAttribute("menu_gubun", "member_modify");
		request.setAttribute("dto", dto);
		
		return "member/modify";
	}
	
	@RequestMapping("/modifyProc.do")
	public void member_modifyProc(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model,
			@RequestParam(value="id", defaultValue="") String id,
			@RequestParam(value="passwd", defaultValue="") String passwd,
			@RequestParam(value="passwdChk", defaultValue="") String passwdChk,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="gender", defaultValue="") String gender,
			@RequestParam(value="bornYear", defaultValue="0") int bornYear,
			@RequestParam(value="sample6_postcode", defaultValue="") String postcode,
			@RequestParam(value="sample6_address", defaultValue="") String address,
			@RequestParam(value="sample6_detailAddress", defaultValue="") String detailAddress,
			@RequestParam(value="sample6_extraAddress", defaultValue="") String extraAddress
			) throws IOException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		
		// 비밀번호 체크
		MemberDTO dbDto = memberDao.getSelectOne(no, search_option, search_data);
		String dbPasswd = dbDto.getPasswd();
		boolean isSamePasswd = (passwd.equals(dbPasswd));

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDTO dto = new MemberDTO();
		if (isSamePasswd) {
			dto.setNo(no);
			dto.setPasswd(passwd);
			dto.setBornYear(bornYear);
			dto.setPostcode(postcode);
			dto.setAddress(address);
			dto.setDetailAddress(detailAddress);
			dto.setExtraAddress(extraAddress);
			
			int result = memberDao.setUpdate(dto);
			out.println("<script>alert('수정완료'); chooseProc('view', '1', '" + no + "');</script>");
		} else {
			out.println("<script>alert('비밀번호오류'); chooseProc('view', '1', '" + no + "');</script>");
		}
		out.flush();
		out.close(); 
	}
	
	@RequestMapping("/delete.do")
	public String member_delete(
			HttpServletResponse response,
			HttpServletRequest request, 
			Model model
			) throws IOException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		
		// no값 없거나 0이면 돌려보내기
		if (no == 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("chooseProc('list', '1', '');");
			out.println("</script>");
			return null;
		}
		
		MemberDTO dto = memberDao.getSelectOne(no, search_option, search_data);
		
		request.setAttribute("menu_gubun", "member_delete");
		request.setAttribute("dto", dto);
		
		return "member/delete";
	}
	
	@RequestMapping("/deleteProc.do")
	public void member_deleteProc(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value="passwd", defaultValue="") String passwd) throws IOException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		
		// 비밀번호 체크
		MemberDTO dbDto = memberDao.getSelectOne(no, search_option, search_data);
		String dbPasswd = dbDto.getPasswd();
		boolean isSamePasswd = (passwd.equals(dbPasswd));

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDTO dto = new MemberDTO();
		if (isSamePasswd) {
			dto.setNo(no);
			dto.setPasswd(passwd);
			
			int result = memberDao.setDelete(dto);
			HttpSession session = request.getSession();
			session.invalidate();
			out.println("<script>alert('삭제완료'); chooseProc('list', '1', '');</script>");
//			out.println("<script>alert('삭제완료');");
//			out.println("location.href='" + path + "';");
//			out.println("</script>");
		} else {
			out.println("<script>alert('비밀번호오류'); chooseProc('view', '1', '" + no + "');</script>");
		}
		out.flush();
		out.close(); 
	}
	
	@RequestMapping("/goLogin.do")
	public String goLogin(Model model) {
		model.addAttribute("menu_gubun", "member_login");
		return "main/main";
	}
	
	@RequestMapping("/login.do")
	public String login(Model model) {
		model.addAttribute("menu_gubun", "member_login");
		return "member/login";
	}
	
	@RequestMapping("/loginProc.do")
	public String loginProc(
			HttpServletRequest request,
			@RequestParam(value="id", defaultValue="") String id,
			@RequestParam(value="passwd", defaultValue="") String passwd
			) throws UnknownHostException {
		System.out.println("here is loginProc");
		Map<String, Object> map = topInfo(request);
		String path = (String)map.get("path");
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPasswd(passwd);
		
		MemberDTO resultDto = memberDao.getLogin(dto);
		
		String temp = "";
		
		if (resultDto == null) {
			temp = "/member/login";
		} else if (resultDto.getNo() == 0) { // 실패
			temp = "/member/login";
		} else { // 성공
			// 세션 등록
			HttpSession session = request.getSession();
			
			session.setAttribute("cookNo", resultDto.getNo());
			session.setAttribute("cookId", resultDto.getId());
			session.setAttribute("cookName", resultDto.getName());
			temp = "main/main";
		}
		System.out.println(temp);
		return temp;
	}
	
	@RequestMapping("/logout.do")
	public void member_logout(
			HttpServletRequest request,
			HttpServletResponse response
			) throws IOException {
		// 세션 해제
		HttpSession session = request.getSession();
		session.invalidate();
		
		Map<String, Object> map = topInfo(request);
		String path = (String)map.get("path");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.');");
		out.println("location.href='" + path + "';");
		out.println("</script>");
	}
 }
