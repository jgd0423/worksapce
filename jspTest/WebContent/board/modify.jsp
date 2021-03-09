<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="modifyForm">
	<input type="hidden" name="no" id="no" value="${dto.no }" />
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">
				<h2>게시글수정</h2>
			</td>
		</tr>
		<tr>
			<td style="align: center;">작성자</td>
			<td><input type="text" name="writer" id="writer" value="${dto.writer }" /></td>
		</tr>
		<tr>
			<td style="align: center;">비밀번호</td>
			<td><input type="password" name="passwd" id="passwd" /></td>
		</tr>
		<tr>
			<td style="align: center;">제목</td>
			<td><input type="text" name="subject" id="subject" value="${dto.subject }" /></td>
		</tr>
		<tr>
			<td style="align: center;">내용</td>
			<td>
				<textarea 
					name="content" 
					id="content" 
					style="width: 300px; height: 100px;" 
					wrap="hard"
				>${dto.content }</textarea>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2" height="50px">
				<button type="button" id="btnModify">수정하기</button>
				<button type="button" id="btnList">목록으로</button>
			</td>
		</tr>
	</table>
</form>

<script>

$(document).ready(() => {
	$("#subject").select();
	$("#subject").focus();
	
	$("#btnModify").click(() => {
		goModify();
	});
	
	$("#btnList").click(() => {
		goList();
	});
});

function goModify() {
	document.modifyForm.method = 'post';
	document.modifyForm.action = '${path}/board_servlet/modifyProc.do';
	document.modifyForm.submit();
}

function goList() {
	location.href = '${path}/board_servlet/list.do';
}

</script>