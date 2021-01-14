<%@page import="guestbook.model.dto.GuestbookDTO"%>
<%@page import="guestbook.model.dao.GuestbookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
GuestbookDAO dao = new GuestbookDAO();
GuestbookDTO dto = dao.getSelectOne(no);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>

<h1>수정</h1>

<form action="" name="modifyForm">
	<input type="hidden" name="no" value="<%=dto.getNo() %>" />
	<table border="1" width="600">
		<tr>
			<td>번호</td>
			<td><%=dto.getNo() %></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=dto.getName() %></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="<%=dto.getEmail() %>" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd" value="<%=dto.getPasswd() %>" /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><input type="text" name="content" value="<%=dto.getContent() %>" /></td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><%=dto.getWrite_date() %></td>
		</tr>
	</table>
	<a href="#" onclick="modifyInfo();">[수정하기]</a>
</form>

<script>
function modifyInfo() {
	if (confirm("수정하시겠습니까?")) {
		document.modifyForm.method = "post";
		document.modifyForm.action = "modifyProc.jsp";
		document.modifyForm.submit();		
	}
}
</script>

</body>
</html>