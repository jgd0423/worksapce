<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="10" align="center">
			<h2>게시글 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="10" align="center">
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'question' }">
						<option value="">- 선택 -</option>
						<option value="question" selected>질문내용</option>
					</c:when>
					<c:otherwise>
						<option value="" selected>- 선택 -</option>
						<option value="question">질문내용</option>
					</c:otherwise>
				</c:choose>
			</select>
			
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px;" />
			&nbsp;
			<input type="button" value="검색" onclick="search();" />		
		</td>
	</tr>
	<tr>
		<td>순번</td>
		<td>제목</td>
		<td>작성자</td>
		<td>등록일</td>
		<td>조회수</td>
		<td>ip</td>
		<td>회원번호</td>
		<td>공지</td>
		<td>비밀글</td>
		<td>자식글여부</td>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="10" height="200" align="center">
				등록된 게시글이 없습니다.
			</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${tableRowNum }</td>
				<td><a href="#" onclick="goView('${dto.no }')">${dto.subject }</a></td>
				<td>${dto.writer }</td>
				<td>${dto.regiDate }</td>
				<td>${dto.hit }</td>
				<td>${dto.ip }</td>
				<td>${dto.memberNo }</td>
				<td>${dto.noticeNo }</td>
				<td>${dto.secretGubun }</td>
				<td>${dto.parentNo }</td>
			</tr>
			<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="10" height="50" align="center">
			<a href="#" onclick="choosePageAndGoList(1)"><<</a>
			<c:choose>
				<c:when test="${pageNum - 1 <= 0 }">
					<a href="#" onclick="choosePageAndGoList(${pageNum})"><</a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="choosePageAndGoList(${pageNum - 1 })"><</a>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
				<c:choose>
					<c:when test="${pageNum == i }">
						<b>[${i }]</b>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePageAndGoList(${i })">${i }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageNum + 1 >= maxPagesCount }">
					<a href="#" onclick="choosePageAndGoList(${maxPagesCount })">></a>
				</c:when>
				<c:otherwise>
					<a href="#" onclick="choosePageAndGoList(${pageNum + 1 })">></a>
				</c:otherwise>
			</c:choose>
			<a href="#" onclick="choosePageAndGoList(${maxPagesCount })">>></a>
		</td>
	</tr>
	<tr>
		<td colspan="10" align="right">
			<button type="button" onclick="goPage('list', '')">전체목록</button>&nbsp;
			<button type="button" onclick="goPage('write', '')">글쓰기</button>&nbsp;
		</td>
	</tr>
</table>
<br>