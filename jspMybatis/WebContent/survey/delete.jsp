<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="deleteForm" id="deleteForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>삭제하기</h2></td>
		</tr>
		<tr>
			<td width="150">question</td>
			<td>${dto.question }</td>
		</tr>
		<tr>
			<td>ans1</td>
			<td>${dto.ans1 }</td>
		</tr>
		<tr>
			<td>ans2</td>
			<td>${dto.ans2 }</td>
		</tr>
		<tr>
			<td>ans3</td>
			<td>${dto.ans3 }</td>
		</tr>
		<tr>
			<td>ans4</td>
			<td>${dto.ans4 }</td>
		</tr>
		<tr>
			<td>status</td>
			<td>${dto.status == '1' ? '진행중' : '종료'}</td>
		</tr>
		<tr>
			<td colspan="2">기간: ${dto.start_date } ~ ${dto.last_date }</td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" id="btnDelete" onclick="chooseProc('deleteProc', '1', '0')">삭제하기</button>
				<button type="button" id="btnList" onclick="chooseProc('list', '1', '')">목록으로</button>
			</td>
		</tr>
	</table>
</form>
