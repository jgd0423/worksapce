<%@page import="java.util.ArrayList"%>
<%@page import="survey.model.dao.SurveyDAO"%>
<%@page import="survey.model.dto.SurveyDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

SurveyDAO dao = new SurveyDAO();
ArrayList<SurveyDTO> surveyList = dao.getSelectStatusTrue();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사</title>
</head>
<body>
<h1>설문조사</h1>

<form name="answerForm">
	<table border="1">
		<% for (SurveyDTO dto : surveyList) { %>
			<tr>
				<td>
					<%=dto.getQuestion() %>
					<input type="hidden" name="survey_no" value="<%=dto.getNo() %>" />
				</td>
			</tr>
			<tr>
				<td>
					1. <%=dto.getSelect1() %><input type="radio" name="survey_answer_<%=dto.getNo() %>" value="1">
					2. <%=dto.getSelect2() %><input type="radio" name="survey_answer_<%=dto.getNo() %>" value="2">
					3. <%=dto.getSelect3() %><input type="radio" name="survey_answer_<%=dto.getNo() %>" value="3">
					4. <%=dto.getSelect4() %><input type="radio" name="survey_answer_<%=dto.getNo() %>" value="4">
				</td>
			</tr>
		<% } %>
	</table>
	<br>
	<button type="button" id="btn">제출하기</button>
</form>

<script>
const btn = document.querySelector('#btn');
btn.addEventListener('click', () => {
	document.answerForm.method = 'post';
	document.answerForm.action = 'do_answer_proc.jsp';
	document.answerForm.submit();
});
</script>

</body>
</html>