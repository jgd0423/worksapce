<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<div id="result" style="border: 1px solid red; height: 500px"></div>


<script>

$(document).ready(() => {
	<c:if test="${menu_gubun == 'board_index'}">
		// goPage('list');
		goPage('write');
	</c:if>
});


function goPage(gubun) {
	let param = {};
	if (gubun === 'writeProc') {
		param = {
				"writer": $("#writer").val(),
				"email": $("#email").val(),
				"passwd": $("#passwd").val(),
				"subject": $("#subject").val(),
				"content": $("#content").val(),
				"noticeGubun": $("#noticeGubun").val(),
				"secretGubun": $("#secretGubun").val()
		};
	} else if (gubun === 'write') {
		param = {};		
	}
	
	const url = `${path}/board_servlet/\${gubun}.do`;
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (gubun === 'writeProc') {
				choosePage(1);
			} else {
				$("#result").html(data);			
			}
		}
	});
}

function choosePage(pageNum) {
	// $("#span_pageNumber").text(pageNum);
	// $("#span_no").text("");
	goPage('list');
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