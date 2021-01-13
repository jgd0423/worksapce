<%@page import="seongJeok.dao.SeongJeckDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="seongJeok.dto.SeongJeokDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

SeongJeckDAO dao = new SeongJeckDAO();
int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("if (confirm('목록페이지로 이동하시겠습니까? (확인: 목록, 취소: 입력)')) {");
	out.println("location.href='sjList.jsp';");
	out.println("} else { ");
	out.println("location.href='sj.jsp';");
	out.println("}");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='sj.jsp';");
	out.println("</script>");
}



%>