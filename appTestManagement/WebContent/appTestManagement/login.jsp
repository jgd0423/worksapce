<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
.loginBtn {
  width: 400px;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer
}

#gongIn {
  background-color: #800000;
}

#naver {
  background-color: #4CAF50;
}

#kakao {
	background-color: #FFD700;
}
</style>
</head>
<body>

<table border="1" width="100%">
	<tr>
		<td>
			<table border="0" align="center" width="60%">
				<tr>
					<th height="30"><a href="#">HOME</a></th>
					<th><a href="#">회원관리</a></th>
					<th><a href="#">게시판(S)</a></th>
					<th><a href="#">게시판(M)</a></th>
					<th><a href="#">상품관리</a></th>
					<th><a href="login.jsp">로그인</a></th>
					<th>192.168.56.1</th>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="padding: 50px 20px;">
			<form name="loginForm">
				<table border="1" align="center">
					<tr>
						<td colspan="2">
							<h2>로그인</h2>
						</td>
					</tr>
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id" /></td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="text" name="passwd" /></td>
					</tr>
					<tr>
						<td colspan="2">
							<a href="#" id="btn">로그인</a><br><br>
							<button type="button" name="" id="gongIn" class="loginBtn">로그인-공인인증서</button><br><br>
							<button type="button" name="" id="naver" class="loginBtn">로그인-네이버</button><br><br>
							<button type="button" name="" id="kakao" class="loginBtn">로그인-카카오</button><br>
						</td>
					</tr>
				</table>
			</form>
		</td>
	</tr>
	<tr>
		<td align="center">
			<table>
				<tr>
					<td>경기도 성남시 대왕판교로 000</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<script>
const btn = document.querySelector('#btn');

function goToLoginProc() {
	if (document.loginForm.id.value === "") {
		alert("아이디를 입력하세요.");
		document.loginForm.id.focus();
		return;
	}
	
	if (document.loginForm.id.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.loginForm.id.focus();
		return;
	}
	
	if (document.loginForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.loginForm.passwd.focus();
		return;
	}	
	
	if (document.loginForm.passwd.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.loginForm.passwd.focus();
		return;
	}
	
	if (confirm('로그인하시겠습니까?')) {
		document.loginForm.method = 'post';
		document.loginForm.action = 'loginProc.jsp';
		document.loginForm.submit();
	}
}

btn.addEventListener('click', goToLoginProc);

</script>

</body>
</html>