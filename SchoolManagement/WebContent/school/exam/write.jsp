<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<form name="writeForm" id="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>시험정보입력</h2></td>
		</tr>
		<tr>
			<td style="align: center;">시험종류</td>
			<td><input type="text" name="name" id="name" /></td>
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
	location.href = '${path}/exam_servlet/list.do';
}

function goWrite() {
	document.writeForm.method = 'post';
	document.writeForm.action = '${path}/exam_servlet/writeProc.do';
	document.writeForm.submit();
}

</script>