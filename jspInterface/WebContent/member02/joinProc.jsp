<%@page import="member02.Member02DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="member02.Member02DTO" scope="page"></jsp:useBean>
<jsp:setProperty property="*" name="dto"/>


<%


String passwd = dto.getPasswd();
String passwdCheck = dto.getPasswdCheck();
String sid = dto.getSid();
String genderNumber_ = sid.substring(6, 7);
int genderNumber = Integer.parseInt(genderNumber_);
String birthYear_ = sid.substring(0, 2);
int birthYear = 0;
String gender = null;
int age = 0;

if (genderNumber == 1 || genderNumber == 3) {
	gender = "M";
} else {
	gender = "F";
}

if (genderNumber == 1 || genderNumber == 2) {
	birthYear_ = "19" + birthYear_;
} else {
	birthYear_ = "20" + birthYear_;
}

birthYear = Integer.parseInt(birthYear_);

age = 2020 - birthYear + 1;

dto.setGender(gender);
dto.setAge(age);

Member02DAO dao = new Member02DAO();
int result = dao.setInsert(dto);

if (passwd == null || passwdCheck == null) {
	out.println("<script>");
	out.println("alert('비밀번호를 입력하세요.');");
	out.println("location.href='join.jsp';");
	out.println("</script>");
} else {
	if (!passwd.equals(passwdCheck)) {
		out.println("<script>");
		out.println("alert('비밀번호가 다릅니다.');");
		out.println("location.href='join.jsp';");
		out.println("</script>");
	} else {
		if (result > 0) {
			out.println("<script>");
			out.println("alert('정상적으로 등록되었습니다.')");
			out.println("location.href='list.jsp';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('DB처리 중 오류가 발생했습니다.')");
			out.println("location.href='join.jsp';");
			out.println("</script>");
		}
	}
}


%>