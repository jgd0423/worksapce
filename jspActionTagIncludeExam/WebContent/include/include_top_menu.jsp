<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String ip = request.getParameter("ip");
String cookNo_ = request.getParameter("cookNo");
String cookName = request.getParameter("cookName");
int cookNo = 0;
if (cookNo_ != null) {
	cookNo = Integer.parseInt(cookNo_);
}

%>

<table border="0" align="center" width="60%">
	<tr>
		<th height="30">HOME</th>
		<th><a href="../member/list.jsp">회원관리</a></th>
		<th>게시판</th>
		<% if (cookNo != 0) { %>
			<th><a href="../member/logout.jsp">로그아웃</a> (<%=cookName %>)</th>
		<% } else { %>
			<th><a href="../member/login.jsp">로그인</a></th>
		<% } %>
		<th><%=ip %></th>
	</tr>
</table>