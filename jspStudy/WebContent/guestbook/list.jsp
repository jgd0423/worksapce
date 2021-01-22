<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<h2>방명록 목록</h2>

<form name="searchForm">
	<select name="searchField">
		<option value="">-선택-</option>
		<option value="writer">작성자</option>
		<option value="subject">제목</option>
		<option value="content">내용</option>
		<option value="all">제목+내용</option>
	</select>

	<input type="text" name="searchData">
	<button type="button" onclick="search();">검색하기</button>
	&nbsp;&nbsp;&nbsp;
	<button type="button" onclick="goPage('guestbook_write', '', '')">글쓰기</button>
</form>

${list.size() }개의 레코드가 있습니다.

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
				<td>${dto.name }</td>
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


<script>

function goPage(value1, value2, value3) {
	if (value1 === 'guestbook_write') {
		location.href = '${path}/guestbook_servlet/write.do';
	}
}

</script>