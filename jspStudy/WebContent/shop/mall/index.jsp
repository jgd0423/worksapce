<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

proc : <span id="span_proc"></span><br>
pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
no : <span id="span_no">${no }</span><br>
search_option : <span id="span_search_option">${search_option }</span><br>
search_data : <span id="span_search_data">${search_data }</span><br>
jumun_su : <span id="span_jumun_su"></span><br>

<input type="text" name="a" style="display: ;" /><br><!-- ajax 테스트를 위한 것 -->

<div id="result" style="height: 100%;"></div>

<script>

$(document).ready(() => {
	<c:if test="${menu_gubun == 'mall_index'}">
		chooseProc('mall_list', '1', '');		
	</c:if>
});

function chooseProc(proc, pageNumber, no) {
	if (proc === 'mall_list') {
		$("#span_no").text("");
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
	let param = {};
	const url = `${path}/mall_servlet/\${proc}.do`;

	if (proc === "mall_list") {
		param = {};
		param.pageNumber = $("#span_pageNumber").text();
		param.search_option = $("#span_search_option").text();
		param.search_data = $("#span_search_data").text();
	} else if (proc === "mall_view") {
		param = {};
		param.no = $("#span_no").text();
	} else if (proc === "cart_add") {
		param = {};
		param.no = $("#span_no").text();
		param.amount = $("#amount").val();
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			$("#result").html(data);				
		}
	});
}

</script>