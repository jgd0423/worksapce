<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getSelectOne(no);

%>

<html>
<head>
<meta charset="UTF-8">
<title>상세보기(오라클)</title>
</head>
<body>

<h2>상세보기(오라클)</h2>

<table border="1">
	<tr>
		<td>일련번호</td>
		<td><%=dto.getNo() %></td>
	</tr>
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
		<td>주민번호</td>
		<td><%=dto.getSid() %></td>
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
		<td>성별</td>
		<td><%=dto.getGender() %></td>
	</tr>
	<tr>
		<td>나이</td>
		<td><%=dto.getAge() %></td>
	</tr>
	<tr>
		<td>가입일시</td>
		<td><%=dto.getwDate() %></td>
	</tr>
</table>
<br>
<a href="#" onclick="move('M', '<%=dto.getNo()%>')">[수정하기]</a>
<a href="#" onclick="move('D', '<%=dto.getNo()%>')">[삭제하기]</a>

<script>
function move(whereToGo, no) {
	 if (whereToGo === 'M') {
		location.href='modify.jsp?no=' + no;
	} else if (whereToGo === 'D') {
		location.href='delete.jsp?no=' + no;
	}
}
</script>

</body>
</html>