<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

proc : <span id="span_proc"></span><br>
pageNumber : <span id="span_pageNumber">${pageNum }</span><br>
no : <span id="span_no">${no }</span><br>
search_option : <span id="span_search_option">${search_option }</span><br>
search_data : <span id="span_search_data">${search_data }</span><br>
path : <span id="span_path">${path }</span><br>

<form name="writeForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>한줄 메모장</h2></td>
		</tr>
		<tr>
			<td width="150">이름</td>
			<td><input type="text" id="writer" name="writer" />${dto.writer }</td>
		</tr>
		<tr>
			<td>메모</td>
			<td><input type="text" id="content" name="content" style="width: 400px" />${dto.content }</td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" id="btnWrite">확인</button>
			</td>
		</tr>
	</table>
</form>



<!-- 결과가 출력되는 영역 -->
<div id="result"></div>

<script type="text/javascript" src="${path }/memo/_memo.js"></script>


