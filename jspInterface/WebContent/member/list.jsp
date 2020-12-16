<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="java.util.ArrayList"%>
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
<title>회원목록(오라클)</title>
</head>
<body>

<h2>회원목록(오라클)</h2>

<table border="1" width="1200">
	<tr>
		<td>일련번호</td>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>주민번호</td>
		<td>전화번호</td>
		<td>이메일</td>
		<td>성별</td>
		<td>나이</td>
		<td>가입일시</td>
	</tr>
	<% for (int i = 0; i < memberList.size(); i++) { %>
		<% MemberDTO dto = memberList.get(i); %>
		<tr>
			<td><%=dto.getNo() %></td>
			<td><a href="#" onclick="view('<%=dto.getNo() %>');"><%=dto.getId() %></a></td>
			<td><%=dto.getPasswd() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getSid() %></td>
			<td><%=dto.getPhone() %></td>
			<td><%=dto.getEmail() %></td>
			<td><%=dto.getGender() %></td>
			<td><%=dto.getAge() %></td>
			<td><%=dto.getwDate() %></td>
		</tr>
	<% } %>
</table>

<script>
function view(no) {
	location.href='view.jsp?no=' + no;
}

</script>

</body>
</html>