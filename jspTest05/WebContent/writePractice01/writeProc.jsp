<%@page import="write.WriteDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="write.WriteDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

WriteDAO dao = new WriteDAO();
int result = dao.insertBoardInfo(dto);
dao.getTotalNumberOfWrittenArticles();

if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 작성되었습니다.')");
	out.println("location.href='write.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다')");
	out.println("location.href='write.jsp';");
	out.println("</script>");
}

%>
