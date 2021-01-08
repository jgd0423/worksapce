<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="java.net.URLEncoder" %>

<%
String pageNum = Optional.ofNullable(request.getParameter("pageNum")).orElse("1"); 
String searchField = request.getParameter("searchField");
String searchData = request.getParameter("searchData");
searchField = URLEncoder.encode(searchField, "utf-8");
searchData = URLEncoder.encode(searchData, "utf-8");

if ((searchField == null || searchField.length() <= 0) || (searchData == null || searchData.length() <= 0)) {
	response.sendRedirect("list.jsp");
	return;
}

response.sendRedirect("list.jsp?searchField=" + searchField + "&searchData=" + searchData);

%>