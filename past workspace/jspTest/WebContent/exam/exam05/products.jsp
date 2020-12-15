<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제창</title>
</head>
<body>

<form name="products_form" method="post" action="productsProc.jsp">
	<table>
		<tr>
			<td>상품이름</td>
			<td>상품가격</td>
		</tr>
		<% for (int i = 0; i < 5; i++) { %>
			<tr>
				<td><input type="text" name="productName"></td>
				<td><input type="text" name="productPrice"></td>
			</tr>
		<% } %>
		<tr>
			<td colspan="2"><input type="submit"></td>
		</tr>
	</table>
</form>

</body>
</html>