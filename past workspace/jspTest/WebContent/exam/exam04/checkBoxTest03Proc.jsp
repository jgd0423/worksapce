<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%
	String counter_ = request.getParameter("counter");
	int counter = Integer.parseInt(counter_);
	for (int i = 0; i < counter; i++) {
		String food = request.getParameter("food" + i);
		out.println(food + "<br>");
	}
%>