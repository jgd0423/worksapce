<%@page import="board.BoardDAO"%>
<%@ page import="board.BoardDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="board.BoardDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

BoardDAO dao = new BoardDAO();
BoardDTO dbDto = dao.getSelectOne(dto.getNo());

int no = dto.getNo();
String passwd = dto.getPasswd();
String dbPasswd = dbDto.getPasswd();

int result = dao.setIsDeleteTrue(dto);

if (dao.isSoloContent(dbDto) == false && dao.isLastChild(dbDto) == false) {
	out.println("<script>");
	out.println("alert('삭제할 수 없습니다.')");
	out.println("history.back();");
	out.println("</script>");
	return;
}

if (!passwd.equals(dbPasswd)) {
	out.println("<script>");
	out.println("alert('비밀번호가 다릅니다.')");
	out.println("history.back();");
	out.println("</script>");
}  else {
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
}


%>