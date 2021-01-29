<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<input type="text" name="a" style="display: block;" /><br />

list_gubun : <span id="span_list_gubun">${list_gubun }</span><br />
pageNumber : <span id="span_pageNumber">${pageNum }</span><br />
no : <span id="span_no">${no }</span><br />
search_option : <span id="span_search_option">${search_option }</span><br />
search_data : <span id="span_search_data">${search_data }</span><br />
search_date_check : <span id="span_search_date_check">${search_date_check }</span><br />
search_date_start : <span id="span_search_date_start">${search_date_start }</span><br />
search_date_end : <span id="span_search_date_end">${search_date_end }</span><br />

<div id="result"></div>


<c:if test="${menu_gubun == 'survey_index' }">
	<script>
		$(document).ready(() => {
			goList();
		});
	</script>
</c:if>


<script>

function goWrite() {
	let param = {};
	$.ajax({
		type: "post",
		data: param,
		url: "${path}/survey_servlet/write.do",
		success: (data) => {
			$("#result").html(data);
		}
	});
}

function goWriteProc() {
	if (confirm('등록하시겠습니까?')) {
		$.ajax({
			type: "post",
			data: $("#writeForm").serialize(),
			url: "${path}/survey_servlet/writeProc.do",
			success: () => {
				choosePageAndGoList(1);
			}
		});
	}
}

function goList() {
	let param = {
			"list_gubun": $("#span_list_gubun").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text(),
			"search_date_check": $("#span_search_date_check").text(),
			"search_date_start": $("#span_search_date_start").text(),
			"search_date_end": $("#span_search_date_end").text()
	};
	
	$.ajax({
		type: "post",
		data: param,
		url: "${path}/survey_servlet/list.do",
		success: (result) => {
			$("#result").html(result);
			if ($("#span_search_date_check").text() === "O") {
				$("input[id=search_date_check]:checkbox").prop("checked", true);
			} else {
				$("input[id=search_date_check]:checkbox").prop("checked", false);
			}
		}
	});
}

function goDetailedList() {
	let param = {};
	$.ajax({
		type: "post",
		data: param,
		url: "${path}/survey_servlet/detailedList.do",
		success: (data) => {
			$("#result").html(data);
		}
	});
}

function goSaveProc() {
	if (confirm('저장하시겠습니까?')) {
		let param = {
				"answer_total": $("#span_answer_total").text()
		}
		
		$.ajax({
			type: "post",
			data: param,
			url: "${path}/survey_servlet/saveProc.do",
			success: () => {
				choosePageAndGoList(1);
			}
		});
	}
}


function goResult() {
	let param = {
			"no": $("#span_no").text()
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: "${path}/survey_servlet/result.do",
		success: (result) => {
			$("#result").html(result);
		}
	});
}

function chooseListTypeAndGoList(gubun) {
	$("#span_list_gubun").text(gubun);
	$("#span_pageNumber").text(1);
	goList();
}

function choosePageAndGoList(pageNum) {
	$("#span_pageNumber").text(pageNum);
	goList();
}

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