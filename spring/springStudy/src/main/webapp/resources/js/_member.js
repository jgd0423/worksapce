$(document).ready(function() {
	if ($("#span_menu_gubun").text() == "member_login") {
		chooseProc("login", "1", "");
	} else if ($("#span_menu_gubun").text() == "member_modify") {
		chooseProc("modify", "1", "0");
	} else if ($("#span_menu_gubun").text() == "member_delete") {
		chooseProc("delete", "1", "0");
	} else {
		chooseProc("list", "1", "");
	}
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
	// var url = "${path}/member_servlet/" + proc + ".do";
	var url = $("#span_path").text() + "/member/" + proc + ".do";
	
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
			"id": $("#id").val(),
			"passwd": $("#passwd").val(),
			"passwdChk": $("#passwdChk").val(),
			"name": $("#name").val(),
			"gender": $("#gender").val(),
			"bornYear": $("#bornYear").val(),
			"sample6_postcode": $("#sample6_postcode").val(),
			"sample6_address": $("#sample6_address").val(),
			"sample6_detailAddress": $("#sample6_detailAddress").val(),
			"sample6_extraAddress": $("#sample6_extraAddress").val()
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
			"search_data": $("#span_search_data").text()
		};
	} else if (proc === "login") {
		param = {};
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "list") {
				$("#result").html(data);
			} else if (proc === "view") {
				$("#result").html(data);
			} else if (proc === "write") {
				$("#result").html(data);
			} else if (proc === "writeProc") {
				chooseProc("list", "1", "");
			} else if (proc === "modify") {
				$("#result").html(data);
			} else if (proc === "modifyProc") {
				$("#result").html(data);
			} else if (proc === "delete") {
				$("#result").html(data);
			} else if (proc === "deleteProc") {
				$("#result").html(data);
			} else if (proc === "login") {
				$("#result").html(data);
			} else if (proc === "showLogin") {
				$("#result").html(data);
			} else {
				$("#result").html(data);
			}
		}
	});
}