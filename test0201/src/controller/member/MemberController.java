package controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

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
		
		if (url.indexOf("join.do") != -1) {
			String temp;
			temp = "/member/join.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(temp);
			rd.forward(request, response);
			
			
		}else if (url.indexOf("joinProc.do") != -1) {
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
				temp = path + "/member_servlet/list.do";
			} else {
				temp = path + "/member_servlet/join.do";
			}
			response.sendRedirect(temp);
			
			
		} else if (url.indexOf("list.do") != -1) {
			MemberDAO dao = new MemberDAO();
			
			String pageNum_ = request.getParameter("page");

			// validation. charAt을 이용하거나 정규표현식을 이용해 숫자만 남기고 걸러야함
			if (pageNum_ == null || pageNum_.trim().equals("") || pageNum_.equals("0")) {
				pageNum_ = "1";
			}
			
			// paging
			final int ONE_PAGE_ROWS = 15;
			final int MAX_PAGING_WIDTH = 15;
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
			
			ArrayList<MemberDTO> list = dao.getPagingList(startNum, endNum);
			
			request.setAttribute("menu_gubun", "member_list");
			request.setAttribute("list", list);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("tableRowNum", tableRowNum);
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			String temp;
			temp = "/member/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(temp);
			rd.forward(request, response);
		
			
		} else if (url.indexOf("view.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String temp;
			temp = "/member/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(temp);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modify.do") != -1) {
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getSelectOne(no);
			
			request.setAttribute("dto", dto);
			
			String temp;
			temp = "/member/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(temp);
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
				temp = path + "/member_servlet/list.do?pageNumber=&no=" + no;
			} else { // 실패
				temp = path + "/member_servlet/modify.do?pageNumber=&no=" + no;
			}
			response.sendRedirect(temp);
			
			
		}  else if (url.indexOf("json.do") != -1) {
			response.setContentType("text/html;charset=UTF-8"); 
			String no_ = request.getParameter("no");
			int no = Integer.parseInt(no_);
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.getSelectOne(no);


			JSONObject jsonObj = new JSONObject();
			jsonObj.put("no", Integer.toString(dto.getNo()));
			jsonObj.put("id", dto.getId());
			jsonObj.put("passwd", dto.getPasswd());
			jsonObj.put("name", dto.getName());
			jsonObj.put("gender", dto.getGender());
			jsonObj.put("bornYear", Integer.toString(dto.getBornYear()));
			jsonObj.put("postcode", dto.getPostcode());
			jsonObj.put("address", dto.getAddress());
			jsonObj.put("detailAddress", dto.getDetailAddress());
			jsonObj.put("extraAddress", dto.getExtraAddress());
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
			jsonObj.put("regiDate", sdf.format(dto.getRegiDate()));

			String json_info = jsonObj.toJSONString();
			
			
			System.out.println("json: " + json_info);
			PrintWriter pw = response.getWriter();
			pw.print(json_info);
			
		}
	}
}
