<%@page import="java.util.ArrayList"%>
<%@page import="survey.model.dao.SurveyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

SurveyDAO dao = new SurveyDAO();
ArrayList<Integer> surveyNoList = dao.getSurveyNos();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 결과</title>
<style type="text/css">
#table-container {
	display: flex;
	justify-content: space-evenly;
	width: 50%;
}

</style>
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
	<div id="table-container">
		<table border="1">
			<tr>
				<td colspan="3">설문번호 : <%=survey_no %></td>
			</tr>
			<tr>
				<td>문항</td>
				<td>응답수</td>
				<td>응답률</td>
			</tr>
			<% for (int i = 0; i < surveyNoAnswersArrayList.size(); i++) { %>
				<% String responseRate = String.format("%.2f", (double)surveyNoAnswersArrayList.get(i) / totalAnswerCount * 100); %>
				<tr>
					<td><%=i + 1 %></td>
					<td><%=surveyNoAnswersArrayList.get(i) %></td>
					<td><%=responseRate%></td>
				</tr>
			<% } %>
		</table>
		
		<table border="1" height="500px" width="500px">
			<tr>
				<% for (int i = 0; i < surveyNoAnswersArrayList.size(); i++) { %>
					<% String responseRate = String.format("%.2f", (double)surveyNoAnswersArrayList.get(i) / totalAnswerCount * 100); %>
						<td align="center" style="vertical-align:bottom;"><div style="background-color:blue; width:80%; height:<%=responseRate%>%"></div></td>
					<% } %>
			</tr>
			<tr height="30px">
				<% for (int i = 0; i < surveyNoAnswersArrayList.size(); i++) { %>
					<% String responseRate = String.format("%.2f", (double)surveyNoAnswersArrayList.get(i) / totalAnswerCount * 100); %>
						<td><%=i+1%> (<%=responseRate %>%)</td>
					<% } %>
			</tr>
		</table>
	</div>
	<br><br><br><br>
<% } %>

</body>
</html>