<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="deleteForm">
	<input type="hidden" name="no" id="no" value="${dto.no }" />
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">
				<h2>게시글삭제</h2>
			</td>
		</tr>
		<tr>
			<td style="align: center;">작성자</td>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<td style="align: center;">이메일</td>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<td style="align: center;">비밀번호</td>
			<td><input type="password" name="passwd" id="passwd" /></td>
		</tr>
		<tr>
			<td style="align: center;">제목</td>
			<td>${dto.subject }</td>
		</tr>
		<tr>
			<td style="align: center;">내용</td>
			<td>
				<textarea 
					name="content" 
					id="content" 
					style="width: 300px; height: 100px;" 
					wrap="hard"
					readonly
				>${dto.content }</textarea>
			</td>
		</tr>
		<tr>
			<td style="align: center;">공지글</td>
			<td>${dto.noticeNo > 0 ? 'T' : 'F' }</td>
		</tr>
		<tr>
			<td style="align: center;">비밀글</td>
			<td>${dto.secretGubun}</td>
		</tr>
		<tr>
			<td align="center" colspan="2" height="50px">
				<button type="button" id="btnDelete">삭제하기</button>
				<button type="button" id="btnList">목록으로</button>
			</td>
		</tr>
	</table>
</form>

<script>

$(document).ready(() => {	
	$("#btnDelete").click(() => {
		if (confirm('삭제하시겠습니까?')) {
			goDelete();
		}
	});
	
	$("#btnList").click(() => {
		goList();
	});
});

function goDelete() {
	document.deleteForm.method = 'post';
	document.deleteForm.action = '${path}/board2_servlet/deleteProc.do';
	document.deleteForm.submit();
}

function goList() {
	location.href = '${path}/board2_servlet/list.do';
}

</script>