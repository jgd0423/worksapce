<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String searchField = request.getParameter("searchField");
String searchData = request.getParameter("searchData");

if ((searchField == null || searchField.length() <= 0) || (searchData == null || searchData.length() <= 0)) {
	response.sendRedirect("list.jsp");
	return;
}

response.sendRedirect("list.jsp?searchField=" + searchField + "&searchData=" + searchData);

%>