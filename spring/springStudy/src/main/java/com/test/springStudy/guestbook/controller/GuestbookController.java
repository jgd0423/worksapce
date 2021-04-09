package com.test.springStudy.guestbook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.springStudy.guestbook.model.dao.GuestbookDAO;
import com.test.springStudy.guestbook.model.dto.GuestbookDTO;
import com.test.springStudy.guestbook.util.GuestbookUtil;
import com.test.springStudy.member.model.dto.MemberDTO;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	// --- 공통부분 ---
	@Inject
	GuestbookDAO guestbookDao;
	
	GuestbookUtil util = new GuestbookUtil();
	
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
	public String guestbook_index(Model model) {
		model.addAttribute("menu_gubun", "guestbook_index");
		return "main/main";
	}
	
	@RequestMapping("/list.do")
	public String guestbook_list(HttpServletRequest request, Model model) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int pageNum = (int)map.get("pageNumber");
		int no = (int)map.get("no");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		
		final int ONE_PAGE_ROWS = 4;
		final int MAX_PAGING_WIDTH = 10;
		int allRowsCount = guestbookDao.getAllRowsCount(search_option, search_data);
		int[] pagerArray = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
		int tableRowNum = pagerArray[0];
		int pagingStartNum = pagerArray[1];
		int pagingEndNum = pagerArray[2];
		int maxPagesCount = pagerArray[3];
		int startNum = pagerArray[4];
		int endNum = pagerArray[5];
		
		List<GuestbookDTO> list = guestbookDao.getPagingList(startNum, endNum, search_option, search_data);
		
		model.addAttribute("menu_gubun", "guestbook_list");
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
		
		return "guestbook/list";
	}
	
	@RequestMapping("/view.do")
	public String guestbook_view(HttpServletRequest request, Model model) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		GuestbookDTO dto = guestbookDao.getView(no);
		
		model.addAttribute("menu_gubun", "guestbook_view");
		model.addAttribute("dto", dto);
		return "guestbook/view";
	}
	
	@RequestMapping("/write.do")
	public String guestbook_write(Model model) {
		model.addAttribute("menu_gubun", "guestbook_write");
		return "guestbook/write";
	}
	
	@RequestMapping("/writeProc.do")
	public void guestbook_writeProc(
			HttpServletResponse response,
			Model model,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="passwd", defaultValue="") String passwd,
			@RequestParam(value="email", defaultValue="") String email,
			@RequestParam(value="content", defaultValue="") String content
			) {
		
		GuestbookDTO dto = new GuestbookDTO();
		dto.setName(name);
		dto.setPasswd(passwd);
		dto.setEmail(email);
		dto.setContent(content);
		
		int result = guestbookDao.setInsert(dto);
		model.addAttribute("menu_gubun", "guestbook_writeProc");

		try {
  			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("chooseProc('list', '1', '');");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/modify.do")
	public String guestbook_modify(
			Model model,
			HttpServletRequest request
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		GuestbookDTO dto = guestbookDao.getView(no);
		
		model.addAttribute("menu_gubun", "guestbook_modify");
		model.addAttribute("dto", dto);
		
		return "guestbook/modify";
	}
	
	@RequestMapping("/modifyProc.do")
	public void guestbook_modifyProc(
			HttpServletResponse response,
			HttpServletRequest request,
			Model model,
			@RequestParam(value="email", defaultValue="") String email,
			@RequestParam(value="content", defaultValue="") String content
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		GuestbookDTO dto = new GuestbookDTO();
		dto.setNo(no);
		dto.setEmail(email);
		dto.setContent(content);
		
		int result = guestbookDao.setUpdate(dto);
		model.addAttribute("menu_gubun", "guestbook_modifyProc");

		try {
  			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("chooseProc('list', '1', '');");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete.do")
	public String guestbook_delete(
			Model model,
			HttpServletRequest request
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		GuestbookDTO dto = guestbookDao.getView(no);
		
		model.addAttribute("menu_gubun", "guestbook_delete");
		model.addAttribute("dto", dto);
		
		return "guestbook/delete";
	}
	
	@RequestMapping("/deleteProc.do")
	public void guestbook_modifyProc(
			HttpServletResponse response,
			HttpServletRequest request,
			Model model
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		GuestbookDTO dto = new GuestbookDTO();
		dto.setNo(no);
		
		int result = guestbookDao.setDelete(dto);
		model.addAttribute("menu_gubun", "guestbook_deleteProc");

		try {
  			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("chooseProc('list', '1', '');");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
