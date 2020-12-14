<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>

<%
	String[] foods = request.getParameterValues("food");
	for (int i = 0; i < foods.length; i++) {
		out.println("food" + (i+1) + " : " + foods[i] + "<br>");
	}


%>