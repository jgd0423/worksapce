<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

${allRowsCount }개의 레코드가 있습니다.<br>

span_list_size : <span id="span_list_size" style="display: ;">${tableRowNum }</span><br>
<span id="span_answer_total" style="display: ;"></span>

<form name="detailedListForm">
	input_answer_total : <input type="text" id="answer_total" name="answer_total" />
	<table>
		<tr>
			<td>
				<table border="1">
					<tr>
						<td colspan="5" align="center">
							<h2>상세 설문조사 목록</h2>
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
							<input type="checkbox" name="search_date_check" id="search_date_check" value="O" /><span style="color: blue; font-size: 9px;">(날짜 검색시 체크)</span>
							&nbsp;
							<input type="button" value="검색" onclick="searchSurvey()" />		
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border="1" width="100%">
					<c:if test="${list.size() == 0 }">
						<tr>
							<td colspan="5" height="200" align="center">
								등록된 설문이 없습니다.
							</td>
						</tr>
					</c:if>
					<c:if test="${list.size() > 0 }">
						<tr>
							<td>
								<c:forEach var="dto" items="${list }">
									<a named="a_${tableRowNum }"></a>
									q_${tableRowNum } : <span id="q_${tableRowNum}">${dto.no }</span><br>
									span_answer_${tableRowNum } : <span id="span_answer_${tableRowNum }" style="display: ;"></span><br>
									<table border="1" width="100%">
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
							</td>
						</tr>
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
							<button type="button" onclick="goDetailedListProc()">답안저장하기</button>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>

<script>

function goDetailedListProc() {
	document.detailedListForm.method = 'post';
	document.detailedListForm.action = '${path}/survey2_servlet/detailedListProc.do';
	document.detailedListForm.submit();
}

function goPage(page, searchingType, search_option, search_data, search_date_start, search_date_end, search_date_check) {
	let url = '';
	url += '${path}/survey2_servlet/detailedList.do?page=' + page;
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

function chooseListType(searchingType) {
	document.detailedListForm.method = 'post';
	document.detailedListForm.action = '${path}/survey2_servlet/detailedList.do?searchingType=' + searchingType;
	document.detailedListForm.submit();
}

function searchSurvey() {
	document.detailedListForm.method = 'post';
	document.detailedListForm.action = '${path}/survey2_servlet/detailedList.do';
	document.detailedListForm.submit();
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
	
	$("#answer_total").val(msg);
}

</script>
