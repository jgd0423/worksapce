package com.test.springStudy.memo.controller;

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

import com.test.springStudy.common.Util;
import com.test.springStudy.memo.model.dao.MemoDAO;
import com.test.springStudy.memo.model.dto.MemoDTO;

@Controller
@RequestMapping("/memo")
public class MemoController {
	// --- 공통부분 ---
	@Inject
	MemoDAO memoDao;
	
	Util util = new Util();
	
    public Map<String, Object> topInfo(HttpServletRequest request) throws UnknownHostException {
		Map<String, Object> map = util.basicInfo(request);
		return map;
	}
	// --- 공통부분 ---
	
	@RequestMapping("/index.do")
	public String memo_index(Model model) {
		model.addAttribute("menu_gubun", "memo_index");
		return "main/main";
	}
	
	@RequestMapping("/list.do")
	public String memo_list(
			HttpServletRequest request,
			Model model
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int pageNum = (int)map.get("pageNumber");
		int no = (int)map.get("no");
		
		// paging
		int allRowsCount = memoDao.getAllRowsCount();
		final int ONE_PAGE_ROWS = 8;
		final int MAX_PAGING_WIDTH = 10;
		
		int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
		int tableRowNum = pagerArr[0];
		int pagingStartNum = pagerArr[1];
		int pagingEndNum = pagerArr[2];
		int maxPagesCount = pagerArr[3];
		int startNum = pagerArr[4];
		int endNum = pagerArr[5];	
		
		List<MemoDTO> list = memoDao.getPagingList(startNum, endNum);
		
		model.addAttribute("menu_gubun", "memo_list");
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
		
		return "memo/list";
	}
	
	@RequestMapping("/writeProc.do")
	public void memo_writeProc(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="writer", defaultValue="") String writer,
			@RequestParam(value="content", defaultValue="") String content
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		MemoDTO dto = new MemoDTO();
		dto.setWriter(writer);
		dto.setContent(content);

		if (no > 0) {
			dto.setNo(no);
			memoDao.setUpdate(dto);	
		} else {
			memoDao.setInsert(dto);
		}

		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("<script>");
			out.println("chooseProc('list', '1', '');");
			out.println("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/deleteProc.do")
	public void memo_deleteProc(
			HttpServletRequest request,
			HttpServletResponse response
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		MemoDTO dto = new MemoDTO();
		dto.setNo(no);
		memoDao.setDelete(dto);
		
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out;
			out = response.getWriter();
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
