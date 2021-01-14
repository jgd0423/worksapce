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
<title>삭제</title>
</head>
<body>

<h1>삭제</h1>

<form action="" name="deleteForm">
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
			<td><%=dto.getEmail() %></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%=dto.getPasswd() %></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><%=dto.getContent() %></td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><%=dto.getWrite_date() %></td>
		</tr>
	</table>
	<a href="#" onclick="deleteInfo();">[삭제하기]</a>
</form>

<script>
function deleteInfo() {
	if (confirm("삭제하시겠습니까?")) {
		document.deleteForm.method = "post";
		document.deleteForm.action = "deleteProc.jsp";
		document.deleteForm.submit();		
	}
}
</script>

</body>
</html>