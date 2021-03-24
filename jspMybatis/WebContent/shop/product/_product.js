$(document).ready(() => {
	chooseProc('list', '1', '');		
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
	let param = {};
	let processData;
	let contentType;
	const url = $("#span_path").text() + "/product_servlet/" + proc + ".do";
	
	if (proc === "write") {
		param = {};
	} else if (proc === "writeProc" || proc === "modifyProc") {
		// 파일 첨부할 때 false 처리 해야함 (왜?)
		processData = false;
		contentType = false;
		
		param = new FormData();
		
		if (proc === 'modifyProc') {
			param.append("no", $("#span_no").text());
		}

		param.append("name", $("#name").val());
		param.append("price", $("#price").val());
		param.append("description", $("#description").val());
		
		const fileCounter = parseInt($('input[name="file"]').length);
		for (i = 0; i < fileCounter; i++) {
			param.append(i, $('input[name="file"]')[i].files[0]);
		}
	} else if (proc === "view" || proc === 'modify' || proc === 'delete' || proc === 'deleteProc') {
		param.no = $("#span_no").text();
	} else if (proc === "list") {
		param.pageNumber = $("#span_pageNumber").text();
		param.search_option = $("#span_search_option").text();
		param.search_data = $("#span_search_data").text();
	}
	
	$.ajax({
		type: "post",
		data: param,
		processData: processData,
		contentType: contentType,
		url: url,
		success: (data) => {
			if (proc === 'writeProc') {
				chooseProc('list', '1', '');
			} else if (proc === 'modifyProc') {
				chooseProc('view', '1', $("#span_no").text());
			} else if (proc === 'deleteProc') {
				chooseProc('list', '1', '');
			} else {
				$("#result").html(data);				
			}
		}
	});
}