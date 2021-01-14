<%@page import="jspInterfaceImplExam.model.resume.ResumeDTO"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<%@page import="jspInterfaceImplExam.model.resume.ResumeExample"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

ResumeExample dao = new ResumeExample();
ResumeDTO dto = new ResumeDTO();

String upload_path = application.getRealPath("/upload/img/");

//String upload_path = "C:\\Users\\hkit\\Desktop\\jgd\\workspace\\jspInterfaceImplExam\\WebContent\\upolad\\img";
int max_size = 10 * 1024 * 1024;
String name = "";
String pic = "";
String gigan = "";
String school = "";
String jeongong = "";

try {
	MultipartRequest multi = new MultipartRequest(request, upload_path, max_size, "utf-8", new DefaultFileRenamePolicy());
	name = multi.getParameter("name");
	gigan = multi.getParameter("gigan");
	school = multi.getParameter("school");
	jeongong = multi.getParameter("jeongong");
	
	Enumeration files = multi.getFileNames();
	String file = (String)files.nextElement();

	pic = multi.getFilesystemName(file);

} catch(Exception e) {
	e.printStackTrace();
}

dto.setPic(pic);
dto.setName(name);
dto.setEmail("e");
dto.setPhone("p");
dto.setAddress("a");
dto.setToeic(1);
dto.setToefl(1);
dto.setJapan(1);
dto.setChina(1);
dto.setGigan1(gigan);
dto.setSchool1(school);
dto.setJeongong1(jeongong);
dto.setGigan2("g");
dto.setSchool2("s");
dto.setJeongong2("j");
dto.setGigan3("g");
dto.setSchool3("s");
dto.setJeongong3("j");
dto.setGigan4("g");
dto.setSchool4("s");
dto.setJeongong4("j");


int result = dao.setInsert(dto);
if (result > 0) {
	out.println("<script>");
	out.println("alert('정상적으로 등록되었습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
} else {
	out.println("<script>");
	out.println("alert('DB처리 중 오류가 발생했습니다.')");
	out.println("location.href='write.jsp';");
	out.println("</script>");
}

%>