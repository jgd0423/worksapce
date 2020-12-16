<%@page import="sj.SjDAO"%>
<%@page import="sj.SjDTO"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>

<%

SjDAO dao = new SjDAO();
Vector<SjDTO> arrayList = new Vector<>();
arrayList = dao.getSelectAll();

%>

<html>
<head>
<meta charset="UTF-8">
<title>성적목록</title>
</head>
<body>

<h2>성적목록</h2>

<table border="1" width="600">
	<tr>
		<th>이름</th>
		<th>시험명</th>
		<th>문제1</th>
		<th>문제2</th>
		<th>문제3</th>
		<th>문제4</th>
		<th>문제5</th>
		<th>점수</th>
	</tr>
	<% for (int i = 0; i < arrayList.size(); i++) { %>
		<% SjDTO dto = arrayList.get(i); %>
		<tr>
			<td><%=dto.getName() %></td>
			<td><%=dto.getSname() %></td>
			<td><%=dto.getMun_1() %> (<%=dto.getMun_ox_1() %>)</td>
			<td><%=dto.getMun_2() %> (<%=dto.getMun_ox_2() %>)</td>
			<td><%=dto.getMun_3() %> (<%=dto.getMun_ox_3() %>)</td>
			<td><%=dto.getMun_4() %> (<%=dto.getMun_ox_4() %>)</td>
			<td><%=dto.getMun_5() %> (<%=dto.getMun_ox_5() %>)</td>
			<td><%=dto.getJumsu() %></td>
		</tr>
	<% } %>
</table>

</body>
</html>