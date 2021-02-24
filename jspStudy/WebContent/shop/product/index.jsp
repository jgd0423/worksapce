<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

proc : <span id="span_proc"></span><br>
pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
no : <span id="span_no">${no }</span><br>
search_option : <span id="span_search_option">${search_option }</span><br>
search_data : <span id="span_search_data">${search_data }</span><br>

<input type="text" name="a" style="display: ;" /><br><!-- ajax 테스트를 위한 것 -->

<div id="result" style="height: 100%;"></div>

<script>

$(document).ready(() => {
	<c:if test="${menu_gubun == 'product_index'}">
		chooseProc('write', '1', '');		
	</c:if>
});

function chooseProc(proc, pageNumber, no) {
	if (proc === "write") {
		$("#span_no").text("");		
	} else if (proc === "writeProc") {
	}
	
	if (proc !== '') {
		$("#span_proc").text(proc);
	}
	if (pageNumber !== '') {
		$("#span_pageNumber").text(pageNumber);
	}
	if (no !== '') {
		$("#span_no").text(no);
	}
	
	goPage(proc);
}

function goPage(proc) {
	let param;
	let processData;
	let contentType;
	const url = `${path}/product_servlet/\${proc}.do`;
	
	if (proc === "write") {
		param = {};
	} else if (proc === "writeProc") {
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
		
// 		console.log($('input[name="file"]')[0].files[0]);
// 		console.log($('input[name="file"]')[1].files[0]);
// 		console.log($('input[name="file"]')[2].files[0]);
		
		const fileCounter = parseInt($('input[name="file"]').length);
		for (i = 0; i < fileCounter; i++) {
			param.append(`file\${i}`, $('input[name="file"]')[i].files[0]);
		}
	}
	
	$.ajax({
		type: "post",
		data: param,
		processData: processData,
		contentType: contentType,
		url: url,
		success: (data) => {
			$("#result").html(data);
		}
	});
}

</script>
