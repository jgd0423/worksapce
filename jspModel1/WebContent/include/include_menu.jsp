<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table border="1" width="800px">
	<tr>
		<td colspan="5"><%@ include file="../include/include_session_check.jsp" %></td>
	</tr>
	<% if (cookId == null) { %>
	<% } else { %>
	<% String grade = cookIdDto.getGrade(); %>
	<% System.out.println(grade);%>
	<tr>
		<% if (grade.equals("1")) { %>
			<td>회원관리(1)</td>
		<% } %>
		
		<% if (grade.equals("1") || grade.equals("2")) { %>
		<td>상품관리(2)</td>
		<% } %>
		
		<% if (grade.equals("1") || grade.equals("3")) { %>
			<td>배송관리(3)</td>
		<% } %>
		<td>문의관리</td>
		<td>주문페이지</td>
	</tr>
	<% } %>
	
</table>