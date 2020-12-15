<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테이블</title>
</head>
<body>

<form name="DirForm">
작성자 : <input type="text" name="writer" value="">
<br><br> 
제목 : <input type="text" name="subject" value="">
<br><br>
이메일 : <input type="text" name="email" value="">
<br><br>
비밀번호 : <input type="text" name="passwd" value="">
<br><br>
내용 : <textarea name="content" style = "width:200px; height:100px;"></textarea>
<br><br>
<input type="button" value="저장하기" onclick="save();">
</form>

<script>

function save() {
	if (confirm('저장하시겠습니까?')) {
		DirForm.method='post';
		DirForm.action='writeProc.jsp';
		DirForm.submit();
	}
}

</script>

</body>
</html>