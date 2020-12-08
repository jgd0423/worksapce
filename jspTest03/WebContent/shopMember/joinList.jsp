<%@page import="java.util.ArrayList"%>
<%@page import="shopMember.ShopMemberDAO"%>
<%@page import="shopMember.ShopMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

ShopMemberDAO dao = new ShopMemberDAO();
ArrayList<ShopMemberDTO> memberList = dao.getMemberList();

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
		<td>번호</td>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>이메일</td>
		<td>나이</td>
		<td>가입일시</td>
		<td>비고</td>
	</tr>
	<% for (int i = 0; i < memberList.size(); i++) { %>
	<% ShopMemberDTO dto = memberList.get(i); %>
		<tr>
			<td><%=dto.getNo() %></td>
			<td><a href="#" onclick="view('<%=dto.getNo()%>');"><%=dto.getId() %></a></td>
			<td><%=dto.getPassword() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getPhone() %></td>
			<td><%=dto.getEmail() %></td>
			<td><%=dto.getAge() %></td>
			<td><%=dto.getJoinDate() %></td>
			<td></td>
		</tr>
	<% } %>
</table>

<script>
function view(value1) {
	location.href='view.jsp?no=' + value1;
}

</script>

</body>
</html>