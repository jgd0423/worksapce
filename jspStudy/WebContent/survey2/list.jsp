<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

<form name="listForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="5" align="center">
				<h2>설문조사 목록</h2>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="center">
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
				<input type="date" name="search_date_start" id="search_date_start" value="${search_date_start }" />
				~
				<input type="date" name="search_date_end" id="search_date_end" value="${search_date_end }" />
				<br>
				<input type="checkbox" name="search_date_check" id="search_date_check" value="O" /><span style="color: blue; font-size: 9px;">(날짜 검색시 체크)</span>
				&nbsp;
				<input type="button" value="검색" onclick="searchSurvey()" />		
			</td>
		</tr>
		<tr>
			<td>순번</td>
			<td>질문</td>
			<td>기간</td>
			<td>참여수</td>
			<td>상태</td>
		</tr>
		<c:if test="${list.size() == 0 }">
			<tr>
				<td colspan="5" height="200" align="center">
					등록된 설문이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:if test="${list.size() > 0 }">
			<c:forEach var="dto" items="${list }">
				<tr>
					<td>${tableRowNum }</td>
					<td><a href="#" onclick="goView('${dto.no }')">${dto.question }</a></td>
					<td>${dto.start_date }<br>${dto.last_date }</td>
					<td>${dto.survey_counter }</td>
					<td>${dto.status }</td>
				</tr>
				<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
			</c:forEach>
		</c:if>
		<tr>
			<td colspan="5" height="50" align="center">
				<a href="#" onclick="goPage('1', '${searchingType}', '${search_option }', '${search_data }', '${search_date_start }', '${search_date_end }', '${search_date_check }')"><<</a>
				<c:choose>
					<c:when test="${pageNum - 1 <= 0 }">
						<a href="#" onclick="goPage('${pageNum }', '${searchingType}', '${search_option }', '${search_data }', '${search_date_start }', '${search_date_end }', '${search_date_check }')"><</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="goPage('${pageNum - 1 }', '${searchingType}', '${search_option }', '${search_data }', '${search_date_start }', '${search_date_end }', '${search_date_check }')"><</a>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pagingStartNum }" end="${pagingEndNum }" step="1" >
					<c:choose>
						<c:when test="${pageNum == i }">
							<b>[${i }]</b>
						</c:when>
						<c:otherwise>
							<a href="#" onclick="goPage('${i }', '${searchingType}', '${search_option }', '${search_data }', '${search_date_start }', '${search_date_end }', '${search_date_check }')">${i }</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageNum + 1 >= maxPagesCount }">
						<a href="#" onclick="goPage('${maxPagesCount }', '${searchingType}', '${search_option }', '${search_data }', '${search_date_start }', '${search_date_end }', '${search_date_check }')">></a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="goPage('${pageNum + 1 }', '${searchingType}', '${search_option }', '${search_data }', '${search_date_start }', '${search_date_end }', '${search_date_check }')">></a>
					</c:otherwise>
				</c:choose>
				<a href="#" onclick="goPage('${maxPagesCount }', '${searchingType}', '${search_option }', '${search_data }', '${search_date_start }', '${search_date_end }', '${search_date_check }')">>></a>
			</td>
		</tr>
		<tr>
			<td colspan="5" align="right">
				<button type="button" onclick="chooseListType('all')">전체 질문목록</button>&nbsp;
				<button type="button" onclick="chooseListType('ing')">진행중인 설문목록</button>&nbsp;
				<button type="button" onclick="chooseListType('end')">종료된 설문목록</button>&nbsp;
				<button type="button" onclick="goWrite()">등록하기</button>&nbsp;
				<button type="button" onclick="goDetailedList()">문제풀이</button>&nbsp;
			</td>
		</tr>
	</table>
</form>
<br>

<script>

if ('${search_date_check}' === 'O') {
	$("input[id=search_date_check]:checkbox").prop("checked", true);
} else {
	$("input[id=search_date_check]:checkbox").prop("checked", false);
}	

function goPage(page, searchingType, search_option, search_data, search_date_start, search_date_end, search_date_check) {
	let url = '';
	url += '${path}/survey2_servlet/list.do?page=' + page;
	url += '&searchingType=' + searchingType;
	url += '&search_option=' + search_option;
	url += '&search_data=' + search_data;
	url += '&search_date_start=' + search_date_start;
	url += '&search_date_end=' + search_date_end;
	url += '&search_date_check=' + search_date_check;
	location.href = url;
}

function goWrite() {
	location.href = '${path}/survey2_servlet/write.do';
}

function goView(no) {
	location.href = '${path}/survey2_servlet/view.do?&no=' + no;
}

function goDetailedList() {
	location.href = '${path}/survey2_servlet/detailedList.do';
}

function chooseListType(searchingType) {
	document.listForm.method = 'post';
	document.listForm.action = '${path}/survey2_servlet/list.do?searchingType=' + searchingType;
	document.listForm.submit();
}

function searchSurvey() {
	document.listForm.method = 'post';
	document.listForm.action = '${path}/survey2_servlet/list.do';
	document.listForm.submit();
}

</script>
