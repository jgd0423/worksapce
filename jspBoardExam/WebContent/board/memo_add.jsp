<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모장</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>

<script>
$(document).ready(function() {
	$("#btnSave").click(function() {
		insert();
	});
});
function insert() {
	let writer = $("#writer").val();
	let memo = $("#memo").val();
	let param = "writer=" + writer + "&memo=" + memo;
	$.ajax({
		type: "post",
		data: param,
		url: "처리페이지",
		success: function() {
			list();
			$("#writer").val("");
			$("#memo").val("");
		}
	});
};
</script>

</head>
<body>

<h1>메모장</h1>

이름 : <input type="text" id="writer" /><br>
메모 : <input type="text" id="memo" size="50"/><br>
<input type="button" id="btnSave" value="확인">

</body>
</html>