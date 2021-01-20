<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2" align="center">
			<h2>상세보기</h2>
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
		<td>${dto.passwd }</td>
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
			<button type="button" onclick="goPage('member_list', '', '')">목록으로</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="goPage('member_modify', '', ${dto.no })">수정하기</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="goPage('member_delete', '', ${dto.no })">삭제하기</button>
		</td>
	</tr>
</table>

<script>

function goPage(value1, value2, value3) {
	if (value1 === 'member_list') {
		location.href = '${path}/member_servlet/list.do';
	} else if (value1 === 'member_modify') {
		location.href = '${path}/member_servlet/modify.do?pageNumber=' + value2 + '&no=' + value3;
	} else if (value1 === 'member_delete') {
		location.href = '${path}/member_servlet/delete.do?pageNumber=' + value2 + '&no=' + value3;
	}
}

</script>