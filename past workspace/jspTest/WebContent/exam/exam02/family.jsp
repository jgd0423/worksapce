<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="family_form" method="post" action="family_Proc.jsp">
	<table border="0">
		<% for (int i = 1; i < 5; i++) { %>
			<tr>
				<td>
					<input type="text" name="row_<%=i%>">
				</td>
			</tr>
		<% } %>
			<tr>
				<td>
					<input type="submit">
				</td>
			</tr>

	</table>
</form>

</body>
</html>