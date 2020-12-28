<%@page import="etc.member.MemberDAO"%>
<%@page import="etc.member.MemberDTO"%>
<%@page import="org.apache.tomcat.util.descriptor.web.LoginConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String cookId = null;
MemberDTO cookIdDto = null;
if (session.getAttribute("cookId") == null) {
	out.println("<a href=\"login.jsp\">[로그인]</a>");
} else {
	cookId = (String)session.getAttribute("cookId");
	MemberDAO dao = new MemberDAO();
	cookIdDto = dao.getSelectOne(cookId);
	out.println("환영합니다 " + cookIdDto.getName() +"님 " + "<a href=\"logout.jsp\">[로그아웃]</a>");
}

%>