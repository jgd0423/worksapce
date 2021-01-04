<%@page import="board.model.dto.BoardDTO"%>
<%@page import="board.model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
String passwd = request.getParameter("passwd");

BoardDAO dao = new BoardDAO();
BoardDTO dto = new BoardDTO();
BoardDTO dbDto = dao.getSelectOne(no);

dto.setNo(no);

String dbPasswd = dbDto.getPasswd();

if (!passwd.equals(dbPasswd)) {
	out.println("<script>");
	out.println("alert('비밀번호가 다릅니다.')");
	out.println("history.back();");
	out.println("</script>");
	return;
}

int result = dao.deletePost(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 삭제되었습니다.')");
	out.println("location.href='write.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("history.back();");
	out.println("</script>");
}
%>