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
	String name = request.getParameter("name");
	String kor_ = request.getParameter("kor");
	String eng_ = request.getParameter("eng");
	String mat_ = request.getParameter("mat");
	String sci_ = request.getParameter("sci");
	String his_ = request.getParameter("his");
	int kor = Integer.parseInt(kor_);
	int eng = Integer.parseInt(eng_);
	int mat = Integer.parseInt(mat_);
	int sci = Integer.parseInt(sci_);
	int his = Integer.parseInt(his_);
	int tot = kor + eng + mat + sci + his;
	double avg = Math.round((double) tot / 5);
	String grade = "";
	
	if (avg >= 90) {
		grade = "수";
	} else if (avg >= 80) {
		grade = "우";
	} else if (avg >= 70) {
		grade = "미";
	} else if (avg >= 60) {
		grade = "양";
	} else {
		grade = "가";
	}
	
	//response.sendRedirect("point2.jsp?name=" + name + "&tot=" + tot); //흐름제어
%>

<!-- 
<script>
	location.href="point2.jsp?name=<%=name%>&tot=<%=tot%>";
</script>
 -->
 
<form name="form" method="post" action="point2.jsp">
	<input type="hidden" name="name" value="<%=name %>">
	<input type="hidden" name="kor" value="<%=kor %>">
	<input type="hidden" name="eng" value="<%=eng %>">
	<input type="hidden" name="mat" value="<%=mat %>">
	<input type="hidden" name="sci" value="<%=sci %>">
	<input type="hidden" name="his" value="<%=his %>">
	<input type="hidden" name="tot" value="<%=tot %>">
	<input type="hidden" name="avg" value="<%=avg %>">
	<input type="hidden" name="grade" value="<%=grade %>">
</form>

<script>
	document.form.submit();
</script>

<table border="1">
	<tr>
		<td>이름</td>
		<td>국어</td>
		<td>영어</td>
		<td>수학</td>
		<td>과학</td>
		<td>역사</td>
		<td>총점</td>
		<td>평균</td>
		<td>등급</td>
	</tr>
	<tr>
		<td><%=name %></td>
		<td><%=kor %></td>
		<td><%=eng %></td>
		<td><%=mat %></td>
		<td><%=sci %></td>
		<td><%=his %></td>
		<td><%=tot %></td>
		<td><%=avg %></td>
		<% if (grade.equals("가")) { %>
			<td style="color: red"><%=grade %></td>
		<% } else { %>
			<td><%=grade %></td>
		<% } %>
	</tr>
</table>

</body>
</html>