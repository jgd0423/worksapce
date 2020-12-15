<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록</title>
</head>
<body>

<h2>상품등록</h2>

<form name="product">
	<table border="1">
		<tr>
			<td>상품카테고리</td>
			<td>
				<input type="radio" name="cate" value="가구">가구
				<input type="radio" name="cate" value="의류">의류
				<input type="radio" name="cate" value="컴퓨터">컴퓨터
				<input type="radio" name="cate" value="가전">가전
			</td>
		</tr>
		<tr>
			<td>상품명</td>
			<td><input type="text" name="pname"></td>
		</tr>
		<tr>
			<td>가격</td>
			<td><input type="text" name="price"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="save();">등록하기</button>
			</td>
		</tr>
	</table>
</form>

<script>

function save() {
	if (confirm("등록하시겠습니까?")) {
		document.product.method = "post";
		document.product.action = "productProc.jsp";
		document.product.submit();		
	}
}

</script>

</body>
</html>