<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<h2>회원가입</h2>

<form action="" name="joinForm">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pwd" /></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="text" name="checkPwd" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" /></td>
		</tr>
		<tr>
			<td>직업</td>
			<td><input type="text" name="job" /></td>
		</tr>
	</table>
	<br>
	<button type="button" onclick="save();">회원가입</button>
</form>

<script>
function save() {
	if (document.joinForm.pwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.joinForm.pwd.focus();
		return;
	}
	
	if (document.joinForm.pwd.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.joinForm.pwd.focus();
		return;
	}
	
	if (document.joinForm.checkPwd.value === "") {
		alert("비밀번호 확인을 입력하세요");
		document.joinForm.checkPwd.focus();
		return;
	}
	
	if (document.joinForm.checkPwd.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.joinForm.checkPwd.focus();
		return;
	}
	
	if (confirm("등록하시겠습니까?")) {
		document.joinForm.method = "post";
		document.joinForm.action = "joinProc.jsp";
		document.joinForm.submit();
	}
}
</script>

</body>
</html>