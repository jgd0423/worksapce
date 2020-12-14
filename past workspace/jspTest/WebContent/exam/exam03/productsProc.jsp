<%@page import="java.awt.print.Printable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>

<%
	String[] names = request.getParameterValues("name");
	String[] prices = request.getParameterValues("price");

	String msg = "";
	int totalPrice = 0;
	
	for (int i = 0; i < names.length; i++) {
		String name = names[i];
		String price_ = prices[i];
		int price = Integer.parseInt(price_);
		totalPrice += price;
		
		msg += name + " ..... " + price + "<br>";
	}
	msg += "--------------<br>";
	msg += "합계 ..... " + totalPrice;
%>

<%=msg %>

