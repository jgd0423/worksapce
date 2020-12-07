<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.sql.*"%>

<%

String cookId = null;
if (session.getAttribute("cookId") == null) {
	out.println("<script>");
	out.println("alert('로그인 후 이용하세요');");
	out.println("location.href='login.jsp';");
	out.println("</script>");
} else {
	cookId = (String) session.getAttribute("cookId");
}

String id = request.getParameter("id");
String pw = null;
String name = null;
String phone = null;
String email = null;
String birthYear = null;

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
	String dbId = "jspTest";
	String dbPasswd = "1234";
	
	Class.forName(driver);
	conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
	System.out.println("-- 오라클 접속 성공 --");
	
	/**/
	String sql = "select * from joinTBL01 where id = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, id);
	rs = pstmt.executeQuery();
	if (rs.next()) {
		id = rs.getString("id");
		pw = rs.getString("pw");
		name = rs.getString("name");
		phone = rs.getString("phone");
		email = rs.getString("email");
		birthYear = rs.getString("birthYear");
	}
	/**/
	if (rs != null) { rs.close(); }
	if (pstmt != null) { pstmt.close(); }
	if (conn != null) { conn.close(); }
	
} catch(Exception e) {
	System.out.println("-- 오라클 접속 실패 --");
	e.printStackTrace();
} 

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원정보수정 - <%=cookId %></h1>

<form name="modify">
	<table>
		<tr>
			<td>아이디</td>
			<td><%=id %><input type="hidden" name="id" value="<%=id %>"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pw" value=""></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="<%=name %>"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" value="<%=phone %>"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="<%=email %>"></td>
		</tr>
		<tr>
			<td>출생년도</td>
			<td><input type="text" name="birthYear" value="<%=birthYear %>"></td>
		</tr>
	</table>
	<a href="#" onclick="modifyInfo();">[수정하기]</a><br>
	<a href="logout.jsp">[로그아웃]</a>
</form>

<script>

function modifyInfo() {
	if (confirm('수정하시겠습니까?')) {
		modify.method = "post";
		modify.action = "modifyProc.jsp";
		modify.submit();
	}
}

</script>

</body>
</html>

