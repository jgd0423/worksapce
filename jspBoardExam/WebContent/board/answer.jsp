<%@page import="board.model.dto.BoardDTO"%>
<%@page import="board.model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);

BoardDAO dao = new BoardDAO();
BoardDTO dto = dao.getSelectOne(no);

String subject = "";
String content = "";

subject = dto.getSubject();
content = "";
content += "[원본글]\n";
content += dto.getContent();
content += "\n-----------------------------\n ";

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변하기</title>
</head>
<body>

<h1>답변하기</h1>

<form name="answerForm">
	<input type="hidden" name="no" value="<%=dto.getNo()%>">
	<table border="1">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject" value="<%=subject %>"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" style="width:500px; height:300px;"><%=content %></textarea>
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
</form>

<script>
function chuga() {
	if (document.answerForm.writer.value === '') {
		alert('작성자를 입력하세요.');
		document.answerForm.writer.focus();
		return;
	}
	
	if (document.answerForm.email.value === '') {
		alert('이메일을 입력하세요.');
		document.answerForm.email.focus();
		return;
	}
	
	if (document.answerForm.passwd.value === '') {
		alert('비밀번호를 입력하세요.');
		document.answerForm.passwd.focus();
		return;
	}
	
	if (document.answerForm.subject.value === '') {
		alert('제목을 입력하세요.');
		document.answerForm.subject.focus();
		return;
	}
	
	if (document.answerForm.content.value === '') {
		alert('내용을 입력하세요.');
		document.answerForm.content.focus();
		return;
	}
	
	if (confirm("등록하시겠습니까?")) {
		document.answerForm.method = 'post';
		document.answerForm.action = 'answerProc.jsp';
		document.answerForm.submit();		
	}
}
</script>


</body>
</html>