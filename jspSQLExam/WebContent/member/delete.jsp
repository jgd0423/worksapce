<%@page import="member.model.MemberDTO"%>
<%@page import="member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

int sessionForDto = (int)session.getAttribute("cookNo");

MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getSelectOne(sessionForDto);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제하기</title>
</head>
<body>

<table border="1" width="100%">
	<tr>
		<td><%@ include file="../include/include_menu.jsp" %></td>
	</tr>
	<tr>
		<td>
			<h2>삭제하기</h2>
			<form name="deleteForm">
				<input type="hidden" name="no" value="<%=dto.getNo() %>" />
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
						<td><input type="text" name="passwd"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><%=dto.getName() %></td>
					</tr>
					<tr>
						<td>성별</td>
						<td><%=dto.getGender() %></td>
					</tr>
					<tr>
						<td>생년월일</td>
						<td><%=dto.getBornYear()%></td>
					</tr>
					<tr>
						<td>가입일시</td>
						<td><%=dto.getRegiDate() %></td>
					</tr>
				</table>
				<br>
				<a href="#" onclick="deleteInfo();">[삭제하기]</a>
			</form>
		</td>
	</tr>
	<tr>
		<td><%@ include file="../include/include_bottom.jsp" %></td>
	</tr>
</table>

<script>
function deleteInfo() {
	if (document.deleteForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.deleteForm.passwd.focus();
		return;
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