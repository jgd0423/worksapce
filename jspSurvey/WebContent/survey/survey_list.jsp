<%@page import="survey.model.dto.SurveyDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="survey.model.dao.SurveyDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%

SurveyDAO dao = new SurveyDAO();
ArrayList<SurveyDTO> surveyList = dao.getSelectAll();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 리스트</title>
</head>
<body>
<h1>설문조사 리스트</h1>
<table border="1" width="800px">
	<tr>
		<td>no</td>
		<td>question</td>
		<td>s1</td>
		<td>s2</td>
		<td>s3</td>
		<td>s4</td>
		<td>status</td>
		<td>수정/삭제</td>
	</tr>
	<% for (SurveyDTO dto : surveyList) { %>
		<tr>
			<td><%=dto.getNo() %></td>
			<td><%=dto.getQuestion() %></td>
			<td><%=dto.getSelect1() %></td>
			<td><%=dto.getSelect2() %></td>
			<td><%=dto.getSelect3() %></td>
			<td><%=dto.getSelect4() %></td>
			<td><%=dto.getStatus() %></td>
			<td>
				<a href="#" onclick="move('M', '<%=dto.getNo()%>');">수정</a>&nbsp;
				<a href="#" onclick="move('D', '<%=dto.getNo()%>');">삭제</a>
			</td>
		</tr>
	<% } %>
</table>
<br>
<button type="button" id="btn">글쓰기</button>

<script>
function move(whereToGo, no) {
	 if (whereToGo === 'M') {
		location.href='survey_modify.jsp?no=' + no;
	} else if (whereToGo === 'D') {
		location.href='survey_delete.jsp?no=' + no;
	}
}

const btn = document.querySelector('#btn');
btn.addEventListener('click', () => {

	document.location = 'survey_write.jsp';

});
</script>

</body>
</html>