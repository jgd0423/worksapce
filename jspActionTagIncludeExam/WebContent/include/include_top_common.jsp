<%@page import="java.net.Inet4Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String ip = Inet4Address.getLocalHost().getHostAddress();

if (!ip.equals("192.168.56.1")) {
	out.println("<script>");
	out.println("alert('허용된 아이피가 아닙니다.');");
	//out.println("location.href='';");
	out.println("</script>");
	return;
}


%>