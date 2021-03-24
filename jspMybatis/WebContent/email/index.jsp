<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<div id="result" style="border: 1px solid red; position: relative;"></div>

<script>

$(document).ready(() => {
	<c:if test="${menu_gubun == 'email_index'}">
		goPage('write');
	</c:if>
});

function goPage(proc) {	
	const url = `${path}/email_servlet/\${proc}.do`;
	let param = {};
	if (proc === "write") {
		param = {};
	} else if (proc === 'writeProc') {
		param = {
				"fromName": $("#fromName").val(),
				"fromEmail": $("#fromEmail").val(),
				"toEmail": $("#toEamil").val(),
				"subject": $("#subject").val(),
				"content": $("#content").val()
		}
	}
	
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: (data) => {
			if (proc === "write") {
				$("#result").html(data);
			} else {
				goPage("write");
			}
		}
	});
}

</script>