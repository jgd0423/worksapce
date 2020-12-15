<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String id = request.getParameter("id");
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getMemberInfo(id);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 상세보기</title>
</head>
<body>

<h1>회원정보 상세보기</h1>
<table border="1">
	<tr>
		<td>아이디</td>
		<td><%=dto.getId() %></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=dto.getPw() %></td>
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
		<td>이메일</td>
		<td><%=dto.getEmail() %></td>
	</tr>
	<tr>
		<td>출생년도</td>
		<td><%=dto.getBirthYear() %></td>
	</tr>
</table>
<a href="#" onclick="move('M', '<%=dto.getId()%>')">[수정하기]</a>&nbsp;&nbsp;
<a href="#" onclick="move('D', '<%=dto.getId()%>')">[삭제하기]</a>

<script>

function move(value1, value2) {
	if (value1 === "M") {
		location.href = 'modify.jsp?id=' + value2;
	} else if (value1 === "D") {
		location.href = 'delete.jsp?id=' + value2;
	}
}

</script>


</body>
</html>