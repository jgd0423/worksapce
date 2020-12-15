<%@page import="java.util.ArrayList"%>
<%@page import="test2.GradesDTO"%>
<%@page import="test2.GradesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

GradesDAO dao = new GradesDAO();
ArrayList<GradesDTO> scoresList = dao.getListAll();

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 점수 리스트</title>
</head>
<body>

<h2>학생 점수 리스트</h2>
<table border="1">
	<tr>
		<td>학년</td>
		<td>시험구분</td>
		<td>국어</td>
		<td>영어</td>
		<td>수학</td>
		<td>과학</td>
		<td>역사</td>
		<td>총점</td>
		<td>평균</td>
		<td>학생아이디</td>
	</tr>
	<% for (int i = 0; i < scoresList.size(); i++) { %>
		<% GradesDTO dto = scoresList.get(i); %>
		<tr>
			<td><%=dto.getHakyun() %></td>
			<td><%=dto.getExamType() %></td>
			<td><%=dto.getKor() %></td>
			<td><%=dto.getEng() %></td>
			<td><%=dto.getMat() %></td>
			<td><%=dto.getSci() %></td>
			<td><%=dto.getHis() %></td>
			<td><%=dto.getTot() %></td>
			<td><%=dto.getAvg() %></td>
			<td><a href="#" onclick="view('<%=dto.getSid() %>');"><%=dto.getSid() %></a></td>
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