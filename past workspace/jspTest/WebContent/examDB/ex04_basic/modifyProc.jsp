<%@page import="com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil"%>
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
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String phone = request.getParameter("phone");
String email = request.getParameter("email");
String birthYear_ = request.getParameter("birthYear");
int birthYear = Integer.parseInt(birthYear_);

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
int result = 0;

try {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
	String dbId = "jspTest";
	String dbPasswd = "1234";
	
	Class.forName(driver);
	conn = DriverManager.getConnection(dbUrl, dbId, dbPasswd);
	System.out.println("-- 오라클 접속 성공 --");

	String sql = "update joinTBL01 set phone = ?, email = ?, birthyear = ? where id = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, phone);
	pstmt.setString(2, email);
	pstmt.setInt(3, birthYear);
	pstmt.setString(4, id);
	result = pstmt.executeUpdate();
	
	if (result > 0) {
		out.println("<script>");
		out.println("alert('정상적으로 수정되었습니다.');");
		out.println("location.href='modify.jsp?id=" + id + "';");
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('회원정보수정 중  오류가 발생했습니다.');");
		out.println("location.href='modify.jsp?id=" + id + "';");
		// out.println("history.back();");
		// out.println("history.go(-1);");
		out.println("</script>");
	}
	
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

</body>
</html>