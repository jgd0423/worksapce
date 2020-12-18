<%@page import="model.member.MemberExample"%>
<%@page import="model.member.MemberDTO"%>
<%@page import="model.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String id = request.getParameter("id");
MemberExample dao = new MemberExample();
MemberDTO dto = dao.getSelectOne(id);

%>

<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>

<h2>게시글 수정</h2>

<form name="modifyForm">
	<input type="hidden" name="id" value="<%=dto.getId()%>">
	<table border="1">
		<tr>
			<td>번호</td>
			<td><%=dto.getNo() %></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=dto.getId() %></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pwd" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=dto.getName() %></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" value="<%=dto.getPhone() %>"/></td>
		</tr>
		<tr>
			<td>직업</td>
			<td><%=dto.getJob() %></td>
		</tr>
	</table>
	<a href="#" onclick="modifyInfo();">[수정하기]</a>
</form>

<script>
function modifyInfo() {
	if (document.modifyForm.pwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.modifyForm.passwd.focus();
		return false;
	}
	
	if (confirm("수정하시겠습니까?")) {
		document.modifyForm.method = "post";
		document.modifyForm.action = "modifyProc.jsp";
		document.modifyForm.submit();		
	}
}
</script>

</body>
</html>