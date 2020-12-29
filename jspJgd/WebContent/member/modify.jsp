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

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정페이지</title>
</head>
<body>

<h2>수정페이지</h2>

<form name="modifyForm">
<input type="hidden" name="id" value="<%=dto.getId() %>" />
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><%=dto.getId() %></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><%=dto.getName() %></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="<%=dto.getEmail() %>"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" value="<%=dto.getPhone() %>"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" name="address" value="<%=dto.getAddress() %>"></td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td><input type="text" name="zipcode" value="<%=dto.getZipcode() %>"></td>
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
	<a href="#" onclick="modifyInfo();">[수정하기]</a>
</form>

<script>
function modifyInfo() {
	if (document.modifyForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.modifyForm.passwd.focus();
		return;
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