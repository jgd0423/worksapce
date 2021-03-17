<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>한줄 메모장</h2></td>
		</tr>
		<tr>
			<td width="150">이름</td>
			<td><input type="text" id="writer" name="writer" /></td>
		</tr>
		<tr>
			<td>메모</td>
			<td><input type="text" id="content" name="content" style="width: 400px" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" id="btnSave">확인</button>
			</td>
		</tr>
	</table>
</form>
