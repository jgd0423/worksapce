<%@page import="jspInterfaceImplExam.model.resume.ResumeDTO"%>
<%@page import="jspInterfaceImplExam.model.resume.ResumeExample"%>
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
<title>이력서 수정</title>
</head>
<body>

<h2>이력서 수정</h2>

<form name="modifyForm">
	<input type="hidden" name="no" value="<%=dto.getNo() %>" />
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
				<input type="text" name="name" value="<%=dto.getName() %>" />
			</td>
		</tr>
		<tr>
			<td colspan="2">이메일</td>
			<td colspan="2"><input type="text" name="email" value="<%=dto.getEmail() %>" /></td>
		</tr>
		<tr>
			<td colspan="2">휴대폰</td>
			<td colspan="2"><input type="text" name="phone" value="<%=dto.getPhone() %>" /></td>
		</tr>
		<tr>
			<td colspan="2">주소</td>
			<td colspan="2"><input type="text" name="address" value="<%=dto.getAddress() %>" /></td>
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
			<td><input type="text" name="gigan1" value="<%=dto.getGigan1() %>"></td>
			<td colspan="2"><input type="text" name="school1" value="<%=dto.getSchool1() %>"></td>
			<td colspan="2"><input type="text" name="jeongong1" value="<%=dto.getJeongong1() %>"></td>
		</tr>
		<tr>
			<td><input type="text" name="gigan2" value="<%=dto.getGigan2() %>"></td>
			<td colspan="2"><input type="text" name="school2" value="<%=dto.getSchool2() %>"></td>
			<td colspan="2"><input type="text" name="jeongong2" value="<%=dto.getJeongong2() %>"></td>
		</tr>
		<tr>
			<td><input type="text" name="gigan3" value="<%=dto.getGigan3() %>"></td>
			<td colspan="2"><input type="text" name="school3" value="<%=dto.getSchool3() %>"></td>
			<td colspan="2"><input type="text" name="jeongong3" value="<%=dto.getJeongong3() %>"></td>
		</tr>
		<tr>
			<td><input type="text" name="gigan4" value="<%=dto.getGigan4() %>"></td>
			<td colspan="2"><input type="text" name="school4" value="<%=dto.getSchool4() %>"></td>
			<td colspan="2"><input type="text" name="jeongong4" value="<%=dto.getJeongong4() %>"></td>
		</tr>
		<tr>
			<td colspan="5">어학능력</td>
		</tr>
		<tr>
			<td rowspan="2" width="80" align="center">어학</td>
			<td>TOEIC</td>
			<td><input type="text" name="toeic" value="<%=dto.getToeic() %>"></td>
			<td>일본어</td>
			<td><input type="text" name="japan" value="<%=dto.getJapan() %>"></td>
		</tr>
		<tr>
			<td>TOEFL</td>
			<td><input type="text" name="toefl" value="<%=dto.getToefl() %>"></td>
			<td>중국어</td>
			<td><input type="text" name="china" value="<%=dto.getChina() %>"></td>
		</tr>
	</table>
	<a href="#" onclick="modifyInfo();">[수정하기]</a>
</form>

<script>
function modifyInfo() {
	if (confirm("수정하시겠습니까?")) {
		document.modifyForm.method = "post";
		document.modifyForm.action = "modifyProc.jsp";
		document.modifyForm.submit();		
	}
}
</script>

</body>
</html>