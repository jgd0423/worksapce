<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="viewForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">
				<h1>글상세보기</h1>
				<input type="hidden" name="no" value="${dto.no }" />
			</td>
		</tr>
		<tr>
			<td width="150px">작성자 : </td>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<td>제목 : </td>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td id="content">${dto.content}</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="goWrite()">글쓰기</button>
				<button type="button" onclick="goModify(${dto.no})">수정하기</button>
				<button type="button" onclick="goDelete(${dto.no})">삭제하기</button>
				<button type="button" onclick="goList()">목록으로</button>
			</td>
		</tr>
	</table>
</form>


<script>

function goList() {
	location.href = '${path}/board_servlet/list.do';
}

function goWrite() {
	location.href = '${path}/board_servlet/write.do';
}

function goView(no) {
	document.viewForm.method = 'post';
	document.viewForm.action = `${path}/board_servlet/view.do?no=\${no}`;
	document.viewForm.submit();
}

function goModify(no) {
	location.href = `${path}/board_servlet/modify.do?no=\${no}`;
}

function goDelete(no) {
	location.href = `${path}/board_servlet/delete.do?no=\${no}`;
}

</script>