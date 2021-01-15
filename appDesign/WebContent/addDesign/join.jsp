<%@page import="java.net.Inet4Address"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

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
					<th><a href="#">로그인</a></th>
					<th>192.168.56.1</th>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="padding: 50px 20px;">
		<form name="joinForm">
			<table border="1" align="center">
				<tr>
					<td colspan="2">
						<h2>회원가입</h2>
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
					<td>비밀번호확인</td>
					<td><input type="text" name="passwdChk" /></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" /><a href="#">[우편번호검색]</a></td>
				</tr>
				<tr>
					<td>기본주소</td>
					<td><input type="text" name="basicAddress" /></td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td><input type="text" name="detailAddress" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" id="btn">가입하기</button>
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

function save() {
	if (document.joinForm.id.value === "") {
		alert("아이디를 입력하세요.");
		document.joinForm.id.focus();
		return;
	}
	
	if (document.joinForm.passwd.value === "") {
		alert("비밀번호를 입력하세요.");
		document.joinForm.passwd.focus();
		return;
	}
	
	if (document.joinForm.passwd.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.joinForm.passwd.focus();
		return;
	}
	
	if (document.joinForm.passwdChk.value === "") {
		alert("비밀번호 확인을 입력하세요");
		document.joinForm.passwdChk.focus();
		return;
	}
	
	if (document.joinForm.passwdChk.value.includes(" ")) {
		alert("공백은 입력할 수 없습니다.");
		document.joinForm.passwdChk.focus();
		return;
	}
	
	if (document.joinForm.name.value === "") {
		alert("이름을 입력하세요.");
		document.joinForm.name.focus();
		return;
	}
	
	if (document.joinForm.address.value === "") {
		alert("우편번호를 입력하세요.");
		document.joinForm.address.focus();
		return;
	}
	
	if (document.joinForm.basicAddress.value === "") {
		alert("기본주소를 입력하세요.");
		document.joinForm.basicAddress.focus();
		return;
	}
	
	if (document.joinForm.detailAddress.value === "") {
		alert("상세주소를 입력하세요.");
		document.joinForm.detailAddress.focus();
		return;
	}
	
	if (confirm("등록하시겠습니까?")) {
		document.joinForm.method = "post";
		document.joinForm.action = "joinProc.jsp";
		document.joinForm.submit();
	}
}

btn.addEventListener('click', save);
</script>

</body>
</html>