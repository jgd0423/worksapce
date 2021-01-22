<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form action="" name="writeForm">
	<table border="1">
		<tr>
			<td colspan="2"><h2>방명록 작성</h2></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd" /></td>
		</tr>
		<tr>
			<td colspan="2"><textarea name="content" id="" cols="30" rows="10"></textarea></td>
		</tr>
	</table>
	<a href="#" onclick="inputInfo();">[등록하기]</a>
</form>

<script>

function inputInfo() {
	if (confirm("등록하시겠습니까?")) {
		document.writeForm.method = 'post';
		document.writeForm.action = '${path}/guestbook_servlet/writeProc.do';
		document.writeForm.submit();		
	}
}

</script>