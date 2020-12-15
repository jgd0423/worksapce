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

<h1>LOGIN PAGE</h1>

<form name="login_form">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pw"></td>
		</tr>
	</table>
	<a href="#" onclick="login();">[로그인]</a><br>
</form>

<script>

function login() {
	login_form.method = "post";
	login_form.action = "loginProc.jsp";
	login_form.submit();
}

</script>

</body>
</html>