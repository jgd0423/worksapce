<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String[] studentInfos = new String[18];
	String[] infoName = {"이름", "국어", "영어", "수학", "과학", "역사" };
//	int oneStudentInfoLength = 6;
//	int oneStudnetInfoPointer = 0;

	String[] name = request.getParameterValues("name");
	String[] kor = request.getParameterValues("kor");
	String[] eng = request.getParameterValues("eng");
	String[] mat = request.getParameterValues("mat");
	String[] sci = request.getParameterValues("sci");
	String[] his = request.getParameterValues("his");
	
	
	
		int studentNamePointer = 0;
	for (int i = 0; i < name.length; i++) {
		studentInfos[studentNamePointer] = name[i];
		studentNamePointer += 6;
	}
	
		int studentKorPointer = 1;
	for (int i = 0; i < kor.length; i++) {
		studentInfos[studentKorPointer] = kor[i];
		studentKorPointer += 6;
	}
	
		int studentEngPointer = 2;
	for (int i = 0; i < eng.length; i++) {
		studentInfos[studentEngPointer] = eng[i];
		studentEngPointer += 6;
	}
	
		int studentMatPointer = 3;
	for (int i = 0; i < mat.length; i++) {
		studentInfos[studentMatPointer] = mat[i];
		studentMatPointer += 6;
	}
	
		int studentSciPointer = 4;
	for (int i = 0; i < sci.length; i++) {
		studentInfos[studentSciPointer] = sci[i];
		studentSciPointer += 6;
	}
	
		int studentHisPointer = 5;
	for (int i = 0; i < his.length; i++) {
		studentInfos[studentHisPointer] = his[i];
		studentHisPointer += 6;
	}
	
//	for (int i = 0; i < studentInfos.length; i++) {
//		out.println(studentInfos[i]);
//	}
%>

<table border="1">
	<% for (int studentNum = 0; studentNum < 3; studentNum++) { %>
		<tr>
			<td>&nbsp;</td>
			<td>이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>과학</td>
			<td>역사</td>
		</tr>
			<%out.println("<td>" + (studentNum + 1) + "학생:</td>");
			for (int studentInfoPointer = (6 * studentNum); studentInfoPointer < (6 * studentNum + 6); studentInfoPointer++) {
				out.println("<td>" + studentInfos[studentInfoPointer] + "</td>");
			}
			out.println("<br>");
		}
	%>
</table>


</body>
</html>