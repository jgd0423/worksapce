<%@page import="memo.model.dto.MemoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%



%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>

<h1>목록</h1>

<table border="1">
	<tr>
		<td>NO</td>
		<td>이름</td>
		<td>메모</td>
		<td>날짜</td>
	</tr>
	<c:forEach var="row" items="${list }">
		<tr>
			<td>${row.no }</td>
			<td>${row.writer }</td>
			<td>${row.content }</td>
			<td>${row.regi_date }</td>
		</tr>
	</c:forEach>
</table>

<script>
function view(no) {
	location.href='view.jsp?no=' + no;
}
</script>

</body>
</html>