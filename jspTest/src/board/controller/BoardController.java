package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.common.UtilBoard;
import board.model.dao.BoardDAO;
import board.model.dto.BoardDTO;

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
		
		String serverInfo[] = util.getServerInfo(request);   // request.getContextPath();
		String referer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
		
		String pageNum_ = request.getParameter("pageNumber");
		int pageNum = util.numberCheck(pageNum_, 1);
		
		String no_ = request.getParameter("no");
		int no = util.numberCheck(no_, 0);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		if (url.indexOf("writeProc.do") != -1) {
			String writer = request.getParameter("writer");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPasswd(passwd);
			
			String temp;
			int result = dao.setInsert(dto);
			if (result > 0) {
				temp = path + "/board_servlet/list.do";
			} else {
				temp = path + "/board_servlet/write.do";
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("list.do") != -1) {	
			// paging
			int allRowsCount = dao.getAllRowsCount();
			final int ONE_PAGE_ROWS = 3;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			ArrayList<BoardDTO> list = dao.getPagingList(startNum, endNum);
			
			request.setAttribute("menu_gubun", "board2_list");
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
			
			String page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("view.do") != -1) {			
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			
			// content의 줄바꿈
			String content = dto.getContent();
			content = content.replace("\n", "</br>");
			dto.setContent(content);

			String page = "/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("write.do") != -1) {
			String page = "/board/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modify.do") != -1) {	
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			
			String page = "/board/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modifyProc.do") != -1) {
			String writer = request.getParameter("writer");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			dto = dao.getView(no);
			
			// 비밀번호 체크
			String dbPasswd = dto.getPasswd();
			boolean isSamePasswd = (passwd.equals(dbPasswd));
			
			if (!isSamePasswd) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
		
			dto.setWriter(writer);
			dto.setPasswd(passwd);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setNo(no);
			
			int result = dao.setUpdate(dto);				
			
			String temp;
			if (result > 0) {
				temp = path + "/board_servlet/view.do?no=" + no;
			} else {
				temp = path + "/board_servlet/modify.do?no=" + no;
			}
			response.sendRedirect(temp);
			
		} else if (url.indexOf("delete.do") != -1) {
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			
			String page = "/board/delete.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("deleteProc.do") != -1) {
			dto = dao.getView(no);
			String passwd = request.getParameter("passwd");

			// 비밀번호 체크
			String dbPasswd = dto.getPasswd();
			boolean isSamePasswd = (passwd.equals(dbPasswd));
			
			if (!isSamePasswd) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			
			int result = dao.setDelete(dto);
			
			String temp;
			if (result > 0) {
				temp = path + "/board_servlet/list.do";
			} else {
				temp = path + "/board_servlet/delete.do?no=" + no;
			}
			response.sendRedirect(temp);
			
		}
	}
}
