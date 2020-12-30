<%@page import="javax.swing.text.View"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include_check.jsp" %>

<table border="0" align="center" width="800">
	<tr>
		<th height="30">HOME</th>
		<th>회원관리</th>
		<th>게시판</th>
		<%
			String isLogin = (cookNo != 0) 
			? 
			"<a href=\"../member/logout.jsp\">로그아웃</a> (" + cookName + 
			") <a href=\"modify.jsp\">[회원정보수정]</a> <a href=\"delete.jsp\">[회원정보삭제]</a>"
			:
			"<a href=\"../member/login.jsp\">로그인</a>"; 
		%>
		<th><%=isLogin %></th>
		<th>접속IP: <%=ip %></th>
	</tr>
</table>