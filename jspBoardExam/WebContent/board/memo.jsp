<%@page import="memo.model.dto.MemoDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="memo.model.dao.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

MemoDAO dao = new MemoDAO();
ArrayList<MemoDTO> memoList = dao.getSelectAll();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모장</title>
</head>
<body>

<h1>메모장</h1>

<form name="memoForm">
	이름 : <input type="text" name="name" /><br>
	메모 : <input type="text" name="content" size="50"/>
	<button id="btn" type="button">확인</button>
</form>

<table border="1">
	<tr>
		<td>ID</td>
		<td>이름</td>
		<td>메모</td>
		<td>날짜</td>
	</tr>
	<% for (MemoDTO dto : memoList) { %>
		<tr>
			<td><%=dto.getNo() %></td>
			<td><%=dto.getName() %></td>
			<td><a href="#" onclick="view('<%=dto.getNo()%>');"><%=dto.getSubject() %></a></td>
			<td><%=dto.getRegi_date() %></td>
		</tr>
	<% } %>
</table>

</body>

<script>
const btn = document.querySelector('#btn');
btn.addEventListener('click', () => {
	document.memoForm.method = 'post';
	document.memoForm.action = 'memoProc.jsp';
	document.memoForm.submit();
});

function view(no) {
	location.href='memoView.jsp?no=' + no;
}
</script>

</html>