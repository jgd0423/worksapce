<%@page import="jspInterfaceTest.model.member.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jspInterfaceTest.model.member.MemberExample"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

MemberExample dao = new MemberExample();
ArrayList<MemberDTO> memberList = dao.getAllMembers();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>

<h2>회원목록</h2>
<table border="1" width="800">
	<tr>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>직업</td>
	</tr>
	<% for (MemberDTO dto : memberList) { %>
		<tr>
			<td><a href="view.jsp?id=<%=dto.getId() %>"><%=dto.getId() %></a></td>
			<td><%=dto.getPwd() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getPhone() %></td>
			<td><%=dto.getJob() %></td>
		</tr>
	<% } %>
</table>

</body>
</html>
