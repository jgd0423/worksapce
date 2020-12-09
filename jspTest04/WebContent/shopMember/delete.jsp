<%@page import="com.sun.xml.internal.bind.api.Bridge"%>
<%@page import="shopMember.ShopMemberDTO"%>
<%@page import="shopMember.ShopMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no = request.getParameter("no");
String id = request.getParameter("id");
ShopMemberDAO dao = new ShopMemberDAO();
ShopMemberDTO dto = dao.getMemberInfo(no, id);

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
			<td>회원번호</td>
			<td><%=dto.getNo() %><input type="hidden" name="no" value="<%=dto.getNo() %>"></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=dto.getId() %><input type="hidden" name="id" value="<%=dto.getId() %>"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd" id="passwd" value=""></td>
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
			<td>이메일</td>
			<td><%=dto.getEmail() %></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><%=dto.getBirthYear() %></td>
		</tr>
		<tr>
			<td>가입일시</td>
			<td><%=dto.getwDate() %></td>
		</tr>
	</table>
	<a href="#" onclick="deleteInfo();">[삭제하기]</a><br>
</form>

<script>

function deleteInfo() {
	const pwCheck = document.querySelector('#passwd').value;
	const pw = '<%=dto.getPasswd() %>';
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