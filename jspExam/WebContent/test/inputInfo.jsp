<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보 입력</title>
</head>
<body>

<h2>학생 정보 입력</h2>

<form name="studentInfo">
	<table>
		<tr>
			<td>학생 아이디 : </td>
			<td><input type="text" name="sid"></td>
		</tr>
		<tr>
			<td>학생 이름 : </td>
			<td><input type="text" name="sname"></td>
		</tr>
		<tr>
			<td>학생 전화번호 : </td>
			<td><input type="text" name="sphone"></td>
		</tr>
		<tr>
			<td>부모 이름 : </td>
			<td><input type="text" name="pname"></td>
		</tr>
		<tr>
			<td>부모 전화번호 : </td>
			<td><input type="text" name="pphone"></td>
		</tr>
		<tr>
			<td>주소 : </td>
			<td><input type="text" name="addr"></td>
		</tr>
		<tr>
			<td>학년 : </td>
			<td><input type="text" name="hakyun"></td>
		</tr>
	</table>
	<button type="button" onclick="save();">저장하기</button>
</form>

<script>

function save() {
	if (document.studentInfo.sid.value === "") {
		alert("학생 아이디를 입력하세요.");
		document.studentInfo.sid.value();
		return false;
	}
	
	if (document.studentInfo.sname.value === "") {
		alert("학생 이름을 입력하세요.");
		document.studentInfo.sname.value();
		return false;
	}
	
	if (document.studentInfo.sphone.value === "") {
		alert("학생 전화번호를 입력하세요.");
		document.studentInfo.sphone.value();
		return false;
	}
	
	if (document.studentInfo.sname.value === "") {
		alert("부모 이름을 입력하세요.");
		document.studentInfo.sname.value();
		return false;
	}
	
	if (document.studentInfo.pphone.value === "") {
		alert("부모 전화번호를 입력하세요.");
		document.studentInfo.pphone.value();
		return false;
	}
	
	if (document.studentInfo.addr.value === "") {
		alert("주소를 입력하세요.");
		document.studentInfo.addr.value();
		return false;
	}
	
	if (document.studentInfo.hakyun.value === "") {
		alert("학년을 입력하세요.");
		document.studentInfo.hakyun.value();
		return false;
	}
	
	if (confirm("등록하시겠습니까?")) {
		document.studentInfo.method = "post";
		document.studentInfo.action = "inputInfoProc.jsp";
		document.studentInfo.submit();		
	}
}

</script>

</body>
</html>