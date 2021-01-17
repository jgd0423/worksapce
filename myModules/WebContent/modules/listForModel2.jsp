<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<!-- column 숫자만큼 td 복사 -->
		<td></td>
	</tr>
	<c:forEach var="row" items="${list }">
		<tr>
			<td>${row. }</td>
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