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

<form name="form">
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
			<td>이름</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" /></td>
		</tr>
		<tr>
			<td>직업</td>
			<td>
				<input type="radio" name="job" value="공무원"/>공무원 &nbsp;
				<input type="radio" name="job" value="개발자"/>개발자 &nbsp;
				<input type="radio" name="job" value="군인"/>군인 &nbsp;
				<input type="radio" name="job" value="디자이너"/>디자이너 &nbsp;
			</td>
		</tr>
	</table>
	<button type="button" onclick="save();">회원가입</button>
</form>

<script>

function save() {
	if (confirm("등록하시겠습니까?")) {
		form.method = "post";
		form.action = "joinProc.jsp";
		form.submit();
	}
}

</script>

</body>
</html>