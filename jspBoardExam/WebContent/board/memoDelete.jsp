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
<title>삭제하기</title>
</head>
<body>

<h1>삭제하기</h1>
<form name="delteForm">
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
			<td><%=dto.getContent()%></td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><%=dto.getRegi_date() %></td>
		</tr>
	</table>
	<button id="btn" type="button">삭제하기</button>
</form>
	
<script>
const btn = document.querySelector('#btn');
btn.addEventListener('click', () => {
	document.delteForm.method = 'post';
	document.delteForm.action = 'memoDeleteProc.jsp';
	document.delteForm.submit();
});

</script>

</body>
</html>