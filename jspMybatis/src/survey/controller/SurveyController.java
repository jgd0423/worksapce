package survey.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Util;
import survey.model.dao.SurveyDAO;
import survey.model.dto.SurveyAnswerDTO;
import survey.model.dto.SurveyDTO;

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
		String page = "/main/main.jsp";
		Util util = new Util();
		
		int[] yearMonthDayHourMinSec = util.getDateTime();
		HashMap<String, Integer> yearMonthDayMap = new HashMap<>();
		yearMonthDayMap.put("nowYear", yearMonthDayHourMinSec[0]);
		yearMonthDayMap.put("nowMonth", yearMonthDayHourMinSec[1]);
		yearMonthDayMap.put("nowDay", yearMonthDayHourMinSec[2]);
		
		String serverInfo[] = util.getServerInfo(request);   // request.getContextPath();
		String referer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
		// String ip6 = serverInfo[5];
		
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
		
		String[] sessionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sessionArray[0]);
		String cookId = sessionArray[1];
		String cookName = sessionArray[2];
		
		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("list_gubun", list_gubun);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		request.setAttribute("search_date_start", search_date_start);
		request.setAttribute("search_date_end", search_date_end);
		request.setAttribute("search_date_check", search_date_check);
		
		SurveyDAO dao = new SurveyDAO();
		SurveyDTO dto = new SurveyDTO();
		
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
			Timestamp start_date = Timestamp.valueOf(start_date_);
			Timestamp last_date = Timestamp.valueOf(last_date_);
			
			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
			
			int result = dao.setInsertQuestion(dto);
			
			
		} else if (url.indexOf("list.do") != -1 || url.indexOf("detailedList.do") != -1) {
			
			if (url.indexOf("list.do") != -1) {
				page = "/survey/list.jsp";
				request.setAttribute("menu_gubun", "survey_list");
			} else {
				page = "/survey/detailedList.jsp";
				request.setAttribute("menu_gubun", "questionBank_list");
			}
			
			// paging
			int allRowsCount = dao.getAllRowsCount(list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
			
			final int ONE_PAGE_ROWS = 5;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];	
			
			List<SurveyDTO> list = dao.getPagingList(startNum, endNum, list_gubun, search_option, search_data, search_date_start, search_date_end, search_date_check);
			
			request.setAttribute("list", list);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("tableRowNum", tableRowNum);
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);			
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("view.do") != -1) {
			dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			page = "/survey/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("viewProc.do") != -1) {
			dao = new SurveyDAO();
			SurveyAnswerDTO surveyAnswerDto = new SurveyAnswerDTO();
			
			String answer_ = request.getParameter("answer");
			int answer = Integer.parseInt(answer_);
			
			surveyAnswerDto.setAnswer(answer);
			surveyAnswerDto.setNo(no);
			dao.setInsertAnswer(surveyAnswerDto);
			
			page = "/survey_servlet/index.do";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("result.do") != -1) {
			dto = dao.getSelectOne(no);
			Map<String, Object> surveyNoAnswers = dao.getSurveyNoAnswers(no);
			ArrayList<Integer> answersList = new ArrayList<>();
			ArrayList<String> answersResponseRate = new ArrayList<>();
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (surveyNoAnswers == null) {
				out.println("<script>alert('자료가 없습니다'); chooseProc('view', '1', '" + no + "');</script>");
				out.flush();
				out.close();
				return;
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
			
			request.setAttribute("dto", dto);
			request.setAttribute("answersList", answersList);
			request.setAttribute("answersResponseRate", answersResponseRate);
			
			page = "/survey/result.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("saveProc.do") != -1) {
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
			
			
		} else if (url.indexOf("modify.do") != -1) {
			dto = dao.getSelectOne(no);
			
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
			
			request.setAttribute("menu_gubun", "member_modify");
			request.setAttribute("dto", dto);
			request.setAttribute("startYear", startYear);
			request.setAttribute("startMonth", startMonth);
			request.setAttribute("startDay", startDay);
			request.setAttribute("lastYear", lastYear);
			request.setAttribute("lastMonth", lastMonth);
			request.setAttribute("lastDay", lastDay);
			
			page = "/survey/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modifyProc.do") != -1) {
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
			Timestamp start_date = Timestamp.valueOf(start_date_);
			Timestamp last_date = Timestamp.valueOf(last_date_);
			
			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
			dto.setNo(no);
			
			int result = dao.setUpdateQuestion(dto);
			
			
		} else if (url.indexOf("delete.do") != -1) {
			dto = dao.getSelectOne(no);
			
			request.setAttribute("menu_gubun", "survey_delete");
			request.setAttribute("dto", dto);
			
			page = "/survey/delete.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("deleteProc.do") != -1) {
			dto.setNo(no);
			
			int result = dao.setDeleteQuestion(dto);
		}
	}
}
