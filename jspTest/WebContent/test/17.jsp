<%@page import="javax.sound.midi.Soundbank"%>
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
	String[] a = { "a", "b", "c", "d", "e" };
	out.println("0번째 인덱스의 값 = " + a[0] + "<br>");
	out.println("1번째 인덱스의 값 = " + a[1] + "<br>");
	out.println("2번째 인덱스의 값 = " + a[2] + "<br>");
	out.println("3번째 인덱스의 값 = " + a[3] + "<br>");
	out.println("4번째 인덱스의 값 = " + a[4] + "<br>");
	
	for (int i = 0; i < a.length; i++) {
		out.println(i + "번째 인덱스의 값 = " + a[i] + "<br>");
	}
	
	String[] b = new String[5];
	b[1] = "b";
	b[4] = "e";
	b[b.length - 1] = "e";
	b[b.length - 1] = null;
	out.println(b[4]);
%>

</body>
</html>