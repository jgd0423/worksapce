<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

String path = request.getContextPath();

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모장</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(() => {
	list();
	$("#btnSave").click(() => {
		insert();
	});
});

function insert() {
	let writer = $("#writer").val();
	let content = $("#content").val();
	let param = "writer=" + writer + "&content=" + content;
	$.ajax({
		type: "post",
		data: param,
		url: "<%=path%>/memo_servlet/write.do",
		success: () => { // 콜백함수
			list();
			$("#writer").val("");
			$("#content").val("");
		}
	});
}

function list() {
	let param = "search_gubun=&sdata=";
	$.ajax({
		type: "post",
		data: param,
		url: "<%=path%>/memo_servlet/list.do",
		success: (result) => {
			$("#result").html(result);
		}
	});
}
</script>
</head>
<body>

<h2>메모장</h2>

이름 : <input type="text" id="writer" size="10" /><br>
내용 : <input type="text" id="content" size="40" /><br>
<input type="button" id="btnSave" value="확인" />

<!-- 결과가 출력되는 영역 -->
<div id="result"></div>

</body>
</html>