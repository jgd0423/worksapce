<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="joinForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>회원가입</h2></td>
		</tr>
		<tr>
			<td width="150">아이디</td>
			<td><input type="text" name="id" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" /></td>
		</tr>
		<tr>
			<td>비밀번호확인</td>
			<td><input type="password" name="passwdChk" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" /></td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				<input type="radio" name="gender" value="M" checked /> 남자
				&nbsp;&nbsp;&nbsp;
				<input type="radio" name="gender" value="F" /> 여자
			</td>
		</tr>
		<tr>
			<td>출생년도</td>
			<td><input type="text" name="bornYear" value="1990" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" onclick="join();">JOIN</button>
			</td>
		</tr>
	</table>
</form>

<script>
function join() {
	if (confirm("등록하시겠습니까?")) {
		document.joinForm.method = 'post';
		document.joinForm.action = '${path}/member_servlet/chugaProc.do';
		document.joinForm.submit();
	}
}
</script>