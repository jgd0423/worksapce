package controller.guestbook;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Util;
import model.guestbook.dao.GuestbookDAO;
import model.guestbook.dto.GuestbookDTO;

@WebServlet("/guestbook_servlet/*")
public class GuestbookController extends HttpServlet {
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
		
		String[] serverInfo = util.getServerInfo(request);
		String path = serverInfo[1];
		String url = serverInfo[2];
		
		if (url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "guestbook_write");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1) {
			String name = request.getParameter("name");
			String passwd = request.getParameter("passwd");
			String email = request.getParameter("email");
			String content = request.getParameter("content");
			
			GuestbookDTO dto = new GuestbookDTO();
			dto.setName(name);
			dto.setPasswd(passwd);
			dto.setEmail(email);
			dto.setContent(content);
			
			GuestbookDAO dao = new GuestbookDAO();
			String temp;
			int result = dao.setInsert(dto);
			if (result > 0) {
				temp = path + "/guestbook_servlet/list.do";
			} else {
				temp = path + "/guestbook_servlet/write.do";
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("list.do") != -1) {
			GuestbookDAO dao = new GuestbookDAO();
			
			String pageNum_ = request.getParameter("page");

			// validation. charAt을 이용하거나 정규표현식을 이용해 숫자만 남기고 걸러야함
			int pageNum = util.numberCheck(pageNum_, 1);
			
			// paging
			int allRowsCount = dao.getAllRowsCount();
			final int ONE_PAGE_ROWS = 4;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];	
			
			ArrayList<GuestbookDTO> list = dao.getPagingList(startNum, endNum);
			
			request.setAttribute("menu_gubun", "guestbook_list");
			request.setAttribute("list", list);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("tableRowNum", tableRowNum);
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}
