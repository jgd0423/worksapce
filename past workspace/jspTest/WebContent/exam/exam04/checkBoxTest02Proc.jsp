<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>

<%
	String food1 = request.getParameter("food1");
	String food2 = request.getParameter("food2");
	String food3 = request.getParameter("food3");
	String food4 = request.getParameter("food4");
	String food5 = request.getParameter("food5");
	String food6 = request.getParameter("food6");
%>

food1 : <%=food1%> <br>
food2 : <%=food2%> <br>
food3 : <%=food3%> <br>
food4 : <%=food4%> <br>
food5 : <%=food5%> <br>
food6 : <%=food6%> <br>