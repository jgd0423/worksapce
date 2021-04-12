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
	var url = $("#span_path").text() + "/board/" + proc + ".do";
	
	if (proc === "list") {
		param = {
			"tbl": $("#span_tbl").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		};
	} else if (proc === "write") {
		param = {};
	} else if (proc === "writeProc" || proc === "modifyProc" || proc === "deleteProc") {
		param = {
			"no": $("#span_no").text(),
			"tbl": $("#span_tbl").text(),
			"writer": $("#writer").val(),
			"email": $("#email").val(),
			"passwd": $("#passwd").val(),
			"subject": $("#subject").val(),
			"content": $("#content").val(),
			"noticeGubun": $("#noticeGubun").val(),
			"secretGubun": $("#secretGubun").val()
		};
	} else if (proc === 'reply' || proc === "modify" || proc === "delete") {
		param = {
			"no": $("#span_no").text()
		};
	} else if (proc === "view") {
		param = {
			"no": $("#span_no").text(),
			"tbl": $("#span_tbl").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text(),
			"view_passwd": $("#view_passwd").val()
		};
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "list") {
				if ($("#span_isUsingTable").text() === 'F') {
					alert('잘못된 접근입니다.');
					history.back();
				} else {
					$("#result").html(data);
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
				if (data === 'false') {
					alert('비밀번호가 틀렸습니다.');
				} else {
					goPage('view', '1', $("#span_no").text());					
				}
			} else if (proc === "delete") {
				$("#result").html(data);
			} else if (proc === "deleteProc") {
				if (data === 'false') {
					alert('비밀번호가 틀렸습니다.');
				} else {
					chooseProc("list", "1", "");
				}
			} else {
				$("#result").html(data);
			}
		}
	});
}