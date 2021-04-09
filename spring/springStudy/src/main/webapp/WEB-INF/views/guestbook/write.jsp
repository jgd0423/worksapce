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
			<td><input type="text" name="name" id="name" /></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" id="email" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd" id="passwd" /></td>
		</tr>
		<tr>
			<td colspan="2"><textarea name="content" id="content" id="" cols="30" rows="10"></textarea></td>
		</tr>
	</table>
	<a href="#" id="btnWrite">[등록하기]</a>
</form>

<script>

$(document).ready(() => {
	$("#btnWrite").click(() => {
		chooseProc('writeProc', '1', '');
	});
});

</script>