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
<title>게시글 삭제</title>
</head>
<body>

<h2>게시글 삭제</h2>

<form name="deleteForm">
	<input type="hidden" name="no" value="<%=dto.getNo()%>">
	<table border="1" width="600">
		<tr>
			<td>작성자 : </td>
			<td><%=dto.getWriter()%><input type="hidden" name="writer" value="<%=dto.getWriter()%>"></td>
		</tr>
		<tr>
			<td>이메일 : </td>
			<td><%=dto.getEmail()%><input type="hidden" name="email" value="<%=dto.getEmail()%>"></td>
		</tr>
		<tr>
			<td>비밀번호 : </td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>제목 : </td>
			<td><%=dto.getSubject()%><input type="hidden" name="subject" value="<%=dto.getSubject()%>"></td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td><textarea name="content" style="width:300px; height:100px;" readonly><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="deletePost();">삭제하기</button>
			</td>
		</tr>
	</table>
</form>

<script>

function deletePost() {
	if (document.deleteForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.deleteForm.passwd.focus();
		return false;
	}
	if (confirm("삭제하시겠습니까?")) {
		document.deleteForm.method = "post";
		document.deleteForm.action = "deleteProc.jsp";
		document.deleteForm.submit();		
	}
}

</script>

</body>
</html>