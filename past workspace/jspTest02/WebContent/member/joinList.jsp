<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

MemberDAO dao = new MemberDAO();
ArrayList<MemberDTO> memberList = dao.getListAll();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>

<h1>회원목록</h1>
<table border="1">
	<tr>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>이메일</td>
		<td>출생년도</td>
		<td>비고</td>
	</tr>
	<% for (int i = 0; i < memberList.size(); i++) { %>
	<% MemberDTO dto = memberList.get(i); %>
		<tr>
			<td><a href="#" onclick="view('<%=dto.getId()%>');"><%=dto.getId() %></a></td>
			<td><%=dto.getPw() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getPhone() %></td>
			<td><%=dto.getEmail() %></td>
			<td><%=dto.getBirthYear() %></td>
			<td></td>
		</tr>
	<% } %>
</table>

<script>
function view(value1) {
	location.href='view.jsp?id=' + value1;
}

</script>

</body>
</html>