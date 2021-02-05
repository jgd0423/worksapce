package controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Util;
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
		String page = "/main/main.jsp";
		Util util = new Util();
		
		String[] serverInfo = util.getServerInfo(request);
		String path = serverInfo[1];
		String url = serverInfo[2];
		
		
		if (url.indexOf("chuga.do") != -1) {
			request.setAttribute("menu_gubun", "member_chuga");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("chugaProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String passwdChk = request.getParameter("passwdChk");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String bornYear_ = request.getParameter("bornYear");
			int bornYear = Integer.parseInt(bornYear_);
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			
			// validation
			id = id.replace("<", "&lt;");
			id = id.replace(">", "&gt;");
			id = id.replace("&", "&amp;");
			id = id.replace("\"", "&quot;");
			id = id.replace("'", "&apos;");
			
			if (!passwd.equals(passwdChk)) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPasswd(passwd);
			dto.setPasswdChk(passwdChk);
			dto.setName(name);
			dto.setGender(gender);
			dto.setBornYear(bornYear);
			dto.setPostcode(postcode);
			dto.setAddress(address);
			dto.setDetailAddress(detailAddress);
			dto.setExtraAddress(extraAddress);
			
			MemberDAO dao = new MemberDAO();
			String temp;
			int result = dao.setInsert(dto);
			if (result > 0) {
				temp = path + "/member_servlet/login.do";
			} else {
				temp = path + "/member_servlet/chuga.do";
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("login.do") != -1) {
			request.setAttribute("menu_gubun", "member_login");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("loginProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			
			MemberDTO dto = new MemberDTO();
			dto.setId(id);
			dto.setPasswd(passwd);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO resultDto = dao.getLogin(dto);
			
			String temp;
			if (resultDto.getNo() == 0) { // 실패
				temp = path + "/member_servlet/login.do";
			} else { // 성공
				// 세션 등록
				HttpSession session = request.getSession();
				session.setAttribute("cookNo", resultDto.getNo());
				session.setAttribute("cookId", resultDto.getId());
				session.setAttribute("cookName", resultDto.getName());
				temp = path;
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("logout.do") != -1) {
			// 세션 해제
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그아웃 되었습니다.');");
			out.println("location.href='" + path + "';");
			out.println("</script>");
			
//			String temp = path;
//			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("list.do") != -1) {
			MemberDAO dao = new MemberDAO();
			
			String pageNum_ = request.getParameter("page");

			// validation. charAt을 이용하거나 정규표현식을 이용해 숫자만 남기고 걸러야함
			int pageNum = util.numberCheck(pageNum_, 1);
			
			// paging
			int allRowsCount = dao.getAllRowsCount();
			final int ONE_PAGE_ROWS = 15;
			final int MAX_PAGING_WIDTH = 15;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];			
			
			ArrayList<MemberDTO> list = dao.getPagingList(startNum, endNum);
			
			request.setAttribute("menu_gubun", "member_list");
			request.setAttribute("list", list);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("tableRowNum", tableRowNum);
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
			
		} else if (url.indexOf("view.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			// 세션과 no가 같은 사람만 들어가게 하기
			HttpSession session = request.getSession();
			
			int cookNo = 0;
			if (session.getAttribute("cookNo") != null) {
				cookNo = (Integer)session.getAttribute("cookNo");
			}
			
			if (cookNo != no) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("menu_gubun", "member_view");
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modify.do") != -1) {
			// 세션 없으면 로그인 페이지로
			HttpSession session = request.getSession();
			if (session.getAttribute("cookNo") == null) {
				response.sendRedirect(path + "/member_servlet/login.do");
				return;
			}
			
			String no_ = request.getParameter("no");
			
			// no값 없거나 0이면 돌려보내기
			if (no_ == "" || no_.equals("0")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			
			int no = Integer.parseInt(no_);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("menu_gubun", "member_modify");
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modifyProc.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			String passwd = request.getParameter("passwd");
			String bornYear_ = request.getParameter("bornYear");
			int bornYear = Integer.parseInt(bornYear_);
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			MemberDAO dao = new MemberDAO();

			// 비밀번호 체크
			MemberDTO dbDto = dao.getSelectOne(no);
			if (!passwd.equals(dbDto.getPasswd())) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			
			MemberDTO dto = new MemberDTO();
			dto.setNo(no);
			dto.setBornYear(bornYear);
			dto.setPostcode(postcode);
			dto.setAddress(address);
			dto.setDetailAddress(detailAddress);
			dto.setExtraAddress(extraAddress);
			
			int result = dao.setUpdate(dto);
			
			String temp;
			if (result > 0) { // 성공
				temp = path + "/member_servlet/view.do?pageNumber=&no=" + no;
			} else { // 실패
				temp = path + "/member_servlet/modify.do?pageNumber=&no=" + no;
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("delete.do") != -1) {
			// 세션 없으면 로그인 페이지로
			HttpSession session = request.getSession();
			if (session.getAttribute("cookNo") == null) {
				response.sendRedirect(path + "/member_servlet/login.do");
				return;
			}
			
			String no_ = request.getParameter("no");
			
			// no값 없거나 0이면 돌려보내기
			if (no_ == "" || no_.equals("0")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			
			int no = Integer.parseInt(no_);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("menu_gubun", "member_delete");
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("deleteProc.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			String passwd = request.getParameter("passwd");
			MemberDAO dao = new MemberDAO();
			
			// 비밀번호 체크
			MemberDTO dbDto = dao.getSelectOne(no);
			if (!passwd.equals(dbDto.getPasswd())) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 틀렸습니다.');");
				out.println("history.back();");
				out.println("</script>");
				return;
			}
			
			MemberDTO dto = new MemberDTO();
			dto.setNo(no);
			
			int result = dao.setDelete(dto);
			
			String temp;
			if (result > 0) { // 성공
				HttpSession session = request.getSession();
				session.invalidate();
				temp = path + "/member_servlet/list.do";
			} else { // 실패
				temp = path + "/member_servlet/delete.do?pageNumber=&no=" + no;
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("id_check.do") != -1) {
			String id = request.getParameter("id");
			
			MemberDAO dao = new MemberDAO();
			int result = dao.getIdCheck(id);
			
//			PrintWriter out = response.getWriter();
			
			// ajax success로 보내줌
			response.getWriter().println(result);
			
			
		} else if (url.indexOf("id_check_win.do") != -1) {
			response.sendRedirect(path + "/member/id_check.jsp");
			
			
		} else if (url.indexOf("id_check_win_open_Proc.do") != -1) {
			String id = request.getParameter("id");
			
			MemberDAO dao = new MemberDAO();
			int result = dao.getIdCheck(id);
			String resultStr = null;
			if (result == 1) {
				resultStr = "중복임";
			} else {
				resultStr = "중복아님";
				request.setAttribute("resultId", id);
			}
			
			request.setAttribute("result", resultStr);
			
			page = "/member/id_check.jsp";
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
}
