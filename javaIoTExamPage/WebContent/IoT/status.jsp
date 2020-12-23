<%@page import="java.util.ArrayList"%>
<%@page import="iot.IoTDAO"%>
<%@page import="iot.IoTDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

IoTDAO dao = new IoTDAO();
ArrayList<IoTDTO> statusList = dao.getAllStatus();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파트 현황</title>
</head>
<body>

<h2>아파트 현황</h2>

<table border="1" width="600px">
	<tr>
		<td>아파트 아이디</td>
		<td>보안</td>
		<td>전등</td>
		<td>에어컨</td>
		<td>티비</td>
		<td>밥솥</td>
	</tr>
	<% for (IoTDTO dto : statusList) { %>
		<tr>
			<td><%=dto.getAptId() %></td>
			<td><%=dto.getSecurity().equals("1") ? "ON":"OFF" %></td>
			<td><%=dto.getLight().equals("1") ? "ON":"OFF"%></td>
			<td><%=dto.getAircondition().equals("1") ? "ON":"OFF" %></td>
			<td><%=dto.getTelevision().equals("1") ? "ON":"OFF" %></td>
			<td><%=dto.getCucu().equals("1") ? "ON":"OFF" %></td>
		</tr>
	<% } %>
</table>

</body>
</html>