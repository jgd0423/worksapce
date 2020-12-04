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
	
	

	while (rs.next()) {
		String id = rs.getString("id");
		String pw = rs.getString("pw");
		String name = rs.getString("name");
		String phone = rs.getString("phone");
		String email = rs.getString("email");
		int birthYear = rs.getInt("birthYear");
		

		
		temp = "";
		temp += id + "/";
		temp += pw + "/";
		temp += name + "/";
		temp += phone + "/";
		temp += email + "/";
		temp += birthYear;
		list.add(temp);
		
	}
	/**/

	pstmt.executeUpdate();
	
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
	<% for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			String[] temps = str.split("/");
			String id = temps[0];
			String pw = temps[1];
			String name = temps[2];
			String phone = temps[3];
			String email = temps[4];
			int birthYear = Integer.parseInt(temps[5]);
			
	%>

		<tr>
			<td><a href="joinView.jsp?id=<%=id%>"><%=id %></a></td>
			<td><%=pw %></td>
			<td><%=name %></td>
			<td><%=phone %></td>
			<td><%=email %></td>
			<td><%=birthYear %></td>
			<td></td>
		</tr>
	<% } %>
</table>

</body>
</html>

