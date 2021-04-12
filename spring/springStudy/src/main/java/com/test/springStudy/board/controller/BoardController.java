package com.test.springStudy.board.controller;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.springStudy.board.model.dao.BoardDAO;
import com.test.springStudy.board.model.dto.BoardDTO;
import com.test.springStudy.board.util.BoardUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	// --- 공통부분 ---
	@Inject
	BoardDAO boardDao;   // 수정할 부분
	
	BoardUtil util = new BoardUtil();   // 수정할 부분
	
    public Map<String, Object> topInfo(HttpServletRequest request) throws UnknownHostException {
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];

		String tbl_ = request.getParameter("tbl");
		String tbl = util.tblCheck(tbl_, "freeboard");
		ArrayList<String> tblStatus = util.tblStatus(tbl);
		String isUsingTable = tblStatus.get(0);
		String tableName = tblStatus.get(1);
		
		Map<String, Object> map = util.basicInfo(request);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("tbl", tbl);
		map.put("isUsingTable", isUsingTable);
		map.put("tableName", tableName);
		return map;
	}
    // --- 공통부분 ---
    
    @RequestMapping("/index.do")
    public String board_index(
    		Model model,
    		HttpServletRequest request
    		) throws UnknownHostException {
    	Map<String, Object> map = topInfo(request);
    	String tbl = (String)map.get("tbl");
    	
    	model.addAttribute("menu_gubun", "board_index");
    	model.addAttribute("menu_gubun_sub", tbl);
    	return "main/main";
    }
    
    @RequestMapping("/list.do")
    public String board_list(HttpServletRequest request, Model model) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int pageNum = (int)map.get("pageNumber");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		String tbl = (String)map.get("tbl");
		
		// paging
		int allRowsCount = boardDao.getAllRowsCount(tbl, search_option, search_data);
		final int ONE_PAGE_ROWS = 10;
		final int MAX_PAGING_WIDTH = 10;
		
		int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
		int tableRowNum = pagerArr[0];
		int pagingStartNum = pagerArr[1];
		int pagingEndNum = pagerArr[2];
		int maxPagesCount = pagerArr[3];
		int startNum = pagerArr[4];
		int endNum = pagerArr[5];
		
		List<BoardDTO> list = boardDao.getPagingList(startNum, endNum, tbl, search_option, search_data);
		
		request.setAttribute("list", list);
		
		request.setAttribute("ONE_PAGE_ROWS", ONE_PAGE_ROWS);
		request.setAttribute("MAX_PAGING_WIDTH", MAX_PAGING_WIDTH);
		request.setAttribute("allRowsCount", allRowsCount);
		request.setAttribute("tableRowNum", tableRowNum);
		
		request.setAttribute("pagingStartNum", pagingStartNum);
		request.setAttribute("pagingEndNum", pagingEndNum);
		
		request.setAttribute("maxPagesCount", maxPagesCount);
		request.setAttribute("pagingStartNum", pagingStartNum);
		request.setAttribute("pagingEndNum", pagingEndNum);		
		
		model.addAttribute("menu_gubun", "board_list");
    	return "board/list";
    }
}
