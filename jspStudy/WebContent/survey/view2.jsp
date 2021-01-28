<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="answerForm">
	<input type="hidden" name="no" value="${dto.no }" />
	<table border="1" align="center" width="80%">
		<tr>
			<td align="center">
				<h2>설문조사 상세보기</h2>
			</td>
		</tr>
		<tr>
			<td>Q) ${dto.question }</td>
		</tr>
		<tr>
			<td><input type="radio" name="answer" value="1"/>${dto.ans1 }</td>
		</tr>
		<tr>
			<td><input type="radio" name="answer" value="2" />${dto.ans2 }</td>
		</tr>
		<tr>
			<td><input type="radio" name="answer" value="3" />${dto.ans3 }</td>
		</tr>
		<tr>
			<td><input type="radio" name="answer" value="4" />${dto.ans4 }</td>
		</tr>
		<tr>
			<td>상태: ${dto.status }</td>
		</tr>
		<tr>
			<td>기간: ${dto.start_date } ~ ${dto.last_date }</td>
		</tr>
		<tr>
			<td align="right">
				<button type="button" onclick="goAnswer()">설문조사 저장하기</button>&nbsp;
				<button type="button" onclick="goList()">목록으로</button>&nbsp;
				<button type="button" onclick="goResult()">설문조사 결과보기</button>&nbsp;
			</td>
	</table>
</form>

<script>

function goAnswer() {
	document.answerForm.method = 'post';
	document.answerForm.action = '${path}/survey_servlet/answer.do';
	document.answerForm.submit();
}

function goResult() {
	document.answerForm.method = 'post';
	document.answerForm.action = '${path}/survey_servlet/result.do';
	document.answerForm.submit();
}


	
</script>