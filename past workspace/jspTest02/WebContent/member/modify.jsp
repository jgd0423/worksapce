<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String id = request.getParameter("id");
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getMemberInfo(id);

String name = dto.getName();
String phone = dto.getPhone();
String email = dto.getEmail();
int birthYear = dto.getBirthYear();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 페이지</title>
</head>
<body>

<h1>회원정보수정</h1>

<form name="modifyForm">
	<table>
		<tr>
			<td>아이디</td>
			<td><%=id %><input type="hidden" name="id" value="<%=id %>"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pw" value=""></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=name %><input type="hidden" name="name" value="<%=name %>"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" value="<%=phone %>"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="<%=email %>"></td>
		</tr>
		<tr>
			<td>출생년도</td>
			<td><input type="text" name="birthYear" value="<%=birthYear %>"></td>
		</tr>
	</table>
	<a href="#" onclick="modifyInfo();">[수정하기]</a><br>
</form>

<script>

function modifyInfo() {
	if (confirm('수정하시겠습니까?')) {
		modifyForm.method = "post";
		modifyForm.action = "modifyProc.jsp";
		modifyForm.submit();
	}
}

</script>

</body>
</html>