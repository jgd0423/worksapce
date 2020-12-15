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

<form name="join">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone"></td>
		</tr>
		<tr>
			<td>직업</td>
			<td>
				<input type="radio" name="job" value="공무원">공무원
				<input type="radio" name="job" value="개발자">개발자
				<input type="radio" name="job" value="군인">군인
				<input type="radio" name="job" value="디자이너">디자이너
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="save();">가입하기</button>
			</td>
		</tr>
	</table>
</form>

<script>

function save() {
	if (confirm("등록하시겠습니까?")) {
		document.join.method = "post";
		document.join.action = "joinProc.jsp";
		document.join.submit();		
	}
}

</script>

</body>
</html>