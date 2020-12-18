<%@page import="model.member.MemberExample"%>
<%@page import="model.member.MemberDTO"%>
<%@page import="model.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String id = request.getParameter("id");
MemberExample dao = new MemberExample();
MemberDTO dto = dao.getSelectOne(id);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>

<h2>상세보기</h2>

<table border="1">
	<tr>
		<td>번호</td>
		<td><%=dto.getNo() %></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><%=dto.getId() %></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=dto.getPwd() %></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><%=dto.getName() %></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><%=dto.getPhone() %></td>
	</tr>
	<tr>
		<td>직업</td>
		<td><%=dto.getJob() %></td>
	</tr>
</table>
<br>
<a href="#" onclick="move('M', '<%=dto.getId()%>')">[수정하기]</a>
<a href="#" onclick="move('D', '<%=dto.getId()%>')">[삭제하기]</a>

<script>

function move(whereToGo, id) {
	 if (whereToGo === 'M') {	
		location.href='modify.jsp?id=' + id;
	} else if (whereToGo === 'D') {
		location.href='delete.jsp?id=' + id;
	}
}

</script>

</body>
</html>