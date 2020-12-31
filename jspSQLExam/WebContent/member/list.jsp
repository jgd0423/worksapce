<%@page import="member.model.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.model.MemberDAO"%>
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

<table border="1" width="100%">
	<tr>
		<td><%@ include file="../include/include_menu.jsp" %></td>
	</tr>
	<tr>
		<td>
			<h2>회원목록</h2>
			<table border="1" width="1500">
				<tr>
					<td>번호</td>
					<td>아이디</td>
					<td>비밀번호</td>
					<td>이름</td>
					<td>성별</td>
					<td>생년월일</td>
					<td>가입일시</td>
				</tr>
				<% for (MemberDTO dto : memberList) { %>
					<tr>
						<td><%=dto.getNo() %></td>
						<% if (cookNo == dto.getNo()) { %>
							<td><a href="view.jsp?no=<%=dto.getNo() %>"><%=dto.getId() %></a></td>
						<% } else { %>
							<td><%=dto.getId() %></td>
						<% } %>
						<td><%=dto.getPasswd() %></td>
						<td><%=dto.getName() %></td>
						<td><%=dto.getGender() %></td>
						<td><%=dto.getBornYear() %></td>
						<td><%=dto.getRegiDate() %></td>
					</tr>
				<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<td><%@ include file="../include/include_bottom.jsp" %></td>
	</tr>
</table>

</body>
</html>