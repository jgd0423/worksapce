<%@page import="member.MemberDAO"%>
<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String cookId = null;
MemberDTO cookIdDto = null;

cookId = (String)session.getAttribute("cookId");
MemberDAO dao = new MemberDAO();
cookIdDto = dao.getSelectOne(cookId);


%>