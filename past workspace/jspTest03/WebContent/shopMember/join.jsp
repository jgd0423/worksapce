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
아이디 : <input type="text" name="id">
<br><br>
비밀번호 : <input type="text" name="password" id="password">
<br><br>
비밀번호 확인 : <input type="text" name="checkPassword" id="checkPassword">
<br><br>
이름 : <input type="text" name="name">
<br><br>
전화번호 : <input type="text" name="phone">
<br><br>
이메일 : <input type="text" name="email">
<br><br>
나이 : <input type="text" name="age">
<br><br>
<a href="#" onclick="save();">[가입하기]</a>
</form>

<script>

function save() {
	const pw = document.querySelector('#password').value;
	const checkPw = document.querySelector('#checkPassword').value;
	if (confirm('가입하시겠습니까?')) {
		if (pw === checkPw) {
		joinForm.method = "post";
		joinForm.action = "joinProc.jsp";
		joinForm.submit();		
		} else {
			alert('비밀번호가 다릅니다.');
		}
	}

}

</script>

</body>
</html>