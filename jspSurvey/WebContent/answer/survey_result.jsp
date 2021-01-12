<%@page import="java.util.ArrayList"%>
<%@page import="survey.model.dao.SurveyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

SurveyDAO dao = new SurveyDAO();
ArrayList<Integer> surveyNoList = dao.getSurveyNos();

// ArrayList<Integer> surveyNoAnswersArrayList = dao.getSurveyNoAnswersArrayList(3);
// int totalAnswerCount = 0;
// for (int i = 0; i < surveyNoAnswersArrayList.size(); i++) {
// 	totalAnswerCount += surveyNoAnswersArrayList.get(i);
// }

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 결과</title>
</head>
<body>
<h1>설문조사 결과</h1>

<% for (int j = 0; j < surveyNoList.size(); j++) { %>
	<% 
	int survey_no = surveyNoList.get(j);
	ArrayList<Integer> surveyNoAnswersArrayList = dao.getSurveyNoAnswersArrayList(survey_no);
	int totalAnswerCount = 0;
	for (int i = 0; i < surveyNoAnswersArrayList.size(); i++) {
		totalAnswerCount += surveyNoAnswersArrayList.get(i);
	}
	%>
	<table border="1">
		<tr>
			<td colspan="4">설문번호 : <%=survey_no %></td>
		</tr>
		<tr>
			<td>문항</td>
			<td>응답수</td>
			<td>응답률</td>
			<td></td>
		</tr>
		<% for (int i = 0; i < surveyNoAnswersArrayList.size(); i++) { %>
			<% String responseRate = String.format("%.2f", (double)surveyNoAnswersArrayList.get(i) / totalAnswerCount * 100); %>
			<tr>
				<td><%=i + 1 %></td>
				<td><%=surveyNoAnswersArrayList.get(i) %></td>
				<td><%=responseRate%></td>
				<td width="300px">
					<div style="background-color:blue; width:<%=responseRate %>%; height:30px"></div>
				</td>
			</tr>
		<% } %>
	</table>
	<br>
<% } %>

</body>
</html>