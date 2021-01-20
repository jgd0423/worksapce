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
			ArrayList<MemoDTO> list = dao.getSelectAll();
			
			request.setAttribute("list", list);
			
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
