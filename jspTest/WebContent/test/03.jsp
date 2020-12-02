<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연습03</title>
</head>
<body>

<form name="form" method="post" action="03Proc.jsp">
	<table border="1" width="400">
		<tr align="center">
			<td width="100">이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr align="center">
			<td>나이</td>
			<td><input type="text" name="age"></td>
		</tr>
		<tr align="center">
			<td colspan="2" height="40">
				<input type="submit">
			</td>
		</tr>
	</table>
</form>

<br>
<br>
<br>

<form name="form2" method="post" action="03Proc2.jsp">
	<table border="1" width="400">
		<tr align="center">
			<td width="100">영화제목 :</td>
			<td><input type="text" name="subject"></td>
		</tr>
		<tr align="center">
			<td>상영관: </td>
			<td><input type="text" name="place"></td>
		</tr>
		<tr align="center">
			<td colspan="2" height="40">
				<input type="submit">
			</td>
		</tr>
	</table>
</form>


</body>
</html>
























