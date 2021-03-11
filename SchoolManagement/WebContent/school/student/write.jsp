<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<form name="writeForm" id="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>학생정보입력</h2></td>
		</tr>
		<tr>
			<td style="align: center;">이름</td>
			<td><input type="text" name="name" id="name" /></td>
		</tr>
		<tr>
			<td style="align: center;">전화번호</td>
			<td><input type="text" name="phone" id="phone" /></td>
		</tr>
		<tr>
			<td style="align: center;">이메일</td>
			<td><input type="text" name="email" id="email" /></td>
		</tr>
		<tr>
			<td style="align: center;">주소</td>
			<td><input type="text" name="address" id="address"/></td>
		</tr>
		<tr>
			<td style="align: center;">학년</td>
			<td><input type="text" name="grade" id="grade"/></td>
		</tr>
		<tr>
			<td style="align: center;">반</td>
			<td><input type="text" name="classes" id="classes"/></td>
		</tr>
		<tr>
			<td align="center" colspan="2" height="50px">
				<button type="button" onclick="goWrite()" id="btnWrite">등록하기</button>
				<button type="button" onclick="goList()" id="btnList">목록으로</button>
			</td>
		</tr>
	</table>
</form>

<script>

function goList() {
	location.href = '${path}/student_servlet/list.do';
}

function goWrite() {
	document.writeForm.method = 'post';
	document.writeForm.action = '${path}/student_servlet/writeProc.do';
	document.writeForm.submit();
}

</script>