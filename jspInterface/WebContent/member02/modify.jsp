<%@page import="member02.Member02DTO"%>
<%@page import="member02.Member02DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
Member02DAO dao = new Member02DAO();
Member02DTO dto = dao.getSelectOne(no);

%>

<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정(MySQL)</title>
</head>
<body>

<h2>게시글 수정(MySQL)</h2>

<form name="modifyForm">
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
			<td><input type="text" name="phone" value="<%=dto.getPhone() %>"/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="<%=dto.getEmail() %>"/></td>
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
	<a href="#" onclick="modifyInfo();">[수정하기]</a>
</form>

<script>
function modifyInfo() {
	if (document.modifyForm.passwd.value === "") {
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