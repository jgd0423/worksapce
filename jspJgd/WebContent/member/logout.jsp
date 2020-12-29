<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/ip_check.jsp" %>
<%@ include file="../include/session_check.jsp" %>
<%@ include file="../include/need_login.jsp" %>
<%@ include file="../include/menu.jsp" %>
<%

session.invalidate();
out.println("<script>");
out.println("alert('로그아웃되었습니다.')");
out.println("location.href='login.jsp';");
out.println("</script>");

%>