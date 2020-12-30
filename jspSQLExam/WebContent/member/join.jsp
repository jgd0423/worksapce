<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<table border="1" width="100%">
	<tr>
		<td><%@ include file="../include/include_top.jsp" %></td>
	</tr>
	<tr>
		<td>
			<form name="joinForm">
				<table border="1" align="center">
					<tr>
						<td colspan="2">
							<h2>회원가입</h2>
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
						<td>비밀번호확인</td>
						<td><input type="text" name="passwdChk" /></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<input type="radio" name="gender" value="M" />남
							<input type="radio" name="gender" value="F" />여
						</td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><input type="text" name="bornYear" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="#" onclick="save();">회원가입</a>
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
function save() {
	if (document.joinForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.joinForm.passwd.focus();
		return;
	}
	
	if (document.joinForm.passwd.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.joinForm.passwd.focus();
		return;
	}
	
	if (document.joinForm.passwdChk.value === "") {
		alert("비밀번호 확인을 입력하세요");
		document.joinForm.passwdChk.focus();
		return;
	}
	
	if (document.joinForm.passwdChk.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.joinForm.passwdChk.focus();
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