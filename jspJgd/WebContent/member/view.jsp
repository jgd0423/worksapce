<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/ip_check.jsp" %>
<%@ include file="../include/session_check.jsp" %>
<%@ include file="../include/need_login.jsp" %>
<%@ include file="../include/menu.jsp" %>

<%

String id = request.getParameter("id");
dao = new MemberDAO();
MemberDTO dto = dao.getSelectOne(id);

if (!cookId.equals(id)) {
	out.println("<script>");
	out.println("alert('잘못된 요청입니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
	return;
}

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
		<td>아이디</td>
		<td><%=dto.getId() %></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=dto.getPasswd() %></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><%=dto.getName() %></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><%=dto.getEmail() %></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td><%=dto.getPhone() %></td>
	</tr>
	<tr>
		<td>주소</td>
		<td><%=dto.getAddress() %></td>
	</tr>
	<tr>
		<td>우편번호</td>
		<td><%=dto.getZipcode() %></td>
	</tr>
	<tr>
		<td>성별</td>
		<td><%=dto.getGender() %></td>
	</tr>
	<tr>
		<td>직업</td>
		<td><%=dto.getJob() %></td>
	</tr>
	<tr>
		<td>회원등급</td>
		<td><%=dto.getGrade() %></td>
	</tr>
	<tr>
		<td>가입일시</td>
		<td><%=dto.getRegi_date() %></td>
	</tr>
	<tr>
		<td>IP</td>
		<td><%=dto.getIp() %></td>
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