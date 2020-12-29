<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 

String cookId = null;

cookId = (String)session.getAttribute("cookId");

if (cookId != null) {
	out.println("<script>");
	out.println("alert('로그인 되어있습니다.')");
	out.println("location.href='list.jsp';");
	out.println("</script>");
}

%>

<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

<h2>로그인</h2>

<form name="loginForm">
아이디 <input type="text" name="id"><br>
비밀번호 <input type="text" name="passwd"><br>
<button type="button" onclick="goToLoginProc();">로그인</button>
</form>

<script>

function goToLoginProc() {
	if (confirm("로그인하시겠습니까?")) {
		document.loginForm.method = "post";
		document.loginForm.action = "loginProc.jsp";
		document.loginForm.submit();
	}
}

</script>

</body>
</html>