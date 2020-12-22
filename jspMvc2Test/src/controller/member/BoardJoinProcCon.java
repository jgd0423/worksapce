package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BoardJoinProcCon.do")
public class BoardJoinProcCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String checkPwd = request.getParameter("checkPwd");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String job = request.getParameter("job");
		
		request.setAttribute("id", id);
		request.setAttribute("pwd", pwd);
		request.setAttribute("checkPwd", checkPwd);
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("job", job);
		
		request.getRequestDispatcher("member/join_result.jsp").forward(request, response);
	}
}
