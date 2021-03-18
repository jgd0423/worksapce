$(document).ready(() => {
	chooseProc("list", "1", "");
	$("#btnWrite").click(() => {
		chooseProc('writeProc', '1', '0');
	});
});

console.log("aaa");

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
	var url = $("#span_path").text() + "/memo_servlet/" + proc + ".do";
	
	if (proc === "list") {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
		};
	} else if (proc === "writeProc") {
		param = {
			"no": $("#span_no").text(),
			"writer": $("#writer").val(),
			"content": $("#content").val()
		};
	} else if (proc === "deleteProc") {
		param = {
			"no": $("#span_no").text()
		}
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "list") {
				$("#result").html(data);
			} else if (proc === "writeProc") {
				$("#writer").val("");
				$("#content").val("");
				$("#btnWrite").text("확인");
				chooseProc("list", "1", "");
			} else if (proc === "deleteProc") {
				chooseProc("list", "1", "");
			}
		}
	});
}

