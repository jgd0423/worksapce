<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>

<%
	String row_1 = request.getParameter("row_1");
	String row_2 = request.getParameter("row_2");
	String row_3 = request.getParameter("row_3");
	String row_4 = request.getParameter("row_4");
%>

<font style="color: blue; font-weight: bold; font-size: 30px">1 : <%=row_1%></font><br>
<font style="color: red; font-weight: bold; font-size: 40px">2 : <%=row_2%></font><br>
<font style="color: orange; font-weight: bold; font-size: 50px">3 : <%=row_3%></font><br>
<font style="color: cyan; font-weight: bold; font-size: 60px">4 : <%=row_4%></font>