<%@page import="guestbook.model.dto.GuestbookDTO"%>
<%@page import="guestbook.model.dao.GuestbookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

// dao, dto 입력하면서 import
GuestbookDAO dao = new GuestbookDAO();
GuestbookDTO dto = new GuestbookDTO();

// request.getParameter();로 정보 받아오기
String name = request.getParameter("name");
String email = request.getParameter("email");
String passwd = request.getParameter("passwd");
String content = request.getParameter("content");

// dto에 들어갈 부가정보 입력

// setter로 dto에 정보 추가
dto.setName(name);
dto.setEmail(email);
dto.setPasswd(passwd);
dto.setContent(content);

int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='write.jsp';");
	out.println("</script>");
}

%>