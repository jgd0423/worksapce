<%@page import="test3.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="test3.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

ProductDAO dao = new ProductDAO();
ArrayList<ProductDTO> productsList = dao.getListAll();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영수증</title>
</head>
<body>

<h2>영수증</h2>
<table border="1">
	<tr>
		<td>상품 이름</td>
		<td>할인된 가격</td>
		<td>상품 가격</td>
	</tr>
	<% double totalSaledPrices = 0; %>
	<% int totalPrices = 0;  %>
	<% for (int i = 0; i < productsList.size(); i++) { %>
		<% ProductDTO dto = productsList.get(i); %>
		<% totalSaledPrices += dto.getSaledPrice(); %>
		<% totalPrices += dto.getPrice(); %>
		<tr>
			<td><%=dto.getName() %></td>
			<td><%=dto.getSaledPrice() %></td>
			<td><%=dto.getPrice() %></td>
		</tr>
	<% } %>
	<tr>
		<td>합계</td>
		<td><%=totalSaledPrices %></td>
		<td><%=totalPrices %>
	</tr>
</table>

</body>
</html>