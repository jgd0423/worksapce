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
	<h2>삭제하기</h2>
	<form name="form1" method="post" action="${path }/memberDeleteProc.do">
		<input type="hidden" name="id" value="${dto.id }">
		<table border="1" width="400">
			<tr>
				<td>아이디</td>
				<td>${dto.id }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${dto.name }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${dto.email }</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="삭제">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>