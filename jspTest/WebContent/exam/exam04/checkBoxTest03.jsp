<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkBoxTest02</title>
</head>
<body>

<form name="checkBoxTest_form" method="post" action="checkBoxTest03Proc.jsp">
	<table>
		<tr>
			<td>
				<input type="checkbox" name="food1" value="붕어빵"> 붕어빵 &nbsp;&nbsp;
				<input type="checkbox" name="food2" value="소주"> 소주 &nbsp;&nbsp;
				<input type="checkbox" name="food3" value="맥주"> 맥주 &nbsp;&nbsp;
				<input type="checkbox" name="food4" value="양주"> 양주 &nbsp;&nbsp;
				<input type="checkbox" name="food5" value="막걸리"> 막걸리 &nbsp;&nbsp;
				<input type="checkbox" name="food6" value="와인"> 와인 &nbsp;&nbsp;
				<input type="text" name="counter" value="6">
			</td>
		</tr>
		<tr>
			<td align="center" height="40"><input type="submit"></td>
		</tr>
	</table>
</form>

</body>
</html>`