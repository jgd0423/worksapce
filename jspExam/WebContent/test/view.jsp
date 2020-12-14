<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@page import="test.StudentDTO"%>
<%@page import="test.StudentDAO"%>

<%

String id = request.getParameter("id");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세정보</title>
</head>
<body>

<h2>상세정보</h2>

<form name="studentInfo">
	<table>
		<tr>
			<td>학생 아이디 : </td>
			<td><input type="text" name="sid"></td>
		</tr>
		<tr>
			<td>학생 이름 : </td>
			<td><input type="text" name="sname"></td>
		</tr>
		<tr>
			<td>학생 전화번호 : </td>
			<td><input type="text" name="sphone"></td>
		</tr>
		<tr>
			<td>부모 이름 : </td>
			<td><input type="text" name="pname"></td>
		</tr>
		<tr>
			<td>부모 전화번호 : </td>
			<td><input type="text" name="pphone"></td>
		</tr>
		<tr>
			<td>주소 : </td>
			<td><input type="text" name="addr"></td>
		</tr>
		<tr>
			<td>학년 : </td>
			<td><input type="text" name="hakyun"></td>
		</tr>
	</table>
	<button type="button" onclick="save();">저장하기</button>
</form>

</body>
</html>