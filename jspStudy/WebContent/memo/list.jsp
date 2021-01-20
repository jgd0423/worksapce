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
			<td colspan="4" height="200" align="center">등록된 내용이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${list.size() > 0 }">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.no }</td>
				<td>${dto.writer }</td>
				<td><a href="#" onclick="goPage('memo_view', '${dto.no }')">${dto.content }</a></td>
				<td>${dto.regiDate }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" height="50" align="right">
				<button type="button" onclick="goPage('memo_write', '')">메모하기</button>
			</td>
		</tr>
	</c:if>
</table>

<script>

function goPage(value1, value2) {
	if (value1 === 'memo_write') {
		location.href = '${path}/memo_servlet/write.do';		
	} else if (value1 === 'memo_view') {
		location.href = '${path}/memo_servlet/view.do?no=' + value2;
	}
	
}

</script>