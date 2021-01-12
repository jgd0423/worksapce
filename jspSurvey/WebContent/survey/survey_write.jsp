<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 내용 입력</title>
</head>
<body>
<h1>설문조사 내용 입력</h1>

<form action="" name="surveyForm">
	<table>
		<tr>
			<td>질문</td>
			<td><input type="text" name="question"/></td>
		</tr>
		<tr>
			<td>선택지1</td>
			<td><input type="text" name="select1"/></td>
		</tr>
		<tr>
			<td>선택지2</td>
			<td><input type="text" name="select2"/></td>
		</tr>
		<tr>
			<td>선택지3</td>
			<td><input type="text" name="select3"/></td>
		</tr>
		<tr>
			<td>선택지4</td>
			<td><input type="text" name="select4"/></td>
		</tr>
		<tr>
			<td>서비스 여부</td>
			<td>
				<input type="radio" name="status" value="1">o
				<input type="radio" name="status" value="0">x
			</td>
		</tr>
	</table>
	<button type="button" id="btn">저장하기</button>
</form>

<script>
const btn = document.querySelector('#btn');
btn.addEventListener('click', () => {
	document.surveyForm.method = 'post';
	document.surveyForm.action = 'survey_write_proc.jsp';
	document.surveyForm.submit();
});


</script>


</body>
</html>