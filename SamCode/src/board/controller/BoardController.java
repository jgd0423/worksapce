package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.UtilBoard;
import board.model.dao.BoardDAO;
import board.model.dto.BoardCommentDTO;
import board.model.dto.BoardDTO;

@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		UtilBoard util = new UtilBoard();
		
		int[] nalja = util.getDateTime();
		Map<String, Integer> naljaMap = new HashMap<>();
		naljaMap.put("now_y", nalja[0]);
		naljaMap.put("now_m", nalja[1]);
		naljaMap.put("now_d", nalja[2]);

		
		String serverInfo[] = util.getServerInfo(request); //request.getContextPath();
		String refer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];	
		String ip = serverInfo[4];	
		//String ip6 = serverInfo[5];

		
		String temp;
		
		temp = request.getParameter("pageNumber");
		int pageNumber = util.numberCheck(temp, 1);

		temp = request.getParameter("tbl");
		String tbl = util.tblCheck(temp, "freeboard");
		
		temp = request.getParameter("no");
		int no = util.numberCheck(temp, 0);
		
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];	
		

		String[] sessionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sessionArray[0]);
		String cookId = sessionArray[1];
		String cookName = sessionArray[2];
		

		request.setAttribute("naljaMap", naljaMap);
		request.setAttribute("ip", ip);
		request.setAttribute("tbl", tbl);
		request.setAttribute("pageNumber", pageNumber);
		//request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);
		

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		
		String page = "/main/main.jsp";
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "board_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("chuga.do") != -1 || url.indexOf("reply.do") != -1) {	
			if (url.indexOf("chuga.do") != -1) {	
				request.setAttribute("menu_gubun", "board_chuga");
			} else {
				request.setAttribute("menu_gubun", "board_reply");
			}

			
			if (no > 0) {
				dto = dao.getView(no, search_option, search_data);

				temp  = "[" + dto.getWriter() + "]님이 작성한 글입니다.\n";
				temp += dto.getContent();
				temp  = temp.replace("\n", "\n> ");
				temp += "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n";
				dto.setContent(temp);
				
				request.setAttribute("dto", dto);
			}
			
			page = "/board/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("chugaProc.do") != -1 || url.indexOf("replyProc.do") != -1) {	
			String writer = request.getParameter("writer");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String noticeGubun = request.getParameter("noticeGubun");
			
			int noticeNo;
			if (noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
				noticeNo = 0;
			} else {
				noticeNo = dao.getMaxNoticeNo(tbl) + 1;
			}
				
			String secretGubun = request.getParameter("secretGubun");
			if (secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
				secretGubun = "F";
			} else {
				secretGubun = "T";
			}
			
			int num = dao.getMaxNum() + 1;
			int refNo = dao.getMaxRefNo() + 1;
			int stepNo = 1;
			int levelNo = 1;
			int parentNo = 0;
			
			if (no > 0) { //답변글
				BoardDTO dto2 = dao.getView(no, search_option, search_data);
				dao.setUpdateReLevel(dto2);
				refNo = dto2.getRefNo();
				stepNo = dto2.getStepNo() + 1;
				levelNo = dto2.getLevelNo() + 1;
				parentNo = dto2.getNo();
			}
			
			int hit = 0;
		
			dto.setNo(no);
			dto.setNum(num);		
			dto.setTbl(tbl);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			
			dto.setRefNo(refNo);
			dto.setStepNo(stepNo);
			dto.setLevelNo(levelNo);
			dto.setParentNo(parentNo);
			dto.setHit(hit);
			dto.setIp(ip);
			
			dto.setMemberNo(cookNo);
			dto.setNoticeNo(noticeNo);
			dto.setSecretGubun(secretGubun);
			
			int result = dao.setInsert(dto);

		} else if (url.indexOf("sujung.do") != -1) {	
			request.setAttribute("menu_gubun", "board_sujung");
			
			if (no > 0) {
				dto = dao.getView(no, search_option, search_data);
				request.setAttribute("dto", dto);
			}
			
			page = "/board/sujung.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("sujungProc.do") != -1) {	
			String writer = request.getParameter("writer");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String noticeGubun = request.getParameter("noticeGubun");

			int noticeNo;
			if (noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
				noticeNo = 0;
			} else {
				noticeNo = dao.getMaxNoticeNo(tbl) + 1;
			}
				
			String secretGubun = request.getParameter("secretGubun");
			if (secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
				secretGubun = "F";
			} else {
				secretGubun = "T";
			}
			
			
			dto.setNo(no);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setPasswd(passwd);			
			dto.setMemberNo(cookNo);
			dto.setNoticeNo(noticeNo);
			dto.setSecretGubun(secretGubun);
			
			//int result = dao.setUpdate(dto);

			
			String dbPasswd = "12345";
			System.out.println(passwd + " / " + dbPasswd);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (passwd.equals(dbPasswd)) {
				int result = dao.setUpdate(dto);
				out.println("<script>alert('정상적으로 수정되었습니다.'); suntaek_proc('view', $(\"#span_pageNumber\").text(), '" + no + "');</script>");
			} else {
				out.println("<script>alert('비밀번호가 다릅니다.'); suntaek_proc('view', $(\"#span_pageNumber\").text(), '" + no + "');</script>");
			}
			out.flush();
			out.close();
			
			
			
		} else if (url.indexOf("sakje.do") != -1) {	
			request.setAttribute("menu_gubun", "board_sakje");
			
			if (no > 0) {
				dto = dao.getView(no, search_option, search_data);
				
				temp = dto.getContent();
				temp = temp.replace("\n", "<br>");
				
				dto.setContent(temp);
				request.setAttribute("dto", dto);
			}
			
			page = "/board/sakje.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("sakjeProc.do") != -1) {	
			String passwd = request.getParameter("passwd");
			
			dto.setNo(no);
			dto.setPasswd(passwd);			
			
			int result = dao.setDelete(dto);
			
		} else if (url.indexOf("list.do") != -1) {
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(tbl, search_option, search_data);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			
			ArrayList<BoardDTO> list = dao.getList(startRecord, lastRecord, tbl, search_option, search_data);

			request.setAttribute("menu_gubun", "board_list");
		    request.setAttribute("list", list);
		    
			//request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("jj", jj);
			
			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);
					
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
			
			page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("view.do") != -1) {
			dao.setUpdatHit(no);
			dto = dao.getView(no, search_option, search_data);
			
			String imsiPage = "viewPage";
			if (dto.getSecretGubun().equals("T")) {
				String view_passwd = util.nullCheck(request.getParameter("view_passwd"));
				if (dto.getPasswd().equals(view_passwd) && !dto.getPasswd().equals("")) { 
					
				} else {
					imsiPage = "viewPasswdPage";
				}
			}
			
//			temp = dto.getContent();
//			temp = temp.replace("\n", "<br>");
//			dto.setContent(temp);
			
			request.setAttribute("menu_gubun", "board_view");
			request.setAttribute("dto", dto);
			request.setAttribute("imsiPage", imsiPage);
			
			page = "/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("commentList.do") != -1) {
			temp = request.getParameter("commentPageNumber");
			int commentPageNumber = util.numberCheck(temp, 1);
			
			int pageSize = 5;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecordComment(no);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, commentPageNumber);
			int jj = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];
			
			ArrayList<BoardCommentDTO> list = dao.getListComment(startRecord, lastRecord, no);

			request.setAttribute("menu_gubun", "board_comment_list");
		    request.setAttribute("list", list);
		    
			request.setAttribute("commentPageNumber", commentPageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("jj", jj);
			
			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);
					
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);
			
			request.setAttribute("cookName", cookName);
			
			page = "/board/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("commentProc.do") != -1) {			
			//String pageNumber_ = request.getParameter("pageNumber");
			//String no_ = request.getParameter("no");
			String writer = request.getParameter("writer");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			
			BoardCommentDTO commentDto = new BoardCommentDTO();
			commentDto.setBoard_no(no);
			commentDto.setWriter(writer);
			commentDto.setContent(content);
			commentDto.setPasswd(passwd);
			commentDto.setMemberNo(cookNo);
			commentDto.setIp(ip);
						
			int result = dao.setInsertComment(commentDto);
			
		}
	}
}
