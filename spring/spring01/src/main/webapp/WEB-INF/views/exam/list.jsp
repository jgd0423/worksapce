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
	<h2>exam목록</h2>
	<table border="1" width="600">
		<tr>
			<td>순번</td>
			<td>이름</td>
			<td>주민번호</td>
			<td>성별</td>
			<td>나이</td>
			<td>등록일자</td>
		</tr>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.no }</td>
				<td>${dto.name }</td>
				<td>${dto.socialNumber }</td>
				<td>${dto.gender }</td>
				<td>${dto.age }</td>
				<td>
					${dto.regi_date }<br> 
					<button type="button" onclick="location.href='examModify.do?no=${dto.no }';">수정하기</button>
					<button type="button" onclick="location.href='examDelete.do?no=${dto.no }';">삭제하기</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="#" onclick="location.href='examWrite.do';">[등록]</a>
</body>
</html>