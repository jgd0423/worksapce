<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

session.invalidate();
out.println("<script>");
out.println("alert('로그아웃되었습니다.');");
out.println("location.href='joinList.jsp';");
out.println("</script>");

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