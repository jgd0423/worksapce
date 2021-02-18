<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table align="center" width="95%">
	<tr>
		<td style="padding : 0 0 20 0;">
			이름 : <input type="text" name="comment_writer" id="comment_writer" size="10" value="${cookName }" />
			비밀번호 : <input type="text" name="comment_passwd" id="comment_passwd" size="10" /><br>
			댓글 : <input type="text" name="comment_content" id="comment_content" size="40" />
			<button type="button" id="btnCommentSave">확인</button>
		</td>
	</tr>
</table>
레코드 수 : ${allRowsCount }
<table align="center" width="95%">
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="4" height="200" align="center">등록된 댓글이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${tableRowNum }</td>
				<td>${dto.writer }</td>
				<td>${dto.content }</td>
			</tr>
			<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="7" height="50" align="center">
			<a href="#" onclick="chooseCommentPage(1)"><<</a>
			<c:choose>
				<c:when test="${pageNum - 1 <= 0 }">
					<a href="#" onclick="chooseCommentPage(${pageNum })"><</a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="chooseCommentPage(${pageNum - 1 })"><</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
				<c:choose>
					<c:when test="${commentPageNumber == i }">
						<b>[${i }]</b>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="chooseCommentPage(${i })">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum + 1 >= maxPagesCount }">
					<a href="#" onclick="chooseCommentPage(${maxPagesCount })">></a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="chooseCommentPage(${pageNum + 1 })">></a>
				</c:otherwise>
			</c:choose>
			<a href="#" onclick="chooseCommentPage(${maxPagesCount })">>></a>
		</td>
	</tr>
</table>


<script>

$(document).ready(() => {
	$("#btnCommentSave").click(() => {
		goPage('commentWriteProc', '');
	})
});

function chooseCommentPage(commentPageNumber) {
	$("#span_commentPageNumber").text(commentPageNumber);
	goPage('commentWrite', '');
}

</script>