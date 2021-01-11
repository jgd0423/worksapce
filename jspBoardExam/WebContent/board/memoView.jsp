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
<title>상세보기</title>
</head>
<body>

<h1>상세보기</h1>
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
		<td><%=dto.getContent() %></td>
	</tr>
	<tr>
		<td>날짜</td>
		<td><%=dto.getRegi_date() %></td>
	</tr>
</table>
<a href="#" onclick="move('M', '<%=no%>');">[수정하기]</a>&nbsp;&nbsp;
<a href="#" onclick="move('D', '<%=no%>');">[삭제하기]</a>

<script>
function move(whereToGo, no) {
	 if (whereToGo === 'M') {
		location.href='memoModify.jsp?no=' + no;
	} else if (whereToGo === 'D') {
		location.href='memoDelete.jsp?no=' + no;
	}
}
</script>

</body>
</html>