<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String id = request.getParameter("id");
MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getMemberInfo(id);
String pw = dto.getPw();
String name = dto.getName();
String phone = dto.getPhone();
String email = dto.getEmail();
int birthYear = dto.getBirthYear();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 페이지</title>
</head>
<body>

<h1>회원정보삭제</h1>

<form name="deleteForm">
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
			<td><%=name %></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><%=phone %></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><%=email %></td>
		</tr>
		<tr>
			<td>출생년도</td>
			<td><%=birthYear %></td>
		</tr>
	</table>
	<a href="#" onclick="deleteInfo();">[삭제하기]</a><br>
</form>

<script>

function deleteInfo() {
	const pwCheck = document.getElementsByName('pw')[0].value;
	const pw = '<%=pw%>';
	if (confirm('삭제하시겠습니까?')) {
		if (pwCheck === pw) {
			deleteForm.method = "post";
			deleteForm.action = "deleteProc.jsp";
			deleteForm.submit();			
		} else {
			alert('비밀번호가 틀렸습니다.');
		}
	}
}

</script>

</body>
</html>