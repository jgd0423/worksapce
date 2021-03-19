<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<span id="hahaha">111</span>
<hr />

<input type="checkbox" name="aaa" id="aaa" value="T" onclick="test()" />

<script>

function test() {
	if ($("input:checkbox[id=aaa]").is(":checked") === true) {
		const checkboxValue = $("#aaa").val();
		$("#hahaha").text(checkboxValue);
	} else {
		$("#hahaha").text("");
	}
	
}

</script>

</body>
</html>