<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
<meta charset="UTF-8">
<title>성적 입력</title>
</head>
<body>

<h2>성적 입력</h2>

<form name="scores">
	<table border="1">
		<tr>
			<td>학년</td>
			<td><input type="text" name="hakyun"></td>
		</tr>
		<tr>
			<td>시험구분</td>
			<td>
				<select name="examType">
					<option value="">시험 종류</option>
					<option value="midterm">중간고사</option>
					<option value="final">기말고사</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>국어</td>
			<td><input type="text" name="kor" id="kor"></td>
		</tr>
		<tr>
			<td>영어</td>
			<td><input type="text" name="eng" id="eng"></td>
		</tr>
		<tr>
			<td>수학</td>
			<td><input type="text" name="mat" id="mat"></td>
		</tr>
		<tr>
			<td>과학</td>
			<td><input type="text" name="sci" id="sci"></td>
		</tr>
		<tr>
			<td>역사</td>
			<td><input type="text" name="his" id="his"></td>
		</tr>
		<tr>
			<td>학생아이디</td>
			<td><input type="text" name="sid"></td>
		</tr>
	</table>
	<input type="hidden" name="tot" id="tot">
	<input type="hidden" name="avg" id="avg">
	<button type="button" onclick="save();">입력</button>
</form>

<script>
function save() {
	if (document.scores.hakyun.value === "") {
		alert("학년을 입력하세요.");
		document.scores.hakyun.value();
		return false;
	}
	
	if (document.scores.examType.value === "") {
		alert("시험종류를 입력하세요.");
		document.scores.examType.value();
		return false;
	}
	
	if (document.scores.kor.value === "") {
		alert("국어점수를 입력하세요.");
		document.scores.kor.value();
		return false;
	}
	
	if (document.scores.eng.value === "") {
		alert("영어점수를 입력하세요.");
		document.scores.eng.value();
		return false;
	}
	
	if (document.scores.mat.value === "") {
		alert("수학점수를 입력하세요.");
		document.scores.mat.value();
		return false;
	}
	
	if (document.scores.sci.value === "") {
		alert("과학점수를 입력하세요.");
		document.scores.sci.value();
		return false;
	}
	
	if (document.scores.sid.value === "") {
		alert("학생아이디를 입력하세요.");
		document.scores.sid.value();
		return false;
	}
	
	const kor = Number(document.querySelector("#kor").value);
	const eng = Number(document.querySelector("#eng").value);
	const mat = Number(document.querySelector("#mat").value);
	const sci = Number(document.querySelector("#sci").value);
	const his = Number(document.querySelector("#his").value);
	const tot = kor + eng + mat + sci + his;
	const avg = tot / 5;
	
	document.querySelector("#tot").value = tot;
	document.querySelector("#avg").value = avg;

	if (confirm("등록하시겠습니까?")) {
		document.scores.method = "post";
		document.scores.action = "inputScoresProc.jsp";
		document.scores.submit();		
	}


}

</script>

</body>
</html>