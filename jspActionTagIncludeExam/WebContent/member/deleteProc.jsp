<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<%@ include file="../include/include_top_import.jsp" %>
<%@ include file="../include/include_top_common.jsp" %>
<%@ include file="../include/include_session.jsp" %>
<%@ include file="../include/include_session_check.jsp" %>
<jsp:useBean id="dto" class="member.model.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

MemberDAO dao = new MemberDAO();
MemberDTO dbDto = dao.getSelectOne(cookNo);

if (!dto.getPasswd().equals(dbDto.getPasswd())) {
	out.println("<script>");
	out.println("alert('비밀번호가 틀렸습니다.')");
	out.println("location.href='delete.jsp';");
	out.println("</script>");
	return;
}
int result = dao.setDelete(dto);
if (result > 0) {
	session.invalidate();
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