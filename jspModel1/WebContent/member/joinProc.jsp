<%@page import="java.net.Inet4Address"%>
<%@page import="etc.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/include_ip_check.jsp" %>
<%@ include file="../include/include_session_check.jsp" %>
<jsp:useBean id="dto" class="etc.member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

dto.setGrade("4");
dto.setLoginFailCounter(0);
dto.setIp(ip);
MemberDAO dao = new MemberDAO();

if (!dto.getPasswd().equals(dto.getPasswdChk())) {
	out.println("<script>");
	out.println("alert('비밀번호가 틀렸습니다.')");
	out.println("location.href='join.jsp';");
	out.println("</script>");
	return;
}
int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='join.jsp';");
	out.println("</script>");
}

%>