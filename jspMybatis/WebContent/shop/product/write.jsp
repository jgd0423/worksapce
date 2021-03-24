<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>
<!DOCTYPE html>
<html lang="ko">

<form>
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>상품등록</h2></td>
		</tr>
		<tr>
			<td style="align: center;">상품명</td>
			<td><input type="text" name="name" id="name" required /></td>
		</tr>
		<tr>
			<td style="align: center;">가격</td>
			<td><input type="text" name="price" id="price" required /></td>
		</tr>
		<tr>
			<td style="align: center;">상품설명</td>
			<td>
				<textarea 
					name="description" 
					id="description" 
					style="width: 300px; height: 100px;" 
					required
				></textarea>
			</td>
		</tr>
		<tr>
			<td style="align: center;">상품이미지</td>
			<td>
				<input type="file" name="file" accept='image/*' />
				<input type="file" name="file" accept='image/*' />
				<input type="file" name="file" accept='image/*' />
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2" height="50px">
				<button type="submit" id="btnWrite">등록하기</button>
				<button type="button" id="btnList">목록으로</button>
			</td>
		</tr>
	</table>
</form>

<script>

$(document).ready(() => {
	$("#name").select();
	$("#name").focus();
	
	$("#btnWrite").click(() => {
		if ($("#name").val() && $("#price").val() && $("#description").val()) {
			chooseProc('writeProc', '1', '');
		}
	});
	
	$("#btnList").click(() => {
		chooseProc('list', '1', '');
	});
});

</script>

</html>