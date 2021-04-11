$(document).ready(function() {
	chooseProc("list", "1", "");
});

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
	var url = $("#span_path").text() + "/survey/" + proc + ".do";
	alert(url);
	if (proc === "list") {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text(),
			"search_date_check": $("#span_search_date_check").text(),
			"search_date_start": $("#span_search_date_start").text(),
			"search_date_end": $("#span_search_date_end").text(),
			"list_gubun": $("#span_list_gubun").text()
		};
	} else if (proc === "write") {
		param = {};
	} else if (proc === "writeProc" || proc === "modifyProc" || proc === "deleteProc") {
		param = {
			"no": $("#span_no").text(),
			"question": $("#question").val(),
			"ans1": $("#ans1").val(),
			"ans2": $("#ans2").val(),
			"ans3": $("#ans3").val(),
			"ans4": $("#ans4").val(),
			"status": $("#status").val(),
			"startYear": $("#startYear").val(),
			"startMonth": $("#startMonth").val(),
			"startDay": $("#startDay").val(),
			"lastYear": $("#lastYear").val(),
			"lastMonth": $("#lastMonth").val(),
			"lastDay": $("#lastDay").val()
		};
	} else if (proc === "modify" || proc === "delete") {
		param = {
			"no": $("#span_no").text()
		};
	} else if (proc === "view") {
		param = {
			"no": $("#span_no").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text(),
			"search_date_check": $("#search_date_check").text(),
			"search_date_start": $("#search_date_start").text(),
			"search_date_end": $("#search_date_end").text(),
			"list_gubun": $("#list_gubun").text()
		};
	} else if (proc === "detailedList") {
		param = {};
	} else if (proc === "viewProc") {
		param = {
			"no": $("#span_no").text(),
			"answer": $("#span_answer").text()
		};
	} else if (proc === "result") {
		param = {
			"no": $("#span_no").text()
		}
	} else if (proc === "saveProc") {
		param = {
			"answer_total": $("#span_answer_total").text()
		}
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "list") {
				$("#result").html(data);
				if ($("#span_search_date_check").text() === "O") {
					$("input[id=search_date_check]:checkbox").prop("checked", true);
				} else {
					$("input[id=search_date_check]:checkbox").prop("checked", false);
				}
			} else if (proc === "view") {
				$("#result").html(data);
			} else if (proc === "write") {
				$("#result").html(data);
			} else if (proc === "writeProc") {
				chooseProc("list", "1", "");
			} else if (proc === "modify") {
				$("#result").html(data);
			} else if (proc === "modifyProc") {
				chooseProc("view", "1", "0");
			} else if (proc === "delete") {
				$("#result").html(data);
			} else if (proc === "deleteProc") {
				chooseProc("list", "1", "");
			} else if (proc === "viewProc") {
				chooseProc("list", "1", "");
			} else if (proc === "result") {
				$("#result").html(data);
			} else if (proc === "saveProc") {
				chooseProc("list", "1", "");
			} else {
				$("#result").html(data);
			}
		}
	});
}