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
	<h2>exam삭제</h2>
	<form name="form1" method="post" action="${path }/examDeleteProc.do">
		<input type="hidden" name="no" value="${dto.no }">
		<table border="1" width="400">
			<tr>
				<td>이름</td>
				<td>${dto.name }</td>
			</tr>
			<tr>
				<td>주민번호</td>
				<td>${dto.socialNumber }</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>${dto.gender }</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>${dto.age }</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>