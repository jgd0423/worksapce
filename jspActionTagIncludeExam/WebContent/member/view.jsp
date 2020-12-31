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

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);

MemberDAO dao = new MemberDAO();
MemberDTO dto = dao.getSelectOne(no);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
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
		<h2>상세보기</h2>
		<table border="1">
			<tr>
				<td>번호</td>
				<td><%=dto.getNo() %></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><%=dto.getId() %></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><%=dto.getPasswd() %></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><%=dto.getName() %></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><%=dto.getGender() %></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><%=dto.getBornYear() %></td>
			</tr>
			<tr>
				<td>가입일시</td>
				<td><%=dto.getRegiDate() %></td>
			</tr>
		</table>
		<br>
		<a href="#" onclick="move('M')">[수정하기]</a>
		<a href="#" onclick="move('D')">[삭제하기]</a>
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

<script>

function move(whereToGo) {
	 if (whereToGo === 'M') {	
		location.href='modify.jsp';
	} else if (whereToGo === 'D') {
		location.href='delete.jsp';
	}
}

</script>

</body>
</html>