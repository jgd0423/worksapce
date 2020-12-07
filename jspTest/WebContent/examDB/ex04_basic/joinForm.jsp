<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
String cookId = null;
if (session.getAttribute("cookId") != null) {
	cookId = (String) session.getAttribute("cookId");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원가입 - <%=cookId %></h1>

<form name="join">
	<table>
		<tr>
			<td>아이디 :</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호 :</td>
			<td><input type="text" name="pw"></td>
		</tr>
		<tr>
			<td>비밀번호확인 :</td>
			<td><input type="text" name="checkPw"></td>
		</tr>
		<tr>
			<td>이름 :</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>전화번호 :</td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td>이메일 :</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>출생년도 :</td>
			<td><input type="text" name="birthYear"></td>
		</tr>
	</table>
	<a href="#" onclick="login();">[가입하기]</a><br>
	<input type="button" value="가입하기" onclick="login();">
	<button type="button" onclick="login();">가입하기</button>
	<a href="logout.jsp">[로그아웃]</a>
</form>

<script>

function login() {
	if (confirm('가입하시겠습니까?')) {
		join.method = "post";
		join.action = "joinFormProc.jsp";
		join.submit();
	}
}

</script>

</body>
</html>