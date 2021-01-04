<%@page import="board.model.dto.BoardDTO"%>
<%@page import="board.model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%
BoardDAO dao = new BoardDAO();
BoardDTO dto = new BoardDTO();

String writer = request.getParameter("writer");
String subject = request.getParameter("subject");
String content = request.getParameter("content");
String email = request.getParameter("email");
String passwd = request.getParameter("passwd");

int num;
int ref;
int re_step;
int re_level;
int hit = 0;

ref = dao.getMaxValue("ref") + 1;
num = dao.getMaxValue("num") + 1;
re_step = 1;
re_level = 1;


dto.setNum(num);
dto.setWriter(writer);
dto.setSubject(subject);
dto.setContent(content);
dto.setEmail(email);
dto.setPasswd(passwd);
dto.setRef(ref);
dto.setRe_step(re_step);
dto.setRe_level(re_level);
dto.setHit(hit);


int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='write.jsp';");
	out.println("</script>");
}
%>