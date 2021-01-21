package controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.member.dao.MemberDAO;
import model.member.dto.MemberDTO;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
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
		
		if (url.indexOf("loginProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPasswd(passwd);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO resultDto = dao.getLogin(dto);
			
			Date changeDate = resultDto.getChangeDate();			
			Date date = new Date();
			long calDate = date.getTime() - changeDate.getTime();
			int caldiffDay = (int) (calDate / (24*60*60*1000));
			
			String temp;
			if (resultDto.getId() == null) { // 실패
				temp = path + "/member_servlet/login.do";
			} else { // 성공
				// 세션 등록
				HttpSession session = request.getSession();
				session.setAttribute("cookId", resultDto.getId());
				session.setAttribute("cookChangeDate", resultDto.getChangeDate());
				session.setMaxInactiveInterval(1 * 60);
				
				if (caldiffDay > 183) {
					response.sendRedirect(path + "/change.jsp");
					return;
				}
				
				temp = path + "/member_servlet/main.do";
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("login.do") != -1) {
			response.sendRedirect(path + "/login.jsp");
			
			
		} else if (url.indexOf("main.do") != -1) {
			response.sendRedirect(path + "/main.jsp");
			
			
		} else if (url.indexOf("logout.do") != -1) {
			// 세션 해제
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그아웃 되었습니다.');");
			out.println("location.href='login.do';");
			out.println("</script>");
			
			
		} else if (url.indexOf("changeProc.do") != -1) {
			MemberDAO dao = new MemberDAO();
			HttpSession session = request.getSession();
			String cookId = (String)session.getAttribute("cookId");
			String passwd = request.getParameter("passwd");
			String passwdChk = request.getParameter("passwdChk");
			
			if (passwd.equals("") || passwd == null) {
				String temp = path + "/change.jsp";
				response.sendRedirect(temp);
				return;
			}
			
			if (passwdChk.equals("") || passwdChk == null) {
				String temp = path + "/change.jsp";
				response.sendRedirect(temp);
				return;
			}
			
			MemberDTO dto = new MemberDTO();
			dto.setId(cookId);
			dto.setPasswd(passwd);
			
			dao.setUpdate(dto);
			
			String temp = path + "/member_servlet/main.do";
			response.sendRedirect(temp);
		}
	}

}
