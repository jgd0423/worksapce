<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="dto" class="test.Test20DTO" scope="page">
	<jsp:setProperty name="dto" property="*" />
</jsp:useBean>

<%
	String boss_1 = dto.getBoss_1();
	String boss_2 = dto.getBoss_2();
	String boss_3 = dto.getBoss_3();
	String boss_4 = dto.getBoss_4();
	String boss_5 = dto.getBoss_5();
%>

boss_1 ==> <%=boss_1 %><br>
boss_2 ==> <%=boss_2 %><br>
boss_3 ==> <%=boss_3 %><br>
boss_4 ==> <%=boss_4 %><br>
boss_5 ==> <%=boss_5 %><br>