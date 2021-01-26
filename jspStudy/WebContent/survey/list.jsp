<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.

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
			<input type="date" id="search_date_start" value="${search_date_start }" />
			~
			<input type="date" id="search_date_end" value="${search_date_end }" />
			<input type="checkbox" id="search_date_check" value="O" /><span style="color: blue; font-size: 9px;">(날짜 검색시 체크)</span>
			&nbsp;
			<input type="button" value="검색" onclick="search();" />		
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
				<td>${tableRowNum = tableRowNum - 1 }</td>
				<td><a href="#" onclick="chooseView('${dto.no }')">${dto.question }</a></td>
				<td>${dto.start_date }<br>${dto.last_date }</td>
				<td>${dto.survey_counter }</td>
				<td>${dto.status }</td>
			</tr>
		</c:forEach>
	</c:if>
	<tr>
		<td colspan="5" height="50" align="center">
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
		<td colspan="5" align="right">
			<button type="button" onclick="chooseListTypeAndGoList('all')">전체 질문목록</button>&nbsp;
			<button type="button" onclick="chooseListTypeAndGoList('ing')">진행중인 설문목록</button>&nbsp;
			<button type="button" onclick="chooseListTypeAndGoList('end')">종료된 설문목록</button>&nbsp;
			<button type="button" onclick="goWrite()">등록하기</button>&nbsp;
		</td>
	</tr>
</table>
<br>

<script>
function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	$("#span_search_date_start").text($("#search_date_start").val());
	$("#span_search_date_end").text($("#search_date_end").val());
	
	if ($("#search_date_check").is(":checked") === true) {
		$("#span_search_date_check").text($("#search_date_check").val());
	} else {
		$("#span_search_date_check").text("");
	}
	
	choosePageAndGoList(1);
}
</script>
