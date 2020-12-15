<%@page import="test2.GradesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="test2.GradesDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

GradesDAO dao = new GradesDAO();
int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='inputScores.jsp';");
	out.println("</script>");
}

%>