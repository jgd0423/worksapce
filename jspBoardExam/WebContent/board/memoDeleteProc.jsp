<%@page import="memo.model.dto.MemoDTO"%>
<%@page import="memo.model.dao.MemoDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);

MemoDAO dao = new MemoDAO();
MemoDTO dto = new MemoDTO();

dto.setNo(no);

int result = dao.setDelete(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 삭제되었습니다.')");
	out.println("location.href='memo.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("history.back();");
	out.println("</script>");
}

%>