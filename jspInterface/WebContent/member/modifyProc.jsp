<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="dto" class="member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

MemberDAO dao = new MemberDAO();
MemberDTO dbDto = dao.getSelectOne(dto.getNo());
String passwd = dto.getPasswd();
String dbPasswd = dbDto.getPasswd();

if (!passwd.equals(dbPasswd)) {
	out.println("<script>");
	out.println("alert('비밀번호가 다릅니다.')");
	out.println("history.back();");
	out.println("</script>");
}  else {
	int result = dao.setUpdate(dto);
	if (result > 0) {
		out.println("<script>");
		out.println("alert('정상적으로 수정되었습니다.')");
		out.println("location.href='view.jsp?no=" + dto.getNo() + "';");
		out.println("</script>");
	} else {
		out.println("<script>");
		out.println("alert('DB처리 중 오류가 발생했습니다.')");
		out.println("history.back();");
		out.println("</script>");
	}
}

%>