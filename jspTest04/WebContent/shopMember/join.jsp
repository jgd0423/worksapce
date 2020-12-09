<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<h1>회원가입</h1>
<form name="joinForm">
	<table>
		<tr>
			<td>아이디 : </td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호 : </td>
			<td><input type="text" name="passwd" id="passwd"></td>
		</tr>
		<tr>
			<td>비밀번호 확인 : </td>
			<td><input type="text" name="checkPasswd" id="checkPasswd"></td>
		</tr>
		<tr>
			<td>이름 : </td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>전화번호 : </td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td>이메일 : </td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="text" name="birthYear"></td>
		</tr>
	</table>
	<a href="#" onclick="save();">[가입하기]</a>
</form>

<script>

function save() {
	const pw = document.querySelector('#passwd').value;
	const checkPw = document.querySelector('#checkPasswd').value;
	if (confirm('가입하시겠습니까?')) {
		if (pw === '' || checkPw === '') {
			alert('비밀번호를 입력하세요.');
		} else {
			if (pw === checkPw) {
			joinForm.method = 'post';
			joinForm.action = 'joinProc.jsp';
			joinForm.submit();		
			} else {
				alert('비밀번호가 다릅니다.');
			}
		}
	}
}

</script>

</body>
</html>