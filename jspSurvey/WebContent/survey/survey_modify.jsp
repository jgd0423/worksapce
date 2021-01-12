<%@page import="survey.model.dto.SurveyDTO"%>
<%@page import="survey.model.dao.SurveyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<%

String no_ = request.getParameter("no");
int no = Integer.parseInt(no_);
SurveyDAO dao = new SurveyDAO();
SurveyDTO dto = dao.getSelectOne(no);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 수정</title>
</head>
<body>

<h1>설문조사 수정</h1>
<form name="modifyForm">
	<input type="hidden" name="no" value="<%=dto.getNo()%>">
	<table border="1">
		<tr>
			<td>no : </td>
			<td><%=dto.getNo() %></td>
		</tr>
		<tr>
			<td>질문 : </td>
			<td><input type="text" name="question" value="<%=dto.getQuestion() %>" /></td>
		</tr>
		<tr>
			<td>s1 : </td>
			<td><input type="text" name="select1" value="<%=dto.getSelect1()%>" /></td>
		</tr>
		<tr>
			<td>s2 : </td>
			<td><input type="text" name="select2" value="<%=dto.getSelect2()%>" /></td>
		</tr>
		<tr>
			<td>s3 : </td>
			<td><input type="text" name="select3" value="<%=dto.getSelect3()%>" /></td>
		</tr>
		<tr>
			<td>s4 : </td>
			<td><input type="text" name="select4" value="<%=dto.getSelect4()%>" /></td>
		</tr>
		<tr>
			<td>status : </td>
			<td>
				<input type="radio" name="status" value="1">o
				<input type="radio" name="status" value="0">x
			</td>
		</tr>
	</table>
	<button type="button" onclick="modifyPost();">수정하기</button>
</form>

<script>
function modifyPost() {
	if (confirm("수정하시겠습니까?")) {
		document.modifyForm.method = "post";
		document.modifyForm.action = "survey_modify_proc.jsp";
		document.modifyForm.submit();		
	}
}

const status = document.modifyForm.status;

for(let i = 0; i < status.length; i++) {
	if(status[i].value === '<%=dto.getStatus()%>') {
		status[i].checked = true;
	}   
}
</script>

</body>
</html>