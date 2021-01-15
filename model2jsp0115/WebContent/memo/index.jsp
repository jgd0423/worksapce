<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

String temp = request.getContextPath() + "/memo/write.jsp";
//response.sendRedirect(temp);

%>
<!-- html 메타태그 -->
<meta http-equiv="refresh" content="0;url=<%=temp%>">


</body>
</html>