<%@page import="memo.model.dto.MemoDTO"%>
<%@page import="memo.model.dao.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

MemoDAO dao = new MemoDAO();
MemoDTO dto = new MemoDTO();

int maxNo = dao.getMaxValue("no") + 1;

String name = request.getParameter("name");
String content = request.getParameter("content");
String subject = "메모" + maxNo;

dto.setName(name);
dto.setContent(content);
dto.setSubject(subject);

int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("location.href='memo.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("location.href='memo.jsp';");
	out.println("</script>");
}

%>