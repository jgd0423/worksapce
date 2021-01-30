<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="1">
	<tr>
		<td colspan="3">설문번호 : ${dto.no }</td>
	</tr>
	<tr>
		<td>문항</td>
		<td>응답수</td>
		<td>응답률</td>
	</tr>
	<c:forEach var="i" begin="0" end="${surveyNoAnswers.size() - 1 }" step="1" >
		<tr>
			<td>${i + 1 }</td>
			<td>${surveyNoAnswers[i] }</td>
			<td>${answersResponseRate[i] }</td>
		</tr>
	</c:forEach>
</table>
<br>
<br>
<table border="1" height="500px" width="500px">
	<tr>
		<c:forEach var="i" begin="0" end="${surveyNoAnswers.size() - 1 }" step="1" >
			<td align="center" style="vertical-align:bottom;"><div style="background-color:blue; width:80%; height:${answersResponseRate[i]}%"></div></td>
		</c:forEach>
	</tr>
	<tr height="30px">
		<c:forEach var="i" begin="0" end="${surveyNoAnswers.size() - 1 }" step="1" >
			<td>${i + 1 } (${answersResponseRate[i] }%)</td>
		</c:forEach>
	</tr>
</table>
<br>
<br>
<button type="button" onclick="goList()">목록으로</button>


<script>

function goList() {
	location.href = '${path}/survey2_servlet/list.do';
}

</script>