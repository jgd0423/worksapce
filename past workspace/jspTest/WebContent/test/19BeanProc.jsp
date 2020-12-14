<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="dto" class="test.Test19DTO" scope="page"></jsp:useBean>
	<!--  
	<jsp:setProperty name="dto" property="boss_1" />
	<jsp:setProperty name="dto" property="boss_2" />
	<jsp:setProperty name="dto" property="boss_3" />
	-->
<jsp:setProperty name="dto" property="*" />


<%
	String boss_1 = dto.getBoss_1();
	String boss_2 = dto.getBoss_2();
	String boss_3 = dto.getBoss_3();
%>

boss_1 ==> <%=boss_1 %><br>
boss_2 ==> <%=boss_2 %><br>
boss_3 ==> <%=boss_3 %><br>