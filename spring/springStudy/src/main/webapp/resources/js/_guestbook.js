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
	// var url = "${path}/guestbook_servlet/" + proc + ".do";
	var url = $("#span_path").text() + "/guestbook/" + proc + ".do";
	
	if (proc === "list") {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		};
	} else if (proc === "write") {
		param = {};
	} else if (proc === "writeProc" || proc === "modifyProc" || proc === "deleteProc") {
		param = {
			"no": $("#span_no").text(),
			"name": $("#name").val(),
			"email": $("#email").val(),
			"passwd": $("#passwd").val(),
			"content": $("#content").val()
		};
	} else if (proc === "view") {
		param = {
			"no": $("#span_no").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		};
	} else if (proc === "modify" || proc === "delete") {
		param = {
			"no": $("#span_no").text()
		};
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "list") {
				$("#result").html(data);
			} else if (proc === "write") {
				$("#result").html(data);
			} else if (proc === "writeProc") {
				chooseProc("list", "1", "");
			} else if (proc === "view") {
				$("#result").html(data);
			} else if (proc === "modify") {
				$("#result").html(data);
			} else if (proc === "modifyProc") {
				chooseProc("view", "0", $("#span_no").text());
				//$("#result").html(data);
			} else if (proc === "delete") {
				$("#result").html(data);
			} else if (proc === "deleteProc") {
				chooseProc("list", "1", "");
				//$("#result").html(data);
			}
		}
	});
}