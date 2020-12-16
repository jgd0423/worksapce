<%@page import="sj.SjDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="sj.SjDTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>

<%

String temp; 
String[] jungdab = { "1", "2", "3", "4", "3" };
String mun_ox_1 = "X";
String mun_ox_2 = "X";
String mun_ox_3 = "X";
String mun_ox_4 = "X";
String mun_ox_5 = "X";
int total = 0;

temp = Integer.toString(dto.getMun_1());
if (temp.equals(jungdab[0])) {
	mun_ox_1 = "O";
	total += 20;
}

temp = Integer.toString(dto.getMun_2());
if (temp.equals(jungdab[1])) {
	mun_ox_2 = "O";
	total += 20;
}

temp = Integer.toString(dto.getMun_3());
if (temp.equals(jungdab[2])) {
	mun_ox_3 = "O";
	total += 20;
}

temp = Integer.toString(dto.getMun_4());
if (temp.equals(jungdab[3])) {
	mun_ox_4 = "O";
	total += 20;
}

temp = Integer.toString(dto.getMun_5());
if (temp.equals(jungdab[4])) {
	mun_ox_5 = "O";
	total += 20;
}

dto.setMun_ox_1(mun_ox_1);
dto.setMun_ox_2(mun_ox_2);
dto.setMun_ox_3(mun_ox_3);
dto.setMun_ox_4(mun_ox_4);
dto.setMun_ox_5(mun_ox_5);
dto.setJumsu(total);

SjDAO dao = new SjDAO();
int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='write.jsp';"); //history.back(); history.go(-1);
	out.println("</script>");
}

%>














