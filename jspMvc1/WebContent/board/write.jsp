<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
</head>
<body>

<h2>게시글 쓰기</h2>

<form name="writeForm">
	<table border="1" width="600">
		<tr>
			<td>작성자 : </td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>이메일 : </td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>비밀번호 : </td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>제목 : </td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td><textarea name="content" style="width:300px; height:100px;"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="save();">저장하기</button>
			</td>
		</tr>
	</table>
</form>

<script>

function save() {
	if (document.writeForm.writer.value === "") {
		alert("작성자를 입력하세요.");
		document.writeForm.writer.focus();
		return false;
	}
	
	if (document.writeForm.email.value === "") {
		alert("이메일을 입력하세요.");
		document.writeForm.email.focus();
		return false;
	}
	
	if (document.writeForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.writeForm.passwd.focus();
		return false;
	}
	
	if (document.writeForm.subject.value === "") {
		alert("제목을 입력하세요.");
		document.writeForm.subject.focus();
		return false;
	}
	
	if (document.writeForm.content.value === "") {
		alert("내용을 입력하세요.");
		document.writeForm.content.focus();
		return false;
	}
	
	if (confirm("등록하시겠습니까?")) {
		document.writeForm.method = "post";
		document.writeForm.action = "writeProc.jsp";
		document.writeForm.submit();		
	}
}

</script>

</body>
</html>