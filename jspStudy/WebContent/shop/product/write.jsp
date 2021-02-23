<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>상품등록</h2></td>
	</tr>
	<tr>
		<td style="align: center;">상품명</td>
		<td><input type="text" name="name" id="name" /></td>
	</tr>
	<tr>
		<td style="align: center;">가격</td>
		<td><input type="text" name="price" id="price" /></td>
	</tr>
	<tr>
		<td style="align: center;">상품설명</td>
		<td>
			<textarea 
				name="description" 
				id="description" 
				style="width: 300px; height: 100px;" 
				wrap="hard"
			>${dto.description }</textarea>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px">
			<button type="button" id="btnWrite">등록하기</button>
			<button type="button" id="btnList">목록으로</button>
		</td>
	</tr>
</table>

<script>

$(document).ready(() => {
	$("#writer").focus();
	
	$("#btnWrite").click(() => {
		//if (confirm('등록하시겠습니까?')) {
			goPage('writeProc', '');
		//}
	});
	
	$("#btnList").click(() => {
		goPage('list', '');
	});
});

</script>