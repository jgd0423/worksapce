<%@page import="board.BoardDTO"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%
String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
BoardDAO dao = new BoardDAO();
BoardDTO dto = dao.getSelectOne(no);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>

<h2>게시글 수정</h2>

<form name="modifyForm">
	<input type="hidden" name="no" value="<%=dto.getNo()%>">
	<table border="1" width="600">
		<tr>
			<td>작성자 : </td>
			<td><%=dto.getWriter()%><input type="hidden" name="writer" value="<%=dto.getWriter()%>"></td>
		</tr>
		<tr>
			<td>이메일 : </td>
			<td><input type="text" name="email" value="<%=dto.getEmail()%>"></td>
		</tr>
		<tr>
			<td>비밀번호 : </td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>제목 : </td>
			<td><input type="text" name="subject" value="<%=dto.getSubject()%>"></td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td><textarea name="content" style="width:300px; height:100px;"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="modifyPost();">수정하기</button>
			</td>
		</tr>
	</table>
</form>

<script>

function modifyPost() {
	if (document.modifyForm.email.value === "") {
		alert("이메일을 입력하세요.");
		document.modifyForm.email.focus();
		return false;
	}
	
	if (document.modifyForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.modifyForm.passwd.focus();
		return false;
	}
	
	if (document.modifyForm.subject.value === "") {
		alert("제목을 입력하세요.");
		document.modifyForm.subject.focus();
		return false;
	}
	
	if (document.modifyForm.content.value === "") {
		alert("내용을 입력하세요.");
		document.modifyForm.content.focus();
		return false;
	}
	
	if (confirm("수정하시겠습니까?")) {
		document.modifyForm.method = "post";
		document.modifyForm.action = "modifyProc.jsp";
		document.modifyForm.submit();		
	}
}

</script>

</body>
</html>