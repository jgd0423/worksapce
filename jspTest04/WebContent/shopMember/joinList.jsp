<%@page import="java.util.ArrayList"%>
<%@page import="shopMember.ShopMemberDAO"%>
<%@page import="shopMember.ShopMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

ShopMemberDAO dao = new ShopMemberDAO();
ArrayList<ShopMemberDTO> memberList = new ArrayList<>();
String state = request.getParameter("state");
if (state == null) {
	state = "1";
}
out.println(state);

memberList = dao.getListByState(state);


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>

<a href="joinList.jsp">전체회원</a>
<a href="joinList.jsp?state=a">정상회원</a>
<a href="joinList.jsp?state=b">탈퇴회원</a>
<a href="joinList.jsp?state=c">휴면회원</a>

<h1>회원목록</h1>
<table border="1">
	<tr>
		<td>회원번호</td>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>이메일</td>
		<td>생년월일</td>
		<td>가입일시</td>
		<td>비고</td>
	</tr>
	<% for (int i = 0; i < memberList.size(); i++) { %>
	<% ShopMemberDTO dto = memberList.get(i); %>
		<tr>
			<td><%=dto.getNo() %></td>
			<td><a href="#" onclick="goView('<%=dto.getNo()%>', '<%=dto.getId()%>');"><%=dto.getId() %></a></td>
			<td><%=dto.getPasswd() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getPhone() %></td>
			<td><%=dto.getEmail() %></td>
			<td><%=dto.getBirthYear() %></td>
			<td><%=dto.getwDate() %></td>
			<td></td>
		</tr>
	<% } %>
</table>

<script>
function goView(no, id) {
	location.href='view.jsp?no=' + no + '&id=' + id;
}

</script>

</body>
</html>