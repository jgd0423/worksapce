<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/inc_header.jsp" %>
<% 

HttpSession sessionForCheck = request.getSession();
String cookId = (String)sessionForCheck.getAttribute("cookId");

if (cookId == null) {
	out.println("<script>");
	out.println("location.href='login.jsp'");
	out.println("</script>");
}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
</head>
<body>

<h2>${cookId }님 환영합니다. <a href="${path }/member_servlet/logout.do">[로그아웃]</a></h2>
<h2>비밀번호 수정 페이지입니다.</h2>
세션ID : ${cookId }

<form name="changeForm">
	<table border="1" width="300px">
		<tr>
			<td width="150">비밀번호:</td>
			<td><input type="text" name="passwd" /></td>
		</tr>
		<tr>
			<td>비밀번호확인:</td>
			<td><input type="text" name="passwdChk" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" onclick="changePasswd();">비밀번호변경하기</button>
			</td>
		</tr>
	</table>
</form>

<script>
function changePasswd() {
	if (confirm("변경하시겠습니까?")) {
		if (document.changeForm.passwd.value === "") {
			alert("비밀번호를 입력하세요.");
			document.changeForm.passwd.focus();
			return;
		}
		
		if (document.changeForm.passwdChk.value === "") {
			alert("비밀번호확인을 입력하세요.");
			document.changeForm.passwdChk.focus();
			return;
		}
		
		document.changeForm.method = 'post';
		document.changeForm.action = '${path}/member_servlet/changeProc.do';
		document.changeForm.submit();
	}
}

</script>

</body>
</html>