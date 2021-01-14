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
<title>상세보기</title>
</head>
<body>

<h1>상세보기</h1>

<table border="1">
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

<a href="#" onclick="move('M', '<%=no%>');">[수정하기]</a>
&nbsp;&nbsp;
<a href="#" onclick="move('D', '<%=no%>');">[삭제하기]</a>

<script>
function move(whereToGo, no) {
	if (whereToGo === 'M') {
		location.href='modify.jsp?no=' + no;
	} else if (whereToGo === 'D') {
		location.href='delete.jsp?no=' + no;
	}
}
</script>

</body>
</html>