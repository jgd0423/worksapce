<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="deleteForm">
	<input type="hidden" name="no" value="${dto.no }" />
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2" align="center">
				<h2>삭제하기</h2>
			</td>
		</tr>
		<tr>
			<td>번호</td>
			<td>${dto.no }</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>ID : ${dto.id } / 세션ID : ${sessionScope.cookId }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="passwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${dto.gender }</td>
		</tr>
		<tr>
			<td>출생년도</td>
			<td>${dto.bornYear }</td>
		</tr>
		<tr>
			<td>가입일시</td>
			<td>${dto.regiDate }</td>
		</tr>
		<tr>
		<td>주소</td>
		<td>
			<table border="1">
				<tr>
					<td>우편번호</td>
					<td>${dto.postcode }</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${dto.address }</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>${dto.detailAddress }</td>
				</tr>
				<tr>
					<td>참고항목</td>
					<td>${dto.extraAddress }</td>
				</tr>
			</table>
		</td>
	</tr>
		<tr>
			<td colspan="2" height="50" align="center">
				<button type="button" onclick="modifyInfo('${dto.no }')">삭제하기</button>
			</td>
		</tr>
	</table>
</form>

<script>

function modifyInfo(no) {
	if (confirm("삭제하시겠습니까?")) {
		document.deleteForm.method = 'post';
		document.deleteForm.action = '${path}/member_servlet/deleteProc.do';
		document.deleteForm.submit();
	}
}

</script>