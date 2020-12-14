<%@page import="shopMember.ShopMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="shopMember.ShopMemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

ShopMemberDAO dao = new ShopMemberDAO();
int result = dao.insertMemberInfo(dto);

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

