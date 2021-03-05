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
			$("#span_no").text("");
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
	const url = `${path}/mall_servlet/\${proc}.do`;

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
				chooseProc('view', '', $("#span_no").text());
			} else {
				$("#result").html(data);
			}
		}
	});
}

</script>