<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="writeForm" id="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>게시글쓰기</h2></td>
		</tr>
		<tr>
			<td style="align: center;">이름</td>
			<td><input type="text" name="writer" id="writer" /></td>
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
				></textarea>
			</td>
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
	location.href = '${path}/board_servlet/list.do';
}

function goWrite() {
	document.writeForm.method = 'post';
	document.writeForm.action = '${path}/board_servlet/writeProc.do?no=${dto.no}';
	document.writeForm.submit();
}

</script>