package controller.survey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Util;
import model.survey.dao.SurveyDAO;
import model.survey.dto.SurveyAnswerDTO;
import model.survey.dto.SurveyDTO;

@WebServlet("/survey_servlet/*")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String page = "/main/main.jsp";
		Util util = new Util();
		
		int[] yearMonthDayHourMinSec = util.getDateTime();
		HashMap<String, Integer> yearMonthDayMap = new HashMap<>();
		yearMonthDayMap.put("nowYear", yearMonthDayHourMinSec[0]);
		yearMonthDayMap.put("nowMonth", yearMonthDayHourMinSec[1]);
		yearMonthDayMap.put("nowDay", yearMonthDayHourMinSec[2]);
		
		String pageNum_ = request.getParameter("pageNumber");
		int pageNum = util.numberCheck(pageNum_, 1);
		
		String no_ = request.getParameter("no");
		int no = util.numberCheck(no_, 0);
		
		String list_gubun = request.getParameter("list_gubun");
		list_gubun = util.list_gubunCheck(list_gubun);
		
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
		
		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("list_gubun", list_gubun);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		request.setAttribute("search_date_start", search_date_start);
		request.setAttribute("search_date_end", search_date_end);
		request.setAttribute("search_date_check", search_date_check);
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "survey_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "survey_write");
			page = "/survey/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1) {
			String question = request.getParameter("question");
			String ans1 = request.getParameter("ans1");
			String ans2 = request.getParameter("ans2");
			String ans3 = request.getParameter("ans3");
			String ans4 = request.getParameter("ans4");
			String status = request.getParameter("status");
			String startYear = request.getParameter("startYear");
			String startMonth = request.getParameter("startMonth");
			String startDay = request.getParameter("startDay");
			String lastYear = request.getParameter("lastYear");
			String lastMonth = request.getParameter("lastMonth");
			String lastDay = request.getParameter("lastDay");
			String start_date_ = "";
			String last_date_ = "";
			start_date_ += startYear + "-" + startMonth + "-" + startDay;
			start_date_ += " 00:00:00.0";
			last_date_ += lastYear + "-" + lastMonth + "-" + lastDay;
			last_date_ += " 23:59:59.9";
			java.sql.Timestamp start_date = java.sql.Timestamp.valueOf(start_date_);
			java.sql.Timestamp last_date = java.sql.Timestamp.valueOf(last_date_);
			
			SurveyDTO dto = new SurveyDTO();
			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
			
			SurveyDAO dao = new SurveyDAO();
			String writeProcTemp;
			dao.setInsertQuestion(dto);
			writeProcTemp = path + "/survey/list.jsp";
			response.sendRedirect(writeProcTemp);
			
			
		} else if (url.indexOf("list.do") != -1 || url.indexOf("detailedList.do") != -1) {
			
			if (url.indexOf("list.do") != -1) {
				page = "/survey/list.jsp";
			} else {
				page = "/survey/detailedList.jsp";
			}
			
			SurveyDAO dao = new SurveyDAO();
			
			// paging
			final int ONE_PAGE_ROWS = 5;
			final int MAX_PAGING_WIDTH = 10;
			int allRowsCount = dao.getAllRowsCount(list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
			int maxPagesCount = (int) Math.ceil((double) allRowsCount / ONE_PAGE_ROWS);
			int tableRowNum = allRowsCount - (pageNum - 1) * ONE_PAGE_ROWS;
			int pagingLoopNum = (int) Math.ceil((double)pageNum / MAX_PAGING_WIDTH) - 1;
			int pagingStartNum = pagingLoopNum * MAX_PAGING_WIDTH + 1;
			int pagingEndNum = pagingStartNum + MAX_PAGING_WIDTH - 1;
			if (pagingEndNum > maxPagesCount) {
				pagingEndNum = maxPagesCount;
			}
			int endNum = pageNum * ONE_PAGE_ROWS;
			int startNum = endNum - ONE_PAGE_ROWS + 1;
			
			ArrayList<SurveyDTO> list = dao.getPagingList(startNum, endNum, list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
			
			request.setAttribute("menu_gubun", "member_list");
			request.setAttribute("list", list);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("tableRowNum", tableRowNum);
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);			
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("view.do") != -1) {
			SurveyDAO dao = new SurveyDAO();
			SurveyDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/survey/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("viewProc.do") != -1) {
			SurveyDAO dao = new SurveyDAO();
			SurveyAnswerDTO dto = new SurveyAnswerDTO();
			
			String answer_ = request.getParameter("answer");
			int answer = Integer.parseInt(answer_);
			
			dto.setNo(no);
			dto.setAnswer(answer);
			
			dao.setInsertAnswer(dto);
			
			page = "/survey_servlet/index.do";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("result.do") != -1) {
			SurveyDAO dao = new SurveyDAO();
			SurveyDTO dto = dao.getSelectOne(no);
			ArrayList<Integer> surveyNoAnswers = dao.getSurveyNoAnswers(no);
			int totalAnswerCount = 0;
			for (int i = 0; i < surveyNoAnswers.size(); i++) {
				totalAnswerCount += surveyNoAnswers.get(i);
			}
			
			ArrayList<String> answersResponseRate = new ArrayList<>();
			
			for (int i = 0; i < surveyNoAnswers.size(); i++) {
				String responseRate = String.format("%.2f", (double)surveyNoAnswers.get(i) / totalAnswerCount * 100);
				answersResponseRate.add(responseRate);
			}
			
			request.setAttribute("dto", dto);
			request.setAttribute("surveyNoAnswers", surveyNoAnswers);
			request.setAttribute("answersResponseRate", answersResponseRate);
			
			page = "/survey/result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("saveProc.do") != -1) {
			SurveyDAO dao = new SurveyDAO();
			
			String answer_total = request.getParameter("answer_total");
			String[] answer_totalArr = answer_total.split("[|]");
			
			for (int i = 0; i < answer_totalArr.length; i++) {
				String[] imsiArr = answer_totalArr[i].split(":");
				int tempNo = Integer.parseInt(imsiArr[0]);
				int tempAnswer = Integer.parseInt(imsiArr[1]);
				
				SurveyAnswerDTO answerDto = new SurveyAnswerDTO();
				answerDto.setNo(tempNo);
				answerDto.setAnswer(tempAnswer);
				
				dao.setInsertAnswer(answerDto);
			}	
		}
	}
}
