<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="test.LayoutDTO" %>

<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String phone = request.getParameter("phone");

	LayoutDTO dto = new LayoutDTO();
	
	dto.setId(id);
	dto.setPassword(password);
	dto.setName(name);
	dto.setEmail(email);
	dto.setPhone(phone);
%>

dto객체를 직접 생성하는 방식 <br>
id --> <%=dto.getId()%><br>
password --> <%=dto.getPassword()%><br>
name --> <%=dto.getName()%><br>
email --> <%=dto.getEmail()%><br>
phone --> <%=dto.getPhone()%><br>