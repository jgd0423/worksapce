<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

menu_gubun : ${menu_gubun }<br>
yearMonthDayMap : ${yearMonthDayMap }<br>
ip : ${ip }<br>
tbl : <span id="span_tbl">${tbl }</span><br>
pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
no : <span id="span_no">${no }</span><br>
search_option : <span id="span_search_option">${search_option }</span><br>
search_data : <span id="span_search_data">${search_data }</span><br>

<input type="text" name="a" style="display: ;" /><br><!-- ajax 테스트를 위한 것 -->

<div id="result" style="height: 500px;"></div>


<script>

$(document).ready(() => {
	<c:if test="${menu_gubun == 'board_index'}">
		goPage('list', '');
		//goPage('write', '');
	</c:if>
});


function goPage(gubun, no) {
	const url = `${path}/board_servlet/\${gubun}.do`;
	let param = {};
	
	if (gubun === 'write') {
			$("#span_no").text("");
	} else if (gubun === 'writeProc') {
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
	} else if (gubun === 'list') {
		param = {
				"tbl": $("#span_tbl").text(),
				"pageNumber": $("#span_pageNumber").text(),
				"search_option": $("#span_search_option").text(),
				"search_data": $("#span_search_data").text()
		};
	} else if (gubun === 'view') {
		$("#span_no").text(no);
		param = {
				"no": $("#span_no").text(),
				"tbl": $("#span_tbl").text(),
				"pageNumber": $("#span_pageNumber").text(),
				"search_option": $("#span_search_option").text(),
				"search_data": $("#span_search_data").text(),
				"view_passwd": $("#view_passwd").val()
		};
	} else if (gubun === 'reply') {
		param = {
				"no": $("#span_no").text()
		};
	} else if (gubun === 'modify') {
		param = {
				"no": $("#span_no").text()
		};
	} else if (gubun === 'modifyProc') {
		param = {
				"no": $("#span_no").text(),
				"writer": $("#writer").val(),
				"email": $("#email").val(),
				"passwd": $("#passwd").val(),
				"subject": $("#subject").val(),
				"content": $("#content").val(),
				"noticeGubun": $("#noticeGubun").val(),
				"secretGubun": $("#secretGubun").val()
		};
	}
		
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (gubun === 'writeProc' || gubun === 'deleteProc') {
				choosePage(1);
			} else if (gubun === 'modifyProc') {
				goPage('view', $("#span_no").text());				
			} else {
				$("#result").html(data);
			}
		}
	});
}

function choosePage(pageNum) {
	$("#span_pageNumber").text(pageNum);
	$("#span_no").text("");
	goPage('list', '');
}

// function clickChk(gubun) {
// 	if (gubun === 'noticeGubun') {
// 		if ($("input:checkbox[name=noticeGubunCheckBox]").is(":checked")) {
// 			$("#noticeGubun").val("T");
// 		} else {
// 			$("#noticeGubun").val("");
// 		}
// 	} else {
// 		if ($("input:checkbox[name=secretGubunCheckBox]").is(":checked")) {
// 			$("#secretGubun").val("T");
// 		} else {
// 			$("#secretGubun").val("");
// 		}
// 	}
// }

function clickChk(gubun) {
	if ($(`input:checkbox[name=\${gubun}CheckBox]`).is(":checked")) {
		$(`#\${gubun}`).val("T");
	} else {
		$(`#\${gubun}`).val("");
	}
}

</script>