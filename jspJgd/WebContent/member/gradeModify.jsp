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
<title>회원 등급 수정</title>
</head>
<body>

<form name=gradeModifyForm>
	<input type="hidden" name="id" value="<%=dto.getId() %>">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>등급</td>
		</tr>
		<tr>
			<td><%=dto.getId() %></td>
			<td><input type="text" name="grade" value="<%=dto.getGrade() %>"></td>
		</tr>
	</table>
	<a href="#" onclick="gradeInfoModify();">[수정하기]</a>
</form>

<script>
function gradeInfoModify() {
	if (confirm("수정하시겠습니까?")) {
		document.gradeModifyForm.method = "post";
		document.gradeModifyForm.action = "gradeModifyProc.jsp";
		document.gradeModifyForm.submit();		
	}
}
</script>

</body>
</html>