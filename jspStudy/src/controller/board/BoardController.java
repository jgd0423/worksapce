package controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.UtilBoard;
import model.board.dao.BoardDAO;
import model.board.dto.BoardDTO;
import model.board.dto.CommentDTO;
import model.member.dto.MemberDTO;

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
		
		String tbl_ = request.getParameter("tbl");
		String tbl = util.tblCheck(tbl_, "freeboard");
		ArrayList<String> tblStatus = util.tblStatus(tbl);
		String isUsingTable = tblStatus.get(0);
		String tableName = tblStatus.get(1);

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
		request.setAttribute("tbl", tbl);
		request.setAttribute("isUsingTable", isUsingTable);
		request.setAttribute("tableName", tableName);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		String page = "/main/main.jsp";
		
		
		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "board_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("write.do") != -1 || url.indexOf("reply.do") != -1) {
			request.setAttribute("menu_gubun", "board_write");
			
			if (url.indexOf("reply.do") != -1) {
				dto = dao.getView(no);
				String parentContent = "";
				parentContent += "[" + dto.getWriter() + "]님이 작성한 글입니다.\n";
				parentContent += dto.getContent();
				parentContent = parentContent.replace("\n", "\n> ");
				parentContent += "\n-------------------------------\n";
				dto.setContent(parentContent);
				request.setAttribute("dto", dto);

			}
			
			page = "/board/write.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("writeProc.do") != -1) {
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
			int refNo = dao.getMaxRefNo() + 1;   // 글 그룹을 의미, 쿼리를 실행시켜서 가장 큰 ref 값을 가져온 후 + 1
			int stepNo = 1;
			int levelNo = 1;
			int parentNo = 0;
			
			// refNo : 부모글 refNo 번호
			// stepNo : 부모글 stepNo + 1
			// levelNo : 부모 levelNo보다 큰 숫자들은 1씩 증가, 그리고 부모글 levelNo + 1
			
			if (no > 0) {   // 답변글일 경우
				BoardDTO dbDto = dao.getView(no);
				dao.setUpdateReLevel(dbDto);   // 답변글. 부모글보다 큰 levelNo값을 전부 1씩 증가시켜준다.
				refNo = dbDto.getRefNo();
				stepNo = dbDto.getStepNo() + 1;
				levelNo = dbDto.getLevelNo() + 1;
				parentNo = dbDto.getNo();
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
			
			
		} else if (url.indexOf("list.do") != -1) {	
			// paging
			int allRowsCount = dao.getAllRowsCount(tbl, search_option, search_data);
			final int ONE_PAGE_ROWS = 10;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, pageNum);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			ArrayList<BoardDTO> list = dao.getPagingList(startNum, endNum, tbl, search_option, search_data);
			
			request.setAttribute("menu_gubun", "board_list");
			request.setAttribute("list", list);
			
			request.setAttribute("ONE_PAGE_ROWS", ONE_PAGE_ROWS);
			request.setAttribute("MAX_PAGING_WIDTH", MAX_PAGING_WIDTH);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("tableRowNum", tableRowNum);
			
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);			
			
			page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("view.do") != -1) {
			dao.setUpdateHit(no);
			dto = dao.getView(no);
			
			// content의 줄바꿈
			String content = dto.getContent();
			content = content.replace("\n", "</br>");
			dto.setContent(content);
			
			String tempPage = "viewPage";
			if (dto.getSecretGubun().equals("T")) {   // 비밀글이면
				String view_passwd = util.nullCheck(request.getParameter("view_passwd"));
				if (dto.getPasswd().equals(view_passwd) && !dto.getPasswd().equals("")) {}
				else {
					tempPage = "viewPasswdPage";
				}
			}
			

			request.setAttribute("menu_gubun", "board_view");
			request.setAttribute("dto", dto);
			request.setAttribute("tempPage", tempPage);
			
			page = "/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

			
		} else if (url.indexOf("modify.do") != -1) {
			request.setAttribute("menu_gubun", "board_modify");
			
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			
			page = "/board/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("modifyProc.do") != -1) {
			String writer = request.getParameter("writer");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String noticeGubun = request.getParameter("noticeGubun");
			String secretGubun = request.getParameter("secretGubun");
			if (secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
				secretGubun = "F";
			} else {
				secretGubun = "T";
			}
			
			dto = dao.getView(no);
			int noticeNo = dto.getNoticeNo();
			if (noticeNo > 0 && (noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T"))) {
				dao.setNoticeNoLargerThenCurrentNoticeNo(no);
				noticeNo = 0;
			} else if (noticeNo == 0 && noticeGubun.equals("T")) {
				noticeNo = dao.getMaxNoticeNo(tbl) + 1;
			}
			
			// 비밀번호 체크
			String dbPasswd = dto.getPasswd();
			boolean isSamePasswd = (passwd.equals(dbPasswd));
			
			if (isSamePasswd) {
				dto.setWriter(writer);
				dto.setEmail(email);
				dto.setPasswd(passwd);
				dto.setSubject(subject);
				dto.setContent(content);
				dto.setMemberNo(cookNo);
				dto.setNoticeNo(noticeNo);
				dto.setSecretGubun(secretGubun);
				dto.setNo(no);
				
				int result = dao.setUpdate(dto);				
			}
			
			request.setAttribute("isSamePasswd", isSamePasswd);
			
			page = "/board/passwdChk.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			/* 비밀번호 체크 다른 방법1
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (isSamePasswd) {
				int result = dao.setUpdate(dto);
				out.println("<script>alert('수정완료'); goPage('view', '" + no + "');</script>");
			} else {
				out.println("<script>alert('비밀번호오류'); goPage('view', '" + no + "');</script>");
			}
			out.flush();
			out.close(); 
			
			index.jsp : goPage에
			
			success: function(data) {
			} else if (value1 === 'sujungProc") {
				$("#result").html(data);
			}
			*/
			
			/* 비밀번호 체크 다른 방법2
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (isSamePasswd) {
				int result = dao.setUpdate(dto);
				out.println("<script>$('#span_passwd').text('O');</script>");
			} else {
				out.println("<script>$('#span_passwd').text('X');</script>");
			}
			out.flush();
			out.close(); 
			
			index.jsp에
			passwd : <span id="span_passwd></span><br> 추가하기
			
			success: function(data) {
			} else if (value1 === 'sujungProc") {
				$("#result").html(data);
				
				if ($("#span_passwd").text() === "O" {
					alert('수정됨');
					goPage('view', $("#span_no").text());
				} else {
					alert('비밀번호 틀림');
					goPage('sujung', $("#span_no").text());
				}
			}	
			*/
			
			
		} else if (url.indexOf("delete.do") != -1) {
			request.setAttribute("menu_gubun", "board_delete");
			
			dto = dao.getView(no);
			request.setAttribute("dto", dto);
			
			page = "/board/delete.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("deleteProc.do") != -1) {
			dto = dao.getView(no);
			String passwd = request.getParameter("passwd");

			// 비밀번호 체크
			String dbPasswd = dto.getPasswd();
			boolean isSamePasswd = (passwd.equals(dbPasswd));
			
			if (isSamePasswd) {
				int result = dao.setDelete(dto);
			}
			
			request.setAttribute("isSamePasswd", isSamePasswd);
			
			page = "/board/passwdChk.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("commentList.do") != -1) {
			// paging
			String commentPageNumber_ = request.getParameter("commentPageNumber");
			int commentPageNumber = util.numberCheck(commentPageNumber_, 1);
			int allRowsCount = dao.getAllCommentRowsCount(no);
			final int ONE_PAGE_ROWS = 5;
			final int MAX_PAGING_WIDTH = 10;
			
			int[] pagerArr = util.pager(ONE_PAGE_ROWS, MAX_PAGING_WIDTH, allRowsCount, commentPageNumber);
			int tableRowNum = pagerArr[0];
			int pagingStartNum = pagerArr[1];
			int pagingEndNum = pagerArr[2];
			int maxPagesCount = pagerArr[3];
			int startNum = pagerArr[4];
			int endNum = pagerArr[5];
			
			
			ArrayList<CommentDTO> list = dao.getCommentPagingList(startNum, endNum, no);
			
			request.setAttribute("list", list);
			
			request.setAttribute("ONE_PAGE_ROWS", ONE_PAGE_ROWS);
			request.setAttribute("MAX_PAGING_WIDTH", MAX_PAGING_WIDTH);
			
			request.setAttribute("commentPageNumber", commentPageNumber);
			request.setAttribute("allRowsCount", allRowsCount);
			request.setAttribute("tableRowNum", tableRowNum);
			
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			request.setAttribute("maxPagesCount", maxPagesCount);
			request.setAttribute("pagingStartNum", pagingStartNum);
			request.setAttribute("pagingEndNum", pagingEndNum);
			
			request.setAttribute("cookName", cookName);
			
			page = "/board/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
			
		} else if (url.indexOf("commentWrite.do") != -1) {
			// input comment data
			String comment_writer = request.getParameter("comment_writer");
			String comment_passwd = request.getParameter("comment_passwd");
			String comment_content = request.getParameter("comment_content");
			
			CommentDTO commentDto = new CommentDTO();
			commentDto.setWriter(comment_writer);
			commentDto.setPasswd(comment_passwd);
			commentDto.setContent(comment_content);
			commentDto.setBoard_no(no);
			commentDto.setMemberNo(cookNo);
			commentDto.setIp(ip);
			
			int result = dao.setInsertComment(commentDto);

			
		} else if (url.indexOf("commentDelete.do") != -1) {
			String comment_no_ = request.getParameter("comment_no");
			int comment_no = Integer.parseInt(comment_no_);
			String passwd = request.getParameter("passwd");
			int result = dao.setDeleteComment(comment_no, passwd);
		}
	}

}
