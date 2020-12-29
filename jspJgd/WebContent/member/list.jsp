<%@page import="member.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="member.MemberDAO"%>
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
<title>회원목록</title>
</head>
<body>

<h2>회원목록</h2>
<table border="1" width="1500">
	<tr>
		<td>번호</td>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>이메일</td>
		<td>전화번호</td>
		<td>주소</td>
		<td>우편번호</td>
		<td>성별</td>
		<td>직업</td>
		<td>등급</td>
		<td>가입일시</td>
		<td>IP</td>
		<td>로그인 실패 횟수</td>
	</tr>
	<% for (MemberDTO dto : memberList) { %>
		<tr>
			<td><%=dto.getNo() %></td>
			<td><a href="view.jsp?id=<%=dto.getId() %>"><%=dto.getId() %></a></td>
			<td><%=dto.getPasswd() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getEmail() %></td>
			<td><%=dto.getPhone() %></td>
			<td><%=dto.getAddress() %></td>
			<td><%=dto.getZipcode() %></td>
			<td><%=dto.getGender() %></td>
			<td><%=dto.getJob() %></td>
			<td><%=dto.getGrade() %></td>
			<td><%=dto.getRegi_date() %></td>
			<td><%=dto.getIp() %></td>
			<td><%=dto.getLoginFailCount() %></td>
		</tr>
	<% } %>
</table>

</body>
</html>