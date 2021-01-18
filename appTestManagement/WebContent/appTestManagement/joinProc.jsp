<%@page import="appTest.model.dto.AppTestDTO"%>
<%@page import="appTest.model.dao.AppTestDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

AppTestDAO dao = new AppTestDAO();
AppTestDTO dto = new AppTestDTO();

String id = request.getParameter("id");
String passwd = request.getParameter("passwd");
String passwdChk = request.getParameter("passwdChk");
String name = request.getParameter("name");
String zipcode = request.getParameter("zipcode");
String basicAddress = request.getParameter("basicAddress");
String detailAddress = request.getParameter("detailAddress");

// setter로 dto에 정보 추가
dto.setId(id);
dto.setPasswd(passwd);
dto.setPasswdChk(passwdChk);
dto.setName(name);
dto.setZipcode(zipcode);
dto.setBasicAddress(basicAddress);
dto.setDetailAddress(detailAddress);

int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='login.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='join.jsp';");
	out.println("</script>");
}

%>