<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>제목은?</h1>
<p>
단락입니다.<br>
단락입니다.<br>
단락입니다.<br>
단락입니다.<br>
단락입니다.
</p>
안녕하세요? <br><br>
오늘은 12월의 첫날입니다.

<hr>

<%
	String name = "홍길동";
	//System.out.println(name);
	out.println(name + "<br>");
	out.println(name);
	int age = 19;
%>

<h2><% out.println(name); out.println(age); %></h2>
<h2><%=name%> <%=age%></h2>


</body>
</html>

















