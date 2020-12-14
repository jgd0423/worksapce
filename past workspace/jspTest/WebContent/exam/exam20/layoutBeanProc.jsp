<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="dto" class="test.LayoutDTO" scope="page" />
<jsp:setProperty property="*" name="dto"/>

<%
	String id = dto.getId();
	String password = dto.getPassword();
	String name = dto.getName();
	String email = dto.getEmail();
	String phone = dto.getPhone();
%>

useBean 방식 <br>
id --> <%=id%><br>
password --> <%=password%><br>
name --> <%=name%><br>
email --> <%=email%><br>
phone --> <%=phone%><br>