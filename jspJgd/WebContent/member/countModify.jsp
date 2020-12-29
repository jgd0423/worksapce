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
<title>회원 로그인 카운트 수정</title>
</head>
<body>

<form name=countModifyForm>
	<input type="hidden" name="id" value="<%=dto.getId() %>">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>로그인 카운트</td>
		</tr>
		<tr>
			<td><%=dto.getId() %></td>
			<td><input type="text" name="loginFailCount" value="<%=dto.getLoginFailCount() %>"></td>
		</tr>
	</table>
	<a href="#" onclick="countInfoModify();">[수정하기]</a>
</form>

<script>
function countInfoModify() {
	if (confirm("수정하시겠습니까?")) {
		document.countModifyForm.method = "post";
		document.countModifyForm.action = "countModifyProc.jsp";
		document.countModifyForm.submit();		
	}
}
</script>

</body>
</html>