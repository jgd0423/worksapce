<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<script>

chartType = "${chart_type}";
chartSubject = "${chart_subject}";
chartJsonFileName = "${chart_jsonFileName}";
curveType = "";

// 구글 차트 라이브러리 로딩
google.load('visualization', '1', {
	'packages': ['corechart']
});

google.setOnLoadCallback(drawChart);
function drawChart(
		chartType = "${chart_type}", 
		chartSubject = "${chart_subject}",
		chartJsonFileName = "${chart_jsonFileName}",
		curveType = ""
		) {
	var jsonData = $.ajax({
		url: "${path}/attach/json/" + chartJsonFileName,
		dataType: "json",
		async: false
	}).responseText;
	console.log(jsonData);
	
	var data = new google.visualization.DataTable(jsonData);
	let chart;
	
	if (chartType === "LineChart") {
		chart = new google.visualization.LineChart(document.getElementById('chart_div'));
	} else if (chartType === "ColumnChart") {
		chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
	} else {
		chart = new google.visualization.PieChart(document.getElementById('chart_div'));
	}
	
	chart.draw(data, {
		title: chartSubject,
		curveType: curveType,
		width: 600,
		height: 440
	});
}

</script>

<div id="chart_div"></div>

<button 
	id="btn" 
	type="button" 
	onclick="drawChart('PieChart', '${chart_subject}', '${chart_jsonFileName}', '')"
>
	PieChart
</button>
&nbsp;&nbsp;&nbsp;
<button 
	id="btn" 
	type="button" 
	onclick="drawChart('LineChart', '${chart_subject}', '${chart_jsonFileName}', '')"
>
	LineChart(직선)
</button>
&nbsp;&nbsp;&nbsp;
<button 
	id="btn" 
	type="button" 
	onclick="drawChart('LineChart', '${chart_subject}', '${chart_jsonFileName}', 'function')"
>
	LineChart(곡선)
</button>
&nbsp;&nbsp;&nbsp;
<button 
	id="btn" 
	type="button" 
	onclick="drawChart('ColumnChart', '${chart_subject}', '${chart_jsonFileName}', '')"
>
	ColumnChart
</button>
&nbsp;&nbsp;&nbsp;