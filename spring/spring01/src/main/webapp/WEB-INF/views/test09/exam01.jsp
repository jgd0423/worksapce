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

<h2>test09.do / exam01.jsp</h2>

id : <input type="text" name="id" id="id"><br>
name : <input type="text" name="name" id="name"><br>
email : <input type="text" name="email" id="email"><br>
<input type="button" id="btnLogin" value="로그인">

<div id="result"></div>


<script>
	$("#btnLogin").click(() => {
		goProc();
	});

	function goProc() {
		const id = $("#id").val();
		const name = $("#name").val();
		const email = $("#email").val();
		const param = {
			"id": id,
			"name": name,
			"email": email
		};
		
		$.ajax({
			type: "post",
			data: param,
			url: "test09Proc.do",
			success: (data) => {
				$("#result").html("id: " + data.id + ", name: " + data.name + ", email: " + data.email);
			}
		});
	}
</script>
</body>
</html>