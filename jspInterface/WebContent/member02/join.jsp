<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입(MySQL)</title>
</head>
<body>

<h2>회원가입(MySql)</h2>

<form name="joinForm">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd" /></td>
		</tr>
		<tr>
			<td>비밀번호확인</td>
			<td><input type="text" name="passwdCheck" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>주민번호('-'제외)</td>
			<td><input type="text" name="sid" /></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
		</tr>
	</table>
	<button type="button" onclick="save();">가입</button>
</form>

<script>

function save() {
	document.joinForm.method = "post";
	document.joinForm.action = "joinProc.jsp";
	document.joinForm.submit();		
}

</script>

</body>
</html>