<%@page import="etc.member.MemberDTO"%>
<%@page import="etc.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="etc.member.MemberDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>
<%

MemberDAO dao = new MemberDAO();
String result = dao.login(dto);
String cookId = null;
MemberDTO dbDto = dao.getSelectOne(dto.getId());

int loginFailCounter = dbDto.getLoginFailCounter() + 1;

if(result.equals("success")) {
	session.setAttribute("cookId", dto.getId()); // 세션 등록
	dao.loginCounterSetZero(dto.getId());
	out.println("<script>");
	out.println("alert('정상적으로 로그인되었습니다.');");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else if(result.equals("fail")) {
	if (loginFailCounter >= 5) {
		out.println("<script>");
		out.println("alert('로그인이 불가능합니다.');");
		out.println("location.href='login.jsp';");
		out.println("</script>");
	} else {
		dao.loginFailCounterPlus(dto.getId());
		out.println("<script>");
		out.println("alert('비밀번호가 맞지 않습니다. 다시 입력하세요. 실패횟수: "+ loginFailCounter + "');");
		out.println("location.href='login.jsp';");
		out.println("</script>");
	}
} else if(result.equals("notMember")) {
	out.println("<script>");
	out.println("if (confirm('가입되어있지 않은 회원입니다. 가입하시겠습니까?')) {");
	out.println("location.href='join.jsp';");
	out.println("} else { ");
	out.println("location.href='login.jsp';");
	out.println("}");
	out.println("</script>");
}

%>