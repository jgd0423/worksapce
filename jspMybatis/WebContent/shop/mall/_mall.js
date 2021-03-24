$(document).ready(() => {
	chooseProc('mall_list', '1', '');		
});

function chooseProc(proc, pageNumber, no) {
	if (proc === 'mall_list') {
		$("#span_proc").text(proc);
		$("#span_no").text("");
	} else if (proc === 'mall_view') {
		$("#span_proc").text(proc);
		$("#span_no").text(no);
	} else if (proc === 'mall_search') {
		proc = 'mall_list';
		$("#span_proc").text(proc);
		$("#span_no").text("");
		$("#span_search_option").text($("#search_option").val());
		$("#span_search_data").text($("#search_data").val());
	} else if (proc === 'cart_add') {
		$("#span_proc").text(proc);
		$("#span_no").text(no);
		$("#span_jumun_su").text($("#amount").val());
	} else if (proc === 'cart_list') {
		$("#span_proc").text(proc);
		$("#span_no").text("");
		$("#span_jumun_su").text("");
	} else if (proc === 'cart_view') {
		if (confirm('제품상세보기 페이지로 이동하시겠습니까?')) {
			proc = 'mall_view';
			$("#span_proc").text(proc);
			$("#span_no").text(no);
		} else {
			return;
		}
	} else if (proc === 'cart_clear') {
		if (confirm('정말 비우시겠습니까?')) {
			$("#span_proc").text(proc);
			$("#span_no").text("");
			$("#span_jumun_su").text("");
		} else {
			return;
		}
	}
	
	if (pageNumber != '') {
		$("#span_pageNumber").text(pageNumber);
	}
	
	goPage(proc);
}

function goPage(proc) {
	let param = {};
	const url = $("#span_path").text() + "/mall_servlet/" + proc + ".do";

	if (proc === "mall_list") {
		param.pageNumber = $("#span_pageNumber").text();
		param.search_option = $("#span_search_option").text();
		param.search_data = $("#span_search_data").text();
	} else if (proc === "mall_view") {
		param.no = $("#span_no").text();
		param.search_option = $("#span_search_option").text();
		param.search_data = $("#span_search_data").text();
	} else if (proc === "cart_add") {
		param.no = $("#span_no").text();
		param.amount = $("#span_jumun_su").text();
	} else if (proc === "cart_list") {
		param.pageNumber = $("span_pageNumber").text();
		//param.search_option = $("#span_search_option").text();
		//param.search_data = $("#span_search_data").text();
	} else if (proc === "cart_clear") {
		let chkNo = '';
		$('input[name="chk"]:checked').each(function(index) {
			chkNo += ',';
			chkNo += $(this).val();
		});
		
		param.chkNo = chkNo;
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === 'cart_add' || proc === 'cart_clear') {
				proc = 'cart_list';
				chooseProc(proc, '1', '');
			} else if (proc === 'modifyProc') {
				chooseProc('view', '1', $("#span_no").text());
			} else {
				$("#result").html(data);
			}
		}
	});
}