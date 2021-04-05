<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>exam등록</h2>
	<form name="form1" method="post" action="${path }/examWriteProc.do">
		<table border="1" width="400">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>주민번호</td>
				<td><input type="text" name="socialNumber"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>