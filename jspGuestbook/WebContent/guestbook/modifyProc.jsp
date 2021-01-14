<%@page import="guestbook.model.dto.GuestbookDTO"%>
<%@page import="guestbook.model.dao.GuestbookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
String email = request.getParameter("email");
String passwd = request.getParameter("passwd");
String content = request.getParameter("content");


GuestbookDAO dao = new GuestbookDAO();
GuestbookDTO dto = new GuestbookDTO();

dto.setNo(no);
dto.setEmail(email);
dto.setPasswd(passwd);
dto.setContent(content);

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