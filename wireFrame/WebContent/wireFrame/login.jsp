<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script>
function login() {
	location.href="modify.jsp";
}
</script>
</head>
<body>

<div align="center">
	<form name="loginForm" method="POST" action="loginProc.jsp">
	  <table border="1" id="login-table">
	    <tr>
	      <td>아이디</td>
	      <td><input type="text" name="id" value="" /></td>
	    </tr>
	    <tr>
	      <td>비밀번호</td>
	      <td><input type="password" name="password" value="" /></td>
	    </tr>
	    <tr>
	      <td colspan="2"><button type="button" onclick="login();">로그인</button></td>
	    </tr>
	  </table>
	</form>
</div>

</body>
</html>