<%@page import="jspInterfaceTest.model.member.MemberDTO"%>
<%@page import="jspInterfaceTest.model.member.MemberExample"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String id = request.getParameter("id");
MemberExample dao = new MemberExample();
MemberDTO dto = dao.getOneMember(id);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세보기</title>
</head>
<body>

<h2>회원 상세보기</h2>

<table border="1">
	<tr>
		<td>아이디</td>
		<td><%=dto.getId() %></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td></td>
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
		<td>직업</td>
		<td><%=dto.getJob() %></td>
	</tr>
</table>
<br>
<button type="button" onclick="move('M', '<%=dto.getId() %>')">수정하기</button>
<button type="button" onclick="move('D', '<%=dto.getId() %>')">삭제하기</button>

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