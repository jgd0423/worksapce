<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<table border="1" width="100%">
	<tr>
		<td><%@ include file="../include/include_menu.jsp" %></td>
	</tr>
	<tr>
		<td>
			<form name="loginForm">
				<table border="1" align="center">
					<tr>
						<td colspan="2">
							<h2>로그인</h2>
						</td>
					</tr>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id" /></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" name="passwd" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="#" onclick="goToLoginProc();">로그인</a>
						</td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
	<tr>
		<td><%@ include file="../include/include_bottom.jsp" %></td>
	</tr>
</table>

<script>

function goToLoginProc() {
	if (confirm("로그인하시겠습니까?")) {
		document.loginForm.method = "post";
		document.loginForm.action = "loginProc.jsp";
		document.loginForm.submit();
	}
}

</script>

</body>
</html>