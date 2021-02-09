package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.UtilBoard;
import model.board.dao.BoardDAO;
import model.board.dto.BoardDTO;
import model.survey.dto.SurveyDTO;

@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilBoard util = new UtilBoard();
		
		int[] yearMonthDayHourMinSec = util.getDateTime();
		HashMap<String, Integer> yearMonthDayMap = new HashMap<>();
		yearMonthDayMap.put("nowYear", yearMonthDayHourMinSec[0]);
		yearMonthDayMap.put("nowMonth", yearMonthDayHourMinSec[1]);
		yearMonthDayMap.put("nowDay", yearMonthDayHourMinSec[2]);
		
		String serverInfo[] = util.getServerInfo(request); //request.getContextPath();
		String referer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];
		
		String pageNum_ = request.getParameter("pageNumber");
		int pageNum = util.numberCheck(pageNum_, 1);
		
		String tbl_ = request.getParameter("tbl_");
		String tbl = util.tblCheck(tbl_, "freeboard");
		
		String no_ = request.getParameter("no");
		int no = util.numberCheck(no_, 0);
		
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];
		
		String[] sessionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sessionArray[0]);
		String cookId = sessionArray[1];
		String cookName = sessionArray[2];

		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("ip", ip);
		request.setAttribute("tbl", tbl);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		String page = "/main/main.jsp";
		
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "board_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("write.do") != -1 || url.indexOf("reply.do") != -1) {
			request.setAttribute("menu_gubun", "board_write");
			page = "/board/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1) {
			String writer = request.getParameter("writer");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String noticeGubun = request.getParameter("noticeGubun");
			
			int noticeNo;
			if (noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
				noticeNo = 0;
			} else {
				noticeNo = dao.getMaxNoticeNo(tbl) + 1;
			}
			
			String secretGubun = request.getParameter("secretGubun");
			if (secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
				secretGubun = "F";
			} else {
				secretGubun = "T";
			}
			
			int num = dao.getMaxNum() + 1;
			int refNo = dao.getMaxRefNo() + 1; // 글 그룹을 의미, 쿼리를 실행시켜서 가장 큰 ref 값을 가져온 후 + 1
			int stepNo = 1;
			int levelNo = 1;
			int parentNo = 0;
			
			int hit = 0;
			
			dto.setNo(no);
			dto.setNum(num);
			dto.setTbl(tbl);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			
			dto.setRefNo(refNo);
			dto.setStepNo(stepNo);
			dto.setLevelNo(levelNo);
			dto.setParentNo(parentNo);
			dto.setHit(hit);
			dto.setIp(ip);
			
			dto.setMemberNo(cookNo);
			dto.setNoticeNo(noticeNo);
			dto.setSecretGubun(secretGubun);
			
			int result = dao.setInsert(dto);
			
			
		} else if (url.indexOf("list.do") != -1) {	
			// paging
			int allRowsCount = dao.getAllRowsCount(tbl, search_option, search_data);
			final int ONE_PAGE_ROWS = 10;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			
			ArrayList<BoardDTO> list = dao.getPagingList(startNum, endNum, tbl, search_option, search_data);
			
			request.setAttribute("menu_gubun", "board_list");
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
			
			page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

}
