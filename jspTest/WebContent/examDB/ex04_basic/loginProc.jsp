<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.sql.*"%>

<%

String id = request.getParameter("id");
String pw = request.getParameter("pw");
String checkId = null;
String checkPw = null;

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
	
	String sql = "select * from joinTBL01 where id = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, id);
	rs = pstmt.executeQuery();
	if (rs.next()) {
		checkId = rs.getString("id");
		checkPw = rs.getString("pw");
	}
	
	String cookId = null;
	
	if (id.equals(checkId) && pw.equals(checkPw)) {
		session.setAttribute("cookId", id); // 세션 등록
		out.println("<script>");
		out.println("alert('로그인 되었습니다.');");
		out.println("location.href='joinList.jsp';");
		out.println("</script>");
	} else if (id.equals(checkId) || pw.equals(checkPw)) {
		out.println("<script>");
		out.println("alert('아이디 또는 비밀번호를 다시 입력하세요.');");
		out.println("location.href='login.jsp';");
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('회원가입을 하세요.');");
		out.println("location.href='joinForm.jsp';");
		out.println("</script>");
	}
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