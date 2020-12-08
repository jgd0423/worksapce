<%@page import="shopMember.ShopMemberDTO"%>
<%@page import="shopMember.ShopMemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="shopMember.ShopMemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<%

ShopMemberDAO dao = new ShopMemberDAO();
ShopMemberDTO dbDto = dao.getMemberInfo(Integer.toString(dto.getNo()));

int modifyResult = dao.modifyMemberInfo(dto);

if (dto.getPassword() == null) {
	out.println("<script>");
	out.println("alert('비밀번호가 틀렸습니다.');");
	out.println("location.href='modify.jsp?no=" + dto.getNo() + "';");
	out.println("</script>");
} else {
	if (!dto.getPassword().equals(dbDto.getPassword())) {
		out.println("<script>");
		out.println("alert('비밀번호가 틀렸습니다.');");
		out.println("location.href='modify.jsp?no=" + dto.getNo() + "';");
		out.println("</script>");
	} else {
		if (modifyResult > 0) {
			out.println("<script>");
			out.println("alert('수정을 완료했습니다.');");
			out.println("location.href='view.jsp?no=" + dto.getNo() + "';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원정보수정 중  오류가 발생했습니다.');");
			out.println("location.href='modify.jsp?no=" + dto.getNo() + "';");
			out.println("</script>");
		}
	}
}
%>