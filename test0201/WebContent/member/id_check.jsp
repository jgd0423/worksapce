<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복확인</title>
</head>
<body>

<form name="idCheckForm">
	<input type="text" name="use_id" id="use_id" value="${resultId }"/>
	<table border="1">
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<h2>아이디중복확인</h2>
			</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" id="id" value="${resultId }" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				${result }
				<button type="button" onclick="search();">검색</button>
				<button type="button" onclick="save();">적용</button>
			</td>
		</tr>
	</table>
</form>

<script>

function save() {
	var id = document.getElementById("use_id").value;
	
	if (id === '') {
		alert('다시 검색하세요.');
		return;
	}
	
	opener.document.getElementById("id").value = id;
	window.close();
}

function search() {
	idCheckForm.method="post";
	idCheckForm.action="${path}/member_servlet/id_check_win_open_Proc.do";
	idCheckForm.submit();
}

</script>

</body>
</html>