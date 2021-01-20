<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

${list.size() }개의 레코드가 있습니다.

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="4" align="center">
			<h2>메모목록</h2>
		</td>
	</tr>
	<tr>
		<td>ID</td>
		<td>이름</td>
		<td>메모</td>
		<td>날짜</td>
	</tr>
	<c:if test="${list.size() == 0 }">
		<tr>
			<td colspan="4" height="200" align="center">등록된 메모가 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.no }</td>
				<td>${dto.writer }</td>
				<td>${dto.content }</td>
				<td>${dto.regiDate }<button type="button" onclick="deleteInfo('${dto.no}')" >삭제</button></td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<script>

function list() {
	let param = "search_gubun=&sdata=";
	$.ajax({
		type: "post",
		data: param,
		url: "${path}/memo_servlet/list.do",
		success: (result) => {
			$("#result").html(result);
		}
	});
}

function deleteInfo(no) {
	let param = "no=" + no;
	$.ajax({
		type: "post",
		data: param,
		url: "${path}/memo_servlet/deleteInfo.do",
		success: () => {
			list();
		}
	});
}

</script>

