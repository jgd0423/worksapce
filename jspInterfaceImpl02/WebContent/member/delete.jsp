<%@page import="model.member.MemberDAOImplMySQL"%>
<%@page import="model.member.MemberDTO"%>
<%@page import="model.member.MemberDAOImplOracle"%>
<%@page import="model.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String id = request.getParameter("id");
//MemberDAO dao = new MemberDAOImplOracle();
MemberDAO dao = new MemberDAOImplMySQL();
MemberDTO dto = dao.getSelectOne(id);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제하기</title>
</head>
<body>

<h2>삭제하기</h2>

<form name="deleteForm">
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
			<td><%=dto.getPhone() %></td>
		</tr>
		<tr>
			<td>직업</td>
			<td><%=dto.getJob() %></td>
		</tr>
	</table>
	<button type="button" onclick="deleteInfo();">삭제하기</button>
</form>

<script>

function deleteInfo() {
	if (document.deleteForm.pwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.deleteForm.pwd.focus();
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