<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

proc : <span id="span_proc"></span><br>
pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
no : <span id="span_no">${no }</span><br>
search_option : <span id="span_search_option">${search_option }</span><br>
search_data : <span id="span_search_data">${search_data }</span><br>
jumun_su : <span id="span_jumun_su"></span>

<input type="text" name="a" style="display: ;" /><br><!-- ajax 테스트를 위한 것 -->

<div id="result" style="height: 100%;"></div>

<script>

$(document).ready(() => {
	<c:if test="${menu_gubun == 'mall_index'}">
		chooseProc('list', '1', '');		
	</c:if>
});

function chooseProc(proc, pageNumber, no) {
	if (proc === "write") {
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
	
	if (proc === "write") {
		param = {};
	} else if (proc === "list") {
		param.pageNumber = $("#span_pageNumber").text();
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === 'writeProc') {
				chooseProc('list', '1', '');
			} else if (proc === 'modifyProc') {
				chooseProc('view', '', $("#span_no").text());
			} else if (proc === 'deleteProc') {
				chooseProc('list', '1', '');
			} else {
				$("#result").html(data);				
			}
		}
	});
}

</script>