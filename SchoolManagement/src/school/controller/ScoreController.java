package school.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.s;
import school.common.Util;
import school.model.dao.ScoreDAO;
import school.model.dto.ExamDTO;
import school.model.dto.ScoreDTO;
import school.model.dto.StudentDTO;

@WebServlet("/score_servlet/*")
public class ScoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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
		
		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		
		ScoreDAO dao = new ScoreDAO();
		ScoreDTO dto = new ScoreDTO();
		
		String page = "/main/main.jsp";
		
		if (url.indexOf("write.do") != -1) {
			String grade = request.getParameter("grade");
			String classes = request.getParameter("classes");
			
			ArrayList<String> classesList = new ArrayList<>();
			if (grade != null) {
				classesList = dao.getClassesList(grade);
			}
			
			ArrayList<StudentDTO> studentIdNameList = new ArrayList<>();
			if (classes != null) {
				studentIdNameList = dao.getIdNameList(grade, classes);
			}
			
			ArrayList<ExamDTO> examList = new ArrayList<>();
			examList = dao.getExamList();
			
			request.setAttribute("menu_gubun", "score_write");
			request.setAttribute("grade", grade);
			request.setAttribute("classes", classes);
			request.setAttribute("classesList", classesList);
			request.setAttribute("studentIdNameList", studentIdNameList);
			request.setAttribute("examList", examList);
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1) {
			String[] studentId = request.getParameterValues("studentId");
			String[] korean_ = request.getParameterValues("korean");
			String[] english_ = request.getParameterValues("english");
			String[] math_ = request.getParameterValues("math");
			String[] science_ = request.getParameterValues("science");
			String[] history_ = request.getParameterValues("history");
			String examId_ = request.getParameter("examId");
			int examId = Integer.parseInt(examId_);
			
			for (int i = 0; i < studentId.length; i ++) {
				int korean = Integer.parseInt(korean_[i]);
				int english = Integer.parseInt(english_[i]);
				int math = Integer.parseInt(math_[i]);
				int science = Integer.parseInt(science_[i]);
				int history = Integer.parseInt(history_[i]);
				
				dto.setStudentId(studentId[i]);
				dto.setExamId(examId);
				dto.setKorean(korean);
				dto.setEnglish(english);
				dto.setMath(math);
				dto.setScience(science);
				dto.setHistory(history);
				
				int result = dao.setInsert(dto);
			}
			
			String temp;
		
			temp = path + "/score_servlet/list.do";
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("list.do") != -1) {				
			HashMap<String, ArrayList<String>> classesMap = dao.getClassesMap();
			String grade = request.getParameter("grade");
			String classes = request.getParameter("classes");
			String examId = request.getParameter("examId");
			String studentName = request.getParameter("studentName");
			String[] searchCheck = util.searchCheck(grade, classes, examId, studentName);
			grade = searchCheck[0];
			classes = searchCheck[1];
			examId = searchCheck[2];
			studentName = searchCheck[3];
			
			ArrayList<String> classesList = new ArrayList<>();
			
			if (grade != "") {
				classesList = classesMap.get(grade);
			}
			
			ArrayList<ExamDTO> examList = new ArrayList<>();
			examList = dao.getExamList();
			
			// paging
			int allRowsCount = dao.getAllRowsCount(grade, classes, examId, studentName);
			final int ONE_PAGE_ROWS = 10;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			ArrayList<ScoreDTO> list = dao.getPagingList(startNum, endNum, grade, classes, examId, studentName);
			
			request.setAttribute("menu_gubun", "score_list");
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
			request.setAttribute("classesList", classesList);
			request.setAttribute("grade", grade);
			request.setAttribute("classes", classes);
			request.setAttribute("examId", examId);
			request.setAttribute("examList", examList);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		}
	}

}
