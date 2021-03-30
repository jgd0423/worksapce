<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<h2>방명록 목록</h2>

<form name="searchForm">
	<select name="search_option" id="search_option">
		<option value="">-선택-</option>
		<option value="name">작성자</option>
		<option value="content">내용</option>
		<option value="name_content">작성자+내용</option>
	</select>

	<input type="text" name="search_data" id="search_data" >
	<button type="button" onclick="search();">검색하기</button>
	&nbsp;&nbsp;&nbsp;
	<button type="button" onclick="chooseProc('write', '1', '')">글쓰기</button>
	&nbsp;&nbsp;&nbsp;
	<button type="button" onclick="chooseAll()">전체목록</button>
</form>

<c:if test="${list.size() == 0 }">
	<tr>
		<td colspan="7" height="200" align="center">등록된 방명록이 없습니다.</td>
	</tr>
</c:if>
<c:if test="${list.size() > 0 }">
	<c:forEach var="dto" items="${list }">
		<table border="1" width="700px">
			<tr>
				<td>이름</td>
				<td><a href="#" onclick="chooseProc('view', '1', '${dto.no }')">${dto.name }</a></td>
				<td>날짜</td>
				<td>${dto.regiDate }</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td colspan="3">${dto.email }</td>
			</tr>
			<tr>
				<td colspan="4">${dto.content }</td>
			</tr>
		</table>
	</c:forEach>
</c:if>

<table>
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

function chooseAll() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	chooseProc('list', '1', '');
}

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	chooseProc('list', '1', '');
}

function choosePage(pageNum) {
	chooseProc('list', pageNum, '');
}

</script>