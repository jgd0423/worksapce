<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<form name="listForm">
	<table border="1" align="center" width="95%">
		<tr>
			<td colspan="10" align="center">
				<h2>학생리스트</h2>
			</td>
		</tr>
		<tr>
			<td>번호</td>
			<td>id</td>
			<td>이름</td>
			<td>학년</td>
			<td>반</td>
			<td>전화번호</td>
			<td>이메일</td>
			<td>주소</td>
			<td>등록일</td>
		</tr>
		<c:if test="${list.size() == 0 }">
			<tr>
				<td colspan="10" height="200" align="center">
					등록된 학생이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list.size() > 0 }">
			<c:forEach var="dto" items="${list }">
				<tr>
					<td>${tableRowNum }</td>
					<td>${dto.id }</td>
					<td>${dto.name }</td>
					<td>${dto.grade }</td>
					<td>${dto.classes }</td>
					<td>${dto.phone }</td>
					<td>${dto.email }</td>
					<td>${dto.address }</td>
					<td>${dto.regiDate }</td>
				</tr>
				<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="10" height="50" align="center">
				<a href="#" onclick="choosePage(1)"><<</a>
				<c:choose>
					<c:when test="${pageNum - 1 <= 0 }">
						<a href="#" onclick="choosePage(${pageNum})"><</a>
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
		<tr>
			<td colspan="10" align="right">
				<button type="button" onclick="chooseAll()">전체목록</button>&nbsp;
				<button type="button" onclick="goWrite()">학생등록</button>&nbsp;
			</td>
		</tr>
	</table>
</form>
<br>

<script>

function choosePage(pageNumber) {
	let url = '';
	url += `${path}/student_servlet/list.do?pageNumber=\${pageNumber}`;
	location.href = url;
}

function chooseAll() {
	location.href = '${path}/student_servlet/list.do';
}

function goView(no) {
	location.href = `${path}/student_servlet/view.do?no=\${no}`;
}

function goWrite() {
	location.href = '${path}/student_servlet/write.do';
}

</script>