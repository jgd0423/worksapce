<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="7" align="center">
			<h2>회원목록</h2>
		</td>
	</tr>
	<tr>
		<td>순번</td>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>성별</td>
		<td>출생년도</td>
		<td>가입일시</td>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="7" height="200" align="center">등록된 회원이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${tableRowNum = tableRowNum - 1 }</td>
				<td><a href="#" onclick="goPage('member_view', '', '${dto.no }')">${dto.id }</a></td>
				<td>${dto.passwd }</td>
				<td>${dto.name }</td>
				<td>${dto.gender }</td>
				<td>${dto.bornYear }</td>
				<td>${dto.regiDate }</td>
			</tr>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="7" height="50" align="center">
			<a href="#" onclick="goPage('member_list', '1', '')"><<</a>
			<c:choose>
				<c:when test="${pageNum - 1 <= 0 }">
					<a href="#" onclick="goPage('member_list', '${pageNum }', '')"><</a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="goPage('member_list', '${pageNum - 1 }', '')"><</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
				<c:choose>
					<c:when test="${pageNum == i }">
						<b>[${i }]</b>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="goPage('member_list', '${i }', '')">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum + 1 >= maxPagesCount }">
					<a href="#" onclick="goPage('member_list', '${maxPagesCount }', '')">></a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="goPage('member_list', '${pageNum + 1 }', '')">></a>
				</c:otherwise>
			</c:choose>
			<a href="#" onclick="goPage('member_list', '${maxPagesCount }', '')">>></a>
		</td>
	</tr>
</table>
<br>
<button type="button" onclick="goPage('member_chuga', '', '')">가입하기</button>

<script>

function goPage(value1, value2, value3) {
	if (value1 === 'member_list') {
		location.href = '${path}/member_servlet/list.do?page=' + value2;
	} else if (value1 === 'member_chuga') {
		location.href = '${path}/member_servlet/chuga.do';
	} else if (value1 === 'member_view') {
		location.href = '${path}/member_servlet/view.do?page=' + value2 + '&no=' + value3;
	}
}

</script>
