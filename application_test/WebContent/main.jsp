<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/inc_header.jsp" %>
<% 

HttpSession sessionForCheck = request.getSession();
String cookId = (String)sessionForCheck.getAttribute("cookId");

if (cookId == null) {
	out.println("<script>");
	out.println("location.href='login.jsp'");
	out.println("</script>");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
</head>
<body>

<h2>${cookId }님 환영합니다. <a href="${path }/member_servlet/logout.do">[로그아웃]</a></h2>
<h2>메인페이지입니다.</h2>
세션ID : ${cookId }

</body>
</html>