<%@page import="memo.model.dto.MemoDTO"%>
<%@page import="memo.model.dao.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);

MemoDAO dao = new MemoDAO();
MemoDTO dto = dao.getSelectOne(no);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
</head>
<body>

<h1>수정하기</h1>
<form name="modifyForm">
	<input type="hidden" name="no" value="<%=dto.getNo()%>">
	<table border="1">
		<tr>
			<td>ID</td>
			<td><%=dto.getNo() %></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=dto.getName() %></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><%=dto.getSubject() %></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><input type="text" name="content" value="<%=dto.getContent()%>"></td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><%=dto.getRegi_date() %></td>
		</tr>
	</table>
	<button id="btn" type="button">수정하기</button>
</form>

<script>
const btn = document.querySelector('#btn');
btn.addEventListener('click', () => {
	document.modifyForm.method = 'post';
	document.modifyForm.action = 'memoModifyProc.jsp';
	document.modifyForm.submit();
});

</script>

</body>
</html>