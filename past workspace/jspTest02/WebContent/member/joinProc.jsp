<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

MemberDAO dao = new MemberDAO();
int result = dao.setInsert(dto);

if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 가입되었습니다.')");
	out.println("location.href='joinList.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다')");
	out.println("location.href='join.jsp';");
	out.println("</script>");
}

%>

