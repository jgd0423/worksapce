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
	var param = {};
	var url = "/jspMybatis/survey_servlet/" + proc + ".do";
	
	if (proc === "saveProc") {
		param = {
			"answer_total": $("#span_answer_total").text()
		}
	} else if (proc === "detailedList") {
		param = {
			"pageNumber": $("#span_pageNumber").text()
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