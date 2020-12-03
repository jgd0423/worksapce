<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	String boss_1 = request.getParameter("boss_1");
	String boss_2 = request.getParameter("boss_2");
	String boss_3 = request.getParameter("boss_3");
	String boss_4 = request.getParameter("boss_4");
	String boss_5 = request.getParameter("boss_5");
%>

1반반장 : <%=boss_1%><br>
2반반장 : <%=boss_2%><br>
3반반장 : <%=boss_3%><br>
4반반장 : <%=boss_4%><br>
5반반장 : <%=boss_5%><br>