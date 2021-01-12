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
<title>설문조사 삭제</title>
</head>
<body>
<h1>설문조사 삭제</h1>

<form name="deleteForm">
	<input type="hidden" name="no" value="<%=dto.getNo()%>">
	<table border="1">
		<tr>
			<td>no : </td>
			<td><%=dto.getNo() %></td>
		</tr>
		<tr>
			<td>질문 : </td>
			<td><%=dto.getQuestion() %></td>
		</tr>
		<tr>
			<td>s1 : </td>
			<td><%=dto.getSelect1()%></td>
		</tr>
		<tr>
			<td>s2 : </td>
			<td><%=dto.getSelect2()%></td>
		</tr>
		<tr>
			<td>s3 : </td>
			<td><%=dto.getSelect3()%></td>
		</tr>
		<tr>
			<td>s4 : </td>
			<td><%=dto.getSelect4()%></td>
		</tr>
		<tr>
			<td>status : </td>
			<td>
				<input type="radio" name="status" value="1">o
				<input type="radio" name="status" value="0">x
			</td>
		</tr>
	</table>
	<button type="button" onclick="deletePost();">삭제하기</button>
</form>

<script>
function deletePost() {
	if (confirm("삭제하시겠습니까?")) {
		document.deleteForm.method = "post";
		document.deleteForm.action = "survey_delete_proc.jsp";
		document.deleteForm.submit();		
	}
}

const status = document.deleteForm.status;

for(let i = 0; i < status.length; i++) {
	if(status[i].value === '<%=dto.getStatus()%>') {
		status[i].checked = true;
	}   
}
</script>

</body>
</html>