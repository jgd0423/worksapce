<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<div style="display: none">
	span_list_size : <span id="span_list_size" style="display: ;">${allRowsCount }</span><br>
	<%-- span_answer_total : <span id="span_answer_total" style="display: ;"></span><br>--%>
</div>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="5" align="center">
			<h2>문제은행</h2>
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
			<br>
			<input type="checkbox" id="search_date_check" value="O" onclick="checkboxChk()" /><span style="color: blue; font-size: 9px;">(날짜 검색시 체크)</span>
			&nbsp;
			<input type="button" value="검색" onclick="search();" />		
		</td>
	</tr>
</table>
<table>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="5" height="200" align="center">
				등록된 설문이 없습니다.
			</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<a named="a_${tableRowNum }"></a>
			<br>
			<br>
			<div style="display: none">
				q_${tableRowNum } : <span id="q_${tableRowNum}">${dto.no }</span><br>
				span_answer_${tableRowNum } : <span id="span_answer_${tableRowNum }" style="display: ;"></span><br>
			</div>
			<table border="1" width="80%">
				<tr>
					<td>Q) ${dto.question }</td>
				</tr>
				<tr>
					<td>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '1')"><font style="font-family:'MS Gothic'"><span id="mun1_${tableRowNum }">①</span></font></a>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '1')">${dto.ans1 }</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '2')"><font style="font-family:'MS Gothic'"><span id="mun2_${tableRowNum }">②</span></font></a>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '2')">${dto.ans2 }</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '3')"><font style="font-family:'MS Gothic'"><span id="mun3_${tableRowNum }">③</span></font></a>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '3')">${dto.ans3 }</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '4')"><font style="font-family:'MS Gothic'"><span id="mun4_${tableRowNum }">④</span></font></a>
						<a href="#a_${tableRowNum }" onclick="checkDetailedListAnswer('${tableRowNum }', '4')">${dto.ans4 }</a>
					</td>
				</tr>
			</table>
			<br>
			<c:set var="tableRowNum" value="${tableRowNum = tableRowNum - 1 }"/>
		</c:forEach>
	</c:if>
</table>
<table>
	<tr>
		<td colspan="5" height="50" align="center">
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
</table>
<table>
	<tr>
		<td colspan="5" align="right">
			<button type="button" onclick="chooseProc('saveProc', '1', '')">답안저장하기</button>&nbsp;
		</td>
	</tr>
</table>

<script>

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	$("#span_search_date_start").text($("#search_date_start").val());
	$("#span_search_date_end").text($("#search_date_end").val());
	chooseProc('detailedList', '1', '');
}

function checkboxChk() {
	if ($("input:checkbox[id=search_date_check]").is(":checked") === true) {
		$("#span_search_date_check").text($("#search_date_check").val());
		$("#span_search_date_start").text($("#search_date_start").val());
		$("#span_search_date_end").text($("#search_date_end").val());
	} else {
		$("#span_search_date_check").text("");
		$("#span_search_date_start").text("");
		$("#span_search_date_end").text("");
		$("#search_date_start").val("");
		$("#search_date_end").val("");
	}
}

function choosePage(pageNum) {
	chooseProc('detailedList', pageNum, '');
}

function checkDetailedListAnswer(tableRowNum, answer) {
	$("#span_answer_" + tableRowNum).text(answer);
	
	if (answer === '1') {
		$("#mun1_" + tableRowNum).text('❶');
		$("#mun2_" + tableRowNum).text('②');
		$("#mun3_" + tableRowNum).text('③');
		$("#mun4_" + tableRowNum).text('④');
	} else if (answer === '2') {
		$("#mun1_" + tableRowNum).text('①');
		$("#mun2_" + tableRowNum).text('❷');
		$("#mun3_" + tableRowNum).text('③');
		$("#mun4_" + tableRowNum).text('④');
	} else if (answer === '3') {
		$("#mun1_" + tableRowNum).text('①');
		$("#mun2_" + tableRowNum).text('②');
		$("#mun3_" + tableRowNum).text('❸');
		$("#mun4_" + tableRowNum).text('④');
	} else if (answer === '4') {
		$("#mun1_" + tableRowNum).text('①');
		$("#mun2_" + tableRowNum).text('②');
		$("#mun3_" + tableRowNum).text('③');
		$("#mun4_" + tableRowNum).text('❹');
	}
	
	let counter = parseInt($("#span_list_size").text());
	let msg = "";
	for (i = counter; i > 0; i--) {
		q_no = $("#q_" + i).text();
		answer = $("#span_answer_" + i).text();
		
		if (answer.length > 0) {
			if (msg === '') {
				msg = q_no + ":" + answer;
			} else {
				msg = msg + "|" + q_no + ":" + answer;
			}
		}
	}
	
	$("#span_answer_total").text(msg);
}

</script>