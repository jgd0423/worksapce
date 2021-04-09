package com.test.springStudy.survey.controller;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.springStudy.common.Util;
import com.test.springStudy.guestbook.model.dto.GuestbookDTO;
import com.test.springStudy.survey.model.dao.SurveyDAO;
import com.test.springStudy.survey.model.dto.SurveyAnswerDTO;
import com.test.springStudy.survey.model.dto.SurveyDTO;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	// --- 공통부분 ---
	@Inject
	SurveyDAO surveyDao;   // 수정할 부분
	
	Util util = new Util();   // 수정할 부분
	
    public Map<String, Object> topInfo(HttpServletRequest request) throws UnknownHostException {
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String search_date_check = request.getParameter("search_date_check");
		String search_date_start = request.getParameter("search_date_start");
		String search_date_end = request.getParameter("search_date_end");
		String[] searchArray = util.searchCheck(search_option, search_data, search_date_start, search_date_end, search_date_check);
		search_option = searchArray[0];
		search_data = searchArray[1];
		search_date_start = searchArray[2];
		search_date_end = searchArray[3];
		search_date_check = searchArray[4];
		
		String list_gubun = request.getParameter("list_gubun");
		list_gubun = util.list_gubunCheck(list_gubun);
		
		Map<String, Object> map = util.basicInfo(request);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("search_date_start", search_date_start);
		map.put("search_date_end", search_date_end);
		map.put("search_date_check", search_date_check);
		map.put("list_gubun", list_gubun);
		return map;
	}
    // --- 공통부분 ---
	
	@RequestMapping("/index.do")
	public String survey_index(Model model) {
		model.addAttribute("menu_gubun", "survey_index");
		return "main/main";
	}
	
	@RequestMapping("/list.do")
	public String survey_list(HttpServletRequest request, Model model) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int pageNum = (int)map.get("pageNumber");
		String list_gubun = (String)map.get("list_gubun");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		String search_date_check = (String)map.get("search_date_check");
		String search_date_start = (String)map.get("search_date_start");
		String search_date_end = (String)map.get("search_date_end");
		
		// paging
		int allRowsCount = surveyDao.getAllRowsCount(list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
		
		final int ONE_PAGE_ROWS = 5;
		final int MAX_PAGING_WIDTH = 10;
		
		int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
		int tableRowNum = pagerArr[0];
		int pagingStartNum = pagerArr[1];
		int pagingEndNum = pagerArr[2];
		int maxPagesCount = pagerArr[3];
		int startNum = pagerArr[4];
		int endNum = pagerArr[5];	
		
		List<SurveyDTO> list = surveyDao.getPagingList(startNum, endNum, list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
		
		request.setAttribute("list", list);
		request.setAttribute("allRowsCount", allRowsCount);
		request.setAttribute("tableRowNum", tableRowNum);
		request.setAttribute("maxPagesCount", maxPagesCount);
		request.setAttribute("pagingStartNum", pagingStartNum);
		request.setAttribute("pagingEndNum", pagingEndNum);			
		
		model.addAttribute("menu_gubun", "survey_list");
		return "survey/list";
	}
	
	@RequestMapping("/detailedList.do")
	public String survey_detailedList(HttpServletRequest request, Model model) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int pageNum = (int)map.get("pageNumber");
		String list_gubun = (String)map.get("list_gubun");
		String search_option = (String)map.get("search_option");
		String search_data = (String)map.get("search_data");
		String search_date_check = (String)map.get("search_date_check");
		String search_date_start = (String)map.get("search_date_start");
		String search_date_end = (String)map.get("search_date_end");
		
		// paging
		int allRowsCount = surveyDao.getAllRowsCount(list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
		
		final int ONE_PAGE_ROWS = 5;
		final int MAX_PAGING_WIDTH = 10;
		
		int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
		int tableRowNum = pagerArr[0];
		int pagingStartNum = pagerArr[1];
		int pagingEndNum = pagerArr[2];
		int maxPagesCount = pagerArr[3];
		int startNum = pagerArr[4];
		int endNum = pagerArr[5];	
		
		List<SurveyDTO> list = surveyDao.getPagingList(startNum, endNum, list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
		
		request.setAttribute("list", list);
		request.setAttribute("allRowsCount", allRowsCount);
		request.setAttribute("tableRowNum", tableRowNum);
		request.setAttribute("maxPagesCount", maxPagesCount);
		request.setAttribute("pagingStartNum", pagingStartNum);
		request.setAttribute("pagingEndNum", pagingEndNum);			
		
		model.addAttribute("menu_gubun", "questionBank_list");
		return "survey/detailedList";
	}
	
	@RequestMapping("/view.do")
	public String survey_view(
			HttpServletRequest request, Model model
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		SurveyDTO dto = surveyDao.getSelectOne(no);
		
		model.addAttribute("menu_gubun", "survey_view");
		model.addAttribute("dto", dto);
		return "survey/view";
	}
	
	@RequestMapping("/viewProc.do")
	public String survey_viewProc(
			HttpServletRequest request,
			@RequestParam(value="answer", defaultValue="") int answer
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		SurveyAnswerDTO surveyAnswerDto = new SurveyAnswerDTO();
		
		
		surveyAnswerDto.setAnswer(answer);
		surveyAnswerDto.setNo(no);
		surveyDao.setInsertAnswer(surveyAnswerDto);
		
		return "redirect:index.do";
	}
}
