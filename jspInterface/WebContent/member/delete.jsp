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
<title>삭제하기(오라클)</title>
</head>
<body>

<h2>삭제하기(오라클)</h2>

<form name="deleteForm">
	<input type="hidden" name="no" value="<%=dto.getNo()%>">
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
			<td><input type="text" name="passwd" /></td>
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
	<button type="button" onclick="deleteInfo();">삭제하기</button>
</form>

<script>

function deleteInfo() {
	if (document.deleteForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.deleteForm.passwd.focus();
		return false;
	}
	if (confirm("삭제하시겠습니까?")) {
		document.deleteForm.method = "post";
		document.deleteForm.action = "deleteProc.jsp";
		document.deleteForm.submit();		
	}
}

</script>

</body>
</html>