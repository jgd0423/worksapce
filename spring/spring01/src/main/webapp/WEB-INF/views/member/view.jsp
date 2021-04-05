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
	<h2>상세보기</h2>
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
				<button type="button" onclick="location.href='memberList.do';">목록으로</button>
				<button type="button" onclick="location.href='memberModify.do?id=${dto.id }';">수정하기</button>
				<button type="button" onclick="location.href='memberDelete.do?id=${dto.id }';">삭제하기</button>
			</td>
		</tr>
	</table>
</body>
</html>