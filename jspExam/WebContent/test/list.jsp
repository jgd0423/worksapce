<%@page import="java.util.ArrayList"%>
<%@page import="test.StudentDTO"%>
<%@page import="test.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

StudentDAO dao = new StudentDAO();
ArrayList<StudentDTO> studentsList = dao.getListAll();

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 리스트</title>
</head>
<body>

<h2>학생 리스트</h2>
<table border="1">
	<tr>
		<td>학생아이디</td>
		<td>학생이름</td>
		<td>학생전화번호</td>
		<td>부모이름</td>
		<td>부모전화번호</td>
		<td>주소</td>
		<td>학년</td>
	</tr>
	<% for (int i = 0; i < studentsList.size(); i++) { %>
		<% StudentDTO dto = studentsList.get(i); %>
		<tr>
			<td><a href="#" onclick="view('<%=dto.getSid() %>');"><%=dto.getSid() %></a></td>
			<td><%=dto.getSname() %></td>
			<td><%=dto.getSphone() %></td>
			<td><%=dto.getPname() %></td>
			<td><%=dto.getPphone() %></td>
			<td><%=dto.getAddr() %></td>
			<td><%=dto.getHakyun() %></td>
		</tr>
	<% } %>
</table>



<script>

function view(id) {
	location.href='view.jsp?id=' + id;
}

</script>

</body>
</html>