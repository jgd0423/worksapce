<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원목록</h2>
	<table border="1" width="600">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>등록일자</td>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>
					<a href="memberView.do?id=${dto.id }">${dto.id }</a>
				</td>
				<td>${dto.name }</td>
				<td>${dto.email }</td>
				<td>
					${dto.regi_date }<br>
					<fmt:formatDate value="${dto.regi_date }" pattern="yyyy-MM-dd" /><br>
					<fmt:formatDate value="${dto.regi_date }" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="#" onclick="location.href='memberWrite.do';">[등록]</a>
</body>
</html>