<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

int cookNo = 0;
String cookName = null;
if (session.getAttribute("cookNo") != null) {
	cookNo = (int) session.getAttribute("cookNo");
	
	MemberDAO sessionDAO = new MemberDAO();
	MemberDTO sessionDTO = sessionDAO.getSelectOne(cookNo);
	cookName = sessionDTO.getName();
}

%>