package controller.guestbook;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String page = "/main/main.jsp";
		
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
			
			ArrayList<GuestbookDTO> list = dao.getSelectAll();
			
			request.setAttribute("menu_gubun", "guestbook_list");
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}
