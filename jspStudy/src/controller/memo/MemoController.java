package controller.memo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.memo.dao.MemoDAO;
import model.memo.dto.MemoDTO;

/**
 * Servlet implementation class MemoController
 */
@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
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
		
		if (url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "memo_write");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1) {
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			MemoDTO dto = new MemoDTO();
			dto.setWriter(writer);
			dto.setContent(content);
			
			MemoDAO dao = new MemoDAO();
			String temp;
			dao.setInsert(dto);
			
			
		} else if (url.indexOf("list.do") != -1) {
			MemoDAO dao = new MemoDAO();
			
			String pageNum_ = request.getParameter("page");

			// validation. charAt을 이용하거나 정규표현식을 이용해 숫자만 남기고 걸러야함
			if (pageNum_ == null || pageNum_.trim().equals("") || pageNum_.equals("0")) {
				pageNum_ = "1";
			}
			
			// paging
			final int ONE_PAGE_ROWS = 8;
			final int MAX_PAGING_WIDTH = 10;
			int allRowsCount = dao.getAllRowsCount();
			int maxPagesCount = (int) Math.ceil((double) allRowsCount / ONE_PAGE_ROWS);
			int pageNum = Integer.parseInt(pageNum_);
			int tableRowNum = allRowsCount - (pageNum - 1) * ONE_PAGE_ROWS;
			int pagingLoopNum = (int) Math.ceil((double)pageNum / MAX_PAGING_WIDTH) - 1;
			int pagingStartNum = pagingLoopNum * MAX_PAGING_WIDTH + 1;
			int pagingEndNum = pagingStartNum + MAX_PAGING_WIDTH - 1;
			if (pagingEndNum > maxPagesCount) {
				pagingEndNum = maxPagesCount;
			}
			int endNum = pageNum * ONE_PAGE_ROWS;
			int startNum = endNum - ONE_PAGE_ROWS + 1;
			
			ArrayList<MemoDTO> list = dao.getPagingList(startNum, endNum);
			
			request.setAttribute("list", list);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("tableRowNum", tableRowNum);
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			page = "/memo/list.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("deleteInfo.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			MemoDAO dao = new MemoDAO();
			MemoDTO dto = new MemoDTO();
			dto.setNo(no);
			dao.setDelete(dto);
		}
	}

}
