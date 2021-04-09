<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2" align="center">
			<h2>수정하기</h2>
		</td>
	</tr>
	<tr>
		<td>번호</td>
		<td>${dto.no }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input id="email" type="text" name="email" value="${dto.email }"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><input id="content" type="text" name="content" value="${dto.content }"></td>
	</tr>
	<tr>
		<td>등록일시</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td colspan="2" height="50" align="center">
			<button type="button" onclick="chooseProc('modifyProc', '1', ${dto.no })">수정하기</button>
		</td>
	</tr>
</table>
