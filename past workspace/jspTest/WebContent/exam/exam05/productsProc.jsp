<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>


<%
	String disableBuyMsg = "카드한도초과";
	int totalPrice = 0;
	
	String[] productNames = request.getParameterValues("productName");
	String[] productPrices = request.getParameterValues("productPrice");
	
	
	
	for (int i = 0; i < productNames.length; i++) {
		int productPrice = Integer.parseInt(productPrices[i]);
		totalPrice += productPrice;
	}
	
	if (totalPrice > 100000) {
		out.println(disableBuyMsg);
	} else { %>
		<table border="1">
			<% for (int i = 0; i < productNames.length; i++) { %>
					<tr>
						<td><%=productNames[i] %></td>
						<td>...</td>
						<td><%=productPrices[i] %></td>
					</tr>
			<% } %>
			<tr>
				<td colspan="3">-----------------</td>
			</tr>
			<tr>
				<td>합계</td>
				<td>...</td>
				<td><%=totalPrice %></td>
			</tr>
		</table>
	<% } %>













