<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

String upload_path = "c:/upload";
int max_size = 10 * 1024 * 1024;

String name = "";
String phone = "";
String filename = "";
String filename2 = "";
int filesize = 0;
int filesize2 = 0;

try {
	MultipartRequest multi = new MultipartRequest(request, upload_path, max_size, "utf-8", new DefaultFileRenamePolicy());
	name = multi.getParameter("name");
	phone = multi.getParameter("phone");
	Enumeration files = multi.getFileNames();
// 	while (files.hasMoreElements()) {
// 	}
	String file1 = (String)files.nextElement();
	String file2 = (String)files.nextElement();
	filename = multi.getFilesystemName(file1);
	File f1 = multi.getFile(file1);
	filesize = (int)f1.length();
	filename2 = multi.getFilesystemName(file2);
	File f2 = multi.getFile(file2);
	filesize2 = (int)f2.length();
} catch(Exception e) {
	e.printStackTrace();
}



%>

이름 : <%=name%><br>
연락처 : <%=phone%><br>
파일1 이름 : <%=filename%><br>
파일1 크기 : <%=filesize%><br>
파일2 이름 : <%=filename2%><br>
파일2 크기 : <%=filesize2%><br>