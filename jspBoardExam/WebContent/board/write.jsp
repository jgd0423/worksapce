<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>

<h1>글쓰기</h1>

<form name="writeForm">
	<input type="hidden" name="no">
	<table border="1">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" style="width:500px; height:300px;"></textarea>
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd"></td>
		</tr>
	</table>
	<a href="#" onclick="chuga();">[등록하기]</a>
	&nbsp;&nbsp;&nbsp;
	<a href="list.jsp">[목록]</a>
</form>

<script>
function chuga() {
	if (document.writeForm.writer.value === '') {
		alert('작성자를 입력하세요.');
		document.writeForm.writer.focus();
		return;
	}
	
	if (document.writeForm.email.value === '') {
		alert('이메일을 입력하세요.');
		document.writeForm.email.focus();
		return;
	}
	
	if (document.writeForm.passwd.value === '') {
		alert('비밀번호를 입력하세요.');
		document.writeForm.passwd.focus();
		return;
	}
	
	if (document.writeForm.subject.value === '') {
		alert('제목을 입력하세요.');
		document.writeForm.subject.focus();
		return;
	}
	
	if (document.writeForm.content.value === '') {
		alert('내용을 입력하세요.');
		document.writeForm.content.focus();
		return;
	}
	
	if (confirm("등록하시겠습니까?")) {
		document.writeForm.method = 'post';
		document.writeForm.action = 'writeProc.jsp';
		document.writeForm.submit();		
	}
}
</script>


</body>
</html>