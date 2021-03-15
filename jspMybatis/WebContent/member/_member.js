$(document).ready(function() {
	chooseProc("list", "1", "");
});

function chooseAll() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	chooseProc('list', '1', '');
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
	// var url = "${path}/member_servlet/" + proc + ".do";
	var url = $("#span_path").text() + "/member_servlet/" + proc + ".do";
	
	if (proc === "list") {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		};
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "list") {
				$("#result").html(data);
			} else {
				$("#result").html(data);
			}
		}
	});
}