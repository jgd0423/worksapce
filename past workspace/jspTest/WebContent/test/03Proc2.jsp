<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>

<%
	String subject = request.getParameter("subject");
	String place = request.getParameter("place");
	String name = request.getParameter("name");
	String age = request.getParameter("age");
%>

영화 : <%=subject%><br>
상영관 : <%=place%><br>
이름 : <%=name%><br>
나이 : <%=age%>