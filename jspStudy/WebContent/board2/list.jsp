<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<form name="listForm">
	<table border="1" align="center" width="95%">
		<tr>
			<td colspan="10" align="center">
				<h2>${tableName}</h2>
			</td>
		</tr>
		<tr>
			<td colspan="10" align="center">
				<select name="search_option" id="search_option">
					<c:choose>
						<c:when test="${search_option == 'writer' }">
							<option value="">- 선택 -</option>
							<option value="writer" selected>작성자</option>
							<option value="subject">제목</option>
							<option value="content">내용</option>
							<option value="writer_subject_content">작성자+제목+내용</option>
						</c:when>
						<c:when test="${search_option == 'subject' }">
							<option value="">- 선택 -</option>
							<option value="writer">작성자</option>
							<option value="subject" selected>제목</option>
							<option value="content">내용</option>
							<option value="writer_subject_content">작성자+제목+내용</option>
						</c:when>
						<c:when test="${search_option == 'content' }">
							<option value="">- 선택 -</option>
							<option value="writer">작성자</option>
							<option value="subject">제목</option>
							<option value="content" selected>내용</option>
							<option value="writer_subject_content">작성자+제목+내용</option>
						</c:when>
						<c:when test="${search_option == 'writer_subject_content' }">
							<option value="">- 선택 -</option>
							<option value="writer">작성자</option>
							<option value="subject">제목</option>
							<option value="content">내용</option>
							<option value="writer_subject_content" selected>작성자+제목+내용</option>
						</c:when>
						<c:otherwise>
							<option value="" selected>- 선택 -</option>
							<option value="writer">작성자</option>
							<option value="subject">제목</option>
							<option value="content">내용</option>
							<option value="writer_subject_content">작성자+제목+내용</option>
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
					<td>
						<c:if test="${dto.noticeNo > 0 }">공지</c:if>
						<c:if test="${dto.noticeNo == 0 }">${tableRowNum }</c:if>
					</td>
					<td>
						<c:forEach var="i" begin="2" end="${dto.stepNo }" step="1">&nbsp;&nbsp;</c:forEach><!-- varStatus="status" -->
						<c:if test="${dto.stepNo > 1 }">
							<c:set var="reVar" value="[Re]:" />
						</c:if>
						<c:if test="${dto.stepNo <= 1 }">
							<c:set var="reVar" value="" />
						</c:if>
						<a href="#" onclick="goView('${dto.no }')">${reVar} ${dto.subject }</a>
					</td>
					<td>${dto.writer }</td>
					<td>${dto.regiDate }</td>
					<td>${dto.hit }</td>
					<td>${dto.ip }</td>
					<td>${dto.memberNo }</td>
					<td>${dto.noticeNo }</td>
					<td>${dto.secretGubun }</td>
					<td>${dto.child_counter }</td>
				</tr>
				<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="10" height="50" align="center">
				<a href="#" onclick="choosePage(1, '${tbl}', '${search_option }', '${search_data }')"><<</a>
				<c:choose>
					<c:when test="${pageNum - 1 <= 0 }">
						<a href="#" onclick="choosePage(${pageNum}, '${tbl}', '${search_option }', '${search_data }')"><</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${pageNum - 1 }, '${tbl}', '${search_option }', '${search_data }')"><</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
					<c:choose>
						<c:when test="${pageNum == i }">
							<b>[${i }]</b>
						</c:when>
						<c:otherwise>
							<a href="#" onclick="choosePage(${i }, '${tbl}', '${search_option }', '${search_data }')">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageNum + 1 >= maxPagesCount }">
						<a href="#" onclick="choosePage(${maxPagesCount }, '${tbl}', '${search_option }', '${search_data }')">></a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${pageNum + 1 }, '${tbl}', '${search_option }', '${search_data }')">></a>
					</c:otherwise>
				</c:choose>
				<a href="#" onclick="choosePage(${maxPagesCount }, '${tbl}', '${search_option }', '${search_data }')">>></a>
			</td>
		</tr>
		<tr>
			<td colspan="10" align="right">
				<button type="button" onclick="chooseAll()">전체목록</button>&nbsp;
				<button type="button" onclick="goWrite()">글쓰기</button>&nbsp;
			</td>
		</tr>
	</table>
</form>
<br>

<script>

function choosePage(pageNumber, tbl, search_option, search_data) {
	let url = '';
	url += `${path}/board2_servlet/list.do?pageNumber=\${pageNumber}`;
	url += `&tbl=\${tbl}`;
	url += `&search_option=\${search_option}`;
	url += `&search_data=\${search_data}`;
	location.href = url;
}

function search() {
	document.listForm.method = 'post';
	document.listForm.action = '${path}/board2_servlet/list.do';
	document.listForm.submit();
}

function chooseAll() {
	location.href = '${path}/board2_servlet/list.do';
}

function goView(no) {
	location.href = `${path}/board2_servlet/list.do?no=\${no}`;
}

function goWrite() {
	location.href = '${path}/board2_servlet/write.do';
}

</script>