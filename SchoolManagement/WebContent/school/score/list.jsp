<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<form name="listForm">
	<table border="1" align="center" width="95%">
		<tr>
			<td colspan="20" align="center">
				<h2>성적리스트</h2>
			</td>
		</tr>
		<tr>
			<td colspan="20" align="center">
				<select name="search_option" id="search_option">
					<c:choose>
						<c:when test="${search_option == 'studentName' }">
							<option value="">- 선택 -</option>
							<option value="studentName" selected>학생이름</option>
							<option value="examName">시험이름</option>
							<option value="studentName_examName">학생이름+시험이름</option>
						</c:when>
						<c:when test="${search_option == 'examName' }">
							<option value="">- 선택 -</option>
							<option value="studentName">학생이름</option>
							<option value="examName" selected>시험이름</option>
							<option value="studentName_examName">학생이름+시험이름</option>
						</c:when>
						<c:when test="${search_option == 'studentName_examName' }">
							<option value="">- 선택 -</option>
							<option value="studentName">학생이름</option>
							<option value="examName">시험이름</option>
							<option value="studentName_examName" selected>학생이름+시험이름</option>
						</c:when>
						<c:otherwise>
							<option value="" selected>- 선택 -</option>
							<option value="studentName">학생이름</option>
							<option value="examName">시험이름</option>
							<option value="studentName_examName">학생이름+시험이름</option>
						</c:otherwise>
					</c:choose>
				</select>
				
				<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px;" />
				&nbsp;
				<input type="button" value="검색" onclick="search();" />		
			</td>
		</tr>
		<tr>
			<td>번호</td>
			<td>성적리스트 id</td>
			<td>학생이름</td>
			<td>시험이름</td>
			<td>국어</td>
			<td>영어</td>
			<td>수학</td>
			<td>과학</td>
			<td>사회</td>
			<td>총점</td>
			<td>평균</td>
			<td>등록일</td>
		</tr>
		<c:if test="${list.size() == 0 }">
			<tr>
				<td colspan="20" height="200" align="center">
					등록된 정보가 없습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list.size() > 0 }">
			<c:forEach var="dto" items="${list }">
				<tr>
					<td>${tableRowNum }</td>
					<td>${dto.no }</td>
					<td>${dto.studentName }</td>
					<td>${dto.examName }</td>
					<td>${dto.korean }</td>
					<td>${dto.english }</td>
					<td>${dto.math }</td>
					<td>${dto.science }</td>
					<td>${dto.history }</td>
					<td>${dto.totalScore }</td>
					<td>${dto.averageScore }</td>
					<td>${dto.regiDate }</td>
				</tr>
				<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="20" height="50" align="center">
				<a href="#" onclick="choosePage(1)"><<</a>
				<c:choose>
					<c:when test="${pageNum - 1 <= 0 }">
						<a href="#" onclick="choosePage(${pageNum}, '${search_option }', '${search_data }')"><</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${pageNum - 1 }, '${search_option }', '${search_data }')"><</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
					<c:choose>
						<c:when test="${pageNum == i }">
							<b>[${i }]</b>
						</c:when>
						<c:otherwise>
							<a href="#" onclick="choosePage(${i }, '${search_option }', '${search_data }')">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageNum + 1 >= maxPagesCount }">
						<a href="#" onclick="choosePage(${maxPagesCount }, '${search_option }', '${search_data }')">></a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="choosePage(${pageNum + 1 }, '${search_option }', '${search_data }')">></a>
					</c:otherwise>
				</c:choose>
				<a href="#" onclick="choosePage(${maxPagesCount }, '${search_option }', '${search_data }')">>></a>
			</td>
		</tr>
		<tr>
			<td colspan="20" align="right">
				<button type="button" onclick="chooseAll()">전체목록</button>&nbsp;
				<button type="button" onclick="goWrite()">성적등록</button>&nbsp;
			</td>
		</tr>
	</table>
</form>
<br>

<script>

function choosePage(pageNumber) {
	let url = '';
	url += `${path}/score_servlet/list.do?pageNumber=\${pageNumber}`;
	url += `&search_option=\${search_option}`;
	url += `&search_data=\${search_data}`;
	location.href = url;
}

function search() {
	document.listForm.method = 'post';
	document.listForm.action = '${path}/score_servlet/list.do';
	document.listForm.submit();
}

function chooseAll() {
	location.href = '${path}/score_servlet/list.do';
}

function goView(no) {
	location.href = `${path}/score_servlet/view.do?no=\${no}`;
}

function goWrite() {
	location.href = '${path}/score_servlet/write.do';
}

</script>