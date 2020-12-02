<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

point2.jsp<br>

<%
	String name = request.getParameter("name");
	String tot = request.getParameter("tot");
	out.println("이름 : " + name + "<br>");
	out.println("점수 : " + tot);
%>

</body>
</html>