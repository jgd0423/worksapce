<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_menu.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>test08.do / exam01.jsp</h2>

<h2>로그인</h2>
id : <input type="text" name="id" id="id"><br>
passwd : <input type="password" name="passwd" id="passwd"><br>
<input type="button" id="btnLogin" value="로그인">

<div id="result"></div>


<script>
	$("#btnLogin").click(() => {
		goProc();
	});

	function goProc() {
		const url = "${path}/test08Proc.do";
		console.log(url);
		const param = {
			"id": $("#id").val(),
			"passwd": $("#passwd").val(),
		};
		
		$.ajax({
			type: "post",
			data: param,
			url: url,
			success: (data) => {
				$("#result").html(data);
			}
		});
	}
</script>
</body>
</html>