<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 입력</title>
</head>
<body>

<h2>상품 입력</h2>

<form name="product">
	<table>
		<tr>
			<td>상품 이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		<tr>
			<td>상품 가격</td>
			<td><input type="text" name="price"></td>
		</tr>
	</table>
	<button type="button" onclick="save();">입력</button>
</form>

<script>

function save() {
	if (document.product.name.value === "") {
		alert("상품이름을 입력하세요.");
		document.product.name.value();
		return false;
	}
	
	if (document.product.price.value === "") {
		alert("상품가격을 입력하세요.");
		document.product.price.value();
		return false;
	}

	if (confirm("등록하시겠습니까?")) {
		document.product.method = "post";
		document.product.action = "inputProductsProc.jsp";
		document.product.submit();		
	}
}

</script>

</body>
</html>