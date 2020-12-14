<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="counsel_form" method="post" action="11stProc.jsp">
	<h1>상담정보</h1>
	상담분류
	<select name=counsel_type>
		<option value="">선택</option>
		<option value="buying">구매관련</option>
		<option value="selling">판매관련</option>
	</select>
	
	<h1>회원정보</h1>
	이름
	<input type="text" name="name"><br>
	연락처(선택, "-" 제외)
	<input type="text" name="phone"><br>
	답변 E-Mail
	<input type="text" name="email_1">@<input type="text" name="email_2"><br>
	
	<h1>질문내용</h1>
	제목
	<input type="text" name="title"><br>
	내용
	<textarea rows="30" cols="30" name="contents"></textarea><br>
	<input type="submit">
	
</form>

</body>
</html>