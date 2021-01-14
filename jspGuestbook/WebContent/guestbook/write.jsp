<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 작성</title>
</head>
<body>

<h1>방명록 작성</h1>

<form action="" name="writeForm">
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd" /></td>
		</tr>
		<tr>
			<td colspan="2"><textarea name="content" id="" cols="30" rows="10"></textarea></td>
		</tr>
	</table>
	<a href="#" onclick="inputInfo();">[등록하기]</a>
</form>


<script>
function inputInfo() {
	if (confirm("등록하시겠습니까?")) {
		document.writeForm.method = 'post';
		document.writeForm.action = 'writeProc.jsp';
		document.writeForm.submit();		
	}
}
</script>

</body>
</html>