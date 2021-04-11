package com.test.springStudy.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.springStudy.common.Util;
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
	
	@RequestMapping("/result.do")
	public String survey_result(
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
			) throws IOException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		SurveyDTO dto = surveyDao.getSelectOne(no);
		Map<String, Object> surveyNoAnswers = surveyDao.getSurveyNoAnswers(no);
		ArrayList<Integer> answersList = new ArrayList<>();
		ArrayList<String> answersResponseRate = new ArrayList<>();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (surveyNoAnswers == null) {
			out.println("<script>alert('자료가 없습니다'); chooseProc('view', '1', '" + no + "');</script>");
			out.flush();
			out.close();
			return null;
		}
		
		if (surveyNoAnswers != null) {
			answersList.add(((BigDecimal)surveyNoAnswers.get("COUNT_OF_1")).intValue());
			answersList.add(((BigDecimal)surveyNoAnswers.get("COUNT_OF_2")).intValue());
			answersList.add(((BigDecimal)surveyNoAnswers.get("COUNT_OF_3")).intValue());
			answersList.add(((BigDecimal)surveyNoAnswers.get("COUNT_OF_4")).intValue());
			
			int totalAnswerCount = 0;
			totalAnswerCount += ((BigDecimal)surveyNoAnswers.get("COUNT_OF_1")).intValue();
			totalAnswerCount += ((BigDecimal)surveyNoAnswers.get("COUNT_OF_2")).intValue();
			totalAnswerCount += ((BigDecimal)surveyNoAnswers.get("COUNT_OF_3")).intValue();
			totalAnswerCount += ((BigDecimal)surveyNoAnswers.get("COUNT_OF_4")).intValue();
			
			for (int i = 0; i < answersList.size(); i++) {
				String responseRate = String.format("%.2f", (double)answersList.get(i) / totalAnswerCount * 100);
				answersResponseRate.add(responseRate);
			}				
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("answersList", answersList);
		model.addAttribute("answersResponseRate", answersResponseRate);
		
		return "survey/result";
	}
	
	@RequestMapping("write.do")
	public String survey_write(Model model) {
		int[] yearMonthDayHourMinSec = util.getDateTime();
		HashMap<String, Integer> yearMonthDayMap = new HashMap<>();
		yearMonthDayMap.put("nowYear", yearMonthDayHourMinSec[0]);
		yearMonthDayMap.put("nowMonth", yearMonthDayHourMinSec[1]);
		yearMonthDayMap.put("nowDay", yearMonthDayHourMinSec[2]);
		
		model.addAttribute("yearMonthDayMap", yearMonthDayMap);
		model.addAttribute("menu_gubun", "survey_write");
		return "survey/write";
	}
	
	@RequestMapping("writeProc.do")
	public void survey_writeProc(
			HttpServletResponse response,
			Model model,
			@RequestParam(value="question", defaultValue="") String question,
			@RequestParam(value="ans1", defaultValue="") String ans1,
			@RequestParam(value="ans2", defaultValue="") String ans2,
			@RequestParam(value="ans3", defaultValue="") String ans3,
			@RequestParam(value="ans4", defaultValue="") String ans4,
			@RequestParam(value="status", defaultValue="") String status,
			@RequestParam(value="startYear", defaultValue="") String startYear,
			@RequestParam(value="startMonth", defaultValue="") String startMonth,
			@RequestParam(value="startDay", defaultValue="") String startDay,
			@RequestParam(value="lastYear", defaultValue="") String lastYear,
			@RequestParam(value="lastMonth", defaultValue="") String lastMonth,
			@RequestParam(value="lastDay", defaultValue="") String lastDay
			) {
		String start_date_ = "";
		String last_date_ = "";
		start_date_ += startYear + "-" + startMonth + "-" + startDay;
		start_date_ += " 00:00:00.0";
		last_date_ += lastYear + "-" + lastMonth + "-" + lastDay;
		last_date_ += " 23:59:59.9";
		Timestamp start_date = Timestamp.valueOf(start_date_);
		Timestamp last_date = Timestamp.valueOf(last_date_);
		
		SurveyDTO dto = new SurveyDTO();
		dto.setQuestion(question);
		dto.setAns1(ans1);
		dto.setAns2(ans2);
		dto.setAns3(ans3);
		dto.setAns4(ans4);
		dto.setStatus(status);
		dto.setStart_date(start_date);
		dto.setLast_date(last_date);
		
		int result = surveyDao.setInsertQuestion(dto);
		
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
	public String survey_modify(
			HttpServletResponse response,
			HttpServletRequest request, 
			Model model
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		SurveyDTO dto = surveyDao.getSelectOne(no);
		
		Date date = new Date();
		Timestamp start_date = dto.getStart_date();
		date.setTime(start_date.getTime());
		String startDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String startYear = startDate.split("[-]")[0];
		String startMonth = startDate.split("[-]")[1];
		String startDay = startDate.split("[-]")[2];
		
		Timestamp last_date = dto.getLast_date();
		date.setTime(last_date.getTime());
		String lastDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String lastYear = lastDate.split("[-]")[0];
		String lastMonth = lastDate.split("[-]")[1];
		String lastDay = lastDate.split("[-]")[2];
		
		int[] yearMonthDayHourMinSec = util.getDateTime();
		HashMap<String, Integer> yearMonthDayMap = new HashMap<>();
		yearMonthDayMap.put("nowYear", yearMonthDayHourMinSec[0]);
		yearMonthDayMap.put("nowMonth", yearMonthDayHourMinSec[1]);
		yearMonthDayMap.put("nowDay", yearMonthDayHourMinSec[2]);
		
		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("menu_gubun", "survey_modify");
		request.setAttribute("dto", dto);
		request.setAttribute("startYear", startYear);
		request.setAttribute("startMonth", startMonth);
		request.setAttribute("startDay", startDay);
		request.setAttribute("lastYear", lastYear);
		request.setAttribute("lastMonth", lastMonth);
		request.setAttribute("lastDay", lastDay);
		
		return "survey/modify";
	}
	
	@RequestMapping("/modifyProc.do")
	public void survey_modifyProc(
			HttpServletResponse response,
			HttpServletRequest request,
			Model model,
			@RequestParam(value="question", defaultValue="") String question,
			@RequestParam(value="ans1", defaultValue="") String ans1,
			@RequestParam(value="ans2", defaultValue="") String ans2,
			@RequestParam(value="ans3", defaultValue="") String ans3,
			@RequestParam(value="ans4", defaultValue="") String ans4,
			@RequestParam(value="status", defaultValue="") String status,
			@RequestParam(value="startYear", defaultValue="") String startYear,
			@RequestParam(value="startMonth", defaultValue="") String startMonth,
			@RequestParam(value="startDay", defaultValue="") String startDay,
			@RequestParam(value="lastYear", defaultValue="") String lastYear,
			@RequestParam(value="lastMonth", defaultValue="") String lastMonth,
			@RequestParam(value="lastDay", defaultValue="") String lastDay
			) throws UnknownHostException {
		String start_date_ = "";
		String last_date_ = "";
		start_date_ += startYear + "-" + startMonth + "-" + startDay;
		start_date_ += " 00:00:00.0";
		last_date_ += lastYear + "-" + lastMonth + "-" + lastDay;
		last_date_ += " 23:59:59.9";
		Timestamp start_date = Timestamp.valueOf(start_date_);
		Timestamp last_date = Timestamp.valueOf(last_date_);
		
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		SurveyDTO dto = new SurveyDTO();
		dto.setQuestion(question);
		dto.setAns1(ans1);
		dto.setAns2(ans2);
		dto.setAns3(ans3);
		dto.setAns4(ans4);
		dto.setStatus(status);
		dto.setStart_date(start_date);
		dto.setLast_date(last_date);
		dto.setNo(no);
		
		int result = surveyDao.setUpdateQuestion(dto);
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
	public String survey_delete(
			HttpServletRequest request,
			Model model
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		
		SurveyDTO dto = surveyDao.getSelectOne(no);
		
		model.addAttribute("menu_gubun", "survey_delete");
		model.addAttribute("dto", dto);
		
		return "survey/delete";
	}
	
	@RequestMapping("/deleteProc.do")
	public void survey_deleteProc(
			HttpServletRequest request,
			HttpServletResponse response
			) throws UnknownHostException {
		Map<String, Object> map = topInfo(request);
		int no = (int)map.get("no");
		SurveyDTO dto = surveyDao.getSelectOne(no);
		int result = surveyDao.setDeleteQuestion(dto);
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
	
	@RequestMapping("/saveProc.do")
	@ResponseBody
	public String survey_saveProc(
			@RequestParam(value="answer_total", defaultValue="") String answer_total
			) {
		String[] answer_totalArr = answer_total.split("[|]");
		
		for (int i = 0; i < answer_totalArr.length; i++) {
			String[] imsiArr = answer_totalArr[i].split(":");
			int tempNo = Integer.parseInt(imsiArr[0]);
			int tempAnswer = Integer.parseInt(imsiArr[1]);
			
			SurveyAnswerDTO answerDto = new SurveyAnswerDTO();
			answerDto.setNo(tempNo);
			answerDto.setAnswer(tempAnswer);
			
			surveyDao.setInsertAnswer(answerDto);
		}
		
		return "";
	}
}
