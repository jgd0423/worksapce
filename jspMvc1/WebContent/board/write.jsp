<%@page import="board.BoardDAO"%>
<%@page import="board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

int no;
String no_ = request.getParameter("no");
if (no_ == null || no_.length() <= 0) {
	no = 0;
} else {
	no = Integer.parseInt(no_);
}

BoardDAO dao = new BoardDAO();
BoardDTO dto = dao.getSelectOne(no);

String subject = "";
String content = "";

if (no != 0) {
	subject = dto.getSubject();
	content = "";
	content += "[원본글]\n";
	content += dto.getContent();
	content += "\n-----------------------------\n ";
}


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
</head>
<body>

<h2>게시글 쓰기</h2>

<form name="writeForm">
	<input type="hidden" name="no" value="<%=no %>">
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
			<td><input type="text" name="subject" value=<%=subject%>></td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td>
				<textarea id="content" name="content" rows="5" cols="50" wrap="hard"><%=content%></textarea>
			</td>
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
	console.log(document.querySelector('#content').value);
	console.log(document.querySelector('#content').value.replace('\n', '<br>'));
	
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