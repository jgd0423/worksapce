package memo.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.model.dao.MemoDAO;
import memo.model.dto.MemoDTO;

// url mapping
@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		MemoDAO dao = new MemoDAO();		
		
		// if (url.contains("write.do")
		if (url.indexOf("write.do") != -1) {
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			MemoDTO dto = new MemoDTO();
			dto.setWriter(writer);
			dto.setContent(content);
			
			dao.setInsert(dto);
			
		} else if (url.indexOf("list.do") != -1) {
			ArrayList<MemoDTO> list = dao.getSelectAll();
			// request 영역에 저장
			request.setAttribute("list", list);
			String page = "/memo/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		}
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
