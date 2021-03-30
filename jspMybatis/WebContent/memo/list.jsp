<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

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
				<td id="n_${dto.no }">${dto.writer }</td>
				<td id="s_${dto.no }">${dto.content }</td>
				<td>
					${dto.regiDate }
					<button type="button" onclick="modifyMemo('${dto.no}')" >수정</button>
					<button type="button" onclick="chooseProc('deleteProc', '0', '${dto.no}')" >삭제</button>
				</td>
			</tr>
			<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="7" height="50" align="center">
			<a href="#" onclick="choosePage(1)"><<</a>
			<c:choose>
				<c:when test="${pageNum - 1 <= 0 }">
					<a href="#" onclick="choosePage(${pageNum })"><</a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="choosePage(${pageNum - 1 })"><</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
				<c:choose>
					<c:when test="${pageNum == i }">
						<b>[${i }]</b>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${i })">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum + 1 >= maxPagesCount }">
					<a href="#" onclick="choosePage(${maxPagesCount })">></a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="choosePage(${pageNum + 1 })">></a>
				</c:otherwise>
			</c:choose>
			<a href="#" onclick="choosePage(${maxPagesCount })">>></a>
		</td>
	</tr>
</table>

<script>

function modifyMemo(no) {
	$("#span_no").text(no);
	$("#writer").val($("#n_" + no).text());
	$("#content").val($("#s_" + no).text());
	$("#btnWrite").text("수정");
}

function choosePage(pageNum) {
	chooseProc('list', pageNum, '');
}

</script>


