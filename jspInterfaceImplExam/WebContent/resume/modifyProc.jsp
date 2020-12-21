<%@page import="jspInterfaceImplExam.model.resume.ResumeExample"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="jspInterfaceImplExam.model.resume.ResumeDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

ResumeExample dao = new ResumeExample();
int result = dao.setUpdate(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 수정되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("history.back();");
	out.println("</script>");
}
%>

