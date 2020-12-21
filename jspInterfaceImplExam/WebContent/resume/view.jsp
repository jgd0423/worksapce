<%@page import="jspInterfaceImplExam.model.resume.ResumeExample"%>
<%@page import="jspInterfaceImplExam.model.resume.ResumeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>

<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
ResumeExample dao = new ResumeExample();
ResumeDTO dto = dao.getSelectOne(no);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>

<h2>상세보기</h2>

<table border="1" width="800">
	<tr>
		<td colspan="5">인적사항</td>
	</tr>
	<tr>
		<td rowspan="4" width="150" align="center">
			<img src="../image/<%=dto.getPic() %>" alt="<%=dto.getPic() %>" style="width:100px; height:100px;"/>
		</td>
		<td colspan="2">성명</td>
		<td colspan="2">
			<a href="view.jsp?no=<%=dto.getNo()%>"><%=dto.getName() %></a>
		</td>
	</tr>
	<tr>
		<td colspan="2">이메일</td>
		<td colspan="2"><%=dto.getEmail() %></td>
	</tr>
	<tr>
		<td colspan="2">휴대폰</td>
		<td colspan="2"><%=dto.getPhone() %></td>
	</tr>
	<tr>
		<td colspan="2">주소</td>
		<td colspan="2"><%=dto.getAddress() %></td>
	</tr>
	<tr>
		<td colspan="5">학력사항</td>
	</tr>
	<tr>
		<td>기간</td>
		<td colspan="2">학교명</td>
		<td colspan="2">전공</td>
	</tr>
	<tr>
		<td><%=dto.getGigan1() %></td>
		<td colspan="2"><%=dto.getSchool1() %></td>
		<td colspan="2"><%=dto.getJeongong1() %></td>
	</tr>
	<tr>
		<td><%=dto.getGigan2() %></td>
		<td colspan="2"><%=dto.getSchool2() %></td>
		<td colspan="2"><%=dto.getJeongong2() %></td>
	</tr>
	<tr>
		<td><%=dto.getGigan3() %></td>
		<td colspan="2"><%=dto.getSchool3() %></td>
		<td colspan="2"><%=dto.getJeongong3() %></td>
	</tr>
	<tr>
		<td><%=dto.getGigan4() %></td>
		<td colspan="2"><%=dto.getSchool4() %></td>
		<td colspan="2"><%=dto.getJeongong4() %></td>
	</tr>
	<tr>
		<td colspan="5">어학능력</td>
	</tr>
	<tr>
		<td rowspan="2" width="80" align="center">어학</td>
		<td>TOEIC</td>
		<td><%=dto.getToeic() %></td>
		<td>일본어</td>
		<td><%=dto.getJapan() %></td>
	</tr>
	<tr>
		<td>TOEFL</td>
		<td><%=dto.getToefl() %></td>
		<td>중국어</td>
		<td><%=dto.getChina() %></td>
	</tr>
</table>
<br>
<a href="#" onclick="move('M', '<%=dto.getNo()%>')">[수정하기]</a>
<a href="#" onclick="move('D', '<%=dto.getNo()%>')">[삭제하기]</a>

<script>

function move(whereToGo, no) {
	 if (whereToGo === 'M') {	
		location.href='modify.jsp?no=' + no;
	} else if (whereToGo === 'D') {
		location.href='delete.jsp?no=' + no;
	}
}

</script>

</body>
</html>