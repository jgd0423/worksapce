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



<h1>회원정보 상세보기 - <%=cookId %></h1>
<table>
	<tr>
		<td>아이디 :</td>
		<td><%=id %></td>
	</tr>
		<tr>
		<td>비밀번호 :</td>
		<td><%=pw %></td>
	</tr>
		<tr>
		<td>이름 :</td>
		<td><%=name %></td>
	</tr>
		<tr>
		<td>전화번호 :</td>
		<td><%=phone %></td>
	</tr>
		<tr>
		<td>이메일 :</td>
		<td><%=email %></td>
	</tr>
		<tr>
		<td>출생년도 :</td>
		<td><%=birthYear %></td>
	</tr>
</table>
<a href="modify.jsp?id=<%=id%>">[수정하기]</a>
<a href="erase.jsp?id=<%=id%>">[삭제하기]</a>
<br><br>
<a href="#" onclick="move('M', '<%=id%>')">[수정하기]</a>
<a href="#" onclick="move('D', '<%=id%>');">[삭제하기]</a>
<br><br>
<a href="#" onclick="modify('<%=id%>')">[수정하기]</a>
<a href="#" onclick="erase('<%=id%>');">[삭제하기]</a>
<br><br>
<a href="logout.jsp">[로그아웃]</a>

<script>

function move(value1, value2) {
	if (value1 == 'M') {
	alert('수정하기 페이지로 이동합니다. - ' + value2);
	location.href = 'modify.jsp?id=' + value2;
	} else {
		alert('삭제하기 페이지로 이동합니다. - ' + value2);
		location.href = 'erase.jsp?id=' + value2;
	}
}

function modify(value1) {
	alert('수정하기 페이지로 이동합니다. - ' + value1);
	location.href = 'modify.jsp?id=' + value1;
}

function erase(value1) {
	alert('삭제하기 페이지로 이동합니다. - ' + value1);
	location.href = 'erase.jsp?id=' + value1;
}


</script>

</body>
</html>