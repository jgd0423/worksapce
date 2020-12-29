<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/ip_check.jsp" %>
<%@ include file="../include/session_check.jsp" %>
<%@ include file="../include/need_login.jsp" %>
<%@ include file="../include/menu.jsp" %>
<%

dao = new MemberDAO();
ArrayList<MemberDTO> memberList = dao.getListAll();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 등급 수정</title>
</head>
<body>

<h2>수정할 사람 선택</h2>

<table border="1" width="500px">
	<tr>
		<td>아이디</td>
		<td>등급</td>
	</tr>
	<% for (MemberDTO dto : memberList) { %>
		<tr>
			<td><a href="gradeModify.jsp?id=<%=dto.getId() %>"><%=dto.getId() %></a></td>
			<td><%=dto.getGrade()%></td>
		</tr>
	<% } %>
</table>



</body>
</html>