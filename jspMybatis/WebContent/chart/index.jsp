<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<script src="https://www.google.com/jsapi"></script>

<button type="button" id="btnCreateJson">Chart</button>
&nbsp;&nbsp;&nbsp;
<button type="button" id="btnGoogleChartJson">구글차트(json)</button>

<br><br>

<div id="result" style="height: 100%; position: relative;"></div>

<script>

$(document).ready(() => {
	$("#btnCreateJson").click(() => {
		goPage('createJson');
	});
	$("#btnGoogleChartJson").click(() => {
		goPage('googleChartJson');
	});
});

function goPage(proc) {
	let method_type = "post";
	let param = {};
	const url = `${path}/chart_servlet/\${proc}.do`;
	
	$.ajax({
		type: method_type,
		data: param,
		url: url,
		success: (data) => {
			$("#result").html(data);
		}
	});
}

</script>