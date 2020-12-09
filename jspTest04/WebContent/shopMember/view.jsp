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
<title>회원정보 상세보기</title>
</head>
<body>

<h1>회원정보 상세보기</h1>
<table border="1">
	<tr>
		<td>회원번호</td>
		<td><%=dto.getNo() %></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><%=dto.getId() %></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><%=dto.getPasswd() %></td>
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
<a href="#" onclick="move('M', '<%=dto.getNo()%>', '<%=dto.getId()%>')">[수정하기]</a>&nbsp;&nbsp;
<a href="#" onclick="move('D', '<%=dto.getNo()%>', '<%=dto.getId()%>')">[삭제하기]</a>

<script>

function move(modOrDel, no, id) {
	if (modOrDel === "M") {
		location.href = 'modify.jsp?no=' + no + '&id=' + id;
	} else if (modOrDel === "D") {
		location.href = 'delete.jsp?no=' + no + '&id=' + id;
	}
}

</script>


</body>
</html>