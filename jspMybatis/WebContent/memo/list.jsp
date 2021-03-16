<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="4" align="center">
			<h2>메모목록</h2>
		</td>
	</tr>
	<tr>
		<td>일련번호</td>
		<td>이름</td>
		<td>메모</td>
		<td>날짜</td>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="4" height="200" align="center">등록된 메모가 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${tableRowNum }</td>
				<td>${dto.writer }</td>
				<td>${dto.content }</td>
				<td>${dto.regiDate }<button type="button" onclick="deleteInfo('${dto.no}', '${pageNum }')" >삭제</button></td>
			</tr>
			<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="7" height="50" align="center">
			<a href="#" onclick="goPage('memo_list', '1', '')"><<</a>
			<c:choose>
				<c:when test="${pageNum - 1 <= 0 }">
					<a href="#" onclick="goPage('memo_list', '${pageNum }', '')"><</a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="goPage('memo_list', '${pageNum - 1 }', '')"><</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
				<c:choose>
					<c:when test="${pageNum == i }">
						<b>[${i }]</b>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="goPage('memo_list', '${i }', '')">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum + 1 >= maxPagesCount }">
					<a href="#" onclick="goPage('memo_list', '${maxPagesCount }', '')">></a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="goPage('memo_list', '${pageNum + 1 }', '')">></a>
				</c:otherwise>
			</c:choose>
			<a href="#" onclick="goPage('memo_list', '${maxPagesCount }', '')">>></a>
		</td>
	</tr>
</table>

<script>

// write.jsp에 list()함수가 있으므로 중복해서 적을 필요 없음

function deleteInfo(no, pageNum) {
	if (${list.size()} === 1) {
		pageNum = pageNum - 1;
	}
	let param = "no=" + no;
	$.ajax({
		type: "post",
		data: param,
		url: "${path}/memo_servlet/deleteInfo.do",
		success: () => {
			list(pageNum);
		}
	});
}

function goPage(value1, value2, value3) {
	if (value1 === 'memo_list') {
		let param = "page=" + value2;
		$.ajax({
			type: "post",
			data: param,
			url: "${path}/memo_servlet/list.do",
			success: () => {
				list(value2);
			}
		});
	} 
}

</script>

