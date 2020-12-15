<%@page import="sj.SjDTO"%>
<%@page import="sj.SjDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

SjDAO dao = new SjDAO();
ArrayList<SjDTO> sjList = dao.getListAll();
String[] jungdab = { "1", "2", "3", "4", "3" };

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성적목록</title>
</head>
<body>

<h2>성적목록</h2>
<p>문제 1번 정답 : <%=jungdab[0] %></p>
<p>문제 2번 정답 : <%=jungdab[1] %></p>
<p>문제 3번 정답 : <%=jungdab[2] %></p>
<p>문제 4번 정답 : <%=jungdab[3] %></p>
<p>문제 5번 정답 : <%=jungdab[4] %></p>
<br>

<table border="1" width="600">
	<tr>
		<td>이름</td>
		<td>시험명</td>
		<td>문제1</td>
		<td>문제2</td>
		<td>문제3</td>
		<td>문제4</td>
		<td>문제5</td>
		<td>점수</td>
	</tr>
	<% for (int i = 0; i < sjList.size(); i++) { %>
		<% SjDTO dto = sjList.get(i); %>
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