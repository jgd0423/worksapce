<%@page import="shopMember.ShopMemberDTO"%>
<%@page import="shopMember.ShopMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no = request.getParameter("no");
ShopMemberDAO dao = new ShopMemberDAO();
ShopMemberDTO dto = dao.getMemberInfo(no);

String id = dto.getId();
String password = dto.getPassword();
String name = dto.getName();
String phone = dto.getPhone();
String email = dto.getEmail();
int age = dto.getAge();
String joinDate = dto.getJoinDate();

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
			<td><%=no %><input type="hidden" name="no" value="<%=no %>"></td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><%=id %><input type="hidden" name="id" value="<%=id %>"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="password" id="password" value=""></td>
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
			<td>나이</td>
			<td><%=age %></td>
		</tr>
		<tr>
			<td>가입일시</td>
			<td><%=joinDate %></td>
		</tr>
	</table>
	<a href="#" onclick="deleteInfo();">[삭제하기]</a><br>
</form>

<script>

function deleteInfo() {
	const pwCheck = document.querySelector('#password').value;
	const pw = '<%=password %>';
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