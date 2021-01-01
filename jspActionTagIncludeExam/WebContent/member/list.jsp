<%@page import="java.util.ArrayList"%>
<%@page import="java.net.Inet4Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/include_top_import.jsp" %>
<%@ include file="../include/include_top_common.jsp" %>
<%@ include file="../include/include_session.jsp" %>
<%@ include file="../include/include_session_check.jsp" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%

MemberDAO dao = new MemberDAO();
String pageNum_ = request.getParameter("page");

final int ONE_PAGE_ROWS = 5;
int allRowsCount = dao.getAllRowsCount();
double allPagesCount = Math.ceil((double) allRowsCount / ONE_PAGE_ROWS);
int pageNum = 1;
if (pageNum_ != null) {
	pageNum = Integer.parseInt(pageNum_);
}


ArrayList<MemberDTO> memberList = dao.getPagingList(pageNum);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>

<table border="1" width="100%">
	<tr>
		<td>
		<!-- 상단 메뉴 -->
		<jsp:include page="../include/include_top_menu.jsp" flush="false">
			<jsp:param name="ip" value="<%=ip %>"/>
			<jsp:param name="cookNo" value="<%=cookNo %>"/>
			<jsp:param name="cookName" value="<%=cookName %>"/>
		</jsp:include>
		<!-- 상단 메뉴 -->
		</td>
	</tr>
	<tr>
		<td style="padding: 50px 20px;">
		<!-- 중단 메뉴 -->
		<h2>회원목록</h2>
		<table border="1" width="1500" align="center">
			<tr>
				<td>번호</td>
				<td>아이디</td>
				<td>비밀번호</td>
				<td>이름</td>
				<td>성별</td>
				<td>생년월일</td>
				<td>가입일시</td>
			</tr>
			<% for (MemberDTO dto : memberList) { %>
				<tr>
					<td><%=dto.getNo() %></td>
					<% if (cookNo == dto.getNo()) { %>
						<td><a href="view.jsp?no=<%=dto.getNo() %>"><%=dto.getId() %></a></td>
					<% } else { %>
						<td><%=dto.getId() %></td>
					<% } %>
					<td><%=dto.getPasswd() %></td>
					<td><%=dto.getName() %></td>
					<td><%=dto.getGender() %></td>
					<td><%=dto.getBornYear() %></td>
					<td><%=dto.getRegiDate() %></td>
				</tr>
			<% } %>
		</table>
		<br>
		<div align="center">
			<% for (int i = 1; i <= allPagesCount; i++) { %>
				<% if (pageNum == i) { %>
					<%=i %>
				<% } else { %>
					<a href="list.jsp?page=<%=i %>"><%=i %></a>
				<% } %>
			<% } %>
		</div>
		<!-- 중단 메뉴 -->
		</td>
	</tr>
	<tr>
		<td>
		<!-- 하단 메뉴 -->
		<jsp:include page="../include/include_bottom_menu.jsp" flush="false"></jsp:include>
		<!-- 하단 메뉴 -->
		</td>
	</tr>
</table>

</body>
</html>