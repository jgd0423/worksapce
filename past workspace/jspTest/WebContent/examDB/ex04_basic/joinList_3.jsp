<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.sql.*"%>

<%
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
ArrayList<String> list = new ArrayList<>();

try {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
	String dbId = "jspTest";
	String dbPasswd = "1234";
	
	Class.forName(driver);
	conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
	System.out.println("-- 오라클 접속 성공 --");
	
	/**/
	String sql = "select * from joinTBL01";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	String temp = "";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원목록</h1>
<table border="1">
	<tr>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>이메일</td>
		<td>출생년도</td>
		<td>비고</td>
	</tr>
	<% while (rs.next()) {
		out.println("<tr>");
		out.println("<td>" + rs.getString("id") + "</td>");
		out.println("<td>" + rs.getString("pw") + "</td>");
		out.println("<td>" + rs.getString("name") + "</td>");
		out.println("<td>" + rs.getString("phone") + "</td>");
		out.println("<td>" + rs.getString("email") + "</td>");
		out.println("<td>" + rs.getString("birthYear") + "</td>");
		out.println("<td></td>");
		out.println("</tr>");
} %>
	<% pstmt.executeUpdate(); %>
</table>

<%
} catch(Exception e) {
	System.out.println("-- 오라클 접속 실패 --");
	e.printStackTrace();
}
%>

</body>
</html>