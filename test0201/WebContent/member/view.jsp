<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2" align="center">
			<h2>상세보기</h2>
		</td>
	</tr>
	<tr>
		<td>번호</td>
		<td>${dto.no }</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>ID : ${dto.id } / 세션ID : ${sessionScope.cookId }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${dto.passwd }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${dto.gender }</td>
	</tr>
	<tr>
		<td>출생년도</td>
		<td>${dto.bornYear }</td>
	</tr>
	<tr>
		<td>가입일시</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>
			<table border="1">
				<tr>
					<td>우편번호</td>
					<td>${dto.postcode }</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${dto.address }</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>${dto.detailAddress }</td>
				</tr>
				<tr>
					<td>참고항목</td>
					<td>${dto.extraAddress }</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="50" align="center">
			<button type="button" onclick="goPage('member_list', '', '')">목록으로</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" onclick="goPage('member_modify', '', ${dto.no })">수정하기</button>
			&nbsp;&nbsp;&nbsp;
			<button type="button" id="btnSave">JSON출력</button>
		</td>
	</tr>
</table>
<br>
<br>
result_json: <span id="result_json"></span><br>
<hr>
no: <span id="json_no"></span><br>
id: <span id="json_id"></span><br>
passwd: <span id="json_passwd"></span><br>
name: <span id="json_name"></span><br>
gender: <span id="json_gender"></span><br>
bornYear: <span id="json_bornYear"></span><br>
postcode: <span id="json_postcode"></span><br>
address: <span id="json_address"></span><br>
detailAddress: <span id="json_detailAddress"></span><br>
extraAddress: <span id="json_extraAddress"></span><br>
regiDate: <span id="json_regiDate"></span><br>


<script>

$(document).ready(function () {
	$('#btnSave').click(function () {
		json01Proc();
	});
});

function json01Proc() {
	var param = {
			"no": ${dto.no}
	};
	
	$.ajax({
		type: 'post',
		data: param,
		datatype: 'JSON',
		url: '${path}/member_servlet/json.do',
		success: function (data) {
			$('#result_json').text(data);
			console.log(data);
			
			var json_info = JSON.parse(data);
			$("#json_no").text(json_info.no);
			$("#json_id").text(json_info.id);
			$("#json_passwd").text(json_info.passwd);
			$("#json_name").text(json_info.name);
			$("#json_gender").text(json_info.gender);
			$("#json_bornYear").text(json_info.bornYear);
			$("#json_postcode").text(json_info.postcode);
			$("#json_address").text(json_info.address);
			$("#json_detailAddress").text(json_info.detailAddress);
			$("#json_extraAddress").text(json_info.extraAddress);
			$("#json_regiDate").text(json_info.regiDate);
		}
	});
}

function goPage(value1, value2, value3) {
	if (value1 === 'member_list') {
		location.href = '${path}/member_servlet/list.do';
	} else if (value1 === 'member_modify') {
		location.href = '${path}/member_servlet/modify.do?pageNumber=' + value2 + '&no=' + value3;
	}
}


</script>

</body>
</html>