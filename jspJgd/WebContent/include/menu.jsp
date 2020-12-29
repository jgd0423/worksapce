<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table border="1" width="800px">
	<% if (cookId == null) { %>
	<% } else { %>
	<tr>
		<td colspan="5">안녕하세요 <%=cookId %>님 <a href="logout.jsp">로그아웃</a></td>
	</tr>
	<% String grade = cookIdDto.getGrade(); %>
	<tr>
		<% if (grade.equals("1")) { %>
			<td><a href="gradePage.jsp">회원등급수정(1)</a></td>
		<% } %>
		
		<% if (grade.equals("1") || grade.equals("2")) { %>
		<td><a href="loginCountPage.jsp">회원로그인수정(2)</a></td>
		<% } %>
		<td><a href="modify.jsp?id=<%=cookId%>">개인정보수정(3)</a></td>
	</tr>
	<% } %>
	
</table>