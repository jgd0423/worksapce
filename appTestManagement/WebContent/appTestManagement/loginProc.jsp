<%@page import="appTest.model.dto.AppTestDTO"%>
<%@page import="appTest.model.dao.AppTestDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>

<%

AppTestDAO dao = new AppTestDAO();
AppTestDTO dto = new AppTestDTO();

String id = request.getParameter("id");
String passwd = request.getParameter("passwd");

dto.setId(id);
dto.setPasswd(passwd);

int result = dao.getLogin(dto);
int cookNo = 0;

if(result > 0) {
	session.setAttribute("cookNo", result); // 세션 등록
	out.println("<script>");
	out.println("alert('정상적으로 로그인되었습니다.');");
	out.println("location.href='welcome.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('비밀번호가 맞지 않거나 회원이 아닙니다.');");
	out.println("location.href='login.jsp';");
	out.println("</script>");
}

%>