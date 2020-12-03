<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%
	String boss_1 = request.getParameter("boss_1");
	String boss_2 = request.getParameter("boss_2");
	String boss_3 = request.getParameter("boss_3");
	
	//데이타베이스 저장
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
		
		String sql = "insert into boss values (seq_boss.nextVal, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boss_1);
		pstmt.setString(2, boss_2);
		pstmt.setString(3, boss_3);
		result = pstmt.executeUpdate();
		
		if (result > 0) { // 성공
			// response.sendRedirect("list.jsp");
		
			out.println("<script>");
			out.println("alert('정상 처리 되었습니다.');");
			out.println("location.href='list.jsp';");
			out.println("</script>");
		} else { // 실패
			out.println("<script>");
			out.println("alert('처리중 오류가 발생했습니다.');");
			out.println("location.href='boss.jsp';");
			out.println("</script>");
		}
		
		System.out.println(result);
	} catch(Exception e) {
		System.out.println("-- 오라클 접속 실패 --");
		e.printStackTrace();
	}
%>












