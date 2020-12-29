<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 

if (cookId == null) { 
out.println("<script>");
out.println("alert('로그인 후 사용하세요');");
out.println("location.href='login.jsp';");
out.println("</script>");
return;
}
%>