<%@page import="jspInterfaceTest.model.member.MemberDTO"%>
<%@page import="jspInterfaceTest.model.member.MemberExample"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="jspInterfaceTest.model.member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<%

MemberExample dao = new MemberExample();
MemberDTO dbDto = dao.getOneMember(dto.getId());

if (!dto.getPwd().equals(dbDto.getPwd())) {
	out.println("<script>");
	out.println("alert('비밀번호가 틀렸습니다.')");
	out.println("location.href='delete.jsp?id=" + dto.getId() +"';");
	out.println("</script>");
	return;
}
int result = dao.setDelete(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 삭제되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='delete.jsp?id=" + dto.getId() +"';");
	out.println("</script>");
}

%>