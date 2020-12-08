<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<%

MemberDAO dao = new MemberDAO();
MemberDTO dbDto = dao.getMemberInfo(dto.getId());

int modifyResult = dao.modifyMemberInfo(dto);

if (dto.getPw() == null) {
	out.println("<script>");
	out.println("alert('비밀번호가 틀렸습니다.');");
	out.println("location.href='modify.jsp?id=" + dto.getId() + "';");
	out.println("</script>");
} else {
	if (!dto.getPw().equals(dbDto.getPw())) {
		out.println("<script>");
		out.println("alert('비밀번호가 틀렸습니다.');");
		out.println("location.href='modify.jsp?id=" + dto.getId() + "';");
		out.println("</script>");
	} else {
		if (modifyResult > 0) {
			out.println("<script>");
			out.println("alert('수정을 완료했습니다.');");
			out.println("location.href='view.jsp?id=" + dto.getId() + "';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원정보수정 중  오류가 발생했습니다.');");
			out.println("location.href='modify.jsp?id=" + dto.getId() + "';");
			out.println("</script>");
		}
	}
}

%>