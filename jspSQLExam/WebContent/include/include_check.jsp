<%@page import="member.model.MemberDTO"%>
<%@page import="member.model.MemberDAO"%>
<%@page import="java.net.Inet4Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String ip = Inet4Address.getLocalHost().getHostAddress();
int cookNo = 0;
String cookName = "";

if (session.getAttribute("cookNo") != null) {
	cookNo = (int) session.getAttribute("cookNo");
	MemberDAO checkDao = new MemberDAO();
	MemberDTO checkDto = checkDao.getSelectOne(cookNo);
	cookName = checkDto.getName();
} 

%>