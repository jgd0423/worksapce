package controller.index;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// url mapping
@WebServlet("/index.do")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getContextPath();            // /jspStudy
		String url = request.getRequestURL().toString();   // http://localhost:8090/jspStudy/index.do
		
		// 컨트롤러에서 jsp로 값을 보내줌 1번 파라미터는 변수명, 2번 파라미터는 담을 값
		request.setAttribute("menu_gubun", "index");
		
		String page = "/main/main.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(page);
		// 포워딩하면서 request, response 보냄
		rd.forward(request, response);
	}
}
