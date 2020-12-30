<%@page import="member.model.MemberDTO"%>
<%@page import="member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
int sessionForCheck = (int)session.getAttribute("cookNo");

if (no != sessionForCheck) {
	out.println("<script>");
	out.println("alert('접근권한이 없습니다.')");
	out.println("location.href='login.jsp';");
	out.println("</script>");
}


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
		<td><%@ include file="../include/include_menu.jsp" %></td>
	</tr>
	<tr>
		<td>
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
		</td>
	</tr>
	<tr>
		<td><%@ include file="../include/include_bottom.jsp" %></td>
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