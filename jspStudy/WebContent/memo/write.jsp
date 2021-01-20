<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>한줄 메모장</h2></td>
		</tr>
		<tr>
			<td width="150">이름</td>
			<td><input type="text" name="writer" /></td>
		</tr>
		<tr>
			<td>메모</td>
			<td><input type="text" name="content" style="width: 400px" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" onclick="inputInfo();">확인</button>
				<button type="button" onclick="goPage();">리스트</button>
			</td>
		</tr>
	</table>
</form>

<script>

function inputInfo() {
	if (confirm("등록하시겠습니까?")) {
		document.writeForm.method = 'post';
		document.writeForm.action = '${path}/memo_servlet/writeProc.do';
		document.writeForm.submit();
	}
}

function goPage() {
	location.href = '${path}/memo_servlet/list.do';
}

</script>