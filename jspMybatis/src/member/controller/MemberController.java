package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Util;
import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import member.util.MemberUtil;


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
		
		MemberUtil util = new MemberUtil();
		int[] yearMonthDayHourMinSec = util.getDateTime();
		HashMap<String, Integer> yearMonthDayMap = new HashMap<>();
		yearMonthDayMap.put("nowYear", yearMonthDayHourMinSec[0]);
		yearMonthDayMap.put("nowMonth", yearMonthDayHourMinSec[1]);
		yearMonthDayMap.put("nowDay", yearMonthDayHourMinSec[2]);
		
		String serverInfo[] = util.getServerInfo(request);   // request.getContextPath();
		String referer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
		// String ip6 = serverInfo[5];
		
		String pageNum_ = request.getParameter("pageNumber");
		int pageNum = util.numberCheck(pageNum_, 1);
		
		String no_ = request.getParameter("no");
		int no = util.numberCheck(no_, 0);
		
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];

		String[] sessionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sessionArray[0]);
		String cookId = sessionArray[1];
		String cookName = sessionArray[2];
		
		request.setAttribute("yearMonthDayMap", yearMonthDayMap);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		String page = "/main/main.jsp";
		
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "member_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("write.do") != -1) {
			request.setAttribute("menu_gubun", "member_write");
			
			page = "/member/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String passwdChk = request.getParameter("passwdChk");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String bornYear_ = request.getParameter("bornYear");
			int bornYear = Integer.parseInt(bornYear_);
			String postcode = request.getParameter("sample6_postcode");
			String address = request.getParameter("sample6_address");
			String detailAddress = request.getParameter("sample6_detailAddress");
			String extraAddress = request.getParameter("sample6_extraAddress");
			
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
			
			int result = dao.setInsert(dto);
			
			
		} else if (url.indexOf("goLogin.do") != -1) {
			request.setAttribute("menu_gubun", "member_login");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("login.do") != -1) {
			request.setAttribute("menu_gubun", "member_login");
			
			page = "/member/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("loginProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			
			dto.setId(id);
			dto.setPasswd(passwd);
			
			MemberDTO resultDto = dao.getLogin(dto);
			
			String temp;
			
			if (resultDto == null) {
				temp = path + "/member_servlet/login.do";
			} else if (resultDto.getNo() == 0) { // 실패
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
			// paging
			int allRowsCount = dao.getAllRowsCount(search_option, search_data);
			final int ONE_PAGE_ROWS = 15;
			final int MAX_PAGING_WIDTH = 15;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];			
			
			List<MemberDTO> list = dao.getPagingList(startNum, endNum, search_option, search_data);
			
			request.setAttribute("menu_gubun", "member_list");
			request.setAttribute("list", list);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("tableRowNum", tableRowNum);
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			page = "/member/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		
			
		} else if (url.indexOf("view.do") != -1) {
			HttpSession session = request.getSession();
			
			if (session.getAttribute("cookNo") != null) {
				cookNo = (Integer)session.getAttribute("cookNo");
			}
			
			if (cookNo == 0) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.');");
				out.println("chooseProc('list', '1', '')");
				out.println("</script>");
				return;
			}
			
			dto = dao.getSelectOne(no);
			
			page = "/member/view.jsp";
			request.setAttribute("menu_gubun", "member_view");
			request.setAttribute("dto", dto);
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("goModify.do") != -1) {
			request.setAttribute("menu_gubun", "member_modify");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modify.do") != -1) {
			// 세션 없으면 로그인 페이지로
			HttpSession session = request.getSession();
			if (session.getAttribute("cookNo") == null) {
				response.sendRedirect(path + "/member_servlet/login.do");
				return;
			}
			
			// no값 없거나 0이면 돌려보내기
			if (no_ == "" || no_.equals("0")) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('잘못된 접근입니다.');");
				out.println("chooseProc('list', '1', '');");
				out.println("</script>");
				return;
			}
			
			dto = dao.getSelectOne(no);
			
			request.setAttribute("menu_gubun", "member_modify");
			request.setAttribute("dto", dto);
			
			page = "/member/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modifyProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			String bornYear_ = request.getParameter("bornYear");
			int bornYear = Integer.parseInt(bornYear_);
			String postcode = request.getParameter("sample6_postcode");
			String address = request.getParameter("sample6_address");
			String detailAddress = request.getParameter("sample6_detailAddress");
			String extraAddress = request.getParameter("sample6_extraAddress");

			// 비밀번호 체크
			MemberDTO dbDto = dao.getSelectOne(no);
			String dbPasswd = dbDto.getPasswd();
			boolean isSamePasswd = (passwd.equals(dbPasswd));

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (isSamePasswd) {
				dto.setNo(no);
				dto.setPasswd(passwd);
				dto.setBornYear(bornYear);
				dto.setPostcode(postcode);
				dto.setAddress(address);
				dto.setDetailAddress(detailAddress);
				dto.setExtraAddress(extraAddress);
				
				int result = dao.setUpdate(dto);
				out.println("<script>alert('수정완료'); chooseProc('view', '1', '" + no + "');</script>");
			} else {
				out.println("<script>alert('비밀번호오류'); chooseProc('view', '1', '" + no + "');</script>");
			}
			out.flush();
			out.close(); 
			
			
		} else if (url.indexOf("goDelete.do") != -1) {
			request.setAttribute("menu_gubun", "member_delete");
			
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("delete.do") != -1) {
			// 세션 없으면 로그인 페이지로
			HttpSession session = request.getSession();
			if (session.getAttribute("cookNo") == null) {
				response.sendRedirect(path + "/member_servlet/login.do");
				return;
			}
			
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

			dto = dao.getSelectOne(no);
			
			request.setAttribute("menu_gubun", "member_delete");
			request.setAttribute("dto", dto);
			
			page = "/member/delete.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("deleteProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			// 비밀번호 체크
			MemberDTO dbDto = dao.getSelectOne(no);
			String dbPasswd = dbDto.getPasswd();
			boolean isSamePasswd = (passwd.equals(dbPasswd));

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (isSamePasswd) {
				dto.setNo(no);
				dto.setPasswd(passwd);
				
				int result = dao.setDelete(dto);
				HttpSession session = request.getSession();
				session.invalidate();
				//out.println("<script>alert('삭제완료'); chooseProc('list', '1', '');</script>");
				out.println("<script>alert('삭제완료');");
				out.println("location.href='" + path + "';");
				out.println("</script>");
			} else {
				out.println("<script>alert('비밀번호오류'); chooseProc('view', '1', '" + no + "');</script>");
			}
			out.flush();
			out.close(); 
			
			
		} else if (url.indexOf("id_check.do") != -1) {
			String id = request.getParameter("id");
			int result = dao.getIdCheck(id);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
			
			// ajax success로 보내줌
			// response.getWriter().println(result);
			
			
		} else if (url.indexOf("id_check_win.do") != -1) {
			// response.sendRedirect(path + "/member/id_check.jsp");
			page = "/member/id_check.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("id_check_win_open_Proc.do") != -1) {
			String id = request.getParameter("id");
			String result = dao.getIdCheckWin(id);   // db 아이디 리턴
			if (result == null || result.equals("")) {
				result = id;
			} else {
				result = "";
			}
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}
	}
}
