$(document).ready(function() {
	chooseProc("detailedList", "1", "");
});

function choosePage(pageNum) {
	chooseProc('detailedList', pageNum, '');
}

function chooseProc(proc, pageNumber, no) {
	$("#span_proc").text(proc);
	
	if (pageNumber !== "0") {
		$("#span_pageNumber").text(pageNumber);
	}
	
	if (no !== "0") {
		$("#span_no").text(no);
	}
	
	goPage(proc);
}

function goPage(proc) {
	let param = {};
	var url = "/jspMybatis/survey_servlet/" + proc + ".do";
	
	if (proc === "saveProc") {
		param = {
			"answer_total": $("#span_answer_total").text()
		}
	} else if (proc === "detailedList") {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text(),
			"search_date_check": $("#span_search_date_check").text(),
			"search_date_start": $("#span_search_date_start").text(),
			"search_date_end": $("#span_search_date_end").text(),
			"list_gubun": $("#span_list_gubun").text()
		};
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "saveProc") {
				alert('등록되었습니다.');
			} else if (proc === "detailedList") {
				$("#result").html(data);
			}
		}
	});
}