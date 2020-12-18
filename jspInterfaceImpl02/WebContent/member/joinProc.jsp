<%@page import="model.member.MemberDAOImplMySQL"%>
<%@page import="model.member.MemberDAO"%>
<%@page import="model.member.MemberDAOImplOracle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="model.member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

//MemberDAO dao = new MemberDAOImplOracle();
MemberDAO dao = new MemberDAOImplMySQL();
int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='join.jsp';");
	out.println("</script>");
}

%>