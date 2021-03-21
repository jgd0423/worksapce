<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>


span_answer: <span id="span_answer"></span>

<table border="1" align="center" width="80%">
	<tr>
		<td align="center">
			<h2>설문조사 상세보기</h2>
		</td>
	</tr>
	<tr>
		<td>Q) ${dto.question }</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('1')"><font style="font-family:'MS Gothic'"><span id="mun1">①</span></font></a>
			<a href="#" onclick="check_answer('1')">${dto.ans1 }</a>
		</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('2')"><font style="font-family:'MS Gothic'"><span id="mun2">②</span></font></a>
			<a href="#" onclick="check_answer('2')">${dto.ans2 }</a>
		</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('3')"><font style="font-family:'MS Gothic'"><span id="mun3">③</span></font></a>
			<a href="#" onclick="check_answer('3')">${dto.ans3 }</a>
		</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('4')"><font style="font-family:'MS Gothic'"><span id="mun4">④</span></font></a>
			<a href="#" onclick="check_answer('4')">${dto.ans4 }</a>
		</td>
	</tr>
	<tr>
		<td>상태: ${dto.status == '1' ? '진행중' : '종료'}</td>
	</tr>
	<tr>
		<td>기간: ${dto.start_date } ~ ${dto.last_date }</td>
	</tr>
	<tr>
		<td align="right">
			<button type="button" onclick="chooseProc('modify', '1', '0')">수정하기</button>&nbsp;
			<button type="button" onclick="chooseProc('delete', '1', '0')">삭제하기</button>&nbsp;
			<button type="button" onclick="chooseProc('viewProc', '1', '0')">설문조사 저장하기</button>&nbsp;
			<button type="button" onclick="chooseProc('list', '1', '')">목록으로</button>&nbsp;
			<button type="button" onclick="chooseProc('result', '1', '0')">설문조사 결과보기</button>&nbsp;
		</td>
	</tr>
</table>

<script>

function check_answer(answer) {
	$("#span_answer").text(answer);
	if (answer === '1') {
		$("#mun1").text('❶');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('④');
	} else if (answer === '2') {
		$("#mun1").text('①');
		$("#mun2").text('❷');
		$("#mun3").text('③');
		$("#mun4").text('④');
	} else if (answer === '3') {
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('❸');
		$("#mun4").text('④');
	} else if (answer === '4') {
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('❹');
	}
}

</script>
