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
<title>회원 로그인 카운트 수정</title>
</head>
<body>

<h2>카운트를 수정할 사람 선택</h2>

<table border="1" width="500px">
	<tr>
		<td>아이디</td>
		<td>로그인 카운트</td>
	</tr>
	<% for (MemberDTO dto : memberList) { %>
		<tr>
			<td><a href="countModify.jsp?id=<%=dto.getId() %>"><%=dto.getId() %></a></td>
			<td><%=dto.getLoginFailCount()%></td>
		</tr>
	<% } %>
</table>



</body>
</html>